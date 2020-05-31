package com.example.demo.services;

import com.example.demo.model.PriceDetails;
import com.example.demo.model.Product;
import com.example.demo.repository.PriceDetailsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService{
    private final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    private PriceDetailsRepository priceDetailsRepository;
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment env;
    @Override
    public Product getProductDetails(int id) throws IOException {
        logger.info("in  getProductDetails ");
        String productdesc = productServiceImpl.getProductDesc(id);
        PriceDetails priceDetails=productServiceImpl.getProductPrice(id);
        if(priceDetails==null){
                logger.info("Price Details with product id " + id+ "not found in MonoDb");
                throw new MongoException("product with id" + id + " not found in MongoDb");
            }

        Product product= new Product(id,productdesc,priceDetails);
        logger.info("prodDetails: "+product);
        return product;
    }

    @Override
    public Product updateProductDetails(int id, Product updatedProduct) throws Exception {
        PriceDetails priceDetails=updatedProduct.getPriceInfo();
        if(priceDetails.getCurrency_code()==null || priceDetails.getValue()<=0.0){
            throw new Exception(" Please give correct product price and currency code details");
        }
        priceDetails.setId(id);
        String productName=getProductDesc(id);
        updatedProduct.setName(productName);
        PriceDetails pd =updateProductPrice(id,updatedProduct);
        updatedProduct.setPriceInfo(pd);
        return updatedProduct;
    }

    @Cacheable(value = "productPriceCache", key = "#id")
    public PriceDetails getProductPrice(int id) throws MongoException {
        logger.info("in getProductPrice");
        PriceDetails pd = null;
        Optional<PriceDetails> OptionalPriceDetails =priceDetailsRepository.findById(id);
        if(OptionalPriceDetails.isPresent()) {
         pd= OptionalPriceDetails.get();
        }
        logger.info("price is " + pd);
        return pd;
    }
    @CachePut(value = "productPriceCache", key = "#id")
    public PriceDetails updateProductPrice(int id,Product newProduct) throws MongoException{
        PriceDetails newProductPrice=newProduct.getPriceInfo();
        newProductPrice.setId(id);
        Optional<PriceDetails> pd = priceDetailsRepository.findById(id);
        if(!pd.isPresent()) {
            throw new MongoException("price details for product with id="+id+" not found in mongo db for collection 'productprice'");
        }
        else{
            newProductPrice=priceDetailsRepository.save(newProduct.getPriceInfo());
        }
        return newProductPrice;
    }

    private String getProductDesc(int id) throws IOException {
        String name = "";
        logger.info("in getProductDesc of service class");
        String url=	env.getProperty("restUrl1")+id+env.getProperty("restUrl2");
        ResponseEntity<String> response= restTemplate.getForEntity(url,String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode treeRoot=null;
        String jsonString=response.getBody();
        logger.info(response.toString());
        if(jsonString!=null||!"".equals(jsonString)) {
            try {
                treeRoot = mapper.readTree(jsonString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (treeRoot.findValue("product") != null) {
                treeRoot = treeRoot.findValue("product");
                if (treeRoot.findValue("item") != null) {
                    treeRoot = treeRoot.findValue("item");
                    if (treeRoot.findValue("product_description") != null) {
                        treeRoot = treeRoot.findValue("product_description");
                        if (treeRoot.findValue("title") != null) {
                            name = treeRoot.findValue("title").asText();
                        }
                    }
                }
            }
        }
        logger.info("name is " + name);
        return name;
    }
}

package com.example.demo;

import com.example.demo.services.ProductService;
import com.example.demo.services.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.example.demo.controller.ProductController;
import com.example.demo.model.PriceDetails;
import com.example.demo.model.Product;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;


@ContextConfiguration(classes=ProductController.class)
@WebMvcTest(controllers = ProductController.class)
@RunWith(SpringRunner.class)
public class WebApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name="productServiceImpl")
    private ProductService productServiceImpl;
    private int PRODUCT_ID=13860428;

    Product product= new Product();
    PriceDetails priceDetails= new PriceDetails();
    @Before
    public void setup() {
        priceDetails.setCurrency_code("USD");
        priceDetails.setValue(200.00);
        product= new Product(PRODUCT_ID,"The Big Lebowski (Blu-ray)",priceDetails);
    }
    @Test
    public void getProductDetailsTest() throws Exception {
        Mockito.when(productServiceImpl.getProductDetails(PRODUCT_ID)).thenReturn(product);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/products/"+PRODUCT_ID).accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{\"id\":13860428,\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\":200.0,\"currency_code\":\"USD\"}}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
    @Test
    public void putProductDetailsTest() throws Exception{
        PriceDetails priceDetails= new PriceDetails();
        priceDetails.setId(PRODUCT_ID);
        priceDetails.setCurrency_code("Rupee");
        priceDetails.setValue(400.0);
        Product product= new Product(PRODUCT_ID,"The Big Lebowski (Blu-ray)",priceDetails);

        Mockito.when(productServiceImpl.updateProductDetails(PRODUCT_ID, product)).thenReturn(product);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
                "/products/"+PRODUCT_ID)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\":"+PRODUCT_ID+",\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\":400,\"currency_code\":\"Rupee\"}}")
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
    }

}

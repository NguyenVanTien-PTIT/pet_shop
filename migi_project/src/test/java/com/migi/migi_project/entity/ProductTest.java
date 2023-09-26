package com.migi.migi_project.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

class ProductTest {
    @Test
    void testConstructor() {
        Product actualProduct = new Product();
        Category category = new Category();
        category.setId(1);
        category.setName("Name");
        category.setProductsById(null);
        actualProduct.setCategoryByIdCategory(category);
        actualProduct.setCreateDate(mock(Timestamp.class));
        actualProduct.setDescription("The characteristics of someone or something");
        actualProduct.setId(1);
        actualProduct.setImage("Image");
        actualProduct.setName("Name");
        actualProduct.setOrderProductsById(null);
        actualProduct.setPrice(10.0);
        assertSame(category, actualProduct.getCategoryByIdCategory());
        assertEquals("The characteristics of someone or something", actualProduct.getDescription());
        assertEquals(1, actualProduct.getId());
        assertEquals("Image", actualProduct.getImage());
        assertEquals("Name", actualProduct.getName());
        assertNull(actualProduct.getOrderProductsById());
        assertEquals(10.0, actualProduct.getPrice().doubleValue());
    }
}


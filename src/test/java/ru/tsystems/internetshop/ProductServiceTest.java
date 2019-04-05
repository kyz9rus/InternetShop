package ru.tsystems.internetshop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tsystems.internetshop.dao.ProductDAO;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Product;
import ru.tsystems.internetshop.service.Impl.ProductServiceImpl;
import ru.tsystems.internetshop.service.ProductService;
import ru.tsystems.internetshop.util.Mapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)


@ContextConfiguration

public class ProductServiceTest {

    @Configuration
    static class ClientServiceTestContextConfiguration {
        @Bean
        public ProductService productService() {
            return new ProductServiceImpl();
        }

        @Bean
        public ProductDAO productDAO() {
            return Mockito.mock(ProductDAO.class);
        }

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public Mapper mapper() {
            return new Mapper();
        }
    }

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDAO productDAO;

    @Before
    public void setup() {
        Product product = new Product();
        product.setId(1L);
        product.setName("product");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setCategory(new Category("fragrances"));

        Product product3 = new Product();
        product3.setId(3L);
        product3.setCategory(new Category("fragrances"));

        Mockito.when(productDAO.findProductByName("product")).thenReturn(product);

        Mockito.when(productDAO.findProductsByCategory(new Category("frangrancy"))).thenReturn(Arrays.asList(product2, product3));

        Mockito.when(productDAO.findTop10Products()).thenReturn(Arrays.asList(new Product(4L), new Product(5L), new Product(6L), new Product(7L), new Product(8L), new Product(9L), new Product(10L), new Product(11L), new Product(12L), new Product(13L)));
    }

    @Test
    public void testGetProductByName() {
        ProductDTO productDTO = productService.getProductByName("product");

        assertEquals("product", productDTO.getName());
    }

    @Test
    public void testGetProductsByCategory() {
        List<ProductDTO> products = productService.getProductsByCategory(new CategoryDTO("frangrancy"));

        assertEquals(2, products.size());

        long productId1 = products.get(0).getId();
        long productId2 = products.get(1).getId();

        assertEquals(2L, productId1);
        assertEquals(3L, productId2);
    }

    @Test
    public void testGetTop10Products() {
        List<ProductDTO> products = productService.getTop10Products();

        assertEquals(10, products.size());
    }

    @After
    public void verify() {
        Mockito.reset(productDAO);
    }
}


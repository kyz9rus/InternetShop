package ru.tsystems.internetshop;

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
import ru.tsystems.internetshop.dao.CategoryDAO;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.service.CategoryService;
import ru.tsystems.internetshop.service.Impl.CategoryServiceImpl;
import ru.tsystems.internetshop.util.Mapper;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)


@ContextConfiguration

public class CategoryServiceTest {

    @Configuration
    static class ClientServiceTestContextConfiguration {
        @Bean
        public CategoryService categoryService() {
            return new CategoryServiceImpl();
        }

        @Bean
        public CategoryDAO categoryDAO() {
            return Mockito.mock(CategoryDAO.class);
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
    private CategoryService categoryService;

    @Autowired
    private CategoryDAO categoryDAO;

    @Before
    public void setup() {
        Category category = new Category("frangrancy");

        Mockito.when(categoryDAO.findByName("frangrancy")).thenReturn(category);
    }

    @Test
    public void testGetCategoryByName() {
        CategoryDTO categoryDTO = categoryService.getCategoryByName("frangrancy");

        assertEquals("frangrancy", categoryDTO.getName());
    }
}


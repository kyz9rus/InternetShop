//package ru.tsystems.internetshop;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jms.connection.CachingConnectionFactory;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import ru.tsystems.internetshop.config.*;
//import ru.tsystems.internetshop.dao.CategoryDAO;
//import ru.tsystems.internetshop.messaging.MessageSender;
//import ru.tsystems.internetshop.service.CategoryService;
//import ru.tsystems.internetshop.service.ClientAddressService;
//import ru.tsystems.internetshop.service.Impl.CategoryServiceImpl;
//import ru.tsystems.internetshop.service.Impl.ClientAddressServiceImpl;
//import ru.tsystems.internetshop.util.Mapper;
//
//import javax.annotation.PostConstruct;
//import javax.jms.ConnectionFactory;
//import javax.naming.NamingException;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = {WebConfigForTesting.class, HibernateConfig.class, MyWebAppInitializer.class, SecurityWebApplicationInitializer.class, ServletInitializer.class, WebSecurityConfig.class})
//
//public class WebSecurityTest {
//
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup() {
//        this.mockMvc = MockMvcBuilders
//                .webAppContextSetup(this.wac)
//                .apply(springSecurity())
//                .build();
//    }
//
//    @Test
//    public void redirectToLoginTest() throws Exception {
//        this.mockMvc.perform(get("/clientProfile"))
//                .andExpect(redirectedUrl("http://localhost/login"));
//    }
//
//    @Test
//    public void redirectToLoginTest2() throws Exception {
//        this.mockMvc.perform(get("/employeeProfile"))
//                .andExpect(redirectedUrl("http://localhost/login"));
//    }
//
//    @Test
//    public void getClientProfilePageTest() throws Exception {
//        this.mockMvc
//                .perform(get("/clientProfile")
//                        .with(user("danukrus@yandex.ru").password("admin").roles("CLIENT")))
//                .andExpect(view().name("clientProfile"));
//    }
//
//    @Test
//    public void getEmployeeProfilePageTest() throws Exception {
//        this.mockMvc
//                .perform(get("/employeeProfile")
//                    .with(user("kyz9rus@yandex.ru").password("admin").roles("EMPLOYEE")))
//                .andExpect(view().name("employeeProfile"));
//    }
//
//    @Test
//    public void getForbiddenClientProfileTest() throws Exception {
//        this.mockMvc
//                .perform(get("/clientProfile")
//                        .with(user("kyz9rus@yandex.ru").password("admin").roles("EMPLOYEE")))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    public void getForbiddenEmployeeProfileTest() throws Exception {
//        this.mockMvc
//                .perform(get("/employeeProfile")
//                        .with(user("danukrus@yandex.ru").password("admin").roles("CLIENT")))
//                .andExpect(status().isForbidden());
//    }
//}
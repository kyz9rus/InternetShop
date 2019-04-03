import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.tsystems.internetshop.config.*;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, MyWebAppInitializer.class, SecurityWebApplicationInitializer.class, ServletInitializer.class, WebConfig.class, WebSecurityConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class PublicControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

//    @Mock
//    private ProductService mockProductService;
//
//    @InjectMocks
//    private PublicController publicController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void test() throws Exception {
        this.mockMvc.perform(get("/registration")).
                andExpect(status().isOk());
    }

    @Test
    public void createBeanPublicControllerTest() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("publicController"));
    }
}

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.tsystems.internetshop.config.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, HibernateConfig.class, MyWebAppInitializer.class, SecurityWebApplicationInitializer.class, ServletInitializer.class, WebConfig.class, WebSecurityConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class ClientProfileTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void toEditProfileTest() throws Exception {
        this.mockMvc
                .perform(get("/clientProfile/editProfile")
                        .with(user("danukrus@yandex.ru").password("admin").roles("CLIENT")))
                .andExpect(view().name("clientProfile/editProfile"));
    }

    @Test
    public void toChangePasswordProfileTest() throws Exception {
        this.mockMvc
                .perform(get("/clientProfile/changePassword")
                        .with(user("danukrus@yandex.ru").password("admin").roles("CLIENT")))
                .andExpect(view().name("clientProfile/changePassword"));
    }

    @Test
    public void toIssueOrderProfileTest() throws Exception {
        this.mockMvc
                .perform(get("/clientProfile/issueOrder")
                        .with(user("danukrus@yandex.ru").password("admin").roles("CLIENT")))
                .andExpect(view().name("clientProfile/issueOrder"));
    }

    @Test
    public void toViewOrderHistoryProfileTest() throws Exception {
        this.mockMvc
                .perform(get("/clientProfile/viewOrderHistory")
                        .with(user("danukrus@yandex.ru").password("admin").roles("CLIENT")))
                .andExpect(view().name("clientProfile/viewOrderHistory"));
    }
}
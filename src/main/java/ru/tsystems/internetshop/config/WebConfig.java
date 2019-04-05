package ru.tsystems.internetshop.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.tsystems.internetshop.util.CategoryInfo;

import java.util.List;
import java.util.Properties;


@EnableWebMvc
@Configuration
@ComponentScan({"ru.tsystems.internetshop"})
@PropertySource("classpath:spring-mail.properties")
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new StringHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_APPLICATION)
    public CategoryInfo createCategories() {
        return new CategoryInfo();
    }

    @Bean(name = "mailSender")
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(env.getRequiredProperty("spring.mail.username"));
        javaMailSender.setPassword(env.getRequiredProperty("spring.mail.password"));
        javaMailSender.setPort(Integer.parseInt(env.getRequiredProperty("spring.mail.port")));

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", env.getRequiredProperty("spring.mail.properties.mail.smtp.auth"));
        javaMailProperties.put("mail.smtp.starttls.enable", env.getRequiredProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        javaMailProperties.put("mail.smtp.starttls.required", env.getRequiredProperty("spring.mail.properties.mail.smtp.starttls.required"));
        javaMailProperties.put("mail.smtp.socketFactory.class", env.getRequiredProperty("spring.mail.properties.mail.smtp.starttls.smtp.socketFactory.class"));
        javaMailProperties.put("mail.smtp.host", env.getRequiredProperty("spring.mail.host"));

        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }
}

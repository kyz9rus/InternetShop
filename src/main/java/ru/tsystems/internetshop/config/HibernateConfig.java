package ru.tsystems.internetshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.tsystems.internetshop.model.entity.*;
import ru.tsystems.internetshop.model.entity.Role;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * This is class, which contains configuration database connections
 */
@Configuration
@EnableTransactionManagement
@ComponentScans(value = {@ComponentScan("ru.tsystems.internetshop")})
@PropertySource("classpath:hibernate.properties")
public class HibernateConfig {

    @Autowired
    private Environment env;

    /**
     * This is method configure session factory
     *
     * @return turned in session factory bean
     */
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setHibernateProperties(hibernateProperties());
        factoryBean.setAnnotatedClasses(Client.class, Order.class, ClientAddress.class, Product.class, Category.class, User.class, Role.class, OrderProduct.class, Coupon.class, News.class);
        return factoryBean;
    }

    /**
     * This is method configure data source
     *
     * @return turned in data source bean
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    /**
     * This is method configure transaction manager
     *
     * @return turned in transaction manager bean
     */
    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

}
package ru.tsystems.internetshop.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.tsystems.internetshop.config.*;
import ru.tsystems.internetshop.selenium.page.HomePage;
import ru.tsystems.internetshop.selenium.page.IssueOrder;
import ru.tsystems.internetshop.selenium.page.LoginPage;
import ru.tsystems.internetshop.selenium.page.RegistrationPage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, HibernateConfig.class, MyWebAppInitializer.class, SecurityWebApplicationInitializer.class, ServletInitializer.class, WebConfig.class, WebSecurityConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class SeleniumTestClass {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");

        driver = new SafariDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
    }

    @Test
    public void headerTest() {
        HomePage homePage = new HomePage(driver, wait);

        wait.until(titleIs(homePage.getTitle()));

        homePage.clickBecomeRepresentativeLink();

        wait.until(titleIs("Регистрация в Avon"));

        homePage.openPage();

        homePage.clickRegisterButton();

        wait.until(titleIs(new RegistrationPage().getTitle()));

        homePage.clickMainLogoImage();

        wait.until(titleIs(homePage.getTitle()));

        homePage.clickLoginButton();

        wait.until(titleIs(new LoginPage().getTitle()));

        homePage.openPage();

        homePage.clickBasket();

        wait.until(titleIs(new LoginPage().getTitle()));
    }

    @Test
    public void mainPageTest() {
        HomePage homePage = new HomePage(driver, wait);

        homePage.clickBuyButton();

        assertEquals(homePage.getNumberOfProductsInBasket().getText(), "Number of products: 1");

        homePage.clickBuyButton();

        assertEquals(homePage.getNumberOfProductsInBasket().getText(), "Number of products: 2");

        homePage.clickGetItButtonWithoutAuth();

        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.fillLoginInput("danukrus@yandex.ru");
        loginPage.fillPasswordInput("admin");
        loginPage.clickSignInButton();

        homePage.clickGetItButtonWithAuth();

        homePage.getCouponBlock();

        homePage.clickOkButtonInCouponBlock();

        homePage.clickGetItButtonWithAuth();

        homePage.clickCrossButtonInCouponBlock();

        homePage.clickIssueOrderButton();

        wait.until(titleIs(new IssueOrder().getTitle()));

    }

    @After
    public void destroy() {
        if (driver != null)
            driver.close();
    }
}

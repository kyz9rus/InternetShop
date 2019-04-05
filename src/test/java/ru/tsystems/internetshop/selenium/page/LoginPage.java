package ru.tsystems.internetshop.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage() {
    }

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private final String title = "Please Sign in";

    private final By loginInput = By.xpath("//*[@id=\"username\"]");
    private final By passwordInput = By.xpath("//*[@id=\"password\"]");
    private final By signInButton = By.xpath("/html/body/div[1]/div/form/button");

    public String getTitle() {
        return title;
    }

    public void fillLoginInput(String login) {
        wait.until(visibilityOfElementLocated(loginInput)).sendKeys(login);
    }

    public void fillPasswordInput(String password) {
        wait.until(visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }

    public void clickSignInButton() {
        wait.until(visibilityOfElementLocated(signInButton)).click();
    }
}

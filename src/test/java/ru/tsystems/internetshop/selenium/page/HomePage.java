package ru.tsystems.internetshop.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final String title = "Avon shop";
    private final String fullUrl = "http://localhost:8081/";

    private final By becomeRepresentativeLink = By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[1]/div[1]/a");

    private final By registerButton = By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[1]/div[2]/div[1]/a/img");
    private final By loginButton = By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[1]/div[2]/div[2]/a/img");

    private final By mainLogoImage = By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/a/img");
    private final By basket = By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/a/img");

    private final By getItButton = By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[3]/button");
    private final By buyButton = By.xpath("/html/body/div[1]/div/div[2]/div[3]/ul/li[1]/div/div/div[2]/div[2]/button");

    private final By numberOfProductsInBasket = By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/div/div[1]/p[2]");
    private final By issueOrderButton = By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/div/div[2]/a/button");

    private final By couponBlock = By.cssSelector(".couponWindow");
    private final By okButtonInCouponBlock = By.xpath("/html/body/div[1]/div/div[2]/div[1]/button");
    private final By crossButtonInCouponBlock = By.xpath("/html/body/div[1]/div/div[2]/div[1]/img");


    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        openPage();
    }

    public void openPage() {
        driver.get(fullUrl);
    }

    public String getTitle() {
        return title;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public WebElement getNumberOfProductsInBasket() {
        return wait.until(visibilityOfElementLocated(numberOfProductsInBasket));
    }

    public WebElement getCouponBlock(){
        return wait.until(visibilityOfElementLocated(couponBlock));
    }

    public void clickBecomeRepresentativeLink() {
        wait.until(visibilityOfElementLocated(becomeRepresentativeLink)).click();
    }

    public void clickRegisterButton() {
        wait.until(visibilityOfElementLocated(registerButton)).click();
    }

    public void clickLoginButton() {
        wait.until(visibilityOfElementLocated(loginButton)).click();
    }

    public void clickMainLogoImage() {
        wait.until(visibilityOfElementLocated(mainLogoImage)).click();
    }

    public void clickBasket() {
        wait.until(visibilityOfElementLocated(basket)).click();
    }

    public void clickGetItButton() {
        wait.until(visibilityOfElementLocated(getItButton)).click();
    }

    public void clickBuyButton() {
        wait.until(visibilityOfElementLocated(buyButton)).click();
    }

    public void clickIssueOrderButton() {
        wait.until(visibilityOfElementLocated(issueOrderButton)).click();
    }

    public void clickOkButtonInCouponBlock() {
        wait.until(visibilityOfElementLocated(okButtonInCouponBlock)).click();
    }

    public void clickCrossButtonInCouponBlock() {
        wait.until(visibilityOfElementLocated(crossButtonInCouponBlock)).click();
    }
}

package pages;

import managers.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    private String productName;
    @FindBy(xpath = "//li[contains(@class,'kitt-top-menu__item_first')]/a[@role or @aria-expanded]")
    private List<WebElement> listBaseMenu;

    @FindBy(xpath = "//a[@data-cga_click_top_menu]")
    private List<WebElement> listSubMenu;

    @FindBy(xpath = "//input[@placeholder]")
    private WebElement placeholder;

    @FindBy(xpath = "//div[contains(@class, 'search-bar-wrapper')]//button")
    private WebElement searchButton;

    /**
     * Функция поиска по названию товара в поисковой строке
     *
     * @param productName - наименование товара поиск котороо будет осуществляться
     * @return HomePage - т.е. остаемся на этой странице
     */
    public HomePage searchProduct(String productName) {
        this.productName = productName;
        placeholder.sendKeys(productName);


        return this;
    }

    /**
     * Функция нажатия кнопки 'поиск' в поисковой строке
     *
     *
     * @return ProductPage - т.е. Возвращаем результаты поиска товаров
     */
    public ProductPage clickSearchButton() {
        searchButton.click();

        return pageManager.getProductPage().checkOpenProductPage(productName);
    }


}

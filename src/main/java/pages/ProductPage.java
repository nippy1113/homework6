package pages;

import data.Product;
import io.qameta.allure.Allure;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//div[@data-widget='fulltextResultsHeader']//div//strong")
    private WebElement title;

    @FindBy(xpath = "//aside[@data-widget='searchResultsFilters']/div")
    private List<WebElement> filterList;

    @FindBy(xpath = "//a[@data-widget='headerIcon']/span[1]")
    private WebElement productCounter;

    @FindBy(xpath = "//a[@data-widget='headerIcon']")
    private WebElement cartButton;

    @FindBy(xpath = "//div[text()='Дальше']")
    private WebElement nextPageButton;

    @FindBy(xpath = "//div[@data-widget= 'searchResultsFiltersActive']//span")
    private List<WebElement> setFiltersList;

    @FindBy(xpath = "//div[@value]")
    private List<WebElement> checkBoxList;

    @FindBy(xpath = "//div[@data-widget='megaPaginator']/div[1]/div[1]/div/div")
    private List<WebElement> productMenu;


    /**
     * Функция проверки открытия результатов поиска по введеному товару
     *
     *
     * @return ProductPage - т.е. Возвращаем результаты поиска товаров
     */
    public ProductPage checkOpenProductPage(String titleName) {
        Assert.assertTrue("Заголовок отсутствует/не соответствует требуемому",
                title.getText().equals(titleName));
        return this;
    }

    /**
     * Функция проверки установки фильтра лимитирования цены
     *
     *
     * @return ProductPage - т.е. Возвращаем результаты поиска товаров
     */
    public ProductPage checkOpenFilter(String setFilterValue) {
        for (WebElement element : setFiltersList) {
            waitUtilElementToBeVisible(element);
            if (element.getText().contains(Utils.convertPrice(setFilterValue))) {
                return this;
            }
        }
        Assert.fail("Установленный фильтр отсутствует!");
        return this;
    }

    /**
     * Функция проверки добавления продукта в корзину
     *
     *
     *
     */
    public void checkAddProductToCart() {
        waitTextToBePresent(productCounter, (dataManager.getProductList().size()) + "");
    }

    /**
     * Функция лимитирования значения фильтра
     *
     * @param filterName - наименование фильтра в котором будет прописана сумма лимита
     * @param filterParameter - наименование параметра фильтра в котором будет прописана сумма лимита (от или до)
     * @param sum - число которое будет записано в поля для лимитирования цены
     * @return ProductPage - т.е. Возвращаем результаты поиска товаров
     */
    public ProductPage limitFilterValue(String filterName, String filterParameter , String sum) {
        for (WebElement element : filterList) {
            if (element.findElement(By.xpath("./div")).getText().contains(filterName)) {
                switch (filterParameter) {
                    case "от":
                        element = element.findElement(By.xpath(".//input[@qa-id='range-from']"));
                        waitUtilElementToBeClickable(element).click();
                        element.sendKeys(Keys.chord(Keys.CONTROL, "a" , Keys.DELETE));
                        element.sendKeys(sum, Keys.ENTER);
                        return this.checkOpenFilter(filterName);

                    case "до":
                        element = element.findElement(By.xpath(".//input[@qa-id='range-to']"));
                        waitUtilElementToBeClickable(element).click();
                        element.sendKeys(Keys.chord(Keys.CONTROL, "a" , Keys.DELETE));
                        element.sendKeys(sum, Keys.ENTER);
                        return this.checkOpenFilter(filterName);
                }
            }
        }
        Assert.fail("Фильтра '" + filterName + "' не было найдено на транице!");
        return this;
    }

    /**
     * Функция нажатия на чекбокс
     * @param checkBoxName - наименование чекбока на который будет осуществлено нажатие
     *
     * @return ProductPage - т.е. Возвращаем результаты поиска товаров
     */
    public ProductPage clickOnCheckBox(String checkBoxName) throws InterruptedException {
        for (WebElement element : checkBoxList) {
            if (element.getText().equals(checkBoxName.trim())) {
                waitUtilElementToBeClickable(element).click();
                Thread.sleep(2000);
                return this.checkOpenFilter(checkBoxName);
            }
        }
        Assert.fail("Поле '" + checkBoxName + "' не было найдено на транице!");
        return this;
    }

    /**
     * Функция нажатия на квадратный чекбокс
     * @param checkBoxСhapterName - раздела в котором расположен чекбокс
     * @param checkBoxName - наименование чекбока на который будет осуществлено нажатие
     *
     * @return ProductPage - т.е. Возвращаем результаты поиска товаров
     */
    public ProductPage clickOnSquareCheckBox(String checkBoxСhapterName, String checkBoxName) throws InterruptedException {
        for (WebElement element : filterList) {
            if (element.findElement(By.xpath("./div")).getText().contains(checkBoxСhapterName)) {
                List<WebElement> insideChapterCheckBoxes = element.findElements(By.xpath(".//div/span"));
                for (WebElement checkBox : insideChapterCheckBoxes) {
                    if (checkBox.getText().contains(checkBoxName)) {
                        js.executeScript("arguments[0].click();", checkBox.findElement(By.xpath("./..")));
                        Thread.sleep(2000);
                        return this.checkOpenFilter(checkBoxName);
                    }
                }
                Assert.fail("Поле '" + checkBoxName + "' не было найдено на транице!");
            }
        }
        Assert.fail("Поле '" + checkBoxСhapterName + "' не было найдено на транице!");
        return this;
    }

    /**
     * Функция добавления в корзину нечетных товаров
     * @param amount - колличество нечетных товаров которые буддут довавлены в корзину
     *
     * @return ProductPage - т.е. Возвращаем результаты поиска товаров
     */
    public ProductPage addOddNumberProducts(String amount) {
        int count = 0;
        for (int i = 0; i < productMenu.size(); i++) {
            if ((i + 1) % 2 == 0 && count < Integer.parseInt(amount)) {
                WebElement buyButton = productMenu.get(i).findElement(By.xpath(".//div/div/div/div//button"));
                String productName = productMenu.get(i).findElement(By.xpath(".//a/span/span")).getText().trim();
                String productPrice = Utils
                        .convertPrice(productMenu.get(i).findElement(By.xpath(".//div/span[contains(text(),'₽')][1]")).getText());
                dataManager.getProductList().add(new Product(productName, Integer.parseInt(productPrice)));

                waitUtilElementToBeClickable(buyButton);
                js.executeScript("arguments[0].click();", buyButton);

                count++;
                checkAddProductToCart();
            }
        }
        return this;
    }


    /**
     * Функция добавления в корзину нечетных товаров
     *
     * @return ProductPage - т.е. Возвращаем результаты поиска товаров
     */
    public ProductPage addAllOddNumberProducts() {
        for (int i = 0; i < productMenu.size(); i++) {
            if ((i + 1) % 2 == 0) {
                WebElement buyButton = productMenu.get(i).findElement(By.xpath(".//div/div/div/div//button"));
                String productName = productMenu.get(i).findElement(By.xpath(".//a/span/span")).getText().trim();
                String productPrice = Utils
                        .convertPrice(productMenu.get(i).findElement(By.xpath(".//div/span[contains(text(),'₽')][1]")).getText());

                dataManager.getProductList().add(new Product(productName, Integer.parseInt(productPrice)));

                waitUtilElementToBeClickable(buyButton);
                js.executeScript("arguments[0].click();", buyButton);

                checkAddProductToCart();

            }
        }
        try {
            if (waitTextToBePresent(nextPageButton, "Дальше")) {
                waitUtilElementToBeClickable(nextPageButton).click();
                return this.addAllOddNumberProducts().checkOpenProductPage(title.getText());
            }
        } catch (TimeoutException e) {
            return this;
        }
        return this;
    }

    /**
     * Функция нажатия на корзину
     *
     *
     * @return CartPage - т.е. Возвращаем результаты поиска товаров
     */
    public CartPage clickCartButton() throws IOException {
        waitUtilElementToBeClickable(cartButton).click();
        Allure.getLifecycle().addAttachment("text file", "file/txt", "txt",
                new FileInputStream(dataManager.getProductsTextFile()));

        return pageManager.getCartPage();
    }
}


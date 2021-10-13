package steps;

import io.cucumber.java.ru.И;
import managers.PageManager;

import java.io.IOException;

public class ProductPageSteps {
    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Вводим в фильтр \"(.+)\" с параметром \"(.+)\" значение \"(.+)\"$")
    public void limitPrice(String filterName, String filterParameter , String sum) {
        pageManager.getProductPage().limitFilterValue(filterName, filterParameter, sum);
    }

    @И("^Кликаем на чекбокс \"(.+)\"$")
    public void clickOnCheckBox(String checkBoxName) throws InterruptedException {
        pageManager.getProductPage().clickOnCheckBox(checkBoxName);
    }

    @И("^Добавляем в корзину \"(.+)\" нечетных продуктов$")
    public void addOddNumberProducts(String amount) {
        pageManager.getProductPage().addOddNumberProducts(amount);
    }

    @И("^Добавляем в корзину все нечетные продукты на всех страницах$")
    public void addAllOddNumberProducts() {
        pageManager.getProductPage().addAllOddNumberProducts();
    }

    @И("^Кликаем на корзину$")
    public void clickCartButton() throws IOException {
        pageManager.getProductPage().clickCartButton();
    }

    @И("В разделе \"(.+)\" кликаем на чекбокс \"(.+)\"$")
    public void clickOnSquareCheckBox(String checkBoxСhapterName, String checkBoxName) throws IOException, InterruptedException {
        pageManager.getProductPage().clickOnSquareCheckBox(checkBoxСhapterName,checkBoxName);
    }
}

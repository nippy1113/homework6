package steps;

import io.cucumber.java.ru.И;
import managers.PageManager;

public class HomePageSteps {

    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Вводим товар \"(.+)\" в поисковую строку$")
    public void selectBaseMenu(String productName) {
        pageManager.getHomePage().searchProduct(productName);
    }

    @И("^Жмем на кнопу 'Поиск' у поисковой строки$")
    public void clickSearchButton() {
        pageManager.getHomePage().clickSearchButton();
    }



}

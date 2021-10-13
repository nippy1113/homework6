package steps;

import io.cucumber.java.ru.И;
import managers.PageManager;

public class CartPageSteps {
    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Закрываем окно рекламы$")
    public void selectBaseMenu() {
        pageManager.getCartPage().closeAdWindow();
    }

    @И("^Проверяем наличие добавленных продуктов в корзине$")
    public void checkProductsInsideCart() {
        pageManager.getCartPage().checkProductsInsideCart();
    }

    @И("^Удалим все продукты из корзины$")
    public void deleteAllProducts() {
        pageManager.getCartPage().deleteAllProducts();
    }

}

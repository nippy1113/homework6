package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//div[contains(@id,'split-Main')]//a/span")
    private List<WebElement> productInsideCartList;

    @FindBy(xpath = "//div[contains(@style,'border-radius: 50%')]")
    private WebElement adWindow;

    @FindBy(xpath = "//span[contains(text(),'Ваша корзина')]/../span[2]")
    private WebElement productsAmountTitle;

    @FindBy(xpath = "//span[contains(text(),'Удалить выбранные')]")
    private WebElement deleteAllProductsButton;

    @FindBy(xpath = "//div[contains(text(),'Удалить')]")
    private WebElement confirmDeleteButton;

    @FindBy(xpath = "//h1[contains(text(),'Корзина пуста')]")
    private WebElement deleteTitle;


    /**
     * Проверяем надпись с информацией о колличестве товаров
     *
     * @return this - т.е. возвращает страничку {@link CartPage}
     */
    public CartPage checkProductsAmountTitle() {
        Assert.assertTrue("Надпись с информацией о колличестве товаров не верна",
                productsAmountTitle.getText().trim().contains(dataManager.getProductList().size() + ""));
        return this;
    }

    /**
     * Проверяем удалились ли все продукты из корзины
     *
     * @return this - т.е. возвращает страничку {@link CartPage}
     */
    public CartPage checkIsAllProductsDeleted() {
        Assert.assertEquals("Продукты не были удалены!",
                "Корзина пуста",
                deleteTitle.getText().trim());

        return this;
    }



    /**
     * Проверяем наличие добавленных продуктов в корзине
     *
     * @return this - т.е. возвращает страничку {@link CartPage}
     */
    public CartPage checkProductsInsideCart() {
        for (WebElement productItem : productInsideCartList) {
            for (int i = 0; i < dataManager.getDataManager().getProductList().size(); i++) {
                if (productItem.getText().equals(dataManager.getDataManager().getProductList().get(i).getName())) {
                    dataManager.getProductList().get(i).setIsChecked(true);
                }
            }
        }
        for (int i = 0; i < dataManager.getProductList().size(); i++) {
            if (dataManager.getProductList().get(i).getIsChecked() == false) {
                Assert.fail("Одного из товаров нет в корзине!");
            }
        }
        return this.checkProductsAmountTitle();
    }


    /**
     * Закрываем рекламу
     *
     * @return this - т.е. возвращает страничку {@link CartPage}
     */

    public CartPage closeAdWindow() {
       waitUtilElementToBeClickable(adWindow).click();
        return this;
    }

    /**
     * Закрываем рекламу
     *
     * @return this - т.е. возвращает страничку {@link CartPage}
     */
    public CartPage deleteAllProducts() {
        waitUtilElementToBeClickable(deleteAllProductsButton).click();
        waitUtilElementToBeVisible(confirmDeleteButton);
        waitUtilElementToBeVisible(confirmDeleteButton).click();
        for (int i = 0; i < dataManager.getProductList().size(); i++) {
            dataManager.getProductList().remove(i);
            i--;
        }
        return this.checkIsAllProductsDeleted();
    }


    public CartPage waitForIt(int miliSeconds) throws InterruptedException {
        Thread.sleep(miliSeconds);
        return this;
    }


}

package data;

import org.junit.Assert;
import utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager INSTANCE = null;
    private List<Product> productList = new ArrayList<>();

    private DataManager() {
    }

    public static DataManager getDataManager() {
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public List<Product> getProductList() {

        return productList;
    }

    public String getProductsTextFile() throws IOException {
        File file = new File("products");
        FileWriter writer = new FileWriter(file, false);

        writer.write("Product list: \n");
        for (int i = 0; i < getDataManager().getProductList().size(); i++) {
            writer.write("name: " + getDataManager().getProductList().get(i).getName()
                    + " price: " + getDataManager().getProductList().get(i).getPrice() + "\n");
        }

        writer.write(getHeightPriceProduct());

        writer.flush();
        return file.getPath();
    }

    public String getHeightPriceProduct() {
        int max = 0;
        for (int i = 0; i < getDataManager().getProductList().size(); i++) {
            if (getProductList().get(i).getPrice() > max) {
                max = getProductList().get(i).getPrice();
            }
        }

        for (int i = 0; i < getDataManager().getProductList().size(); i++) {
            if (getProductList().get(i).getPrice() == max) {
                return "The max price product is " + getProductList().get(i).getName() + " and its price is: " + getProductList().get(i).getPrice();
            }
        }

        return null;
    }
}

package utils;

public class Utils {
    public static String convertPrice(String price) {

        return price.replaceAll("\\D", "");
    }

    public static String convertNameProduct(String nameProduct) {

        return nameProduct.replaceAll("\n", "").replaceAll("\\s", "");
    }
}

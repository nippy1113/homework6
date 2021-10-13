package data;

public class Product {
   private String name;
   private int price;
   private boolean isChecked;

    public Product(String name, int price, boolean isChecked) {
        this.name = name;
        this.price = price;
        this.isChecked = isChecked;
    }



    public Product(String name, int price) {
        this(name, price, false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        isChecked = checked;
    }
}




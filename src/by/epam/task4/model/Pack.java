/*
 * class: Pack
 */

package by.epam.task4.model;

/**
 * Represents package form, size and price for concrete medicine version
 * 
 * @author Maxim Burishinets
 * @version 1.0 20 Aug 2018
 */
public class Pack {

    private int quantity;
    private double price;
    private String size;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (obj == null) { return false; }
        if (obj.getClass() != getClass()) { return false; }
        Pack pack = (Pack) obj;
        if (quantity != pack.quantity) { return false; }
        if (price != pack.price) { return false; }
        if (size == null) {
            if (pack.size != null) {
                return false;
            }
        } else if (!size.equals(pack.size)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode += (int) (31 * (quantity + price));
        hashCode += size == null ? 0 : size.hashCode();
        return hashCode;
    }
    
    @Override
    public String toString() {
        return "\n        " + getClass().getSimpleName() + ":"
                + " size='" + size + "'"
                + "\n            quantity: " + quantity
                + "\n            price:    " + price;
    }
}

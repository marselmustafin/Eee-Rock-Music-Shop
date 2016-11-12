package ru.itis.kpfu.marsel_mustafin.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Cart {

    private Map<Integer, Product> products;
    private int productCount;
    private int totalPrice;

    public Cart() {
        products = new HashMap<Integer, Product>();
        productCount = 0;
        totalPrice = 0;
    }

    public void addProduct(Product product, int userQuantity) throws Exception {
        int realQuantity = product.getQuantity();
        int productId = product.getId();

        if (realQuantity < userQuantity) {
            throw new Exception("Total quantity of product in the cart more than real");
        }

        product.setQuantity(userQuantity);

        if (!products.containsKey(productId)) {
            productCount += userQuantity;
            totalPrice += userQuantity * product.getPrice();
            products.put(productId, product);

        } else {
            int oldQuantity = products.get(productId).getQuantity();

            if (oldQuantity + userQuantity <= realQuantity) {
                productCount += userQuantity;
                totalPrice += userQuantity * product.getPrice();
                product.setQuantity(userQuantity + oldQuantity);
                products.put(productId, product);

            } else {
                throw new Exception("Total quantity of product in the cart more than real");
            }
        }
    }

    public boolean removeProduct(int id) {
        if (products.containsKey(id)) {
            Product p = products.get(id);
            productCount -= p.getQuantity();
            totalPrice -= p.getQuantity() * p.getPrice();
            products.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public int getProductCount() {
        return productCount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}

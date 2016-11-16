import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.itis.kpfu.marsel_mustafin.models.Cart;
import ru.itis.kpfu.marsel_mustafin.models.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CartTest {

    private static Random rnd;
    private Cart cart;

    @BeforeClass
    public static void initializeClass() {
        rnd = new Random();
    }

    @Before
    public void initialize() {
        cart = new Cart();
    }

    @Test
    public void addToCart() throws Exception {
        Product product = new Product("1", "1", "1", 1, 1);
        cart.addProduct(product, 1);
        Assert.assertEquals(product, cart.getProducts().get(0));
    }

    @Test
    public void removeFromCart() throws Exception {
        int size = rnd.nextInt(1000);
        size = size % 2 == 0 ? size : size - 1;
        for (int i = 0; i < size; i++) {
            Product product = new Product("", "", "", i, i);
            product.setId(i);
            cart.addProduct(product, i);
        }
        for (int k = 1; k <= size / 2; k++) {
            cart.removeProduct(k);
        }
        Assert.assertTrue(cart.getProducts().size() == size / 2);
    }

    @Test
    public void clearCart() throws Exception {
        int size = rnd.nextInt(1000);
        for (int i = 0; i < size; i++) {
            Product product = new Product("", "", "", i, i);
            product.setId(i);
            cart.addProduct(product, i);
        }
        cart.clear();
        Assert.assertTrue(cart.getProducts().size() == 0);
    }

    @Test
    public void getProducts() throws Exception {
        Map <Integer,Product> map = new HashMap<Integer, Product>();
        int size = rnd.nextInt(1000);
        for (int i = 0; i < size; i++) {
            Product product = new Product("", "", "", i, i);
            product.setId(i);
            cart.addProduct(product, i);
            map.put(i,product);
        }
        Assert.assertEquals(map,cart.getProducts());
    }
}

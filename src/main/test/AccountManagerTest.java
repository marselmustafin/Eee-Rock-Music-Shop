import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.itis.kpfu.marsel_mustafin.models.Account;
import ru.itis.kpfu.marsel_mustafin.models.AccountManager;

import java.util.Random;

import static org.mockito.Mockito.*;

public class AccountManagerTest {

    AccountManager spy;
    private static Random rnd;

    @BeforeClass
    public static void initializeClass() {
        rnd = new Random();
    }

    @Before
    public void initialize() {
        spy = spy(new AccountManager());
    }


    @Test
    public void emailCheck() {
        String dict = "qwertyuiopasdfghjklzxcvbnm";
        String email = "";
        for (int i = 0; i < 10; i++) {
            email += dict.charAt(rnd.nextInt(dict.length()));
        }
        email += "@";
        for (int i = 0; i < 5; i++) {
            email += dict.charAt(rnd.nextInt(dict.length()));
        }
        email += ".";
        for (int i = 0; i < 2; i++) {
            email += dict.charAt(rnd.nextInt(dict.length()));
        }
        Assert.assertTrue(spy.emailCheck(email));
    }
}

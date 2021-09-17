package servicetest;

import com.bluemsun.entity.Good;
import com.bluemsun.entity.Order;
import com.bluemsun.service.impl.CartManagerServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class CartManagerServiceTest {
    @Test
    public void addGoodInCart(){
        CartManagerServiceImpl cartManagerService = new CartManagerServiceImpl();
        System.out.println(cartManagerService.addGoodInCart(3,10,1));
    }
    @Test
    public void getCartTest(){
        CartManagerServiceImpl cartManagerService = new CartManagerServiceImpl();
        System.out.println(cartManagerService.getCart(1).getList());
    }

    @Test
    public void deleteGoodInCartTest(){
        CartManagerServiceImpl cartManagerService = new CartManagerServiceImpl();
        System.out.println(cartManagerService.deleteGoodInCart(1,9));
    }

    @Test
    public void clearCartTest(){
        CartManagerServiceImpl cartManagerService = new CartManagerServiceImpl();
        System.out.println(cartManagerService.clearCart(1));
    }

    @Test
    public void settleCartTest(){
        CartManagerServiceImpl cartManagerService = new CartManagerServiceImpl();
        BigDecimal money = BigDecimal.valueOf(163);
        cartManagerService.settleCart(new Order(3,money,"111111111","waixngrne","rrrn"));
    }

    @Test
    public void buyGoodTest(){
        CartManagerServiceImpl cartManagerService = new CartManagerServiceImpl();
        Good good = new Good();
        good.setGoodID(9);
        good.setAmount(2);
        BigDecimal money = BigDecimal.valueOf(163);
        Order order = new Order(1,money,"18736878369","河南省商丘市","windlin");
        order = cartManagerService.buyGood(order, good.getGoodID(),good.getAmount());
        System.out.println(order);
    }
}

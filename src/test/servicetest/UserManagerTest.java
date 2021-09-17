package servicetest;

import com.bluemsun.service.impl.UserManagerServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class UserManagerTest {
    @Test
    public void rechargeBalanceTest(){
        UserManagerServiceImpl userManagerService = new UserManagerServiceImpl();
        BigDecimal money = BigDecimal.valueOf(1000);
        System.out.println(userManagerService.rechargeBalance("18736878369",money));
    }
    @Test
    public void queryUser(){
        UserManagerServiceImpl userManagerService = new UserManagerServiceImpl();
        System.out.println(userManagerService.queryUser("18736878369"));
    }
}

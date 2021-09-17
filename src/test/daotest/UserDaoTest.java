package daotest;

import com.bluemsun.dao.UserDao;
import org.junit.Test;

import java.math.BigDecimal;

public class UserDaoTest {
    //@Test
    public void updateBalanceTest(){
        UserDao userDao = new UserDao();
        BigDecimal money;
        money = BigDecimal.valueOf(23.33);
        //System.out.println(money);
        //money = BigDecimal.valueOf();
        userDao.updateBalance("18736878363",money);
    }
}

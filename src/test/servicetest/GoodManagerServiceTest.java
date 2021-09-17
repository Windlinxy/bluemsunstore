package servicetest;

import com.bluemsun.dao.GoodDao;
import com.bluemsun.entity.Good;
import com.bluemsun.service.impl.GoodManagerServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class GoodManagerServiceTest {
    //@Test
    public void setGood(){
        GoodManagerServiceImpl goodManagerService = new GoodManagerServiceImpl();
        GoodDao goodDao = new GoodDao();
        BigDecimal price = BigDecimal.valueOf(29.90);
        Good good = new Good("桶装士力架",price,"食品",1002,"士力架巧克力花生夹心460g全家桶七夕节日送礼休闲糖果零食品小吃233",null);
        good.setGoodID(15);
        goodManagerService.setGood(good);
    }
}

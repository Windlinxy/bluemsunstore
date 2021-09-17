package daotest;

import com.bluemsun.dao.GoodDao;
import com.bluemsun.entity.Good;
import org.junit.Test;

import java.math.BigDecimal;

public class GoodDaoTest {
    //@Test
    public void setGoodTest(){
        GoodDao goodDao = new GoodDao();
        BigDecimal price = BigDecimal.valueOf(39.90);
        Good good = new Good("桶装士力架",price,"食品",1001,"士力架巧克力花生夹心460g全家桶七夕节日送礼休闲糖果零食品小吃233",null);
        good.setGoodID(15);
        goodDao.updateGood(good);
    }

    //@Test
    public void insertGoodTest(){
        GoodDao goodDao = new GoodDao();
        BigDecimal price = BigDecimal.valueOf(169.99);
        Good good = new Good("华为手环",price,"奢品",1000,"阿巴巴巴","http://bluemsunstore.natapp1.cc:80/images/10c8a048-1d32.png");
        goodDao.insertGood(good);
    }



    //@Test
    public void test(){

    }

}

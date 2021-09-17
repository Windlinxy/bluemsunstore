package daotest;

import com.bluemsun.dao.CartDao;
import org.junit.Test;

public class CartDaoTest {
    //@Test
    public void addGoodTest(){
        CartDao cartDao = new CartDao();
        int jud = cartDao.insertGoodInCart(1,9,1);
        System.out.println(jud);
    }

    //@Test
    public void deleteGoodTest(){
        CartDao cartDao = new CartDao();
        int jud = cartDao.dropGoodInCart(1,9);
        System.out.println(jud);
    }

    //@Test
    public void updateGoodTest(){
        CartDao cartDao = new CartDao();
        int jud = 0;
        jud = cartDao.updateGoodInCart(1,9,2);
        System.out.println(jud);
    }

    //@Test
    public void getGoodIDByUserIDTest(){
        CartDao cartDao = new CartDao();
        //int[] jud = cartDao.getGoodIDByUserID(1);
        //System.out.println(jud[0]);
        System.out.println(cartDao.getGoodListByUserID(1));
    }
}

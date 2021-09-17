package daotest;

import com.bluemsun.dao.OrderDao;
import com.bluemsun.entity.Order;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderDaoTest {
    //@Test
    public void insertOrderTest(){
        OrderDao orderDao = new OrderDao();
        BigDecimal money = BigDecimal.valueOf(163);
        System.out.println(orderDao.insertOrder(new Order(1,money,"18736878369","河南省商丘市","windlin")));
    }

    //@Test
    public void getGoodIDTest(){
        OrderDao orderDao = new OrderDao();
        //System.out.println(orderDao.getOrderID(1));
    }

}

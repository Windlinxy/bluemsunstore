package daotest;

import com.bluemsun.dao.PageDao;
import org.junit.Test;

public class PageDaoTest {
    //@Test
    public void getAllOrderTest(){
        PageDao pageDao = new PageDao();
        System.out.println(pageDao.getAllOrder(0,5));
    }
}

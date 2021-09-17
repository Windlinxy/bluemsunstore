package servicetest;

import com.bluemsun.service.impl.PageManagerServiceImpl;
import org.junit.Test;

public class PageManagerServiceTest {
    @Test
    public void searchGoodTest(){
        PageManagerServiceImpl pageManagerService = new PageManagerServiceImpl();
        System.out.println(pageManagerService.getGood(1,1,"其他"));
    }

    @Test
    public void getOrderTest(){
        PageManagerServiceImpl pageManagerService = new PageManagerServiceImpl();
        System.out.println(pageManagerService.getOrder(1,5,2));
    }
}

package com.nowcoder.community;
/*测试事务传播机制 alphaService中的public Object save1()  这个事务是否生效*/
import com.nowcoder.community.service.AlphaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class TransactionTests {
    @Autowired
    private AlphaService alphaService;
    @Test
    public void testSavel(){
        Object obj = alphaService.savel();
        System.out.println(obj);
    }
    @Test
    public void testSave2(){
        Object obj = alphaService.savel2();
        System.out.println(obj);
    }

}

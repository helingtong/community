package com.nowcoder.community;

import com.nowcoder.community.config.AlphaConfig;
import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.dao.AlphaDaoHibernateImpl;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/*在com.example.test下新建controller文件夹，在controller文件夹下建一个简单的helloController类；
（Controller类要添加@Controller注解，项目启动时，SpringBoot会自动扫描加载Controller）*/
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)//测试类启用java包中的communityApplication为配置类
public class CommunityApplicationTests implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;   //程序运行的时候这个容器就被传入进来 ApplicationContexts 接口
    }
    //测试方法中使用测试容器
    @Test
   public void testApplicationContext() {  //测试spring容器
       System.out.println(applicationContext);

        AlphaDao alphaDao =applicationContext.getBean(AlphaDao.class);  //调用的时候和接口有关和具体的接口实现无关
        System.out.println(alphaDao.select());  //输出Mybatis
        alphaDao = applicationContext.getBean("alphaHibernate", AlphaDao.class);    //得到的object转成"alphaHibernate"
        System.out.println(alphaDao.select());  //输出Hibernate
    }
//测试bean管理的模式
    @Test
    public void testBeanmanageMent(){
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
    }
    @Test
    public void testBeanConfig(){
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }
//当前容器要用alphaDao Bean 没有必要主动获取，直接告诉当前容器注入就可以

    @Autowired   //实现注入的注解 bean依赖的是接口，降低了耦合性
     @Qualifier("alphaHibernate")           // 如果不想注入的是默认的mybatis(这个是用了@Primary优先级注解）
    private AlphaDao alphaDao;  //spring容器把Alphadao注入给这个属性，我直接使用这个属性就行

    @Autowired
    private AlphaService alphaService;

    @Autowired
    private AlphaDaoHibernateImpl alphaDaoHibernate;
    @Test
    public void testDI(){   //测试依赖注入
        System.out.println(alphaDao);
        System.out.println(alphaService);
        System.out.println(alphaDaoHibernate);
    }
}

package com.nowcoder.community.service;
//很多业务的时候需要这个service包
import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype") 让bean变成多例的，但是一般我们用的时候都是单例的
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao; //   注入的注解+成员变量    将AlphaDao注入给AlphaService，在处理查询业务中就可以调用
//    对照构造器
    public AlphaService(){
        System.out.println("实例化AlphaService");
    }

    @PostConstruct
//    这个方法会在构造器之后调用
    public void init(){
        System.out.println("初始化AlphaService");
    }
    @PreDestroy
//    在销毁对象之前调用它,不然销毁之后就不能调用了
    /*public void destroy(){
        System.out.println("销毁AlphaService");
    }*/
    public String find(){   //模拟查询业务
       return alphaDao.select();
    }
}

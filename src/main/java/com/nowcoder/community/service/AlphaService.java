package com.nowcoder.community.service;
//很多业务的时候需要这个service包

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

@Service
//@Scope("prototype") 让bean变成多例的，但是一般我们用的时候都是单例的
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao; //   注入的注解+成员变量    将AlphaDao注入给AlphaService，在处理查询业务中就可以调用
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;   //spring自动创建和装配的，可以用其装配事务保证4个特性

    //    对照构造器
    public AlphaService() {

//        System.out.println("实例化AlphaService");
    }

    @PostConstruct
//    这个方法会在构造器之后调用
    public void init() {
//        System.out.println("初始化AlphaService");
    }

    @PreDestroy
//    在销毁对象之前调用它,不然销毁之后就不能调用了
    /*public void destroy(){
        System.out.println("销毁AlphaService");
    }*/
    public String find() {   //模拟查询业务
        return alphaDao.select();
    }
/*//传播机制
    //REQUIRED: 支持当前事务(外部事务),如果不存在则创建新事务.
    // REQUIRES_NEW: 创建一个新事务,并且暂停当前事务(外部事务).
    // NESTED: 如果当前存在事务(外部事务),则嵌套在该事务中执行(独立的提交和回滚),否则就会REQUIRED一样.*/
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    // @Transactional管理事务的 READ_COMMITTED第二个隔离级别读取提交数据
    public Object savel() {
//        新增用户
        User user = new User();
        user.setUsername("alpha");
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setPassword(CommunityUtil.md5("123" + user.getSalt()));
        user.setEmail("alpha@qq.com");
        user.setHeaderUrl("http://image.nowcoder.com/head/99t.png");//头像
        user.setCreateTime(new Date());//new Date()当前时间
        userMapper.insertUser(user);//马上就会生效不会回滚

//        新增帖子
        DiscussPost post = new DiscussPost();
        post.setUserId(post.getId());
        post.setTitle("Hello");
        post.setContent("新人报道！");
        post.setCreateTime(new Date());
        discussPostMapper.insertDiscussPost(post);
        Integer.valueOf("abc");//abc转为整数，肯定会报错， 报错看看能不能回滚回去

        return "ok";
    }
    public Object savel2(){
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        return transactionTemplate.execute(new TransactionCallback<Object>() {

            @Override
            public Object doInTransaction(TransactionStatus status) {
                //        新增用户
                User user = new User();
                user.setUsername("beta");
                user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
                user.setPassword(CommunityUtil.md5("123" + user.getSalt()));
                user.setEmail("beta@qq.com");
                user.setHeaderUrl("http://image.nowcoder.com/head/999t.png");//头像
                user.setCreateTime(new Date());//new Date()当前时间
                userMapper.insertUser(user);//马上就会生效不会回滚

                //        新增帖子
                DiscussPost post = new DiscussPost();
                post.setUserId(post.getId());
                post.setTitle("你好");
                post.setContent("我是新人！");
                post.setCreateTime(new Date());
                discussPostMapper.insertDiscussPost(post);
                Integer.valueOf("abc");//abc转为整数，肯定会报错， 报错看看能不能回滚回去

                return "ok";
            }
        });
    }
}

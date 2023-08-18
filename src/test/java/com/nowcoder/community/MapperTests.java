package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;
    //注意要写注解，注入方法
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Test
    public void testSelectUser(){   //测试数据库中查找用户信息
        User user = userMapper.selectById(101);
        System.out.println(user);
        user = userMapper.selectByName("liubei");
        System.out.println(user);
    }
    @Test
    public void testInsertUser(){   //数据库中添加一条用户信息
        User user = new User();
        user.setUsername("test");
        user.setPassword("123567");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://wwwcoder.com/101.png");
        user.setCreateTime(new Date());
        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }
    @Test
    public void updateUser(){   //修改数据库中的数据
        int rows = userMapper.updatePassword(150,"890789");
        System.out.println(rows);
        rows = userMapper.updateStatus(150,1);
        System.out.println(rows);
    }
    @Test
    public void testDiscussPosts(){
       List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0,0,10);
//       遍历查询每一行的数据结果并输出
       for (DiscussPost post : list){
           System.out.println(post);
       }
//       查询总的行数
        int rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
    }
}
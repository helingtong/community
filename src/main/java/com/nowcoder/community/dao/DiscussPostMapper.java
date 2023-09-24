package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper //写注解才可以打开容器被自动装配
public interface DiscussPostMapper {
    //    查询表里一共多少数据
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);   //多条数据返回的是集合，集合里面的是多条对象 帖子表里有userId,首页查询不用传userid，默认是0 开发用户个人主页的时候需要传userId
//    offset这一页起始行行号 limit这一页最多需要显示多少数据

//    @Param注解用于给参数取别名 可以名字和参数一致 如果这个方法只有一个参数并且在<if>里面使用就必须加别名。动态的
//    查询表里一共多少行
//    声明查询的方法
    int selectDiscussPostRows(@Param("userId") int userId);
//    声明插入的方法
    int insertDiscussPost(DiscussPost discussPost);

//    根据帖子的id查询帖子的详情
    DiscussPost selectDiscussPostById(int id);

    int updateCommentCount(int id,int commentCount);
}

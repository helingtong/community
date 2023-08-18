package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

//UserMapper 用于操作数据库中与用户相关的数据表，通常是进行数据库查询、插入、更新、删除等操作
@Mapper
public interface UserMapper {
//    声明增删改查的方法和对应的mysql的配置文件
//    根据ID，Name,Email查询用户
    User selectById(int id);
    User selectByName(String username);
    User selectByEmail(String email);
//    插入用户信息到数据库中
    int insertUser(User user);
//    更新用户状态
    int updateStatus(int id,int status);
    int updateHeader(int id,String headerUrl);
    int updatePassword(int id,String password);

}

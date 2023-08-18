package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository //用于标识数据访问层组件
@Primary    //使用@Primary注解可以指定一个首选的实现类，告诉Spring容器优先选择该类进行依赖注入。
public class AlphaMyBatisImpl implements AlphaDao{
    @Override
    public String select() {
        return "MyBatis";
    }
}

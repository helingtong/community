package com.nowcoder.community.dao;
//定义AlphaDao接口的实现类，用Hibernate实现
import org.springframework.stereotype.Repository;
//访问数据库的Bean
@Repository("alphaHibernate")   //强制容器返回这个bean
public class AlphaDaoHibernateImpl implements AlphaDao {
    @Override
    public String select() {
        return "Hibernate";
    }
}

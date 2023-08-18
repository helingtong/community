package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class AlphaConfig{
    @Bean
    public SimpleDateFormat simpleDateFormat(){ //方法名字就是bean的名字，bean的名字以方法命名的
         return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }   //这个方法将被装配到容器中
}

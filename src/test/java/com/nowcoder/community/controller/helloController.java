package com.nowcoder.community.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//dao表示 data access object数据库访问对象
public class helloController {
    @Controller
    public class HelloController{
        @RequestMapping("/index")
        public String sayHello(){
            return "index";
        }
    }
}

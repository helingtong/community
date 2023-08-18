package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
//    访问网址：http://localhost:8080/alpha/data
    public String getData(){
        return alphaService.find();
    }
//    如何在springMVC的情况下处理请求数据和响应的数据？ 数据组件封装起来
    @RequestMapping("/http")
//    通过request获取相关数据的方法
//    这个方法的访问路径 Request常用的接口请求对象HttpServletRequest 响应对象HttpServletResponse
    public void http(HttpServletRequest request, HttpServletResponse response) throws IOException {
//       处理请求： 读取请求中包含的数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());   //得到请求的路径
        Enumeration<String> enumeration = request.getHeaderNames();
//        迭代器对象通过while进行遍历
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
        }
        System.out.println(request.getParameter("code"));
//        response 是给浏览器返回响应数据的
        response.setContentType("text/html;charset=utf-8");
        try(
                PrintWriter writer = response.getWriter();
        ) {
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    GET请求 在spring框架的情况下获取浏览器的参数
//    查询student的信息当前多少页，分页查询服务器应该如何处理？student?current=1&limit=20
    @RequestMapping(path = "/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current",required = false,defaultValue = "1") int current,
            @RequestParam(name = "limit",required = false,defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some Students";
    }
// 直接将参数成为路径的一部分 /student/123
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){   //    路径变量 从注解中得到这个id变量赋值给id变量
        return "a student";
    }
//POST请求
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){ //名称对应就会自动传过来
        System.out.println(name);
        System.out.println(age);
        return "success";
    }
//   方法1 向浏览器响应HTML数据
//    服务器查到了老师的相关数据 返回给浏览器
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age","16");
        mav.setViewName("/demo/view");
        return mav;
    }
//    方法2 向浏览器响应HTML数据 更简单
    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
//        model的数据存到参数model里面
        model.addAttribute("name","北京大学");
        model.addAttribute("age",80);
        return "/demo/view";
    }
//    向浏览器响应JSON数据 在异步请求的条件下响应JSON数据
//    当前网页不刷新，但是悄悄反应给浏览器了，就是异步请求
//    java对象 通过JSON（特定格式的字符串）给浏览器传过去转化成JS对象
//    Java对象 ->JSON字符串 ->JS对象
//    如何向浏览器响应这样的字符串
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",14);
        emp.put("salary",8000);
        return emp;
    }
//返回多个相似结构
@RequestMapping(path = "/emps",method = RequestMethod.GET)
@ResponseBody
public List<Map<String, Object>> getEmps(){
    List<Map<String,Object>> list = new ArrayList<>();
    Map<String,Object> emp = new HashMap<>();
    emp.put("name","张三");
    emp.put("age",14);
    emp.put("salary",8000);
    list.add(emp);

    emp = new HashMap<>();
    emp.put("name","李四");
    emp.put("age",24);
    emp.put("salary",9000);

    list.add(emp);
    return list;
    }
}

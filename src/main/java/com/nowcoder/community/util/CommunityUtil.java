package com.nowcoder.community.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommunityUtil {
//    生成随机字符串 UUID可以自动生成字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-",""); //替换所有的-为""
    }
//    MD5加密 不能解密
//    hello -> abc12345def456 每次加密都是这个值
//    都加上随机的字符串再加密更安全 hello+3e4a8 -> abc123der456abc
    public static String md5(String key){   //判断key（就是密码）是否为空   key 原始密码+盐
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes()); //String类型的key转化成byte类型的
    }
    //实现获取JSON字符串的方法。
    public static String getJSONString(int code, String msg, Map<String,Object> map){
        JSONObject json = new JSONObject();
        json.put("code",code);  //code和msg放入map中
        json.put("msg",msg);
        if(map!=null){
            for(String key : map.keySet()){ //遍历map的3种方法：key value key-value
                json.put(key,map.get(key));
            }
        }
        return json.toJSONString();//json里面封装的对象转化成json字符串
    }

    // 在调用时有时候只有编号，没有提示和业务数据  对上面的方法重载，便于调用
    public static String getJSONString(int code,String msg){
        return getJSONString(code,msg,null);
    }
    // 在调用时有时候只有编号，只有编号code
    public static String getJSONString(int code){
        return getJSONString(code,null,null);//json里面封装的对象转化成json字符串
    }

    // 测试
    public static void main(String[] args){  //方法名字一样，参数不一样 个数，顺序， 类型
        Map<String, Object> map = new HashMap<>();
        map.put("name","zhangshan");
        map.put("age",12);
        System.out.println(getJSONString(0,"ok",map));
    }
}


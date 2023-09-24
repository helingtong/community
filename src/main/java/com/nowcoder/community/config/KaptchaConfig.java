package com.nowcoder.community.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.context.annotation.Bean;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {
    @Bean   //bean说明将被spring所管理
    public Producer kaptchaProducer() { //实例化kptcha的接口Producer
        Properties properties = new Properties();   //为了封装properties中的数据
        properties.setProperty("kaptcha.image.width", "100");   //验证码的宽度啥的设置
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.textproducer.font.size", "32");
        properties.setProperty("kaptcha.textproducer.font.color", "0,0,0");
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYAZ");//生成验证码随机字符的组成，给它一个范围
        properties.setProperty("kaptcha.textproducer.char.length", "4");//验证码随机字符的个数
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");//采用哪种干扰类，防止机器人暴力破解

        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Config config = new Config(properties); //给kaptcha传入一些参数放在config中
        kaptcha.setConfig(config);
        return kaptcha;
    }
}

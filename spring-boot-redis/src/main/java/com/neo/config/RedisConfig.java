package com.neo.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    // 自定义redis-key的生成规则

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            /**
             *
             * @param target 目标类
             * @param method 目标方法
             * @param params 目标方法参数
             * @return
             */
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());//获取目标类的全路径名: com.example.d02redis.web.UserController
                sb.append("." + method.getName());//获取目标方法的名: getUser
                for (Object obj : params) { // 参数值:?1
                    sb.append("?" + obj.toString());
                }
                return sb.toString();//com.example.d02redis.web.UserController.getUser?1?han
            }
        };
    }
}
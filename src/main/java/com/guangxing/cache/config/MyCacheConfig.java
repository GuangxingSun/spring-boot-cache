package com.guangxing.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * @author apple
 * @create time 2020/4/29 4:46 下午
 **/
@Configuration
public class MyCacheConfig {

    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new KeyGenerator(){
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                return method.getName()+"["+objects[0]+"]";
            }
        };
    }
}

package com.guangxing.cache.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid配置类
 * @author apple
 * @create time 2020/4/28 7:32 下午
 **/
@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource",ignoreInvalidFields = true)
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");

        Map<String,String> map = new HashMap();
        map.put("loginusername", "admin");
        map.put("loginpassword","123456");

        map.put("allow","");
        map.put("deny", "127.0.0.1");

        registrationBean.setInitParameters(map);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        HashMap<String, String> param = new HashMap<>();
        param.put("exclusions","*.js,*.css,*.jpg,*.css,*.gif,/druid/*");
        filterRegistrationBean.setInitParameters(param);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }
}

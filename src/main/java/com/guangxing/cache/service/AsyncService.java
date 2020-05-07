package com.guangxing.cache.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author apple
 * @create time 2020/5/7 11:37 上午
 * 测试异步任务
 **/
@Service
public class AsyncService {

    @Async
    public void hello(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("打印输出");
    }
}

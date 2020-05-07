package com.guangxing.cache.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 定时任务测试
 * @create time 2020/5/7 11:58 上午
 **/
@Service
public class ScheduledService {

    @Scheduled(cron = "0 * * * * MON-STA")
    @Scheduled(cron = "0,1,2,3,4 * * * * MON-STA")
    @Scheduled(cron = "0-4 * * * * 1-6")
    @Scheduled(cron = "0/4 * * * * MON-STA")//每4秒执行一次
    public void scheduled(){
        System.out.println("hello……");
    }
}

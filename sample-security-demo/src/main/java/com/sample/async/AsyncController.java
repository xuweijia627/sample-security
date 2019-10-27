package com.sample.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * @author xuWeiJia
 * @date 2019/10/20
 */
@Slf4j
@RestController
public class AsyncController {

    @RequestMapping("/order")
    public Callable<String> order() throws InterruptedException {

        log.info("主线程开始");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("副线程开始");
				Thread.sleep(1000);
                log.info("副线程返回");
                return "success";
            }
        };
        log.info("主线程返回");
        return callable;
    }

}

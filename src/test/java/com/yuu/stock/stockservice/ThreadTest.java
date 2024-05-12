package com.yuu.stock.stockservice;

import com.yuu.stock.stockservice.common.Result;
import com.yuu.stock.stockservice.dto.OrderDTO;
import com.yuu.stock.stockservice.service.IStockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadTest {

    @Autowired
    private IStockService stockService;

    /**
     * 并发测试重试机制
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        AtomicInteger count = new AtomicInteger();
        int clientTotal = 100;
        // 同时并发执行的线程数
        int threadTotal = 20;
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量，此处用于控制并发的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);
        //闭锁，可实现计数器递减
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    OrderDTO dto = new OrderDTO();
                    dto.setStockId(1L);
                    Random random = new Random();
                    dto.setNum(random.nextInt(10)+1);
                    //执行此方法用于获取执行许可，当总计未释放的许可数不超过200时，
                    //允许通行，否则线程阻塞等待，直到获取到许可。
                    semaphore.acquire();
                    Result order = stockService.order(dto);
                    count.addAndGet((Integer) order.get("data"));
                    //释放许可
                    semaphore.release();
                } catch (Exception e) {
                    //log.error("exception", e);
                    e.printStackTrace();

                }
                //闭锁减一
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();//线程阻塞，直到闭锁值为0时，阻塞才释放，继续往下执行
        executorService.shutdown();
        System.out.println("下单总数量:" + count.get());
    }
}

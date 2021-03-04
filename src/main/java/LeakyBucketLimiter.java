import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: leixingbang@qiyi.com
 * @create: 2021/03/04 10:09
 * @description:
 * 漏桶算法实现限流操作
 * 当前水量按照 = 当前水量 - 流速*时间 即为：
 * water = Math.max(0, (int) (water - (now - lastTime) * rate /1000));【定时任务，每隔0.5s从工作队列中取出固定的水量】
 * 如果剩余空间= 容量 -当前水量 < 请求数量 即为：capacity - water < permits
 * 则请求拒绝
 * 否则水量进行累加： water += permits;
 */
public class LeakyBucketLimiter {
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    // 桶的容量
    public int capacity = 10;
    // 当前水量
    public int water = 0;
    //水流速度/s
    public int rate = 4;
    // 最后一次加水时间
    public long lastTime = System.currentTimeMillis();

    public void acquire() {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            long now = System.currentTimeMillis();
            //计算当前水量
            water = Math.max(0, (int) (water - (now - lastTime) * rate /1000));
            int permits = (int) (Math.random() * 8) + 1;
            System.out.println("请求数：" + permits + "，当前桶余量：" + (capacity - water));
            lastTime = now;
            if (capacity - water < permits) {
                // 若桶满,则拒绝
                System.out.println("限流了");
            } else {
                // 还有容量
                water += permits;
                System.out.println("剩余容量=" + (capacity - water));
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        LeakyBucketLimiter limiter = new LeakyBucketLimiter();
        limiter.acquire();
    }
}
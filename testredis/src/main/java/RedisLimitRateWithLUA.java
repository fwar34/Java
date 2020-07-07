//https://segmentfault.com/a/1190000016552464?utm_source=sf-related
import org.apache.commons.io.FileUtils;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class RedisLimitRateWithLUA {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 7; ++i) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                        System.out.println("请求是否被执行:" + accquire());
                    } catch (Exception e ) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        latch.countDown();
    }

    public static boolean accquire() throws IOException, URISyntaxException {
        Jedis jedis = new Jedis("192.168.32.86");
//        File luaFile = new File(RedisLimitRateWithLUA.class.getResource("/").toURI().getPath() + "limit.lua");
        File luaFile = new File(RedisLimitRateWithLUA.class.getResource("/").toURI().getPath() + "rate_limit.lua");
        String luaScript = FileUtils.readFileToString(luaFile);

        String key = "ip:" + System.currentTimeMillis() / 1000;
        String limit = "5";
        List<String> keys = new ArrayList<String>();
        keys.add(key);
        List<String> args = new ArrayList<String>();
        args.add(limit);
        args.add("5");
        Long result = (Long)(jedis.eval(luaScript, keys, args));
        return result == 1;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Publisher extends Thread{

    private final JedisPool jedisPool;
    private final String channel;

    Publisher(JedisPool jedisPool, String channel) {
        this.jedisPool = jedisPool;
        this.channel = channel;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Jedis jedis = jedisPool.getResource();   //连接池中取出一个连接
        while (true) {
            String line;
            try {
                line = reader.readLine();
                if ("quit".equals(line)) {
                    Util.print("pub(2)");
                    break;
                } else if (line.startsWith(channel)) {
                    jedis.publish(channel, line);   //从 mychannel 的频道上推送消息
                    Util.print("pub(1)");
                }
            } catch (IOException e) {
                e.printStackTrace();
                Util.print("pub(3)");
            }
        }
        Util.print("pub(4)");
    }
}
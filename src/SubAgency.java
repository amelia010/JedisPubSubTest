import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class SubAgency extends Thread {

    private final JedisPool jedisPool;
    private final Subscriber subscriber;

    private final String channel;

    SubAgency(JedisPool jedisPool, Subscriber subs, String channel) {
        super("SubThread");
        this.jedisPool = jedisPool;
        this.subscriber = subs;
        this.channel = channel;
    }

    @Override
    public void run() {
        Util.print(String.format("subscribe redis, channel %s, thread will be blocked", channel));
        Jedis jedis  = null;
        try {
            Util.print("SubThread.run(1)");
            jedis = jedisPool.getResource();   //取出一个连接
            Util.print("SubThread.run(2)");
            jedis.subscribe(subscriber, channel);    //通过subscribe 的api去订阅，入参是订阅者和频道名
            Util.print("SubThread.run(3)");
        } catch (Exception e) {
            Util.print(String.format("subsrcibe channel error, %s", e));
            Util.print("SubThread.run(4)");
        } finally {
            if (jedis != null) {
                jedis.close();
                Util.print("SubThread.run(5)");
            }
            Util.print("SubThread.run(6)");
        }
    }
}
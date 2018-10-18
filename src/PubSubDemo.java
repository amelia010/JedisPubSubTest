import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class PubSubDemo {

    public static void main( String[] args )
    {
        // 连接redis服务端
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);
        System.out.println(String.format("redis pool is starting, redis ip %s, redis port %d", "127.0.0.1", 6379));
        Subscriber subscriber = new Subscriber();
        String channel1 = "Pub1";
        String channel2 = "Pub2";
        //
        Publisher publisher = new Publisher(jedisPool, channel1);    //发布者
        publisher.start();
        Util.print("main(1)");

     //   Publisher pub2 = new Publisher(jedisPool, channel2);    //发布者
     //   pub2.start();
     //   Util.print("main(2)");
        //
        SubAgency subAgent = new SubAgency(jedisPool, subscriber, channel1);  //订阅者
        subAgent.start();
        Util.print("main(3)");

     //   SubAgency subAgent2 = new SubAgency(jedisPool, subscriber, channel2);  //订阅者
     //   subAgent2.start();
     //   Util.print("main(4)");
    }
}
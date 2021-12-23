package cn.wsg.hello.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;

/**
 * @author Kingen
 */
public class RedisCli {

    private Jedis jedis;
    private JedisPool jedisPool;
    private ShardedJedis shardedJedis;
    private ShardedJedisPool shardedJedisPool;

    public RedisCli() {
        initial();
    }

    public static void main(String[] args) {
        RedisCli cli = new RedisCli();
        cli.jedis.flushDB();
        cli.keyOperate();
        cli.stringOperate();
        cli.listOperate();
        cli.setOperate();
        cli.sortedSetOperate();
        cli.hashOperate();
    }

    private void initial() {
        initialPool();
        initialSharedPool();
        shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();
    }

    private void initialPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(20);
        config.setMaxIdle(5);
        config.setMaxWait(1000L);
        config.setTestOnBorrow(false);
        jedisPool = new JedisPool(config, "127.0.0.1", 6379);
    }

    private void initialSharedPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(20);
        config.setMaxIdle(5);
        config.setMaxWait(1000L);
        config.setTestOnBorrow(false);
        List<JedisShardInfo> shards = new ArrayList<>();
        shards.add(new JedisShardInfo("127.0.0.1", 6379, "master"));

        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    public void keyOperate() {
        System.out.println("======================key==========================");
        System.out.println("清空库中所有数据：" + jedis.flushDB());
        System.out.println("判断key999键是否存在：" + shardedJedis.exists("key999"));
        System.out.println("新增key001,value001键值对：" + shardedJedis.set("key001", "value001"));
        System.out.println("判断key001是否存在：" + shardedJedis.exists("key001"));
        System.out.println("新增key002,value002键值对：" + shardedJedis.set("key002", "value002"));
        System.out.println("系统中所有键如下：");
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println("系统中删除key002: " + jedis.del("key002"));
        System.out.println("判断key002是否存在：" + shardedJedis.exists("key002"));
        System.out.println("设置 key001的过期时间为5秒:" + jedis.expire("key001", 5));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("查看key001的剩余生存时间：" + jedis.ttl("key001"));
        System.out.println("移除key001的生存时间：" + jedis.persist("key001"));
        System.out.println("查看key001的剩余生存时间：" + jedis.ttl("key001"));
        System.out.println("查看key所储存的值的类型：" + jedis.type("key001"));
        jedis.rename("key6", "key0");
        jedis.move("foo", 1);
    }

    private void stringOperate() {
        System.out.println("======================String_1==========================");
        System.out.println("清空库中所有数据：" + jedis.flushDB());
        System.out.println("=============增=============");
        jedis.set("key001", "value001");
        jedis.set("key002", "value002");
        jedis.set("key003", "value003");
        System.out.println("已新增的3个键值对如下：");
        System.out.println(jedis.get("key001"));
        System.out.println(jedis.get("key002"));
        System.out.println(jedis.get("key003"));

        System.out.println("=============删=============");
        System.out.println("删除key003键值对：" + jedis.del("key003"));
        System.out.println("获取key003键对应的值：" + jedis.get("key003"));

        System.out.println("=============改=============");
        System.out.println("直接覆盖key001原来的数据：" + jedis.set("key001", "value001-update"));
        System.out.println("获取key001对应的新值：" + jedis.get("key001"));
        System.out.println("在key002原来值后面追加：" + jedis.append("key002", "+appendString"));
        System.out.println("获取key002对应的新值" + jedis.get("key002"));

        System.out.println("=============增，删，查（多个）=============");
        System.out
            .println("一次性新增key201,key202,key203,key204及其对应值：" + jedis.mset("key201", "value201",
                "key202", "value202", "key203", "value203", "key204", "value204"));
        System.out.println("一次性获取key201,key202,key203,key204各自对应的值：" +
            jedis.mget("key201", "key202", "key203", "key204"));
        System.out.println("一次性删除key201,key202：" + jedis.del("key201", "key202"));
        System.out.println("一次性获取key201,key202,key203,key204各自对应的值：" +
            jedis.mget("key201", "key202", "key203", "key204"));
        System.out.println();

        System.out.println("======================String_2==========================");
        System.out.println("清空库中所有数据：" + jedis.flushDB());

        System.out.println("=============新增键值对时防止覆盖原先值=============");
        System.out.println("原先key301不存在时，新增key301：" + shardedJedis.setnx("key301", "value301"));
        System.out.println("原先key302不存在时，新增key302：" + shardedJedis.setnx("key302", "value302"));
        System.out.println("当key302存在时，尝试新增key302：" + shardedJedis.setnx("key302", "value302_new"));
        System.out.println("获取key301对应的值：" + shardedJedis.get("key301"));
        System.out.println("获取key302对应的值：" + shardedJedis.get("key302"));

        System.out.println("=============超过有效期键值对被删除=============");
        System.out
            .println("新增key303，并指定过期时间为2秒" + shardedJedis.setex("key303", 2, "key303-2second"));
        System.out.println("获取key303对应的值：" + shardedJedis.get("key303"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("3秒之后，获取key303对应的值：" + shardedJedis.get("key303"));

        System.out.println("=============获取原值，更新为新值一步完成=============");
        System.out.println("key302原值：" + shardedJedis.getSet("key302", "value302-after-getset"));
        System.out.println("key302新值：" + shardedJedis.get("key302"));

        System.out.println("=============获取子串=============");
        System.out.println("获取key302对应值中的子串：" + shardedJedis.getrange("key302", 5, 7));
    }

    private void listOperate() {
        System.out.println("======================list==========================");
        System.out.println("清空库中所有数据：" + jedis.flushDB());

        System.out.println("=============增=============");
        shardedJedis.lpush("stringLists", "vector");
        shardedJedis.lpush("stringLists", "ArrayList");
        shardedJedis.lpush("stringLists", "vector");
        shardedJedis.lpush("stringLists", "vector");
        shardedJedis.lpush("stringLists", "LinkedList");
        shardedJedis.lpush("stringLists", "MapList");
        shardedJedis.lpush("stringLists", "SerialList");
        shardedJedis.lpush("stringLists", "HashList");
        shardedJedis.lpush("numberLists", "3");
        shardedJedis.lpush("numberLists", "1");
        shardedJedis.lpush("numberLists", "5");
        shardedJedis.lpush("numberLists", "2");
        System.out.println("所有元素-stringLists：" + shardedJedis.lrange("stringLists", 0, -1));
        System.out.println("所有元素-numberLists：" + shardedJedis.lrange("numberLists", 0, -1));

        System.out.println("=============删=============");
        System.out
            .println("成功删除指定元素个数-stringLists：" + shardedJedis.lrem("stringLists", 2, "vector"));
        System.out.println("删除指定元素之后-stringLists：" + shardedJedis.lrange("stringLists", 0, -1));
        System.out.println("删除下标0-3区间之外的元素：" + shardedJedis.ltrim("stringLists", 0, 3));
        System.out.println("删除指定区间之外元素后-stringLists：" + shardedJedis.lrange("stringLists", 0, -1));
        System.out.println("出栈元素：" + shardedJedis.lpop("stringLists"));
        System.out.println("元素出栈后-stringLists：" + shardedJedis.lrange("stringLists", 0, -1));

        System.out.println("=============改=============");
        shardedJedis.lset("stringLists", 0, "hello list!");
        System.out.println("下标为0的值修改后-stringLists：" + shardedJedis.lrange("stringLists", 0, -1));
        System.out.println("=============查=============");
        System.out.println("长度-stringLists：" + shardedJedis.llen("stringLists"));
        System.out.println("长度-numberLists：" + shardedJedis.llen("numberLists"));
        SortingParams sortingParameters = new SortingParams();
        sortingParameters.alpha();
        sortingParameters.limit(0, 3);
        System.out
            .println("返回排序后的结果-stringLists：" + shardedJedis.sort("stringLists", sortingParameters));
        System.out.println("返回排序后的结果-numberLists：" + shardedJedis.sort("numberLists"));
        System.out.println("子串-第二个开始到结束：" + shardedJedis.lrange("stringLists", 1, -1));
        System.out.println("获取下标为2的元素：" + shardedJedis.lindex("stringLists", 2) + "\n");
    }

    private void setOperate() {
        System.out.println("======================set==========================");
        System.out.println("清空库中所有数据：" + jedis.flushDB());
        System.out.println("=============增=============");
        System.out.println("向sets集合中加入元素element001：" + jedis.sadd("sets", "element001"));
        System.out.println("向sets集合中加入元素element002：" + jedis.sadd("sets", "element002"));
        System.out.println("向sets集合中加入元素element003：" + jedis.sadd("sets", "element003"));
        System.out.println("向sets集合中加入元素element004：" + jedis.sadd("sets", "element004"));
        System.out.println("查看sets集合中的所有元素:" + jedis.smembers("sets"));
        System.out.println();
        System.out.println("=============删=============");
        System.out.println("集合sets中删除元素element003：" + jedis.srem("sets", "element003"));
        System.out.println("查看sets集合中的所有元素:" + jedis.smembers("sets"));
        System.out.println();

        System.out.println("=============改=============");
        System.out.println();

        System.out.println("=============查=============");
        System.out.println("判断element001是否在集合sets中：" + jedis.sismember("sets", "element001"));
        System.out.println("循环查询获取sets中的每个元素：");
        Set<String> set = jedis.smembers("sets");
        for (Object obj : set) {
            System.out.println(obj);
        }
        System.out.println();

        System.out.println("=============集合运算=============");
        System.out.println("sets1中添加元素element001：" + jedis.sadd("sets1", "element001"));
        System.out.println("sets1中添加元素element002：" + jedis.sadd("sets1", "element002"));
        System.out.println("sets1中添加元素element003：" + jedis.sadd("sets1", "element003"));
        System.out.println("sets1中添加元素element002：" + jedis.sadd("sets2", "element002"));
        System.out.println("sets1中添加元素element003：" + jedis.sadd("sets2", "element003"));
        System.out.println("sets1中添加元素element004：" + jedis.sadd("sets2", "element004"));
        System.out.println("查看sets1集合中的所有元素:" + jedis.smembers("sets1"));
        System.out.println("查看sets2集合中的所有元素:" + jedis.smembers("sets2"));
        System.out.println("sets1和sets2交集：" + jedis.sinter("sets1", "sets2"));
        System.out.println("sets1和sets2并集：" + jedis.sunion("sets1", "sets2"));
        System.out.println("sets1和sets2差集：" + jedis.sdiff("sets1", "sets2"));

    }

    private void sortedSetOperate() {
        System.out.println("======================zset==========================");
        // 清空数据
        System.out.println(jedis.flushDB());

        System.out.println("=============增=============");
        System.out.println("zset中添加元素element001：" + shardedJedis.zadd("zset", 7.0, "element001"));
        System.out.println("zset中添加元素element002：" + shardedJedis.zadd("zset", 8.0, "element002"));
        System.out.println("zset中添加元素element003：" + shardedJedis.zadd("zset", 2.0, "element003"));
        System.out.println("zset中添加元素element004：" + shardedJedis.zadd("zset", 3.0, "element004"));
        System.out.println("zset集合中的所有元素：" + shardedJedis.zrange("zset", 0, -1));//按照权重值排序
        System.out.println();

        System.out.println("=============删=============");
        System.out.println("zset中删除元素element002：" + shardedJedis.zrem("zset", "element002"));
        System.out.println("zset集合中的所有元素：" + shardedJedis.zrange("zset", 0, -1));
        System.out.println();

        System.out.println("=============改=============");
        System.out.println();

        System.out.println("=============查=============");
        System.out.println("统计zset集合中的元素中个数：" + shardedJedis.zcard("zset"));
        System.out
            .println("统计zset集合中权重某个范围内（1.0——5.0），元素的个数：" + shardedJedis.zcount("zset", 1.0, 5.0));
        System.out.println("查看zset集合中element004的权重：" + shardedJedis.zscore("zset", "element004"));
        System.out.println("查看下标1到2范围内的元素值：" + shardedJedis.zrange("zset", 1, 2));

    }

    private void hashOperate() {
        System.out.println("======================hash==========================");
        //清空数据
        System.out.println(jedis.flushDB());

        System.out.println("=============增=============");
        System.out.println(
            "hashs中添加key001和value001键值对：" + shardedJedis.hset("hashs", "key001", "value001"));
        System.out.println(
            "hashs中添加key002和value002键值对：" + shardedJedis.hset("hashs", "key002", "value002"));
        System.out.println(
            "hashs中添加key003和value003键值对：" + shardedJedis.hset("hashs", "key003", "value003"));
        System.out.println("新增key004和4的整型键值对：" + shardedJedis.hincrBy("hashs", "key004", 4L));
        System.out.println("hashs中的所有值：" + shardedJedis.hvals("hashs"));
        System.out.println();

        System.out.println("=============删=============");
        System.out.println("hashs中删除key002键值对：" + shardedJedis.hdel("hashs", "key002"));
        System.out.println("hashs中的所有值：" + shardedJedis.hvals("hashs"));
        System.out.println();

        System.out.println("=============改=============");
        System.out.println("key004整型键值的值增加100：" + shardedJedis.hincrBy("hashs", "key004", 100L));
        System.out.println("hashs中的所有值：" + shardedJedis.hvals("hashs"));
        System.out.println();

        System.out.println("=============查=============");
        System.out.println("判断key003是否存在：" + shardedJedis.hexists("hashs", "key003"));
        System.out.println("获取key004对应的值：" + shardedJedis.hget("hashs", "key004"));
        System.out
            .println("批量获取key001和key003对应的值：" + shardedJedis.hmget("hashs", "key001", "key003"));
        System.out.println("获取hashs中所有的key：" + shardedJedis.hkeys("hashs"));
        System.out.println("获取hashs中所有的value：" + shardedJedis.hvals("hashs"));
        System.out.println();
    }
}

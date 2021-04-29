package com.neo;

import com.neo.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class MyTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增
     */
    @Test
    void testBasic() {
        // 普通的新增
        stringRedisTemplate.opsForValue().set("h1", "111111");
        // 设置过期时间
        stringRedisTemplate.opsForValue().set("h2", "hhhhh", 6, TimeUnit.MINUTES);
        // 持久化
        System.out.println(stringRedisTemplate.persist("h1"));

        stringRedisTemplate.opsForValue().set("h3", "111111");
        stringRedisTemplate.opsForValue().set("h4", "111111");
        stringRedisTemplate.opsForValue().set("h5", "111111");

    }

    /**
     * 查询
     */
    @Test
    void test2() {
        // 判断是否有key所对应的值，有则返回true，没有则返回false
        System.out.println(stringRedisTemplate.hasKey("h1"));//true
        // 返回传入key所存储的值的类型
        System.out.println(stringRedisTemplate.type("h1"));//STRING
        // 有则取出key值所对应的值
        System.out.println(stringRedisTemplate.opsForValue().get("h1"));//111111

        // 过期相关
        // 查看key是否会在某个date过期(返回boolean值)
        System.out.println(stringRedisTemplate.expireAt("h2", new Date()));//过期时间1分钟 true
        // 返回当前key所对应的剩余过期时间(-2:已经过期)
        System.out.println(stringRedisTemplate.getExpire("h2"));
        System.out.println(stringRedisTemplate.getExpire("h2", TimeUnit.SECONDS));

    }

    /**
     * 修改 删除
     */
    @Test
    public void test3() {
        // 修改redis中key的名称(如果key不存在,会报错JedisDataException: ERR no such key)
        stringRedisTemplate.rename("h3", "hh2");//没有返回值
        // 如果key存在,则修改(如果key不存在,会报错JedisDataException: ERR no such key)
        stringRedisTemplate.renameIfAbsent("h3", "hh2");
        // 删除单个key值(删除成功,返回true; key不存在时,不会报错,会返回false)
        System.out.println(stringRedisTemplate.delete("h3"));//true:表示删除成功

    }

    /**
     * 存储对象
     */
    @Test
    public void test4() throws InterruptedException {
        User user = new User("aa@126.com", "aa", "aa123456", "aa", "123");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("com.neox", user);
        operations.set("com.neo.f", user, 1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists = redisTemplate.hasKey("com.neo.f");
        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
    }
}
























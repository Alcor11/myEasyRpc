package com.alcor.rpc;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.io.*;

/**
 * @author guchun
 * @description redis工具类
 * @date 2022/6/10 16:21
 */
@Slf4j
public class JedisUtils {

    private static Jedis jedis = new Jedis("127.0.0.1", 6379);

    public static void recordCache(byte[] key, byte[] value) {
        jedis.set(key, value);
    }

    public static byte[] getObject(byte[] key) {
        return jedis.get(key);
    }

    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            log.warn("error" + e);
        } finally {

            try {
                if (baos != null) {
                    baos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (Exception e) {
                log.warn(e.getMessage());
            }
        }
        return null;
    }

    public static Object deserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            try {
                if (bais != null) {
                    bais.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (Exception e) {
                log.warn(e.getMessage());
            }
        }
        return null;
    }


}

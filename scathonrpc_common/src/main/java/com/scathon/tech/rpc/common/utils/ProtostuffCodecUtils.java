package com.scathon.tech.rpc.common.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Protostuff 序列化反序列化工具类.
 *
 * @ClassName ProtostuffCodecUtils.
 * @Author linhd eng:ScathonLin
 * @Date 2019/5/3
 * @Version 1.0
 */
public final class ProtostuffCodecUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProtostuffCodecUtils.class);

    private ProtostuffCodecUtils() {
    }

    /**
     * 反序列化.
     *
     * @param serialBytes 字节数组.
     * @param objType     目标反序列化类型.
     * @param <T>         泛型类型参数.
     * @return 反序列化对象.
     */
    public static <T> Optional<T> deserialize(byte[] serialBytes, Class<T> objType) {

        if (serialBytes == null || serialBytes.length == 0) {
            LOGGER.warn("failed to deserialize the bytes to target obj: {}", objType.getSimpleName());
            return Optional.empty();
        }

        Schema<T> schema = RuntimeSchema.getSchema(objType);
        T targetObj = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(serialBytes, targetObj, schema);
        return Optional.of(targetObj);
    }

    /**
     * 序列化.
     *
     * @param objToSerialize 被序列化的对象.
     * @param objType        对象类型.
     * @param <T>            泛型类型参数.
     * @return 序列化后的字节数组.
     */
    public static <T> Optional<byte[]> serialize(T objToSerialize, Class<T> objType) {

        if (objToSerialize == null) {
            LOGGER.warn("obj is null, it's will not be serialized.. obj type is : {}", objType.getSimpleName());
            return Optional.empty();
        }

        Schema<T> schema = RuntimeSchema.getSchema(objType);
        LinkedBuffer buffer = LinkedBuffer.allocate(512);
        byte[] bytesBuffer = ProtostuffIOUtil.toByteArray(objToSerialize, schema, buffer);
        buffer.clear();
        return Optional.of(bytesBuffer);
    }
}

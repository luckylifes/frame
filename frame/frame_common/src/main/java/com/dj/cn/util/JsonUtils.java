package com.dj.cn.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * @version V1.0
 * @Title: JsonUtils
 * @Package: com.dj.cn.util
 * @author: Lenovo
 * @date: 2019/7/22 11:18
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class JsonUtils {
    /**
     * ObjectMapper
     */
    protected static ObjectMapper objectMapper = new ObjectMapper();

    /**
     *  Serialize string to object.  将对象序列化为String
     * @param value Object need serialize
     * @return String Json string
     * @throws JsonProcessingException
     */
    public static String serialize(Object value) throws JsonProcessingException {
        if (value == null) {
            return null;
        }

        return objectMapper.writeValueAsString(value);
    }

    /**
     * Deserialize object to string.  将对象反序列化为字符串
     * @param value String need to be deserialized.
     * @param clazz Class object
     * @return {@code T}
     * @throws IOException
     */
    public static <T> T deserialize(String value, Class<T> clazz) throws IOException {
        if (JsonStringUtils.isNullOrEmpty(value)) {
            return null;
        }

        return objectMapper.readValue(value, clazz);
    }

    /**
     * Deserialize jsonParser object to Date object. 将jsonParser对象反序列化为Date对象。
     * @param jsonParser JsonParser object.
     * @param dateFormat DateFormat object.
     * @return Date
     * @throws Exception
     */
    public static Date deserializeToDate(JsonParser jsonParser, DateFormat dateFormat) throws Exception {
        String text = jsonParser.getText();
        return dateFormat.parse(text);
    }

    /**
     * Deserialize string value to List. 反序列化要列表的字符串值。
     * @param value Json string value.
     * @param collectionClass Class object for collection.
     * @param elementClasses Class object for element in collection.
     * @return {@code List<T>}
     * @throws IOException From readValue.
     */
    public static <T> List<T> deserializeToList(String value, Class<?> collectionClass, Class<T> elementClasses) throws IOException {
        if (JsonStringUtils.isNullOrEmpty(value)) {
            return null;
        }

        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        return objectMapper.readValue(value, javaType);
    }
}

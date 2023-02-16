package com.zjq.test.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.*;

public class ReadProper {
    /**
     * 通过配置文件名读取内容
     * @param fileName
     * @return
     */
    public static Properties readPropertiesFile(String fileName) {
        try {
            Resource resource = new ClassPathResource(fileName);
            Properties props = PropertiesLoaderUtils.loadProperties(resource);

            return props;
        } catch (Exception e) {
            System.out.println("————读取配置文件：" + fileName + "出现异常，读取失败————");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Properties properties = readPropertiesFile("application.properties");
        String clss = properties.getProperty("cls1");
        List<String> split1 = Arrays.asList(clss.split(","));
        for (String cls:split1){

        }

    }
}


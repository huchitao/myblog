package cn.zptc.blog.util;


import cn.zptc.blog.constant.ConfigConstant;
import cn.zptc.blog.service.ConfigService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class ConfigUtils {

    public static StringRedisTemplate staticRedisTemplate;

    public static ConfigService staticConfigService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ConfigService configService;

    @PostConstruct
    public void init(){
        staticRedisTemplate=redisTemplate;
        staticConfigService=configService;
    }

    public static Map<String,String> getInfo(){
        String info = staticRedisTemplate.opsForValue().get(ConfigConstant.CONFIG_REDIS_KEY);
        Map<String, String> data=null;
        if(!StringUtils.isEmpty(info)){
            data = JSON.parseObject(info,new TypeReference<Map<String, String>>() {
            });
        }else {
            data=staticConfigService.updateRedisInfo();
        }
        return data;
    }

}

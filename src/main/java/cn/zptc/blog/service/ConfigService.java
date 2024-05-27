package cn.zptc.blog.service;

import cn.zptc.blog.entity.Config;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ConfigService {
    Integer updateValue(String key,String value);

    List<Config> getAll();

    void updateMess(String updateValue, Integer type);

    void upload(MultipartFile multipartFile, Integer type) throws IOException;

    Map<String, String> updateRedisInfo();
}

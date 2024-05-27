package cn.zptc.blog.service.serviceImpl;

import cn.hutool.core.io.FileUtil;
import cn.zptc.blog.constant.ConfigConstant;
import cn.zptc.blog.dao.ConfigDao;
import cn.zptc.blog.entity.Config;
import cn.zptc.blog.service.ConfigService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Value("${file.upload}")
    private String uploadUrl;

    @Value("${file.baseUrl}")
    private String baseUrl;

    @Autowired
    private ConfigDao configDao;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public Integer updateValue(String key,String value) {
        return configDao.updateValue(key,value);
    }

    @Override
    public List<Config> getAll() {
        return configDao.getAll();
    }

    @Override
    public void updateMess(String updateValue, Integer type) {
        if(type==1){
            this.updateValue("qq",updateValue);
        }else {
            this.updateValue("email",updateValue);
        }
        updateRedisInfo();
    }

    @Override
    public void upload(MultipartFile multipartFile, Integer type) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().replaceAll("-","")+suffix;
        String result=save(uploadUrl,(FileInputStream)multipartFile.getInputStream(),fileName);
        String resultUrl = baseUrl + result;
        if(type==1){
            this.updateValue("wechatQrCode",resultUrl);
        }else if(type==2){
            this.updateValue("wechatCollectionCode",resultUrl);
        }else {
            this.updateValue("alipayCollectionCode",resultUrl);
        }
        updateRedisInfo();
    }

    public Map<String, String> updateRedisInfo(){
        List<Config> list= this.getAll();
        Map<String, String> data = list.stream().collect(Collectors.toMap(Config::getConfigKey, Config::getConfigValue));
        String info = JSON.toJSONString(data);
        redisTemplate.opsForValue().set(ConfigConstant.CONFIG_REDIS_KEY,info);
        return data;
    }

    public static String save(String uploadPath,FileInputStream fis, String fileName) {
        FileOutputStream fos = null;
        //创建通道
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        //图片公用的存储位置
        String publicPath = null;
        try {
            publicPath = uploadPath+fileName;
            //如不存在则创建目录及文件
            FileUtil.touch(publicPath);
            fos = new FileOutputStream(publicPath);
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            //通道间传输
            inChannel.transferTo(0, inChannel.size(), outChannel);
            inChannel.close();
            outChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "file/"+fileName;
    }
}

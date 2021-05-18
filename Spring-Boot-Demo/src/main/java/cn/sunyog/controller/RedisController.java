package cn.sunyog.controller;

import cn.sunyog.entity.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: MysteriousGT
 * @Date: 2021/5/18 3:27 下午
 * @Desc: redis相关功能
 */
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/publish/channel")
    public String publishChannel(@RequestParam String msg) throws JsonProcessingException {
        String[] permission={"abc","def"};
        UserDto user =
                new UserDto().setPermission(permission).setMobile(msg).setFullName(msg).setUsername(msg).setPassword(msg);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        redisTemplate.convertAndSend("channel1",json);
        log.info("====>频道channel1，消息发布成功！");
        return "channel1 message send success!";
    }

    @GetMapping("/add/str")
    public String addStr(@RequestParam String key,@RequestParam String val){
        redisTemplate.opsForValue().set(key,val);
        return "redis key value add success!";
    }

    @GetMapping("/get/{key}")
    public String getVal(@PathVariable(name = "key")String key){
        Object res = redisTemplate.opsForValue().get(key);
        return String.valueOf(res);
    }

}

package cc.young.system.controller;

import cc.yound.common.core.util.R;
import cc.young.common.redis.service.RedisService;
import cc.young.system.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * description: TestController
 * date: 2020/9/19 18:09
 * author: faner
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private WxService wxService;

    @Autowired
    private RedisService redisService;
    /**
     * 小程序openId
     */
    @RequestMapping("/getOpenId")
    public Map<String,Object> getOpenId(@RequestParam("code") String code){
        return wxService.getOpenId(code);
    }

    /**
     * add
     * 购物车测试
     */
    @RequestMapping("/add")
    public R add(){
        Boolean exists = redisService.hHasKey("1","2");
        if (exists){
            System.out.println("存在啦");
        }
        Map<String, Object> res = new HashMap<>();
        res.put("fan",123);
        redisService.hset("1","2",res);

        redisService.hdel("1","2");
        return R.ok();
    }

}

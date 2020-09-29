package cc.young.system.controller;

import cc.young.system.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 小程序openId
     */
    @RequestMapping("/getOpenId")
    public Map<String,Object> getOpenId(@RequestParam("code") String code){
        return wxService.getOpenId(code);
    }

}

package cc.young.system.util;

import cc.yound.common.core.exception.FailedException;
import cc.young.system.model.TemplateMsg;
import cc.young.system.model.WxConfig;

import java.util.List;
import java.util.Map;

public class WeixinApiUtils {

    public static Map<String, Object> getOpendIdAndSessionKey(WxConfig config, String jsCode) {
        Map<String, Object> map = WeixinUtils.jscode2session(config, jsCode);
        System.out.println(map);
        if (map.containsKey("errcode")) {
            throw new FailedException("jscode失效");
        }
        return map;
    }

    public static Map<String, Object> getAccessToken(WxConfig config) {
        Map<String, Object> map = WeixinUtils.getAccessToken(config);
        if (!map.containsKey("access_token")) {
            throw new FailedException("获取access_token失败");
        }
        return map;
    }

    public static Map<String,Object> pushMsg(String accessToken, String touser, String templateId, String formId, List<TemplateMsg> value) {
        Map<String, Object> map = WeixinUtils.pushMsg(accessToken,touser,templateId,formId,value);
        return map;
    }


}

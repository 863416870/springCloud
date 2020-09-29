package cc.young.system.util;

import cc.young.system.model.TemplateMsg;
import cc.young.system.model.WxConfig;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeixinUtils {



    public static Map<String, Object> jscode2session(WxConfig config, String jsCode) {
        String url = HttpApiUrl.JSCODE_SESSION_API + "?appid=" + config.getAppId() + "&secret=" + config.getAppSecret() + "&js_code=" + jsCode + "&grant_type=authorization_code";
        Map<String, Object> map = null;
        String res = HttpUtil.get(url);
        LogUtils.logResult("获取sessionKey与opendId订单", res);
        try {
            map = JSONObject.parseObject(res);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }


    public static Map<String, Object> getAccessToken(WxConfig config) {
        String url = String.format(HttpApiUrl.PAY_ACCESS_TOKEN, config.getAppId(), config.getAppSecret());
        Map<String, Object> map = null;
        String res = HttpUtil.get(url);
        LogUtils.logResult("获取ACCESS_TOKEN", res);
        try {
            map = JSONObject.parseObject(res);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

    public static Map<String, Object> pushMsg(String accessToken, String touser, String templateId, String formId, List<TemplateMsg> value) {
        String url = String.format(HttpApiUrl.SEND_MSG, accessToken);

        Map<String, Object> data = new HashMap<>();
        data.put("touser", touser);
        data.put("template_id", templateId);
        data.put("form_id", formId);
        Map<String, Object> msgs = new HashMap<>();
        for (int i = 0; i < value.size(); i++) {
            TemplateMsg msg = value.get(i);
            msgs.put("keyword" + (i + 1), msg);
        }
        data.put("data", msgs);
        String json = new JSONObject(data).toJSONString();
        System.out.println(json);
        String res = HttpUtil.post(url, json);
        LogUtils.logResult("发送模板消息", res);
        Map<String, Object> map = null;
        try {
            map = JSONObject.parseObject(res);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }


}

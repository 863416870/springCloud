package cc.young.system.api;

import cc.yound.common.core.exception.FailedException;
import cc.young.system.model.TemplateMsg;
import cc.young.system.model.WxConfig;
import cc.young.system.util.WeixinApiUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public abstract class AbstractWxPay {

    public static final String APP = "APP";
    public static final String NATIVE = "NATIVE";
    public static final String JSAPI = "JSAPI";
    @Autowired
    protected WxConfig wxConfig;


    public AbstractWxPay() {
    }

    public AbstractWxPay(WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }

    public WxConfig getWxConfig() {
        return wxConfig;
    }

    public void setWxConfig(WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }


    /**
     * 小程序获取用户openid与sessionkey
     *
     * @param jsCode 来自小程序的jscode
     * @return 详情见https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html?t=20161122
     */
    public Map<String, Object> getOpendIdAndSessionKey(String jsCode)  {
        return WeixinApiUtils.getOpendIdAndSessionKey(wxConfig, jsCode);
    }

    /**
     * 获取access_token
     * @return accessToken与时间
     * @throws
     */
    public Map<String, Object> getAccessToken ()  {
        return WeixinApiUtils.getAccessToken(wxConfig);
    }



    /**
     *   touser	是	接收者（用户）的 openid
     *    template_id	是	所需下发的模板消息的id
     *    page	否	点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
     *    form_id	是	表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
     *    value	是	模板内容，不填则下发空模板
     *    color	否	模板内容字体的颜色，不填默认黑色
     *    emphasis_keyword	否	模板需要放大的关键词，不填则默认无放大
     * 发送模板消息
     * @return  推送返回结果
     * @throws
     */
    public Map<String, Object> pushMsg(String accessToken,String touser,String templateId,String formId,List<TemplateMsg> value) {
        return WeixinApiUtils.pushMsg(accessToken,touser,templateId,formId,value);
    }





    /**
     * 通过jscode赋值openid
     *
     * @param jsCode 小程序的openid
     * @return openId
     * @throws
     */
    public String getOpendIdByJsCode(String jsCode)  {
        Map<String, Object> map = getOpendIdAndSessionKey(jsCode);
        if (map != null) {
            return (String)map.get("openid");
        }
        throw new FailedException("获取openid 失败");
    }

}

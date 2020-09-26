package cc.young.pay.client.controller.wxpay;

import cc.young.pay.wx.WxPayApiConfig;


public abstract class AbstractWxPayApiController {
    /**
     * 获取微信支付配置
     *
     * @return {@link WxPayApiConfig} 微信支付配置
     */
    public abstract WxPayApiConfig getApiConfig();
}

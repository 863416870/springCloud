package cc.young.pay.client.controller.wxpay;

import cc.yound.common.core.util.R;
import cc.young.pay.client.config.WxPayConfig;
import cc.young.pay.core.enums.SignType;
import cc.young.pay.core.enums.TradeType;
import cc.young.pay.core.kit.HttpKit;
import cc.young.pay.core.kit.IpKit;
import cc.young.pay.core.kit.WxPayKit;
import cc.young.pay.wx.WxPayApi;
import cc.young.pay.wx.WxPayApiConfig;
import cc.young.pay.wx.WxPayApiConfigKit;
import cc.young.pay.wx.model.UnifiedOrderModel;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * description: WxPayController
 * date: 2020/9/23 12:42
 * author: faner
 */
@RestController
@RequestMapping("/wxPay")
@Api(tags = "WxPayController", description = "微信支付管理")
public class WxPayController extends AbstractWxPayApiController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WxPayConfig wxPayBean;

    private String notifyUrl;
    private String refundNotifyUrl;


    @Override
    public WxPayApiConfig getApiConfig() {
        WxPayApiConfig apiConfig = WxPayApiConfig.builder()
                .appId(wxPayBean.getAppId())
                .mchId(wxPayBean.getMchId())
                .partnerKey(wxPayBean.getPartnerKey())
                .certPath(wxPayBean.getCertPath())
                .domain(wxPayBean.getDomain())
                .build();
        notifyUrl = apiConfig.getDomain().concat("/wxPay/payNotify");
//		refundNotifyUrl = apiConfig.getDomain().concat("/wxPay/refundNotify");
        return apiConfig;
    }

    @RequestMapping(value = "index",method = {RequestMethod.GET})
    @ApiOperation("[支付]index")
    public String index() {
        log.info(wxPayBean.toString());
        return ("欢迎使用 微信支付 ");
    }

    @GetMapping("/test")
    public WxPayConfig test() {
        return wxPayBean;
    }

    @GetMapping("/getKey")
    public String getKey() {
        return WxPayApi.getSignKey(wxPayBean.getMchId(), wxPayBean.getPartnerKey(), SignType.MD5);
    }

    /**
     * 微信小程序支付
     */
    @RequestMapping(value = "/miniAppPay", method = {RequestMethod.POST})
    public R miniAppPay(HttpServletRequest request) {
            //需要通过授权来获取openId
    //        String openId = (String) request.getSession().getAttribute("openId");
            String openId = "oyHld5cPRH_KzdqbEp_wig0ErBqY";
            //通过不同类型调用不同服务
//            Integer type = CommonConstants.ORDER_MALL_BUY_TYPE;
//            //TODO 此处
//            String orderId = WxPayKit.generateStr();
//            Integer integer = UserOrderTypeServiceStrategyFactory.getOrderType(type).searchOrderInfo(orderId);

            String ip = IpKit.getRealIp(request);
            if (StrUtil.isBlank(ip)) {
                ip = "127.0.0.1";
            }
            WxPayApiConfig wxPayApiConfig = this.getApiConfig();
            // 统一下单构建请求参数
            Map<String, String> params = UnifiedOrderModel
                    .builder()
                    .appid(wxPayApiConfig.getAppId())
                    .mch_id(wxPayApiConfig.getMchId())
                    .nonce_str(WxPayKit.generateStr())
                    .body("让支付触手可及-小程序支付")
                    .attach("------------")
                    .out_trade_no(WxPayKit.generateStr())
                    .total_fee("1000")
                    .spbill_create_ip(ip)
                    .notify_url(notifyUrl)
                    .trade_type(TradeType.JSAPI.getTradeType())
                    .openid(openId)
                    .build()
                    // 同时支持 SignType.MD5、SignType.HMACSHA256
                    .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);
            // 发送请求
            String xmlResult = WxPayApi.pushOrder(false, params);

            log.info(xmlResult);
            // 将请求返回的 xml 数据转为 Map，方便后面逻辑获取数据
            Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
            // 判断返回的结果
            String returnCode = result.get("return_code");
            String returnMsg = result.get("return_msg");
            if (!WxPayKit.codeIsOk(returnCode)) {
                return R.failed(returnMsg);
            }
            String resultCode = result.get("result_code");
            if (!WxPayKit.codeIsOk(resultCode)) {
                return R.failed(returnMsg);
            }
            // 以下字段在 return_code 和 result_code 都为 SUCCESS 的时候有返回
            String prepayId = result.get("prepay_id");
            Map<String, String> packageParams = WxPayKit.miniAppPrepayIdCreateSign(wxPayApiConfig.getAppId(), prepayId,
                    wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);
            // 将二次签名构建的数据返回给前端并唤起公众号支付
            String jsonStr = JSON.toJSONString(packageParams);
            log.info("小程序支付的参数:" + jsonStr);
            return R.ok(jsonStr);
    }


    /**
     * 异步通知
     */
    @RequestMapping(value = "/payNotify", method = {RequestMethod.POST, RequestMethod.GET})
    public String payNotify(HttpServletRequest request) {
        String xmlMsg = HttpKit.readData(request);
        log.info("支付通知=" + xmlMsg);
        System.out.println("支付通知=" + xmlMsg);
        Map<String, String> params = WxPayKit.xmlToMap(xmlMsg);

        String returnCode = params.get("return_code");

        // 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
        // 注意此处签名方式需与统一下单的签名类型一致
        if (WxPayKit.verifyNotify(params, WxPayApiConfigKit.getWxPayApiConfig().getPartnerKey(), SignType.HMACSHA256)) {
            if (WxPayKit.codeIsOk(returnCode)) {
                // 更新订单信息
                // 发送通知等
                Map<String, String> xml = new HashMap<String, String>(2);
                xml.put("return_code", "SUCCESS");
                xml.put("return_msg", "OK");
                return WxPayKit.toXml(xml);
            }
        }
        return null;
    }
}

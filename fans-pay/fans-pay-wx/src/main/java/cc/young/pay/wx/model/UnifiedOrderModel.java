
package cc.young.pay.wx.model;

import cc.young.pay.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 统一下单类
 * 文档地址 参数自己补全
 * @link {https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=9_1}
 * @author faner
 */
@Builder
@AllArgsConstructor
@Getter
@Setter
@ApiModel(value = "统一下单类")
public class UnifiedOrderModel extends BaseModel {
    @ApiModelProperty(value = "小程序id")
    private String appid;
    @ApiModelProperty(value = "商户id")
    private String mch_id;
    private String sub_appid;
    private String sub_mch_id;
    @ApiModelProperty(value = "设备号")
    private String device_info;
    @ApiModelProperty(value = "随机字符串",required = true)
    private String nonce_str;
    @ApiModelProperty(value = "签名",required = true)
    private String sign;
    @ApiModelProperty(value = "签名类型，默认MD5",required = true)
    private String sign_type;
    @ApiModelProperty(value = "商品描述",required = true)
    private String body;
    @ApiModelProperty(value = "商品详情")
    private String detail;
    @ApiModelProperty(value = "附加数据")
    private String attach;
    @ApiModelProperty(value = "商户订单号",required = true)
    private String out_trade_no;
    @ApiModelProperty(value = "标价币种")
    private String fee_type;
    @ApiModelProperty(value = "标价金额",required = true)
    private String total_fee;
    @ApiModelProperty(value = "终端IP",required = true)
    private String spbill_create_ip;
    @ApiModelProperty(value = "交易起始时间")
    private String time_start;
    @ApiModelProperty(value = "交易结束时间")
    private String time_expire;
    @ApiModelProperty(value = "订单优惠标记")
    private String goods_tag;
    @ApiModelProperty(value = "通知地址",required = true)
    private String notify_url;
    @ApiModelProperty(value = "交易类型",required = true)
    private String trade_type;
    @ApiModelProperty(value = "商品ID")
    private String product_id;
    @ApiModelProperty(value = "指定支付方式")
    private String limit_pay;
    @ApiModelProperty(value = "用户标识",required = true,notes = "trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识")
    private String openid;
    private String sub_openid;
    @ApiModelProperty(value = "电子发票入口开放标识")
    private String receipt;
    private String scene_info;
    private String profit_sharing;
}

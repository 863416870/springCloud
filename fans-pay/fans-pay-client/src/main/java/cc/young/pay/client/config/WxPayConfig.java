package cc.young.pay.client.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * description: WxPayConfig
 * date: 2020/9/23 12:59
 * author: faner
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WxPayConfig {
    @Value("${wxpay.appId}")
    private String appId;
    @Value("${wxpay.appSecret}")
    private String appSecret;
    @Value("${wxpay.mchId}")
    private String mchId;
    @Value("${wxpay.partnerKey}")
    private String partnerKey;
    @Value("${wxpay.certPath}")
    private String certPath;
    @Value("${wxpay.domain}")
    private String domain;


}

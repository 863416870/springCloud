package cc.young.system.service;

import cc.young.system.api.AccountPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * description: WxService
 * date: 2020/9/29 16:26
 * author: faner
 */
@Service
public class WxService {
    @Autowired
    AccountPay accountPay;

    public Map<String, Object> getOpenId(String code) {

        return accountPay.getOpendIdAndSessionKey(code);

    }

}

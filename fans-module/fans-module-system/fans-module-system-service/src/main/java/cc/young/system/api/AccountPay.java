package cc.young.system.api;

import cc.young.system.model.WxConfig;
import org.springframework.stereotype.Service;

@Service
public class AccountPay extends AbstractWxPay {


    public AccountPay() {
    }

    public AccountPay(WxConfig wxConfig) {
        super(wxConfig);
    }


}

package org.ares.app.wxtck.sys.wx.pay.cfg;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "wechat")
@Component
public class PayAccount {

    private String mpAppId;//公众账号ID
    private String mchId;//商户号
    private String mchKey;//商户密钥
    private String keyPath;//商户证书路径
    private String payBackCallUrl;//微信支付异步通知地址
}

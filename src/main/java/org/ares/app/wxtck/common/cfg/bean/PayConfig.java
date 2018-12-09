package org.ares.app.wxtck.common.cfg.bean;

import javax.annotation.Resource;

import org.ares.app.wxtck.sys.wx.pay.cfg.PayAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;

@Configuration
public class PayConfig {

    @Bean
    public WxPayH5Config wxPayH5Config() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(accountConfig.getMpAppId());
        wxPayH5Config.setMchId(accountConfig.getMchId());
        wxPayH5Config.setMchKey(accountConfig.getMchKey());
        wxPayH5Config.setKeyPath(accountConfig.getKeyPath());
        wxPayH5Config.setNotifyUrl(accountConfig.getPayBackCallUrl());
        return wxPayH5Config;
    }

    @Bean
    public BestPayService/*Impl*/ bestPayService(WxPayH5Config wxPayH5Config) {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config);
        return bestPayService;
    }
    
    @Resource private PayAccount accountConfig;
    
}

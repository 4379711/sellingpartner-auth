package com.honbow.beans;

import com.honbow.GetTokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyalong
 */
@Configuration
public class AppBeanFactory {
    @Autowired
    private AppBeans appBeans;

    @Bean
    public Map<String, GetTokens> getApp() {

        List<App> appList = appBeans.getAppList();
        HashMap<String, GetTokens> hashMap = new HashMap<>(10);
        appList.forEach(i -> hashMap.put(i.getAppName(), new GetTokens(i.getClientId(), i.getClientSecret(), i.getAppId())));
        return hashMap;
    }
}

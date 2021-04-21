package com.honbow.beans;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liuyalong
 */
@Component
@ConfigurationProperties(prefix = "apps")
@Data
public class AppBeans {

    private List<App> appList;

}


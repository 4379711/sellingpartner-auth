package com.honbow.beans;

import lombok.Data;
import lombok.ToString;

/**
 * @author liuyalong
 */
@Data
@ToString
public class App {
    /**
     * app 的名字
     */
    private String appName;
    /**
     * 申请app后LWA中的clientId
     */
    private String clientId;
    /**
     * 申请app后LWA中的clientSecret
     */
    private String clientSecret;
    /**
     * app 的ID
     */
    private String appId;
}

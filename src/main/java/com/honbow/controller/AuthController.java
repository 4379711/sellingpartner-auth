package com.honbow.controller;

import com.honbow.GetTokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author liuyalong
 */
@CrossOrigin
@RestController
public class AuthController {
    @Autowired
    private Map<String, GetTokens> getApp;

    @GetMapping(value = "{appName}/getcode/{region}")
    public String getCodeUrl(
            @RequestParam(value = "beta", required = false) String betaValue,
            @PathVariable(value = "region") String region,
            @PathVariable(value = "appName") String appName
    ) {
        boolean beta = false;
        if (null != betaValue) {
            beta = true;
        }
        GetTokens getTokens = getApp.get(appName);
        if (getTokens == null) {
            return "App name [" + appName + "] can not be found !";
        }
        return getTokens.getCodeUrl(beta, region);
    }

    @RequestMapping(value = "{appName}/get_refresh_token")
    public String getRefreshToken(HttpServletRequest request, @PathVariable(value = "appName") String appName) {

        GetTokens getTokens = getApp.get(appName);
        if (getTokens == null) {
            return "App name [" + appName + "] can not be found !";
        }

        String code = request.getParameter("spapi_oauth_code");
        String queryString = request.getQueryString();
        if (code == null) {
            return "Can not get spapi_oauth_code ! redirectURI is :" + queryString;
        }

        return getTokens.getRefreshTokenByCode(code);
    }

}

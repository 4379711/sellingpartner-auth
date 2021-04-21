package com.honbow;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import java.util.UUID;

/**
 * @author liuyalong
 */
public class GetTokens {

    public static final String LWA_END_POINT = "https://api.amazon.com/auth/o2/token";
    public static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    /**
     * 申请app后LWA中的clientId
     */
    private final String clientId;
    /**
     * 申请app后LWA中的clientSecret
     */
    private final String clientSecret;

    /**
     * app 的ID
     */
    private final String applicationId;

    public GetTokens(String clientId, String clientSecret, String applicationId) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.applicationId = applicationId;
    }


    public String getCodeUrl(boolean beta, String region) {
        // https://sellercentral.amazon.com/apps/authorize/consent?application_id=appidexample&state=stateexample&version=beta
        String codeEndPoint = null;

        switch (region) {
            case "na":
                // 北美地区
                codeEndPoint = "https://sellercentral.amazon.com/apps/authorize/consent";
                break;
            case "eu":
                // 欧洲地区
                codeEndPoint = "https://sellercentral-europe.amazon.com/apps/authorize/consent";
                break;
            case "jp":
                // 日本
                codeEndPoint = "https://sellercentral-japan.amazon.com/apps/authorize/consent";
                break;
            case "au":
                // 澳大利亚
                codeEndPoint = "https://sellercentral.amazon.com.au/apps/authorize/consent";
                break;
            case "sg":
                // 新加坡
                codeEndPoint = "https://sellercentral.amazon.sg/apps/authorize/consent";
                break;
            default:
                return "The region must be one of the following region ,[na,eu,jp,au,sg]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(codeEndPoint);
        stringBuilder.append("?application_id=");
        stringBuilder.append(applicationId);
        stringBuilder.append("&state=");
        stringBuilder.append(UUID.randomUUID().toString());
        if (beta) {
            stringBuilder.append("&version=beta");
        }
        return stringBuilder.toString();
    }

    /**
     * @param code 授权以后得到的授权码
     * @return 返回授权结果
     */
    public String getRefreshTokenByCode(String code) {

        LWARefreshTokenRequestMeta lwaRefreshTokenRequestMeta = LWARefreshTokenRequestMeta.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .code(code)
                .build();
        RequestBody requestBody = RequestBody.create(JSON_MEDIA_TYPE, new Gson().toJson(lwaRefreshTokenRequestMeta));
        Request refreshTokenRequest = new Request.Builder().url(LWA_END_POINT).post(requestBody).build();
        String result;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Response response = okHttpClient.newCall(refreshTokenRequest).execute();
            result = response.body().string();
        } catch (Exception e) {
            result = e.toString();
        }
        return result;
    }
}

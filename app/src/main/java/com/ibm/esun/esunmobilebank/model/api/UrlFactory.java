package com.ibm.esun.esunmobilebank.model.api;

public class UrlFactory {

    private final String HOST_NAME = "http://180.176.8.137:3000";

    private static UrlFactory sInstance;

    public enum Target {

        GetGoldRate,       // 黃金牌價
        GetRate,            // 台幣存款利率
        GetBusinessPhone        // 玉山e客服

    }

    public static String getUrl(final Target target) {
        if (sInstance == null) {
            sInstance = new UrlFactory();
        }
        return sInstance.getTargetUrl(target);
    }

    public String getTargetUrl(Target target) {
        String url = "";
        switch (target) {
            case GetGoldRate:
                url = HOST_NAME + "/api/GetGlodRate";
                break;

            case GetRate:
                url = HOST_NAME + "/api/GetRate";
                break;

            case GetBusinessPhone:
                url = HOST_NAME + "/api/GetBusinessPhone";
                break;
        }
        return url;
    }
}

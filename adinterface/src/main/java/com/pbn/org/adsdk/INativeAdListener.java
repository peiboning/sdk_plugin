package com.pbn.org.adsdk;

/**
 * @author peiboning
 * @DATE 2019/03/06
 */
public interface INativeAdListener {
    void onSuccess(INativeAd ad);
    void onFailed();
}

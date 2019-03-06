package com.pbn.org.adsdk;

/**
 * @author peiboning
 * @DATE 2019/03/06
 */
public interface IAdLoader {
     void loadNative(String adid, INativeAdListener listener);
     String getPluginVersion();
}

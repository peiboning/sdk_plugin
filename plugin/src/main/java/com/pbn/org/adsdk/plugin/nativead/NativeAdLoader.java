package com.pbn.org.adsdk.plugin.nativead;

import com.pbn.org.adsdk.INativeAd;
import com.pbn.org.adsdk.INativeAdListener;
import com.pbn.org.adsdk.plugin.PluginLog;

/**
 * function:
 * native广告
 * @author peiboning
 * @DATE 2019/03/06
 */
public class NativeAdLoader{
    public void loadNative(String adid, INativeAdListener listener) {
        PluginLog.i("NativeAdLoader", "load success");
        if(listener != null){
            listener.onSuccess(new INativeAd() {
                @Override
                public String getTitle() {
                    return "plugin_title";
                }

                @Override
                public String getDesc() {
                    return "plugin_desc";
                }
            });
        }
    }
}

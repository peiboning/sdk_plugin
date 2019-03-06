package com.pbn.org.adsdk.plugin;

import com.pbn.org.adsdk.IAdLoader;
import com.pbn.org.adsdk.INativeAdListener;
import com.pbn.org.adsdk.plugin.nativead.NativeAdLoader;

/**
 * function:
 * 插件和宿主的桥接文件
 * @author peiboning
 * @DATE 2019/03/06
 */
public class AdLoader implements IAdLoader{
    @Override
    public void loadNative(String adid, INativeAdListener listener) {
        new NativeAdLoader().loadNative(adid, listener);
    }

    @Override
    public String getPluginVersion() {
        return "1.0.1";
    }
}

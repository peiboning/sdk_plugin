package com.pbn.org.adsdk.library;

import android.util.Log;

import com.pbn.org.adsdk.IAdLoader;
import com.pbn.org.adsdk.INativeAd;
import com.pbn.org.adsdk.INativeAdListener;
import com.pbn.org.adsdk.library.pluginloader.PluginManager;

/**
 * @author peiboning
 * @DATE 2019/03/06
 */
public class NativeAdLoader {
    private IAdLoader reallyLoader;
    public interface INativeAdLoaderListener {
        void onSuccess(INativeAd ad);
        void onFailed();
    }

    public NativeAdLoader(){
        reallyLoader = PluginManager.getInstance().getLoader();
    }

    public void load(String id, final INativeAdLoaderListener loaderListener){
        if(null != reallyLoader){
            reallyLoader.loadNative(id, new INativeAdListener() {
                @Override
                public void onSuccess(INativeAd ad) {
                    if(null != loaderListener){
                        loaderListener.onSuccess(ad);
                    }
                }

                @Override
                public void onFailed() {
                    if(null != loaderListener){
                        loaderListener.onFailed();
                    }
                }
            });
        }else{
            Log.e("NativeAdLoader", "init native ad error");
        }
    }

}

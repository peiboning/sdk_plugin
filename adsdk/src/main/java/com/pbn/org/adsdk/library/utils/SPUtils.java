package com.pbn.org.adsdk.library.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.pbn.org.adsdk.library.AdSDK;

/**
 * function:
 *
 * @author peiboning
 * @DATE 2019/03/06
 */
public class SPUtils {
    private static SPUtils sInstance = null;

    private SharedPreferences mPluginSP;

    public static SPUtils getInstance() {
        if (null == sInstance) {
            synchronized (SPUtils.class) {
                if (null == sInstance) {
                    sInstance = new SPUtils();
                }
            }
        }
        return sInstance;
    }

    private SPUtils() {
        mPluginSP = AdSDK.getContext().getSharedPreferences("plugin_sp", Context.MODE_PRIVATE);
    }

    public String getPluginVersion(){
        return mPluginSP.getString("plugin_v", "");
    }
    public void setPluginVersion(String version){
        SharedPreferences.Editor e = mPluginSP.edit();
        e.putString("plugin_v", version);
        e.commit();
    }

}
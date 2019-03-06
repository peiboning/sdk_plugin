package com.pbn.org.adsdk.library;

import android.content.Context;

import com.pbn.org.adsdk.library.pluginloader.PluginManager;

/**
 * @author peiboning
 * @DATE 2019/03/06
 */
public class AdSDK {
    private static Context sContext;
    private static boolean sInit;

    public static interface AdInitListener{
        void onInitOver();
        void onInitFailed();
    }

    public static void init(Context context, final AdInitListener listener){
        if(!sInit){
            sContext = context;
            PluginManager.getInstance().loadPlugin(new AdInitListener() {
                @Override
                public void onInitOver() {
                    PluginManager.getInstance().beginCheckPluginVersion();
                    if(null != listener){
                        listener.onInitOver();
                    }
                }

                @Override
                public void onInitFailed() {
                    if(null != listener){
                        listener.onInitOver();
                    }
                }
            });
            sInit = true;
        }
    }

    public static Context getContext(){
        return sContext;
    }
}

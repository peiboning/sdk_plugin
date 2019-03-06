package com.pbn.org.adsdk;

import android.app.Application;
import android.util.Log;

import com.pbn.org.adsdk.library.AdSDK;

/**
 * function:
 *
 * @author peiboning
 * @DATE 2019/03/06
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        AdSDK.init(this, new AdSDK.AdInitListener() {
            @Override
            public void onInitOver() {
                Log.e("MyApplication", "init over");
            }

            @Override
            public void onInitFailed() {
                Log.e("MyApplication", "init failed");
            }
        });
    }
}

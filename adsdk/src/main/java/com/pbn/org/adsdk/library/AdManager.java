package com.pbn.org.adsdk.library;

import com.pbn.org.adsdk.IAdLoader;

/**
 * @author peiboning
 * @DATE 2019/03/06
 */
public class AdManager {
    private static AdManager sInstance = null;

    public static AdManager getInstance() {
        if (null == sInstance) {
            synchronized (AdManager.class) {
                if (null == sInstance) {
                    sInstance = new AdManager();
                }
            }
        }
        return sInstance;
    }

    private AdManager() {
    }



}
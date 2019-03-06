package com.pbn.org.adsdk.library.pluginloader;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;

import com.pbn.org.adsdk.IAdLoader;
import com.pbn.org.adsdk.library.AdSDK;
import com.pbn.org.adsdk.library.utils.SPUtils;
import com.pbn.org.adsdk.library.utils.ThreadHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author peiboning
 * @DATE 2019/03/06
 */
public class PluginManager {
    private static Map<Class, String> map = new HashMap<>();
    static {
        map.put(IAdLoader.class, "com.pbn.org.adsdk.plugin.AdLoader");
    }
    private static PluginManager sInstance = null;
    private AdClassLoader mClassLoader;
    private String pluginPath = "";
    private IAdLoader loader;

    public static PluginManager getInstance() {
        if (null == sInstance) {
            synchronized (PluginManager.class) {
                if (null == sInstance) {
                    sInstance = new PluginManager();
                }
            }
        }
        return sInstance;
    }

    private PluginManager() {
        pluginPath = AdSDK.getContext().getExternalCacheDir().getAbsolutePath()+File.separator+"plugin";
    }

    public void loadPlugin(final AdSDK.AdInitListener listener) {
        final String optimizePath = AdSDK.getContext().getDir("ad_plugin", Context.MODE_PRIVATE).getAbsolutePath();
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                String path = pluginPath;
                File file = new File(path);
                boolean result = true;
                if (!file.exists()) {
                    try {
                        InputStream in = AdSDK.getContext().getAssets().open("plugin.jar");
                        OutputStream out = new FileOutputStream(file);
                        int len = -1;
                        byte[] buf = new byte[1024];
                        while ((len = in.read(buf)) != -1) {
                            out.write(buf, 0, len);
                        }
                        out.close();
                        in.close();
                    } catch (IOException e) {
                        result = false;
                    }
                }

                if (result) {
                    mClassLoader = new AdClassLoader(pluginPath, optimizePath, null, AdSDK.getContext().getClassLoader());
                }

                return result;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (null != listener) {
                    if (aBoolean) {
                        listener.onInitOver();
                    } else {
                        listener.onInitFailed();
                    }
                }
            }
        }.execute();
    }

    public IAdLoader getLoader() {
        if(null == loader){
            loader = createLoader(IAdLoader.class);
        }
        return loader;
    }

    private  <T> T createLoader(Class<T> cls){
        String className = map.get(cls);
        if(!TextUtils.isEmpty(className)){
            try {
                Class clsImpl = mClassLoader.loadClass(className);
                return cls.cast(clsImpl.newInstance());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void beginCheckPluginVersion(){
        ThreadHelper.post(new Runnable() {
            @Override
            public void run() {
                String v = SPUtils.getInstance().getPluginVersion();
                IAdLoader adLoader = getLoader();
                if(null != adLoader){
                    String localV = adLoader.getPluginVersion();
                    if(!TextUtils.isEmpty(localV) && !localV.equals(v)){
                        SPUtils.getInstance().setPluginVersion(localV);
                    }

                    //去云端拉取是否有新的SDK。获取到file的MD5，size等信息
                    //如果有更新，就下载新的jar。等到下一次启动时就会加载新的sdk

                }

            }
        });
    }


}
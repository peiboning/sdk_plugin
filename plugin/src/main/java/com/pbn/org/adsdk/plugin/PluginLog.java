package com.pbn.org.adsdk.plugin;

import android.text.TextUtils;
import android.util.Log;

/**
 * function:
 * 日志输出
 * @author peiboning
 * @DATE 2019/03/06
 */
public class PluginLog {

    private static final String PRIFIX = "ad_plugin:";
    public static boolean DEBUG = true;

    public static void e(String tag, String msg){
        if(DEBUG){
            Log.e(getThreadName()+","+PRIFIX+tag, msg);
        }
    }
    public static void i(String tag, String msg){
        if(DEBUG){
            Log.i(getThreadName()+","+PRIFIX+tag, msg);
        }
    }
    public static void w(String tag, String msg){
        if(DEBUG){
            Log.w(getThreadName()+","+PRIFIX+tag, msg);
        }
    }
    public static void v(String tag, String msg){
        if(DEBUG){
            Log.v(getThreadName()+","+PRIFIX+tag, msg);
        }
    }
    public static void d(String tag, String msg){
        if(DEBUG){
            Log.d(getThreadName()+","+PRIFIX+tag, msg);
        }
    }

    public static void ex(Throwable t){
        if(DEBUG){
            String e = Log.getStackTraceString(t);
            if(!TextUtils.isEmpty(e)){
                Log.e(getThreadName()+","+PRIFIX, e);
            }
        }
    }

    private static String getThreadName(){
        return Thread.currentThread().getName();
    }


}

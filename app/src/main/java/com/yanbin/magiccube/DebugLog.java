package com.yanbin.magiccube;

import android.util.Log;

/**
 * Created by 彥彬 on 2015/4/5.
 */
public class DebugLog {

    private static boolean debugOn = true;

    public static void print(Object logObject, String log){
        if(debugOn)
            Log.d(logObject.getClass().getSimpleName(), log);
    }

    public static boolean isDebugOn(){
        return debugOn;
    }
}

package com.wgw.flux;

import android.util.Log;


import java.util.Arrays;

/**
 * Flux日志工具
 *
 * @author Created by 汪高皖 on 2019/2/21 0021 14:00
 */
class FluxLogger {
    private static final String TAG = "RxFlux";
    private static boolean logEnabled = BuildConfig.DEBUG;
    
    /**
     * 打印Store注册消息
     */
    static void logRegisterStore(String storeName, String... actionType) {
        if (logEnabled) {
            if (actionType == null || actionType.length == 0) {
                Log.d(TAG, String.format("Store %s has registered all action", storeName));
            } else {
                StringBuilder builder = new StringBuilder("Store %s has registered action : %s");
                int length = actionType.length;
                for (int i = 1; i < length; i++) {
                    builder.append(",").append("%s");
                }
                Log.d(TAG, String.format(builder.toString(), storeName, Arrays.toString(actionType)));
            }
        }
    }
    
    /**
     * 打印Store取消注册的消息
     */
    static void logUnregisterStore(String storeName) {
        if (logEnabled) {
            Log.d(TAG, String.format("Store %s has unregistered", storeName));
        }
    }
    
    /**
     * 打印Action分发消息
     */
    static void logPostAction(Action action) {
        if (logEnabled) {
            Log.d(TAG, String.format("Dispatcher post action : %s", action.toString()));
        }
    }
    
    /**
     * 打印异常消息
     */
    static void logHandleException(String storeName, Throwable e) {
        if (logEnabled) {
            Log.e(TAG, String.format("Store %s handle action throws Exceptiop", storeName), e);
        }
    }
}

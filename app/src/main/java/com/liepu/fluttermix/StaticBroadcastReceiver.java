package com.liepu.fluttermix;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Trace;
import android.util.Log;


/**
 * @Author: liepu
 * @CreateDate: 2021/5/17 5:55 PM.
 * @Description: 描述具体内容
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/17 5:55 PM.
 * @UpdateRemark: 更新说明
 */
public class StaticBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        Trace.beginSection(method);
        Log.d("111", "static");
        Trace.endSection();
    }
}

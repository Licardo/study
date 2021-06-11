package com.liepu.fluttermix

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 *
 *
 * @Author: liepu
 * @CreateDate: 2021/6/4 6:01 PM.
 * @Description:  描述具体内容
 * @UpdateUser:  更新者
 * @UpdateDate: 2021/6/4 6:01 PM.
 * @UpdateRemark:  更新说明
 */
class RecyclerService : Service() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        print("")
        Log.e("", "onBind")
        return MyBinder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    inner class MyBinder : Binder() {
        fun getService(): RecyclerService {
            return this@RecyclerService
        }
    }

    fun getString(): String {
        return "service is bind service"
    }

}
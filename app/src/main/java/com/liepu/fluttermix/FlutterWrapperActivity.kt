package com.liepu.fluttermix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class FlutterWrapperActivity : FlutterActivity() {

    lateinit var methodChannel: MethodChannel

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "orange")
        methodChannel.setMethodCallHandler { call, result ->
                    if (call.method == "lanhai") {
                        val name = "this is my first method channel"
                        result.success(name)
                    } else {
                        result.error("-1","i have not this method", null)
                        result.notImplemented()
                    }
                }
    }

    override fun onResume() {
        super.onResume()
        methodChannel.invokeMethod("flu", "i am java", object : MethodChannel.Result{
            override fun success(result: Any?) {
                Log.e("wrapper",">>> success")
                Log.e("wrapper",result.toString())
            }

            override fun error(errorCode: String?, errorMessage: String?, errorDetails: Any?) {
                Log.e("wrapper",">>> $errorMessage")
            }

            override fun notImplemented() {
                Log.e("wrapper","notImplemented")
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("wrapper", ">>> onCreate")

    }

}
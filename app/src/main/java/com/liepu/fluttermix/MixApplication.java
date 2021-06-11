package com.liepu.fluttermix;

import android.app.Application;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

/**
 * @Author: liepu
 * @CreateDate: 2021/5/13 2:08 PM.
 * @Description: 描述具体内容
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/13 2:08 PM.
 * @UpdateRemark: 更新说明
 */
public class MixApplication extends Application {
    public FlutterEngine flutterEngine;
    public static final String ENGINE_ID = "little_dog";

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化引擎
        flutterEngine = new FlutterEngine(this);
        // 设置初始路由
        flutterEngine.getNavigationChannel().setInitialRoute("login");
        // 执行dart代码，预热引擎
        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );
        // 缓存引擎
        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
                .getInstance()
                .put(ENGINE_ID, flutterEngine);


//        LayoutInflater.from(this).inflate()
    }
}

package com.example.flutterrouter

import android.app.Application
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoost.BoostLifecycleListener
import com.idlefish.flutterboost.Utils
import com.idlefish.flutterboost.interfaces.INativeRouter
import io.flutter.embedding.android.FlutterView

/**
 * Description :
 * Created by yue on 2020/4/14
 */
class RouterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //此处必须启动初始化，主要是载入Flutter引擎文件
        FlutterBootsManager.init(this)
    }
}
package com.example.flutterrouter

import android.app.Application
import android.content.Context
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.Utils
import com.idlefish.flutterboost.interfaces.INativeRouter
import io.flutter.embedding.android.FlutterView

/**
 * Description :
 * Created by yue on 2020/4/14
 */
object FlutterBootsManager {

    @JvmStatic
    fun init(context: Application) {
        //此处必须启动初始化，主要是载入Flutter引擎文件
        val router = INativeRouter { context, url, urlParams, requestCode, exts ->
            val assembleUrl = Utils.assembleUrl(url, urlParams)
            PageRouter.openPageByUrl(context, url, urlParams)
        }
        val boostLifecycleListener: FlutterBoost.BoostLifecycleListener = object : FlutterBoost.BoostLifecycleListener {
            override fun beforeCreateEngine() {}
            override fun onEngineCreated() {}
            override fun onPluginsRegistered() {}
            override fun onEngineDestroy() {}
        }

        //
        // AndroidManifest.xml 中必须要添加 flutterEmbedding 版本设置
        //
        //   <meta-data android:name="flutterEmbedding"
        //               android:value="2">
        //    </meta-data>
        // GeneratedPluginRegistrant 会自动生成 新的插件方式　
        //
        // 插件注册方式请使用
        // FlutterBoost.instance().engineProvider().getPlugins().add(new FlutterPlugin());
        // GeneratedPluginRegistrant.registerWith()，是在engine 创建后马上执行，放射形式调用
        //

        //
// AndroidManifest.xml 中必须要添加 flutterEmbedding 版本设置
//
//   <meta-data android:name="flutterEmbedding"
//               android:value="2">
//    </meta-data>
// GeneratedPluginRegistrant 会自动生成 新的插件方式　
//
// 插件注册方式请使用
// FlutterBoost.instance().engineProvider().getPlugins().add(new FlutterPlugin());
// GeneratedPluginRegistrant.registerWith()，是在engine 创建后马上执行，放射形式调用
//
        val platform = FlutterBoost.ConfigBuilder(context, router)
                .isDebug(true)
                .whenEngineStart(FlutterBoost.ConfigBuilder.ANY_ACTIVITY_CREATED)
                .renderMode(FlutterView.RenderMode.texture)
                .lifecycleListener(boostLifecycleListener)
                .build()
        FlutterBoost.instance().init(platform)
    }

}
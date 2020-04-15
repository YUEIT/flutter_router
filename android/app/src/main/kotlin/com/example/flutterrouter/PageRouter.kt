package com.example.flutterrouter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.flutterrouter.MainActivity
import com.idlefish.flutterboost.containers.BoostFlutterActivity

/**
 * Description :
 * Created by yue on 2020/4/14
 */
object PageRouter {

    fun openPageByUrl(context: Context, url: String): Boolean {
        return openPageByUrl(context, url, HashMap<Any, Any>())
    }

    fun openPageByUrl(context: Context, url: String, params: Map<*, *>?): Boolean {
        return openPageByUrl(context, url, params, 0)
    }

    fun openPageByUrl(context: Context, url: String, params: Map<*, *>?, requestCode: Int): Boolean {
        try {
            if (url.startsWith(IRouter.FLUTTER)) {
                val intent = BoostFlutterActivity.withNewEngine().url(url).params(params!!)
                        .backgroundMode(BoostFlutterActivity.BackgroundMode.opaque).build(context)
                if (context is Activity) {
                    context.startActivityForResult(intent, requestCode)
                } else {
                    context.startActivity(intent)
                }
                return true
            } else if (url.startsWith(IRouter.NATIVE)) {
                context.startActivity(Intent(context, MainActivity::class.java))
                return true
            }
            return false
        } catch (t: Throwable) {
            t.printStackTrace()
            return false
        }
    }
}
package com.example.flutterrouter

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        findViewById<TextView>(R.id.jump).setOnClickListener {
            val map = HashMap<String, String>()
            map.put("test1", "param1")
            map.put("test2", "param2")
            PageRouter.openPageByUrl(MainActivity@this, IRouter.Flutter.SECOND_PAGE, map)
        }
    }
}

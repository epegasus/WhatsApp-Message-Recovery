package dev.pegasus.whatsapp.message.recovery

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dev.pegasus.whatsapp.message.recovery.di.DIManual

/**
 * Created by: Sohaib Ahmed
 * Date: 7/17/2025
 * <p>
 * Links:
 * - LinkedIn: <a href="https://linkedin.com/in/epegasus">Linkedin</a>
 * - GitHub: <a href="https://github.com/epegasus">Github</a>
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        val diManual by lazy { DIManual(context) }
    }
}
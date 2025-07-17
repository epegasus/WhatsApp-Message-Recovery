package dev.pegasus.whatsapp.message.recovery.utils

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

/**
 * Created by: Sohaib Ahmed
 * Date: 7/17/2025
 * <p>
 * Links:
 * - LinkedIn: <a href="https://linkedin.com/in/epegasus">Linkedin</a>
 * - GitHub: <a href="https://github.com/epegasus">Github</a>
 */

object BitmapUtils {

    fun Bitmap.toBase64(): String {
        val stream = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.PNG, 100, stream)
        val bytes = stream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }
}
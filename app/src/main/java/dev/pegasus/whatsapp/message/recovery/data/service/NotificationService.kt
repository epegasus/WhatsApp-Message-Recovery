package dev.pegasus.whatsapp.message.recovery.data.service

import android.app.Notification
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Parcelable
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.core.graphics.drawable.toBitmapOrNull
import dev.pegasus.whatsapp.message.recovery.App
import dev.pegasus.whatsapp.message.recovery.data.entities.ItemDbMessage
import dev.pegasus.whatsapp.message.recovery.utils.Constants.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

/**
 * Created by: Sohaib Ahmed
 * Date: 7/17/2025
 * <p>
 * Links:
 * - LinkedIn: <a href="https://linkedin.com/in/epegasus">Linkedin</a>
 * - GitHub: <a href="https://github.com/epegasus">Github</a>
 */

class NotificationService : NotificationListenerService() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val useCase by lazy { App.diManual.useCase }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "NotificationService: onStartCommand: Service Started")
        return START_STICKY
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        Log.d(TAG, "NotificationService: onNotificationPosted: :$sbn")

        sbn?.notification?.let {
            if (listOf("com.whatsapp", "com.whatsapp.w4b").contains(sbn.packageName)) {
                recordMessage(sbn)
            }
        }
    }

    private fun recordMessage(sbn: StatusBarNotification) = serviceScope.launch {
        val extras = sbn.notification.extras

        val sender = extras.getString(Notification.EXTRA_TITLE) ?: return@launch
        val message = extras.getCharSequence(Notification.EXTRA_TEXT)?.toString() ?: return@launch
        val icon = extras.getParcelable<Parcelable>(Notification.EXTRA_LARGE_ICON)
        val time = sbn.notification.`when`

        val largeBitmap: Bitmap? = when (icon) {
            is Bitmap -> icon
            is Icon -> icon.loadDrawable(this@NotificationService)?.toBitmapOrNull()
            else -> null
        }
        val byteArray = bitmapToByteArray(bitmap = largeBitmap)

        if (checkIfContentValid(sender, message)) {
            insertEntry(sbn.packageName, sender, message, byteArray, time)
        }

        if (message == "This message was deleted") {
            //notifyDeletedMessage()
        }
    }

    private fun bitmapToByteArray(bitmap: Bitmap?): ByteArray? {
        if (bitmap == null) return null
        val stream = ByteArrayOutputStream()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            bitmap.compress(Bitmap.CompressFormat.WEBP_LOSSY, 60, stream)
        } else {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream)
        }
        return stream.toByteArray()
    }

    private suspend fun insertEntry(packageName: String, sender: String, message: String, iconBytes: ByteArray?, timeStamp: Long) {
        val itemDbMessage = ItemDbMessage(
            packageName = packageName,
            user = sender,
            message = message,
            iconBytes = iconBytes,
            time = timeStamp,
            isGroup = false,
        )
        Log.d(TAG, "insertEntry: $itemDbMessage")
        useCase.insert(itemDbMessage)
    }

    private fun checkIfContentValid(sender: String, message: String): Boolean {
        val blockedSenders = setOf(
            "", "WhatsApp", "WhatsApp Business", "DiscussBot",
            "Checking for messages…", "Signal", "Calling…", "Ringing…",
            "Deleting messages…", "Ongoing voice call", "WhatsApp Web",
            "Finished backup", "Backup in progress", "Backup paused",
            "Restoring media", "Checking for new messages",
            "WhatsApp Web is currently active", "Tap for more info",
            "Waiting for Wi-Fi", "This message was deleted", "This message was deleted.",
            "Couldn't complete backup", "Number not verified",
            "Logged out of WhatsApp", "Logged out of WhatsApp Business", "WA Business"
        )
        val invalidTextPhrases = listOf("new messages", "Incoming voice call", "Incoming video call")

        val isSenderValid = sender.trim() !in blockedSenders
        val isMessageValid = message.isNotBlank() && invalidTextPhrases.none { phrase -> message.contains(phrase, ignoreCase = false) }

        return isSenderValid && isMessageValid
    }

    private fun notifyDeletedMessage(intent: Intent, context: Context) {
        val isDelete = intent.getBooleanExtra("isDeleted", false)
        val title = intent.getStringExtra("title") ?: ""
        val app = intent.getStringExtra("app") ?: ""

        /*if (isDelete) {
            serviceScope.launch {
                val lastMessage = repositoryTextMessageRecovery?.lastMessage(title)
                lastMessage?.let {
                    if (!lastMessage.isDeleted) {
                        Log.d("CheckDeleted", "notifyDeletedMessage: ")
                        //if the message is deleted notify the user and change delete state to deleted

                        val notifications = Notifications(context)
                        notifications.notify(title, "$title deleted a message", "Click here to see", app)
                        repositoryTextMessageRecovery?.messageIsDeleted(lastMessage.id)
                    }
                }
            }
        }*/
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "NotificationService: onDestroy: Service Ended")
        serviceScope.cancel()
    }
}
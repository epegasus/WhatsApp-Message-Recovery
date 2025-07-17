package dev.pegasus.whatsapp.message.recovery.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by: Sohaib Ahmed
 * Date: 7/17/2025
 * <p>
 * Links:
 * - LinkedIn: <a href="https://linkedin.com/in/epegasus">Linkedin</a>
 * - GitHub: <a href="https://github.com/epegasus">Github</a>
 */

@Parcelize
@Entity(tableName = "table_message")
data class ItemDbMessage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val packageName: String,
    val user: String,
    val message: String,
    val iconBytes: ByteArray?,
    val time: Long,
    val isGroup: Boolean = false,
    val isDeleted: Boolean = false,
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ItemDbMessage

        if (id != other.id) return false
        if (time != other.time) return false
        if (isGroup != other.isGroup) return false
        if (packageName != other.packageName) return false
        if (user != other.user) return false
        if (message != other.message) return false
        if (!iconBytes.contentEquals(other.iconBytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + time.hashCode()
        result = 31 * result + isGroup.hashCode()
        result = 31 * result + packageName.hashCode()
        result = 31 * result + user.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + (iconBytes?.contentHashCode() ?: 0)
        return result
    }
}
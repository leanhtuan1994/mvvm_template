package com.zkuzan.mvvm.util.extension

import android.app.*
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.content.ActivityNotFoundException
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.*
import androidx.core.app.ActivityOptionsCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

/**
 * Extension method to provide simpler access to { @link ContextCompat#getColor(int) }.
 */
fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

/**
 * Extension method to find a device width in pixels
 */
inline val Context.displayWidth: Int
    get() = resources.displayMetrics.widthPixels
/**
 * Extension method to find a device height in pixels
 */
inline val Context.displayHeight: Int
    get() = resources.displayMetrics.heightPixels
/**
 * Extension method to get displayMetrics in Context.displayMetricks
 */
inline val Context.displayMetricks: DisplayMetrics
    get() = resources.displayMetrics
/**
 * Extension method to get LayoutInflater
 */
inline val Context.inflater: LayoutInflater
    get() = LayoutInflater.from(this)

/**
 * Extension method to get a new Intent for an Activity class
 */
inline fun <reified T : Any> Context.intent() = Intent(this, T::class.java)

/**
 * Create an intent for [T] and apply a lambda on it
 */
inline fun <reified T : Any> Context.intent(body: Intent.() -> Unit): Intent {
    val intent = Intent(this, T::class.java)
    intent.body()
    return intent
}

/**
 * Extension method to startActivity for Context.
 */
inline fun <reified T : Activity> Context?.startActivity() =
    this?.startActivity(Intent(this, T::class.java))

/**
 * Extension method to start Service for Context.
 */
inline fun <reified T : Service> Context?.startService() =
    this?.startService(Intent(this, T::class.java))

/**
 * Extension method to startActivity with Animation for Context.
 */
inline fun <reified T : Activity> Context.startActivityWithAnimation(
    enterResId: Int = 0,
    exitResId: Int = 0
) {
    val intent = Intent(this, T::class.java)
    val bundle = ActivityOptionsCompat.makeCustomAnimation(this, enterResId, exitResId)
        .toBundle()
    ContextCompat.startActivity(this, intent, bundle)
}

/**
 * Extension method to startActivity with Animation for Context.
 */
inline fun <reified T : Activity> Context.startActivityWithAnimation(
    enterResId: Int = 0,
    exitResId: Int = 0,
    intentBody: Intent.() -> Unit
) {
    val intent = Intent(this, T::class.java)
    intent.intentBody()
    val bundle = ActivityOptionsCompat.makeCustomAnimation(this, enterResId, exitResId)
        .toBundle()
    ContextCompat.startActivity(this, intent, bundle)
}

/**
 * Extension method to show toast for Context.
 */
fun Context?.toast(
    text: CharSequence,
    duration: Int = Toast.LENGTH_LONG
) =
    this?.let {
        Toast.makeText(it, text, duration)
            .show()
    }

/**
 * Extension method to show toast for Context.
 */
fun Context?.toast(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) =
    this?.let {
        Toast.makeText(it, textId, duration)
            .show()
    }

/**
 * Extension method to Get Integer resource for Context.
 */
fun Context.getInteger(@IntegerRes id: Int) = resources.getInteger(id)

/**
 * Extension method to Get Boolean resource for Context.
 */
fun Context.getBoolean(@BoolRes id: Int) = resources.getBoolean(id)

/**
 * InflateLayout
 */
fun Context.inflateLayout(
    @LayoutRes layoutId: Int, parent: ViewGroup? = null,
    attachToRoot: Boolean = false
): View = LayoutInflater.from(this).inflate(layoutId, parent, attachToRoot)

/**
 * Extension method to get inputManager for Context.
 */
inline val Context.inputManager: InputMethodManager?
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
/**
 * Extension method to get notificationManager for Context.
 */
inline val Context.notificationManager: NotificationManager?
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
/**
 * Extension method to get keyguardManager for Context.
 */
inline val Context.keyguardManager: KeyguardManager?
    get() = getSystemService(Context.KEYGUARD_SERVICE) as? KeyguardManager
/**
 * Extension method to get telephonyManager for Context.
 */
inline val Context.telephonyManager: TelephonyManager?
    get() = getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager
/**
 * Extension method to get devicePolicyManager for Context.
 */
inline val Context.devicePolicyManager: DevicePolicyManager?
    get() = getSystemService(Context.DEVICE_POLICY_SERVICE) as? DevicePolicyManager
/**
 * Extension method to get connectivityManager for Context.
 */
inline val Context.connectivityManager: ConnectivityManager?
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
/**
 * Extension method to get alarmManager for Context.
 */
inline val Context.alarmManager: AlarmManager?
    get() = getSystemService(Context.ALARM_SERVICE) as? AlarmManager
/**
 * Extension method to get clipboardManager for Context.
 */
inline val Context.clipboardManager: ClipboardManager?
    get() = getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
/**
 * Extension method to get jobScheduler for Context.
 */
inline val Context.jobScheduler: JobScheduler?
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.JOB_SCHEDULER_SERVICE) as? JobScheduler

/**
 * Extension method to show notification for Context.
 */
inline fun Context.notification(
    body: NotificationCompat.Builder.() -> Unit,
    channelId: String
): Notification {
    val builder = NotificationCompat.Builder(this, channelId)
    builder.body()
    return builder.build()
}

/**
 * Extension method to share for Context.
 */
fun Context.share(
    text: String,
    subject: String = ""
): Boolean {
    val intent = Intent()
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    intent.putExtra(Intent.EXTRA_TEXT, text)
    try {
        startActivity(Intent.createChooser(intent, null))
        return true
    } catch (e: ActivityNotFoundException) {
        return false
    }
}

/**
 * Extension method to provide quicker access to the [LayoutInflater] from [Context].
 */
fun Context.getLayoutInflater() =
    getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

/**
 * Extension method to provide quicker access to the [Drawable] from [Context].
 */
fun Context.getDrawableCompat(drawableId: Int) = ContextCompat.getDrawable(this, drawableId)


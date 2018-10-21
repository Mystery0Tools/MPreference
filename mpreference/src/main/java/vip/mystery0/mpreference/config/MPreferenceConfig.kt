package vip.mystery0.mpreference.config

import android.graphics.Color
import vip.mystery0.mpreference.base.BaseMPreference

class MPreferenceConfig {
    var showDivider = true
    var showIcon = false
    var startMargin: Int = 48
    var endMargin: Int = 48
    var topMargin: Int = 32
    var bottomMargin: Int = 32
    var titleTextSize: Float = 16F
    var titleTextColor: Int = Color.BLACK
    var summaryTextSize: Float = 14F
    var summaryTextColor: Int = Color.BLACK

    val mpreferenceList = ArrayList<Class<out BaseMPreference>>()
}
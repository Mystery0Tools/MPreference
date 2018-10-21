package vip.mystery0.mpreference.config

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.TypedValue
import vip.mystery0.mpreference.R
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
    var categoryTextColor: Int = Color.RED
    var divider: Drawable? = null

    val mpreferenceList = ArrayList<Class<out BaseMPreference>>()

    fun init(context: Context):MPreferenceConfig {
        initCategoryTextColor(context)
        initDivider(context)
        return this
    }

    private fun initCategoryTextColor(context: Context) {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(R.attr.colorAccent, typedValue, true)
        categoryTextColor = typedValue.data
    }

    private fun initDivider(context: Context) {
        val typedArray = context.obtainStyledAttributes(intArrayOf(android.R.attr.listDivider))
        divider = typedArray.getDrawable(0)
        typedArray.recycle()
    }
}
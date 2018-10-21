package vip.mystery0.mpreference.config

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.TypedValue
import vip.mystery0.mpreference.R
import vip.mystery0.mpreference.base.BaseMPreference

class MPreferenceConfig {
    var showDivider = true
    var showIcon = true
    var iconSize: Float = 24F//图标大小——dp
    var startMargin: Int = 48//左边距——px
    var endMargin: Int = 48//右边距——px
    var topMargin: Int = 32//上边距——px
    var bottomMargin: Int = 32//下边距——px
    var titleTextSize: Float = 16F//sp
    var titleTextColor: Int = Color.BLACK
    var summaryTextSize: Float = 14F//sp
    var summaryTextColor: Int = Color.BLACK
    var categoryTextColor: Int = Color.RED
    var divider: Drawable? = null

    val mpreferenceList = ArrayList<Class<out BaseMPreference>>()

    fun init(context: Context): MPreferenceConfig {
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
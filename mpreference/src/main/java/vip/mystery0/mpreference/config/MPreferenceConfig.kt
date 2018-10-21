package vip.mystery0.mpreference.config

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.ColorInt
import vip.mystery0.mpreference.R
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.impl.*

class MPreferenceConfig {
    var showDivider = true
    var showIcon = true
    var iconSize: Float = 24F//图标大小——dp
    var startMargin: Int = 48//左边距——px
    var endMargin: Int = 48//右边距——px
    var topMargin: Int = 32//上边距——px
    var bottomMargin: Int = 32//下边距——px
    var titleTextSize: Float = 16F//sp
    @ColorInt
    var titleTextColor: Int = Color.BLACK
    var summaryTextSize: Float = 14F//sp
    @ColorInt
    var summaryTextColor: Int = Color.BLACK
    @ColorInt
    var categoryTextColor: Int = Color.RED
    var divider: Drawable? = null
    @ColorInt
    var backgroundColor: Int = Color.WHITE

    val preferenceList = ArrayList<Class<out BaseMPreference>>()

    init {
        preferenceList.add(CheckBoxMPreference::class.java)
        preferenceList.add(PageMPreference::class.java)
        preferenceList.add(SwitchMPreference::class.java)
        preferenceList.add(TextMPreference::class.java)
        preferenceList.add(CategoryMPreference::class.java)
        preferenceList.add(NextMPreference::class.java)
    }

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
package vip.mystery0.mpreference.base

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.constant.NodeAttributeConstant
import vip.mystery0.mpreference.mPreferenceInterface.MPreferenceClickable
import vip.mystery0.mpreference.mPreferenceInterface.MPreferenceValueChangeable
import vip.mystery0.mpreference.mPreferenceInterface.OnMPreferenceClickListener
import vip.mystery0.mpreference.mPreferenceInterface.OnMPreferenceValueChangeListener

abstract class BaseMPreference : MPreferenceClickable<BaseMPreference>, MPreferenceValueChangeable<BaseMPreference> {
    var clickListenerMPreference: OnMPreferenceClickListener<BaseMPreference>? = null
    var changeListenerMPreference: OnMPreferenceValueChangeListener<BaseMPreference>? = null
    var icon: Drawable? = null
    var title: String = ""
    var summary: String = ""
    lateinit var id: String
    var isEnable: Boolean = true

    open fun parseAttribute(context: Context, attributeName: String, attributeValue: String, config: MPreferenceConfig) {
        when (attributeName) {
            NodeAttributeConstant.ID -> id = parseString(context, attributeValue)
            NodeAttributeConstant.TITLE -> title = parseString(context, attributeValue)
            NodeAttributeConstant.SUMMARY -> summary = parseString(context, attributeValue)
            NodeAttributeConstant.IS_ENABLE -> isEnable = attributeValue == "true"
            NodeAttributeConstant.ICON -> {
                if (!config.showIcon) return
                icon = parseDrawable(context, attributeValue)
            }
        }
    }

    fun parseString(context: Context, attributeValue: String): String {
        try {
            if (!attributeValue.startsWith("@string/")) return attributeValue
            return context.getString(parseID(context, attributeValue))
        } catch (e: Exception) {
            throw NullPointerException("cannot find resource called $attributeValue")
        }
    }

    fun parseDrawable(context: Context, attributeValue: String): Drawable? {
        try {
            return ContextCompat.getDrawable(context, parseID(context, attributeValue))
        } catch (e: Exception) {
            throw NullPointerException("cannot find resource called $attributeValue")
        }
    }

    fun parseID(context: Context, attributeValue: String): Int {
        val temp = attributeValue.substring(1).split('/')
        val folder = temp[0]
        val resourceName = temp[1]
        return context.resources.getIdentifier(resourceName, folder, context.packageName)
    }

    override fun setOnMPreferenceClickListener(listenerMPreference: OnMPreferenceClickListener<BaseMPreference>) {
        this.clickListenerMPreference = listenerMPreference
    }

    override fun setOnMPreferenceChangeListener(listenerMPreference: OnMPreferenceValueChangeListener<BaseMPreference>) {
        this.changeListenerMPreference = listenerMPreference
    }

    open fun setOnMPreferenceClickListener(listenerMPreference: (BaseMPreference) -> Unit) {
        this.clickListenerMPreference = object : OnMPreferenceClickListener<BaseMPreference> {
            override fun onClick(base: BaseMPreference) {
                listenerMPreference.invoke(base)
            }
        }
    }

    open fun setOnMPreferenceChangeListener(listenerMPreference: (BaseMPreference) -> Unit) {
        this.changeListenerMPreference = object : OnMPreferenceValueChangeListener<BaseMPreference> {
            override fun onValueChange(base: BaseMPreference) {
                listenerMPreference.invoke(base)
            }
        }
    }
}

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
    lateinit var icon: Drawable
    lateinit var title: String
    lateinit var summary: String
    lateinit var id: String
    var isEnable: Boolean = true

    open fun parseAttribute(
        context: Context,
        attributeName: String,
        attributeValue: String,
        config: MPreferenceConfig
    ) {
        when (attributeName) {
            NodeAttributeConstant.ID -> id = attributeValue
            NodeAttributeConstant.TITLE -> title = attributeValue
            NodeAttributeConstant.SUMMARY -> summary = attributeValue
            NodeAttributeConstant.IS_ENABLE -> isEnable = attributeValue == "true"
            NodeAttributeConstant.ICON -> {
                if (!config.showIcon)
                    return
                try {
                    val temp = attributeValue.substring(1).split('/')
                    val folder = temp[0]
                    val resourceName = temp[1]
                    val id = context.resources.getIdentifier(resourceName, folder, context.packageName)
                    val iconDrawable = ContextCompat.getDrawable(context, id)
                        ?: throw NullPointerException("cannot find resource called $attributeValue")
                    icon = iconDrawable
                } catch (e: Exception) {
                    throw NullPointerException("cannot find resource called $attributeValue")
                }
            }
        }
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

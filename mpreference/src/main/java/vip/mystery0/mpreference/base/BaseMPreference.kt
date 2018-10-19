package vip.mystery0.mpreference.base

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import vip.mystery0.mpreference.constant.NodeAttributeConstant

abstract class BaseMPreference {
    lateinit var icon: Drawable
    lateinit var title: String
    lateinit var summary: String
    lateinit var id: String

    open fun parseAttribute(context: Context, attributeName: String, attributeValue: String) {
        when (attributeName) {
            NodeAttributeConstant.ID -> id = attributeValue
            NodeAttributeConstant.TITLE -> title = attributeValue
            NodeAttributeConstant.SUMMARY -> summary = attributeValue
            NodeAttributeConstant.ICON -> {
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
}

package vip.mystery0.mpreference.impl

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import vip.mystery0.mpreference.adapter.viewholder.NextMPreferenceViewHolder
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.constant.NodeAttributeConstant
import vip.mystery0.mpreference.mpreferenceAnnotation.DeclareMPreference

@DeclareMPreference(NextMPreferenceViewHolder::class)
open class NextMPreference : BaseMPreference() {
    var nextDrawable: Drawable? = null

    override fun parseAttribute(context: Context, attributeName: String, attributeValue: String, config: MPreferenceConfig) {
        when (attributeName) {
            NodeAttributeConstant.NEXT_ICON -> {
                if (!config.showIcon) return
                try {
                    val temp = attributeValue.substring(1).split('/')
                    val folder = temp[0]
                    val resourceName = temp[1]
                    val id = context.resources.getIdentifier(resourceName, folder, context.packageName)
                    nextDrawable = ContextCompat.getDrawable(context, id)
                } catch (e: Exception) {
                    throw NullPointerException("cannot find resource called $attributeValue")
                }
            }
            else -> super.parseAttribute(context, attributeName, attributeValue, config)
        }
    }
}
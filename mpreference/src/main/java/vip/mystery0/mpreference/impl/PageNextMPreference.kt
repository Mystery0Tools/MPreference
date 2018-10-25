package vip.mystery0.mpreference.impl

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import vip.mystery0.mpreference.adapter.viewholder.PageNextMPreferenceViewHolder
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.constant.NodeAttributeConstant
import vip.mystery0.mpreference.mpreferenceAnnotation.DeclareMPreference

@DeclareMPreference(PageNextMPreferenceViewHolder::class)
open class PageNextMPreference : PageMPreference() {
    var nextDrawable: Drawable? = null

    override fun parseAttribute(context: Context, attributeName: String, attributeValue: String, config: MPreferenceConfig) {
        when (attributeName) {
            NodeAttributeConstant.NEXT_ICON -> {
                if (!config.showIcon) return
                nextDrawable = parseDrawable(context, attributeValue)
            }
            else -> super.parseAttribute(context, attributeName, attributeValue, config)
        }
    }
}
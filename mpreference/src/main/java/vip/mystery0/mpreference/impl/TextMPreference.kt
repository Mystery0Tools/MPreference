package vip.mystery0.mpreference.impl

import android.content.Context
import vip.mystery0.mpreference.adapter.viewholder.TextMPreferenceViewHolder
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.constant.NodeAttributeConstant
import vip.mystery0.mpreference.mpreferenceAnnotation.DeclareMPreference

@DeclareMPreference(TextMPreferenceViewHolder::class)
class TextMPreference : BaseMPreference() {
    lateinit var category: String

    override fun parseAttribute(context: Context, attributeName: String, attributeValue: String, config: MPreferenceConfig) {
        when (attributeName) {
            NodeAttributeConstant.CATEGORY -> category = attributeValue
            else -> super.parseAttribute(context, attributeName, attributeValue, config)
        }
    }
}
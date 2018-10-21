package vip.mystery0.mpreference.impl

import android.content.Context
import vip.mystery0.mpreference.adapter.viewholder.CategoryMPreferenceViewHolder
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.constant.NodeAttributeConstant
import vip.mystery0.mpreference.mpreferenceAnnotation.DeclareMPreference

@DeclareMPreference(CategoryMPreferenceViewHolder::class)
class CategoryMPreference : PageMPreference() {
    override fun parseAttribute(context: Context, attributeName: String, attributeValue: String, config: MPreferenceConfig) {
        when (attributeName) {
            NodeAttributeConstant.IS_ENABLE -> isEnable = true//设置category不可禁用
            else -> super.parseAttribute(context, attributeName, attributeValue, config)
        }
    }
}
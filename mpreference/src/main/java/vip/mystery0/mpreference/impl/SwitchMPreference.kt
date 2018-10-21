package vip.mystery0.mpreference.impl

import android.content.Context
import vip.mystery0.mpreference.adapter.viewholder.SwitchMPreferenceViewHolder
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.constant.NodeAttributeConstant
import vip.mystery0.mpreference.mpreferenceAnnotation.DeclareMPreference

@DeclareMPreference(SwitchMPreferenceViewHolder::class)
class SwitchMPreference : TwoStatePreference() {
    var isChecked = isOn

    override fun parseAttribute(context: Context, attributeName: String, attributeValue: String, config: MPreferenceConfig) {
        when (attributeName) {
            NodeAttributeConstant.IS_CHECKED -> isChecked = attributeValue == "true"
            else -> super.parseAttribute(context, attributeName, attributeValue, config)
        }
    }
}
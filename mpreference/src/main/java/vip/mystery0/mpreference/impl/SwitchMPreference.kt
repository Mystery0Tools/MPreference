package vip.mystery0.mpreference.impl

import android.content.Context
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.constant.NodeAttributeConstant

class SwitchMPreference : TwoStatePreference() {
    var isChecked = isOn

    override fun parseAttribute(
        context: Context,
        attributeName: String,
        attributeValue: String,
        config: MPreferenceConfig
    ) {
        when (attributeName) {
            NodeAttributeConstant.IS_CHECKED -> isChecked = attributeValue == "true"
            else -> super.parseAttribute(context, attributeName, attributeValue, config)
        }
    }
}
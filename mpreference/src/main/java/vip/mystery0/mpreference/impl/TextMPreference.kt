package vip.mystery0.mpreference.impl

import android.content.Context
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.constant.NodeAttributeConstant

class TextMPreference : BaseMPreference() {
    lateinit var category: String
    override fun parseAttribute(context: Context, attributeName: String, attributeValue: String) {
        when (attributeName) {
            NodeAttributeConstant.CATEGORY -> category = attributeValue
            else -> super.parseAttribute(context, attributeName, attributeValue)
        }
    }
}
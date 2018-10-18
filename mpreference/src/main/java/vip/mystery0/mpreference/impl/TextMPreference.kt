package vip.mystery0.mpreference.impl

import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.constant.NodeAttributeConstant

class TextMPreference : BaseMPreference() {
    lateinit var category: String
    override fun parseAttribute(attributeName: String, attributeValue: String) {
        when (attributeName) {
            NodeAttributeConstant.ID -> id = attributeValue
            NodeAttributeConstant.TITLE -> id = attributeValue
            NodeAttributeConstant.SUMMARY -> summary = attributeValue
            NodeAttributeConstant.CATEGORY -> category = attributeValue
        }
    }
}
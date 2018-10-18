package vip.mystery0.mpreference.impl

import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.constant.NodeAttributeConstant

class PageMPreference : BaseMPreference() {
    override fun parseAttribute(attributeName: String, attributeValue: String) {
        when (attributeName) {
            NodeAttributeConstant.ID -> id = attributeValue
            NodeAttributeConstant.TITLE -> title = attributeValue
        }
    }
}
package vip.mystery0.mpreference.base

import android.graphics.drawable.Drawable

abstract class BaseMPreference {
    lateinit var icon: Drawable
    lateinit var title: String
    lateinit var summary: String
    lateinit var id: String

    abstract fun parseAttribute(attributeName: String, attributeValue: String)
}
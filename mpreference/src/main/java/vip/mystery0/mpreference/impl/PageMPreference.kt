package vip.mystery0.mpreference.impl

import vip.mystery0.mpreference.base.BaseMPreference

class PageMPreference : BaseMPreference() {
    var root: PageMPreference? = null
    lateinit var next: PageMPreference
    val content = ArrayList<BaseMPreference>()
}
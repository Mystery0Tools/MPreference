package vip.mystery0.mpreference.impl

import vip.mystery0.mpreference.adapter.viewholder.PageMPreferenceViewHolder
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.mpreferenceAnnotation.DeclareMPreference

@DeclareMPreference(PageMPreferenceViewHolder::class)
class PageMPreference : BaseMPreference() {
    var root: PageMPreference? = null
    lateinit var next: PageMPreference
    val content = ArrayList<BaseMPreference>()
}
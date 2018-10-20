package vip.mystery0.mpreference.mPreferenceInterface

import vip.mystery0.mpreference.base.BaseMPreference

interface OnMPreferenceClickListener<T : BaseMPreference> {
    fun onClick(base: T)
}
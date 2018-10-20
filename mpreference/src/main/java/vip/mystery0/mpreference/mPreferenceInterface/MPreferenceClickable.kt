package vip.mystery0.mpreference.mPreferenceInterface

import vip.mystery0.mpreference.base.BaseMPreference

interface MPreferenceClickable<T : BaseMPreference> {
    fun setOnMPreferenceClickListener(listenerMPreference: OnMPreferenceClickListener<T>)
}
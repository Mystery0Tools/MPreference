package vip.mystery0.mpreference.mPreferenceInterface

import vip.mystery0.mpreference.base.BaseMPreference

interface MPreferenceValueChangeable<T : BaseMPreference> {
    fun setOnMPreferenceChangeListener(listenerMPreference: OnMPreferenceValueChangeListener<T>)
}
package vip.mystery0.mpreference.mPreferenceInterface

import android.content.Context
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.config.MPreferenceConfig

interface MPreferenceViewHolderInterface<T : BaseMPreference> {
    fun onLayout(context: Context, config: MPreferenceConfig, base: T)
    fun onSetListener(base: T)
    fun onEnable(config: MPreferenceConfig)
    fun onDisable(config: MPreferenceConfig)
}
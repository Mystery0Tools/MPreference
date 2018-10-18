package vip.mystery0.mpreference.`interface`

import androidx.databinding.ViewDataBinding
import vip.mystery0.mpreference.base.BaseMPreference

interface MPreferenceViewHolderLayout<T : ViewDataBinding, B : BaseMPreference> {
    fun layout(binding: T, base: B)
}
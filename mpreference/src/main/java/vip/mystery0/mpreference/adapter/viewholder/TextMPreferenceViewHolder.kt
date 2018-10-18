package vip.mystery0.mpreference.adapter.viewholder

import vip.mystery0.mpreference.`interface`.MPreferenceViewHolderLayout
import vip.mystery0.mpreference.base.BaseMPreferenceViewHolder
import vip.mystery0.mpreference.databinding.LayoutMpreferenceTextBinding
import vip.mystery0.mpreference.impl.TextMPreference

class TextMPreferenceViewHolder(binding: LayoutMpreferenceTextBinding) :
    BaseMPreferenceViewHolder(binding), MPreferenceViewHolderLayout<LayoutMpreferenceTextBinding, TextMPreference> {
    override fun layout(binding: LayoutMpreferenceTextBinding, base: TextMPreference) {
        binding.textView.text = base.title
    }
}
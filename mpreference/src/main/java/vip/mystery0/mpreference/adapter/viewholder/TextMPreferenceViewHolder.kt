package vip.mystery0.mpreference.adapter.viewholder

import android.view.LayoutInflater
import android.widget.TextView
import vip.mystery0.mpreference.R
import vip.mystery0.mpreference.base.BaseMPreferenceViewHolder
import vip.mystery0.mpreference.impl.TextMPreference

class TextMPreferenceViewHolder(layoutInflater: LayoutInflater) :
    BaseMPreferenceViewHolder<TextMPreference>(layoutInflater.inflate(R.layout.layout_mpreference_text, null)) {
    override fun layout(base: TextMPreference) {
        val textView = view.findViewById<TextView>(R.id.textView)
        textView.text = base.title
    }
}
package vip.mystery0.mpreference.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.widget.Switch
import android.widget.TextView
import vip.mystery0.mpreference.R
import vip.mystery0.mpreference.base.BaseMPreferenceViewHolder
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.impl.SwitchMPreference

class SwitchMPreferenceViewHolder(layoutInflater: LayoutInflater) :
    BaseMPreferenceViewHolder<SwitchMPreference>(layoutInflater.inflate(R.layout.layout_mpreference_switch, null)) {

    override fun layout(context: Context, config: MPreferenceConfig, base: SwitchMPreference) {
        super.layout(context, config, base)
        val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
        val textViewSummary = view.findViewById<TextView>(R.id.textViewSummary)
        val switchButton = view.findViewById<Switch>(R.id.switchButton)
        textViewTitle.text = base.title
        textViewTitle.textSize = config.titleTextSize
        textViewSummary.text = base.summary
        textViewSummary.textSize = config.summaryTextSize
        switchButton.isEnabled = base.isEnable
        switchButton.isChecked = base.isChecked
    }
}
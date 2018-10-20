package vip.mystery0.mpreference.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.TextView
import vip.mystery0.mpreference.R
import vip.mystery0.mpreference.base.BaseMPreferenceViewHolder
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.impl.CheckBoxMPreference
import vip.mystery0.mpreference.impl.SwitchMPreference

class CheckBoxMPreferenceViewHolder(layoutInflater: LayoutInflater) :
    BaseMPreferenceViewHolder<CheckBoxMPreference>(layoutInflater.inflate(R.layout.layout_mpreference_check_box, null)) {

    override fun layout(context: Context, config: MPreferenceConfig, base: CheckBoxMPreference) {
        super.layout(context, config, base)
        val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
        val textViewSummary = view.findViewById<TextView>(R.id.textViewSummary)
        val checkBox = view.findViewById<CheckBox>(R.id.checkbox)
        textViewTitle.text = base.title
        textViewTitle.textSize = config.titleTextSize
        textViewSummary.text = base.summary
        textViewSummary.textSize = config.summaryTextSize
        checkBox.isChecked = base.isChecked
    }

    override fun onInterface(base: CheckBoxMPreference) {
        val checkBox = view.findViewById<CheckBox>(R.id.checkbox)
        view.setOnClickListener { base.clickListenerMPreference?.onClick(base) }
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            base.isChecked = isChecked
            base.changeListenerMPreference?.onValueChange(base)
        }
    }
}
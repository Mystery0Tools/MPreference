package vip.mystery0.mpreference.adapter.viewholder

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.TextView
import vip.mystery0.mpreference.R
import vip.mystery0.mpreference.base.BaseMPreferenceViewHolder
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.impl.TextMPreference

class TextMPreferenceViewHolder(layoutInflater: LayoutInflater) : BaseMPreferenceViewHolder<TextMPreference>(layoutInflater.inflate(R.layout.layout_mpreference_text, null)) {
    override fun layout(context: Context, config: MPreferenceConfig, base: TextMPreference) {
        super.layout(context, config, base)
        val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
        val textViewSummary = view.findViewById<TextView>(R.id.textViewSummary)
        textViewTitle.text = base.title
        textViewTitle.textSize = config.titleTextSize
        textViewSummary.text = base.summary
        textViewSummary.textSize = config.summaryTextSize
    }

    override fun onInterface(base: TextMPreference) {
        view.setOnClickListener { base.clickListenerMPreference?.onClick(base) }
    }

    override fun onEnable(config: MPreferenceConfig) {
        super.onEnable(config)
        val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
        val textViewSummary = view.findViewById<TextView>(R.id.textViewSummary)
        textViewTitle.setTextColor(config.titleTextColor)
        textViewSummary.setTextColor(config.summaryTextColor)
    }

    override fun onDisable(config: MPreferenceConfig) {
        super.onDisable(config)
        val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
        val textViewSummary = view.findViewById<TextView>(R.id.textViewSummary)
        textViewTitle.setTextColor(Color.GRAY)
        textViewSummary.setTextColor(Color.GRAY)
    }
}
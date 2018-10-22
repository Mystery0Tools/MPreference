package vip.mystery0.mpreference.adapter.viewholder

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import vip.mystery0.mpreference.R
import vip.mystery0.mpreference.base.BaseMPreferenceViewHolder
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.impl.PageNextMPreference

class PageMPreferenceViewHolder(layoutInflater: LayoutInflater) : BaseMPreferenceViewHolder<PageNextMPreference>(layoutInflater.inflate(R.layout.layout_mpreference_text, null)) {
    override fun onLayout(context: Context, config: MPreferenceConfig, base: PageNextMPreference) {
        super.onLayout(context, config, base)
        val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
        val textViewSummary = view.findViewById<TextView>(R.id.textViewSummary)
        val imageViewNext = view.findViewById<ImageView>(R.id.imageViewNext)
        textViewTitle.text = base.title
        textViewTitle.textSize = config.titleTextSize
        textViewSummary.text = base.summary
        textViewSummary.textSize = config.summaryTextSize
        imageViewNext.setImageDrawable(base.nextDrawable)
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
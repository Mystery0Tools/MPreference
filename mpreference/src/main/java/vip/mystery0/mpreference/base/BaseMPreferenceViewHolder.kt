package vip.mystery0.mpreference.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import vip.mystery0.mpreference.R
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.mPreferenceInterface.MPreferenceViewHolderInterface
import vip.mystery0.mpreference.util.DensityTools

abstract class BaseMPreferenceViewHolder<T : BaseMPreference>(val view: View, private val isLayout: Boolean = true, private val isSetListener: Boolean = true, private val isEnable: Boolean = true, private val isDisable: Boolean = true) : RecyclerView.ViewHolder(view), MPreferenceViewHolderInterface<T> {
    fun layout(context: Context, config: MPreferenceConfig, base: T) {
        if (isLayout) {
            view.setPadding(config.startMargin, config.topMargin, config.endMargin, config.bottomMargin)
            val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            view.layoutParams = layoutParams
            view.background = ContextCompat.getDrawable(context, R.drawable.ripple_item)
            if (base.isEnable) onEnable(config)
            else onDisable(config)
            if (config.showIcon) {
                val icon = view.findViewById<ImageView>(R.id.icon)
                if (icon != null) {
                    val iconSize = DensityTools.dp2px(context, config.iconSize)
                    val iconLayoutParams = icon.layoutParams
                    iconLayoutParams.height = iconSize
                    iconLayoutParams.width = iconSize
                    icon.layoutParams = iconLayoutParams
                    if (base.icon != null) icon.setImageDrawable(base.icon)
                    val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
                    val textViewSummary = view.findViewById<TextView>(R.id.textViewSummary)
                    if (textViewTitle != null) {
                        val tempLayoutParams = textViewTitle.layoutParams as ConstraintLayout.LayoutParams
                        tempLayoutParams.marginStart = config.startMargin
                        textViewTitle.layoutParams = tempLayoutParams
                    }
                    if (textViewSummary != null) {
                        val tempLayoutParams = textViewSummary.layoutParams as ConstraintLayout.LayoutParams
                        tempLayoutParams.marginStart = config.startMargin
                        textViewSummary.layoutParams = tempLayoutParams
                    }
                }
            }
        }
        onLayout(context, config, base)
    }

    fun setListener(base: T) {
        if (isSetListener) {
            view.setOnClickListener { base.clickListenerMPreference?.onClick(base) }
        }
        onSetListener(base)
    }

    fun enable(config: MPreferenceConfig) {
        if (isEnable) {
            view.isEnabled = true
        }
        onEnable(config)
    }

    fun disable(config: MPreferenceConfig) {
        if (isDisable) {
            view.isEnabled = false
        }
        onDisable(config)
    }
}
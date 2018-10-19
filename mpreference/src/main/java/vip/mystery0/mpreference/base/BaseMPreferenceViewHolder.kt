package vip.mystery0.mpreference.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.util.DensityTools

abstract class BaseMPreferenceViewHolder<T : BaseMPreference>(val view: View) : RecyclerView.ViewHolder(view) {
    open fun layout(context: Context, config: MPreferenceConfig, base: T) {
        view.setPadding(config.startMargin, config.topMargin, config.endMargin, config.bottomMargin)
        val layoutParams =
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = layoutParams
    }

    open fun clickAndChange(
        base: T,
        clickListener: (T) -> Unit,
        valueChangeListener: (T) -> Unit
    ){
        view.setOnClickListener { clickListener.invoke(base) }
    }
}
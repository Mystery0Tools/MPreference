package vip.mystery0.mpreference.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import vip.mystery0.mpreference.R
import vip.mystery0.mpreference.config.MPreferenceConfig

abstract class BaseMPreferenceViewHolder<T : BaseMPreference>(val view: View) : RecyclerView.ViewHolder(view) {
    open fun layout(context: Context, config: MPreferenceConfig, base: T) {
        view.setPadding(config.startMargin, config.topMargin, config.endMargin, config.bottomMargin)
        val layoutParams =
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = layoutParams
        view.background = ContextCompat.getDrawable(context, R.drawable.ripple_item)
        view.isEnabled = base.isEnable
    }

    abstract fun onInterface(base: T)
}
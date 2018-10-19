package vip.mystery0.mpreference.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseMPreferenceViewHolder<T : BaseMPreference>(val view: View) : RecyclerView.ViewHolder(view) {
    abstract fun layout(base: T)
}
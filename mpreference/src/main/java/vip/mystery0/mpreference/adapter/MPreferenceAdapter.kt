package vip.mystery0.mpreference.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vip.mystery0.mpreference.adapter.viewholder.CheckBoxMPreferenceViewHolder
import vip.mystery0.mpreference.adapter.viewholder.PageMPreferenceViewHolder
import vip.mystery0.mpreference.adapter.viewholder.SwitchMPreferenceViewHolder
import vip.mystery0.mpreference.adapter.viewholder.TextMPreferenceViewHolder
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.base.BaseMPreferenceViewHolder
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.impl.CheckBoxMPreference
import vip.mystery0.mpreference.impl.PageMPreference
import vip.mystery0.mpreference.impl.SwitchMPreference
import vip.mystery0.mpreference.impl.TextMPreference

class MPreferenceAdapter(private val context: Context, private val list: List<BaseMPreference>, private val config: MPreferenceConfig) : RecyclerView.Adapter<BaseMPreferenceViewHolder<out BaseMPreference>>() {
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseMPreferenceViewHolder<out BaseMPreference> = when (viewType) {
        TYPE_PAGE -> PageMPreferenceViewHolder(layoutInflater)
        TYPE_TEXT -> TextMPreferenceViewHolder(layoutInflater)
        TYPE_SWITCH -> SwitchMPreferenceViewHolder(layoutInflater)
        TYPE_CHECK_BOX -> CheckBoxMPreferenceViewHolder(layoutInflater)
        else -> throw NullPointerException("null")
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseMPreferenceViewHolder<out BaseMPreference>, position: Int) {
        when (holder) {
            is PageMPreferenceViewHolder -> set(list[position] as PageMPreference, holder)
            is TextMPreferenceViewHolder -> set(list[position] as TextMPreference, holder)
            is SwitchMPreferenceViewHolder -> set(list[position] as SwitchMPreference, holder)
            is CheckBoxMPreferenceViewHolder -> set(list[position] as CheckBoxMPreference, holder)
        }
    }

    private fun <T : BaseMPreference, H : BaseMPreferenceViewHolder<T>> set(base: T, holder: H) {
        holder.layout(context, config, base)
        holder.onInterface(base)
    }

    override fun getItemViewType(position: Int): Int = when (list[position]) {
        is PageMPreference -> TYPE_PAGE
        is TextMPreference -> TYPE_TEXT
        is SwitchMPreference -> TYPE_SWITCH
        is CheckBoxMPreference -> TYPE_CHECK_BOX
        else -> UNKNOWN
    }

    companion object {
        private const val UNKNOWN = -1
        private const val TYPE_PAGE = 0
        private const val TYPE_TEXT = 1
        private const val TYPE_SWITCH = 2
        private const val TYPE_CHECK_BOX = 3
    }
}
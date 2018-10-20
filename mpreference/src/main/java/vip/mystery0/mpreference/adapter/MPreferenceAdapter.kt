package vip.mystery0.mpreference.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vip.mystery0.mpreference.adapter.viewholder.CheckBoxMPreferenceViewHolder
import vip.mystery0.mpreference.adapter.viewholder.SwitchMPreferenceViewHolder
import vip.mystery0.mpreference.adapter.viewholder.TextMPreferenceViewHolder
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.base.BaseMPreferenceViewHolder
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.impl.CheckBoxMPreference
import vip.mystery0.mpreference.impl.SwitchMPreference
import vip.mystery0.mpreference.impl.TextMPreference

class MPreferenceAdapter(private val context: Context, private val list: List<BaseMPreference>, private val config: MPreferenceConfig) : RecyclerView.Adapter<BaseMPreferenceViewHolder<out BaseMPreference>>() {
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseMPreferenceViewHolder<out BaseMPreference> = when (viewType) {
        TYPE_TEXT -> TextMPreferenceViewHolder(layoutInflater)
        TYPE_SWITCH -> SwitchMPreferenceViewHolder(layoutInflater)
        TYPE_CHECK_BOX -> CheckBoxMPreferenceViewHolder(layoutInflater)
        else -> throw NullPointerException("null")
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseMPreferenceViewHolder<out BaseMPreference>, position: Int) {
        when (holder) {
            is TextMPreferenceViewHolder -> {
                val preference = list[position] as TextMPreference
                holder.layout(context, config, preference)
                holder.onInterface(preference)
            }
            is SwitchMPreferenceViewHolder -> {
                val preference = list[position] as SwitchMPreference
                holder.layout(context, config, preference)
                holder.onInterface(preference)
            }
            is CheckBoxMPreferenceViewHolder -> {
                val preference = list[position] as CheckBoxMPreference
                holder.layout(context, config, preference)
                holder.onInterface(preference)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (list[position]) {
        is TextMPreference -> TYPE_TEXT
        is SwitchMPreference -> TYPE_SWITCH
        is CheckBoxMPreference -> TYPE_CHECK_BOX
        else -> UNKNOWN
    }

    companion object {
        private const val UNKNOWN = -1
        private const val TYPE_TEXT = 1
        private const val TYPE_SWITCH = 2
        private const val TYPE_CHECK_BOX = 3
    }
}
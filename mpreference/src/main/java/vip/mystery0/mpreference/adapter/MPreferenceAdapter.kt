package vip.mystery0.mpreference.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vip.mystery0.mpreference.adapter.viewholder.SwitchMPreferenceViewHolder
import vip.mystery0.mpreference.adapter.viewholder.TextMPreferenceViewHolder
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.base.BaseMPreferenceViewHolder
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.impl.SwitchMPreference
import vip.mystery0.mpreference.impl.TextMPreference

class MPreferenceAdapter(
    private val context: Context,
    private val list: List<BaseMPreference>,
    private val config: MPreferenceConfig
) :
    RecyclerView.Adapter<BaseMPreferenceViewHolder<out BaseMPreference>>() {
    private val layoutInflater = LayoutInflater.from(context)
    lateinit var clickListener: (BaseMPreference) -> Unit
    lateinit var valueChangeListener: (BaseMPreference) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseMPreferenceViewHolder<out BaseMPreference> =
        when (viewType) {
            TYPE_TEXT -> TextMPreferenceViewHolder(layoutInflater)
            TYPE_SWITCH -> SwitchMPreferenceViewHolder(layoutInflater)
            else -> throw NullPointerException("null")
        }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(
        holder: BaseMPreferenceViewHolder<out BaseMPreference>, position: Int
    ) {
        when (holder) {
            is TextMPreferenceViewHolder -> holder.layout(context, config, list[position] as TextMPreference)
            is SwitchMPreferenceViewHolder -> holder.layout(context, config, list[position] as SwitchMPreference)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (list[position]) {
            is TextMPreference -> TYPE_TEXT
            is SwitchMPreference -> TYPE_SWITCH
            else -> UNKNOWN
        }

    companion object {
        private const val UNKNOWN = -1
        private const val TYPE_TEXT = 1
        private const val TYPE_SWITCH = 2
    }
}
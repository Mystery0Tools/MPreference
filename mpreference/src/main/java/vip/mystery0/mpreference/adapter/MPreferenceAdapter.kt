package vip.mystery0.mpreference.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vip.mystery0.mpreference.adapter.viewholder.TextMPreferenceViewHolder
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.base.BaseMPreferenceViewHolder
import vip.mystery0.mpreference.databinding.LayoutMpreferenceTextBinding
import vip.mystery0.mpreference.impl.TextMPreference
import java.lang.NullPointerException

class MPreferenceAdapter(context: Context, private val list: List<BaseMPreference>) :
    RecyclerView.Adapter<BaseMPreferenceViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)
    lateinit var clickListener: (Int, BaseMPreference) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseMPreferenceViewHolder =
        when (viewType) {
            TYPE_TEXT -> TextMPreferenceViewHolder(LayoutMpreferenceTextBinding.inflate(layoutInflater))
            else -> throw NullPointerException("null")
        }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(
        holder: BaseMPreferenceViewHolder, position: Int
    ) {
        when (holder) {
            is TextMPreferenceViewHolder -> {
                holder.layout(holder.binding as LayoutMpreferenceTextBinding, list[position] as TextMPreference)
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (list[position]) {
            is TextMPreference -> TYPE_TEXT
            else -> UNKNOWN
        }

    companion object {
        private const val UNKNOWN = -1
        private const val TYPE_TEXT = 1
    }
}
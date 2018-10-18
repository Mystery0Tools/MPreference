package vip.mystery0.mpreference

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import vip.mystery0.mpreference.adapter.MPreferenceAdapter
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.impl.PageMPreference
import java.io.InputStream
import java.lang.RuntimeException
import javax.xml.parsers.DocumentBuilderFactory

class MPreference : RecyclerView {
    private val originList = ArrayList<BaseMPreference>()
    private val showList = ArrayList<BaseMPreference>()
    private val adapter = MPreferenceAdapter(context, showList)
    private val config = MPreferenceConfig()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        if (config.showDivider)
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        setAdapter(adapter)
    }

    fun parseResource(fileName: String) {
        parseInputStream(context.assets.open(fileName))
    }

    fun parseInputStream(stream: InputStream) {
        val list = ArrayList<BaseMPreference>()

        Log.i("TAG","parseInputStream: ")
    }

    fun setList(array: Array<BaseMPreference>) = setList(array.asList())

    fun setList(list: List<BaseMPreference>) {
        originList.clear()
        originList.addAll(list)
        adapter.notifyDataSetChanged()
    }
}
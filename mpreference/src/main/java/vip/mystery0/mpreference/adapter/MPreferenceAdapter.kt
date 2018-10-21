package vip.mystery0.mpreference.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.base.BaseMPreferenceViewHolder
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.mpreferenceAnnotation.DeclareMPreference

class MPreferenceAdapter(private val context: Context, private val list: List<BaseMPreference>, private val config: MPreferenceConfig) : RecyclerView.Adapter<BaseMPreferenceViewHolder<out BaseMPreference>>() {
    private val layoutInflater = LayoutInflater.from(context)
    private val viewTypeMap = ArrayList<Class<out BaseMPreference>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseMPreferenceViewHolder<out BaseMPreference> {
        val clazz = viewTypeMap[viewType]
        val holderClass = clazz.getAnnotation(DeclareMPreference::class.java)!!.bindMPreferenceViewHolder.java
        return holderClass.getDeclaredConstructor(LayoutInflater::class.java).newInstance(layoutInflater)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseMPreferenceViewHolder<out BaseMPreference>, position: Int) {
        val preference = list[position]
        val clazz = preference.javaClass
        val holderClass = clazz.getAnnotation(DeclareMPreference::class.java)!!.bindMPreferenceViewHolder.java
        if (holder.javaClass == holderClass) {
            val layoutFunction = holderClass.getDeclaredMethod("layout", Context::class.java, MPreferenceConfig::class.java, clazz)
            val onInterfaceFunction = holderClass.getDeclaredMethod("onInterface", clazz)
            layoutFunction.invoke(holder, context, config, preference)
            onInterfaceFunction.invoke(holder, preference)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val clazz = list[position].javaClass
        config.preferenceList.forEach {
            if (it.name == clazz.name) {
                if (!viewTypeMap.contains(it)) {
                    viewTypeMap.add(it)
                    return viewTypeMap.size - 1
                }
                return viewTypeMap.indexOf(it)
            }
        }
        throw NullPointerException("cannot parse class ${clazz.simpleName}")
    }
}
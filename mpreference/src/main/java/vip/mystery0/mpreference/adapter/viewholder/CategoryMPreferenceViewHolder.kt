package vip.mystery0.mpreference.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import vip.mystery0.mpreference.R
import vip.mystery0.mpreference.base.BaseMPreferenceViewHolder
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.impl.CategoryMPreference
import vip.mystery0.mpreference.mpreferenceAnnotation.DeclareMPreference

class CategoryMPreferenceViewHolder(private val layoutInflater: LayoutInflater) : BaseMPreferenceViewHolder<CategoryMPreference>(layoutInflater.inflate(R.layout.layout_mpreference_category, null)) {
    override fun layout(context: Context, config: MPreferenceConfig, base: CategoryMPreference) {
        super.layout(context, config, base)
        view.setPadding(0, 0, 0, 0)
        val textViewCategory = view.findViewById<TextView>(R.id.textViewCategory)
        val contentLinearLayout = view.findViewById<LinearLayout>(R.id.contentLinearLayout)
        textViewCategory.setPadding(config.startMargin, textViewCategory.paddingTop, config.endMargin, textViewCategory.paddingBottom)
        textViewCategory.text = base.title
        textViewCategory.setTextColor(config.categoryTextColor)
        contentLinearLayout.dividerDrawable = config.divider
        contentLinearLayout.removeAllViews()
        base.content.forEach {
            val clazz = it.javaClass
            val holderClass = clazz.getAnnotation(DeclareMPreference::class.java)!!.bindMPreferenceViewHolder.java
            val holderInstance = holderClass.getDeclaredConstructor(LayoutInflater::class.java).newInstance(layoutInflater)
            val layoutFunction = holderClass.getDeclaredMethod("layout", Context::class.java, MPreferenceConfig::class.java, clazz)
            val onInterfaceFunction = holderClass.getDeclaredMethod("onInterface", clazz)
            layoutFunction.invoke(holderInstance, context, config, it)
            onInterfaceFunction.invoke(holderInstance, it)
            contentLinearLayout.addView(holderInstance.view)
        }
    }

    override fun onInterface(base: CategoryMPreference) {
    }
}
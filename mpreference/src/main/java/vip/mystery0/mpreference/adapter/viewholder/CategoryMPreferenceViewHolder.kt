package vip.mystery0.mpreference.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import vip.mystery0.mpreference.R
import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.base.BaseMPreferenceViewHolder
import vip.mystery0.mpreference.config.MPreferenceConfig
import vip.mystery0.mpreference.impl.CategoryMPreference
import vip.mystery0.mpreference.mpreferenceAnnotation.DeclareMPreference
import vip.mystery0.mpreference.util.DensityTools

class CategoryMPreferenceViewHolder(private val layoutInflater: LayoutInflater) : BaseMPreferenceViewHolder<CategoryMPreference>(layoutInflater.inflate(R.layout.layout_mpreference_category, null)) {
    override fun onLayout(context: Context, config: MPreferenceConfig, base: CategoryMPreference) {
        super.onLayout(context, config, base)
        view.setPadding(0, 0, 0, 0)
        val textViewCategory = view.findViewById<TextView>(R.id.textViewCategory)
        val contentLinearLayout = view.findViewById<LinearLayout>(R.id.contentLinearLayout)
        textViewCategory.setPadding(if (config.showIcon) 2 * config.startMargin + DensityTools.dp2px(context, config.iconSize) else config.startMargin, textViewCategory.paddingTop, config.endMargin, textViewCategory.paddingBottom)
        textViewCategory.text = base.title
        textViewCategory.setTextColor(config.categoryTextColor)
        contentLinearLayout.dividerDrawable = config.divider
        contentLinearLayout.removeAllViews()
        base.content.forEach {
            val holderClass = it.javaClass.getAnnotation(DeclareMPreference::class.java)!!.bindMPreferenceViewHolder.java
            val holderInstance = holderClass.getDeclaredConstructor(LayoutInflater::class.java).newInstance(layoutInflater)
            val layoutFunction = holderClass.getMethod("generateView", Context::class.java, MPreferenceConfig::class.java, BaseMPreference::class.java)
            layoutFunction.invoke(holderInstance, context, config, it)
            contentLinearLayout.addView(holderInstance.view)
        }
    }
}
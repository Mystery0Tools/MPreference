package vip.mystery0.mpreference.util

import android.content.Context

object DensityTools {
    /**
     * dp转px
     * @param context 上下文
     * @param dpValue dp值
     * @return 转换之后的px值
     */
    fun dp2px(context: Context, dpValue: Float): Int = (dpValue * context.resources.displayMetrics.density + 0.5).toInt()

    /**
     * dp转px
     * @param context 上下文
     * @param dpValue dp值
     * @return 转换之后的px值
     */
    fun dp2px(context: Context, dpValue: Int): Float = dpValue * context.resources.displayMetrics.density + 0.5f

    /**
     * px转dp
     * @param context 上下文
     * @param pxValue px值
     * @return 转换之后的dp值
     */
    fun px2dp(context: Context, pxValue: Float): Int = (pxValue / context.resources.displayMetrics.density + 0.5).toInt()

    /**
     * px转dp
     * @param context 上下文
     * @param pxValue px值
     * @return 转换之后的dp值
     */
    fun px2dp(context: Context, pxValue: Int): Float = pxValue / context.resources.displayMetrics.density + 0.5f

    /**
     * 获取屏幕宽度
     * @param context 上下文
     * @return px值
     */
    fun getScreenWidth(context: Context): Int = context.resources.displayMetrics.widthPixels


    /**
     * 获取屏幕高度
     * @param context 上下文
     * @return px值
     */
    fun getScreenHeight(context: Context): Int = context.resources.displayMetrics.heightPixels
}
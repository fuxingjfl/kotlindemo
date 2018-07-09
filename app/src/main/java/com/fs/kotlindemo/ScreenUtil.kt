package com.fs.kotlindemo

import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager

/**
 * Created by Administrator on 2018/6/11.
 */

object ScreenUtil {

    /**
     * 获取屏幕宽高，sdk17后不建议采用

     * @param context
     */
    fun getScreenHW(context: Context): IntArray {
        val manager: WindowManager
        manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = manager.defaultDisplay
        val width = display.width
        val height = display.height
        val HW = intArrayOf(width, height)
        return HW
    }

    /**
     * 获取屏幕宽高，建议采用

     * @param context
     */
    fun getScreenHW2(context: Context): IntArray {
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        manager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        val HW = intArrayOf(width, height)
        return HW
    }

    /**
     * 获取屏幕的宽度

     * @param context
     * *
     * @return
     */
    fun getScreenW(context: Context): Int {
        return getScreenHW2(context)[0]
    }

    /**
     * 获取屏幕的高度

     * @param context
     * *
     * @return
     */
    fun getScreenH(context: Context): Int {
        return getScreenHW2(context)[1]
    }

}

package com.fs.kotlindemo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.graphics.Xfermode
import android.util.AttributeSet
import android.view.View

/**
 * Created by Administrator on 2018/6/11.
 */

class AvoidXfermodeView1 : View {

    private var pdXfermode: PorterDuffXfermode ?= null   //定义PorterDuffXfermode变量
    private var screenW: Int=0
    private var screenH: Int = 0 //屏幕宽高
    private val width :Int ?= 200      //绘制的图片宽高
    private val height :Int?= 200
    private var srcBitmap: Bitmap ?= null
    private var dstBitmap: Bitmap ?= null     //上层SRC的Bitmap和下层Dst的Bitmap

    constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        screenW = ScreenUtil.getScreenW(context)
        screenH = ScreenUtil.getScreenH(context)
        //创建一个PorterDuffXfermode对象
        pdXfermode = PorterDuffXfermode(PD_MODE)
        //实例化两个Bitmap
        srcBitmap = makeSrc(width!!, height!!)
        dstBitmap = makeDst(width, height)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}


    //定义一个绘制圆形Bitmap的方法
    private fun makeDst(w: Int, h: Int): Bitmap {
        val bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val c = Canvas(bm)
        val p = Paint(Paint.ANTI_ALIAS_FLAG)
        p.color = 0xFF26AAD1.toInt()
        c.drawOval(RectF(0f, 0f, (w * 3 / 4).toFloat(), (h * 3 / 4).toFloat()), p)
        return bm
    }

    //定义一个绘制矩形的Bitmap的方法
    private fun makeSrc(w: Int, h: Int): Bitmap {
        val bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val c = Canvas(bm)
        val p = Paint(Paint.ANTI_ALIAS_FLAG)
        p.color = 0xFFFFCE43.toInt()
        c.drawRect((w / 3).toFloat(), (h / 3).toFloat(), (w * 19 / 20).toFloat(), (h * 19 / 20).toFloat(), p)
        return bm
    }

    override fun onDraw(canvas: Canvas) {
        val paint = Paint()
        paint.isFilterBitmap = false
        paint.style = Paint.Style.FILL


        if(screenW!=null&&width!=null&&screenH!=null&&height!=null){
            canvas.drawBitmap(srcBitmap, ((screenW / 3 - width) / 2).toFloat(), ((screenH / 2 - height) / 2).toFloat(), paint)
            canvas.drawBitmap(dstBitmap, ((screenW / 3 - width) / 2 + screenW / 3).toFloat(), ((screenH / 2 - height) / 2).toFloat(), paint)
        }


        //创建一个图层，在图层上演示图形混合后的效果o
        val sc = canvas.saveLayer(0f, 0f, screenW.toFloat(), screenH.toFloat(), null, Canvas.MATRIX_SAVE_FLAG or
                Canvas.CLIP_SAVE_FLAG or
                Canvas.HAS_ALPHA_LAYER_SAVE_FLAG or
                Canvas.FULL_COLOR_LAYER_SAVE_FLAG or
                Canvas.CLIP_TO_LAYER_SAVE_FLAG)
        if(screenW!=null&&width!=null&&screenH!=null&&height!=null){
            canvas.drawBitmap(dstBitmap, ((screenW / 3 - width) / 2 + screenW / 3 * 2).toFloat(),
                    ((screenH / 2 - height) / 2).toFloat(), paint)     //绘制i

        }
        //设置Paint的Xfermode
        paint.xfermode = pdXfermode
        if(screenW!=null&&width!=null&&screenH!=null&&height!=null){
            canvas.drawBitmap(srcBitmap, ((screenW / 3 - width) / 2 + screenW / 3 * 2).toFloat(),
                    ((screenH / 2 - height) / 2).toFloat(), paint)
        }
        paint.xfermode = null
        // 还原画布
        canvas.restoreToCount(sc)
    }

    companion object {
        //定义MODE常量，等下直接改这里即可进行测试
        private val PD_MODE = PorterDuff.Mode.SCREEN
    }


}

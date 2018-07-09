package com.fs.kotlindemo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View


/**
 * Created by Administrator on 2018/6/12.
 */
class MyView : View {

    //将水平和竖直方向上都划分为20格
    private val WIDTH = 20
    private val HEIGHT = 20
    private val COUNT = (WIDTH + 1) * (HEIGHT + 1)  //记录该图片包含21*21个点
    //扭曲前的坐标数组
    private var verts: FloatArray = kotlin.FloatArray(COUNT * 2)
    //扭曲后的坐标数组
    private var orig: FloatArray = kotlin.FloatArray(COUNT * 2)

    private var mBitmap: Bitmap? = null
    private var bH: Float = 0.0f
    private var bW: Float = 0.0f

    constructor(con: Context) : super(con) {
        init()
    }

    constructor(con: Context, attr: AttributeSet? = null) : super(con, attr) {
        init()
    }

    constructor(con: Context, attr: AttributeSet? = null, def: Int) : super(con, attr, def) {
        init()
    }

    private fun init() {

        mBitmap = BitmapFactory.decodeResource(this.resources, R.drawable.mz)
        bW = (mBitmap as Bitmap?)!!.getWidth().toFloat();
        bH = (mBitmap as Bitmap?)!!.getHeight().toFloat();
        Log.e("TAG", "图片坐标==bW==" + bW + ",bH==" + bH)
        var index = 0
        //初始化orig和verts数组。
        for (y in 0..HEIGHT) {
            val fy = bH * y / HEIGHT
//            Log.e("TAG","y坐标=="+fy)
            for (x in 0..WIDTH) {
                val fx = bW * x / WIDTH
//                Log.e("TAG","x坐标=="+fx)
                verts[index * 2 + 0] = fx
                orig[index * 2 + 0] = verts[index * 2 + 0]
                verts[index * 2 + 1] = fy
                orig[index * 2 + 1] = verts[index * 2 + 1]
                index += 1
            }
        }

        //设置背景色
        setBackgroundColor(Color.WHITE);
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmapMesh(mBitmap, WIDTH, HEIGHT, verts, 0, null, 0, null)
    }


    private fun warp(cx: Float, cy: Float) {

        Log.e("TAG", "数组长度：：" + orig.size)

        var i = 0
        while (i < COUNT * 2) {
            val dx = cx - orig[i + 0]
            val dy = cy - orig[i + 1]
            val dd = dx * dx + dy * dy
            //计算每个座标点与当前点（cx、cy）之间的距离
            val d = Math.sqrt(dd.toDouble()).toFloat()
            Log.e("TAG", "dd：：" + dd + ",d==" + d)
            //计算扭曲度，距离当前点（cx、cy）越远，扭曲度越小
            val pull = 80000 / (dd * d).toFloat()
            //对verts数组（保存bitmap上21 * 21个点经过扭曲后的座标）重新赋值
            if (pull >= 1) {
                verts[i + 0] = cx
                verts[i + 1] = cy
            } else {
                //控制各顶点向触摸事件发生点偏移
                verts[i + 0] = orig[i + 0] + dx * pull
                verts[i + 1] = orig[i + 1] + dy * pull
            }
            i += 2
        }
        //通知View组件重绘
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //调用warp方法根据触摸屏事件的座标点来扭曲verts数组
        warp(event.x, event.y)
        return true
    }

}
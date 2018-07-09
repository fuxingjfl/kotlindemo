package com.fs.kotlindemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.Canvas.ALL_SAVE_FLAG


/**
 * Created by Administrator on 2018/6/25.
 */
class SaveLayerAlphaView : View {

    private var mPaint: Paint? = null
    private var xf:PorterDuffXfermode  = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    constructor(con:Context):super(con){
        init()

    }

    constructor(con:Context,attr: AttributeSet):super(con,attr){
        init()

    }

    constructor(con:Context, attr: AttributeSet, def:Int):super(con,attr,def){
        init()
    }


    fun init(){

        mPaint = Paint()
        mPaint!!.color = Color.RED

    }

    override protected fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(100f, 100f, 300f, 300f, mPaint)
        val layerID = canvas.saveLayerAlpha(0f, 0f, 600f, 600f, 0x88, Canvas.ALL_SAVE_FLAG)
        mPaint!!.setColor(Color.GREEN)
        canvas.drawRect(200f, 200f, 400f, 400f, mPaint)
        canvas.restoreToCount(layerID)

    }
}
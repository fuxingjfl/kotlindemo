package com.fs.kotlindemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.Canvas.MATRIX_SAVE_FLAG



/**
 * Created by Administrator on 2018/6/25.
 */
class MATRIX_SAVE_FLAG_View :View{

    private var mPaint: Paint? = null

    constructor(con: Context):super(con){

    }

    constructor(con: Context, attr: AttributeSet):super(con,attr){
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        mPaint = Paint()

        mPaint!!.setColor(Color.GREEN)
    }

    constructor(con: Context, attr: AttributeSet, def:Int):super(con,attr,def){
    }

    override protected fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save(Canvas.MATRIX_SAVE_FLAG)
        canvas.clipRect(100, 0, 200, 100)
        canvas.drawColor(Color.GREEN)
        canvas.restore()
        canvas.drawColor(Color.YELLOW)
    }
}
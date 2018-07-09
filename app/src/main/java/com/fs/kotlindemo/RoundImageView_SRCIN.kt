package com.fs.kotlindemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.Canvas.ALL_SAVE_FLAG






/**
 * Created by Administrator on 2018/6/20.
 */
class RoundImageView_SRCIN :View{

    private var mBitPaint: Paint? = null
    private var BmpDST: Bitmap? = null
    private var BmpSRC: Bitmap? = null

    constructor(con: Context):super(con){
        init()

    }

    constructor(con: Context, attr: AttributeSet):super(con,attr){
        init()

    }

    constructor(con: Context, attr: AttributeSet, def:Int):super(con,attr,def){
        init()
    }

    fun init(){
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mBitPaint = Paint()
        BmpDST = BitmapFactory.decodeResource(resources, R.drawable.dog_shade, null)
        BmpSRC = BitmapFactory.decodeResource(resources, R.drawable.dog, null)
    }

    override protected fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val layerId = canvas.saveLayer(0F, 0F, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)

        canvas!!.drawBitmap(BmpDST, 0f, 0f, mBitPaint)
        mBitPaint!!.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        canvas.drawBitmap(BmpSRC, 0f, 0f, mBitPaint)

        mBitPaint!!.setXfermode(null)
        canvas.restoreToCount(layerId)
    }

}
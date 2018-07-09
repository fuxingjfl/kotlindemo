package com.fs.kotlindemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Canvas.ALL_SAVE_FLAG



/**
 * Created by Administrator on 2018/6/22.
 */
class XfermodeView : View {

    private var width :Int?= 400
    private var height :Int?= 400
    private var dstBmp: Bitmap?=null
    private var srcBmp: Bitmap?=null
    private var mPaint: Paint?=null

    constructor(con:Context):super(con){

    }

    constructor(con:Context,attr:AttributeSet):super(con,attr){
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        srcBmp = makeSrc(width!!, height!!)
        dstBmp = makeDst(width!!, height!!)
        mPaint = Paint()

    }


    override protected fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.GREEN);
        canvas.drawBitmap(dstBmp, 0f, 0f, mPaint);
        mPaint!!.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcBmp, (width!! / 2).toFloat(), (height!! / 2).toFloat(), mPaint);
        mPaint!!.setXfermode(null);
    }

    // create a bitmap with a circle, used for the "dst" image
    fun makeDst(w: Int, h: Int): Bitmap {
        val bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val c = Canvas(bm)
        val p = Paint(Paint.ANTI_ALIAS_FLAG)

        p.setColor(0xFFFFCC44.toInt())
        c.drawOval(RectF(0f, 0f, w.toFloat(), h.toFloat()), p)
        return bm
    }

    // create a bitmap with a rect, used for the "src" image
    fun makeSrc(w: Int, h: Int): Bitmap {
        val bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val c = Canvas(bm)
        val p = Paint(Paint.ANTI_ALIAS_FLAG)

        p.setColor(0xFF66AAFF.toInt())
        c.drawRect(0f, 0f, w.toFloat(), h.toFloat(), p)
        return bm
    }
}
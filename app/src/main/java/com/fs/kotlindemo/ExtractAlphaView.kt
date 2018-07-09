package com.fs.kotlindemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.BlurMaskFilter
import android.opengl.ETC1.getWidth
import android.opengl.ETC1.getHeight
import android.graphics.BitmapFactory
import android.graphics.Bitmap



/**
 * Created by Administrator on 2018/6/26.
 */
class ExtractAlphaView :View{


    private var paint:Paint?=null
    private var bitmap:Bitmap?=null
    private var mAlphaBmp:Bitmap?=null
    private var bitH:Int?=null
    private var bitW:Int?=null
    constructor(con: Context):super(con){
        init()

    }

    constructor(con: Context, attr: AttributeSet):super(con,attr){
        init()

    }

    constructor(con: Context, attr: AttributeSet, def:Int):super(con,attr,def){
        init()
    }

    private fun init(){
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        paint = Paint()
        bitmap = BitmapFactory.decodeResource(this.resources,R.drawable.blog12)
        bitH= (bitmap as Bitmap?)!!.height
        bitW= (bitmap as Bitmap?)!!.width
        mAlphaBmp= (bitmap as Bitmap?)!!.extractAlpha()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //阴影
        paint!!.setColor(Color.RED);
        paint!!.setMaskFilter(BlurMaskFilter(10f, BlurMaskFilter.Blur.NORMAL))
        canvas!!.drawBitmap(mAlphaBmp,null, Rect(0,0, bitW!!.toInt()-(bitW!!.toInt()/bitH!!.toInt()*100), bitH!!.toInt()-100),paint)
        paint!!.setMaskFilter(null)
        canvas.drawBitmap(bitmap,null, Rect(0,0,bitW!!.toInt()-(bitW!!.toInt()/bitH!!.toInt()*110), bitH!!.toInt()-110),null)
    }
}
package com.fs.kotlindemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.Bitmap
import android.util.Log
import android.view.MotionEvent


/**
 * Created by Administrator on 2018/6/20.
 */
class RectangleImageView :View{

    var hb:Paint ?=null
    var hb_tm:Paint ?=null
    var bitW:Int?=0
    var bitH:Int?=0
    var mbitmap:Bitmap?=null
    var xf:PorterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
    var huabu:Canvas ?=null
    var bitmap:Bitmap?=null
    var bs:BitmapShader?=null

    constructor(con:Context):super(con){
        init()
    }

    constructor(con:Context,attr:AttributeSet):super(con,attr){
        init()
    }

    constructor(con:Context,attr:AttributeSet,def:Int):super(con,attr,def){
        init()
    }


    private fun init(){
        hb = Paint()
        hb!!.isAntiAlias=true
        hb!!.setStyle(Paint.Style.STROKE);
        hb_tm = Paint()
        hb_tm!!.isAntiAlias=true
//        hb_tm!!.color=this.resources.getColor(R.color.tm)
//        hb_tm!!.setStyle(Paint.Style.FILL_AND_STROKE);

        mbitmap=BitmapFactory.decodeResource(this.resources,R.drawable.mz)

        bitW= (mbitmap as Bitmap?)!!.width;
        bitH= (mbitmap as Bitmap?)!!.height;

        bitmap= Bitmap.createBitmap(bitW!!, bitH!!,
                Bitmap.Config.ARGB_8888)

        huabu = Canvas(bitmap)
        huabu!!.drawRoundRect(RectF(0f,0f, bitW!!.toFloat(), bitH!!.toFloat()),50f,50f,hb_tm)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas != null) {

            bs=BitmapShader(mbitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
            //矩形导角图
//            canvas.drawRoundRect(RectF(0f,0f, bitW!!.toFloat(), bitH!!.toFloat()),50f,50f,hb_tm)
            var mMatrix:Matrix= Matrix()
            mMatrix.setScale(300f/bitW!!.toFloat(),300f/bitH!!.toFloat())
            bs!!.setLocalMatrix(mMatrix)
            hb_tm!!.setShader(bs)

            canvas.drawCircle(150f,150f,150f,hb_tm)

        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when(event!!.action){

            MotionEvent.ACTION_MOVE ->{
//                Log.e("TAG","正在移动----------------------")
                move(event.getX().toFloat())
            }

        }

        return true
    }

    var move : (i:Float) -> Unit ={}

    public fun setOnMoveListener(m:(i:Float)-> Unit={}){

        this.move=m

    }


}
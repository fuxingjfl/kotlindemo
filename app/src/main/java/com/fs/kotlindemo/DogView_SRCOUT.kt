package com.fs.kotlindemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by Administrator on 2018/6/20.
 */
class DogView_SRCOUT : View {


    private var hb:Paint?=null
    private var bitmap:Bitmap?=null

    private var bitW:Int?=0
    private var bitH:Int=0
    private var xf:PorterDuffXfermode= PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
    private var mPath:Path =Path()


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

        hb = Paint()
        hb!!.isAntiAlias=true
        hb!!.isDither=true;
        hb!!.style=Paint.Style.STROKE
        hb!!.strokeCap= Paint.Cap.ROUND
        hb!!.strokeJoin=Paint.Join.ROUND
        hb!!.strokeWidth=50.toFloat()


        bitmap=BitmapFactory.decodeResource(this.resources,R.drawable.mz)
        bitW= (bitmap as Bitmap?)!!.width
        bitH= (bitmap as Bitmap?)!!.height




    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas != null) {
            var lay:Int=canvas.saveLayer(0f,0f, bitW!!.toFloat(),bitH.toFloat(),null,Canvas.ALL_SAVE_FLAG)
            canvas.drawBitmap(bitmap,0f,0f,null)

            hb!!.setXfermode(xf)
            canvas.drawPath(mPath,hb)
            hb!!.setXfermode(null)
            canvas.restoreToCount(lay)
        }
    }

    var startX:Float ?=0f
    var startY:Float?=0f


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event!!.action){
            MotionEvent.ACTION_DOWN -> {
                startX=event.x
                startY=event.y
                mPath!!.moveTo(startX!!, startY!!)
            }
            MotionEvent.ACTION_MOVE ->{
                if(startX!=event.x||startY!=event.y){//移动了
                    mPath!!.lineTo(event.x,event.y)
                }
            }
            MotionEvent.ACTION_UP ->{

                startX=0f
                startY=0f


            }
        }
        invalidate()
        return true
    }

}
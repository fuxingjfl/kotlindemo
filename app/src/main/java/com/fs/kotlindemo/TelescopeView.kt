package com.fs.kotlindemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.MotionEvent
import android.graphics.Shader
import android.graphics.BitmapShader




/**
 * Created by Administrator on 2018/6/26.
 */
class TelescopeView :View{

    private var mPaint: Paint? = null
    private var mBitmap: Bitmap? = null
    private var mBitmapBG: Bitmap? = null
    private var mDx = -1
    private var mDy = -1

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

        mPaint = Paint()
        mBitmap=BitmapFactory.decodeResource(this.resources,R.drawable.bg)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {


        when(event!!.action){

            MotionEvent.ACTION_DOWN ->{

                mDx= event.x.toInt()
                mDy= event.y.toInt()
                postInvalidate()
                return true
            }
            MotionEvent.ACTION_MOVE ->{

                mDx= event.x.toInt()
                mDy= event.y.toInt()

            }

        }

        postInvalidate()
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (mBitmapBG == null) {
            mBitmapBG = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvasbg = Canvas(mBitmapBG)
            canvasbg.drawBitmap(mBitmap, null, Rect(0, 0, width, height), mPaint)
        }
        mPaint!!.setShader(BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT))
        if (canvas != null) {
            canvas.drawCircle(mDx.toFloat(), mDy.toFloat(), 150f, mPaint)
        }
    }

}
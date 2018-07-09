package com.fs.kotlindemo

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.Bitmap
import android.graphics.RectF
import android.util.Log
import android.view.MotionEvent







/**
 * Created by Administrator on 2018/6/11.
 */
class StripMeiZi : View{

    private var paint:Paint ?=null
    private var type:Xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
    private var screenW: Int = 0
    private var screenH: Int = 0 //屏幕宽高
    private var hb:Canvas ?=null
    private var mBeforeBitmap: Bitmap? = null
    private val mPath = Path()
    private var mBackBitmap: Bitmap? = null
    private var mLastX: Int = 0
    private var mLastY: Int = 0
    constructor(context :Context ,attrs: AttributeSet ?=null) :super(context,attrs){
        screenW = ScreenUtil.getScreenW(context);
        screenH = ScreenUtil.getScreenH(context);
        init()

    }


    constructor(context :Context ,attrs: AttributeSet ?=null,defStyleAttr:Int) :super(context,attrs,defStyleAttr){
        screenW = ScreenUtil.getScreenW(context);
        screenH = ScreenUtil.getScreenH(context);
        init()
    }



    private fun init(){

        paint = Paint()
        paint!!.isAntiAlias=true
        paint!!.isDither=true
        paint!!.style=Paint.Style.STROKE
        paint!!.strokeCap= Paint.Cap.ROUND
        paint!!.strokeJoin=Paint.Join.ROUND
        paint!!.strokeWidth= 80.toFloat()

        mBackBitmap = BitmapFactory.decodeResource(this.resources,R.drawable.meizi_back)

        Log.e("TAG","对象：：："+mBackBitmap)

        mBackBitmap= Bitmap.createScaledBitmap(mBackBitmap,screenW,screenH,false)

        mBeforeBitmap=Bitmap.createBitmap(screenW, screenH, Bitmap.Config.ARGB_8888)

//        mBeforeBitmap=BitmapFactory.decodeResource(this.resources,
//                R.drawable.meizi_before)
                    hb = Canvas(mBeforeBitmap)
        if(screenW!=null&&screenH!=null){
                hb!!.drawBitmap(BitmapFactory.decodeResource(this.resources,
                    R.drawable.meizi_before),null,RectF(0f, 0f, screenW.toFloat(), screenH.toFloat()),null)
        }

    }

    private fun drawPath() {
        hb!!.drawPath(mPath, paint)
        paint!!.setXfermode(type)
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        //另一种写法
        //这个相当于最顶部的图层上的一张图片
        canvas!!.drawBitmap(BitmapFactory.decodeResource(this.resources,R.drawable.meizi_back),null, RectF(0.toFloat(),0.toFloat(),screenW.toFloat(),screenH.toFloat()),null)
        if (canvas != null) {
            //创建一个图层覆盖在另一个图层上其中包括你的上层图片已经手指移动的路径区域，并且设置混排模式
            var la=canvas.saveLayer(0.toFloat(),0.toFloat(),screenW.toFloat(),screenH.toFloat(),paint,Canvas.ALL_SAVE_FLAG)
            canvas!!.drawBitmap(BitmapFactory.decodeResource(this.resources,
                    R.drawable.meizi_before),null,RectF(0f, 0f, screenW.toFloat(), screenH.toFloat()),null)
            paint!!.setXfermode(type)
            canvas!!.drawPath(mPath, paint)
            paint!!.setXfermode(null)
            canvas.restoreToCount(la)
        }






        //第二种实现方法
        /*canvas!!.drawBitmap(mBackBitmap,0.toFloat(),0.toFloat(),null)
        drawPath()
        canvas!!.drawBitmap(mBeforeBitmap,0.toFloat(),0.toFloat(),null)*/

    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.action
        val x = event.x.toInt()
        val y = event.y.toInt()
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                mLastX = x
                mLastY = y
                mPath.moveTo(mLastX.toFloat(), mLastY.toFloat())
            }
            MotionEvent.ACTION_MOVE -> {

                val dx = Math.abs(x - mLastX)
                val dy = Math.abs(y - mLastY)

                if (dx > 3 || dy > 3)
                    mPath.lineTo(x.toFloat(), y.toFloat())

                mLastX = x
                mLastY = y
            }
        }
        invalidate()
        return true
    }


}



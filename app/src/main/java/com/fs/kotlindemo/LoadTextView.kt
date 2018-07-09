package com.fs.kotlindemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


/**
 * Created by Administrator on 2018/6/11.
 */
class LoadTextView :View{
    private var screenW: Int = 0
    private var screenH: Int = 0 //屏幕宽高
    private val mXfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private var backBitmap: Bitmap? = null
    private var mPaint: Paint? = null
    private var mCurTop: Int = 0
    private var mDynamicRect: Rect? = null
    private var mBitW: Int = 0
    private var mBitH: Int = 0
    constructor(context : Context, attrs: AttributeSet?=null) :super(context,attrs){
        screenW = ScreenUtil.getScreenW(context);
        screenH = ScreenUtil.getScreenH(context);
        init()
    }
    constructor(context : Context, attrs: AttributeSet?=null, defStyleAttr:Int) :super(context,attrs,defStyleAttr){
        screenW = ScreenUtil.getScreenW(context);
        screenH = ScreenUtil.getScreenH(context);
        init()
    }
    private fun init(){
        //画笔初始化：
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint!!.isFilterBitmap=true
        mPaint!!.isDither=true
        mPaint!!.color=Color.RED
        //背部图片的初始化
        backBitmap = BitmapFactory.decodeResource(this.resources,R.drawable.wz)
        mBitH = (backBitmap as Bitmap?)!!.getHeight();
        mBitW = (backBitmap as Bitmap?)!!.getWidth();
        //设置当前的高度
        mCurTop = mBitH;
        mDynamicRect = Rect(0, mBitH, mBitW, mBitH)  //初始化原图
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas != null) {
            var la=canvas.saveLayer(0.toFloat(),0.toFloat(),screenW.toFloat(),screenH.toFloat(),mPaint,Canvas.ALL_SAVE_FLAG)
            canvas.drawBitmap(backBitmap, 0.toFloat(), 0.toFloat(), null);// 绘制目标图
            mPaint!!.setXfermode(mXfermode)
            canvas.drawRect(mDynamicRect, mPaint);   //绘制源图
            mPaint!!.setXfermode(null)
            canvas.restoreToCount(la)
            mCurTop -= 2;
            if (mCurTop <= 0) {
                mCurTop = mBitH;
            }
            mDynamicRect!!.top=mCurTop
            invalidate()
        }
    }
}
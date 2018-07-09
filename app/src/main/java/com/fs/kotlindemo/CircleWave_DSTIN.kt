package com.fs.kotlindemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Created by Administrator on 2018/6/20.
 */
class CircleWave_DSTIN :View{


    private var mbitmap:Bitmap?=null
    private var bitW:Int?=0
    private var bitH:Int?=0
    private var paint:Paint ?=null
    private var jxtop:Int=0
    private var xf:PorterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private var hb:Canvas?=null
    var mpath:Path?=null

    var cz1:Float?=null
    var cz2:Float?=null

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

        paint = Paint()
        paint!!.isAntiAlias=true
        paint!!.color=resources.getColor(R.color.lanse)
        paint!!.setFilterBitmap(true);
        paint!!.setDither(true);
        paint!!.style=Paint.Style.FILL_AND_STROKE

        mbitmap = BitmapFactory.decodeResource(this.resources,R.drawable.text_shade)
        bitW= (mbitmap as Bitmap?)!!.width;
        bitH= (mbitmap as Bitmap?)!!.height
        jxtop= bitH as Int


        cz1=bitW!!.toFloat()/3
        cz2=bitW!!.toFloat()/3*2
//        mbitmap = Bitmap.createBitmap(bitW!!, bitH!!,Bitmap.Config.ARGB_8888)
//
//        hb= Canvas(mbitmap)
//
//        hb!!.drawBitmap(BitmapFactory.decodeResource(this.resources,R.drawable.text_shade),0f,0f,null)

//        mpath = Rect(0,jxtop, bitW!!, bitH!!)

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var ws=MeasureSpec.getSize(widthMeasureSpec)
        var wm=MeasureSpec.getMode(widthMeasureSpec)

        var hs=MeasureSpec.getSize(heightMeasureSpec)
        var hm=MeasureSpec.getMode(heightMeasureSpec)

        var width:Int?=0
        var height:Int?=0

        if(wm==MeasureSpec.EXACTLY){

            width=ws

        }else{

            width=bitW

        }

        if(hm==MeasureSpec.EXACTLY){
            height=hs
        }else{
            height=bitH
        }



        setMeasuredDimension(width!!, height!!)
    }

    private var isqh:Boolean?=false

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        Log.e("TAG","jxtop=="+jxtop)
        if (canvas != null) {
            var lay=canvas.saveLayer(0f,0f, width.toFloat(), height.toFloat(),null,Canvas.ALL_SAVE_FLAG)
            canvas!!.drawBitmap(mbitmap,0f,0f,null)
            paint!!.setXfermode(xf)

            var path:Path= Path()
            path.moveTo(0f,jxtop.toFloat())
            path.lineTo(0f,bitH!!.toFloat())
            path.lineTo(bitW!!.toFloat(),bitH!!.toFloat())
            path.lineTo(bitW!!.toFloat(),jxtop.toFloat())
            path.cubicTo(cz2!!.toFloat(),jxtop.toFloat()-200,cz1!!.toFloat(),jxtop.toFloat()+200,0f,jxtop.toFloat())

            canvas!!.drawPath(path,paint)
            paint!!.setXfermode(null)
            canvas.restoreToCount(lay)
        }
        jxtop-=2
        if(jxtop>0||jxtop>1){

        }else{
            jxtop= bitH!!

        }
        if(cz1!!.toFloat()==cz1!!.toFloat()+100){//开始往左侧走
            isqh=true
        }else if (cz1!!.toFloat()==cz1!!.toFloat()-100){
            isqh=false
        }
        if(isqh as Boolean){
            cz1=5f+cz1!!.toFloat();
            cz2=5f+cz2!!.toFloat();
        }else{
            cz1=cz1!!.toFloat()-5;
            cz2=cz2!!.toFloat()-5;
        }
        invalidate()
    }

}
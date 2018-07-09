package com.fs.kotlindemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Created by Administrator on 2018/6/22.
 */
class WaterRippleView :View{


    private var bitW:Int?=400
    private var bitH:Int?=400
    private var bj:Int=200
    private var lan_paint: Paint?=null
    private var bai_paint: Paint?=null
    private var jxtop:Int=0
    private var xf: PorterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    var pc:Int=50

    var xz:Int=0

    var kg:Boolean=false

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
        lan_paint = Paint()
        lan_paint!!.isAntiAlias=true
        lan_paint!!.color=resources.getColor(R.color.lanse)
        lan_paint!!.style=Paint.Style.FILL_AND_STROKE

        bai_paint = Paint()
        bai_paint!!.isAntiAlias=true
        bai_paint!!.color=Color.WHITE
        bai_paint!!.style=Paint.Style.FILL_AND_STROKE
        jxtop= bitH as Int
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

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        Log.e("TAG","jxtop=="+jxtop)

        if (canvas != null) {
            var lay=canvas.saveLayer(0f,0f, width.toFloat(), height.toFloat(),null,Canvas.ALL_SAVE_FLAG)
            canvas.drawCircle(bj.toFloat(), bj.toFloat(), bj.toFloat(),bai_paint)
            lan_paint!!.setXfermode(xf)

            var path:Path=Path();
            path.moveTo(0f, jxtop.toFloat())
            path.cubicTo(100f+xz,jxtop.toFloat()-100f,300f+xz,jxtop.toFloat()+100f,400f,jxtop.toFloat())
            path.lineTo(400f,400f)
            path.lineTo(0f,400f)
            path.lineTo(0f,jxtop.toFloat())
            canvas!!.drawPath(path,lan_paint)
            lan_paint!!.setXfermode(null)
            canvas.restoreToCount(lay)
        }
        jxtop-=2
        if(jxtop>0||jxtop>1){

        }else{
            jxtop= bitH!!
        }

        if(xz==0){

            kg=true

        }else if(xz==pc){
            kg=false
        }

        if(kg){
            xz+=2
        }else{
            xz-=2
        }
        invalidate()
    }


}
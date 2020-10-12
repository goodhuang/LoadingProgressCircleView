package com.example.loadingcircleview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class mProgress:View {
    var progress = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var cx = 0f
    private var cy = 0f
    private var radius = 0f
    var bgColor = resources.getColor(R.color.colorAccent,null)
        set(value) {
            field = value
            bgPaint.color = value
        }
    private var circleWidth = 50f
    private val bgPaint = Paint().apply {
        style = Paint.Style.STROKE
        color = bgColor
        strokeWidth = circleWidth
    }
    private var fgColor = resources.getColor(R.color.colorPrimary,null)
    private val fgPaint = Paint().apply {
        style = Paint.Style.STROKE
        color = fgColor
        strokeWidth = circleWidth
    }
    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 100f
        textAlign = Paint.Align.CENTER
    }
    constructor(context:Context):super(context,null,0){}
    constructor(context: Context,attrs:AttributeSet):super(context, attrs,0){
        parseContext(context,attrs)
    }
    constructor(context: Context,attrs:AttributeSet,style:Int):super(context, attrs,style){
        parseContext(context,attrs)
    }
    fun parseContext(context: Context,attrs: AttributeSet){
        context.obtainStyledAttributes(attrs,R.styleable.mProgress).apply {
            circleWidth = getFloat(R.styleable.mProgress_lineWidth,50f)
            bgColor = getColor(R.styleable.mProgress_bgCircleColor,Color.GREEN)
            recycle()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        cx = width/2f
        cy = height/2f
        radius = Math.min(width.toFloat(),height.toFloat())/2 - circleWidth
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //画背景圆
        canvas?.drawCircle(cx,cy,radius,bgPaint)
        //画进度圆
        canvas?.drawArc(cx - radius,
                        cy - radius,
                        cx + radius,
                        cy + radius,
                        270f,progress,false,fgPaint)
        //画进度文字
        val text = "${(progress/3.6).toInt()}%"
        val metrics = textPaint.getFontMetrics()
        val space = (metrics.descent - metrics.ascent)/2 - metrics.descent
        canvas?.drawText(text,cx,cy + space,textPaint)
    }

}
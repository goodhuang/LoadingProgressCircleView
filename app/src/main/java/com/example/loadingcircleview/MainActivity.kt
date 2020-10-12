package com.example.loadingcircleview

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ValueAnimator.ofFloat(0f,360f).apply {
            duration = 10000
            addUpdateListener {
                mProgressView.progress = it.animatedValue as Float
            }
            start()
        }
    }
}
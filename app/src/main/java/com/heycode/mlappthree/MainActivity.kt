package com.heycode.mlappthree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.nex3z.fingerpaintview.FingerPaintView

class MainActivity : AppCompatActivity() {
    private lateinit var clear: Button
    private lateinit var predict: Button
    private lateinit var draw_area: FingerPaintView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clear = findViewById(R.id.clear_value_button)
        predict = findViewById(R.id.predict_button)
        draw_area = findViewById(R.id.draw_area)

        clear.setOnClickListener {
            draw_area.clear()
        }
    }
}
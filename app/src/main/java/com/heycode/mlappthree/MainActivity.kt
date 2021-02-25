package com.heycode.mlappthree

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nex3z.fingerpaintview.FingerPaintView
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var drawArea: FingerPaintView
    private lateinit var probability: TextView
    private lateinit var timeTaken: TextView
    private lateinit var predictedResult: TextView

    private lateinit var classifier: Classifier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawArea = findViewById(R.id.draw_area)
        probability = findViewById(R.id.result_probability)
        predictedResult = findViewById(R.id.result_text_view)
        timeTaken = findViewById(R.id.time_taken)

        try {
            classifier = Classifier(this)
        } catch (e: IOException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }

        findViewById<Button>(R.id.clear_value_button).setOnClickListener {
            drawArea.clear()
        }
        findViewById<Button>(R.id.predict_button).setOnClickListener {
            val bitmap: Bitmap =
                drawArea.exportToBitmap(Classifier.IMG_WIDTH, Classifier.IMG_HEIGHT)
            val res: Result = classifier.classify(bitmap)
            probability.text = "Accuracy: " + res.probability
            timeTaken.text = "TimeCost: " + res.timeCost
            predictedResult.text = res.number.toString()
        }
    }
}

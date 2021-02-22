package com.heycode.mlappthree

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.nex3z.fingerpaintview.FingerPaintView
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.FileChannel

class MainActivity : AppCompatActivity() {
    private lateinit var draw_area: FingerPaintView

    private lateinit var interpreter: Interpreter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        draw_area = findViewById(R.id.draw_area)

        try {
            interpreter = Interpreter(loadModelFile(), null)

        } catch (e: Exception) {
            println(e.stackTrace)
        }

        findViewById<Button>(R.id.clear_value_button).setOnClickListener {
            draw_area.clear()
        }
        findViewById<Button>(R.id.predict_button).setOnClickListener {

        }
    }

    private fun doInference(`val`: String): Float {
        val input = FloatArray(1)
        input[0] = `val`.toFloat()
        val output = Array(1) { FloatArray(1) }
        interpreter.run(input, output)
        return output[0][0]
    }

    @Throws(IOException::class)
    private fun loadModelFile(): ByteBuffer {
        val assetFileDescriptor = this.assets.openFd("digit.tflite")
        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel: FileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val length = assetFileDescriptor.length
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, length)
    }
}
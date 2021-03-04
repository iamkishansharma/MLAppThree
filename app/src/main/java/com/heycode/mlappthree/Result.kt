package com.heycode.mlappthree

class Result(probs: FloatArray, timeCost: Long) {
    val number: Int = argmax(probs)
    val probability: Float = probs[number]
    val timeCost: Long = timeCost

    companion object {
        private fun argmax(probs: FloatArray): Int {
            var maxIdx = -1
            var maxProb = 0.0f
            for (i in probs.indices) {
                if (probs[i] > maxProb) {
                    maxProb = probs[i]
                    maxIdx = i
                }
            }
            return maxIdx
        }
    }

}
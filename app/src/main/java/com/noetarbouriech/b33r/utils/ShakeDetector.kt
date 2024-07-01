package com.noetarbouriech.b33r.utils

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class ShakeDetector(context: Context) : SensorEventListener {

    private val sensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private var lastUpdate: Long = 0
    private var lastX: Float = 0f
    private var lastY: Float = 0f
    private var lastZ: Float = 0f

    private var listener: (() -> Unit)? = null

    init {
        start()
    }

    fun setOnShakeListener(listener: () -> Unit) {
        this.listener = listener
    }

    private fun start() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val currentTime = System.currentTimeMillis()
            val timeDifference = currentTime - lastUpdate

            if (timeDifference > SHAKE_THRESHOLD_INTERVAL) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                val accelerationDifference = Math.abs(x + y + z - lastX - lastY - lastZ) / timeDifference * 10000

                if (accelerationDifference > SHAKE_THRESHOLD) {
                    listener?.invoke()
                }

                lastX = x
                lastY = y
                lastZ = z
                lastUpdate = currentTime
            }
        }
    }

    companion object {
        private const val SHAKE_THRESHOLD = 800
        private const val SHAKE_THRESHOLD_INTERVAL = 100
    }
}

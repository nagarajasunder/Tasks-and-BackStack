package com.geekydroid.tasksandbackstack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.geekydroid.tasksandbackstack.databinding.ActivityBSingleTopBinding

private const val TAG = "SingleTopActivityB"
class SingleTopActivityB : AppCompatActivity() {

    private lateinit var binding:ActivityBSingleTopBinding
    private lateinit var launchModeStr:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_b_single_top)
        launchModeStr = intent.getStringExtra("LAUNCH_MODE")?:""

        binding.btnLaunchA.setOnClickListener {
            val intent = Intent(this,ActivityA::class.java)
            intent.putExtra("LAUNCH_MODE",launchModeStr)
            startActivity(intent)
        }

        binding.btnLaunchC.setOnClickListener {
            val intent = Intent(this,ActivityC::class.java)
            intent.putExtra("LAUNCH_MODE",launchModeStr)
            startActivity(intent)
        }

        binding.btnLaunchB.setOnClickListener {
            val activity = when(launchModeStr)
            {
                LAUNCH_MODE.SINGLE_TOP.toString() -> SingleTopActivityB::class.java
                LAUNCH_MODE.SINGLE_INSTANCE.toString() -> ActivityBSingleInstance::class.java
                LAUNCH_MODE.SINGLE_TASK.toString() -> SingleTaskActivityB::class.java
                LAUNCH_MODE.SINGLE_INSTANCE_PER_TASK.toString() -> SingleInstancePerTaskActivityB::class.java
                else -> MainActivity::class.java
            }

            val intent = Intent(this,activity)
            intent.putExtra("LAUNCH_MODE",launchModeStr)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent: called")
    }
}

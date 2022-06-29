package com.geekydroid.tasksandbackstack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.geekydroid.tasksandbackstack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedLaunchMode: LAUNCH_MODE? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.launchMode.setOnCheckedChangeListener { _, checkedId ->

            selectedLaunchMode = when (checkedId) {
                R.id.btn_single_top -> LAUNCH_MODE.SINGLE_TOP
                R.id.btn_single_instance -> LAUNCH_MODE.SINGLE_INSTANCE
                R.id.btn_single_task -> LAUNCH_MODE.SINGLE_TASK
                R.id.btn_single_instance_per_task -> LAUNCH_MODE.SINGLE_INSTANCE_PER_TASK
                else -> LAUNCH_MODE.SINGLE_TOP
            }
        }

        binding.btnGo.setOnClickListener {
            selectedLaunchMode?.let {
                val intent = Intent(this, ActivityA::class.java)
                intent.putExtra("LAUNCH_MODE",it.toString())
                startActivity(intent)
            }
        }
    }
}

enum class LAUNCH_MODE {
    SINGLE_TOP,
    SINGLE_INSTANCE,
    SINGLE_TASK,
    SINGLE_INSTANCE_PER_TASK
}
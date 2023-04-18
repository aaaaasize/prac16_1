package com.example.sensorinfo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sensorinfo.adapters.RecyclerView.SensorRVAdapter
import com.example.sensorinfo.viewModel.SensorViewModel

class MainActivity : AppCompatActivity() {
    lateinit var rvSensors: RecyclerView
    lateinit var spnColour: Spinner

    private val viewModel: SensorViewModel by lazy {
        ViewModelProvider(this).get(SensorViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SensorRVAdapter(this, viewModel.sensorList)
        rvSensors = findViewById(R.id.rvSensors)
        rvSensors.layoutManager = LinearLayoutManager(this)
        rvSensors.adapter = adapter

        val rvListener = object : SensorRVAdapter.ItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(this@MainActivity, "position:$position", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        adapter.setClickListener(rvListener)

        spnColour = findViewById(R.id.spnColour)
        val spnAdapter = ArrayAdapter.createFromResource(
            this, R.array.coloursArray, android.R.layout.simple_list_item_1
        )
        spnColour.adapter = spnAdapter

        spnColour.onItemSelectedListener = spnColourListener
    }

    val spnColourListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when (position) {
                0 -> rvSensors.setBackgroundColor(Color.BLUE)
                1 -> rvSensors.setBackgroundColor(Color.YELLOW)
                2 -> rvSensors.setBackgroundColor(Color.GREEN)
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }
}
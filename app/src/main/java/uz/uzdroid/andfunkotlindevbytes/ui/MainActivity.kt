package uz.uzdroid.andfunkotlindevbytes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import uz.uzdroid.andfunkotlindevbytes.adapter.InfoAdapter
import uz.uzdroid.andfunkotlindevbytes.database.InfoDatabase
import uz.uzdroid.andfunkotlindevbytes.databinding.ActivityMainBinding
import uz.uzdroid.andfunkotlindevbytes.viewmodel.InfoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var infoViewModel: InfoViewModel

    private lateinit var infoAdapter: InfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = InfoDatabase.getInstance(applicationContext)

        val infoDao = database.infoDao()

        val viewModelFactory = InfoViewModel.InfoViewModelFactory(infoDao)

        infoViewModel = ViewModelProvider(this, viewModelFactory).get(InfoViewModel::class.java)

        infoAdapter = InfoAdapter()



        infoViewModel.infoVideos.observe(this, Observer {
            infoAdapter.info = it
            infoAdapter.notifyDataSetChanged()
        })

        binding.recyclerView.apply {
            adapter = infoAdapter
            layoutManager = LinearLayoutManager(applicationContext)}

    }
}
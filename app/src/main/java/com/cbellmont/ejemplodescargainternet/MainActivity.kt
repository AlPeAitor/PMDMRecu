package com.cbellmont.ejemplodescargainternet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cbellmont.ejemplodescargainternet.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.cbellmont.ejemplodescargainternet.PeopleAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface MainActivityInterface {
    suspend fun onFilmsReceived(listPeople : List<People>)
}

// IMPORTANT: Passing the activity to a the receiver is not a good practice, it may cause issues
// with the activity-s lifecycle. We are doing it just to keep the focus on the target of this example
/*
class MainActivity : AppCompatActivity(), MainActivityInterface {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO){
            GetAllFilms.send(this@MainActivity)
        }
    }

    override suspend fun onFilmsReceived(listPeople : List<People>) {
        withContext(Dispatchers.Main){
            binding.tvFilms.text = ""
            listPeople.forEach {
                binding.tvFilms.append(it.toString())
            }
        }

    }
}*/

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var model: ViewModelActivityMain
    lateinit var adapter: PeopleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        model = ViewModelProvider(this).get(ViewModelActivityMain::class.java)

        createRecyclerView()

        GlobalScope.launch(Dispatchers.IO) {
            val resultado = model.getResults().await()
            withContext(Dispatchers.Main) {
                model.changeLoading(binding.root)
            }
            adapter.setCoinList(resultado)
        }
    }

    fun createRecyclerView() {
        adapter = PeopleAdapter()
        binding.recyclerViewCoins.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCoins.adapter = adapter

    }
}
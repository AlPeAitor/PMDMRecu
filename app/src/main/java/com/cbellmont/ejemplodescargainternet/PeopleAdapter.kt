package com.cbellmont.ejemplodescargainternet

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cbellmont.ejemplodescargainternet.People
import com.cbellmont.ejemplodescargainternet.databinding.ActivityMainBinding
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PeopleAdapter (): RecyclerView.Adapter<PeopleAdapter.peopleAdapterViewHolder>(){

    var coinList : List<People>? = null
    lateinit var recyclerView: RecyclerView

    class peopleAdapterViewHolder(var view: ActivityMainBinding): RecyclerView.ViewHolder(view.root)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): peopleAdapterViewHolder {
        val view = LayoutInflater.from(parent.context)
        val adaptador = ActivityMainBinding.inflate(view,parent,false)
        return peopleAdapterViewHolder(adaptador)
    }

    override fun onBindViewHolder(holder: peopleAdapterViewHolder, position: Int) {
        coinList?.let{
            holder.view.people.text = it[position].name
            holder.view.especie.text = it[position].species
        }
        /*holder.itemView.setOnClickListener {
            val posicion = recyclerView.getChildLayoutPosition(it)

            coinList?.let{ itCoin ->
                CoinSpecificActivity.createSpecificActivity(it.context,itCoin[posicion].id)
            }
        }*/
    }

    override fun getItemCount(): Int {
        coinList?.let {
            return it.size
        }
        return 0
    }

    suspend fun setCoinList(lista: List<People>?){
        coinList = lista
        withContext(Dispatchers.Main){
            notifyDataSetChanged()
        }
    }
}
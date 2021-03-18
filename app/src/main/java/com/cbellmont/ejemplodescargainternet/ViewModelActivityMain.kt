package com.cbellmont.ejemplodescargainternet

import android.view.View
import androidx.lifecycle.ViewModel
/*import com.example.trabajo_api.DownloaderManager
import kotlinx.android.synthetic.main.activity_main.view.**/
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ViewModelActivityMain: ViewModel() {
    suspend fun getResults() = GlobalScope.async(Dispatchers.IO){
        val data = DownloaderManager.downloadAllPeople()
        data
    }
    fun changeLoading(view : View){
       /* view.progressBar_carga.visibility = View.GONE
        view.tv_loading.visibility = View.GONE*/
    }
}
package uz.uzdroid.andfunkotlindevbytes.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.uzdroid.andfunkotlindevbytes.database.InfoDao
import uz.uzdroid.andfunkotlindevbytes.model.Videos
import uz.uzdroid.andfunkotlindevbytes.repository.InfoRepository
import uz.uzdroid.andfunkotlindevbytes.service.InfoApi

class InfoViewModel(val infoDao: InfoDao) : ViewModel() {

    val job = Job()
    val viewModelScope = CoroutineScope(Dispatchers.Main + job)

    val repo = InfoRepository(infoDao)


    val infoVideos = repo.infoVideos

    init {

        fetchInfo()

    }

    private fun fetchInfo() {



        viewModelScope.launch {

            repo.fetchVideos()


        }

    }

    class InfoViewModelFactory(val infoDao: InfoDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(InfoViewModel::class.java)) {
                return InfoViewModel(infoDao = infoDao) as T
            }
            throw IllegalArgumentException("infoViewModeldan boshqasini tekshirdi")

        }


    }

}

package uz.uzdroid.andfunkotlindevbytes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import uz.uzdroid.andfunkotlindevbytes.database.InfoDao
import uz.uzdroid.andfunkotlindevbytes.repository.InfoRepository

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

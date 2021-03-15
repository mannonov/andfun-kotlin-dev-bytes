package uz.uzdroid.andfunkotlindevbytes.repository

import kotlinx.coroutines.*
import uz.uzdroid.andfunkotlindevbytes.database.InfoDao
import uz.uzdroid.andfunkotlindevbytes.service.InfoApi

class InfoRepository(val infoDao: InfoDao) {

    val infoVideos = infoDao.queryAllVideo()

    suspend fun fetchVideos() {


        withContext(Dispatchers.IO) {

            val slowResultOfVideos = InfoApi.infoService.getInfo().await()
            infoDao.insertVideo(*slowResultOfVideos.videos.toTypedArray())


        }

    }

}
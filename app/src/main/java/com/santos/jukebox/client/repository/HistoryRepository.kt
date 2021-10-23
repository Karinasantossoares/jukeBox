package com.santos.jukebox.client.repository

import com.santos.jukebox.client.data.*
import com.santos.jukebox.client.remote.HistoryFirebase
import com.santos.jukebox.establishment.data.MusicEstablishmentResponse

class HistoryRepository(
    private val firebase: HistoryFirebase
) {

    fun addMusicQueue(music: Music) = firebase.addMusicHistory(music)

    fun findAllHistory(
        success: (TopMusicsResponse) -> Unit,
        error: (Throwable) -> Unit
    ) {
        firebase.findAllHistory(
            error = error,
            success = { musicList ->
                val graphMusic = getGraphMusic(musicList)
                val top10Musics = getTop10Musics(musicList)
                success.invoke(TopMusicsResponse(top10Musics, graphMusic))
            }
        )
    }

    fun deleteAll(success: () -> Unit, error: (Throwable) -> Unit) {
        firebase.deleteAll(success, error)
    }

    private fun getTop10Musics(musicList: List<Music>): List<Music> {
        val topMusics: MutableList<MusicCount> = mutableListOf()

        musicList.forEach { currentMusic ->
            val topMusic = topMusics.find { it.music == currentMusic }
            if (topMusic == null) {
                topMusics.add(MusicCount(currentMusic, 1))
            } else {
                topMusic.count++
            }
        }
        topMusics.sortByDescending { it.count }

        return topMusics.take(10).map { it.music }
    }

    private fun getGraphMusic(musicList: List<Music>): List<GraphMusic> {
        val response = mutableListOf<MusicResponse>()
        musicList.forEach { currentMusic ->
            currentMusic.types.forEach { currentType ->
                if (response.find { it.type == currentType } == null) {
                    response.add(MusicResponse(currentType, mutableListOf()))
                }

                response.find { it.type == currentType }?.musics?.add(currentMusic)
            }
        }

        response.sortBy { it.musics.size }
        val totalMusics = response.map { it.musics.size }.sum().toFloat()
        return response.map {
            GraphMusic(it.type, (it.musics.size.toFloat() * 100.0F) / totalMusics)
        }.take(6)
    }

}
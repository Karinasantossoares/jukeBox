package com.santos.jukebox.client.repository

import com.santos.jukebox.client.data.GraphMusic
import com.santos.jukebox.client.data.Music
import com.santos.jukebox.client.data.MusicResponse
import com.santos.jukebox.client.data.TopMusicsResponse
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

    private fun getTop10Musics(musicList: List<Music>): List<Music> {
        return musicList
            .sortedWith(compareBy { it.requestName })
            .take(10)
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
        val totalMusics = response.map { it.musics.size }.sum()
        return response.map {
            GraphMusic(it.type, (it.musics.size * 100) / totalMusics)
        }
    }

}
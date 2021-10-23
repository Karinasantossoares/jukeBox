package com.santos.jukebox.client.data


data class TopMusicsResponse(
    val top10Musics: List<Music>,
    val topTypes: List<GraphMusic>
)

data class GraphMusic(
    val name: String,
    val percentage: Int
)
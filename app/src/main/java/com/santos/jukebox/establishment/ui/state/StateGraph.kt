package com.santos.jukebox.establishment.ui.state

import com.github.mikephil.charting.data.PieEntry
import com.santos.jukebox.client.data.Music

sealed class StateGraph {
    data class SuccessTopMusics(val musics: List<Music>) : StateGraph()
    data class SuccessGraph(val chartData: List<PieEntry>) : StateGraph()
    object SuccessGraphEmpty : StateGraph()
    object SuccessTopMusicsEmpty : StateGraph()
    data class ShowMessageId(val idMusic: Int) : StateGraph()
    object Loading : StateGraph()
}
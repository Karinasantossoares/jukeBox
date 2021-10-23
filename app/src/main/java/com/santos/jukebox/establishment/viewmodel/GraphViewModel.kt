package com.santos.jukebox.establishment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intrusoft.scatter.ChartData
import com.santos.jukebox.R
import com.santos.jukebox.client.data.GraphMusic
import com.santos.jukebox.establishment.data.MusicEstablishmentResponse
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.ui.state.EventListMusic
import com.santos.jukebox.establishment.ui.state.StateGraph
import com.santos.jukebox.establishment.ui.state.StateListMusic
import com.santos.jukebox.establishment.useCase.GraphUseCase

internal class GraphViewModel(
    private val useCaseMusic: GraphUseCase,
) : ViewModel() {

    private var _stateLiveData = MutableLiveData<StateGraph>()
    val stateLiveData: LiveData<StateGraph>
        get() = _stateLiveData

    init {
        findAllHistory()
    }

    private fun findAllHistory() {
        _stateLiveData.value = StateGraph.Loading
        useCaseMusic.findAllHistory(
            success = {
                if (it.top10Musics.isEmpty()) {
                    notifyScreen(StateGraph.SuccessTopMusicsEmpty)
                } else {
                    notifyScreen(StateGraph.SuccessTopMusics(it.top10Musics))
                }

                if (it.topTypes.isEmpty()) {
                    notifyScreen(StateGraph.SuccessGraphEmpty)
                } else {
                    notifyScreen(StateGraph.SuccessGraph(it.topTypes.map { graph ->
                        ChartData(graph.name, graph.percentage.toFloat())
                    }))
                }
            },
            error = {
                notifyScreen(StateGraph.ShowMessageId(R.string.error_message_graph))
            }
        )
    }

    private fun notifyScreen(value: StateGraph) {
        _stateLiveData.value = value
    }

}
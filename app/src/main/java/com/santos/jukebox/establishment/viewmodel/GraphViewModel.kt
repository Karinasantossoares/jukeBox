package com.santos.jukebox.establishment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.PieEntry
import com.santos.jukebox.R
import com.santos.jukebox.establishment.ui.state.StateGraph
import com.santos.jukebox.establishment.useCase.GraphUseCase

internal class GraphViewModel(
    private val graphUseCase: GraphUseCase,
) : ViewModel() {

    private var _stateLiveData = MutableLiveData<StateGraph>()
    val stateLiveData: LiveData<StateGraph>
        get() = _stateLiveData

    init {
        findAllHistory()
    }

    private fun findAllHistory() {
        _stateLiveData.value = StateGraph.Loading
        graphUseCase.findAllHistory(
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
                        PieEntry(
                            graph.percentage,
                            graph.name
                        )
                    }))
                }
            },
            error = {
                notifyScreen(StateGraph.ShowMessageId(R.string.error_message_graph))
            }
        )
    }

    fun clearAll() {
        graphUseCase.deleteAll(
            success = {
                notifyScreen(StateGraph.ShowMessageId(R.string.success_delete_message_graph))
            },
            error = {
                notifyScreen(StateGraph.ShowMessageId(R.string.error_delete_message_graph))
            }
        )
    }

    private fun notifyScreen(value: StateGraph) {
        _stateLiveData.value = value
    }

}
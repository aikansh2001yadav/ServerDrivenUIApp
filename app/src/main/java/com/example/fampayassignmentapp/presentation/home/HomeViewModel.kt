package com.example.fampayassignmentapp.presentation.home

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fampayassignmentapp.commons.ErrorResponse
import com.example.fampayassignmentapp.commons.Failure
import com.example.fampayassignmentapp.commons.Resource
import com.example.fampayassignmentapp.domain.models.CardGroup
import com.example.fampayassignmentapp.domain.usecase.GetCardGroupsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getCardGroupsUseCase: GetCardGroupsUseCase) :
    ViewModel() {
    private val _cardGroups = MutableLiveData<List<CardGroup>>()
    val cardGroups: LiveData<List<CardGroup>>
        get() = _cardGroups

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _error = MutableLiveData<Pair<Failure, ErrorResponse>?>()
    val error: LiveData<Pair<Failure, ErrorResponse>?>
        get() = _error

    private val _cardDismissed = MutableLiveData<Pair<Boolean, String>>()
    val cardDismissed: LiveData<Pair<Boolean, String>>
        get() = _cardDismissed

    private val _browserIntent = MutableLiveData<Intent>()
    val browserIntent: LiveData<Intent> get() = _browserIntent

    init {
        getCardGroups()
    }

    fun getCardGroups() {
        getCardGroupsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _isLoading.value = true
                }
                is Resource.Error -> {
                    _isLoading.value = false
                    _error.value = result.error
                }
                is Resource.Success -> {
                    _isLoading.value = false
                    result.data?.let {
                        _cardGroups.value = it
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onDismissOrRemindLater(dismissed: Boolean, cardName: String) {

        val currentList = if (cardGroups.value != null) {
            cardGroups.value!!.toMutableList()
        } else {
            mutableListOf()
        }

        currentList.forEachIndexed { index, cardGroup ->
            if (cardGroup.designType == "HC3") {
                for (card in cardGroup.cards!!) {
                    if (card!!.name.equals(cardName, ignoreCase = true)) {
                        _cardDismissed.value = Pair<Boolean, String>(dismissed, cardName)
                        currentList[index].cards!!.remove(card)
                        _cardGroups.value = currentList
                    }
                }
            }
        }
    }
}
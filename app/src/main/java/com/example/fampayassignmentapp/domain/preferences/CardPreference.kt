package com.example.fampayassignmentapp.domain.preferences

const val CARD_PREFS = "CARD_PREFS"
const val CARD_TEMP_PREFS = "CARD_TEMP_PREFS"

interface CardPreference {
    fun addCard(name: String)
    fun removeCard(name: String)
    fun getCardList(): HashSet<String>
    fun removeCardItems(cardList: List<String>)
    fun addCardTemp(name : String)
    fun removeTempCardList()
}
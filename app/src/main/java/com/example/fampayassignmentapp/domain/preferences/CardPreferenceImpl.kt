package com.example.fampayassignmentapp.domain.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CardPreferenceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CardPreference {

    private var pref: SharedPreferences
    private var tempPref: SharedPreferences
    private var editor: SharedPreferences.Editor
    private var tempEditor: SharedPreferences.Editor

    private val PRIVATE_MODE = 0

    init {
        pref = context.getSharedPreferences(CARD_PREFS, PRIVATE_MODE)
        tempPref = context.getSharedPreferences(CARD_TEMP_PREFS, PRIVATE_MODE)
        editor = pref.edit()
        tempEditor = tempPref.edit()
    }

    override fun addCard(name: String) {
        val list = pref.getStringSet(CARD_KEY, hashSetOf())
        val copyList = list!!.toMutableSet()
        copyList.add(name)
        editor.putStringSet(CARD_KEY, copyList).apply()
    }

    override fun removeCard(name: String) {
        val list = pref.getStringSet(CARD_KEY, hashSetOf())
        val copyList = list!!.toMutableSet()
        copyList.remove(name)
        editor.putStringSet(CARD_KEY, copyList).apply()
    }

    override fun getCardList(): HashSet<String> {
        return pref.getStringSet(CARD_KEY, hashSetOf())!!.toHashSet()
    }

    override fun removeCardItems(cardList: List<String>) {
        val list = pref.getStringSet(CARD_KEY, hashSetOf())
        val copyList = list!!.toMutableSet()
        copyList.removeAll(cardList.toSet())
        editor.putStringSet(CARD_KEY, copyList).apply()
    }

    override fun addCardTemp(name: String) {
        val list = tempPref.getStringSet(CARD_TEMP_KEY, hashSetOf())
        val copyList = list!!.toMutableSet()
        copyList.add(name)
        tempEditor.putStringSet(CARD_TEMP_KEY, copyList).apply()
    }

    override fun removeTempCardList() {
        val list = pref.getStringSet(CARD_KEY, hashSetOf())!!
        val tempList = tempPref.getStringSet(CARD_TEMP_KEY, hashSetOf())

        val copyList = list.toMutableSet()

        copyList.removeAll(tempList!!.toSet())
        editor.putStringSet(CARD_KEY, copyList).apply()
        tempEditor.putStringSet(CARD_TEMP_KEY, hashSetOf()).apply()
    }

    companion object {
        const val CARD_KEY = "CARD_KEY"
        const val CARD_TEMP_KEY = "CARD_TEMP_KEY"
    }
}

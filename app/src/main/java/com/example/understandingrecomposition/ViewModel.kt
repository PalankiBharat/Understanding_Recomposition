package com.example.understandingrecomposition

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class MyViewModel {
    private val _uiStates = MutableStateFlow(SuperheroListingUIStates())
    val uiStates = _uiStates.asStateFlow()

    fun updateLoading(){
        _uiStates.update { it.copy(loading = !_uiStates.value.loading) }
    }

    fun changeEmail()
    {
        val randomString = randomString(5)
        _uiStates.update { it.copy(email = randomString) }

    }

    fun changeUser()
    {
        val randomUser = randomUser()
        _uiStates.value = _uiStates.value.copy(user = randomUser)

    }

    fun changelistRandomly()
    {
        val list = arrayListOf<String>()
       repeat(10){
           list.add(randomString(5))
       }
        _uiStates.value = _uiStates.value.copy(list = list)

    }

    fun changeUserNameOnly()
    {
        val user = _uiStates.value.user.copy(name = randomString(10))
        _uiStates.value = _uiStates.value.copy(user = user)

    }



    fun randomString(length: Int): String =
        CharArray(length) { validChars.random() }.concatToString()

    fun randomUser():User{
        return User(
            id = randomString(10), name = randomString(9), roll = Random.nextInt().toString()
        )
    }


    val validChars: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
}

data class SuperheroListingUIStates(
    var loading: Boolean = false,
    val user: User = User(id = "asdasd","Bharat","1"),
    var email:String= "",
    val error: String = "",
    val list: List<String> = emptyList()
)
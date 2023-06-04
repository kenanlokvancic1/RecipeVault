package com.example.recipevault.database

sealed interface UserEvent{

    object SaveUser: UserEvent
    data class SetUsername(val username: String) : UserEvent
    data class SetEmail(val username: String) : UserEvent
    data class SetPassword(val username: String) : UserEvent
}
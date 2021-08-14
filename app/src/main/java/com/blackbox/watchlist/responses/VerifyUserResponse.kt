package com.blackbox.watchlist.responses

import kotlinx.serialization.Serializable

@Serializable
data class VerifyUserResponse(
    val login : String? = null
)
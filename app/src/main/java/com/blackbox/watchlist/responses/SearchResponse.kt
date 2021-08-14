package com.blackbox.watchlist.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class SearchResponse(
    @SerialName("total_count")
    val totalCount : Int? = null,
    @SerialName("incomplete_results")
    val incompleteResults : Boolean? = null,
    val items : List<SearchData>? = null
):Parcelable

@Serializable
@Parcelize
data class SearchData(
    @SerialName("full_name")
    val fullName : String? = null,
    @SerialName("name")
    val name :String? = null,
    val owner:LoginData? = null,
    @SerialName("stargazers_count")
    val starGazersCount:Int? = null,
    @SerialName("watchers_count")
    val watchersCount :Int? = null
):Parcelable

@Serializable
@Parcelize
data class LoginData (
    val login :String? = null
):Parcelable

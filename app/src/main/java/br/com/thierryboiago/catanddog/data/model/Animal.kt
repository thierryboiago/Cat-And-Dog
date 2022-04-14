package br.com.thierryboiago.catanddog.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Animal (

    @SerializedName("id")
    @Expose
    val id:String,

    @SerializedName("url")
    @Expose
    val url:String
        )
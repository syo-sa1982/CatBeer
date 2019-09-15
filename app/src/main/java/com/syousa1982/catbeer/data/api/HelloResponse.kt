package com.syousa1982.catbeer.data.api

import com.squareup.moshi.Json

data class HelloResponse(
    @Json(name = "body") val body: String
)
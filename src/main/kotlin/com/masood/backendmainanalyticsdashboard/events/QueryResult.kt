package com.masood.backendmainanalyticsdashboard.events


data class QueryResult(
    val status: String,
    val data: List<List<Any?>>? = null,
    val error: String? = null
)

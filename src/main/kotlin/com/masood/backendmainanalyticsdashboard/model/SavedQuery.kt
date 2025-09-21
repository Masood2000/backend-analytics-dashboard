package com.masood.backendmainanalyticsdashboard.model

import jakarta.persistence.*

@Entity
data class SavedQuery(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val queryText: String = ""
)

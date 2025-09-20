package com.masood.backendmainanalyticsdashboard.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table


/***
 * Passenger Model 
 */
@Entity
@Table(name = "passengers")
data class Passenger(
    @Id
    @Column(name = "PassengerId") // matching the csv column
    val passengerId: Int = 0,

    @Column(name = "Survived")
    val survived: Int,

    @Column(name = "Pclass")
    val pclass: Int,

    @Column(name = "Name")
    val name: String,

    @Column(name = "Sex")
    val sex: String,

    @Column(name = "Age")
    val age: Double?,

    @Column(name = "SibSp")
    val sibSp: Int,

    @Column(name = "Parch")
    val parch: Int,

    @Column(name = "Ticket")
    val ticket: String,

    @Column(name = "Fare")
    val fare: Double,

    @Column(name = "Cabin")
    val cabin: String?,

    @Column(name = "Embarked")
    val embarked: String?
)

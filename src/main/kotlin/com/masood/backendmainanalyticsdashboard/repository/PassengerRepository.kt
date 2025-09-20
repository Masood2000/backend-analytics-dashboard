package com.masood.backendmainanalyticsdashboard.repository

import com.masood.backendmainanalyticsdashboard.model.Passenger
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


/***
 * Repository for populating the h2 database...
 */
@Repository
interface PassengerRepository : JpaRepository<Passenger, Int>

package com.masood.backendmainanalyticsdashboard.repository

import com.masood.backendmainanalyticsdashboard.model.SavedQuery
import org.springframework.data.jpa.repository.JpaRepository

interface SavedQueryRepository : JpaRepository<SavedQuery, Long>



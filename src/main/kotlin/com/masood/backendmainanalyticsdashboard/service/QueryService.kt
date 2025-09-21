package com.masood.backendmainanalyticsdashboard.service


import com.masood.backendmainanalyticsdashboard.model.SavedQuery
import com.masood.backendmainanalyticsdashboard.repository.SavedQueryRepository
import org.springframework.stereotype.Service

@Service
class QueryService( private val repository: SavedQueryRepository, ) {


    fun saveQuery(query: String): Long {

        val saved = repository.save(SavedQuery(queryText = query))
        return saved.id

    }

    fun getAllQueries(): List<SavedQuery> = repository.findAll()

}

package com.masood.backendmainanalyticsdashboard.service


import com.masood.backendmainanalyticsdashboard.model.SavedQuery
import com.masood.backendmainanalyticsdashboard.repository.SavedQueryRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class QueryService( private val repository: SavedQueryRepository, private val jdbcTemplate: JdbcTemplate) {


    fun saveQuery(query: String): Long {

        val saved = repository.save(SavedQuery(queryText = query))
        return saved.id

    }

    fun getAllQueries(): List<SavedQuery> = repository.findAll()

    fun executeQuery(id: Long): List<List<Any?>> {
        val saved = repository.findById(id).orElseThrow { IllegalArgumentException("Query not found") }
        return jdbcTemplate.query(saved.queryText) { result, _ ->
            (1..result.metaData.columnCount).map { result.getObject(it) }
        }
    }

}

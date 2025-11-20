package com.masood.backendmainanalyticsdashboard.service


import com.masood.backendmainanalyticsdashboard.events.QueryResult
import com.masood.backendmainanalyticsdashboard.model.SavedQuery
import com.masood.backendmainanalyticsdashboard.repository.SavedQueryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap


@Service
class QueryService( private val repository: SavedQueryRepository, private val jdbcTemplate: JdbcTemplate) {

    private val forbidden = listOf( "insert", "update", "delete", "drop", "alter", "create", "truncate", "merge", "replace" )

    // Store results or status
    private val results = ConcurrentHashMap<Long, QueryResult>()

    fun saveQuery(query: String): Long {

        val lowered = query.lowercase()
        if (!lowered.startsWith("select")) {
            throw IllegalArgumentException("Only SELECT queries are allowed")
        }
        if (forbidden.any { lowered.contains(it) }) {
            throw IllegalArgumentException("Query contains forbidden operation: modification not allowed")
        }

        val saved = repository.save(SavedQuery(queryText = query))
        return saved.id

    }

    fun getAllQueries(): List<SavedQuery> = repository.findAll()

    suspend fun executeQuery(id: Long): QueryResult = withContext(Dispatchers.IO) {
        try {
            // Find the saved query
            val savedQuery = repository.findById(id).orElseThrow {
                IllegalArgumentException("Query not found")
            }

            // Execute the SQL query
            val data = mutableListOf<List<Any?>>()
            val resultSet = jdbcTemplate.queryForRowSet(savedQuery.queryText)

            while (resultSet.next()) {
                val row = mutableListOf<Any?>()
                for (i in 1..resultSet.metaData.columnCount) {
                    row.add(resultSet.getObject(i))
                }
                data.add(row)
            }

            // Return the results
            QueryResult(status = "DONE", data = data)

        } catch (ex: Exception) {
            QueryResult(status = "FAILED", error = ex.message)
        }
    }


    fun executeQueryAsync(id:Long, jobId:String){
        TODO()
    }

    fun getJobStatus(jobId: String) {
        TODO()
    }
    fun getJobResults(jobId: String) {
        TODO()
    }

}

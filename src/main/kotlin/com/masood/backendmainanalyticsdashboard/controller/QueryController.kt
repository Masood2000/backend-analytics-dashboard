package com.masood.backendmainanalyticsdashboard.controller


import com.masood.backendmainanalyticsdashboard.model.SavedQuery
import com.masood.backendmainanalyticsdashboard.service.QueryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/queries")
class QueryController( private val queryService: QueryService) {

    @PostMapping
    fun addQuery(@RequestBody query: String): ResponseEntity<Map<String, Long>> {
        val id = queryService.saveQuery(query)
        return ResponseEntity.ok(mapOf("id" to id))
    }

    @GetMapping
    fun listAllQueries(): List<SavedQuery> {

        return queryService.getAllQueries()

    }

    @GetMapping("/execute")
    fun executeQuery(@RequestParam query: Long): List<List<Any?>> {

        return queryService.executeQuery(query)

    }


}



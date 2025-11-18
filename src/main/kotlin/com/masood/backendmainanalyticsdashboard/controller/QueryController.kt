package com.masood.backendmainanalyticsdashboard.controller


import com.masood.backendmainanalyticsdashboard.model.SavedQuery
import com.masood.backendmainanalyticsdashboard.service.QueryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

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
    suspend fun executeQuery(@RequestParam query: Long): List<List<Any?>> {

        return queryService.executeQuery(query).data?: emptyList()

    }

    /***
     * To be Implemented
     */
    /*
    // Start query execution
    @PostMapping("/queries/{id}/execute")
    suspend fun startQueryExecution(@PathVariable id: Long): ResponseEntity<JobResponse> {
        val jobId = UUID.randomUUID().toString()
        queryService.executeQueryAsync(id, jobId)
        return ResponseEntity.ok(JobResponse(jobId, "RUNNING"))
    }



    // Check job status
    @GetMapping("/jobs/{jobId}/status")
    fun getJobStatus(@PathVariable jobId: String): ResponseEntity<Unit> {
        val status = queryService.getJobStatus(jobId)
        return ResponseEntity.ok(status)
    }



    // Get results when ready
    @GetMapping("/jobs/{jobId}/results")
    fun getJobResults(@PathVariable jobId: String): ResponseEntity<Unit> {
        val results = queryService.getJobResults(jobId)
        return ResponseEntity.ok(results)
    }




    class QueryResults {

    }

    class JobStatus {

    }

    class JobResponse(jobId: String, s: String) {

    }*/
}





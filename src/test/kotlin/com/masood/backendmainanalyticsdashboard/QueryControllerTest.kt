package com.masood.backendmainanalyticsdashboard

import com.masood.backendmainanalyticsdashboard.controller.QueryController
import com.masood.backendmainanalyticsdashboard.model.SavedQuery
import com.masood.backendmainanalyticsdashboard.service.QueryService

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus


@ExtendWith(MockitoExtension::class)
class QueryControllerTest {

    @Mock
    private lateinit var queryService: QueryService

    private lateinit var queryController: QueryController

    @BeforeEach
    fun setUp() {
        queryController = QueryController(queryService)
    }

    @Test
    fun `addQuery should save query and return ID`() {
        // Given
        val queryText = "SELECT * FROM passengers"
        val expectedId = 1L
        `when`(queryService.saveQuery(queryText)).thenReturn(expectedId)

        // When
        val response = queryController.addQuery(queryText)

        // Then
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(mapOf("id" to expectedId), response.body)
        verify(queryService).saveQuery(queryText)
    }

    @Test
    fun `addQuery should handle empty query string`() {
        // Given
        val queryText = ""
        val expectedId = 2L
        `when`(queryService.saveQuery(queryText)).thenReturn(expectedId)

        // When
        val response = queryController.addQuery(queryText)

        // Then
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(mapOf("id" to expectedId), response.body)
        verify(queryService).saveQuery(queryText)
    }

    @Test
    fun `addQuery should handle complex SELECT query`() {
        // Given
        val complexQuery = "SELECT name, age FROM passengers WHERE age > 30 ORDER BY age DESC LIMIT 10"
        val expectedId = 3L
        `when`(queryService.saveQuery(complexQuery)).thenReturn(expectedId)

        // When
        val response = queryController.addQuery(complexQuery)

        // Then
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(mapOf("id" to expectedId), response.body)
        verify(queryService).saveQuery(complexQuery)
    }

    @Test
    fun `listAllQueries should return empty list when no queries exist`() {
        // Given
        `when`(queryService.getAllQueries()).thenReturn(emptyList())

        // When
        val result = queryController.listAllQueries()

        // Then
        assertTrue(result.isEmpty())
        verify(queryService).getAllQueries()
    }

    @Test
    fun `listAllQueries should return list of saved queries`() {
        // Given
        val expectedQueries = listOf(
            SavedQuery(id = 1L, queryText = "SELECT * FROM passengers"),
            SavedQuery(id = 2L, queryText = "SELECT name FROM passengers WHERE age > 30")
        )
        `when`(queryService.getAllQueries()).thenReturn(expectedQueries)

        // When
        val result = queryController.listAllQueries()

        // Then
        assertEquals(2, result.size)
        assertEquals(expectedQueries, result)
        verify(queryService).getAllQueries()
    }
}


class QueryControllerBasicTest {

    private lateinit var queryService: QueryService
    private lateinit var queryController: QueryController

    @BeforeEach
    fun setUp() {
        queryService = mock(QueryService::class.java)
        queryController = QueryController(queryService)
    }

    @Test
    fun `addQuery creates query successfully`() {
        // Given
        val queryText = "SELECT COUNT(*) FROM passengers"
        val expectedId = 10L
        `when`(queryService.saveQuery(queryText)).thenReturn(expectedId)

        // When
        val response = queryController.addQuery(queryText)

        // Then
        assertNotNull(response)
        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
        assertEquals(expectedId, response.body?.get("id"))
    }

    @Test
    fun `listAllQueries returns queries list`() {
        // Given
        val query1 = SavedQuery(1L, "SELECT * FROM passengers")
        val query2 = SavedQuery(2L, "SELECT name FROM passengers")
        val expectedQueries = listOf(query1, query2)
        `when`(queryService.getAllQueries()).thenReturn(expectedQueries)

        // When
        val result = queryController.listAllQueries()

        // Then
        assertNotNull(result)
        assertEquals(2, result.size)
        assertEquals("SELECT * FROM passengers", result[0].queryText)
        assertEquals("SELECT name FROM passengers", result[1].queryText)
    }

    @Test
    fun `addQuery handles special characters in query`() {
        // Given
        val queryWithSpecialChars = "SELECT * FROM passengers WHERE name LIKE '%Smith%'"
        val expectedId = 5L
        `when`(queryService.saveQuery(queryWithSpecialChars)).thenReturn(expectedId)

        // When
        val response = queryController.addQuery(queryWithSpecialChars)

        // Then
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(expectedId, response.body?.get("id"))
        verify(queryService).saveQuery(queryWithSpecialChars)
    }

    @Test
    fun `listAllQueries handles empty repository`() {
        // Given
        `when`(queryService.getAllQueries()).thenReturn(emptyList())

        // When
        val result = queryController.listAllQueries()

        // Then
        assertNotNull(result)
        assertTrue(result.isEmpty())
        verify(queryService).getAllQueries()
    }


}
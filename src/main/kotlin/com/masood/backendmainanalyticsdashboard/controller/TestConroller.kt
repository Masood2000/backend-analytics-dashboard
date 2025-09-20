package com.masood.backendmainanalyticsdashboard.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestConroller {

    @GetMapping("/test")
    fun hello(): String {
        return "Hello, Spring Boot with Kotlin & H2!"
    }

}





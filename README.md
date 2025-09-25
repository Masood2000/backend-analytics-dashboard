# Backend Main Analytics Dashboard

This project is an implementation of an **analytics query service** built with **Spring Boot (Kotlin)** as part of an internship assignment.  
It allows saving SQL queries, listing them, and executing them safely against a dataset.  

The focus is on handling **long-running queries** with Kotlin **coroutines**, while ensuring security by only allowing safe `SELECT` statements.  

Please refer to word document "assignment_solution" for more details.


---

##  Features

- **Save queries**  
  Store SQL queries (`SELECT` only, safe execution enforced).
- **List queries**  
  View all saved queries with their IDs.
- **Execute queries**  
  Execute saved queries via an endpoint. Results are returned as JSON.
- **Safe execution**  
  Disallows dangerous SQL (`INSERT`, `UPDATE`, `DELETE`, `DROP`, etc.).
- **Coroutine support**  
  Queries run inside a suspendable `withContext(Dispatchers.IO)` block to avoid blocking main threads.

---

## Tech Stack

- **Kotlin** 1.9  
- **Spring Boot** 3.5.6 
- **Spring JDBC (JdbcTemplate)**  
- **H2 Database** (in-memory demo DB)  
- **Kotlin Coroutines**  

---

## Getting Started

### Requirements
- Java 17+  
- Gradle (wrapper included)


---

## Run locally
```bash
git clone https://github.com/Masood2000/backend-main-analytics-dashboard.git
cd backend-main-analytics-dashboard
./gradlew bootRun




# Backend Main Analytics Dashboard

This project is an implementation of an **analytics query service** built with **Spring Boot (Kotlin)** as part of an internship assignment.  
It allows saving SQL queries, listing them, and executing them safely against a dataset.  

The focus is on handling **long-running queries** with Kotlin **coroutines**, while ensuring security by only allowing safe `SELECT` statements.  


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
**### Assumptions**

- **Dataset is static**: The analytic data does not change frequently (or at all), which allows caching of query results.  
- **Read-only queries**: Only `SELECT` queries are allowed; all modification statements are forbidden.  
- **Client waits for execution**: The implemented approach (Approach 1) assumes that clients can wait for query results.  
- **Moderate dataset size**: The system is optimized for datasets that are not extremely large, as Approach 1 executes queries synchronously using coroutines.

---

**### Design Decisions**

**### a) Query Storage**
- Saved queries are stored in a **JPA repository** (`SavedQueryRepository`) with fields for `id` and `queryText`.  
- This allows users to save, list, and execute queries consistently.

**### b) Safe Query Execution**
- Only `SELECT` queries are allowed.  
- A list of forbidden keywords (`INSERT`, `UPDATE`, `DELETE`, `DROP`, `ALTER`, etc.) is checked before saving a query.  
- Execution is done via **JdbcTemplate**, mapping each row to a list of objects.

**### c) Coroutine-based Execution**
- Implemented **Approach 1** using Kotlin coroutines:  
  - Controller methods are `suspend` functions.  
  - `executeQuery` in the service uses `withContext(Dispatchers.IO)` to run queries safely on the I/O dispatcher.  
- This ensures that database operations do not block main threads while allowing the client to wait for results.

---

**### Limitations**

- **Blocking for large queries**: Approach 1 blocks the client until query execution completes. This is fine for moderate datasets but may not scale for very large datasets.  
- **In-memory cache**: Cached results are lost on server restart.  
- **No pagination**: Query results are returned as a full JSON array, which may be inefficient for very large datasets.  
- **Basic SQL validation**: Only keyword checking is used; complex SQL injection patterns are not fully handled.  

---

**### Possible Improvements
**
- Implement **Approach 2 **:  
  - Start queries in background coroutines.  
  - Return a job ID immediately.  
  - Add an endpoint `/result/{id}` to fetch the query result later.  
  - This avoids blocking the client and is suitable for very large datasets.


---


### Run locally
```bash
git clone https://github.com/Masood2000/backend-main-analytics-dashboard.git
cd backend-main-analytics-dashboard
./gradlew bootRun

---


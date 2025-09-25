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

#API Endpoints & Postman Testing

You can test all endpoints using **Postman** or any API client. Make sure the application is running at:

http://localhost:8080


##  Add a Query (POST `/queries`)

- **URL:** `http://localhost:8080/queries`  
- **Method:** `POST`  
- **Body:** Raw → Text (text/plain)  

**Example:**
SELECT * FROM passengers;

**Response:**
```json
{ "id": 1 }
---

## List All Queries (GET /queries)
URL: http://localhost:8080/queries

Method: GET

Response Example:

[
  { "id": 1, "queryText": "SELECT * FROM passengers;" },
  { "id": 2, "queryText": "SELECT name, age FROM passengers;" }
]
### Execute a Query (GET /queries/execute)
URL: http://localhost:8080/queries/execute?query=1

Method: GET

Query Parameter:

query → The ID of the query to execute (e.g., 1)

Response (Success):

{
  "status": "DONE",
  "data": [
    [1, "Braund, Mr Owen Harris", 22, "male", "S", 7.25, 0]
  ]
}
Response (Error):
{
  "status": "FAILED",
  "error": "Query not found"
}
🔹 Notes
In Postman, ensure the POST body is sent as raw text/plain.
GET requests for execute query require query ID as a URL parameter: ?query=1.




## Run locally
```bash
git clone https://github.com/Masood2000/backend-main-analytics-dashboard.git
cd backend-main-analytics-dashboard
./gradlew bootRun

---


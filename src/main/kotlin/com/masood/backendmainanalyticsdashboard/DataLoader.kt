package com.masood.backendmainanalyticsdashboard

import com.masood.backendmainanalyticsdashboard.repository.PassengerRepository
import com.masood.backendmainanalyticsdashboard.model.Passenger
import com.opencsv.CSVReader
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.io.InputStreamReader


/***
 * Data loader class to initially fill the h2 database with the
 * csv file contents...
 */

@Component
class DataLoader(private val passengerRepository: PassengerRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {

        val inputStream = this::class.java.getResourceAsStream("/titanic.csv")
        val reader = CSVReader(InputStreamReader(inputStream))
        val allRows = reader.readAll()

        // Skipping the header
        for (row in allRows.drop(1)) {
            try {
                val fare = row[9].toDoubleOrNull() ?: continue
                val age = row[5].toDoubleOrNull()
                val cabin = if (row[10].isNotBlank()) row[10] else null
                val embarked = if (row[11].isNotBlank()) row[11] else null

                val passenger = Passenger (
                    passengerId = row[0].toInt(),
                    survived = row[1].toInt(),
                    pclass = row[2].toInt(),
                    name = row[3],
                    sex = row[4],
                    age = age,
                    sibSp = row[6].toInt(),
                    parch = row[7].toInt(),
                    ticket = row[8],
                    fare = fare,
                    cabin = cabin,
                    embarked = embarked
                )

                passengerRepository.save(passenger)

            }
            catch (e: Exception) {
                println("Skipping row due to error: ${e.message}")
                println("Stack Trace of exception is ${e.stackTrace}")

            }
        }
        println("Titanic data loaded successfully!")
    }
}





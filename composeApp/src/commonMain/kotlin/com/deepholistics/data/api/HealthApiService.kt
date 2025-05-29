package com.deepholistics.data.api

import com.deepholistics.data.models.HealthOverview
import com.deepholistics.data.models.ApiResponse
import com.deepholistics.data.models.ApiResult
import com.deepholistics.httpClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class HealthApiService {

    //    private val baseUrl = "https://54c349f5-a8c5-4302-bb3a-00ba252ecd77-00-2bnkcupu7svj6.pike.replit.dev"
    //private val baseUrl = "https://api.stg.dh.deepholistics.com/v4/human-token/health-data"
    private val baseUrl = "https://api.stg.dh.deepholistics.com/v4/human-token"

    val client = httpClient()

    suspend fun getHealthOverview(): Result<ApiResult> {
        return try {

            println("--> Health overview API called.")

            val response = client.get("$baseUrl/recommendation") {
                headers {

                    append("client_id", "6tVjHMtvhLEKtcx0XDgzmKH5TY9YeC1Waroq")
                    append("content-type", "application/json")
                    append("user_timezone", "Asia/Calcutta")
                    append(
                        "access_token",
                        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiQkVUQV8wMzcyNGE3Yi0wZjA5LTQ1ODYtYmYyMy1hYTQ1NzA5NzVhYjciLCJzZXNzaW9uX2lkIjoiOGM0MmFlMzAtZmVkMC00NTNjLWIwMzEtYmQyYmFjNzQ5N2Y0IiwidXNlcl9pbnRfaWQiOiI0NzUiLCJpYXQiOjE3NDg0OTkwODgsImV4cCI6MTc0OTEwMzg4OH0.jbbY5r1g-SSzYvII3EkcfzFfdDF2OHZwifx9DFuH20E"
                    )
                }
            }


            println("Response JSON: ${response.status}")
            println("Response JSON: ${response.bodyAsText()}")

            println("\n\n --> Health overview API response $response ")

            if (true) {
                Result.success("")
            } else {
                Result.failure(Exception("Unknown error"))
            }
        } catch (e: Exception) {
            // Fallback with mock data for demo

            println("--> The exception is $e")
            Result.success(getMockHealthOverview())
        } as Result<ApiResult>
    }

    private fun getMockHealthOverview(): HealthOverview {
        return HealthOverview(
            overallScore = 85,
            riskLevel = "Low",
            lastUpdated = "2024-01-15",
            nextTestDue = "2024-03-15",
            biomarkers = listOf(
                com.deepholistics.data.models.Biomarker(
                    name = "Cholesterol",
                    value = 180.0,
                    unit = "mg/dL",
                    status = "Normal",
                    normalRange = "< 200",
                    trend = "stable",
                    percentChange = -2.5,
                    riskLevel = "Low"
                ), com.deepholistics.data.models.Biomarker(
                    name = "Blood Sugar",
                    value = 95.0,
                    unit = "mg/dL",
                    status = "Normal",
                    normalRange = "70-100",
                    trend = "improving",
                    percentChange = -5.2,
                    riskLevel = "Low"
                ), com.deepholistics.data.models.Biomarker(
                    name = "Vitamin D",
                    value = 32.0,
                    unit = "ng/mL",
                    status = "Low",
                    normalRange = "30-100",
                    trend = "declining",
                    percentChange = -8.1,
                    riskLevel = "Medium"
                )
            ),
            recentTests = listOf(
                com.deepholistics.data.models.TestResult(
                    testName = "Complete Blood Count",
                    date = "2024-01-10",
                    status = "Completed",
                    score = 88,
                    category = "Blood Work"
                ), com.deepholistics.data.models.TestResult(
                    testName = "Lipid Panel",
                    date = "2024-01-08",
                    status = "Completed",
                    score = 92,
                    category = "Cardiovascular"
                )
            ),
            recommendations = listOf(
                "Increase vitamin D supplementation",
                "Maintain current exercise routine",
                "Schedule follow-up in 8 weeks"
            ),
            healthMetrics = listOf(
                com.deepholistics.data.models.HealthMetric(
                    name = "Heart Rate",
                    value = "72 bpm",
                    change = "+2%",
                    isPositive = true,
                    icon = "heart",
                    progress = 0.72f
                ), com.deepholistics.data.models.HealthMetric(
                    name = "Sleep Quality",
                    value = "8.2/10",
                    change = "+15%",
                    isPositive = true,
                    icon = "sleep",
                    progress = 0.82f
                ), com.deepholistics.data.models.HealthMetric(
                    name = "Stress Level",
                    value = "Low",
                    change = "-12%",
                    isPositive = true,
                    icon = "stress",
                    progress = 0.25f
                ), com.deepholistics.data.models.HealthMetric(
                    name = "Activity Level",
                    value = "High",
                    change = "+8%",
                    isPositive = true,
                    icon = "activity",
                    progress = 0.85f
                )
            )
        )
    }

    fun close() {
        client.close()
    }
}

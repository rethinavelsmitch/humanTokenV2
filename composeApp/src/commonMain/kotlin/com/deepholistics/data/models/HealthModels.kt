package com.deepholistics.data.models

import kotlinx.serialization.Serializable

@Serializable
data class HealthOverview(
    val overallScore: Int,
    val riskLevel: String,
    val lastUpdated: String,
    val nextTestDue: String,
    val biomarkers: List<Biomarker>,
    val recentTests: List<TestResult>,
    val recommendations: List<String>,
    val healthMetrics: List<HealthMetric>
)

@Serializable
data class Biomarker(
    val name: String,
    val value: Double,
    val unit: String,
    val status: String,
    val normalRange: String,
    val trend: String,
    val percentChange: Double,
    val riskLevel: String
)

@Serializable
data class TestResult(
    val testName: String, val date: String, val status: String, val score: Int, val category: String
)

@Serializable
data class HealthMetric(
    val name: String,
    val value: String,
    val change: String,
    val isPositive: Boolean,
    val icon: String,
    val progress: Float
)


@Serializable
data class ApiResult(
    val success: Boolean, val message: String?
)


@Serializable
data class ApiResponse<T>(
    val success: Boolean, val data: T?, val message: String?
)

package com.deepholistics.data.models.apiresult

import kotlinx.serialization.Serializable

@Serializable
data class RecommendationResponse(
    val status: String,
    val message: String,
    val data: RecommendationData,
)

@Serializable
data class RecommendationData(
    val recommendations: List<Recommendation>,
)

@Serializable
data class Recommendation(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val priority: String? = null,
    val category: String? = null
)

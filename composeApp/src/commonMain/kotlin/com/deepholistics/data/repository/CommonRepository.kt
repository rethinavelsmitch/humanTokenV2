package com.deepholistics.data.repository

import com.deepholistics.data.models.apiresult.RecommendationResponse
import com.deepholistics.data.networking.ApiUrlConstant
import com.deepholistics.data.networking.get
import io.ktor.client.HttpClient


class CommonRepository(private val httpClient: HttpClient) {

    suspend fun getRecommendation(accessToken: String): Result<RecommendationResponse> =
        httpClient.get<RecommendationResponse>(
            url = ApiUrlConstant.RECOMMENDATION_URL,
            accessToken = accessToken,
        )

}
package com.deepholistics.data.repository

import com.deepholistics.data.models.apiresult.RecommendationData
import com.deepholistics.data.networking.ApiUrlConstant
import com.deepholistics.data.networking.get
import com.deepholistics.data.networking.post
import io.ktor.client.HttpClient
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put


class CommonRepository(private val httpClient: HttpClient) {

    suspend fun getRecommendation(accessToken: String) = httpClient.get<RecommendationData>(
        url = ApiUrlConstant.RECOMMENDATION_URL,
        accessToken = accessToken,
    )

    suspend fun userLogOut(accessToken: String) = httpClient.post(
        url = ApiUrlConstant.LOG_OUT_URL,
        accessToken = accessToken,
    )

}
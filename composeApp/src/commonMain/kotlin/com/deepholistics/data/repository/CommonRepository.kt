package com.deepholistics.data.repository

import com.deepholistics.android.data.model.apiresult.ApiResult
import com.deepholistics.data.models.apiresult.RecommendationResponse
import com.deepholistics.data.networking.ApiUrlConstant
import com.deepholistics.data.networking.get
import com.deepholistics.data.networking.post
import io.ktor.client.HttpClient


class CommonRepository(private val httpClient: HttpClient) {

    suspend fun getRecommendation(accessToken:String)= httpClient.get<RecommendationResponse>(
        url = ApiUrlConstant.RECOMMENDATION_URL,
        accessToken = accessToken,
    )

    suspend fun userLogOut(accessToken:String) = httpClient.post<RecommendationResponse>(
        url = ApiUrlConstant.LOG_OUT_URL,
        accessToken = accessToken,
    )

}

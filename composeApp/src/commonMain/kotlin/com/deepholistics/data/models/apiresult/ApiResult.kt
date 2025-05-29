package com.deepholistics.android.data.model.apiresult

import kotlinx.serialization.Serializable

@Serializable
data class ApiResult(
    val token: String = "",
    var message: String = "",
    var data: String? = "",
    var httpCode: Int = -1,
    val status: String = "",
    var errorMessage: String? = "",
    val type: String = "",
    var isSuccessful: Boolean = true,
    var hasTokenExpired: Boolean = false,
    var display_mode: String = "",
)


@Serializable
data class ResponseData(
    var isNewAccount: Boolean? = false,
    var access_token: String = "",
    var code: String? = "",
    var refresh_token: String? = "",
    val course_status: String? = "",
    val profile: Profile? = null,
)

@Serializable
data class Profile(
    val city: String? = null,
    val country_code: String? = null,
    val created_at: String? = null,
    val dob: String? = null,
    val email: String? = null,
    val gender: String? = null,
    var height: String? = null,
    val height_updated_at: String? = null,
    val id: String? = null,
    val mobile: String? = null,
    val name: String? = null,
    val profile_photo_key: String? = null,
    val profile_photo_updated_at: String? = null,
    val profile_photo_url: String? = null,
    val updated_at: String? = null,
    var weight: String? = null,
)


@Serializable
data class UserCheckData(
    val isUserPresent: Boolean? = null,
)

@Serializable
data class Device(
    val active: Boolean? = null,
    val category: String? = null,
    val created_at: String? = null,
    var device_id: String? = null,
    val id: String? = null,
    val manufacturer: String? = null,
    var name: String? = null,
    val sub_type: String? = null,
    val type: String? = null,
    val updated_at: String? = null,
    var software_version: String? = null,
    var hardware_version: String? = null,
    var last_sync_at: String? = null,
    var battery_percentage: Int? = null,
    val profile_devices: List<ProfileDevice>? = null,
    var attached_at: String? = null,
    var detached_at: String? = null,
    var sf_device_id: String? = null,
    var integration_id: String? = null,

    // local purpose
    var isDeviceFounded: Boolean = false,
    var isDeviceConnectionLoading: Boolean = false,
    var isDeviceConnected: Boolean = false,
    var isDeviceSearch: Boolean = true,
    var order: Int? = null,
    var isBluetoothOff: Boolean = false,
    var connection_status: String? = null
)

@Serializable
data class ProfileDevice(
    val created_at: String? = null,
    val device_access_role: Int? = null,
    val device_id: String? = null,
    val id: String? = null,
    val last_synced_at: String? = null,
    val mobile_type: String? = null,
    val profile_id: String? = null,
    val updated_at: String? = null,
) 
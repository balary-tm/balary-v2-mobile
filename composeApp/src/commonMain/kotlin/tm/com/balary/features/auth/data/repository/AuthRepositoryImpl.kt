package tm.com.balary.features.auth.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tm.com.balary.core.Constant
import tm.com.balary.core.Resource
import tm.com.balary.features.auth.data.entity.request.ChangePasswordBody
import tm.com.balary.features.auth.data.entity.request.LoginBody
import tm.com.balary.features.auth.data.entity.request.SentOtpBody
import tm.com.balary.features.auth.data.entity.request.VerifyOtpBody
import tm.com.balary.features.auth.data.entity.request.VerifyOtpForPassword
import tm.com.balary.features.auth.data.entity.response.LoginResponse
import tm.com.balary.features.auth.data.entity.response.VerifyOtpResponse
import tm.com.balary.features.auth.data.settings.AuthSettings
import tm.com.balary.features.auth.domain.model.AuthModel
import tm.com.balary.features.auth.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val authSettings: AuthSettings
) : AuthRepository {
    @OptIn(InternalAPI::class)
    override suspend fun sendOtp(data: SentOtpBody): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        delay(3000L)
        try {
            val response = httpClient.post("${Constant.BASE_URL}/auth/send-otp") {
                setBody(data)
                contentType(ContentType.Application.Json)
            }

            if(response.status.value in 200..299) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error(response.status.description, response.status.value))
            }

        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }

    @OptIn(InternalAPI::class)
    override suspend fun verifyOtp(data: VerifyOtpBody): Flow<Resource<AuthModel>> = flow {
        emit(Resource.Loading())
        delay(3000L)
        try {
            val response = httpClient.post("${Constant.BASE_URL}/auth/verify-otp") {
                setBody(data)
                contentType(ContentType.Application.Json)
            }

            if(response.status.value in 200..299) {
                val result = response.body<VerifyOtpResponse>()
                authSettings.saveToken(result.access_token)
                emit(Resource.Success(result.toUIEntity()))
            } else {
                emit(Resource.Error(response.status.description, response.status.value))
            }

        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }

    @OptIn(InternalAPI::class)
    override suspend fun signIn(data: LoginBody): Flow<Resource<AuthModel>> = flow {
        emit(Resource.Loading())
        delay(3000L)
        try {
            val response = httpClient.post("${Constant.BASE_URL}/auth/customer/signin") {
                setBody(data)
                contentType(ContentType.Application.Json)
            }

            if(response.status.value in 200..299) {
                val result = response.body<LoginResponse>()
                authSettings.saveToken(result.access_token)
                emit(Resource.Success(result.toUIEntity()))
            } else {
                emit(Resource.Error(response.status.description, response.status.value))
            }

        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }

    override suspend fun verifyOtpForPassword(data: VerifyOtpForPassword): Flow<Resource<AuthModel>> = flow {
        emit(Resource.Loading())
        delay(3000L)
        try {
            val response = httpClient.post("${Constant.BASE_URL}/auth/verify-otp-for-pass-change") {
                setBody(data)
                contentType(ContentType.Application.Json)
            }

            if(response.status.value in 200..299) {
                val result = response.body<VerifyOtpResponse>()
                authSettings.saveToken(result.access_token)
                emit(Resource.Success(result.toUIEntity()))
            } else {
                emit(Resource.Error(response.status.description, response.status.value))
            }

        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }

    override suspend fun changePassword(data: ChangePasswordBody): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        delay(3000L)
        try {
            val response = httpClient.post("${Constant.BASE_URL}/auth/change-password") {
                setBody(data)
                contentType(ContentType.Application.Json)
                headers.append("Authorization", "Bearer ${authSettings.getToken()}")
            }

            if(response.status.value in 200..299) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error(response.status.description, response.status.value))
            }

        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }
}
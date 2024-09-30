package tm.com.balary.features.auth.domain.repository

import kotlinx.coroutines.flow.Flow
import tm.com.balary.core.Resource
import tm.com.balary.features.auth.data.entity.request.ChangePasswordBody
import tm.com.balary.features.auth.data.entity.request.LoginBody
import tm.com.balary.features.auth.data.entity.request.SentOtpBody
import tm.com.balary.features.auth.data.entity.request.VerifyOtpBody
import tm.com.balary.features.auth.data.entity.request.VerifyOtpForPassword
import tm.com.balary.features.auth.domain.model.AuthModel

interface AuthRepository {
    suspend fun sendOtp(data: SentOtpBody): Flow<Resource<Boolean>>
    suspend fun verifyOtp(data: VerifyOtpBody): Flow<Resource<AuthModel>>
    suspend fun signIn(data: LoginBody): Flow<Resource<AuthModel>>
    suspend fun verifyOtpForPassword(data: VerifyOtpForPassword): Flow<Resource<AuthModel>>
    suspend fun changePassword(data: ChangePasswordBody): Flow<Resource<Boolean>>
}
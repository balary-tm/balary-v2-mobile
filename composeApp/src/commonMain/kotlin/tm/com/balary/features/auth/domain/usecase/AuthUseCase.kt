package tm.com.balary.features.auth.domain.usecase

import kotlinx.coroutines.flow.Flow
import tm.com.balary.core.Resource
import tm.com.balary.features.auth.data.entity.request.ChangePasswordBody
import tm.com.balary.features.auth.data.entity.request.LoginBody
import tm.com.balary.features.auth.data.entity.request.SentOtpBody
import tm.com.balary.features.auth.data.entity.request.VerifyOtpBody
import tm.com.balary.features.auth.data.entity.request.VerifyOtpForPassword
import tm.com.balary.features.auth.domain.model.AuthModel
import tm.com.balary.features.auth.domain.repository.AuthRepository

class AuthUseCase(
    private val repository: AuthRepository
) {
    suspend fun sendOtp(body: SentOtpBody): Flow<Resource<Boolean>> {
        return repository.sendOtp(body)
    }
    suspend fun verifyOtp(body: VerifyOtpBody): Flow<Resource<AuthModel>> {
        return repository.verifyOtp(body)
    }
    suspend fun signIn(body: LoginBody): Flow<Resource<AuthModel>> {
        return repository.signIn(body)
    }
    suspend fun verifyOtpForPassword(data: VerifyOtpForPassword): Flow<Resource<AuthModel>> {
        return repository.verifyOtpForPassword(data)
    }
    suspend fun changePassword(data: ChangePasswordBody): Flow<Resource<Boolean>> {
        return repository.changePassword(data)
    }
}
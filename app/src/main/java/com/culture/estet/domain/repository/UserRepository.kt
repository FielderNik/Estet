package com.culture.estet.domain.repository

import com.culture.estet.core.funcional.Either
import com.culture.estet.core.funcional.Failure
import com.culture.estet.core.network.EstetApi
import com.culture.estet.domain.models.profile.UserProfile
import javax.inject.Inject

interface UserRepository {
    suspend fun createUser(): Either<Failure, String>
    suspend fun loadUserData(userId: String): Either<Failure, UserProfile>
}

class UserRepositoryImpl @Inject constructor(private val api: EstetApi) : BaseRepository(), UserRepository {

    override suspend fun createUser(): Either<Failure, String> {
        return handleSuspendRequest {
            api.createEmptyUser()
        }
    }

    override suspend fun loadUserData(userId: String): Either<Failure, UserProfile> {
        return handleSuspendRequest {
            api.getUserData(userId)
        }
    }

}
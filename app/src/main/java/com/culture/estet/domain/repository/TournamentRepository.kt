package com.culture.estet.domain.repository

import com.culture.estet.core.funcional.Either
import com.culture.estet.core.funcional.Failure
import com.culture.estet.data.mock.Tournament
import com.culture.estet.domain.models.ArtType
import com.culture.estet.domain.models.tournament.User
import com.culture.estet.domain.models.tournament.toUser
import javax.inject.Inject

interface TournamentRepository {
    suspend fun getUsersByArtType(userId: String, artType: ArtType?): Either<Failure, List<User>>
}

class TournamentRepositoryImpl @Inject constructor() : TournamentRepository, BaseRepository() {
    override suspend fun getUsersByArtType(userId: String, artType: ArtType?): Either<Failure, List<User>> {
        return handleRequest {
            val users = Tournament.users
            users.map { it.toUser(artType) }
        }
    }

}
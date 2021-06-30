package com.picpay.desafio.android.user.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.user.data.network.model.UserResponse
import com.picpay.desafio.android.user.domain.model.User

@Entity(tableName = "picPayUsers")
data class UserEntity(
    @PrimaryKey val id: Int?,
    val img: String?,
    val name: String?,
    val username: String?
)

fun List<UserEntity>.toUserList(): List<User> {
    return with(this) {
        this.map { userEntity ->
            User(
                id = userEntity.id,
                img = userEntity.img,
                name = userEntity.name,
                username = userEntity.username
            )
        }
    }
}

fun List<UserResponse>.toUserEntityList(): List<UserEntity> {
    return with(this) {
        this.map { userResponse ->
            UserEntity(
                id = userResponse.id,
                img = userResponse.img,
                name = userResponse.name,
                username = userResponse.username
            )
        }
    }
}

fun List<UserResponse>.responseToUserList(): List<User> {
    return with(this) {
        this.map { userResponse ->
            User(
                id = userResponse.id,
                img = userResponse.img,
                name = userResponse.name,
                username = userResponse.username
            )
        }
    }
}
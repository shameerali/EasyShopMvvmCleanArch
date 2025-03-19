package com.luminuses.easyshopmvvmcleanarch.ui.mapper

import com.luminuses.easyshopmvvmcleanarch.domain.entity.user.UserInformationEntity
import com.luminuses.easyshopmvvmcleanarch.domain.mapper.ProductBaseMapper
import com.luminuses.easyshopmvvmcleanarch.ui.auth.UserInformationUiData
import javax.inject.Inject

class UserInfoUiDataToEntityMapper @Inject constructor() : ProductBaseMapper<UserInformationUiData, UserInformationEntity> {
    override fun map(input: UserInformationUiData): UserInformationEntity {
        return UserInformationEntity(
            id = input.id,
            name = input.name,
            surname = input.surname,
            email = input.email,
            phone = input.phone,
            image = input.image,
            password = input.password,
            token = input.token,
        )
    }

}

class UserInfoEntityToUiDataMapper @Inject constructor() :
    ProductBaseMapper<UserInformationEntity, UserInformationUiData> {
    override fun map(input: UserInformationEntity): UserInformationUiData {
        return UserInformationUiData(
            id = input.id,
            name = input.name,
            surname = input.surname,
            email = input.email,
            phone = input.phone,
            image = input.image,
            password = input.password,
            token = input.token,
        )
    }
}
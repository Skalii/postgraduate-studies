package skalii.web.server.postgraduatestudies.entities.enums


import com.fasterxml.jackson.annotation.JsonValue

import javax.persistence.AttributeConverter
import javax.persistence.Converter


enum class UserRole(@get:JsonValue val value: String) {

    UNKNOWN("Невідомий користувач"),
    ADMIN("Аспірантура"),
    GRADUATE_STUDENT("Аспірант"),
    DOCTORAL_STUDENT("Докторант"),
    INSTRUCTOR("Керівник");

    companion object {

        @Converter
        class EnumConverter : AttributeConverter<UserRole, String> {

            override fun convertToDatabaseColumn(attribute: UserRole?) =
                    attribute?.value ?: UNKNOWN.value

            override fun convertToEntityAttribute(dbData: String?): UserRole {
                UserRole.values().forEach { if (it.value == dbData) return it }
                return UNKNOWN
            }

        }

    }

}
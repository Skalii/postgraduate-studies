package skaliy.web.server.postgraduatestudies.entities.enums


import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.annotation.JsonView

import javax.persistence.AttributeConverter
import javax.persistence.Converter

import skaliy.web.server.postgraduatestudies.views.View


@JsonView(View.UI::class)
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
package skaliy.web.server.postgraduatestudies.enums


import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.annotation.JsonView

import javax.persistence.AttributeConverter
import javax.persistence.Converter

import skaliy.web.server.postgraduatestudies.views.View


@JsonView(View.UI::class)
enum class FamilyStatus(@get:JsonValue val value: String) {

    UNKNOWN("Невідомий сімейний стан"),
    MARRIED_FEMALE("Заміжня"),
    MARRIED_MALE("Одружений"),
    UNMARRIED_FEMALE("Незаміжня"),
    UNMARRIED_MALE("Неодружений"),
    DIVORCED_FEMALE("Розлучена"),
    DIVORCED_MALE("Розлучений"),
    WIDOW("Вдова"),
    WIDOWER("Вдівець");

    companion object {

        @Converter
        class EnumConverter : AttributeConverter<FamilyStatus, String> {

            override fun convertToDatabaseColumn(attribute: FamilyStatus?) =
                    attribute?.value ?: UNKNOWN.value

            override fun convertToEntityAttribute(dbData: String?): FamilyStatus? {
                FamilyStatus.values().forEach { if (it.value == dbData) return it }
                return UNKNOWN
            }

        }

    }

}
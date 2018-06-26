package skalii.web.server.postgraduatestudies.entities.enums


import com.fasterxml.jackson.annotation.JsonValue

import javax.persistence.AttributeConverter
import javax.persistence.Converter


enum class AcademicRank(@get:JsonValue val value: String) {

    UNKNOWN("Невідоме академічне звання"),
    DOCENT("Доцент"),
    PROFESSOR("Професор");

    companion object {

        @Converter
        class EnumConverter : AttributeConverter<AcademicRank, String> {

            override fun convertToDatabaseColumn(attribute: AcademicRank?) =
                    attribute?.value ?: UNKNOWN.value

            override fun convertToEntityAttribute(dbData: String?): AcademicRank? {
                AcademicRank.values().forEach { if (it.value == dbData) return it }
                return UNKNOWN
            }

        }

    }

}
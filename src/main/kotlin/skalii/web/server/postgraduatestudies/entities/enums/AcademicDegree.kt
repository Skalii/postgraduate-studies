package skalii.web.server.postgraduatestudies.entities.enums


import com.fasterxml.jackson.annotation.JsonValue

import javax.persistence.AttributeConverter
import javax.persistence.Converter


enum class AcademicDegree(@get:JsonValue val value: String) {

    EMPTY(""),
    UNKNOWN("Невідомий вчений ступінь"),
    CANDIDATE("Кандидат"),
    DOCTOR("Доктор"),
    PHD("PhD");

    companion object {

        @Converter
        class EnumConverter : AttributeConverter<AcademicDegree, String> {

            override fun convertToDatabaseColumn(attribute: AcademicDegree?) =
                    attribute?.value ?: UNKNOWN.value

            override fun convertToEntityAttribute(dbData: String?): AcademicDegree {
                AcademicDegree.values().forEach { if (it.value == dbData) return it }
                return UNKNOWN
            }

        }

    }

}
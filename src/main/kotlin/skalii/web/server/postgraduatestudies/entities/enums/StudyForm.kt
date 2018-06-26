package skalii.web.server.postgraduatestudies.entities.enums


import com.fasterxml.jackson.annotation.JsonValue

import javax.persistence.AttributeConverter
import javax.persistence.Converter


enum class StudyForm(@get:JsonValue val value: String) {

    UNKNOWN("Невідома форма навчання"),
    FULL_TIME("Денна"),
    EXTRAMURAL("Заочна");

    companion object {

        @Converter
        class EnumConverter : AttributeConverter<StudyForm, String> {

            override fun convertToDatabaseColumn(attribute: StudyForm?) =
                    attribute?.value ?: UNKNOWN.value

            override fun convertToEntityAttribute(dbData: String?): StudyForm {
                StudyForm.values().forEach { if (it.value == dbData) return it }
                return UNKNOWN
            }

        }

    }

}
package skaliy.web.server.postgraduatestudies.enums


import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.annotation.JsonView

import javax.persistence.AttributeConverter
import javax.persistence.Converter

import skaliy.web.server.postgraduatestudies.views.View


@JsonView(View.UI::class)
enum class StudyBasis(@get:JsonValue val value: String) {

    UNKNOWN("Невідома основа навчання"),
    BUDGET("Бюджет"),
    CONTRACT("Контракт");

    companion object {

        @Converter
        class EnumConverter : AttributeConverter<StudyBasis, String> {

            override fun convertToDatabaseColumn(attribute: StudyBasis?) =
                    attribute?.value ?: UNKNOWN.value

            override fun convertToEntityAttribute(dbData: String?): StudyBasis {
                StudyBasis.values().forEach { if (it.value == dbData) return it }
                return UNKNOWN
            }

        }

    }

}
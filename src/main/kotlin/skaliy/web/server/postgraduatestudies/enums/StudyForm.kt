package skaliy.web.server.postgraduatestudies.enums


import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.annotation.JsonView

import javax.persistence.AttributeConverter
import javax.persistence.Converter

import skaliy.web.server.postgraduatestudies.views.View


@JsonView(View.UI::class)
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
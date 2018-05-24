package skaliy.web.server.postgraduatestudies.enums


import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.annotation.JsonView

import javax.persistence.AttributeConverter
import javax.persistence.Converter

import skaliy.web.server.postgraduatestudies.views.View


@JsonView(View.UI::class)
enum class AcademicDegree(@get:JsonValue val value: String) {

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
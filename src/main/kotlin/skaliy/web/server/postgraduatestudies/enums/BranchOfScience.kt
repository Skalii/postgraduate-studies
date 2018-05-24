package skaliy.web.server.postgraduatestudies.enums


import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.annotation.JsonView

import javax.persistence.AttributeConverter
import javax.persistence.Converter

import skaliy.web.server.postgraduatestudies.views.View


@JsonView(View.UI::class)
enum class BranchOfScience(@get:JsonValue val value: String) {

    UNKNOWN("Невідома галузь науки"),
    ECONOMIC("економічних наук"),
    TECHNICAL("технічних наук"),
    PHYSICAL_MATHEMATICAL("фізико-математичних наук");

    companion object {

        @Converter
        class EnumConverter : AttributeConverter<BranchOfScience, String> {

            override fun convertToDatabaseColumn(attribute: BranchOfScience?) =
                    attribute?.value ?: UNKNOWN.value

            override fun convertToEntityAttribute(dbData: String?): BranchOfScience {
                BranchOfScience.values().forEach { if (it.value == dbData) return it }
                return UNKNOWN
            }

        }

    }

}
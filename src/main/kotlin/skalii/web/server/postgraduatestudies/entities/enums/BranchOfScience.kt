package skalii.web.server.postgraduatestudies.entities.enums


import com.fasterxml.jackson.annotation.JsonValue

import javax.persistence.AttributeConverter
import javax.persistence.Converter


enum class BranchOfScience(@get:JsonValue val value: String) {

    UNKNOWN("Невідома галузь науки"),
    ECONOMIC("економічних наук"),
    TECHNICAL("технічних наук"),
    CHEMICAL("хімічних наук"),
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
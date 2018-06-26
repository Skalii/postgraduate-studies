package skalii.web.server.postgraduatestudies.entities


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.SEQUENCE
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.OneToMany
import javax.persistence.OrderBy
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import skalii.web.server.postgraduatestudies.views.View.REST
import skalii.web.server.postgraduatestudies.views.View.TREE


@Entity(name = "Institute")
@SequenceGenerator(
        name = "institutes_seq",
        sequenceName = "institutes_id_institute_seq",
        schema = "public",
        allocationSize = 1)
@Table(name = "institutes",
        schema = "public",
        indexes = [
            Index(name = "institutes_pkey",
                    columnList = "id_institute",
                    unique = true),
            Index(name = "institutes_id_institute_uindex",
                    columnList = "id_institute",
                    unique = true),
            Index(name = "institutes_name_uindex",
                    columnList = "name",
                    unique = true)])
data class Institute(

        @Column(name = "id_institute",
                nullable = false)
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "institutes_seq")
        @Id
        @get:JsonProperty(value = "id_institute")
        @JsonView(REST::class)
        @NotNull
        val idInstitute: Int = 0,

        @NotNull
        @Size(max = 200)
        @Column(name = "name",
                nullable = false,
                length = 200)
        @get:JsonProperty(value = "name")
        @JsonView(REST::class)
        val name: String = "Невідомий інститут",

        @Column(name = "named_after",
                length = 100)
        @get:JsonProperty(value = "named_after")
        @JsonView(REST::class)
        @Size(max = 100)
        val namedAfter: String? = null,

        @Column(name = "abbreviation",
                length = 25)
        @get:JsonProperty(value = "abbreviation")
        @JsonView(REST::class)
        @Size(max = 25)
        val abbreviation: String? = null

) {

    @JsonIgnoreProperties(value = ["institute"])
    @get:JsonProperty(value = "departments")
    @JsonView(TREE::class)
    @OneToMany(
            targetEntity = Department::class,
            mappedBy = "institute")
    @OrderBy
    var departments: MutableList<Department> = mutableListOf(Department())


    constructor() : this(
            0,
            "Невідомий інститут",
            null,
            null
    )

}
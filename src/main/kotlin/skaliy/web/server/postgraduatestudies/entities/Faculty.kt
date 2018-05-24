package skaliy.web.server.postgraduatestudies.entities


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.OneToMany
import javax.persistence.OrderBy
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import skaliy.web.server.postgraduatestudies.views.View


@Entity(name = "Faculty")
@SequenceGenerator(
        name = "faculties_seq",
        sequenceName = "faculties_id_faculty_seq",
        schema = "public",
        allocationSize = 1)
@Table(name = "faculties",
        schema = "public",
        indexes = [
            Index(name = "faculties_pkey",
                    columnList = "id_faculty",
                    unique = true),
            Index(name = "faculties_id_faculty_uindex",
                    columnList = "id_faculty",
                    unique = true),
            Index(name = "faculties_name_uindex",
                    columnList = "name",
                    unique = true)])
data class Faculty(

        @Column(name = "id_faculty",
                nullable = false)
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "faculties_seq")
        @Id
        @JsonProperty(value = "id_faculty")
        @JsonView(View.REST::class)
        @NotNull
        val idFaculty: Int,

        @Column(name = "name",
                nullable = false,
                length = 200)
        @JsonProperty(value = "name")
        @JsonView(View.UI::class)
        @NotNull
        @Size(max = 200)
        val name: String

) {

    @JsonIgnoreProperties(value = ["faculty"])
    @JsonProperty(value = "departments")
    @JsonView(View.TREE::class)
    @OneToMany(
            targetEntity = Department::class,
            mappedBy = "faculty")
    @OrderBy
    lateinit var departments: MutableList<Department>


    constructor() : this(
            0,
            "")

}
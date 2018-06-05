package skaliy.web.server.postgraduatestudies.entities


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView

import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import skaliy.web.server.postgraduatestudies.entities.enums.StudyBasis
import skaliy.web.server.postgraduatestudies.entities.enums.StudyForm
import skaliy.web.server.postgraduatestudies.views.View


@Entity(name = "StudyInfo")
@SequenceGenerator(
        name = "study_info_seq",
        sequenceName = "study_info_id_study_info_seq",
        schema = "public",
        allocationSize = 1)
@Table(name = "study_info",
        schema = "public",
        indexes = [
            Index(name = "study_info_pkey",
                    columnList = "id_study_info",
                    unique = true),
            Index(name = "study_info_id_study_info_uindex",
                    columnList = "id_study_info",
                    unique = true)])
data class StudyInfo(

        @Column(name = "id_study_info",
                nullable = false)
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "study_info_seq")
        @Id
        @JsonProperty(value = "id_study_info")
        @JsonView(View.REST::class)
        @NotNull
        val idStudyInfo: Int,

        @Column(name = "year",
                nullable = false)
        @JsonProperty(value = "year")
        @JsonView(View.REST::class)
        @NotNull
        val year: Int = 1,

        @Column(name = "form")
        @Convert(converter = StudyForm.Companion.EnumConverter::class)
        @JsonProperty(value = "form")
        @JsonView(View.REST::class)
        val form: StudyForm? = StudyForm.FULL_TIME,

        @Column(name = "basis")
        @Convert(converter = StudyBasis.Companion.EnumConverter::class)
        @JsonProperty(value = "basis")
        @JsonView(View.REST::class)
        val basis: StudyBasis? = StudyBasis.CONTRACT,

        @Column(name = "theme_title",
                length = 200)
        @JsonProperty(value = "theme_title")
        @NotNull
        @Size(max = 200)
        @JsonView(View.REST::class)
        val themeTitle: String = "Невідома тема роботи"

) {

    @JoinColumn(
            name = "id_instructor",
            foreignKey = ForeignKey(name = "study_info_instructors_fkey"))
    @JsonIgnoreProperties(value = ["study_info", "students", "sections"])
    @JsonProperty(value = "instructor")
    @JsonView(View.STUDENT_TREE::class)
    @ManyToOne(
            targetEntity = User::class,
            fetch = FetchType.LAZY)
    var instructor: User? = null

    @JsonIgnoreProperties(value = ["study_info", "students", "sections"])
    @JsonProperty(value = "user")
    @JsonView(View.TREE::class, View.INSTRUCTOR_TREE::class)
    @OneToOne(
            targetEntity = User::class,
            fetch = FetchType.LAZY,
            optional = false,
            mappedBy = "studyInfo")
    var user: User? = null


    constructor() : this(
            0,
            1,
            StudyForm.FULL_TIME,
            StudyBasis.CONTRACT,
            "Невідома тема роботи")

    constructor(
            idStudyInfo: Int,
            year: Int,
            form: StudyForm?,
            basis: StudyBasis?,
            themeTitle: String,
            instructor: User?,
            user: User?
    ) : this(
            idStudyInfo,
            year,
            form,
            basis,
            themeTitle
    ) {
        this.instructor = instructor
        this.user = user
    }


    override fun toString() =
            """StudyInfo(
                idStudyInfo=$idStudyInfo,
                year=$year,
                form=${form?.value},
                basis=${basis?.value},
                themeTitle=$themeTitle,
                instructor=$instructor
                )""".trimMargin()

}
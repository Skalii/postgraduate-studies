package skalii.web.server.postgraduatestudies.entities


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import skalii.web.server.postgraduatestudies.repositories.DepartmentsRepository
import skalii.web.server.postgraduatestudies.repositories.FacultiesRepository
import skalii.web.server.postgraduatestudies.repositories.InstitutesRepository

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.SEQUENCE
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OrderBy
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import skalii.web.server.postgraduatestudies.views.View.REST
import skalii.web.server.postgraduatestudies.views.View.TREE


@Entity(name = "Department")
@SequenceGenerator(
        name = "departments_seq",
        sequenceName = "departments_id_department_seq",
        schema = "public",
        allocationSize = 1)
@Table(name = "departments",
        schema = "public",
        indexes = [
            Index(name = "departments_pkey",
                    columnList = "id_department",
                    unique = true),
            Index(name = "departments_id_department_uindex",
                    columnList = "id_department",
                    unique = true),
            Index(name = "departments_name_uindex",
                    columnList = "name",
                    unique = true)])
data class Department(

        @Column(name = "id_department",
                nullable = false)
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "departments_seq")
        @Id
        @get:JsonProperty(value = "id_department")
        @JsonView(REST::class)
        @NotNull
        val idDepartment: Int = 0,

        @Column(name = "name",
                nullable = false,
                length = 200)
        @get:JsonProperty(value = "name")
        @JsonView(REST::class)
        @NotNull
        @Size(max = 200)
        val name: String = ""

) {

    @JoinColumn(
            name = "id_institute",
            nullable = false,
            foreignKey = ForeignKey(name = "departments_institutes_fkey"))
    @JsonIgnoreProperties(value = ["departments"])
    @get:JsonProperty(value = "institute")
    @JsonView(REST::class)
    @ManyToOne(
            targetEntity = Institute::class,
            fetch = LAZY,
            optional = false)
    lateinit var institute: Institute

    @JoinColumn(
            name = "id_faculty",
            nullable = false,
            foreignKey = ForeignKey(name = "departments_faculties_fkey"))
    @JsonIgnoreProperties(value = ["departments"])
    @get:JsonProperty(value = "faculty")
    @JsonView(REST::class)
    @ManyToOne(
            targetEntity = Faculty::class,
            fetch = LAZY,
            optional = false)
    lateinit var faculty: Faculty

    @JsonIgnoreProperties(value = ["department", "students", "sections"])
    @get:JsonProperty(value = "users")
    @JsonView(TREE::class)
    @OneToMany(
            targetEntity = User::class,
            mappedBy = "department")
    @OrderBy
    var users: MutableList<User> = mutableListOf(User())


    constructor() : this(
            0,
            "Невідома кафедра"
    )

    constructor(
            idDepartment: Int = 0,
            name: String = "Невідома кафедра",
            institute: Institute = Institute(),
            faculty: Faculty = Faculty()
    ) : this(
            idDepartment,
            name
    ) {
        this.institute = institute
        this.faculty = faculty
    }


    fun fixInitializedAdd(
            institutesRepository: InstitutesRepository,
            facultiesRepository: FacultiesRepository
    ): Department {
        if (this.institute.idInstitute == 0
                && this.institute.name != "Невідомий інститут") {
            this.institute = institutesRepository.get(this.institute.name)
        }
        if (this.faculty.idFaculty == 0
                && this.faculty.name != "Невідомий факультет") {
            this.faculty = facultiesRepository.get(this.faculty.name)
        }
        return this
    }

    fun fixInitializedSet(
            departmentsRepository: DepartmentsRepository,
            institutesRepository: InstitutesRepository,
            facultiesRepository: FacultiesRepository
    ): Department {
        var foundDepartment: Department? = null
        if (!this::institute.isInitialized) {
            foundDepartment = departmentsRepository.get(this.name)
            this.institute = foundDepartment.institute
        }
        if (!this::faculty.isInitialized) {
            this.faculty = foundDepartment?.faculty
                    ?: departmentsRepository.get(this.name).faculty
        }
        fixInitializedAdd(
                institutesRepository,
                facultiesRepository
        )
        return this
    }

}
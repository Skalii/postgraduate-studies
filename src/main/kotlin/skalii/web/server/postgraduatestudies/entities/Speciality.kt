package skalii.web.server.postgraduatestudies.entities


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import skalii.web.server.postgraduatestudies.repositories.BranchesRepository
import skalii.web.server.postgraduatestudies.repositories.SpecialitiesRepository

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


@Entity(name = "Speciality")
@SequenceGenerator(
        name = "specialities_seq",
        sequenceName = "specialities_id_speciality_seq",
        schema = "public",
        allocationSize = 1)
@Table(name = "specialities",
        schema = "public",
        indexes = [
            Index(name = "specialities_pkey",
                    columnList = "id_speciality",
                    unique = true),
            Index(name = "specialities_id_speciality_uindex",
                    columnList = "id_speciality",
                    unique = true),
            Index(name = "specialities_number_uindex",
                    columnList = "number",
                    unique = true),
            Index(name = "specialities_name_uindex",
                    columnList = "name",
                    unique = true)])
data class Speciality(

        @Column(name = "id_speciality",
                nullable = false)
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "specialities_seq")
        @Id
        @get:JsonProperty(value = "id_speciality")
        @JsonView(REST::class)
        @NotNull
        val idSpeciality: Int = 0,

        @Column(name = "number",
                nullable = false,
                length = 30)
        @get:JsonProperty(value = "number")
        @JsonView(REST::class)
        @NotNull
        @Size(max = 30)
        val number: String = "",

        @Column(name = "name",
                nullable = false,
                length = 200)
        @JsonView(REST::class)
        @get:JsonProperty(value = "name")
        @NotNull
        @Size(max = 200)
        val name: String = ""

) {

    @JsonIgnoreProperties(value = ["specialities"])
    @JoinColumn(
            name = "id_branch",
            nullable = false,
            foreignKey = ForeignKey(name = "specialities_branches_fkey"))
    @get:JsonProperty(value = "branch")
    @JsonView(TREE::class)
    @ManyToOne(
            targetEntity = Branch::class,
            fetch = LAZY,
            optional = false)
    lateinit var branch: Branch

    @JsonIgnoreProperties(value = ["speciality", "sections", "students"])
    @get:JsonProperty(value = "users")
    @JsonView(TREE::class)
    @OneToMany(
            targetEntity = User::class,
            mappedBy = "speciality")
    @OrderBy
    var users: MutableList<User> = mutableListOf(User())


    constructor() : this(
            0,
            "Невідомий код",
            "Невідома спеціальність"
    )

    constructor(
            idSpeciality: Int = 0,
            number: String = "Невідомий код",
            name: String = "Невідома спеціальність",
            branch: Branch = Branch()
    ) : this(
            idSpeciality,
            number,
            name
    ) {
        this.branch = branch
    }


    fun fixInitializedAdd(branchesRepository: BranchesRepository): Speciality {
        if (this.branch.idBranch == 0) {
            if (this.branch.number != "Невідомий шифр") {
                this.branch = branchesRepository.get(this.branch.number)
            } else if (this.branch.name != "Невідома галузь") {
                this.branch = branchesRepository.get(name = this.branch.name)
            }
        }
        return this
    }

    fun fixInitializedSet(
            specialitiesRepository: SpecialitiesRepository,
            branchesRepository: BranchesRepository
    ): Speciality {
        if (!this::branch.isInitialized) {
            if (this.branch.number != "Невідомий шифр") {
                this.branch = specialitiesRepository.get(this.number).branch
            } else if (this.branch.name != "Невідома галузь") {
                this.branch = specialitiesRepository.get(name = this.name).branch
            }
        }
        fixInitializedAdd(branchesRepository)
        return this
    }

}
package skaliy.web.server.postgraduatestudies.entities


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView

import javax.persistence.Column
import javax.persistence.Convert
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

import skaliy.web.server.postgraduatestudies.entities.enums.AcademicDegree
import skaliy.web.server.postgraduatestudies.entities.enums.BranchOfScience
import skaliy.web.server.postgraduatestudies.views.View.REST
import skaliy.web.server.postgraduatestudies.views.View.TREE


@Entity(name = "Degree")
@SequenceGenerator(
        name = "degrees_seq",
        sequenceName = "contact_info_id_contact_info_seq",
        schema = "public",
        allocationSize = 1)
@Table(name = "degrees",
        schema = "public",
        indexes = [
            Index(name = "degrees_pkey",
                    columnList = "id_degree",
                    unique = true),
            Index(name = "degrees_id_degree_uindex",
                    columnList = "id_degree",
                    unique = true),
            Index(name = "degrees_name_branch_uindex",
                    columnList = "name, branch",
                    unique = true)])
data class Degree(

        @Column(name = "id_degree",
                nullable = false)
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "degrees_seq")
        @Id
        @get:JsonProperty(value = "id_degree")
        @JsonView(REST::class)
        @NotNull
        val idDegree: Int,

        @Column(name = "name",
                nullable = false)
        @Convert(converter = AcademicDegree.Companion.EnumConverter::class)
        @get:JsonProperty(value = "name")
        @NotNull
        val name: AcademicDegree,

        @Column(name = "branch",
                nullable = false)
        @Convert(converter = BranchOfScience.Companion.EnumConverter::class)
        @get:JsonProperty(value = "branch")
        @NotNull
        val branch: BranchOfScience

) {

    @JsonIgnoreProperties(value = ["degree", "students", "sections"])
    @get:JsonProperty(value = "users")
    @JsonView(TREE::class)
    @OneToMany(
            targetEntity = User::class,
            mappedBy = "degree")
    @OrderBy
    lateinit var users: MutableList<User?>


    constructor() : this(
            0,
            AcademicDegree.UNKNOWN,
            BranchOfScience.UNKNOWN
    )


    override fun toString() =
            """Degree(
                idDegree=$idDegree,
                name=${name.value},
                branch=${branch.value}
                )""".trimMargin()

}
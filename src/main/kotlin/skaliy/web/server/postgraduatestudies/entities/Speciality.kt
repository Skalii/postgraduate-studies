package skaliy.web.server.postgraduatestudies.entities


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
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

import skaliy.web.server.postgraduatestudies.views.View


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
                strategy = GenerationType.SEQUENCE,
                generator = "specialities_seq")
        @Id
        @JsonProperty(value = "id_speciality")
        @JsonView(View.REST::class)
        @NotNull
        val idSpeciality: Int,

        @Column(name = "number",
                nullable = false,
                length = 30)
        @JsonProperty(value = "number")
        @JsonView(View.UI::class)
        @NotNull
        @Size(max = 30)
        val number: String,

        @Column(name = "name",
                nullable = false,
                length = 200)
        @JsonView(View.UI::class)
        @JsonProperty(value = "name")
        @NotNull
        @Size(max = 200)
        val name: String

) {

    @JsonIgnoreProperties(value = ["specialities"])
    @JoinColumn(
            name = "id_branch",
            nullable = false,
            foreignKey = ForeignKey(name = "specialities_branches_fkey"))
    @JsonProperty(value = "branch")
    @JsonView(View.UI::class)
    @ManyToOne(
            targetEntity = Branch::class,
            fetch = FetchType.LAZY,
            optional = false)
    lateinit var branch: Branch

    @JsonIgnoreProperties(value = ["speciality", "students", "sections"])
    @JsonProperty(value = "users")
    @JsonView(View.TREE::class)
    @OneToMany(
            targetEntity = User::class,
            mappedBy = "speciality")
    @OrderBy
    lateinit var users: MutableList<User>


    constructor() : this(
            0,
            "",
            "")

    constructor(
            idSpeciality: Int,
            number: String,
            name: String,
            branch: Branch
    ) : this(
            idSpeciality,
            number,
            name
    ) {
        this.branch = branch
    }

}
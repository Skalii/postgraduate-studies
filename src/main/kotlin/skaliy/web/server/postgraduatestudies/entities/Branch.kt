package skaliy.web.server.postgraduatestudies.entities


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

import skaliy.web.server.postgraduatestudies.views.View.REST
import skaliy.web.server.postgraduatestudies.views.View.TREE


@Entity(name = "Branch")
@SequenceGenerator(
        name = "branches_seq",
        sequenceName = "branches_id_branch_seq",
        schema = "public",
        allocationSize = 1)
@Table(name = "branches",
        schema = "public",
        indexes = [
            Index(name = "branch_pkey",
                    columnList = "id_branch",
                    unique = true),
            Index(name = "branches_id_branch_uindex",
                    columnList = "id_branch",
                    unique = true),
            Index(name = "branches_number_uindex",
                    columnList = "number",
                    unique = true),
            Index(name = "branches_name_uindex",
                    columnList = "name",
                    unique = true)])
data class Branch(

        @Column(name = "id_branch",
                nullable = false)
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "branches_seq")
        @Id
        @get:JsonProperty(value = "id_branch")
        @JsonView(REST::class)
        @NotNull
        val idBranch: Int,

        @Column(name = "number",
                nullable = false,
                length = 30)
        @get:JsonProperty(value = "number")
        @JsonView(REST::class)
        @NotNull
        @Size(max = 30)
        val number: String,

        @Column(name = "name",
                nullable = false,
                length = 200)
        @get:JsonProperty(value = "name")
        @JsonView(REST::class)
        @NotNull
        @Size(max = 200)
        val name: String

) {

    @JsonIgnoreProperties(value = ["branch", "users"])
    @get:JsonProperty(value = "specialities")
    @JsonView(TREE::class)
    @OneToMany(
            targetEntity = Speciality::class,
            mappedBy = "branch")
    @OrderBy
    lateinit var specialities: MutableList<Speciality?>


    constructor() : this(
            0,
            "Невідомий шифр",
            "Невідома галузь"
    )

}
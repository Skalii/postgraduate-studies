package skalii.web.server.postgraduatestudies.entities


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView

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

import skalii.web.server.postgraduatestudies.views.View


@Entity(name = "Section")
@SequenceGenerator(
        name = "sections_seq",
        sequenceName = "sections_id_section_seq",
        schema = "public",
        allocationSize = 1)
@Table(name = "sections",
        schema = "public",
        indexes = [
            Index(name = "sections_pkey",
                    columnList = "id_section",
                    unique = true),
            Index(name = "sections_id_section_uindex",
                    columnList = "id_section",
                    unique = true),
            Index(name = "sections_id_user_number_title_uindex",
                    columnList = "id_user,number,title",
                    unique = true)])
data class Section(

        @Column(name = "id_section",
                nullable = false)
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "sections_seq")
        @Id
        @get:JsonProperty(value = "id_section")
        @JsonView(View.REST::class)
        @NotNull
        val idSection: Int = 0,

        @Column(name = "number",
                nullable = false)
        @get:JsonProperty(value = "number")
        @JsonView(View.REST::class)
        @NotNull
        val number: Int = 1,

        @Column(name = "title",
                nullable = false,
                length = 200)
        @get:JsonProperty(value = "title")
        @JsonView(View.REST::class)
        @NotNull
        @Size(max = 200)
        val title: String = "Новий розділ"

) {

    @JsonIgnoreProperties(value = ["section"])
    @get:JsonProperty(value = "tasks")
    @JsonView(View.STUDENT_TREE::class)
    @OneToMany(
            targetEntity = Task::class,
            mappedBy = "section")
    @OrderBy
    var tasks: MutableList<Task> = mutableListOf(Task())

    @JoinColumn(
            name = "id_user",
            nullable = false,
            foreignKey = ForeignKey(name = "sections_users_fkey"))
    @JsonIgnoreProperties(value = ["students", "sections"])
    @get:JsonProperty(value = "user")
    @JsonView(View.TREE::class)
    @ManyToOne(
            targetEntity = User::class,
            fetch = LAZY,
            optional = false)
    lateinit var user: User


    constructor() : this(
            0,
            1,
            "Новий розділ"
    )

    constructor(
            idSection: Int = 0,
            number: Int = 1,
            title: String = "Новий розділ",
            user: User = User()
    ) : this(
            idSection,
            number,
            title
    ) {
        this.user = user
    }

}
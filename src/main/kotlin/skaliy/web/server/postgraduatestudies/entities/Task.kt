package skaliy.web.server.postgraduatestudies.entities


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView

import java.sql.Timestamp
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.util.Date

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
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import skaliy.web.server.postgraduatestudies.views.View.REST
import skaliy.web.server.postgraduatestudies.views.View.TREE


@Entity(name = "Task")
@SequenceGenerator(
        name = "tasks_seq",
        sequenceName = "tasks_id_task_seq",
        schema = "public",
        allocationSize = 1)
@Table(name = "tasks",
        schema = "public",
        indexes = [
            Index(name = "tasks_pkey",
                    columnList = "id_task",
                    unique = true),
            Index(name = "tasks_id_task_uindex",
                    columnList = "id_task",
                    unique = true),
            Index(name = "tasks_id_section_number_title_uindex",
                    columnList = "id_section,number,title",
                    unique = true)])
data class Task(

        @Column(name = "id_task",
                nullable = false)
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "tasks_seq")
        @Id
        @get:JsonProperty(value = "id_task")
        @JsonView(REST::class)
        @NotNull
        val idTask: Int = 0,

        @Column(name = "number",
                nullable = false)
        @get:JsonProperty(value = "number")
        @JsonView(REST::class)
        @NotNull
        val number: Int = 1,

        @Column(name = "title",
                nullable = false,
                length = 500)
        @get:JsonProperty(value = "title")
        @JsonView(REST::class)
        @NotNull
        @Size(max = 500)
        val title: String = "Нове завдання",

        @Column(name = "balkline",
                nullable = false)
        @get:JsonProperty(value = "balkline")
        @JsonView(REST::class)
        @NotNull
        @Temporal(TemporalType.TIMESTAMP)
        val balkline: Date = Timestamp
                .from(Instant.now(Clock.system(ZoneId.of("Europe/Kiev")))),

        @Column(name = "deadline",
                nullable = false)
        @get:JsonProperty(value = "deadline")
        @JsonView(REST::class)
        @NotNull
        @Temporal(TemporalType.TIMESTAMP)
        val deadline: Date = Timestamp
                .from(Instant.now(Clock.system(ZoneId.of("Europe/Kiev")))
                        .plusSeconds(2419200)),

        @Column(name = "mark_done_user")
        @get:JsonProperty(value = "mark_done_user")
        @JsonView(REST::class)
        val markDoneUser: Boolean? = false,

        @Column(name = "mark_done_instructor")
        @get:JsonProperty(value = "mark_done_instructor")
        @JsonView(REST::class)
        val markDoneInstructor: Boolean? = false,

        @Column(name = "link",
                length = 1000)
        @get:JsonProperty(value = "link")
        @JsonView(REST::class)
        @Size(max = 1000)
        val link: String? = "Невідоме посилання",

        @Column(name = "timestamp_done_user")
        @get:JsonProperty(value = "timestamp_done_user")
        @JsonView(REST::class)
        @Temporal(TemporalType.TIMESTAMP)
        val timestampDoneUser: Date?,

        @Column(name = "timestamp_done_instructor")
        @get:JsonProperty(value = "timestamp_done_instructor")
        @JsonView(REST::class)
        @Temporal(TemporalType.TIMESTAMP)
        var timestampDoneInstructor: Date?

) {

    @JoinColumn(
            name = "id_section",
            nullable = false,
            foreignKey = ForeignKey(name = "tasks_sections_fkey"))
    @JsonIgnoreProperties(value = ["tasks"])
    @get:JsonProperty(value = "section")
    @JsonView(TREE::class)
    @ManyToOne(
            targetEntity = Section::class,
            fetch = LAZY,
            optional = false)
    lateinit var section: Section


    constructor() : this(
            0,
            1,
            "Нове завдання",
            Timestamp.from(Instant.now(Clock.system(ZoneId.of("Europe/Kiev")))),
            Timestamp.from(Instant.now(Clock.system(ZoneId.of("Europe/Kiev")))
                    .plusSeconds(2419200)),
            false,
            false,
            "Невідоме посилання",
            null,
            null)

    constructor(
            idTask: Int = 0,
            number: Int = 1,
            title: String = "Нове завдання",
            balkline: Date = Timestamp.from(Instant.now(Clock.system(ZoneId.of("Europe/Kiev")))),
            deadline: Date = Timestamp.from(Instant.now(Clock.system(ZoneId.of("Europe/Kiev")))
                    .plusSeconds(2419200)),
            markDoneUser: Boolean? = false,
            markDoneInstructor: Boolean? = false,
            link: String? = "Невідоме посилання",
            timestampDoneUser: Date? = null,
            timestampDoneInstructor: Date? = null,
            section: Section
    ) : this(
            idTask,
            number,
            title,
            balkline,
            deadline,
            markDoneUser,
            markDoneInstructor,
            link,
            timestampDoneUser,
            timestampDoneInstructor
    ) {
        this.section = section
    }

}
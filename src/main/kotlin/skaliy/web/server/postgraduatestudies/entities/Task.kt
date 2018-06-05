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
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
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

import skaliy.web.server.postgraduatestudies.views.View


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
                strategy = GenerationType.SEQUENCE,
                generator = "tasks_seq")
        @Id
        @JsonProperty(value = "id_task")
        @JsonView(View.REST::class)
        @NotNull
        val idTask: Int,

        @Column(name = "number",
                nullable = false)
        @JsonProperty(value = "number")
        @JsonView(View.REST::class)
        @NotNull
        val number: Int = 1,

        @Column(name = "title",
                nullable = false,
                length = 500)
        @JsonProperty(value = "title")
        @JsonView(View.REST::class)
        @NotNull
        @Size(max = 500)
        val title: String = "Нове завдання",

        @Column(name = "balkline",
                nullable = false)
        @JsonProperty(value = "balkline")
        @JsonView(View.REST::class)
        @NotNull
        @Temporal(TemporalType.TIMESTAMP)
        val balkline: Date = Timestamp
                .from(Instant.now(Clock.system(ZoneId.of("Europe/Kiev")))),

        @Column(name = "deadline",
                nullable = false)
        @JsonProperty(value = "deadline")
        @JsonView(View.REST::class)
        @NotNull
        @Temporal(TemporalType.TIMESTAMP)
        val deadline: Date = Timestamp
                .from(Instant.now(Clock.system(ZoneId.of("Europe/Kiev")))
                        .plusSeconds(2419200)),

        @Column(name = "mark_done_user")
        @JsonProperty(value = "mark_done_user")
        @JsonView(View.REST::class)
        val markDoneUser: Boolean? = false,

        @Column(name = "mark_done_instructor")
        @JsonProperty(value = "mark_done_instructor")
        @JsonView(View.REST::class)
        val markDoneInstructor: Boolean? = false,

        @Column(name = "link",
                length = 1000)
        @JsonProperty(value = "link")
        @JsonView(View.REST::class)
        @Size(max = 1000)
        val link: String?,

        @Column(name = "timestamp_done_user")
        @JsonProperty(value = "timestamp_done_user")
        @JsonView(View.REST::class)
        @Temporal(TemporalType.TIMESTAMP)
        val timestampDoneUser: Date?,

        @Column(name = "timestamp_done_instructor")
        @JsonProperty(value = "timestamp_done_instructor")
        @JsonView(View.REST::class)
        @Temporal(TemporalType.TIMESTAMP)
        val timestampDoneInstructor: Date?

) {

    @JoinColumn(
            name = "id_section",
            nullable = false,
            foreignKey = ForeignKey(name = "tasks_sections_fkey"))
    @JsonIgnoreProperties(value = ["tasks"])
    @JsonProperty(value = "section")
    @JsonView(View.REST::class)
    @ManyToOne(
            targetEntity = Section::class,
            fetch = FetchType.LAZY,
            optional = false)
    lateinit var section: Section


    constructor() : this(
            0,
            1,
            "Нове завдання",
            Timestamp
                    .from(Instant.now(Clock.system(ZoneId.of("Europe/Kiev")))),
            Timestamp
                    .from(Instant.now(Clock.system(ZoneId.of("Europe/Kiev")))
                            .plusSeconds(2419200)),

            false,
            false,
            "",
            null,
            null)

    constructor(
            idTask: Int,
            number: Int,
            title: String,
            balkline: Timestamp,
            deadline: Timestamp,
            markDoneUser: Boolean?,
            markDoneInstructor: Boolean?,
            link: String?,
            timestampDoneUser: Timestamp?,
            timestampDoneInstructor: Timestamp?,
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
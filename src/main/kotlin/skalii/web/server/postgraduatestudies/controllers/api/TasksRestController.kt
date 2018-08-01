package skalii.web.server.postgraduatestudies.controllers.api


import java.time.Instant

import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import skalii.web.server.postgraduatestudies.entities.Task
import skalii.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skalii.web.server.postgraduatestudies.repositories.SectionsRepository
import skalii.web.server.postgraduatestudies.repositories.TasksRepository
import skalii.web.server.postgraduatestudies.repositories.UsersRepository
import skalii.web.server.postgraduatestudies.views.Json
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


@RequestMapping(
        value = ["/api/tasks"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class TasksRestController(
        val contactInfoRepository: ContactInfoRepository,
        val sectionsRepository: SectionsRepository,
        val tasksRepository: TasksRepository,
        val usersRepository: UsersRepository
) {


    /** ============================== GET requests ============================== */


    @GetMapping(value = ["my-one{-view}"])
    fun getMyOne(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?
    ) =
            Json.get(
                    view,
                    usersRepository.get(authUser.username).run {
                        tasksRepository.get(
                                sectionsRepository.get(
                                        idUser,
                                        sectionNumber,
                                        sectionTitle
                                ),
                                idUser,
                                taskNumber,
                                taskTitle
                        )
                    }
            )

    @GetMapping(value = ["my-all{-view}"])
    fun getMyAll(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.get(
                    view,
                    tasksRepository.getAll(
                            idUser = usersRepository.get(
                                    authUser.username
                            ).idUser
                    )
            )

    @GetMapping(value = ["my-all-by-section{-view}"])
    fun getMyAllBySection(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?
    ) =
            Json.get(
                    view,
                    tasksRepository.getAll(
                            sectionsRepository.get(
                                    usersRepository.get(
                                            authUser.username
                                    ).idUser,
                                    sectionNumber,
                                    sectionTitle
                            ).idSection
                    )
            )

    @GetMapping(value = ["one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) _idUser: Int?,
            @RequestParam(
                    value = "task_number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) title: String?,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            Json.get(
                    view,
                    usersRepository.get(
                            email,
                            phoneNumber,
                            _idUser
                    ).run {
                        tasksRepository.get(
                                sectionsRepository.get(
                                        idUser,
                                        sectionNumber,
                                        sectionTitle,
                                        idSection
                                ),
                                idUser,
                                number,
                                title,
                                idTask
                        )
                    }
            )

    @GetMapping(value = ["all{-view}"])
    fun getAll(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) _idUser: Int?
    ) =
            Json.get(
                    view,
                    usersRepository.get(
                            email,
                            phoneNumber,
                            _idUser
                    ).run {
                        tasksRepository.getAll(
                                sectionsRepository.get(
                                        idUser,
                                        sectionNumber,
                                        sectionTitle,
                                        idSection
                                ).idSection,
                                idUser
                        )
                    }
            )


    /** ============================== POST requests ============================== */


    @PostMapping(value = ["my{-view}"])
    fun addMy(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestBody newTask: Task
    ) =
            Json.get(
                    view,
                    tasksRepository.add(
                            newTask.fixInitializedAdd(
                                    sectionsRepository,
                                    usersRepository,
                                    authUser.username
                            ).section.idSection,
                            newTask.title,
                            newTask.balkline.toString(),
                            newTask.deadline.toString(),
                            newTask.link
                    )
            )


    /** ============================== PUT requests ============================== */


    @PutMapping(value = ["my{-view}"])
    fun setMy(
            @PathVariable(value = "-view") view: String,
            @RequestBody changedTask: Task,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            Json.get(
                    view,
                    tasksRepository.set(
                            changedTask.fixInitializedSet(
                                    tasksRepository,
                                    sectionsRepository,
                                    usersRepository,
                                    authUser.username
                            ),
                            changedTask.section,
                            changedTask.section.user.idUser,
                            number,
                            title,
                            idTask
                    )

            )

    @PutMapping(value = ["my-mark-student{-view}"])
    fun setMyMarkUser(
            @PathVariable(value = "-view") view: String,
            @RequestBody changedTask: Task,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            tasksRepository.run {
                val student = usersRepository.get(authUser.username)
                val found =
                        setMarkUser(
                                changedTask.markDoneUser,
                                get(
                                        changedTask.fixInitializedSet(
                                                tasksRepository,
                                                sectionsRepository,
                                                usersRepository,
                                                user = student
                                        ).section,
                                        student.idUser,
                                        changedTask.number,
                                        changedTask.title
                                ).idTask
                        )
                Json.get(
                        view,
                        found,
                        "mark_done_user\":${found.markDoneUser}"
                                to "mark_done_user\":${changedTask.markDoneUser}",
                        "timestamp_done_user\":${found.timestampDoneUser}".run {
                            if (found.markDoneUser == true) {
                                "timestamp_done_user\":\"${found.timestampDoneUser}".substring(0, 41) + "\""
                            } else "timestamp_done_user\":${found.timestampDoneUser}"
                        }
                                to "timestamp_done_user\":${
                        if (changedTask.markDoneUser == true) {
                            "\"${DateTimeFormatter
                                    .ofPattern("yyyy-MM-dd HH:mm:ss")
                                    .withZone(ZoneOffset.UTC)
                                    .format(Instant.now())}\""
                        } else {
                            "null"
                        }
                        }"
                )
            }

    @PutMapping(value = ["my-mark-instructor{-view}"])
    fun setMyMarkInstructor(
            @PathVariable(value = "-view") view: String,
            @RequestBody changedTask: Task,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "id_student",
                    required = false) idUser: Int?
    ) =
            tasksRepository.run {
                val student = usersRepository.get(
                        email,
                        phoneNumber,
                        idUser
                )
                val found = get(
                        changedTask.fixInitializedSet(
                                tasksRepository,
                                sectionsRepository,
                                usersRepository,
                                student.contactInfo.email
                        ).section,
                        student.idUser,
                        changedTask.number,
                        changedTask.title
                )
                if (student.studyInfo?.instructor?.contactInfo?.email == authUser.username) {
                    setMarkInstructor(
                            changedTask.markDoneInstructor,
                            found.idTask
                    )
                    println("this")
                    return@run Json.get(
                            view,
                            found,
                            "mark_done_instructor\":${found.markDoneInstructor}"
                                    to "mark_done_instructor\":${changedTask.markDoneInstructor}",
                            "timestamp_done_instructor\":${found.timestampDoneInstructor}".run {
                                if (found.markDoneInstructor == true) {
                                    "timestamp_done_instructor\":\"${found.timestampDoneInstructor}".substring(0, 47) + "\""
                                } else "timestamp_done_instructor\":${found.timestampDoneInstructor}"
                            }
                                    to "timestamp_done_instructor\":${
                            if (changedTask.markDoneInstructor == true) {
                                "\"${DateTimeFormatter
                                        .ofPattern("yyyy-MM-dd HH:mm:ss")
                                        .withZone(ZoneOffset.UTC)
                                        .format(Instant.now())}\""
                            } else {
                                "null"
                            }
                            }"
                    )
                } else {
                    return@run Json.get(
                            view,
                            found
                    )
                }
            }

    @PutMapping(value = ["one{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody changedTask: Task,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            Json.get(
                    view,
                    tasksRepository.set(
                            changedTask.fixInitializedSet(
                                    tasksRepository,
                                    sectionsRepository,
                                    usersRepository
                            ),
                            changedTask.section,
                            changedTask.section.user.idUser,
                            number,
                            title,
                            idTask
                    )

            )


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["my-one{-view}"])
    fun deleteMy(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?
    ) =
            Json.get(
                    view,
                    tasksRepository.delete(
                            sectionsRepository.get(
                                    usersRepository.get(
                                            authUser.username
                                    ).idUser,
                                    sectionNumber,
                                    sectionTitle
                            ).idSection,
                            taskNumber,
                            taskTitle
                    )
            )

    @DeleteMapping(value = ["my-all-by-section{-view}"])
    fun deleteMyAll(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?
    ) =
            Json.get(
                    view,
                    tasksRepository.deleteAll(
                            sectionsRepository.get(
                                    usersRepository.get(
                                            authUser.username
                                    ).idUser,
                                    sectionNumber,
                                    sectionTitle
                            )
                    )
            )

    @DeleteMapping(value = ["one{-view}"])
    fun delete(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            Json.get(
                    view,
                    tasksRepository.delete(
                            sectionsRepository.get(
                                    idUser ?: usersRepository.get(
                                            email,
                                            phoneNumber
                                    ).idUser,
                                    sectionNumber,
                                    sectionTitle,
                                    idSection
                            ).idSection,
                            taskNumber,
                            taskTitle,
                            idTask
                    )
            )

    @GetMapping(value = ["all-by-section{-view}"])
    fun deleteAll(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?
    ) =
            Json.get(
                    view,
                    tasksRepository.deleteAll(
                            sectionsRepository.get(
                                    idUser ?: usersRepository.get(
                                            email,
                                            phoneNumber
                                    ).idUser,
                                    sectionNumber,
                                    sectionTitle,
                                    idSection
                            )
                    )
            )

}
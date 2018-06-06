package skaliy.web.server.postgraduatestudies.controllers.api


import java.sql.Timestamp
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

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

import skaliy.web.server.postgraduatestudies.entities.Task
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.SectionsRepository
import skaliy.web.server.postgraduatestudies.repositories.TasksRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.Json


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


    /**
     *
     *      GET / SELECT
     *      requests
     *
     */


    /** ============================== MY
     *                                 ONE ============================== */


    @GetMapping(value = ["get/my-one{-view}"])
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
            contactInfoRepository.get(
                    authUser.username
            ).user.run {
                return@run Json.get(
                        view,
                        tasksRepository.get(
                                section = sectionsRepository.get(
                                        user = this,
                                        number = sectionNumber,
                                        title = sectionTitle
                                ),
                                user = this,
                                number = taskNumber,
                                title = taskTitle
                        )
                )
            }


    /** ============================== MY
     *                                 ALL ============================== */


    @GetMapping(value = ["get/my-all{-view}"])
    fun getMyAll(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.get(
                    view,
                    tasksRepository.getAll(
                            user = contactInfoRepository.get(
                                    authUser.username
                            ).user
                    )
            )


    /** ============================== MY
     *                                 ALL
     *                                 BY
     *                                 SECTION ============================== */


    @GetMapping(value = ["get/my-all-by-section{-view}"])
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
            tasksRepository.getAll(
                    sectionsRepository.get(
                            user = contactInfoRepository.get(
                                    authUser.username
                            ).user,
                            number = sectionNumber,
                            title = sectionTitle
                    )
            )


    /** ============================== ONE ============================== */


    @GetMapping(value = ["get/one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "task_number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) title: String?
    ) =
            contactInfoRepository.get(
                    email,
                    phoneNumber,
                    usersRepository.get(idUser)
            ).user.run {

                return@run Json.get(
                        view,
                        tasksRepository.get(
                                idTask,
                                sectionsRepository.get(
                                        this,
                                        sectionNumber,
                                        sectionTitle,
                                        idSection
                                ),
                                this,
                                number,
                                title
                        )
                )

            }


    /** ============================== ALL ============================== */


    @GetMapping(value = ["get/all{-view}"])
    fun getAll(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?
    ) =
            contactInfoRepository.get(
                    email,
                    phoneNumber,
                    usersRepository.get(idUser)
            ).user.run {

                return@run Json.get(
                        view,
                        tasksRepository.getAll(
                                sectionsRepository.get(
                                        this,
                                        sectionNumber,
                                        sectionTitle,
                                        idSection
                                ),
                                this
                        )
                )

            }


    /**
     *
     *      ADD / INSERT INTO
     *      requests
     *
     */


    /** ============================== MY ============================== */


    @PostMapping(value = ["post/add-my{-view}"])
    fun add(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestBody task: Task
    ) =
            tasksRepository.add(
                    sectionsRepository.get(
                            contactInfoRepository.get(
                                    authUser.username
                            ).user,
                            sectionNumber,
                            sectionTitle
                    ).idSection,
                    task.title,
                    Timestamp.from(task.balkline.toInstant()).toString(),
                    Timestamp.from(task.deadline.toInstant()).toString(),
                    task.link
            )


    /**
     *
     *      SET / UPDATE
     *      requests
     *
     */


    /** ============================== MY ============================== */

    // todo check for work

    @PutMapping(value = ["put/set-my{-view}"])
    fun setMy(
            @PathVariable(value = "-view") view: String,
            @RequestBody newTask: Task,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
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
            contactInfoRepository.get(
                    authUser.username
            ).user.run {

                val task =
                        tasksRepository.set(
                                newTask,
                                idTask,
                                sectionsRepository.get(
                                        this,
                                        sectionNumber,
                                        sectionTitle
                                ),
                                this,
                                taskNumber,
                                taskTitle
                        )

                return@run Json.get(
                        view,
                        Task(
                                task.idTask,
                                newTask.number,
                                newTask.title,
                                newTask.balkline,
                                newTask.deadline,
                                newTask.markDoneUser,
                                newTask.markDoneInstructor,
                                newTask.link,
                                if (newTask.markDoneUser == false
                                        || newTask.markDoneUser == null) null
                                else Timestamp
                                        .from(Instant.now(Clock.system(ZoneId.of("Europe/Kiev")))),
                                if (newTask.markDoneInstructor == false
                                        || newTask.markDoneInstructor == null) null
                                else Timestamp
                                        .from(Instant.now(Clock.system(ZoneId.of("Europe/Kiev")))),
                                sectionsRepository.get(task)
                        )
                )
            }


    /*: Task? {
        val task =
                tasksRepository.set(
                        newTask,
                        idTask,
                        sectionsRepository.get(
                                user = contactInfoRepository.get(
                                        email = authUser.username
                                ).user,
                                number = sectionNumber,
                                title = sectionTitle
                        ),
                        contactInfoRepository.get(
                                email = authUser.username
                        ).user,
                        taskNumber,
                        taskTitle
                )
        return tasksRepository.get(task?.idTask)
    }*/


    /** ============================== MARK
     *                                 INSTRUCTOR ============================== */

    // todo check for work

    @PutMapping(value = ["put/set-mark-instructor{-view}"])
    fun setMarkInstructor(
            @PathVariable(value = "-view") view: String,
            @RequestBody task: Task,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.get(
                    view,
                    task.run {

                        section.user = usersRepository.get(section)

                        return@run if (section.user.studyInfo?.instructor?.contactInfo?.email == authUser.username) {

                            tasksRepository.setMarkInstructor(this)

                            this.also {
                                timestampDoneInstructor =
                                        if (markDoneInstructor == false
                                                || markDoneInstructor == null) null
                                        else Timestamp
                                                .from(Instant.now(Clock.system(ZoneId.of("Europe/Kiev"))))

                            }

                        } else Task()

                    }
            )


    /*: Task? {

        task.section.user = usersRepository.get(task.section)!!

        if (task.section.user.studyInfo?.instructor?.contactInfo?.email == authUser.username) {

            val newTask =
                    tasksRepository.setMarkInstructor(task)
            return tasksRepository.get(newTask.idTask)
        }

        return Task()
    }*/


    /** ============================== ONE ============================== */


    @PutMapping(value = ["put/set{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody newTask: Task,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?
    ) =
            usersRepository.get(
                    idUser,
                    contactInfoRepository.get(
                            email,
                            phoneNumber
                    )
            ).run {

                val task =
                        tasksRepository.set(
                                newTask,
                                idTask,
                                sectionsRepository.get(
                                        this,
                                        sectionNumber,
                                        sectionTitle
                                ),
                                this,
                                taskNumber,
                                taskTitle
                        )

                return@run Json.get(
                        view,
                        Task(
                                task.idTask,
                                newTask.number,
                                newTask.title,
                                newTask.balkline,
                                newTask.deadline,
                                newTask.markDoneUser,
                                newTask.markDoneInstructor,
                                newTask.link,
                                if (newTask.markDoneUser == false
                                        || newTask.markDoneUser == null) null
                                else Timestamp
                                        .from(Instant.now(Clock.system(ZoneId.of("Europe/Kiev")))),
                                if (newTask.markDoneInstructor == false
                                        || newTask.markDoneInstructor == null) null
                                else Timestamp
                                        .from(Instant.now(Clock.system(ZoneId.of("Europe/Kiev")))),
                                sectionsRepository.get(task)
                        )
                )
            }


    /**
     *
     *      DELETE
     *      requests
     *
     */


    /** ============================== MY ============================== */


    @DeleteMapping(value = ["delete/my{-view}"])
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
                            section = sectionsRepository.get(
                                    contactInfoRepository.get(
                                            authUser.username
                                    ).user,
                                    sectionNumber,
                                    sectionTitle
                            ),
                            number = taskNumber,
                            title = taskTitle
                    )
            )


    /** ============================== MY
     *                                 ALL ============================== */


    @DeleteMapping(value = ["delete/my-all{-view}"])
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
                                    contactInfoRepository.get(
                                            authUser.username
                                    ).user,
                                    sectionNumber,
                                    sectionTitle
                            )
                    )
            )


    /** ============================== ONE ============================== */


    @DeleteMapping(value = ["delete/one{-view}"])
    fun delete(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
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
                            idTask,
                            sectionsRepository.get(
                                    usersRepository.get(
                                            idUser,
                                            contactInfoRepository.get(
                                                    email,
                                                    phoneNumber
                                            )
                                    ),
                                    sectionNumber,
                                    sectionTitle,
                                    idSection
                            ),
                            taskNumber,
                            taskTitle
                    )
            )


    /** ============================== ALL ============================== */


    @GetMapping(value = ["delete/all{-view}"])
    fun deleteAll(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?
    ) =
            Json.get(
                    view,
                    tasksRepository.deleteAll(
                            sectionsRepository.get(
                                    contactInfoRepository.get(
                                            email,
                                            phoneNumber,
                                            usersRepository.get(idUser)
                                    ).user,
                                    sectionNumber,
                                    sectionTitle,
                                    idSection
                            )
                    )
            )

}
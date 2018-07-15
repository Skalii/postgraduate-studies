package skalii.web.server.postgraduatestudies.controllers.api


import java.time.Instant
import java.util.Date

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
            usersRepository.get(authUser.username).run {

                return@run Json.get(
                        view,
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
                )

            }

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
            usersRepository.get(
                    email,
                    phoneNumber,
                    _idUser
            ).run {

                return@run Json.get(
                        view,
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
                )

            }

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
            usersRepository.get(
                    email,
                    phoneNumber,
                    _idUser
            ).run {

                return@run Json.get(
                        view,
                        tasksRepository.getAll(
                                sectionsRepository.get(
                                        idUser,
                                        sectionNumber,
                                        sectionTitle,
                                        idSection
                                ).idSection,
                                idUser
                        )
                )

            }


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
                                    usersRepository
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
                                    usersRepository
                            ),
                            changedTask.section,
                            changedTask.section.user.idUser,
                            number,
                            title,
                            idTask
                    )

            )

    @PutMapping(value = ["mark-instructor{-view}"])
    fun setMarkInstructor(
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
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            Json.get(
                    view,
                    changedTask.run {

                        var thisIdTask = 0

                        if (idTask != 0) {
                            thisIdTask = idTask
                        } else {

                            try {
                                if (section.idSection == 0) {
                                    throw UninitializedPropertyAccessException()
                                }
                            } catch (e: UninitializedPropertyAccessException) {
                                section =
                                        sectionsRepository.get(
                                                idUser ?: usersRepository.get(
                                                        email,
                                                        phoneNumber
                                                ).idUser,
                                                sectionNumber,
                                                sectionTitle,
                                                idSection
                                        )
                            } finally {
                                section.tasks.forEach {
                                    if (it.number == number || it.title == title) {
                                        thisIdTask = it.idTask
                                    }
                                }
                            }

                            if (thisIdTask == 0) {
                                return@run Task()
                            }

                        }

                        return@run if (section.user.studyInfo?.instructor?.contactInfo?.email == authUser.username) {

                            tasksRepository.setMarkInstructor(
                                    markDoneInstructor,
                                    thisIdTask
                            )

                            val newTask = tasksRepository.get(idTask = thisIdTask)

                            Task(
                                    thisIdTask,
                                    newTask.number,
                                    newTask.title,
                                    newTask.balkline,
                                    newTask.balkline,
                                    newTask.markDoneUser,
                                    markDoneInstructor,
                                    newTask.link,
                                    newTask.timestampDoneUser,
                                    if (markDoneInstructor == false
                                            || markDoneInstructor == null) null
                                    else Date.from(Instant.now()),
                                    section
                            )

                        } else Task()

                    }
            )

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
package skaliy.web.server.postgraduatestudies.controllers.api


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

import skaliy.web.server.postgraduatestudies.entities.Section
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.SectionsRepository
import skaliy.web.server.postgraduatestudies.repositories.TasksRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.Json


@RequestMapping(
        value = ["/api/sections"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class SectionsRestController(
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
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            Json.get(
                    view,
                    sectionsRepository.get(
                            usersRepository.get(
                                    authUser.username
                            ).idUser,
                            number,
                            title
                    )
            )


    /** ============================== MY
     *                                 ALL ============================== */


    @GetMapping(value = ["get/my-all{-view}"])
    fun getMyAll(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.get(
                    view,
                    sectionsRepository.getAll(
                            usersRepository.get(
                                    authUser.username
                            )
                    )
            )


    /** ============================== ONE ============================== */


    @GetMapping(value = ["get/one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
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
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            Json.get(
                    view,
                    sectionsRepository.get(
                            idUser ?: usersRepository.get(
                                    email,
                                    phoneNumber
                            ).idUser,
                            number,
                            title,
                            idSection
                    )
            )


    /** ============================== ONE
     *                                 BY
     *                                 TASK ============================== */


    @GetMapping(value = ["get/one-by-task{-view}"])
    fun getOneByTask(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            Json.get(
                    view,
                    sectionsRepository.get(
                            tasksRepository.get(
                                    idTask = idTask
                            )
                    )
            )


    /** ============================== ALL ============================== */


    @GetMapping(value = ["get/all{-view}"])
    fun getAll(
            @PathVariable(value = "-view") view: String,
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
                    sectionsRepository.getAll(
                            usersRepository.get(
                                    email,
                                    phoneNumber,
                                    idUser
                            )
                    )
            )


    /**
     *
     *      ADD / INSERT INTO
     *      requests
     *
     */


    /** ============================== MY ============================== */


    @PostMapping(value = ["post/add-my{-view}"])
    fun addMy(
            @PathVariable(value = "-view") view: String,
            @RequestBody section: Section,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.get(
                    view,
                    sectionsRepository.add(
                            usersRepository.get(
                                    authUser.username
                            ).idUser,
                            section.number,
                            section.title
                    )
            )


    /** ============================== ONE ============================== */


    @PostMapping(value = ["post/add{-view}"])
    fun add(
            @PathVariable(value = "-view") view: String,
            @RequestBody section: Section,
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
                    sectionsRepository.add(
                            idUser ?: usersRepository.get(
                                    email,
                                    phoneNumber
                            ).idUser,
                            section.number,
                            section.title
                    )
            )


    /**
     *
     *      SET / UPDATE
     *      requests
     *
     */


    /** ============================== MY ============================== */


    @PutMapping(value = ["put/set-my{-view}"])
    fun setMy(
            @PathVariable(value = "-view") view: String,
            @RequestBody newSection: Section,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "number",
                    required = false) _number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.get(
                    usersRepository.get(
                            authUser.username
                    ).idUser,
                    _number,
                    title
            ).run {

                sectionsRepository.set(
                        newSection,
                        idSection
                )

                return@run Json.get(
                        view,
                        Section(
                                idSection,
                                if (newSection.number == 1) number
                                else newSection.number,
                                newSection.title,
                                user
                        )
                )

            }


    /** ============================== ONE ============================== */


    @PutMapping(value = ["put/set{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody newSection: Section,
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
                    value = "number",
                    required = false) _number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) _title: String?,
            @RequestParam(
                    value = "id_section",
                    required = false) _idSection: Int?
    ) =
            sectionsRepository.get(
                    idUser ?: usersRepository.get(
                            email,
                            phoneNumber
                    ).idUser,
                    _number,
                    _title,
                    _idSection
            ).run {

                sectionsRepository.set(
                        newSection,
                        user.idUser,
                        number,
                        title,
                        idSection
                )

                return@run Json.get(
                        view,
                        Section(
                                idSection,
                                if (newSection.number == 1) number
                                else newSection.number,
                                newSection.title,
                                user
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
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            Json.get(
                    view,
                    sectionsRepository.delete(
                            usersRepository.get(
                                    authUser.username
                            ).idUser,
                            number,
                            title
                    )
            )


    /** ============================== MY
     *                                 ALL ============================== */


    @DeleteMapping(value = ["delete/my-all{-view}"])
    fun deleteMyAll(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.get(
                    view,
                    sectionsRepository.deleteAll(
                            usersRepository.get(
                                    authUser.username
                            )
                    )
            )

    /** ============================== ONE ============================== */


    @DeleteMapping(value = ["delete/one{-view}"])
    fun delete(
            @PathVariable(value = "-view") view: String,
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
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            Json.get(
                    view,
                    sectionsRepository.delete(
                            idUser ?: usersRepository.get(
                                    email,
                                    phoneNumber
                            ).idUser,
                            number,
                            title,
                            idSection
                    )
            )


    /** ============================== ALL
     *                                 BY
     *                                 USER ============================== */


    @DeleteMapping(value = ["delete/all-by-user{-view}"])
    fun deleteAllByUser(
            @PathVariable(value = "-view") view: String,
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
                    sectionsRepository.deleteAll(
                            usersRepository.get(
                                    email,
                                    phoneNumber,
                                    idUser
                            )
                    )
            )

}
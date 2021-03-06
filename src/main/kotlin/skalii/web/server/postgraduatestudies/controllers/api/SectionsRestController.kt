package skalii.web.server.postgraduatestudies.controllers.api


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

import skalii.web.server.postgraduatestudies.entities.Section
import skalii.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skalii.web.server.postgraduatestudies.repositories.SectionsRepository
import skalii.web.server.postgraduatestudies.repositories.TasksRepository
import skalii.web.server.postgraduatestudies.repositories.UsersRepository
import skalii.web.server.postgraduatestudies.views.Json


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


    /** ============================== GET requests ============================== */


    @GetMapping(value = ["my-one{-view}"])
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

    @GetMapping(value = ["my-all{-view}"])
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

    @GetMapping(value = ["one{-view}"])
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

    @GetMapping(value = ["one-by-task{-view}"])
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

    @GetMapping(value = ["all{-view}"])
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


    /** ============================== POST requests ============================== */


    @PostMapping(value = ["my{-view}"])
    fun addMy(
            @PathVariable(value = "-view") view: String,
            @RequestBody newSection: Section,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.get(
                    view,
                    sectionsRepository.add(
                            usersRepository.get(
                                    authUser.username
                            ).idUser,
                            newSection.number,
                            newSection.title
                    )
            )

    @PostMapping(value = ["one{-view}"])
    fun add(
            @PathVariable(value = "-view") view: String,
            @RequestBody newSection: Section,
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
                            ).idUser ?: newSection.fixInitializedAdd(
                                    usersRepository
                            ).user.idUser,
                            newSection.number,
                            newSection.title
                    )
            )


    /** ============================== PUT requests ============================== */


    @PutMapping(value = ["my{-view}"])
    fun setMy(
            @PathVariable(value = "-view") view: String,
            @RequestBody changedSection: Section,
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
                    sectionsRepository.set(
                            changedSection.fixInitializedSet(
                                    sectionsRepository,
                                    usersRepository
                            ),
                            usersRepository.get(
                                    authUser.username
                            ).idUser,
                            number,
                            title
                    )
            )

    @PutMapping(value = ["one{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody changedSection: Section,
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
                    sectionsRepository.set(
                            changedSection.fixInitializedSet(
                                    sectionsRepository,
                                    usersRepository
                            ),
                            changedSection.user.idUser,
                            number,
                            title,
                            idSection
                    )
            )


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["my-one{-view}"])
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

    @DeleteMapping(value = ["my-all{-view}"])
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

    @DeleteMapping(value = ["one{-view}"])
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

    @DeleteMapping(value = ["all-by-user{-view}"])
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
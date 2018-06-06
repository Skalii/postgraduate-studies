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

import skaliy.web.server.postgraduatestudies.entities.Degree
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.DegreesRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.Json


@RequestMapping(
        value = ["/api/degrees"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class DegreesRestController(
        val contactInfoRepository: ContactInfoRepository,
        val degreesRepository: DegreesRepository,
        val usersRepository: UsersRepository
) {


    /**
     *
     *      GET / SELECT
     *      requests
     *
     */


    /** ============================== MY ============================== */


    @GetMapping(value = ["get/my{-view}"])
    fun getMy(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.get(
                    view,
                    degreesRepository.get(
                            contactInfoRepository.get(
                                    authUser.username
                            ).user
                    )
            )


    /** ============================== ONE ============================== */


    @GetMapping(value = ["get/one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            Json.get(
                    view,
                    degreesRepository.get(
                            idDegree,
                            name,
                            branch
                    )
            )


    /** ============================== ONE
     *                                 BY
     *                                 USER ============================== */


    @GetMapping(value = ["get/one-by-user{-view}"])
    fun getOneByUser(
            @PathVariable(value = "-view") view: String,
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
                    degreesRepository.get(
                            usersRepository.get(
                                    idUser,
                                    contactInfoRepository.get(
                                            email,
                                            phoneNumber
                                    )
                            )
                    )
            )


    /** ============================== ALL ============================== */


    @GetMapping(value = ["get/all{-view}"])
    fun getAll(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            Json.get(
                    view,
                    degreesRepository.getAll(
                            allRecords,
                            name,
                            branch
                    )
            )


    /**
     *
     *      ADD / INSERT INTO
     *      requests
     *
     */


    /** ============================== ONE ============================== */


    @PostMapping(value = ["post/add{-view}"])
    fun add(
            @PathVariable(value = "-view") view: String,
            @RequestBody degree: Degree
    ) =
            Json.get(
                    view,
                    degreesRepository.add(
                            degree.name.value,
                            degree.branch.value
                    )
            )


    /**
     *
     *      SET / UPDATE
     *      requests
     */


    /** ============================== ONE ============================== */


    @PutMapping(value = ["put/set{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody newDegree: Degree,
            @RequestParam(
                    value = "id_degree",
                    required = false) _idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.get(
                    _idDegree,
                    name,
                    branch
            ).run {

                degreesRepository.set(
                        newDegree,
                        idDegree
                )

                return@run Json.get(
                        view,
                        Degree(
                                idDegree,
                                newDegree.name,
                                newDegree.branch
                        )
                )

            }


    /**
     *
     *      DELETE
     *      requests
     *
     */


    /** ============================== ONE ============================== */


    @DeleteMapping(value = ["delete/one{-view}"])
    fun deleteTree(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            Json.get(
                    view,
                    degreesRepository.delete(
                            idDegree,
                            name,
                            branch
                    )
            )

}
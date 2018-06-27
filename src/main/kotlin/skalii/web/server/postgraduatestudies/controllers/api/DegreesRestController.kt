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

import skalii.web.server.postgraduatestudies.entities.Degree
import skalii.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skalii.web.server.postgraduatestudies.repositories.DegreesRepository
import skalii.web.server.postgraduatestudies.repositories.UsersRepository
import skalii.web.server.postgraduatestudies.views.Json


@RequestMapping(
        value = ["/api/degrees"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class DegreesRestController(
        val contactInfoRepository: ContactInfoRepository,
        val degreesRepository: DegreesRepository,
        val usersRepository: UsersRepository
) {


    /** ============================== GET requests ============================== */


    @GetMapping(value = ["my{-view}"])
    fun getMy(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.get(
                    view,
                    degreesRepository.get(
                            usersRepository.get(
                                    authUser.username
                            )
                    )
            )

    @GetMapping(value = ["one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?
    ) =
            Json.get(
                    view,
                    degreesRepository.get(
                            name,
                            branch,
                            idDegree
                    )
            )

    @GetMapping(value = ["one-by-user{-view}"])
    fun getOneByUser(
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
                    degreesRepository.get(
                            usersRepository.get(
                                    email,
                                    phoneNumber,
                                    idUser
                            )
                    )
            )

    @GetMapping(value = ["all{-view}"])
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


    /** ============================== POST requests ============================== */


    @PostMapping(value = ["one{-view}"])
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


    /** ============================== PUT requests ============================== */


    @PutMapping(value = ["one{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody newDegree: Degree,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?,
            @RequestParam(
                    value = "id_degree",
                    required = false) _idDegree: Int?
    ) =
            degreesRepository.get(
                    name,
                    branch,
                    _idDegree
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


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["one{-view}"])
    fun delete(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?
    ) =
            Json.get(
                    view,
                    degreesRepository.delete(
                            name,
                            branch,
                            idDegree
                    )
            )

}
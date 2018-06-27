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

import skalii.web.server.postgraduatestudies.entities.Institute
import skalii.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skalii.web.server.postgraduatestudies.repositories.DepartmentsRepository
import skalii.web.server.postgraduatestudies.repositories.InstitutesRepository
import skalii.web.server.postgraduatestudies.repositories.UsersRepository
import skalii.web.server.postgraduatestudies.views.Json


@RequestMapping(
        value = ["/api/institutes"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class InstitutesRestController(
        val contactInfoRepository: ContactInfoRepository,
        val departmentsRepository: DepartmentsRepository,
        val institutesRepository: InstitutesRepository,
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
                    institutesRepository.get(
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
                    value = "id_institute",
                    required = false) idInstitute: Int?
    ) =
            Json.get(
                    view,
                    institutesRepository.get(
                            name,
                            idInstitute
                    )
            )

    @GetMapping(value = ["one-by-department{-view}"])
    fun getOneByDepartment(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "department_name",
                    required = false) departmentName: String?,
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?
    ) =
            Json.get(
                    view,
                    institutesRepository.get(
                            departmentsRepository.get(
                                    departmentName,
                                    idDepartment
                            )
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
                    institutesRepository.get(
                            usersRepository.get(
                                    email,
                                    phoneNumber,
                                    idUser
                            )
                    )
            )

    @GetMapping(value = ["all{-view}"])
    fun getAll(@PathVariable(value = "-view") view: String) =
            Json.get(
                    view,
                    institutesRepository.getAll()
            )


    /** ============================== POST requests ============================== */


    @PostMapping(value = ["one{-view}"])
    fun add(
            @PathVariable(value = "-view") view: String,
            @RequestBody institute: Institute
    ) =
            Json.get(
                    view,
                    institutesRepository.add(
                            institute.name,
                            institute.namedAfter,
                            institute.abbreviation
                    )
            )


    /** ============================== PUT requests ============================== */


    @PutMapping(value = ["one{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody newInstitute: Institute,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "id_institute",
                    required = false) _idInstitute: Int?
    ) =
            institutesRepository.get(
                    name,
                    _idInstitute
            ).run {

                institutesRepository.set(
                        newInstitute,
                        idInstitute
                )

                return@run Json.get(
                        view,
                        Institute(
                                idInstitute,
                                newInstitute.name,
                                newInstitute.namedAfter,
                                newInstitute.abbreviation
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
                    value = "id_institute",
                    required = false) idInstitute: Int?
    ) =
            Json.get(
                    view,
                    institutesRepository.delete(
                            name,
                            idInstitute
                    )
            )

}
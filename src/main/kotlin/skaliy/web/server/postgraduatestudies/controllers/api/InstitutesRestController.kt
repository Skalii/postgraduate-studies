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

import skaliy.web.server.postgraduatestudies.entities.ContactInfo
import skaliy.web.server.postgraduatestudies.entities.Department
import skaliy.web.server.postgraduatestudies.entities.Faculty
import skaliy.web.server.postgraduatestudies.entities.Institute
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.DepartmentsRepository
import skaliy.web.server.postgraduatestudies.repositories.InstitutesRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.Json


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
                    institutesRepository.get(
                            contactInfoRepository.get(
                                    email = authUser.username
                            )?.user
                    )
            )


    /** ============================== ONE ============================== */


    @GetMapping(value = ["get/one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            Json.get(
                    view,
                    institutesRepository.get(
                            idInstitute,
                            name
                    )
                            ?: Institute()
            )


    /** ============================== ONE
     *                                 BY
     *                                 DEPARTMENT ============================== */


    @GetMapping(value = ["get/one-by-department{-view}"])
    fun getOneByDepartment(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "department_name",
                    required = false) departmentName: String?
    ) =
            Json.get(
                    view,
                    institutesRepository.get(
                            departmentsRepository.get(
                                    idDepartment,
                                    departmentName
                            )
                                    ?: Department()
                                            .also {
                                                it.institute = Institute()
                                                it.faculty = Faculty()
                                            }
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
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            Json.get(
                    view,
                    institutesRepository.get(
                            usersRepository.get(
                                    idUser,
                                    contactInfoRepository.get(
                                            phoneNumber = phoneNumber,
                                            email = email
                                    )
                                            ?: ContactInfo()
                            )
                    )
            )


    /** ============================== ALL ============================== */


    @GetMapping(value = ["get/all{-view}"])
    fun getAll(@PathVariable(value = "-view") view: String) =
            Json.get(
                    view,
                    institutesRepository.getAll()
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


    /**
     *
     *      SET / UPDATE
     *      requests
     *
     */


    /** ============================== ONE ============================== */


    @PutMapping(value = ["put/set{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody newInstitute: Institute,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            Json.get(
                    view,
                    Institute(
                            institutesRepository.set(
                                    newInstitute,
                                    idInstitute ?: institutesRepository.get(
                                            name = name
                                    )!!.idInstitute
                            )!!.idInstitute,
                            newInstitute.name,
                            newInstitute.namedAfter,
                            newInstitute.abbreviation
                    )
            )


    /**
     *
     *      DELETE
     *      requests
     *
     */


    /** ============================== ONE ============================== */


    @DeleteMapping(value = ["delete/one{-view}"])
    fun delete(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            Json.get(
                    view,
                    institutesRepository.delete(
                            idInstitute,
                            name
                    )
                            ?: Institute()
            )

}
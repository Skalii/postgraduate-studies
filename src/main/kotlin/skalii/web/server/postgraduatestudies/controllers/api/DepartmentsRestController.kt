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

import skalii.web.server.postgraduatestudies.entities.Department
import skalii.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skalii.web.server.postgraduatestudies.repositories.DepartmentsRepository
import skalii.web.server.postgraduatestudies.repositories.InstitutesRepository
import skalii.web.server.postgraduatestudies.repositories.FacultiesRepository
import skalii.web.server.postgraduatestudies.repositories.UsersRepository
import skalii.web.server.postgraduatestudies.views.Json


@RequestMapping(
        value = ["/api/departments"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class DepartmentsRestController(
        val contactInfoRepository: ContactInfoRepository,
        val departmentsRepository: DepartmentsRepository,
        val institutesRepository: InstitutesRepository,
        val facultiesRepository: FacultiesRepository,
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
                    departmentsRepository.get(
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
                    value = "id_department",
                    required = false) idDepartment: Int?
    ) =
            Json.get(
                    view,
                    departmentsRepository.get(
                            name,
                            idDepartment
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
                    departmentsRepository.get(
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
                    value = "institute_name",
                    required = false) instituteName: String?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "faculty_name",
                    required = false) facultyName: String?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?
    ) =
            Json.get(
                    view,
                    departmentsRepository.getAll(
                            allRecords,
                            idInstitute ?: institutesRepository.get(
                                    instituteName
                            ).idInstitute,
                            idFaculty ?: facultiesRepository.get(
                                    facultyName
                            ).idFaculty
                    )
            )


    /** ============================== POST requests ============================== */


    @PostMapping(value = ["one{-view}"])
    fun add(
            @PathVariable(value = "-view") view: String,
            @RequestBody newDepartment: Department
    ) =
            Json.get(
                    view,
                    departmentsRepository.run {
                        newDepartment.also {
                            if (it.institute.idInstitute == 0) {
                                it.institute = institutesRepository.get(it.institute.name)
                            }
                            if (it.faculty.idFaculty == 0) {
                                it.faculty = facultiesRepository.get(it.faculty.name)
                            }
                        }
                        add(
                                newDepartment.name,
                                newDepartment.institute.idInstitute,
                                newDepartment.faculty.idFaculty
                        )
                    }
            )


    /** ============================== PUT requests ============================== */


    @PutMapping(value = ["one{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody newDepartment: Department,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "id_department",
                    required = false) _idDepartment: Int?
    ) =
            Json.get(
                    view,
                    departmentsRepository.run {
                        flush()
                        try {
                            newDepartment.institute
                        } catch (e: Exception) {
                            newDepartment.institute = get(
                                    name,
                                    _idDepartment
                            ).institute
                        }
                        try {
                            newDepartment.faculty
                        } catch (e: Exception) {
                            newDepartment.faculty = get(
                                    name,
                                    _idDepartment
                            ).faculty
                        }
                        flush()
                        val foundId = set(
                                newDepartment,
                                name,
                                _idDepartment
                        ).idDepartment
                        flush()
                        get(idDepartment = foundId)
                    }
            )


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["one{-view}"])
    fun delete(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?
    ) =
            Json.get(
                    view,
                    departmentsRepository.delete(
                            name,
                            idDepartment
                    )
            )

}
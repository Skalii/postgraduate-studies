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

import skalii.web.server.postgraduatestudies.entities.User
import skalii.web.server.postgraduatestudies.repositories.BranchesRepository
import skalii.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skalii.web.server.postgraduatestudies.repositories.DegreesRepository
import skalii.web.server.postgraduatestudies.repositories.DepartmentsRepository
import skalii.web.server.postgraduatestudies.repositories.FacultiesRepository
import skalii.web.server.postgraduatestudies.repositories.InstitutesRepository
import skalii.web.server.postgraduatestudies.repositories.SpecialitiesRepository
import skalii.web.server.postgraduatestudies.repositories.UsersRepository
import skalii.web.server.postgraduatestudies.views.Json


@RequestMapping(
        value = ["/api/users"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class UsersRestController(
        val usersRepository: UsersRepository,
        val branchesRepository: BranchesRepository,
        val contactInfoRepository: ContactInfoRepository,
        val degreesRepository: DegreesRepository,
        val departmentsRepository: DepartmentsRepository,
        var facultiesRepository: FacultiesRepository,
        var institutesRepository: InstitutesRepository,
        var specialitiesRepository: SpecialitiesRepository
) {


    /** ============================== GET requests ============================== */


    @GetMapping(value = ["me{-view}"])
    fun getMe(
            @PathVariable("-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.getUser(
                    view,
                    usersRepository.get(authUser.username)
            )

    @GetMapping(value = ["my-instructor{-view}"])
    fun getMyInstructor(
            @PathVariable("-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.getUser(
                    view,
                    usersRepository.get(
                            authUser.username
                    ).studyInfo?.instructor
            )

    @GetMapping(value = ["my-students{-view}"])
    fun getMyStudents(
            @PathVariable("-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ): String {

        val myStudents: MutableList<User> = mutableListOf()

        usersRepository.get(
                authUser.username
        ).students.forEach { myStudents.add(it!!.user!!) }

        return Json.getUser(
                view,
                myStudents
        )
    }

    @GetMapping(value = ["one{-view}"])
    fun get(
            @PathVariable("-view") view: String,
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
            Json.getUser(
                    view,
                    usersRepository.get(
                            email,
                            phoneNumber,
                            idUser
                    )
            )

    @GetMapping(value = ["all{-view}"])
    fun getAll(
            @PathVariable("-view") view: String,
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?,
            @RequestParam(
                    value = "degree_name",
                    required = false) degreeName: String?,
            @RequestParam(
                    value = "degree_branch",
                    required = false) degreeBranch: String?,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "branch_number",
                    required = false) branchNumber: String?,
            @RequestParam(
                    value = "branch_name",
                    required = false) branchName: String?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "speciality_number",
                    required = false) specialityNumber: String?,
            @RequestParam(
                    value = "speciality_name",
                    required = false) specialityName: String?,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "department_name",
                    required = false) departmentName: String?,
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "faculty_name",
                    required = false) facultyName: String?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "institute_name",
                    required = false) instituteName: String?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?
    ) =
            Json.getUser(
                    view,
                    usersRepository.getAll(
                            allRecords,
                            idDegree ?: degreesRepository.get(
                                    degreeName,
                                    degreeBranch
                            ).idDegree,
                            idBranch ?: branchesRepository.get(
                                    branchNumber,
                                    branchName
                            ).idBranch,
                            idSpeciality ?: specialitiesRepository.get(
                                    specialityNumber,
                                    specialityName
                            ).idSpeciality,
                            idDepartment ?: departmentsRepository.get(
                                    departmentName
                            ).idDepartment,
                            idFaculty ?: facultiesRepository.get(
                                    facultyName
                            ).idFaculty,
                            idInstitute ?: institutesRepository.get(
                                    instituteName
                            ).idInstitute
                    )
            )


    /** ============================== POST requests ============================== */


    @PostMapping(value = ["one{-view}"])
    fun add(
            @PathVariable("-view") view: String,
            @RequestBody newUser: User,
            @RequestParam(value = "password") password: String
    ) =
            Json.getUser(
                    view,
                    usersRepository.add(
                            newUser.fixInitializedAdd(
                                    degreesRepository,
                                    specialitiesRepository,
                                    departmentsRepository
                            ).role.value,
                            password,
                            newUser.fullNameUa,
                            newUser.fullNameEn,
                            newUser.birthday.toString(),
                            newUser.familyStatus?.value,
                            newUser.children,
                            newUser.academicRank?.value,
                            newUser.degree?.idDegree,
                            newUser.speciality.idSpeciality,
                            newUser.department.idDepartment,
                            newUser.contactInfo.email,
                            newUser.contactInfo.phoneNumber,
                            newUser.contactInfo.address!!,
                            newUser.scientificLinks.orcid!!,
                            newUser.scientificLinks.researcherid!!,
                            newUser.scientificLinks.googleScholarId!!,
                            newUser.scientificLinks.scopusAuthorId!!,
                            newUser.studyInfo!!.year,
                            newUser.studyInfo!!.form.value,
                            newUser.studyInfo!!.basis.value,
                            newUser.studyInfo!!.themeTitle,
                            newUser.studyInfo!!.instructor.idUser
                    )
            )


    /** ============================== PUT requests ============================== */


    @PutMapping(value = ["me{-view}"])
    fun setMe(
            @PathVariable("-view") view: String,
            @RequestBody changedUser: User,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "password",
                    required = false) newPassword: String?
    ) =
            usersRepository.get(authUser.username).run {

                usersRepository.setMe(
                        changedUser,
                        newPassword,
                        authUser.username
                )

                return@run Json.get(
                        view,
                        User(
                                idUser,
                                role,
                                salt,
                                hash,
                                changedUser.fullNameUa,
                                changedUser.fullNameEn,
                                changedUser.birthday,
                                changedUser.familyStatus,
                                changedUser.children,
                                academicRank,
                                degree,
                                speciality,
                                department,
                                contactInfo,
                                studyInfo,
                                scientificLinks
                        )
                )
            }

    @PutMapping(value = ["one{-view}"])
    fun set(
            @PathVariable("-view") view: String,
            @RequestBody changedUser: User,
            @RequestParam(
                    value = "password",
                    required = false) newPassword: String?,
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
                    usersRepository.set(
                            changedUser.fixInitializedSet(
                                    usersRepository,
                                    degreesRepository,
                                    specialitiesRepository,
                                    departmentsRepository
                            ),
                            newPassword,
                            email,
                            phoneNumber,
                            idUser
                    )
            )


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["one{-view}"])
    fun delete(
            @PathVariable("-view") view: String,
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
            Json.getUser(
                    view,
                    usersRepository.run {

                        val oldUser = get(email, phoneNumber, idUser)

                        return@run delete(
                                idUser = oldUser.idUser
                        ).also {
                            it.contactInfo = oldUser.contactInfo
                            it.studyInfo = oldUser.studyInfo
                            it.scientificLinks = oldUser.scientificLinks
                        }

                    }
            )

}
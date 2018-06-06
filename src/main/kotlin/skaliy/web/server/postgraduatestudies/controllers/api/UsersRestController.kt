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

import skaliy.web.server.postgraduatestudies.entities.User
import skaliy.web.server.postgraduatestudies.repositories.BranchesRepository
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.DegreesRepository
import skaliy.web.server.postgraduatestudies.repositories.DepartmentsRepository
import skaliy.web.server.postgraduatestudies.repositories.FacultiesRepository
import skaliy.web.server.postgraduatestudies.repositories.InstitutesRepository
import skaliy.web.server.postgraduatestudies.repositories.ScientificLinksRepository
import skaliy.web.server.postgraduatestudies.repositories.SpecialitiesRepository
import skaliy.web.server.postgraduatestudies.repositories.StudyInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.Json


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
        var scientificLinksRepository: ScientificLinksRepository,
        var specialitiesRepository: SpecialitiesRepository,
        var studyInfoRepository: StudyInfoRepository
) {


    /**
     *
     *      GET / SELECT
     *      requests
     *
     */


    /** ============================== ME ============================== */


    @GetMapping(value = ["get/me{-view}"])
    fun getMe(
            @PathVariable("-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.getUser(
                    view,
                    contactInfoRepository.get(
                            authUser.username
                    ).user
            )


    /** ============================== MY INSTRUCTOR ============================== */


    @GetMapping(value = ["get/my-instructor{-view}"])
    fun getMyInstructor(
            @PathVariable("-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.getUser(
                    view,
                    contactInfoRepository.get(
                            authUser.username
                    ).user.studyInfo?.instructor
            )


    /** ============================== MY STUDENTS ============================== */


    @GetMapping(value = ["get/my-students{-view}"])
    fun getMyStudents(
            @PathVariable("-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ): String {

        val myStudents: MutableList<User> = mutableListOf()

        contactInfoRepository.get(
                authUser.username
        ).user.students.forEach { myStudents.add(it!!.user!!) }

        return Json.getUser(
                view,
                myStudents
        )
    }


    /** ============================== ONE ============================== */


    @GetMapping(value = ["get/one{-view}"])
    fun get(
            @PathVariable("-view") view: String,
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
            Json.getUser(
                    view,
                    usersRepository.get(
                            idUser,
                            contactInfoRepository.get(
                                    email,
                                    phoneNumber
                            )
                    )
            )


    /** ============================== ALL ============================== */


    @GetMapping(value = ["get/all{-view}"])
    fun getAll(
            @PathVariable("-view") view: String,
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "degree_name",
                    required = false) degreeName: String?,
            @RequestParam(
                    value = "degree_branch",
                    required = false) degreeBranch: String?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "branch_number",
                    required = false) branchNumber: String?,
            @RequestParam(
                    value = "branch_name",
                    required = false) branchName: String?,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "speciality_number",
                    required = false) specialityNumber: String?,
            @RequestParam(
                    value = "speciality_name",
                    required = false) specialityName: String?,
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "department_name",
                    required = false) departmentName: String?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "faculty_name",
                    required = false) facultyName: String?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "institute_name",
                    required = false) instituteName: String?
    ) =
            Json.getUser(
                    view,
                    usersRepository.getAll(
                            allRecords,
                            degreesRepository.get(
                                    idDegree,
                                    degreeName,
                                    degreeBranch
                            ),
                            branchesRepository.get(
                                    idBranch,
                                    branchNumber,
                                    branchName
                            ),
                            specialitiesRepository.get(
                                    idSpeciality,
                                    specialityNumber,
                                    specialityName
                            ),
                            departmentsRepository.get(
                                    idDepartment,
                                    departmentName
                            ),
                            facultiesRepository.get(
                                    idFaculty,
                                    facultyName
                            ),
                            institutesRepository.get(
                                    idInstitute,
                                    instituteName
                            )
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
            @PathVariable("-view") view: String,
            @RequestBody user: User,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "degree_name",
                    required = false) degreeName: String?,
            @RequestParam(
                    value = "degree_branch",
                    required = false) degreeBranch: String?,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "speciality_number",
                    required = false) specialityNumber: String?,
            @RequestParam(
                    value = "speciality_name",
                    required = false) specialityName: String?,
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "department_name",
                    required = false) departmentName: String?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?,
            @RequestParam(
                    value = "orcid",
                    required = false) orcid: String?,
            @RequestParam(
                    value = "researcherid",
                    required = false) researcherid: String?,
            @RequestParam(
                    value = "google_scholar_id",
                    required = false) googleScholarId: String?,
            @RequestParam(
                    value = "scopus_author_id",
                    required = false) scopusAuthorId: String?
    ) =
            Json.getUser(
                    view,
                    usersRepository.add(
                            user.role.value,
                            user.hash,
                            user.fullNameUa,
                            user.fullNameEn,
                            user.birthday.toString(),
                            user.familyStatus?.value,
                            user.children,
                            user.academicRank?.value,
                            degreesRepository.get(
                                    idDegree,
                                    degreeName,
                                    degreeBranch
                            ).idDegree,
                            specialitiesRepository.get(
                                    idSpeciality,
                                    specialityNumber,
                                    specialityName
                            ).idSpeciality,
                            departmentsRepository.get(
                                    idDepartment,
                                    departmentName
                            ).idDepartment,
                            contactInfoRepository.get(
                                    email,
                                    phoneNumber,
                                    idContactInfo = idContactInfo
                            ).idContactInfo,
                            studyInfoRepository.get(
                                    idStudyInfo
                            ).idStudyInfo,
                            scientificLinksRepository.get(
                                    idScientificLinks,
                                    orcid,
                                    researcherid,
                                    googleScholarId,
                                    scopusAuthorId
                            ).idScientificLinks
                    )
            )


    /**
     *
     *      SET / UPDATE
     *      requests
     *
     */


    /** ============================== ME ============================== */

    // todo check for work

    @PutMapping(value = ["put/set-me{-view}"])
    fun setMe(
            @PathVariable("-view") view: String,
            @RequestBody newUser: User,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            contactInfoRepository.get(
                    authUser.username
            ).user.run {

                usersRepository.setMe(
                        newUser,
                        idUser
                )

                return@run Json.get(
                        view,
                        User(
                                idUser,
                                role,
                                newUser.salt,
                                newUser.hash,
                                newUser.fullNameUa,
                                newUser.fullNameEn,
                                newUser.birthday,
                                newUser.familyStatus,
                                newUser.children,
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

    /*: String {

        val user =
                contactInfoRepository.get(
                        authUser.username
                ).user

        return Json.getUser(
                view,
                User(
                        usersRepository.setMe(
                                newUser,
                                user.idUser
                        ).idUser,
                        user.role,
                        newUser.salt,
                        newUser.hash,
                        newUser.fullNameUa,
                        newUser.fullNameEn,
                        newUser.birthday,
                        newUser.familyStatus,
                        newUser.children,
                        user.academicRank,
                        user.degree,
                        user.speciality,
                        user.department,
                        user.contactInfo,
                        user.studyInfo,
                        user.scientificLinks
                )
        )
    }*/

    /** ============================== ONE ============================== */

    // todo check for work

    @PutMapping(value = ["put/set{-view}"])
    fun set(
            @PathVariable("-view") view: String,
            @RequestBody newUser: User,
            @RequestParam(
                    value = "id_user",
                    required = false) _idUser: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            usersRepository.get(
                    _idUser,
                    contactInfoRepository.get(
                            email,
                            phoneNumber
                    )
            ).run {

                usersRepository.set(
                        newUser,
                        idUser
                )

                return@run Json.get(
                        view,
                        User(
                                idUser,
                                role,
                                newUser.salt,
                                newUser.hash,
                                newUser.fullNameUa,
                                newUser.fullNameEn,
                                newUser.birthday,
                                newUser.familyStatus,
                                newUser.children,
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

    /*: String {

        val user =
                usersRepository.get(
                        idUser,
                        contactInfoRepository.get(
                                phoneNumber = phoneNumber,
                                email = email
                        )
                )!!

        return Json.getUser(
                view,
                User(
                        usersRepository.set(
                                newUser,
                                user.idUser
                        )!!.idUser,
                        newUser.role,
                        newUser.salt,
                        newUser.hash,
                        newUser.fullNameUa,
                        newUser.fullNameEn,
                        newUser.birthday,
                        newUser.familyStatus,
                        newUser.children,
                        newUser.academicRank,
                        newUser.degree,
                        newUser.speciality,
                        newUser.department,
                        user.contactInfo,
                        user.studyInfo,
                        user.scientificLinks
                )
        )

    }*/


    /**
     *
     *      DELETE
     *      requests
     *
     */


    /** ============================== ONE ============================== */


    @DeleteMapping(value = ["delete/one{-view}"])
    fun delete(
            @PathVariable("-view") view: String,
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
            Json.getUser(
                    view,
                    usersRepository.delete(
                            idUser,
                            contactInfoRepository.get(
                                    email,
                                    phoneNumber
                            )
                    )
            )

}
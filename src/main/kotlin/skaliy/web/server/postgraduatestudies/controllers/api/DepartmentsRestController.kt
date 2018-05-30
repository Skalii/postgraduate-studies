package skaliy.web.server.postgraduatestudies.controllers.api


import com.fasterxml.jackson.annotation.JsonView

import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import skaliy.web.server.postgraduatestudies.entities.Department
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.DepartmentsRepository
import skaliy.web.server.postgraduatestudies.repositories.InstitutesRepository
import skaliy.web.server.postgraduatestudies.repositories.FacultiesRepository
import skaliy.web.server.postgraduatestudies.repositories.ScientificLinksRepository
import skaliy.web.server.postgraduatestudies.repositories.StudyInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/departments"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class DepartmentsRestController(
        val contactInfoRepository: ContactInfoRepository,
        val departmentsRepository: DepartmentsRepository,
        val institutesRepository: InstitutesRepository,
        val facultiesRepository: FacultiesRepository,
        val scientificLinksRepository: ScientificLinksRepository,
        val studyInfoRepository: StudyInfoRepository,
        val usersRepository: UsersRepository
) {


    /**
     * Queries to
     * GET
     * records
     */


    /** ============================== MY ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/my-ui"])
    fun getMyUI(@AuthenticationPrincipal authUser: UserDetails) =
            departmentsRepository.get(
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    )
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-rest"])
    fun getMyRest(@AuthenticationPrincipal authUser: UserDetails) =
            departmentsRepository.get(
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    )
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/my-tree"])
    fun getMyTree(@AuthenticationPrincipal authUser: UserDetails) =
            departmentsRepository.get(
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    )
            )


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-ui"])
    fun getOneUI(
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            departmentsRepository.get(
                    idDepartment,
                    name
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getOneRest(
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            departmentsRepository.get(
                    idDepartment,
                    name
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getOneTree(
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            departmentsRepository.get(
                    idDepartment,
                    name
            )


    /** ============================== ONE
     *                                 BY
     *                                 USER ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-by-user-ui"])
    fun getOneByUserUI(
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
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
            departmentsRepository.get(
                    usersRepository.get(
                            idUser,
                            contactInfoRepository.get(
                                    idContactInfo,
                                    phoneNumber,
                                    email
                            ),
                            studyInfoRepository.get(idStudyInfo),
                            scientificLinksRepository.get(
                                    idScientificLinks,
                                    orcid,
                                    researcherid,
                                    googleScholarId,
                                    scopusAuthorId
                            )
                    )
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-by-user-rest"])
    fun getOneByUserRest(
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
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
            departmentsRepository.get(
                    usersRepository.get(
                            idUser,
                            contactInfoRepository.get(
                                    idContactInfo,
                                    phoneNumber,
                                    email
                            ),
                            studyInfoRepository.get(idStudyInfo),
                            scientificLinksRepository.get(
                                    idScientificLinks,
                                    orcid,
                                    researcherid,
                                    googleScholarId,
                                    scopusAuthorId
                            )
                    )
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-by-user-tree"])
    fun getOneByUserTree(
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
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
            departmentsRepository.get(
                    usersRepository.get(
                            idUser,
                            contactInfoRepository.get(
                                    idContactInfo,
                                    phoneNumber,
                                    email
                            ),
                            studyInfoRepository.get(idStudyInfo),
                            scientificLinksRepository.get(
                                    idScientificLinks,
                                    orcid,
                                    researcherid,
                                    googleScholarId,
                                    scopusAuthorId
                            )
                    )
            )


    /** ============================== ALL ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-ui"])
    fun getAllUI(@RequestParam(
            value = "all_records",
            required = false) allRecords: Boolean?,
                 @RequestParam(
                         value = "id_institute",
                         required = false) idInstitute: Int?,
                 @RequestParam(
                         value = "institute_name",
                         required = false) instituteName: String?,
                 @RequestParam(
                         value = "id_faculty",
                         required = false) idFaculty: Int?,
                 @RequestParam(
                         value = "faculty_name",
                         required = false) facultyName: String?
    ) =
            departmentsRepository.getAll(
                    allRecords,
                    institutesRepository.get(
                            idInstitute,
                            instituteName
                    ),
                    facultiesRepository.get(
                            idFaculty,
                            facultyName
                    )
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest(@RequestParam(
            value = "all_records",
            required = false) allRecords: Boolean?,
                   @RequestParam(
                           value = "id_institute",
                           required = false) idInstitute: Int?,
                   @RequestParam(
                           value = "institute_name",
                           required = false) instituteName: String?,
                   @RequestParam(
                           value = "id_faculty",
                           required = false) idFaculty: Int?,
                   @RequestParam(
                           value = "faculty_name",
                           required = false) facultyName: String?
    ) =
            departmentsRepository.getAll(
                    allRecords,
                    institutesRepository.get(
                            idInstitute,
                            instituteName
                    ),
                    facultiesRepository.get(
                            idFaculty,
                            facultyName
                    )
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree(
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "institute_name",
                    required = false) instituteName: String?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "faculty_name",
                    required = false) facultyName: String?
    ) =
            departmentsRepository.getAll(
                    allRecords,
                    institutesRepository.get(
                            idInstitute,
                            instituteName
                    ),
                    facultiesRepository.get(
                            idFaculty,
                            facultyName
                    )
            )


    /**
     * Queries to
     * ADD / INSERT INTO
     * records
     */


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @PostMapping(value = ["post/add-ui"])
    fun addUI(@RequestBody department: Department?) =
            departmentsRepository.add(department)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/add-rest"])
    fun addRest(@RequestBody department: Department?) =
            departmentsRepository.add(department)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/add-tree"])
    fun addTree(@RequestBody department: Department?) =
            departmentsRepository.add(department)


    /**
     * Queries to
     * SET / UPDATE
     * records
     */


    /** ============================== MY ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/set-my-ui"])
    fun setMyUI(
            @RequestBody newDepartment: Department?,
            @AuthenticationPrincipal authUser: UserDetails
    ): Department? {
        val department =
                departmentsRepository.set(
                        newDepartment,
                        usersRepository.get(
                                contactInfo = contactInfoRepository.get(
                                        email = authUser.username
                                )
                        )?.department?.idDepartment
                )
        return departmentsRepository.get(department?.idDepartment)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-my-rest"])
    fun setMyRest(
            @RequestBody newDepartment: Department?,
            @AuthenticationPrincipal authUser: UserDetails
    ): Department? {
        val department =
                departmentsRepository.set(
                        newDepartment,
                        usersRepository.get(
                                contactInfo = contactInfoRepository.get(
                                        email = authUser.username
                                )
                        )?.department?.idDepartment
                )
        return departmentsRepository.get(department?.idDepartment)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-my-tree"])
    fun setMyTree(
            @RequestBody newDepartment: Department?,
            @AuthenticationPrincipal authUser: UserDetails
    ): Department? {
        val department =
                departmentsRepository.set(
                        newDepartment,
                        usersRepository.get(
                                contactInfo = contactInfoRepository.get(
                                        email = authUser.username
                                )
                        )?.department?.idDepartment
                )
        return departmentsRepository.get(department?.idDepartment)
    }


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/set-ui"])
    fun setUI(
            @RequestBody newDepartment: Department?,
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ): Department? {
        val department =
                departmentsRepository.set(
                        newDepartment,
                        idDepartment,
                        name
                )
        return departmentsRepository.get(department?.idDepartment)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-rest"])
    fun setRest(
            @RequestBody newDepartment: Department?,
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ): Department? {
        val department =
                departmentsRepository.set(
                        newDepartment,
                        idDepartment,
                        name
                )
        return departmentsRepository.get(department?.idDepartment)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-tree"])
    fun setTree(
            @RequestBody newDepartment: Department?,
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ): Department? {
        val department =
                departmentsRepository.set(
                        newDepartment,
                        idDepartment,
                        name
                )
        return departmentsRepository.get(department?.idDepartment)
    }


    /**
     * Queries to
     * DELETE
     * records
     */


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/one-ui"])
    fun deleteUI(
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            departmentsRepository.delete(
                    idDepartment,
                    name
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/one-rest"])
    fun deleteRest(
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            departmentsRepository.delete(
                    idDepartment,
                    name
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/one-tree"])
    fun deleteTree(
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            departmentsRepository.delete(
                    idDepartment,
                    name
            )

}
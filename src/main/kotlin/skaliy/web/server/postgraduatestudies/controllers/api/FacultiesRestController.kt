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

import skaliy.web.server.postgraduatestudies.entities.Faculty
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.DepartmentsRepository
import skaliy.web.server.postgraduatestudies.repositories.InstitutesRepository
import skaliy.web.server.postgraduatestudies.repositories.FacultiesRepository
import skaliy.web.server.postgraduatestudies.repositories.ScientificLinksRepository
import skaliy.web.server.postgraduatestudies.repositories.StudyInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/faculties"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class FacultiesRestController(
        val contactInfoRepository: ContactInfoRepository,
        val departmentsRepository: DepartmentsRepository,
        val facultiesRepository: FacultiesRepository,
        val institutesRepository: InstitutesRepository,
        val scientificLinksRepository: ScientificLinksRepository,
        val studyInfoRepository: StudyInfoRepository,
        val usersRepository: UsersRepository
) {


    /**
     * Queries to
     * GET / SELECT
     * records
     */


    /** ============================== MY ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/my-ui"])
    fun getOneUI(@AuthenticationPrincipal authUser: UserDetails) =
            facultiesRepository.get(
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    )
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-rest"])
    fun getOneRest(@AuthenticationPrincipal authUser: UserDetails) =
            facultiesRepository.get(
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    )
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/my-tree"])
    fun getOneTree(@AuthenticationPrincipal authUser: UserDetails) =
            facultiesRepository.get(
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
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            facultiesRepository.get(
                    idFaculty,
                    name
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getOneRest(
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            facultiesRepository.get(
                    idFaculty,
                    name
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getOneTree(
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            facultiesRepository.get(
                    idFaculty,
                    name
            )


    /** ============================== ONE
     *                                 BY
     *                                 DEPARTMENT ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-by-department-ui"])
    fun getOneByUserUI(
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?
    ) =
            facultiesRepository.get(
                    departmentsRepository.get(
                            idDepartment,
                            name
                    ))

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-by-department-rest"])
    fun getOneByUserRest(
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?
    ) =
            facultiesRepository.get(
                    departmentsRepository.get(
                            idDepartment,
                            name
                    ))

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-by-department-tree"])
    fun getOneByUserTree(
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?
    ) =
            facultiesRepository.get(
                    departmentsRepository.get(
                            idDepartment,
                            name
                    ))

/*

    */
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
            facultiesRepository.get(
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
            facultiesRepository.get(
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
            facultiesRepository.get(
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
    fun getAllUI(
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            facultiesRepository.getAll(
                    allRecords,
                    institutesRepository.get(
                            idInstitute,
                            name
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
                           value = "name",
                           required = false) name: String?
    ) =
            facultiesRepository.getAll(
                    allRecords,
                    institutesRepository.get(
                            idInstitute,
                            name
                    )
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree(@RequestParam(
            value = "all_records",
            required = false) allRecords: Boolean?,
                   @RequestParam(
                           value = "id_institute",
                           required = false) idInstitute: Int?,
                   @RequestParam(
                           value = "name",
                           required = false) name: String?
    ) =
            facultiesRepository.getAll(
                    allRecords,
                    institutesRepository.get(
                            idInstitute,
                            name
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
    fun addUI(@RequestBody faculty: Faculty?) =
            facultiesRepository.add(faculty)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/add-rest"])
    fun addRest(@RequestBody faculty: Faculty?) =
            facultiesRepository.add(faculty)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/add-tree"])
    fun addTree(@RequestBody faculty: Faculty?) =
            facultiesRepository.add(faculty)


    /**
     * Queries to
     * SET / UPDATE
     * records
     */


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/set-ui"])
    fun setUI(
            @RequestBody newFaculty: Faculty?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ): Faculty? {
        val faculty =
                facultiesRepository.set(
                        newFaculty,
                        idFaculty,
                        name
                )
        return facultiesRepository.get(faculty?.idFaculty)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-rest"])
    fun setRest(
            @RequestBody newFaculty: Faculty?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ): Faculty? {
        val faculty =
                facultiesRepository.set(
                        newFaculty,
                        idFaculty,
                        name
                )
        return facultiesRepository.get(faculty?.idFaculty)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-tree"])
    fun setTree(
            @RequestBody newFaculty: Faculty?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ): Faculty? {
        val faculty =
                facultiesRepository.set(
                        newFaculty,
                        idFaculty,
                        name
                )
        return facultiesRepository.get(faculty?.idFaculty)
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
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            facultiesRepository.delete(
                    idFaculty,
                    name
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/one-rest"])
    fun deleteRest(
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            facultiesRepository.delete(
                    idFaculty,
                    name
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/one-tree"])
    fun deleteTree(
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            facultiesRepository.delete(
                    idFaculty,
                    name
            )

}
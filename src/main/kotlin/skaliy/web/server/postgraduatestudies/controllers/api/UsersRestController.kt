package skaliy.web.server.postgraduatestudies.controllers.api


import com.fasterxml.jackson.annotation.JsonView

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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

import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/users"],
        produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
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
        val studyInfoRepository: StudyInfoRepository
) {


    /**
     * Queries to
     * GET
     * records
     */


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-ui"])
    fun getUI(
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

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getRest(
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

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getTree(
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


    /** ============================== ALL ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-ui"])
    fun getAllUI(
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
                    required = false) instituteName: String?,
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?
    ) =
            usersRepository.getAll(
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
                    ),
                    allRecords
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest(
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
                    required = false) instituteName: String?,
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?
    ) =
            usersRepository.getAll(
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
                    ),
                    allRecords
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree(
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
                    required = false) instituteName: String?,
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?
    ) =
            usersRepository.getAll(
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
                    ),
                    allRecords
            )

}
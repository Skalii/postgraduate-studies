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

import skaliy.web.server.postgraduatestudies.entities.Speciality
import skaliy.web.server.postgraduatestudies.repositories.BranchesRepository
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.ScientificLinksRepository
import skaliy.web.server.postgraduatestudies.repositories.SpecialitiesRepository
import skaliy.web.server.postgraduatestudies.repositories.StudyInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/specialities"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class SpecialitiesRestController(
        val contactInfoRepository: ContactInfoRepository,
        val scientificLinksRepository: ScientificLinksRepository,
        val specialitiesRepository: SpecialitiesRepository,
        val studyInfoRepository: StudyInfoRepository,
        val branchesRepository: BranchesRepository,
        val usersRepository: UsersRepository
) {


    /**
     * Queries to
     * GET / SELECt
     * records
     */


    /** ============================== MY ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/my-ui"])
    fun getMyUI(@AuthenticationPrincipal authUser: UserDetails) =
            specialitiesRepository.get(
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    )?.speciality?.idSpeciality
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-rest"])
    fun getMyRest(@AuthenticationPrincipal authUser: UserDetails) =
            specialitiesRepository.get(
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    )?.speciality?.idSpeciality
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/my-tree"])
    fun getMyTree(@AuthenticationPrincipal authUser: UserDetails) =
            specialitiesRepository.get(
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    )?.speciality?.idSpeciality
            )


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-ui"])
    fun getOneUI(
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            specialitiesRepository.get(
                    idSpeciality,
                    number,
                    name
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getOneRest(
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            specialitiesRepository.get(
                    idSpeciality,
                    number,
                    name
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getOneTree(
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            specialitiesRepository.get(
                    idSpeciality,
                    number,
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
                    value = "all_records",
                    required = false) allRecords: Boolean?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            specialitiesRepository.getAll(
                    allRecords,
                    branchesRepository.get(
                            idBranch,
                            number,
                            name
                    )
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest(@RequestParam(
            value = "all_records",
            required = false) allRecords: Boolean?,
                   @RequestParam(
                           value = "id_branch",
                           required = false) idBranch: Int?,
                   @RequestParam(
                           value = "number",
                           required = false) number: String?,
                   @RequestParam(
                           value = "name",
                           required = false) name: String?
    ) =
            specialitiesRepository.getAll(
                    allRecords,
                    branchesRepository.get(
                            idBranch,
                            number,
                            name
                    )
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree(@RequestParam(
            value = "all_records",
            required = false) allRecords: Boolean?,
                   @RequestParam(
                           value = "id_branch",
                           required = false) idBranch: Int?,
                   @RequestParam(
                           value = "number",
                           required = false) number: String?,
                   @RequestParam(
                           value = "name",
                           required = false) name: String?
    ) =
            specialitiesRepository.getAll(
                    allRecords,
                    branchesRepository.get(
                            idBranch,
                            number,
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
    fun addUI(@RequestBody speciality: Speciality?) =
            specialitiesRepository.add(speciality)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/add-rest"])
    fun addRest(@RequestBody speciality: Speciality?) =
            specialitiesRepository.add(speciality)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/add-tree"])
    fun addTree(@RequestBody speciality: Speciality?) =
            specialitiesRepository.add(speciality)


    /**
     * Queries to
     * SET / UPDATE
     * records
     */


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/set-ui"])
    fun setUI(
            @RequestBody newSpeciality: Speciality?,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ): Speciality? {
        val speciality =
                specialitiesRepository.set(
                        newSpeciality,
                        idSpeciality,
                        number,
                        name
                )
        return specialitiesRepository.get(speciality?.idSpeciality)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-rest"])
    fun setRest(
            @RequestBody newSpeciality: Speciality?,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ): Speciality? {
        val speciality =
                specialitiesRepository.set(
                        newSpeciality,
                        idSpeciality,
                        number,
                        name
                )
        return specialitiesRepository.get(speciality?.idSpeciality)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-tree"])
    fun setTree(
            @RequestBody newSpeciality: Speciality?,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ): Speciality? {
        val speciality =
                specialitiesRepository.set(
                        newSpeciality,
                        idSpeciality,
                        number,
                        name
                )
        return specialitiesRepository.get(speciality?.idSpeciality)
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
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            specialitiesRepository.delete(
                    idSpeciality,
                    number,
                    name
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/one-rest"])
    fun deleteRest(
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            specialitiesRepository.delete(
                    idSpeciality,
                    number,
                    name
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/one-tree"])
    fun deleteTree(
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            specialitiesRepository.delete(
                    idSpeciality,
                    number,
                    name
            )


    /** ============================== ALL
     *                                 BY
     *                                 BRANCH ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/all-by-branch-ui"])
    fun deleteAllByBranchUI(
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            specialitiesRepository.deleteAll(
                    branchesRepository.get(
                            idBranch,
                            number,
                            name
                    )
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/all-by-branch-rest"])
    fun deleteAllByBranchRest(
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            specialitiesRepository.deleteAll(
                    branchesRepository.get(
                            idBranch,
                            number,
                            name
                    )
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/all-by-branch-tree"])
    fun deleteAllByBranchTree(
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            specialitiesRepository.deleteAll(
                    branchesRepository.get(
                            idBranch,
                            number,
                            name
                    )
            )

}
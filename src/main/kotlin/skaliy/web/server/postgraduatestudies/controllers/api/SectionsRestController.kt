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

import skaliy.web.server.postgraduatestudies.entities.Section
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.ScientificLinksRepository
import skaliy.web.server.postgraduatestudies.repositories.SectionsRepository
import skaliy.web.server.postgraduatestudies.repositories.StudyInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.TasksRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/sections"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class SectionsRestController(
        val contactInfoRepository: ContactInfoRepository,
        val scientificLinksRepository: ScientificLinksRepository,
        val sectionsRepository: SectionsRepository,
        val studyInfoRepository: StudyInfoRepository,
        val tasksRepository: TasksRepository,
        val usersRepository: UsersRepository
) {


    /**
     * Queries to
     * GET / SELECT
     * records
     */


    /** ============================== MY
     *                                 ONE ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/my-one-ui"])
    fun getMyOneUI(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.get(
                    idSection,
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    ),
                    number,
                    title
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-one-rest"])
    fun getMyOneRest(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.get(
                    idSection,
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    ),
                    number,
                    title
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/my-one-tree"])
    fun getMyOneTree(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.get(
                    idSection,
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    ),
                    number,
                    title
            )


    /** ============================== MY
     *                                 ALL ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/my-all-ui"])
    fun getMyAllUI(@AuthenticationPrincipal authUser: UserDetails) =
            sectionsRepository.getAll(
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    )
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-all-rest"])
    fun getMyAllRest(@AuthenticationPrincipal authUser: UserDetails) =
            sectionsRepository.getAll(
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    )
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/my-all-tree"])
    fun getMyAllTree(@AuthenticationPrincipal authUser: UserDetails) =
            sectionsRepository.getAll(
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
                    value = "id_section",
                    required = false) idSection: Int?,
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
                    required = false) scopusAuthorId: String?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.get(
                    idSection,
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
                    ),
                    number,
                    title
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getOneRest(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
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
                    required = false) scopusAuthorId: String?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.get(
                    idSection,
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
                    ),
                    number,
                    title
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getOneTree(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
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
                    required = false) scopusAuthorId: String?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.get(
                    idSection,
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
                    ),
                    number,
                    title
            )


    /** ============================== ONE
     *                                 BY
     *                                 TASK ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-by-task-ui"])
    fun getOneByTaskUI(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.get(tasksRepository.get(idTask))

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-by-task-rest"])
    fun getOneByTaskRest(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.get(tasksRepository.get(idTask))

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-by-task-tree"])
    fun getOneByTaskTree(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.get(tasksRepository.get(idTask))


    /** ============================== ALL ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-ui"])
    fun getAllUI(
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
            sectionsRepository.getAll(
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
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest(@RequestParam(
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
            sectionsRepository.getAll(
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
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree(@RequestParam(
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
            sectionsRepository.getAll(
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


    /**
     * Queries to
     * ADD / INSERT INTO
     * records
     */


    /** ============================== MY ============================== */


    @JsonView(View.UI::class)
    @PostMapping(value = ["post/add-my-ui"])
    fun addMyUI(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestBody section: Section
    ) =
            sectionsRepository.add(
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    ),
                    section
            )

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/add-my-rest"])
    fun addMyRest(@AuthenticationPrincipal authUser: UserDetails,
                  @RequestBody section: Section
    ) =
            sectionsRepository.add(
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    ),
                    section
            )

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/add-my-tree"])
    fun addMyTree(@AuthenticationPrincipal authUser: UserDetails,
                  @RequestBody section: Section
    ) =
            sectionsRepository.add(
                    usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    ),
                    section
            )


    /**
     * Queries to
     * SET / UPDATE
     * records
     */


    /** ============================== MY ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/set-my-ui"])
    fun setMyUI(
            @RequestBody newSection: Section?,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ): Section? {
        val section =
                sectionsRepository.set(
                        newSection,
                        user = usersRepository.get(
                                contactInfo = contactInfoRepository.get(
                                        email = authUser.username
                                )
                        ),
                        number = number,
                        title = title
                )
        return sectionsRepository.get(section?.idSection)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-my-rest"])
    fun setMyRest(
            @RequestBody newSection: Section?,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ): Section? {
        val section =
                sectionsRepository.set(
                        newSection,
                        user = usersRepository.get(
                                contactInfo = contactInfoRepository.get(
                                        email = authUser.username
                                )
                        ),
                        number = number,
                        title = title
                )
        return sectionsRepository.get(section?.idSection)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-my-tree"])
    fun setMyTree(
            @RequestBody newSection: Section?,
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ): Section? {
        val section =
                sectionsRepository.set(
                        newSection,
                        user = usersRepository.get(
                                contactInfo = contactInfoRepository.get(
                                        email = authUser.username
                                )
                        ),
                        number = number,
                        title = title
                )
        return sectionsRepository.get(section?.idSection)
    }


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/set-ui"])
    fun setUI(
            @RequestBody newSection: Section?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
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
                    required = false) scopusAuthorId: String?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ): Section? {
        val section =
                sectionsRepository.set(
                        newSection,
                        idSection,
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
                        ),
                        number,
                        title
                )
        return sectionsRepository.get(section?.idSection)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-rest"])
    fun setRest(
            @RequestBody newSection: Section?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
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
                    required = false) scopusAuthorId: String?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ): Section? {
        val section =
                sectionsRepository.set(
                        newSection,
                        idSection,
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
                        ),
                        number,
                        title
                )
        return sectionsRepository.get(section?.idSection)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-tree"])
    fun setTree(
            @RequestBody newSection: Section?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
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
                    required = false) scopusAuthorId: String?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ): Section? {
        val section =
                sectionsRepository.set(
                        newSection,
                        idSection,
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
                        ),
                        number,
                        title
                )
        return sectionsRepository.get(section?.idSection)
    }


    /** ============================== ONE
     *                                 BY
     *                                 TASK ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["put/set-by-task-ui"])
    fun setByTaskUI(
            @RequestBody newSection: Section?,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ): Section? {
        val section =
                sectionsRepository.set(
                        newSection,
                        tasksRepository.get(idTask)
                )
        return sectionsRepository.get(section?.idSection)
    }

    @JsonView(View.REST::class)
    @GetMapping(value = ["put/set-by-task-rest"])
    fun setByTaskRest(
            @RequestBody newSection: Section?,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ): Section? {
        val section =
                sectionsRepository.set(
                        newSection,
                        tasksRepository.get(idTask)
                )
        return sectionsRepository.get(section?.idSection)
    }

    @JsonView(View.TREE::class)
    @GetMapping(value = ["put/set-by-task-tree"])
    fun setByTaskTree(
            @RequestBody newSection: Section?,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ): Section? {
        val section =
                sectionsRepository.set(
                        newSection,
                        tasksRepository.get(idTask)
                )
        return sectionsRepository.get(section?.idSection)
    }


    /**
     * Queries to
     * DELETE
     * records
     */


    /** ============================== MY ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/my-ui"])
    fun deleteMyUI(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.delete(
                    user = usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    ),
                    number = number,
                    title = title
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/my-rest"])
    fun deleteMyRest(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.delete(
                    user = usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    ),
                    number = number,
                    title = title
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/my-tree"])
    fun deleteMyTree(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.delete(
                    user = usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    ),
                    number = number,
                    title = title
            )


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/one-ui"])
    fun deleteUI(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
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
                    required = false) scopusAuthorId: String?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.delete(
                    idSection,
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
                    ),
                    number,
                    title
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/one-rest"])
    fun deleteRest(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
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
                    required = false) scopusAuthorId: String?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.delete(
                    idSection,
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
                    ),
                    number,
                    title
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/one-tree"])
    fun deleteTree(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
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
                    required = false) scopusAuthorId: String?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.delete(
                    idSection,
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
                    ),
                    number,
                    title
            )


    /** ============================== ONE
     *                                 BY
     *                                 TASK ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/one-by-task-ui"])
    fun deleteByTaskUI(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            sectionsRepository.delete(tasksRepository.get(idTask))

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/one-by-task-rest"])
    fun deleteByTaskRest(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            sectionsRepository.delete(tasksRepository.get(idTask))

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/one-by-task-tree"])
    fun deleteByTaskTree(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            sectionsRepository.delete(tasksRepository.get(idTask))


    /** ============================== ALL
     *                                 BY
     *                                 USER ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/all-by-user-ui"])
    fun deleteAllByUserUI(
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
            sectionsRepository.deleteAll(
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
    @DeleteMapping(value = ["delete/all-by-user-rest"])
    fun deleteAllByUserRest(
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
            sectionsRepository.deleteAll(
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
    @DeleteMapping(value = ["delete/all-by-user-tree"])
    fun deleteAllByUserTree(
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
            sectionsRepository.deleteAll(
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

}
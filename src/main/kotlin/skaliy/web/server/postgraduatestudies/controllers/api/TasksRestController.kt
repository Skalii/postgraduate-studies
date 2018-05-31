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

import skaliy.web.server.postgraduatestudies.entities.Task
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.ScientificLinksRepository
import skaliy.web.server.postgraduatestudies.repositories.SectionsRepository
import skaliy.web.server.postgraduatestudies.repositories.StudyInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.TasksRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/tasks"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class TasksRestController(
        val contactInfoRepository: ContactInfoRepository,
        val scientificLinksRepository: ScientificLinksRepository,
        val sectionsRepository: SectionsRepository,
        val studyInfoRepository: StudyInfoRepository,
        val tasksRepository: TasksRepository,
        val usersRepository: UsersRepository
) {


    /**
     * Queries to
     * GET
     * records
     */


    /** ============================== MY
     *                                 ONE ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/my-one-ui"])
    fun getMyOneUI(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?
    ) =
            tasksRepository.get(
                    section = sectionsRepository.get(
                            user = usersRepository.get(
                                    contactInfo = contactInfoRepository.get(
                                            email = authUser.username
                                    )
                            ),
                            number = sectionNumber,
                            title = sectionTitle
                    ),
                    user = usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    ),
                    number = taskNumber,
                    title = taskTitle
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-one-rest"])
    fun getMyOneRest(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?
    ) =
            tasksRepository.get(
                    section = sectionsRepository.get(
                            user = usersRepository.get(
                                    contactInfo = contactInfoRepository.get(
                                            email = authUser.username
                                    )
                            ),
                            number = sectionNumber,
                            title = sectionTitle
                    ),
                    user = usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    ),
                    number = taskNumber,
                    title = taskTitle
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/my-one-tree"])
    fun getMyOneTree(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?
    ) =
            tasksRepository.get(
                    section = sectionsRepository.get(
                            user = usersRepository.get(
                                    contactInfo = contactInfoRepository.get(
                                            email = authUser.username
                                    )
                            ),
                            number = sectionNumber,
                            title = sectionTitle
                    ),
                    user = usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    ),
                    number = taskNumber,
                    title = taskTitle
            )


    /** ============================== MY
     *                                 ALL ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/my-all-ui"])
    fun getMyAllUI(@AuthenticationPrincipal authUser: UserDetails) =
            tasksRepository.getAll(
                    user = usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    )
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-all-rest"])
    fun getMyAllRest(@AuthenticationPrincipal authUser: UserDetails) =
            tasksRepository.getAll(
                    user = usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    )
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/my-all-tree"])
    fun getMyAllTree(@AuthenticationPrincipal authUser: UserDetails) =
            tasksRepository.getAll(
                    user = usersRepository.get(
                            contactInfo = contactInfoRepository.get(
                                    email = authUser.username
                            )
                    )
            )


    /** ============================== MY
     *                                 ALL
     *                                 BY
     *                                 SECTION ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/my-all-by-section-ui"])
    fun getMyAllBySectionUI(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?
    ) =
            tasksRepository.getAll(
                    sectionsRepository.get(
                            user = usersRepository.get(
                                    contactInfo = contactInfoRepository.get(
                                            email = authUser.username
                                    )
                            ),
                            number = sectionNumber,
                            title = sectionTitle
                    )
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-all-by-section-rest"])
    fun getMyAllBySectionRest(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?
    ) =
            tasksRepository.getAll(
                    sectionsRepository.get(
                            user = usersRepository.get(
                                    contactInfo = contactInfoRepository.get(
                                            email = authUser.username
                                    )
                            ),
                            number = sectionNumber,
                            title = sectionTitle
                    )
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/my-all-by-section-tree"])
    fun getMyAllBySectionTree(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?
    ) =
            tasksRepository.getAll(
                    sectionsRepository.get(
                            user = usersRepository.get(
                                    contactInfo = contactInfoRepository.get(
                                            email = authUser.username
                                    )
                            ),
                            number = sectionNumber,
                            title = sectionTitle
                    )
            )


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-ui"])
    fun getOneUI(
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
                    value = "task_number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) title: String?
    ) =
            tasksRepository.get(
                    idTask,
                    sectionsRepository.get(idSection),
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
                    value = "id_task",
                    required = false) idTask: Int?,
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
                    value = "task_number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) title: String?
    ) =
            tasksRepository.get(
                    idTask,
                    sectionsRepository.get(idSection),
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
                    value = "id_task",
                    required = false) idTask: Int?,
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
                    value = "task_number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) title: String?
    ) =
            tasksRepository.get(
                    idTask,
                    sectionsRepository.get(idSection),
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


    /** ============================== ALL ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-ui"])
    fun getAllUI(
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
                    required = false) scopusAuthorId: String?
    ) =
            tasksRepository.getAll(
                    sectionsRepository.get(idSection),
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
    fun getAllRest(
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
                    required = false) scopusAuthorId: String?
    ) =
            tasksRepository.getAll(
                    sectionsRepository.get(idSection),
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
    fun getAllTree(
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
                    required = false) scopusAuthorId: String?
    ) =
            tasksRepository.getAll(
                    sectionsRepository.get(idSection),
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
    fun addUI(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestBody task: Task
    ) =
            tasksRepository.add(
                    sectionsRepository.get(
                            user = usersRepository.get(
                                    contactInfo = contactInfoRepository.get(
                                            email = authUser.username
                                    )
                            ),
                            number = sectionNumber,
                            title = sectionTitle
                    ),
                    task
            )

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/add-my-rest"])
    fun addRest(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestBody task: Task
    ) =
            tasksRepository.add(
                    sectionsRepository.get(
                            user = usersRepository.get(
                                    contactInfo = contactInfoRepository.get(
                                            email = authUser.username
                                    )
                            ),
                            number = sectionNumber,
                            title = sectionTitle
                    ),
                    task
            )

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/add-my-tree"])
    fun addTree(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestBody task: Task
    ) =
            tasksRepository.add(
                    sectionsRepository.get(
                            user = usersRepository.get(
                                    contactInfo = contactInfoRepository.get(
                                            email = authUser.username
                                    )
                            ),
                            number = sectionNumber,
                            title = sectionTitle
                    ),
                    task
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
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestBody newTask: Task?,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?
    ): Task? {
        val task =
                tasksRepository.set(
                        newTask,
                        idTask,
                        sectionsRepository.get(
                                user = usersRepository.get(
                                        contactInfo = contactInfoRepository.get(
                                                email = authUser.username
                                        )
                                ),
                                number = sectionNumber,
                                title = sectionTitle
                        ),
                        usersRepository.get(
                                contactInfo = contactInfoRepository.get(
                                        email = authUser.username
                                )
                        ),
                        taskNumber,
                        taskTitle
                )
        return tasksRepository.get(task?.idTask)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-my-rest"])
    fun setMyRest(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestBody newTask: Task?,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?
    ): Task? {
        val task =
                tasksRepository.set(
                        newTask,
                        idTask,
                        sectionsRepository.get(
                                user = usersRepository.get(
                                        contactInfo = contactInfoRepository.get(
                                                email = authUser.username
                                        )
                                ),
                                number = sectionNumber,
                                title = sectionTitle
                        ),
                        usersRepository.get(
                                contactInfo = contactInfoRepository.get(
                                        email = authUser.username
                                )
                        ),
                        taskNumber,
                        taskTitle
                )
        return tasksRepository.get(task?.idTask)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-my-tree"])
    fun setMyTree(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestBody newTask: Task?,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?
    ): Task? {
        val task =
                tasksRepository.set(
                        newTask,
                        idTask,
                        sectionsRepository.get(
                                user = usersRepository.get(
                                        contactInfo = contactInfoRepository.get(
                                                email = authUser.username
                                        )
                                ),
                                number = sectionNumber,
                                title = sectionTitle
                        ),
                        usersRepository.get(
                                contactInfo = contactInfoRepository.get(
                                        email = authUser.username
                                )
                        ),
                        taskNumber,
                        taskTitle
                )
        return tasksRepository.get(task?.idTask)
    }


    //todo forbidden
    /** ============================== MARK
     *                                 INSTRUCTOR ============================== */

    @JsonView(View.UI::class)
    @PutMapping(value = ["put/set-mark-instructor-ui"])
    fun setMarkInstructorUI(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestBody task: Task
    ): Task? {

        if (task.section.user.studyInfo?.instructor?.contactInfo?.email == authUser.username) {

            val newTask =
                    tasksRepository.setMarkInstructor(task)

            return tasksRepository.get(newTask?.idTask)
        }

        return Task()
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-mark-instructor-rest"])
    fun setMarkInstructorRest(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestBody task: Task
    ): Task? {

        if (task.section.user.studyInfo?.instructor?.contactInfo?.email == authUser.username) {

            val newTask =
                    tasksRepository.setMarkInstructor(task)

            return tasksRepository.get(newTask?.idTask)
        }

        return Task()
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-mark-instructor-tree"])
    fun setMarkInstructorTree(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestBody task: Task
    ): Task? {

        if (task.section.user.studyInfo?.instructor?.contactInfo?.email == authUser.username) {

            val newTask =
                    tasksRepository.setMarkInstructor(task)

            return tasksRepository.get(newTask?.idTask)
        }

        return Task()
    }


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/set-one-ui"])
    fun setUI(
            @RequestBody newTask: Task?,
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
                    value = "task_number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) title: String?
    ): Task? {
        val task =
                tasksRepository.set(
                        newTask,
                        idTask,
                        sectionsRepository.get(idSection),
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
        return tasksRepository.get(task?.idTask)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-one-rest"])
    fun setRest(
            @RequestBody newTask: Task?,
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
    ): Task? {
        val task =
                tasksRepository.set(
                        newTask,
                        idTask,
                        sectionsRepository.get(idSection),
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
        return tasksRepository.get(task?.idTask)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-one-tree"])
    fun setTree(
            @RequestBody newTask: Task?,
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
    ): Task? {
        val task =
                tasksRepository.set(
                        newTask,
                        idTask,
                        sectionsRepository.get(idSection),
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
        return tasksRepository.get(task?.idTask)
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
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?
    ) =
            tasksRepository.delete(
                    section = sectionsRepository.get(
                            user = usersRepository.get(
                                    contactInfo = contactInfoRepository.get(
                                            email = authUser.username
                                    )
                            ),
                            number = sectionNumber,
                            title = sectionTitle
                    ),
                    number = taskNumber,
                    title = taskTitle
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/my-rest"])
    fun deleteMyRest(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?
    ) =
            tasksRepository.delete(
                    section = sectionsRepository.get(
                            user = usersRepository.get(
                                    contactInfo = contactInfoRepository.get(
                                            email = authUser.username
                                    )
                            ),
                            number = sectionNumber,
                            title = sectionTitle
                    ),
                    number = taskNumber,
                    title = taskTitle
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/my-tree"])
    fun deleteMyTree(
            @AuthenticationPrincipal authUser: UserDetails,
            @RequestParam(
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?
    ) =
            tasksRepository.delete(
                    section = sectionsRepository.get(
                            user = usersRepository.get(
                                    contactInfo = contactInfoRepository.get(
                                            email = authUser.username
                                    )
                            ),
                            number = sectionNumber,
                            title = sectionTitle
                    ),
                    number = taskNumber,
                    title = taskTitle
            )


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/one-ui"])
    fun deleteUI(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?,
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
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?
    ) =
            tasksRepository.delete(
                    idTask,
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
                            sectionNumber,
                            sectionTitle
                    ),
                    taskNumber,
                    taskTitle
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/one-rest"])
    fun deleteRest(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?,
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
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?
    ) =
            tasksRepository.delete(
                    idTask,
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
                            sectionNumber,
                            sectionTitle
                    ),
                    taskNumber,
                    taskTitle
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/one-tree"])
    fun deleteTree(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "task_number",
                    required = false) taskNumber: Int?,
            @RequestParam(
                    value = "task_title",
                    required = false) taskTitle: String?,
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
                    value = "section_number",
                    required = false) sectionNumber: Int?,
            @RequestParam(
                    value = "section_title",
                    required = false) sectionTitle: String?
    ) =
            tasksRepository.delete(
                    idTask,
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
                            sectionNumber,
                            sectionTitle
                    ),
                    taskNumber,
                    taskTitle
            )


    /** ============================== ALL ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["delete/all-ui"])
    fun deleteAllUI(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            tasksRepository.deleteAll(sectionsRepository.get(idSection))

    @JsonView(View.REST::class)
    @GetMapping(value = ["delete/all-rest"])
    fun deleteAllRest(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            tasksRepository.deleteAll(sectionsRepository.get(idSection))

    @JsonView(View.TREE::class)
    @GetMapping(value = ["delete/all-tree"])
    fun deleteAllTree(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            tasksRepository.deleteAll(sectionsRepository.get(idSection))

}
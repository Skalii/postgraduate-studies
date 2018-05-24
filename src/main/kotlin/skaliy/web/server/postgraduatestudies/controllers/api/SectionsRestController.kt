package skaliy.web.server.postgraduatestudies.controllers.api


import com.fasterxml.jackson.annotation.JsonView

import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import skaliy.web.server.postgraduatestudies.entities.Section
import skaliy.web.server.postgraduatestudies.repositories.SectionsRepository
import skaliy.web.server.postgraduatestudies.repositories.TasksRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/sections"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class SectionsRestController(
        val sectionsRepository: SectionsRepository,
        val tasksRepository: TasksRepository,
        val usersRepository: UsersRepository
) {


    /**
     * Queries to
     * GET
     * records
     */


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-ui"])
    fun getOneUI(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            sectionsRepository.get(idSection)

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getOneRest(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            sectionsRepository.get(idSection)

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getOneTree(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            sectionsRepository.get(idSection)


    /** ============================== ALL ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-ui"])
    fun getAllUI() = sectionsRepository.getAll()

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest() = sectionsRepository.getAll()

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree() = sectionsRepository.getAll()


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
            sectionsRepository.getByTask(tasksRepository.get(idTask))

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
            sectionsRepository.getByTask(tasksRepository.get(idTask))

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
            sectionsRepository.getByTask(tasksRepository.get(idTask))

/*

    */
/** ============================== ONE
     *                                 BY
     *                                 USER ============================== *//*



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
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.getByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    ),
                    number,
                    title)

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
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.getByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    ),
                    number,
                    title)

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
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.getByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    ),
                    number,
                    title)


    */
/** ============================== ALL
     *                                 BY
     *                                 USER ============================== *//*



    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-by-user-ui"])
    fun getAllByUserUI(
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?
    ) =
            sectionsRepository.getAllByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    )
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-by-user-rest"])
    fun getAllByUserRest(
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?
    ) =
            sectionsRepository.getAllByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    )
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-by-user-tree"])
    fun getAllByUserTree(
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?
    ) =
            sectionsRepository.getAllByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    )
            )
*/


    /**
     * Queries to
     * UPDATE
     * records
     */


    /** ============================== INSERT ============================== */


    @JsonView(View.UI::class)
    @PostMapping(value = ["post/create-ui"])
    fun createUI(@RequestBody section: Section) =
            sectionsRepository.create(section)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/create-rest"])
    fun createRest(@RequestBody section: Section) =
            sectionsRepository.create(section)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/create-tree"])
    fun createTree(@RequestBody section: Section) =
            sectionsRepository.create(section)


    /** ============================== UPDATE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/update-ui"])
    fun updateUI(
            @RequestBody newSection: Section?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            sectionsRepository.update(
                    newSection,
                    idSection
            )

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/update-rest"])
    fun updateRest(
            @RequestBody newSection: Section?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            sectionsRepository.update(
                    newSection,
                    idSection
            )

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/update-tree"])
    fun updateTree(
            @RequestBody newSection: Section?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            sectionsRepository.update(
                    newSection,
                    idSection
            )


    /** ============================== UPDATE
     *                                 BY
     *                                 TASK ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["put/update-by-task-ui"])
    fun updateByTaskUI(
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
                sectionsRepository.updateByTask(
                        newSection,
                        tasksRepository.get(idTask))
        return Section(
                section?.idSection!!,
                newSection?.number!!,
                newSection.title,
                section.user
        )
    }

    @JsonView(View.REST::class)
    @GetMapping(value = ["put/update-by-task-rest"])
    fun updateByTaskRest(
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
                sectionsRepository.updateByTask(
                        newSection,
                        tasksRepository.get(idTask))
        return Section(
                section?.idSection!!,
                newSection?.number!!,
                newSection.title,
                section.user
        )
    }

    @JsonView(View.TREE::class)
    @GetMapping(value = ["put/update-by-task-tree"])
    fun updateByTaskTree(
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
                sectionsRepository.updateByTask(
                        newSection,
                        tasksRepository.get(idTask))
        return Section(
                section?.idSection!!,
                newSection?.number!!,
                newSection.title,
                section.user
        )
    }

/*

    */
/** ============================== UPDATE
     *                                 BY
     *                                 USER ============================== *//*



    @JsonView(View.UI::class)
    @GetMapping(value = ["put/update-by-user-ui"])
    fun updateByUserUI(
            @RequestBody newSection: Section?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ): Section? {
        val section =
                sectionsRepository.updateByUser(
                        newSection,
                        usersRepository.get(
                                idUser,
                                idContactInfo,
                                idStudyInfo,
                                idScientificLinks),
                        number,
                        title
                )
        return Section(
                section?.idSection!!,
                newSection?.number!!,
                newSection.title,
                section.user
        )
    }

    @JsonView(View.REST::class)
    @GetMapping(value = ["put/update-by-user-rest"])
    fun updateByUserRest(
            @RequestBody newSection: Section?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ): Section? {
        val section =
                sectionsRepository.updateByUser(
                        newSection,
                        usersRepository.get(
                                idUser,
                                idContactInfo,
                                idStudyInfo,
                                idScientificLinks),
                        number,
                        title
                )
        return Section(
                section?.idSection!!,
                newSection?.number!!,
                newSection.title,
                section.user
        )
    }

    @JsonView(View.TREE::class)
    @GetMapping(value = ["put/update-by-user-tree"])
    fun updateByUserTree(
            @RequestBody newSection: Section?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ): Section? {
        val section =
                sectionsRepository.updateByUser(
                        newSection,
                        usersRepository.get(
                                idUser,
                                idContactInfo,
                                idStudyInfo,
                                idScientificLinks),
                        number,
                        title
                )
        return Section(
                section?.idSection!!,
                newSection?.number!!,
                newSection.title,
                section.user
        )
    }
*/


    /** ============================== DELETE ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/delete-ui"])
    fun deleteUI(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            sectionsRepository.delete(idSection)

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/delete-rest"])
    fun deleteRest(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            sectionsRepository.delete(idSection)

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/delete-tree"])
    fun deleteTree(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            sectionsRepository.delete(idSection)


    /** ============================== DELETE
     *                                 BY
     *                                 TASK ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/delete-by-task-ui"])
    fun deleteByTaskUI(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            sectionsRepository.deleteByTask(tasksRepository.get(idTask))

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/delete-by-task-rest"])
    fun deleteByTaskRest(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            sectionsRepository.deleteByTask(tasksRepository.get(idTask))

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/delete-by-task-tree"])
    fun deleteByTaskTree(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            sectionsRepository.deleteByTask(tasksRepository.get(idTask))

/*

    */
/** ============================== DELETE
     *                                 BY
     *                                 USER ============================== *//*



    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/delete-by-user-ui"])
    fun deleteByUserUI(
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.deleteByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    ),
                    number,
                    title)

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/delete-by-user-rest"])
    fun deleteByUserRest(
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.deleteByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    ),
                    number,
                    title
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/delete-by-user-tree"])
    fun deleteByUserTree(
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            sectionsRepository.deleteByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    ),
                    number,
                    title
            )


    */
/** ============================== DELETE
     *                                 ALL
     *                                 BY
     *                                 USER ============================== *//*



    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/delete-all-by-user-ui"])
    fun deleteAllByUserUI(
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?
    ) =
            sectionsRepository.deleteAllByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    )
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/delete-all-by-user-rest"])
    fun deleteAllByUserRest(
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?
    ) =
            sectionsRepository.deleteAllByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    )
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/delete-all-by-user-tree"])
    fun deleteAllByUserTree(
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?
    ) =
            sectionsRepository.deleteAllByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    )
            )
*/

}
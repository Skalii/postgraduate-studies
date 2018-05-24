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
import skaliy.web.server.postgraduatestudies.entities.Task

import skaliy.web.server.postgraduatestudies.repositories.SectionsRepository
import skaliy.web.server.postgraduatestudies.repositories.TasksRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/tasks"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class TasksRestController(
        val tasksRepository: TasksRepository,
        val sectionsRepository: SectionsRepository,
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
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            tasksRepository.get(idTask)

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getOneRest(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            tasksRepository.get(idTask)

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getOneTree(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            tasksRepository.get(idTask)


    /** ============================== ALL ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-ui"])
    fun getAllUI() = tasksRepository.getAll()

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest() = tasksRepository.getAll()

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree() = tasksRepository.getAll()


    /** ============================== ONE
     *                                 BY
     *                                 SECTION ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-by-section-ui"])
    fun getOneBySectionUI(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            tasksRepository.getBySection(
                    sectionsRepository.get(idSection),
                    number,
                    title
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-by-section-rest"])
    fun getOneBySectionRest(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            tasksRepository.getBySection(
                    sectionsRepository.get(idSection),
                    number,
                    title
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-by-section-tree"])
    fun getOneBySectionTree(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            tasksRepository.getBySection(
                    sectionsRepository.get(idSection),
                    number,
                    title
            )


    /** ============================== ALL
     *                                 BY
     *                                 SECTION ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-by-section-ui"])
    fun getAllBySectionUI(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            tasksRepository.getAllBySection(sectionsRepository.get(idSection))

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-by-section-rest"])
    fun getAllBySectionRest(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            tasksRepository.getAllBySection(sectionsRepository.get(idSection))

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-by-section-tree"])
    fun getAllBySectionTree(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            tasksRepository.getAllBySection(sectionsRepository.get(idSection))

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
            tasksRepository.getByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    ),
                    number,
                    title
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
            tasksRepository.getByUser(
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
            tasksRepository.getByUser(
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
            tasksRepository.getAllByUser(
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
            tasksRepository.getAllByUser(
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
            tasksRepository.getAllByUser(
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
    fun createUI(@RequestBody task: Task) =
            tasksRepository.create(task)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/create-rest"])
    fun createRest(@RequestBody task: Task) =
            tasksRepository.create(task)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/create-tree"])
    fun createTree(@RequestBody task: Task) =
            tasksRepository.create(task)


    /** ============================== UPDATE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/update-ui"])
    fun updateUI(
            @RequestBody newTask: Task?,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            tasksRepository.update(
                    newTask,
                    idTask
            )

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/update-rest"])
    fun updateRest(
            @RequestBody newTask: Task?,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            tasksRepository.update(
                    newTask,
                    idTask
            )

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/update-tree"])
    fun updateTree(
            @RequestBody newTask: Task?,
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            tasksRepository.update(
                    newTask,
                    idTask
            )


    /** ============================== UPDATE
     *                                 BY
     *                                 SECTION ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["put/update-by-section-ui"])
    fun updateBySectionUI(
            @RequestBody newTask: Task?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            tasksRepository.updateBySection(
                    newTask,
                    sectionsRepository.get(idSection),
                    number,
                    title
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["put/update-by-section-rest"])
    fun updateBySectionRest(
            @RequestBody newTask: Task?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            tasksRepository.updateBySection(
                    newTask,
                    sectionsRepository.get(idSection),
                    number,
                    title
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["put/update-by-section-tree"])
    fun updateBySectionTree(
            @RequestBody newTask: Task?,
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            tasksRepository.updateBySection(
                    newTask,
                    sectionsRepository.get(idSection),
                    number,
                    title
            )

/*

    */
/** ============================== UPDATE
     *                                 BY
     *                                 USER ============================== *//*



    @JsonView(View.UI::class)
    @GetMapping(value = ["put/update-by-user-ui"])
    fun updateByUserUI(
            @RequestBody newTask: Task?,
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
            tasksRepository.updateByUser(
                    newTask,
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    ),
                    number,
                    title
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["put/update-by-user-rest"])
    fun updateByUserRest(
            @RequestBody newTask: Task?,
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
            tasksRepository.updateByUser(
                    newTask,
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
    @GetMapping(value = ["put/update-by-user-tree"])
    fun updateByUserTree(
            @RequestBody newTask: Task?,
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
            tasksRepository.updateByUser(
                    newTask,
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


    /** ============================== DELETE ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/delete-ui"])
    fun deleteUI(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            tasksRepository.delete(idTask)

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/delete-rest"])
    fun deleteRest(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            tasksRepository.delete(idTask)

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/delete-tree"])
    fun deleteTree(
            @RequestParam(
                    value = "id_task",
                    required = false) idTask: Int?
    ) =
            tasksRepository.delete(idTask)


    /** ============================== DELETE
     *                                 BY
     *                                 SECTION ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["delete/delete-by-section-ui"])
    fun deleteBySectionUI(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            tasksRepository.deleteBySection(
                    sectionsRepository.get(idSection),
                    number,
                    title
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["delete/delete-by-section-rest"])
    fun deleteBySectionRest(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            tasksRepository.deleteBySection(
                    sectionsRepository.get(idSection),
                    number,
                    title
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["delete/delete-by-section-tree"])
    fun deleteBySectionTree(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: Int?,
            @RequestParam(
                    value = "title",
                    required = false) title: String?
    ) =
            tasksRepository.deleteBySection(
                    sectionsRepository.get(idSection),
                    number,
                    title
            )


    /** ============================== DELETE
     *                                 ALL
     *                                 BY
     *                                 SECTION ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["delete/delete-all-by-section-ui"])
    fun deleteAllBySectionUI(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            tasksRepository.deleteAllBySection(sectionsRepository.get(idSection))

    @JsonView(View.REST::class)
    @GetMapping(value = ["delete/delete-all-by-section-rest"])
    fun deleteAllBySectionRest(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            tasksRepository.deleteAllBySection(sectionsRepository.get(idSection))

    @JsonView(View.TREE::class)
    @GetMapping(value = ["delete/delete-all-by-section-tree"])
    fun deleteAllBySectionTree(
            @RequestParam(
                    value = "id_section",
                    required = false) idSection: Int?
    ) =
            tasksRepository.deleteAllBySection(sectionsRepository.get(idSection))

}
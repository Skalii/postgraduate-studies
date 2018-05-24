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

import skaliy.web.server.postgraduatestudies.entities.Degree
import skaliy.web.server.postgraduatestudies.repositories.DegreesRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/degrees"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class DegreesRestController(
        val degreesRepository: DegreesRepository,
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
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.get(
                    idDegree,
                    name,
                    branch
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getOneRest(
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.get(
                    idDegree,
                    name,
                    branch
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getOneTree(
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.get(
                    idDegree,
                    name,
                    branch
            )


    /** ============================== ALL ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-ui"])
    fun getAllUI() = degreesRepository.getAll()

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest() = degreesRepository.getAll()

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree() = degreesRepository.getAll()


    /** ============================== ALL
     *                                 BY
     *                                 NAME ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-by-name-ui"])
    fun getAllByNameUI(
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            degreesRepository.getAllByName(name)

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-by-name-rest"])
    fun getAllByNameRest(
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            degreesRepository.getAllByName(name)

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-by-name-tree"])
    fun getAllByNameTree(
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            degreesRepository.getAllByName(name)


    /** ============================== ALL
     *                                 BY
     *                                 BRANCH ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-by-branch-ui"])
    fun getAllByBranchUI(
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.getAllByBranch(branch)

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-by-branch-rest"])
    fun getAllByBranchRest(
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.getAllByBranch(branch)

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-by-branch-tree"])
    fun getAllByBranchTree(
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.getAllByBranch(branch)

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
                    required = false) idScientificLinks: Int?
    ) =
            degreesRepository.getByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    ))

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
                    required = false) idScientificLinks: Int?
    ) =
            degreesRepository.getByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    ))

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
                    required = false) idScientificLinks: Int?
    ) =
            degreesRepository.getByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    ))
*/


    /**
     * Queries to
     * UPDATE
     * records
     */


    /** ============================== INSERT ============================== */


    @JsonView(View.UI::class)
    @PostMapping(value = ["post/create-ui"])
    fun createUI(@RequestBody degree: Degree) =
            degreesRepository.create(degree)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/create-rest"])
    fun createRest(@RequestBody degree: Degree) =
            degreesRepository.create(degree)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/create-tree"])
    fun createTree(@RequestBody degree: Degree) =
            degreesRepository.create(degree)


    /** ============================== UPDATE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/update-ui"])
    fun updateUI(
            @RequestBody newDegree: Degree?,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.update(
                    newDegree,
                    idDegree,
                    name,
                    branch
            )

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/update-rest"])
    fun updateRest(
            @RequestBody newDegree: Degree?,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.update(
                    newDegree,
                    idDegree,
                    name,
                    branch
            )

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/update-tree"])
    fun updateTree(
            @RequestBody newDegree: Degree?,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.update(
                    newDegree,
                    idDegree,
                    name,
                    branch
            )


    /** ============================== DELETE ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/delete-ui"])
    fun deleteUI(
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.delete(
                    idDegree,
                    name,
                    branch
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/delete-rest"])
    fun deleteRest(
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.delete(
                    idDegree,
                    name,
                    branch
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/delete-tree"])
    fun deleteTree(
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.delete(
                    idDegree,
                    name,
                    branch
            )

}
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
import skaliy.web.server.postgraduatestudies.entities.Speciality

import skaliy.web.server.postgraduatestudies.repositories.BranchesRepository
import skaliy.web.server.postgraduatestudies.repositories.SpecialitiesRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/specialities"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class SpecialitiesRestController(
        val specialitiesRepository: SpecialitiesRepository,
        val branchesRepository: BranchesRepository,
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


    /** ============================== ALL ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-ui"])
    fun getAllUI() = specialitiesRepository.getAll()

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest() = specialitiesRepository.getAll()

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree() = specialitiesRepository.getAll()


    /** ============================== ALL
     *                                 BY
     *                                 BRANCH ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-by-branch-ui"])
    fun getAllByBranchUI(
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
            specialitiesRepository.getAllByBranch(
                    branchesRepository.get(
                            idBranch,
                            number,
                            name
                    ))

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-by-branch-rest"])
    fun getAllByBranchRest(
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
            specialitiesRepository.getAllByBranch(
                    branchesRepository.get(
                            idBranch,
                            number,
                            name
                    ))

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-by-branch-tree"])
    fun getAllByBranchTree(
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
            specialitiesRepository.getAllByBranch(
                    branchesRepository.get(
                            idBranch,
                            number,
                            name
                    ))

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
            specialitiesRepository.getByUser(
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
            specialitiesRepository.getByUser(
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
            specialitiesRepository.getByUser(
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
    fun createUI(@RequestBody speciality: Speciality?) =
            specialitiesRepository.create(speciality)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/create-rest"])
    fun createRest(@RequestBody speciality: Speciality?) =
            specialitiesRepository.create(speciality)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/create-tree"])
    fun createTree(@RequestBody speciality: Speciality?) =
            specialitiesRepository.create(speciality)


    /** ============================== UPDATE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/update-ui"])
    fun updateUI(
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
    ) =
            specialitiesRepository.update(
                    newSpeciality,
                    idSpeciality,
                    number,
                    name
            )

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/update-rest"])
    fun updateRest(
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
    ) =
            specialitiesRepository.update(
                    newSpeciality,
                    idSpeciality,
                    number,
                    name
            )

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/update-tree"])
    fun updateTree(
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
    ) =
            specialitiesRepository.update(
                    newSpeciality,
                    idSpeciality,
                    number,
                    name
            )


    /** ============================== DELETE ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/delete-ui"])
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
    @DeleteMapping(value = ["delete/delete-rest"])
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
    @DeleteMapping(value = ["delete/delete-tree"])
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


    /** ============================== DELETE
     *                                 ALL
     *                                 BY
     *                                 BRANCH ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/delete-all-by-branch-ui"])
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
            specialitiesRepository.deleteAllByBranch(
                    branchesRepository.get(
                            idBranch,
                            number,
                            name
                    )
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/delete-all-by-branch-rest"])
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
            specialitiesRepository.deleteAllByBranch(
                    branchesRepository.get(
                            idBranch,
                            number,
                            name
                    )
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/delete-all-by-branch-tree"])
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
            specialitiesRepository.deleteAllByBranch(
                    branchesRepository.get(
                            idBranch,
                            number,
                            name
                    )
            )

}
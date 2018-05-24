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

import skaliy.web.server.postgraduatestudies.entities.Faculty
import skaliy.web.server.postgraduatestudies.repositories.DepartmentsRepository
import skaliy.web.server.postgraduatestudies.repositories.FacultiesRepository
import skaliy.web.server.postgraduatestudies.repositories.InstitutesRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/faculties"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class FacultiesRestController(
        val facultiesRepository: FacultiesRepository,
        val institutesRepository: InstitutesRepository,
        val departmentsRepository: DepartmentsRepository,
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


    /** ============================== ALL ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-ui"])
    fun getAllUI() = facultiesRepository.getAll()

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest() = facultiesRepository.getAll()

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree() = facultiesRepository.getAll()


    /** ============================== ALL
     *                                 BY
     *                                 INSTITUTE ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-by-institute-ui"])
    fun getAllByInstituteUI(
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            facultiesRepository.getAllByInstitute(
                    institutesRepository.get(
                            idInstitute,
                            name
                    ))

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-by-institute-rest"])
    fun getAllByInstituteRest(
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            facultiesRepository.getAllByInstitute(
                    institutesRepository.get(
                            idInstitute,
                            name
                    ))

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-by-institute-tree"])
    fun getAllByInstituteTree(
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            facultiesRepository.getAllByInstitute(
                    institutesRepository.get(
                            idInstitute,
                            name
                    ))


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
            facultiesRepository.getByDepartment(
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
            facultiesRepository.getByDepartment(
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
            facultiesRepository.getByDepartment(
                    departmentsRepository.get(
                            idDepartment,
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
            facultiesRepository.getByUser(
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
            facultiesRepository.getByUser(
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
            facultiesRepository.getByUser(
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
    fun createUI(@RequestBody faculty: Faculty?) =
            facultiesRepository.create(faculty)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/create-rest"])
    fun createRest(@RequestBody faculty: Faculty?) =
            facultiesRepository.create(faculty)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/create-tree"])
    fun createTree(@RequestBody faculty: Faculty?) =
            facultiesRepository.create(faculty)


    /** ============================== UPDATE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/update-ui"])
    fun updateUI(
            @RequestBody newFaculty: Faculty?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            facultiesRepository.update(
                    newFaculty,
                    idFaculty,
                    name
            )

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/update-rest"])
    fun updateRest(
            @RequestBody newFaculty: Faculty?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            facultiesRepository.update(
                    newFaculty,
                    idFaculty,
                    name
            )

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/update-tree"])
    fun updateTree(
            @RequestBody newFaculty: Faculty?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            facultiesRepository.update(
                    newFaculty,
                    idFaculty,
                    name
            )


    /** ============================== DELETE ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/delete-ui"])
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
    @DeleteMapping(value = ["delete/delete-rest"])
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
    @DeleteMapping(value = ["delete/delete-tree"])
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
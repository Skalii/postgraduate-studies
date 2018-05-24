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
import skaliy.web.server.postgraduatestudies.entities.StudyInfo

import skaliy.web.server.postgraduatestudies.repositories.StudyInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/study-info"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class StudyInfoRestController(
        val studyInfoRepository: StudyInfoRepository,
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
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?
    ) =
            studyInfoRepository.get(idStudyInfo)

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getOneRest(
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?
    ) =
            studyInfoRepository.get(idStudyInfo)

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getOneTree(
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?
    ) =
            studyInfoRepository.get(idStudyInfo)


    /** ============================== ALL ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-ui"])
    fun getAllUI() = studyInfoRepository.getAll()

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest() = studyInfoRepository.getAll()

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree() = studyInfoRepository.getAll()

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
            studyInfoRepository.getByUser(
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
            studyInfoRepository.getByUser(
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
            studyInfoRepository.getByUser(
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
    fun createUI(@RequestBody studyInfo: StudyInfo?) =
            studyInfoRepository.create(studyInfo)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/create-rest"])
    fun createRest(@RequestBody studyInfo: StudyInfo?) =
            studyInfoRepository.create(studyInfo)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/create-tree"])
    fun createTree(@RequestBody studyInfo: StudyInfo?) =
            studyInfoRepository.create(studyInfo)


    /** ============================== UPDATE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/update-ui"])
    fun updateUI(
            @RequestBody newStudyInfo: StudyInfo?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?
    ) =
            studyInfoRepository.update(
                    newStudyInfo,
                    idStudyInfo
            )

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/update-rest"])
    fun updateRest(
            @RequestBody newStudyInfo: StudyInfo?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?
    ) =
            studyInfoRepository.update(
                    newStudyInfo,
                    idStudyInfo
            )

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/update-tree"])
    fun updateTree(
            @RequestBody newStudyInfo: StudyInfo?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?
    ) =
            studyInfoRepository.update(
                    newStudyInfo,
                    idStudyInfo
            )

/*

    */
/** ============================== UPDATE
     *                                 BY
     *                                 USER ============================== *//*



    @JsonView(View.UI::class)
    @PutMapping(value = ["put/update-by-user-ui"])
    fun updateByUserUI(
            @RequestBody newStudyInfo: StudyInfo?,
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
    ): StudyInfo? {
        val studyInfo =
                studyInfoRepository.updateByUser(
                        newStudyInfo,
                        usersRepository.get(
                                idUser,
                                idContactInfo,
                                idStudyInfo,
                                idScientificLinks
                        )
                )
        return StudyInfo(
                studyInfo?.idStudyInfo!!,
                newStudyInfo?.year!!,
                newStudyInfo.form,
                newStudyInfo.basis,
                newStudyInfo.themeTitle,
                newStudyInfo.instructor,
                studyInfo.user
        )
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/update-by-user-rest"])
    fun updateByUserRest(
            @RequestBody newStudyInfo: StudyInfo?,
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
    ): StudyInfo? {
        val studyInfo =
                studyInfoRepository.updateByUser(
                        newStudyInfo,
                        usersRepository.get(
                                idUser,
                                idContactInfo,
                                idStudyInfo,
                                idScientificLinks
                        )
                )
        return StudyInfo(
                studyInfo?.idStudyInfo!!,
                newStudyInfo?.year!!,
                newStudyInfo.form,
                newStudyInfo.basis,
                newStudyInfo.themeTitle,
                newStudyInfo.instructor,
                studyInfo.user
        )
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/update-by-user-tree"])
    fun updateByUserTree(
            @RequestBody newStudyInfo: StudyInfo?,
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
    ): StudyInfo? {
        val studyInfo =
                studyInfoRepository.updateByUser(
                        newStudyInfo,
                        usersRepository.get(
                                idUser,
                                idContactInfo,
                                idStudyInfo,
                                idScientificLinks
                        )
                )
        return StudyInfo(
                studyInfo?.idStudyInfo!!,
                newStudyInfo?.year!!,
                newStudyInfo.form,
                newStudyInfo.basis,
                newStudyInfo.themeTitle,
                newStudyInfo.instructor,
                studyInfo.user
        )
    }
*/


    /** ============================== DELETE ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/delete-ui"])
    fun deleteUI(
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?
    ) =
            studyInfoRepository.delete(idStudyInfo)

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/delete-rest"])
    fun deleteRest(
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?
    ) =
            studyInfoRepository.delete(idStudyInfo)

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/delete-tree"])
    fun deleteTree(
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?
    ) =
            studyInfoRepository.delete(idStudyInfo)

/*

    */
/** ============================== DELETE
     *                                 BY
     *                                 USER ============================== *//*



    @JsonView(View.UI::class)
    @PutMapping(value = ["delete/delete-by-user-ui"])
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
                    required = false) idScientificLinks: Int?
    ) =
            studyInfoRepository.deleteByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    )
            )

    @JsonView(View.REST::class)
    @PutMapping(value = ["delete/delete-by-user-rest"])
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
                    required = false) idScientificLinks: Int?
    ) =
            studyInfoRepository.deleteByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    )
            )

    @JsonView(View.TREE::class)
    @PutMapping(value = ["delete/delete-by-user-tree"])
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
                    required = false) idScientificLinks: Int?
    ) =
            studyInfoRepository.deleteByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    )
            )
*/

}
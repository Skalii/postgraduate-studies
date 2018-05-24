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

import skaliy.web.server.postgraduatestudies.entities.ScientificLinks
import skaliy.web.server.postgraduatestudies.repositories.ScientificLinksRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/scientific-links"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class ScientificLinksRestController(
        val scientificLinksRepository: ScientificLinksRepository,
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
            scientificLinksRepository.get(
                    idScientificLinks,
                    orcid,
                    researcherid,
                    googleScholarId,
                    scopusAuthorId
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getOneRest(
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
                    required = false) googleScholarId: String?, @RequestParam(
                    value = "scopus_author_id",
                    required = false) scopusAuthorId: String?
    ) =
            scientificLinksRepository.get(
                    idScientificLinks,
                    orcid,
                    researcherid,
                    googleScholarId,
                    scopusAuthorId
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getOneTree(
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
                    required = false) googleScholarId: String?, @RequestParam(
                    value = "scopus_author_id",
                    required = false) scopusAuthorId: String?
    ) =
            scientificLinksRepository.get(
                    idScientificLinks,
                    orcid,
                    researcherid,
                    googleScholarId,
                    scopusAuthorId
            )


    /** ============================== ALL ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-ui"])
    fun getAllUI() = scientificLinksRepository.getAll()

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest() = scientificLinksRepository.getAll()

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree() = scientificLinksRepository.getAll()

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
            scientificLinksRepository.getByUser(
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
            scientificLinksRepository.getByUser(
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
            scientificLinksRepository.getByUser(
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
    fun createUI(@RequestBody scientificLinks: ScientificLinks) =
            scientificLinksRepository.create(scientificLinks)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/create-rest"])
    fun createRest(@RequestBody scientificLinks: ScientificLinks) =
            scientificLinksRepository.create(scientificLinks)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/create-tree"])
    fun createTree(@RequestBody scientificLinks: ScientificLinks) =
            scientificLinksRepository.create(scientificLinks)


    /** ============================== UPDATE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/update-ui"])
    fun updateUI(
            @RequestBody newScientificLinks: ScientificLinks?,
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
            scientificLinksRepository.update(
                    newScientificLinks,
                    idScientificLinks,
                    orcid,
                    researcherid,
                    googleScholarId,
                    scopusAuthorId
            )

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/update-rest"])
    fun updateRest(
            @RequestBody newScientificLinks: ScientificLinks?,
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
            scientificLinksRepository.update(
                    newScientificLinks,
                    idScientificLinks,
                    orcid,
                    researcherid,
                    googleScholarId,
                    scopusAuthorId
            )

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/update-tree"])
    fun updateTree(
            @RequestBody newScientificLinks: ScientificLinks?,
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
            scientificLinksRepository.update(
                    newScientificLinks,
                    idScientificLinks,
                    orcid,
                    researcherid,
                    googleScholarId,
                    scopusAuthorId
            )

/*

    */
/** ============================== UPDATE
     *                                 BY
     *                                 USER ============================== *//*



    @JsonView(View.UI::class)
    @PutMapping(value = ["put/update-by-user-ui"])
    fun updateByUserUI(
            @RequestBody newScientificLinks: ScientificLinks,
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
    ): ScientificLinks? {
        val scientificLinks =
                scientificLinksRepository.updateByUser(
                        newScientificLinks,
                        usersRepository.get(
                                idUser,
                                idContactInfo,
                                idStudyInfo,
                                idScientificLinks
                        )
                )
        return ScientificLinks(
                scientificLinks?.idScientificLinks!!,
                newScientificLinks.orcid,
                newScientificLinks.researcherid,
                newScientificLinks.googleScholarId,
                newScientificLinks.scopusAuthorId,
                scientificLinks.user
        )
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/update-by-user-rest"])
    fun updateByUserRest(
            @RequestBody newScientificLinks: ScientificLinks,
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
    ): ScientificLinks? {
        val scientificLinks =
                scientificLinksRepository.updateByUser(
                        newScientificLinks,
                        usersRepository.get(
                                idUser,
                                idContactInfo,
                                idStudyInfo,
                                idScientificLinks
                        )
                )
        return ScientificLinks(
                scientificLinks?.idScientificLinks!!,
                newScientificLinks.orcid,
                newScientificLinks.researcherid,
                newScientificLinks.googleScholarId,
                newScientificLinks.scopusAuthorId,
                scientificLinks.user
        )
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/update-by-user-tree"])
    fun updateByUserTree(
            @RequestBody newScientificLinks: ScientificLinks,
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
    ): ScientificLinks? {
        val scientificLinks =
                scientificLinksRepository.updateByUser(
                        newScientificLinks,
                        usersRepository.get(
                                idUser,
                                idContactInfo,
                                idStudyInfo,
                                idScientificLinks
                        )
                )
        return ScientificLinks(
                scientificLinks?.idScientificLinks!!,
                newScientificLinks.orcid,
                newScientificLinks.researcherid,
                newScientificLinks.googleScholarId,
                newScientificLinks.scopusAuthorId,
                scientificLinks.user
        )
    }
*/


    /** ============================== DELETE ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/delete-ui"])
    fun deleteUI(
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
            scientificLinksRepository.delete(
                    idScientificLinks,
                    orcid,
                    researcherid,
                    googleScholarId,
                    scopusAuthorId
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/delete-rest"])
    fun deleteRest(
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
            scientificLinksRepository.delete(
                    idScientificLinks,
                    orcid,
                    researcherid,
                    googleScholarId,
                    scopusAuthorId
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/delete-tree"])
    fun deleteTree(
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
            scientificLinksRepository.delete(
                    idScientificLinks,
                    orcid,
                    researcherid,
                    googleScholarId,
                    scopusAuthorId
            )

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
            scientificLinksRepository.deleteByUser(
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
            scientificLinksRepository.deleteByUser(
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
            scientificLinksRepository.deleteByUser(
                    usersRepository.get(
                            idUser,
                            idContactInfo,
                            idStudyInfo,
                            idScientificLinks
                    )
            )
*/

}
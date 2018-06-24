package skaliy.web.server.postgraduatestudies.controllers.api


import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import skaliy.web.server.postgraduatestudies.entities.ScientificLinks
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.ScientificLinksRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.Json


@RequestMapping(
        value = ["/api/scientific-links"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class ScientificLinksRestController(
        val contactInfoRepository: ContactInfoRepository,
        val scientificLinksRepository: ScientificLinksRepository,
        val usersRepository: UsersRepository
) {


    /**
     *
     *      GET / SELECT
     *      requests
     *
     */


    /** ============================== MY ============================== */


    @GetMapping(value = ["get/my{-view}"])
    fun getMy(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.get(
                    view,
                    scientificLinksRepository.get(
                            idUser = usersRepository.get(
                                    authUser.username
                            ).idUser
                    )
            )


    /** ============================== ONE ============================== */


    @GetMapping(value = ["get/one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
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
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?
    ) =
            Json.get(
                    view,
                    scientificLinksRepository.get(
                            orcid,
                            researcherid,
                            googleScholarId,
                            scopusAuthorId,
                            idScientificLinks,
                            idUser ?: usersRepository.get(
                                    email,
                                    phoneNumber,
                                    idUser
                            ).idUser
                    )
            )


    /** ============================== ALL ============================== */


    @GetMapping(value = ["get/all{-view}"])
    fun getAll(@PathVariable(value = "-view") view: String) =
            Json.get(
                    view,
                    scientificLinksRepository.getAll()
            )


    /**
     *
     *      ADD / INSERT INTO
     *      requests
     *
     */


    /** ============================== ONE ============================== */


    @PostMapping(value = ["post/add{-view}"])
    fun add(
            @PathVariable(value = "-view") view: String,
            @RequestBody scientificLinks: ScientificLinks
    ) =
            Json.get(
                    view,
                    scientificLinksRepository.add(
                            scientificLinks.orcid,
                            scientificLinks.researcherid,
                            scientificLinks.googleScholarId,
                            scientificLinks.scopusAuthorId
                    )
            )


    /**
     *
     *      SET / UPDATE
     *      requests
     *
     */


    /** ============================== MY ============================== */


    @PutMapping(value = ["put/set-my{-view}"])
    fun setMy(
            @PathVariable(value = "-view") view: String,
            @RequestBody newScientificLinks: ScientificLinks,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            scientificLinksRepository.get(
                    idUser = usersRepository.get(
                            authUser.username
                    ).idUser
            ).run {

                scientificLinksRepository.set(
                        newScientificLinks,
                        idScientificLinks
                )

                return@run Json.get(
                        view,
                        ScientificLinks(
                                idScientificLinks,
                                if (newScientificLinks.orcid == "") orcid
                                else newScientificLinks.orcid,
                                if (newScientificLinks.researcherid == "") researcherid
                                else newScientificLinks.researcherid,
                                if (newScientificLinks.googleScholarId == "") googleScholarId
                                else newScientificLinks.googleScholarId,
                                if (newScientificLinks.scopusAuthorId == "") scopusAuthorId
                                else newScientificLinks.scopusAuthorId,
                                user
                        )
                )

            }


    /** ============================== ONE ============================== */


    @PutMapping(value = ["put/set{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody newScientificLinks: ScientificLinks,
            @RequestParam(
                    value = "orcid",
                    required = false) _orcid: String?,
            @RequestParam(
                    value = "researcherid",
                    required = false) _researcherid: String?,
            @RequestParam(
                    value = "google_scholar_id",
                    required = false) _googleScholarId: String?,
            @RequestParam(
                    value = "scopus_author_id",
                    required = false) _scopusAuthorId: String?,
            @RequestParam(
                    value = "id_scientific_links",
                    required = false) _idScientificLinks: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?
    ) =
            scientificLinksRepository.get(
                    _orcid,
                    _researcherid,
                    _googleScholarId,
                    _scopusAuthorId,
                    _idScientificLinks,
                    idUser ?: usersRepository.get(
                            email,
                            phoneNumber
                    ).idUser
            ).run {

                scientificLinksRepository.set(
                        newScientificLinks,
                        idScientificLinks
                )

                return@run Json.get(
                        view,
                        ScientificLinks(
                                idScientificLinks,
                                if (newScientificLinks.orcid == "") orcid
                                else newScientificLinks.orcid,
                                if (newScientificLinks.researcherid == "") researcherid
                                else newScientificLinks.researcherid,
                                if (newScientificLinks.googleScholarId == "") googleScholarId
                                else newScientificLinks.googleScholarId,
                                if (newScientificLinks.scopusAuthorId == "") scopusAuthorId
                                else newScientificLinks.scopusAuthorId,
                                user
                        )
                )

            }


    /**
     *
     *      DELETE
     *      requests
     *
     */


    /** ============================== ONE ============================== */


    @DeleteMapping(value = ["delete/one{-view}"])
    fun delete(
            @PathVariable(value = "-view") view: String,
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
                    value = "id_scientific_links",
                    required = false) idScientificLinks: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?
    ) =
            Json.get(
                    view,
                    scientificLinksRepository.delete(
                            orcid,
                            researcherid,
                            googleScholarId,
                            scopusAuthorId,
                            idScientificLinks,
                            idUser ?: usersRepository.get(
                                    email,
                                    phoneNumber
                            ).idUser
                    )
            )

}
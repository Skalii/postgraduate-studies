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

import skaliy.web.server.postgraduatestudies.entities.StudyInfo
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.StudyInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.Json


@RequestMapping(
        value = ["/api/study-info"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class StudyInfoRestController(
        val contactInfoRepository: ContactInfoRepository,
        val studyInfoRepository: StudyInfoRepository,
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
                    studyInfoRepository.get(
                            user = contactInfoRepository.get(
                                    authUser.username
                            ).user
                    )
            )


    /** ============================== ONE ============================== */


    @GetMapping(value = ["get/one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?
    ) =
            Json.get(
                    view,
                    studyInfoRepository.get(
                            idStudyInfo,
                            usersRepository.get(
                                    idUser,
                                    contactInfoRepository.get(
                                            email,
                                            phoneNumber
                                    )
                            )
                    )
            )


    /** ============================== ALL ============================== */


    @GetMapping(value = ["get/all{-view}"])
    fun getAll(@PathVariable(value = "-view") view: String) =
            Json.get(
                    view,
                    studyInfoRepository.getAll()
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
            @RequestBody studyInfo: StudyInfo

    ) =
            Json.get(
                    view,
                    studyInfoRepository.add(
                            studyInfo.year,
                            studyInfo.form.value,
                            studyInfo.basis.value,
                            studyInfo.themeTitle,
                            studyInfo.instructor.idUser
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
            @RequestBody newStudyInfo: StudyInfo,
            @AuthenticationPrincipal authUser: UserDetails
    ) =

            studyInfoRepository.get(
                    user = contactInfoRepository.get(
                            authUser.username
                    ).user
            ).run {

                studyInfoRepository.set(
                        newStudyInfo,
                        idStudyInfo
                )

                return@run Json.get(
                        view,
                        StudyInfo(
                                idStudyInfo,
                                newStudyInfo.year,
                                newStudyInfo.form,
                                newStudyInfo.basis,
                                newStudyInfo.themeTitle,
                                instructor,
                                user
                        )
                )

            }


    /** ============================== ONE ============================== */


    @PutMapping(value = ["put/set{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody newStudyInfo: StudyInfo,
            @RequestParam(
                    value = "id_study_info",
                    required = false) _idStudyInfo: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?
    ) =

            studyInfoRepository.get(
                    _idStudyInfo,
                    usersRepository.get(
                            idUser,
                            contactInfoRepository.get(
                                    email,
                                    phoneNumber
                            )
                    )
            ).run {

                studyInfoRepository.set(
                        newStudyInfo,
                        idStudyInfo
                )

                return@run Json.get(
                        view,
                        StudyInfo(
                                idStudyInfo,
                                newStudyInfo.year,
                                newStudyInfo.form,
                                newStudyInfo.basis,
                                newStudyInfo.themeTitle,
                                newStudyInfo.instructor,
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
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?
    ) =
            Json.get(
                    view,
                    studyInfoRepository.delete(
                            idStudyInfo,
                            usersRepository.get(
                                    idUser,
                                    contactInfoRepository.get(
                                            email,
                                            phoneNumber
                                    )
                            )
                    )
            )

}
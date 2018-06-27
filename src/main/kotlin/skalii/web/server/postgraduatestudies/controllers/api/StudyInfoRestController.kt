package skalii.web.server.postgraduatestudies.controllers.api


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

import skalii.web.server.postgraduatestudies.entities.StudyInfo
import skalii.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skalii.web.server.postgraduatestudies.repositories.StudyInfoRepository
import skalii.web.server.postgraduatestudies.repositories.UsersRepository
import skalii.web.server.postgraduatestudies.views.Json


@RequestMapping(
        value = ["/api/study-info"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class StudyInfoRestController(
        val contactInfoRepository: ContactInfoRepository,
        val studyInfoRepository: StudyInfoRepository,
        val usersRepository: UsersRepository
) {


    /** ============================== GET requests ============================== */


    @GetMapping(value = ["my{-view}"])
    fun getMy(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.get(
                    view,
                    studyInfoRepository.get(
                            idUser = usersRepository.get(
                                    authUser.username
                            ).idUser
                    )
            )

    @GetMapping(value = ["one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
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
                    studyInfoRepository.get(
                            idStudyInfo,
                            idUser ?: usersRepository.get(
                                    email,
                                    phoneNumber
                            ).idUser
                    )
            )

    @GetMapping(value = ["all{-view}"])
    fun getAll(@PathVariable(value = "-view") view: String) =
            Json.get(
                    view,
                    studyInfoRepository.getAll()
            )


    /** ============================== POST requests ============================== */


    @PostMapping(value = ["one{-view}"])
    fun add(
            @PathVariable(value = "-view") view: String,
            @RequestBody studyInfo: StudyInfo,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "id_instructor",
                    required = false) idUser: Int?
    ) =
            Json.get(
                    view,
                    studyInfoRepository.add(
                            studyInfo.year,
                            studyInfo.form.value,
                            studyInfo.basis.value,
                            studyInfo.themeTitle,
                            try {
                                studyInfo.instructor.idUser
                            } catch (e: Exception) {
                                idUser ?: usersRepository.get(
                                        email,
                                        phoneNumber
                                ).idUser
                            }
                    )
            )


    /** ============================== PUT requests ============================== */


    @PutMapping(value = ["my{-view}"])
    fun setMy(
            @PathVariable(value = "-view") view: String,
            @RequestBody newStudyInfo: StudyInfo,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            studyInfoRepository.get(
                    idUser = usersRepository.get(
                            authUser.username
                    ).idUser
            ).run {

                try {
                    newStudyInfo.instructor
                } catch (e: Exception) {
                    newStudyInfo.instructor = instructor
                }

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

    @PutMapping(value = ["one{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody newStudyInfo: StudyInfo,
            @RequestParam(
                    value = "id_study_info",
                    required = false) _idStudyInfo: Int?,
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

            studyInfoRepository.get(
                    _idStudyInfo,
                    idUser ?: usersRepository.get(
                            email,
                            phoneNumber
                    ).idUser
            ).run {

                try {
                    newStudyInfo.instructor
                } catch (e: Exception) {
                    newStudyInfo.instructor = instructor
                }

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


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["one{-view}"])
    fun delete(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
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
                    studyInfoRepository.delete(
                            idStudyInfo,
                            idUser ?: usersRepository.get(
                                    email,
                                    phoneNumber
                            ).idUser
                    )
            )

}
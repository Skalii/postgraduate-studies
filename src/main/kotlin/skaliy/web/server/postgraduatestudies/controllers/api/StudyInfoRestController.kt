package skaliy.web.server.postgraduatestudies.controllers.api


import com.fasterxml.jackson.annotation.JsonView

import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
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
import skaliy.web.server.postgraduatestudies.views.View


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


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/my-ui"])
    fun getMyUI(@AuthenticationPrincipal authUser: UserDetails) =
            studyInfoRepository.get(
                    user = contactInfoRepository.get(
                            email = authUser.username
                    )?.user
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-rest"])
    fun getMyRest(@AuthenticationPrincipal authUser: UserDetails) =
            studyInfoRepository.get(
                    user = contactInfoRepository.get(
                            email = authUser.username
                    )?.user
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/my-tree"])
    fun getMyTree(@AuthenticationPrincipal authUser: UserDetails) =
            studyInfoRepository.get(
                    user = contactInfoRepository.get(
                            email = authUser.username
                    )?.user
            )


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-ui"])
    fun getOneUI(
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            studyInfoRepository.get(
                    idStudyInfo,
                    usersRepository.get(
                            idUser,
                            contactInfoRepository.get(
                                    idContactInfo,
                                    phoneNumber,
                                    email
                            )
                    )
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getOneRest(
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            studyInfoRepository.get(
                    idStudyInfo,
                    usersRepository.get(
                            idUser,
                            contactInfoRepository.get(
                                    idContactInfo,
                                    phoneNumber,
                                    email
                            )
                    )
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getOneTree(
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            studyInfoRepository.get(
                    idStudyInfo,
                    usersRepository.get(
                            idUser,
                            contactInfoRepository.get(
                                    idContactInfo,
                                    phoneNumber,
                                    email
                            )
                    )
            )


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


    /**
     *
     *      ADD / INSERT INTO
     *      requests
     *
     */


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @PostMapping(value = ["post/add-ui"])
    fun addUI(@RequestBody studyInfo: StudyInfo?) =
            studyInfoRepository.add(studyInfo)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/add-rest"])
    fun addRest(@RequestBody studyInfo: StudyInfo?) =
            studyInfoRepository.add(studyInfo)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/add-tree"])
    fun addTree(@RequestBody studyInfo: StudyInfo?) =
            studyInfoRepository.add(studyInfo)


    /**
     *
     *      SET / UPDATE
     *      requests
     *
     */


    /** ============================== MY ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/set-my-ui"])
    fun setMyUI(
            @RequestBody newStudyInfo: StudyInfo?,
            @AuthenticationPrincipal authUser: UserDetails
    ): StudyInfo? {
        val studyInfo =
                studyInfoRepository.set(
                        newStudyInfo,
                        user = contactInfoRepository.get(
                                email = authUser.username
                        )?.user
                )
        return studyInfoRepository.get(studyInfo?.idStudyInfo)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-my-rest"])
    fun setMyRest(
            @RequestBody newStudyInfo: StudyInfo?,
            @AuthenticationPrincipal authUser: UserDetails
    ): StudyInfo? {
        val studyInfo =
                studyInfoRepository.set(
                        newStudyInfo,
                        user = contactInfoRepository.get(
                                email = authUser.username
                        )?.user
                )
        return studyInfoRepository.get(studyInfo?.idStudyInfo)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-my-tree"])
    fun setMyTree(
            @RequestBody newStudyInfo: StudyInfo?,
            @AuthenticationPrincipal authUser: UserDetails
    ): StudyInfo? {
        val studyInfo =
                studyInfoRepository.set(
                        newStudyInfo,
                        user = contactInfoRepository.get(
                                email = authUser.username
                        )?.user
                )
        return studyInfoRepository.get(studyInfo?.idStudyInfo)
    }


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/set-one-ui"])
    fun setUI(
            @RequestBody newStudyInfo: StudyInfo?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ): StudyInfo? {
        val studyInfo =
                studyInfoRepository.set(
                        newStudyInfo,
                        idStudyInfo,
                        usersRepository.get(
                                idUser,
                                contactInfoRepository.get(
                                        idContactInfo,
                                        phoneNumber,
                                        email
                                )
                        )
                )
        return studyInfoRepository.get(studyInfo?.idStudyInfo)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-one-rest"])
    fun setRest(
            @RequestBody newStudyInfo: StudyInfo?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ): StudyInfo? {
        val studyInfo =
                studyInfoRepository.set(
                        newStudyInfo,
                        idStudyInfo,
                        usersRepository.get(
                                idUser,
                                contactInfoRepository.get(
                                        idContactInfo,
                                        phoneNumber,
                                        email
                                )
                        )
                )
        return studyInfoRepository.get(studyInfo?.idStudyInfo)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-one-tree"])
    fun setTree(
            @RequestBody newStudyInfo: StudyInfo?,
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ): StudyInfo? {
        val studyInfo =
                studyInfoRepository.set(
                        newStudyInfo,
                        idStudyInfo,
                        usersRepository.get(
                                idUser,
                                contactInfoRepository.get(
                                        idContactInfo,
                                        phoneNumber,
                                        email
                                )
                        )
                )
        return studyInfoRepository.get(studyInfo?.idStudyInfo)
    }


    /**
     *
     *      DELETE
     *      requests
     *
     */


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/one-ui"])
    fun deleteUI(
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            studyInfoRepository.delete(
                    idStudyInfo,
                    usersRepository.get(
                            idUser,
                            contactInfoRepository.get(
                                    idContactInfo,
                                    phoneNumber,
                                    email
                            )
                    )
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/one-rest"])
    fun deleteRest(
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            studyInfoRepository.delete(
                    idStudyInfo,
                    usersRepository.get(
                            idUser,
                            contactInfoRepository.get(
                                    idContactInfo,
                                    phoneNumber,
                                    email
                            )
                    )
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/one-tree"])
    fun deleteTree(
            @RequestParam(
                    value = "id_study_info",
                    required = false) idStudyInfo: Int?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            studyInfoRepository.delete(
                    idStudyInfo,
                    usersRepository.get(
                            idUser,
                            contactInfoRepository.get(
                                    idContactInfo,
                                    phoneNumber,
                                    email
                            )
                    )
            )

}
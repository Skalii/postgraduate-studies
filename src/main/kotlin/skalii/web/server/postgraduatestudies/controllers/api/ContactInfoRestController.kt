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

import skalii.web.server.postgraduatestudies.entities.ContactInfo
import skalii.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skalii.web.server.postgraduatestudies.repositories.UsersRepository
import skalii.web.server.postgraduatestudies.views.Json


@RequestMapping(
        value = ["/api/contact-info"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class ContactInfoRestController(
        val contactInfoRepository: ContactInfoRepository,
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
                    contactInfoRepository.get(authUser.username)
            )

    @GetMapping(value = ["one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?
    ) =
            Json.get(
                    view,
                    contactInfoRepository.get(
                            email,
                            phoneNumber,
                            idUser,
                            idContactInfo
                    )
            )

    @GetMapping(value = ["all{-view}"])
    fun getAll(@PathVariable(value = "-view") view: String) =
            Json.get(
                    view,
                    contactInfoRepository.getAll()
            )


    /** ============================== POST requests ============================== */


    @PostMapping(value = ["one{-view}"])
    fun add(
            @PathVariable(value = "-view") view: String,
            @RequestBody contactInfo: ContactInfo
    ) =
            Json.get(
                    view,
                    contactInfoRepository.add(
                            contactInfo.phoneNumber,
                            contactInfo.email,
                            contactInfo.address
                    )
            )


    /** ============================== PUT requests ============================== */


    @PutMapping(value = ["my{-view}"])
    fun setMy(
            @PathVariable(value = "-view") view: String,
            @RequestBody newContactInfo: ContactInfo,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.get(
                    view,
                    contactInfoRepository.run {
                        flush()
                        set(
                                newContactInfo,
                                authUser.username
                        )
                        flush()
                        get(authUser.username)
                    }
            )

    @PutMapping(value = ["one{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody newContactInfo: ContactInfo,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) _idContactInfo: Int?
    ) =
            Json.get(view,
                    contactInfoRepository.run {
                        flush()
                        val foundId = set(
                                newContactInfo,
                                email,
                                phoneNumber,
                                idUser,
                                _idContactInfo
                        ).idContactInfo
                        flush()
                        get(idContactInfo = foundId)
                    }
            )


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["one{-view}"])
    fun delete(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?
    ) =
            Json.get(
                    view,
                    contactInfoRepository.delete(
                            email,
                            phoneNumber,
                            idContactInfo
                    )
            )

}
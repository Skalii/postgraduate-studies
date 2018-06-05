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

import skaliy.web.server.postgraduatestudies.entities.ContactInfo
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/contact-info"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class ContactInfoRestController(
        val contactInfoRepository: ContactInfoRepository,
        val usersRepository: UsersRepository
) {


    /**
     *
     *      GET / SELECT
     *      requests
     *
     */


    /** ============================== MY ============================== */


    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-ui"])
    fun getMyUI(@AuthenticationPrincipal authUser: UserDetails) =
            contactInfoRepository.get(email = authUser.username)

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-rest"])
    fun getMyRest(@AuthenticationPrincipal authUser: UserDetails) =
            contactInfoRepository.get(email = authUser.username)

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/my-tree"])
    fun getMyTree(@AuthenticationPrincipal authUser: UserDetails) =
            contactInfoRepository.get(email = authUser.username)


    /** ============================== ONE ============================== */


    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-ui"])
    fun getOneUI(
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?
    ) =
            contactInfoRepository.get(
                    idContactInfo,
                    phoneNumber,
                    email,
                    usersRepository.get(idUser)
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getOneRest(
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?
    ) =
            contactInfoRepository.get(
                    idContactInfo,
                    phoneNumber,
                    email,
                    usersRepository.get(idUser)
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getOneTree(
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?
    ) =
            contactInfoRepository.get(
                    idContactInfo,
                    phoneNumber,
                    email,
                    usersRepository.get(idUser)
            )


    /** ============================== ALL ============================== */


    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-ui"])
    fun getAllUI() = contactInfoRepository.getAll()

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest() = contactInfoRepository.getAll()

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree() = contactInfoRepository.getAll()


    /**
     *
     *      ADD / INSERT INTO
     *      requests
     *
     */


    /** ============================== ONE ============================== */


    @JsonView(View.REST::class)
    @PostMapping(value = ["post/add-ui"])
    fun addUI(@RequestBody contactInfo: ContactInfo) =
            contactInfoRepository.add(contactInfo)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/add-rest"])
    fun addRest(@RequestBody contactInfo: ContactInfo) =
            contactInfoRepository.add(contactInfo)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/add-tree"])
    fun addTree(@RequestBody contactInfo: ContactInfo) =
            contactInfoRepository.add(contactInfo)


    /**
     *
     *      SET / UPDATE
     *      requests
     *
     */


    /** ============================== MY ============================== */


    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-my-ui"])
    fun setMyUI(
            @RequestBody newContactInfo: ContactInfo?,
            @AuthenticationPrincipal authUser: UserDetails
    ): ContactInfo? {
        val contactInfo =
                contactInfoRepository.set(
                        newContactInfo,
                        email = authUser.username
                )
        return contactInfoRepository.get(contactInfo?.idContactInfo)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-my-rest"])
    fun setMyRest(
            @RequestBody newContactInfo: ContactInfo?,
            @AuthenticationPrincipal authUser: UserDetails
    ): ContactInfo? {
        val contactInfo =
                contactInfoRepository.set(
                        newContactInfo,
                        email = authUser.username
                )
        return contactInfoRepository.get(contactInfo?.idContactInfo)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-my-tree"])
    fun setMyTree(
            @RequestBody newContactInfo: ContactInfo?,
            @AuthenticationPrincipal authUser: UserDetails
    ): ContactInfo? {
        val contactInfo =
                contactInfoRepository.set(
                        newContactInfo,
                        email = authUser.username
                )
        return contactInfoRepository.get(contactInfo?.idContactInfo)
    }


    /** ============================== ONE ============================== */


    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-one-ui"])
    fun setUI(
            @RequestBody newContactInfo: ContactInfo?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?
    ): ContactInfo? {
        val contactInfo =
                contactInfoRepository.set(
                        newContactInfo,
                        idContactInfo,
                        phoneNumber,
                        email,
                        usersRepository.get(idUser)
                )
        return contactInfoRepository.get(contactInfo?.idContactInfo)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-one-rest"])
    fun setRest(
            @RequestBody newContactInfo: ContactInfo?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?
    ): ContactInfo? {
        val contactInfo =
                contactInfoRepository.set(
                        newContactInfo,
                        idContactInfo,
                        phoneNumber,
                        email,
                        usersRepository.get(idUser)
                )
        return contactInfoRepository.get(contactInfo?.idContactInfo)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-one-tree"])
    fun setTree(
            @RequestBody newContactInfo: ContactInfo?,
            @RequestParam(
                    value = "id_contact_info",
                    required = false) idContactInfo: Int?,
            @RequestParam(
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?,
            @RequestParam(
                    value = "id_user",
                    required = false) idUser: Int?
    ): ContactInfo? {
        val contactInfo =
                contactInfoRepository.set(
                        newContactInfo,
                        idContactInfo,
                        phoneNumber,
                        email,
                        usersRepository.get(idUser)
                )
        return contactInfoRepository.get(contactInfo?.idContactInfo)
    }


    /**
     *
     *      DELETE
     *      requests
     *
     */


    /** ============================== ONE ============================== */


    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/one-ui"])
    fun deleteUI(
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
            contactInfoRepository.delete(
                    idContactInfo,
                    phoneNumber,
                    email
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/one-rest"])
    fun deleteRest(
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
            contactInfoRepository.delete(
                    idContactInfo,
                    phoneNumber,
                    email
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/one-tree"])
    fun deleteTree(
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
            contactInfoRepository.delete(
                    idContactInfo,
                    phoneNumber,
                    email
            )

}
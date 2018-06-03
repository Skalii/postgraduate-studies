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

import skaliy.web.server.postgraduatestudies.entities.Degree
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.DegreesRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/degrees"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class DegreesRestController(
        val contactInfoRepository: ContactInfoRepository,
        val degreesRepository: DegreesRepository,
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
            degreesRepository.get(
                    contactInfoRepository.get(
                            email = authUser.username
                    )?.user
            )


    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-rest"])
    fun getMyRest(@AuthenticationPrincipal authUser: UserDetails) =
            degreesRepository.get(
                    contactInfoRepository.get(
                            email = authUser.username
                    )?.user
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/my-tree"])
    fun getMyTree(@AuthenticationPrincipal authUser: UserDetails) =
            degreesRepository.get(
                    contactInfoRepository.get(
                            email = authUser.username
                    )?.user
            )


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-ui"])
    fun getOneUI(
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.get(
                    idDegree,
                    name,
                    branch
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getOneRest(
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.get(
                    idDegree,
                    name,
                    branch
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getOneTree(
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.get(
                    idDegree,
                    name,
                    branch
            )


    /** ============================== ONE
     *                                 BY
     *                                 USER ============================== */


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
                    value = "phone_number",
                    required = false) phoneNumber: String?,
            @RequestParam(
                    value = "email",
                    required = false) email: String?
    ) =
            degreesRepository.get(
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
    @GetMapping(value = ["get/one-by-user-rest"])
    fun getOneByUserRest(
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
            degreesRepository.get(
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
    @GetMapping(value = ["get/one-by-user-tree"])
    fun getOneByUserTree(
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
            degreesRepository.get(
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
    fun getAllUI(
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.getAll(
                    allRecords,
                    name,
                    branch
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest(
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.getAll(
                    allRecords,
                    name,
                    branch
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-Tree"])
    fun getAllTree(
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.getAll(
                    allRecords,
                    name,
                    branch
            )


    /**
     *
     *      ADD / INSERT INTO
     *      requests
     *
     */


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @PostMapping(value = ["post/add-ui"])
    fun addUI(@RequestBody degree: Degree) =
            degreesRepository.add(degree)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/add-rest"])
    fun addRest(@RequestBody degree: Degree) =
            degreesRepository.add(degree)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/add-tree"])
    fun addTree(@RequestBody degree: Degree) =
            degreesRepository.add(degree)


    /**
     *
     *      SET / UPDATE
     *      requests
     */


    /** ============================== MY ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/set-my-ui"])
    fun setMyUI(
            @RequestBody newDegree: Degree?,
            @AuthenticationPrincipal authUser: UserDetails
    ): Degree? {
        val degree =
                degreesRepository.set(
                        newDegree,
                        contactInfoRepository.get(
                                email = authUser.username
                        )?.user?.degree?.idDegree
                )
        return degreesRepository.get(degree?.idDegree)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-my-rest"])
    fun setMyRest(
            @RequestBody newDegree: Degree?,
            @AuthenticationPrincipal authUser: UserDetails
    ): Degree? {
        val degree =
                degreesRepository.set(
                        newDegree,
                        contactInfoRepository.get(
                                email = authUser.username
                        )?.user?.degree?.idDegree
                )
        return degreesRepository.get(degree?.idDegree)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-my-tree"])
    fun setMyTree(
            @RequestBody newDegree: Degree?,
            @AuthenticationPrincipal authUser: UserDetails
    ): Degree? {
        val degree =
                degreesRepository.set(
                        newDegree,
                        contactInfoRepository.get(
                                email = authUser.username
                        )?.user?.degree?.idDegree
                )
        return degreesRepository.get(degree?.idDegree)
    }


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/set-one-ui"])
    fun setUI(
            @RequestBody newDegree: Degree?,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ): Degree? {
        val degree =
                degreesRepository.set(
                        newDegree,
                        idDegree,
                        name,
                        branch
                )
        return degreesRepository.get(degree?.idDegree)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-one-rest"])
    fun setRest(
            @RequestBody newDegree: Degree?,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ): Degree? {
        val degree =
                degreesRepository.set(
                        newDegree,
                        idDegree,
                        name,
                        branch
                )
        return degreesRepository.get(degree?.idDegree)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-one-tree"])
    fun setTree(
            @RequestBody newDegree: Degree?,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ): Degree? {
        val degree =
                degreesRepository.set(
                        newDegree,
                        idDegree,
                        name,
                        branch
                )
        return degreesRepository.get(degree?.idDegree)
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
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.delete(
                    idDegree,
                    name,
                    branch
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/one-rest"])
    fun deleteRest(
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.delete(
                    idDegree,
                    name,
                    branch
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/one-tree"])
    fun deleteTree(
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "branch",
                    required = false) branch: String?
    ) =
            degreesRepository.delete(
                    idDegree,
                    name,
                    branch
            )

}
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

import skaliy.web.server.postgraduatestudies.entities.Branch
import skaliy.web.server.postgraduatestudies.repositories.BranchesRepository
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.SpecialitiesRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/branches"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class BranchesRestController(
        val branchesRepository: BranchesRepository,
        val contactInfoRepository: ContactInfoRepository,
        val specialitiesRepository: SpecialitiesRepository,
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
            branchesRepository.get(
                    contactInfoRepository.get(
                            email = authUser.username
                    )?.user?.speciality?.branch?.idBranch
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-rest"])
    fun getMyRest(@AuthenticationPrincipal authUser: UserDetails) =
            branchesRepository.get(
                    contactInfoRepository.get(
                            email = authUser.username
                    )?.user?.speciality?.branch?.idBranch
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/my-tree"])
    fun getMyTree(@AuthenticationPrincipal authUser: UserDetails) =
            branchesRepository.get(
                    contactInfoRepository.get(
                            email = authUser.username
                    )?.user?.speciality?.branch?.idBranch
            )


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-ui"])
    fun getOneUI(
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            branchesRepository.get(
                    idBranch,
                    number,
                    name
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getOneRest(
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            branchesRepository.get(
                    idBranch,
                    number,
                    name
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getOneTree(
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            branchesRepository.get(
                    idBranch,
                    number,
                    name
            )


    /** ============================== ONE
     *                                 BY
     *                                 SPECIALITY ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-by-speciality-ui"])
    fun getOneBySpecialityUI(
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            branchesRepository.getBySpeciality(
                    specialitiesRepository.get(
                            idSpeciality,
                            number,
                            name
                    ))


    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-by-speciality-rest"])
    fun getOneBySpecialityRest(
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            branchesRepository.getBySpeciality(
                    specialitiesRepository.get(
                            idSpeciality,
                            number,
                            name
                    ))

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-by-speciality-tree"])
    fun getOneBySpecialityTree(
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            branchesRepository.getBySpeciality(
                    specialitiesRepository.get(
                            idSpeciality,
                            number,
                            name
                    ))

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
            branchesRepository.getByUser(
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
            branchesRepository.getByUser(
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
            branchesRepository.getByUser(
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
    fun getAllUI() = branchesRepository.getAll()

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest() = branchesRepository.getAll()

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree() = branchesRepository.getAll()


    /**
     *
     *      ADD / INSERT INTO
     *      requests
     *
     */


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @PostMapping(value = ["post/add-ui"])
    fun addUI(@RequestBody branch: Branch?) =
            branchesRepository.add(branch)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/add-rest"])
    fun addRest(@RequestBody branch: Branch?) =
            branchesRepository.add(branch)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/add-tree"])
    fun addTree(@RequestBody branch: Branch?) =
            branchesRepository.add(branch)


    /**
     *
     *      SET / UPDATE
     *      requests
     *
     */


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/set-one-ui"])
    fun setUI(
            @RequestBody newBranch: Branch?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ): Branch? {
        val branch =
                branchesRepository.set(
                        newBranch,
                        idBranch,
                        number,
                        name
                )
        return branchesRepository.get(branch?.idBranch)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-one-rest"])
    fun setRest(
            @RequestBody newBranch: Branch?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ): Branch? {
        val branch =
                branchesRepository.set(
                        newBranch,
                        idBranch,
                        number,
                        name
                )
        return branchesRepository.get(branch?.idBranch)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-one-tree"])
    fun setTree(
            @RequestBody newBranch: Branch?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ): Branch? {
        val branch =
                branchesRepository.set(
                        newBranch,
                        idBranch,
                        number,
                        name
                )
        return branchesRepository.get(branch?.idBranch)
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
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            branchesRepository.delete(
                    idBranch,
                    number,
                    name
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/one-rest"])
    fun deleteRest(
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            branchesRepository.delete(
                    idBranch,
                    number,
                    name
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/one-tree"])
    fun deleteTree(
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            branchesRepository.delete(
                    idBranch,
                    number,
                    name
            )

    /** ============================== DELETE
     *                                 BY
     *                                 SPECIALITY ============================== */


    @JsonView(View.UI::class)
    @DeleteMapping(value = ["delete/one-by-speciality-ui"])
    fun deleteBySpecialityUI(
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            branchesRepository.deleteBySpeciality(
                    specialitiesRepository.get(
                            idSpeciality,
                            number,
                            name
                    )
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/one-by-speciality-rest"])
    fun deleteBySpecialityRest(
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            branchesRepository.deleteBySpeciality(
                    specialitiesRepository.get(
                            idSpeciality,
                            number,
                            name
                    )
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/one-by-speciality-tree"])
    fun deleteBySpecialityTree(
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            branchesRepository.deleteBySpeciality(
                    specialitiesRepository.get(
                            idSpeciality,
                            number,
                            name
                    )
            )

}
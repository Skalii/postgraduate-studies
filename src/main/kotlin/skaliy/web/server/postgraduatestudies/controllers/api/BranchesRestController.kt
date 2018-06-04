package skaliy.web.server.postgraduatestudies.controllers.api


import com.fasterxml.jackson.annotation.JsonView
import com.fasterxml.jackson.databind.MapperFeature

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

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


    @GetMapping(value = ["get/my-{view}"])
    fun getMy(
            @PathVariable("view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            ObjectMapper()
                    //                .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                    .writerWithView(
                            when (view) {
                                "rest" -> View.REST::class.java
                                "tree" -> View.TREE::class.java
                                else -> View.UI::class.java
                            }
                    )
                    .writeValueAsString(
                            branchesRepository.get(
                                    contactInfoRepository.get(
                                            email = authUser.username
                                    )?.user?.speciality?.branch?.idBranch
                            )
                    )!!


    /** ============================== ONE ============================== */


    @GetMapping(value = ["get/one-{view}"])
    fun getOne(
            @PathVariable("view") view: String,
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
            ObjectMapper()
                    .writerWithView(
                            when (view) {
                                "rest" -> View.REST::class.java
                                "tree" -> View.TREE::class.java
                                else -> View.UI::class.java
                            }
                    )
                    .writeValueAsString(
                            branchesRepository.get(
                                    idBranch,
                                    number,
                                    name
                            )
                    )!!


    /** ============================== ONE
     *                                 BY
     *                                 SPECIALITY ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-by-speciality-{view}"])
    fun getOneBySpecialityUI(
            @PathVariable("view") view: String,
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
            ObjectMapper()
                    .writerWithView(
                            when (view) {
                                "rest" -> View.REST::class.java
                                "tree" -> View.TREE::class.java
                                else -> View.UI::class.java
                            }
                    )
                    .writeValueAsString(
                            branchesRepository.getBySpeciality(
                                    specialitiesRepository.get(
                                            idSpeciality,
                                            number,
                                            name
                                    )
                            )
                    )!!


    /** ============================== ONE
     *                                 BY
     *                                 USER ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-by-user-{view}"])
    fun getOneByUserUI(
            @PathVariable("view") view: String,
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
            ObjectMapper()
                    .writerWithView(
                            when (view) {
                                "rest" -> View.REST::class.java
                                "tree" -> View.TREE::class.java
                                else -> View.UI::class.java
                            }
                    )
                    .writeValueAsString(
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

                    )!!


    /** ============================== ALL ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/all-{view}"])
    fun getAllUI(@PathVariable("view") view: String) =
            ObjectMapper()
                    .writerWithView(
                            when (view) {
                                "rest" -> View.REST::class.java
                                "tree" -> View.TREE::class.java
                                else -> View.UI::class.java
                            }
                    )
                    .writeValueAsString(branchesRepository.getAll())!!


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
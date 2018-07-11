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

import skalii.web.server.postgraduatestudies.entities.Branch
import skalii.web.server.postgraduatestudies.repositories.BranchesRepository
import skalii.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skalii.web.server.postgraduatestudies.repositories.SpecialitiesRepository
import skalii.web.server.postgraduatestudies.repositories.UsersRepository
import skalii.web.server.postgraduatestudies.views.Json


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


    /** ============================== GET requests ============================== */


    @GetMapping(value = ["my{-view}"])
    fun getMy(
            @PathVariable(value = "-view") view: String,
            @AuthenticationPrincipal authUser: UserDetails
    ) =
            Json.get(
                    view,
                    branchesRepository.get(
                            usersRepository.get(
                                    authUser.username
                            )
                    )
            )

    @GetMapping(value = ["one{-view}"])
    fun getOne(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?
    ) =
            Json.get(
                    view,
                    branchesRepository.get(
                            number,
                            name,
                            idBranch
                    )
            )

    @GetMapping(value = ["one-by-speciality{-view}"])
    fun getOneBySpeciality(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "speciality_number",
                    required = false) specialityNumber: String?,
            @RequestParam(
                    value = "speciality_name",
                    required = false) specialityName: String?,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?
    ) =
            Json.get(
                    view,
                    branchesRepository.get(
                            specialitiesRepository.get(
                                    specialityNumber,
                                    specialityName,
                                    idSpeciality
                            )
                    )
            )

    @GetMapping(value = ["one-by-user{-view}"])
    fun getOneByUser(
            @PathVariable(value = "-view") view: String,
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
                    branchesRepository.get(
                            usersRepository.get(
                                    email,
                                    phoneNumber,
                                    idUser
                            )
                    )
            )

    @GetMapping(value = ["all{-view}"])
    fun getAll(@PathVariable(value = "-view") view: String) =
            Json.get(
                    view,
                    branchesRepository.getAll()
            )


    /** ============================== POST requests ============================== */


    @PostMapping(value = ["one{-view}"])
    fun add(
            @PathVariable(value = "-view") view: String,
            @RequestBody branch: Branch
    ) =
            Json.get(
                    view,
                    branchesRepository.add(
                            branch.number,
                            branch.name
                    )
            )


    /** ============================== PUT requests ============================== */


    @PutMapping(value = ["one{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody newBranch: Branch,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "id_branch",
                    required = false) _idBranch: Int?
    ) =
            Json.get(
                    view,
                    branchesRepository.run {
                        flush()
                        val foundId = set(
                                newBranch,
                                number,
                                name,
                                _idBranch
                        ).idBranch
                        flush()
                        get(idBranch = foundId)
                    }
            )


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["one{-view}"])
    fun delete(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?
    ) =
            Json.get(
                    view,
                    branchesRepository.delete(
                            number,
                            name,
                            idBranch
                    )
            )

    @DeleteMapping(value = ["one-by-speciality{-view}"])
    fun deleteBySpeciality(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "speciality_number",
                    required = false) specialityNumber: String?,
            @RequestParam(
                    value = "speciality_name",
                    required = false) specialityName: String?,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?
    ) =
            Json.get(
                    view,
                    branchesRepository.delete(
                            specialitiesRepository.get(
                                    specialityNumber,
                                    specialityName,
                                    idSpeciality
                            )
                    )
            )

}
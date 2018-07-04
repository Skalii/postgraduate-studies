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

import skalii.web.server.postgraduatestudies.entities.Speciality
import skalii.web.server.postgraduatestudies.repositories.BranchesRepository
import skalii.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skalii.web.server.postgraduatestudies.repositories.SpecialitiesRepository
import skalii.web.server.postgraduatestudies.repositories.UsersRepository
import skalii.web.server.postgraduatestudies.views.Json


@RequestMapping(
        value = ["/api/specialities"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class SpecialitiesRestController(
        val contactInfoRepository: ContactInfoRepository,
        val specialitiesRepository: SpecialitiesRepository,
        val branchesRepository: BranchesRepository,
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
                    specialitiesRepository.get(
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
                    value = "id_speciality",
                    required = false) idSpeciality: Int?
    ) =
            Json.get(
                    view,
                    specialitiesRepository.get(
                            number,
                            name,
                            idSpeciality
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
                    specialitiesRepository.get(
                            usersRepository.get(
                                    email,
                                    phoneNumber,
                                    idUser
                            )
                    )
            )

    @GetMapping(value = ["all{-view}"])
    fun getAll(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?,
            @RequestParam(
                    value = "branch_number",
                    required = false) branchNumber: String?,
            @RequestParam(
                    value = "branch_name",
                    required = false) branchName: String?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?
    ) =
            Json.get(
                    view,
                    specialitiesRepository.getAll(
                            allRecords,
                            idBranch ?: branchesRepository.get(
                                    branchNumber,
                                    branchName
                            ).idBranch
                    )
            )


    /** ============================== POST requests ============================== */


    @PostMapping(value = ["one{-view}"])
    fun add(
            @PathVariable(value = "-view") view: String,
            @RequestBody speciality: Speciality,
            @RequestParam(
                    value = "branch_number",
                    required = false) branchNumber: String?,
            @RequestParam(
                    value = "branch_name",
                    required = false) branchName: String?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?
    ) =
            Json.get(
                    view,
                    specialitiesRepository.add(
                            speciality.number,
                            speciality.name,
                            idBranch ?: branchesRepository.get(
                                    branchNumber,
                                    branchName
                            ).idBranch
                    )
            )


    /** ============================== PUT requests ============================== */


    @PutMapping(value = ["one{-view}"])
    fun set(
            @PathVariable(value = "-view") view: String,
            @RequestBody newSpeciality: Speciality,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "id_speciality",
                    required = false) _idSpeciality: Int?
    ) =
            specialitiesRepository.get(
                    number,
                    name,
                    _idSpeciality
            ).run {

                try {
                    newSpeciality.branch
                } catch (e: Exception) {
                    newSpeciality.branch = branch
                }

                specialitiesRepository.set(
                        newSpeciality,
                        idSpeciality
                )

                return@run Json.get(
                        view,
                        Speciality(
                                idSpeciality,
                                newSpeciality.number,
                                newSpeciality.name,
                                newSpeciality.branch
                        )
                )
            }


    /** ============================== DELETE requests ============================== */


    @DeleteMapping(value = ["one{-view}"])
    fun deleteUI(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "number",
                    required = false) number: String?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?
    ) =
            Json.get(
                    view,
                    specialitiesRepository.delete(
                            number,
                            name,
                            idSpeciality
                    )
            )

    @DeleteMapping(value = ["all-by-branch{-view}"])
    fun deleteAllByBranchUI(
            @PathVariable(value = "-view") view: String,
            @RequestParam(
                    value = "branch_number",
                    required = false) number: String?,
            @RequestParam(
                    value = "branch_name",
                    required = false) name: String?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?
    ) =
            Json.get(
                    view,
                    specialitiesRepository.deleteAll(
                            branchesRepository.get(
                                    number,
                                    name,
                                    idBranch
                            )
                    )
            )

}
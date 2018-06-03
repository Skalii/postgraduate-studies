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

import skaliy.web.server.postgraduatestudies.entities.Institute
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.DepartmentsRepository
import skaliy.web.server.postgraduatestudies.repositories.InstitutesRepository
import skaliy.web.server.postgraduatestudies.repositories.FacultiesRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/institutes"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class InstitutesRestController(
        val contactInfoRepository: ContactInfoRepository,
        val departmentsRepository: DepartmentsRepository,
        val institutesRepository: InstitutesRepository,
        val facultiesRepository: FacultiesRepository,
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
    fun getOneUI(@AuthenticationPrincipal authUser: UserDetails) =
            institutesRepository.get(
                    contactInfoRepository.get(
                            email = authUser.username
                    )?.user
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-rest"])
    fun getOneRest(@AuthenticationPrincipal authUser: UserDetails) =
            institutesRepository.get(
                    contactInfoRepository.get(
                            email = authUser.username
                    )?.user
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/my-tree"])
    fun getOneTree(@AuthenticationPrincipal authUser: UserDetails) =
            institutesRepository.get(
                    contactInfoRepository.get(
                            email = authUser.username
                    )?.user
            )



    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-ui"])
    fun getOneUI(
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            institutesRepository.get(
                    idInstitute,
                    name
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getOneRest(
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            institutesRepository.get(
                    idInstitute,
                    name
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getOneTree(
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            institutesRepository.get(
                    idInstitute,
                    name
            )


    /** ============================== ONE
     *                                 BY
     *                                 FACULTY ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-by-faculty-ui"])
    fun getOneBuFacultyUI(
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            institutesRepository.get(
                    facultiesRepository.get(
                            idFaculty,
                            name
                    )
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-by-faculty-rest"])
    fun getOneBuFacultyRest(
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            institutesRepository.get(
                    facultiesRepository.get(
                            idFaculty,
                            name
                    )
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-by-faculty-tree"])
    fun getOneBuFacultyTree(
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            institutesRepository.get(
                    facultiesRepository.get(
                            idFaculty,
                            name
                    )
            )


    /** ============================== ONE
     *                                 BY
     *                                 DEPARTMENT ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-by-department-ui"])
    fun getOneByUserUI(
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?
    ) =
            institutesRepository.get(
                    departmentsRepository.get(
                            idDepartment,
                            name
                    )
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-by-department-rest"])
    fun getOneByUserRest(
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?
    ) =
            institutesRepository.get(
                    departmentsRepository.get(
                            idDepartment,
                            name
                    )
            )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-by-department-tree"])
    fun getOneByUserTree(
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?
    ) =
            institutesRepository.get(
                    departmentsRepository.get(
                            idDepartment,
                            name
                    )
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
            institutesRepository.get(
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
            institutesRepository.get(
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
            institutesRepository.get(
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
    fun getAllUI() = institutesRepository.getAll()

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest() = institutesRepository.getAll()

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree() = institutesRepository.getAll()


    /**
     *
     *      ADD / INSERT INTO
     *      requests
     *
     */


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @PostMapping(value = ["post/add-ui"])
    fun addUI(@RequestBody institute: Institute?) =
            institutesRepository.add(institute)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/add-rest"])
    fun addRest(@RequestBody institute: Institute?) =
            institutesRepository.add(institute)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/add-tree"])
    fun addTree(@RequestBody institute: Institute?) =
            institutesRepository.add(institute)


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
            @RequestBody newInstitute: Institute?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ): Institute? {
        val institute =
                institutesRepository.set(
                        newInstitute,
                        idInstitute,
                        name
                )
        return institutesRepository.get(institute?.idInstitute)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-one-rest"])
    fun setRest(
            @RequestBody newInstitute: Institute?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ): Institute? {
        val institute =
                institutesRepository.set(
                        newInstitute,
                        idInstitute,
                        name
                )
        return institutesRepository.get(institute?.idInstitute)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-one-tree"])
    fun setTree(
            @RequestBody newInstitute: Institute?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ): Institute? {
        val institute =
                institutesRepository.set(
                        newInstitute,
                        idInstitute,
                        name
                )
        return institutesRepository.get(institute?.idInstitute)
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
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            institutesRepository.delete(
                    idInstitute,
                    name
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/one-rest"])
    fun deleteRest(
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            institutesRepository.delete(
                    idInstitute,
                    name
            )

    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/one-tree"])
    fun deleteTree(
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "name",
                    required = false) name: String?
    ) =
            institutesRepository.delete(
                    idInstitute,
                    name
            )

}
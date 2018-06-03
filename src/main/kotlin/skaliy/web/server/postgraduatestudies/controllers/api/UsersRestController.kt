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

import skaliy.web.server.postgraduatestudies.entities.User
import skaliy.web.server.postgraduatestudies.repositories.BranchesRepository
import skaliy.web.server.postgraduatestudies.repositories.ContactInfoRepository
import skaliy.web.server.postgraduatestudies.repositories.DegreesRepository
import skaliy.web.server.postgraduatestudies.repositories.DepartmentsRepository
import skaliy.web.server.postgraduatestudies.repositories.FacultiesRepository
import skaliy.web.server.postgraduatestudies.repositories.InstitutesRepository
import skaliy.web.server.postgraduatestudies.repositories.SpecialitiesRepository
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository
import skaliy.web.server.postgraduatestudies.views.View


@RequestMapping(
        value = ["/api/users"],
        produces = [APPLICATION_JSON_UTF8_VALUE])
@RestController
class UsersRestController(
        val usersRepository: UsersRepository,
        val branchesRepository: BranchesRepository,
        val contactInfoRepository: ContactInfoRepository,
        val degreesRepository: DegreesRepository,
        val departmentsRepository: DepartmentsRepository,
        var facultiesRepository: FacultiesRepository,
        var institutesRepository: InstitutesRepository,
        var specialitiesRepository: SpecialitiesRepository
) {


    /**
     *
     *      GET / SELECT
     *      requests
     *
     */


    /** ============================== ME ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/me-ui"])
    fun getMeUI(@AuthenticationPrincipal authUser: UserDetails) =
            contactInfoRepository.get(
                    email = authUser.username
            )?.user

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/me-rest"])
    fun getMeRest(@AuthenticationPrincipal authUser: UserDetails) =
            contactInfoRepository.get(
                    email = authUser.username
            )?.user

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/me-tree"])
    fun getMeTree(@AuthenticationPrincipal authUser: UserDetails) =
            contactInfoRepository.get(
                    email = authUser.username
            )?.user


    /** ============================== MY INSTRUCTOR ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/my-instructor-ui"])
    fun getMyInstructorUI(@AuthenticationPrincipal authUser: UserDetails) =
            contactInfoRepository.get(
                    email = authUser.username
            )?.user?.studyInfo?.instructor

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-instructor-rest"])
    fun getMyInstructorRest(@AuthenticationPrincipal authUser: UserDetails) =
            contactInfoRepository.get(
                    email = authUser.username
            )?.user?.studyInfo?.instructor

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/my-instructor-tree"])
    fun getMyInstructorTree(@AuthenticationPrincipal authUser: UserDetails) =
            contactInfoRepository.get(
                    email = authUser.username
            )?.user?.studyInfo?.instructor


    /** ============================== MY STUDENTS ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/my-students-ui"])
    fun getMyStudentsUI(@AuthenticationPrincipal authUser: UserDetails): MutableList<User>? {

        val myStudents: MutableList<User> = mutableListOf()

        contactInfoRepository.get(
                email = authUser.username
        )?.user?.students?.forEach { myStudents.add(it.user!!) }

        return myStudents
    }

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/my-students-rest"])
    fun getMyStudentsRest(@AuthenticationPrincipal authUser: UserDetails): MutableList<User>? {

        val myStudents: MutableList<User> = mutableListOf()

        contactInfoRepository.get(
                email = authUser.username
        )?.user?.students?.forEach { myStudents.add(it.user!!) }

        return myStudents
    }

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/my-students-tree"])
    fun getMyStudentsTree(@AuthenticationPrincipal authUser: UserDetails): MutableList<User>? {

        val myStudents: MutableList<User> = mutableListOf()

        contactInfoRepository.get(
                email = authUser.username
        )?.user?.students?.forEach { myStudents.add(it.user!!) }

        return myStudents
    }


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @GetMapping(value = ["get/one-ui"])
    fun getUI(
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
            usersRepository.get(
                    idUser,
                    contactInfoRepository.get(
                            idContactInfo,
                            phoneNumber,
                            email
                    )
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/one-rest"])
    fun getRest(
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
            usersRepository.get(
                    idUser,
                    contactInfoRepository.get(
                            idContactInfo,
                            phoneNumber,
                            email
                    )
            )


    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/one-tree"])
    fun getTree(
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
            usersRepository.get(
                    idUser,
                    contactInfoRepository.get(
                            idContactInfo,
                            phoneNumber,
                            email
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
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "degree_name",
                    required = false) degreeName: String?,
            @RequestParam(
                    value = "degree_branch",
                    required = false) degreeBranch: String?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "branch_number",
                    required = false) branchNumber: String?,
            @RequestParam(
                    value = "branch_name",
                    required = false) branchName: String?,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "speciality_number",
                    required = false) specialityNumber: String?,
            @RequestParam(
                    value = "speciality_name",
                    required = false) specialityName: String?,
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "department_name",
                    required = false) departmentName: String?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "faculty_name",
                    required = false) facultyName: String?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "institute_name",
                    required = false) instituteName: String?
    ) =
            usersRepository.getAll(
                    allRecords,
                    degreesRepository.get(
                            idDegree,
                            degreeName,
                            degreeBranch
                    ),
                    branchesRepository.get(
                            idBranch,
                            branchNumber,
                            branchName
                    ),
                    specialitiesRepository.get(
                            idSpeciality,
                            specialityNumber,
                            specialityName
                    ),
                    departmentsRepository.get(
                            idDepartment,
                            departmentName
                    ),
                    facultiesRepository.get(
                            idFaculty,
                            facultyName
                    ),
                    institutesRepository.get(
                            idInstitute,
                            instituteName
                    )
            )

    @JsonView(View.REST::class)
    @GetMapping(value = ["get/all-rest"])
    fun getAllRest(
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "degree_name",
                    required = false) degreeName: String?,
            @RequestParam(
                    value = "degree_branch",
                    required = false) degreeBranch: String?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "branch_number",
                    required = false) branchNumber: String?,
            @RequestParam(
                    value = "branch_name",
                    required = false) branchName: String?,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "speciality_number",
                    required = false) specialityNumber: String?,
            @RequestParam(
                    value = "speciality_name",
                    required = false) specialityName: String?,
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "department_name",
                    required = false) departmentName: String?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "faculty_name",
                    required = false) facultyName: String?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "institute_name",
                    required = false) instituteName: String?
    ) = usersRepository.getAll(
            allRecords,
            degreesRepository.get(
                    idDegree,
                    degreeName,
                    degreeBranch
            ),
            branchesRepository.get(
                    idBranch,
                    branchNumber,
                    branchName
            ),
            specialitiesRepository.get(
                    idSpeciality,
                    specialityNumber,
                    specialityName
            ),
            departmentsRepository.get(
                    idDepartment,
                    departmentName
            ),
            facultiesRepository.get(
                    idFaculty,
                    facultyName
            ),
            institutesRepository.get(
                    idInstitute,
                    instituteName
            )
    )

    @JsonView(View.TREE::class)
    @GetMapping(value = ["get/all-tree"])
    fun getAllTree(
            @RequestParam(
                    value = "all_records",
                    required = false) allRecords: Boolean?,
            @RequestParam(
                    value = "id_degree",
                    required = false) idDegree: Int?,
            @RequestParam(
                    value = "degree_name",
                    required = false) degreeName: String?,
            @RequestParam(
                    value = "degree_branch",
                    required = false) degreeBranch: String?,
            @RequestParam(
                    value = "id_branch",
                    required = false) idBranch: Int?,
            @RequestParam(
                    value = "branch_number",
                    required = false) branchNumber: String?,
            @RequestParam(
                    value = "branch_name",
                    required = false) branchName: String?,
            @RequestParam(
                    value = "id_speciality",
                    required = false) idSpeciality: Int?,
            @RequestParam(
                    value = "speciality_number",
                    required = false) specialityNumber: String?,
            @RequestParam(
                    value = "speciality_name",
                    required = false) specialityName: String?,
            @RequestParam(
                    value = "id_department",
                    required = false) idDepartment: Int?,
            @RequestParam(
                    value = "department_name",
                    required = false) departmentName: String?,
            @RequestParam(
                    value = "id_faculty",
                    required = false) idFaculty: Int?,
            @RequestParam(
                    value = "faculty_name",
                    required = false) facultyName: String?,
            @RequestParam(
                    value = "id_institute",
                    required = false) idInstitute: Int?,
            @RequestParam(
                    value = "institute_name",
                    required = false) instituteName: String?
    ) =
            usersRepository.getAll(
                    allRecords,
                    degreesRepository.get(
                            idDegree,
                            degreeName,
                            degreeBranch
                    ),
                    branchesRepository.get(
                            idBranch,
                            branchNumber,
                            branchName
                    ),
                    specialitiesRepository.get(
                            idSpeciality,
                            specialityNumber,
                            specialityName
                    ),
                    departmentsRepository.get(
                            idDepartment,
                            departmentName
                    ),
                    facultiesRepository.get(
                            idFaculty,
                            facultyName
                    ),
                    institutesRepository.get(
                            idInstitute,
                            instituteName
                    )
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
    fun addUI(@RequestBody user: User?) =
            usersRepository.add(user)

    @JsonView(View.REST::class)
    @PostMapping(value = ["post/add-rest"])
    fun addRest(@RequestBody user: User?) =
            usersRepository.add(user)

    @JsonView(View.TREE::class)
    @PostMapping(value = ["post/add-tree"])
    fun addTree(@RequestBody user: User?) =
            usersRepository.add(user)


    /**
     *
     *      SET / UPDATE
     *      requests
     *
     */


    /** ============================== ME ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/set-me-ui"])
    fun updateMeUI(
            @RequestBody newUser: User?,
            @AuthenticationPrincipal authUser: UserDetails
    ): User? {
        val user =
                usersRepository.set(
                        newUser,
                        contactInfoRepository.get(
                                email = authUser.username
                        )?.user?.idUser
                )
        return usersRepository.get(user?.idUser)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-me-rest"])
    fun updateMeRest(
            @RequestBody newUser: User?,
            @AuthenticationPrincipal authUser: UserDetails
    ): User? {
        val user =
                usersRepository.set(
                        newUser,
                        contactInfoRepository.get(
                                email = authUser.username
                        )?.user?.idUser
                )
        return usersRepository.get(user?.idUser)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-me-tree"])
    fun updateMeTree(
            @RequestBody newUser: User?,
            @AuthenticationPrincipal authUser: UserDetails
    ): User? {
        val user =
                usersRepository.set(
                        newUser,
                        contactInfoRepository.get(
                                email = authUser.username
                        )?.user?.idUser
                )
        return usersRepository.get(user?.idUser)
    }


    /** ============================== ONE ============================== */


    @JsonView(View.UI::class)
    @PutMapping(value = ["put/set-one-ui"])
    fun updateUI(
            @RequestBody newUser: User?,
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
    ): User? {
        val user =
                usersRepository.set(
                        newUser,
                        idUser,
                        contactInfoRepository.get(
                                idContactInfo,
                                phoneNumber,
                                email
                        )
                )
        return usersRepository.get(user?.idUser)
    }

    @JsonView(View.REST::class)
    @PutMapping(value = ["put/set-one-rest"])
    fun updateRest(
            @RequestBody newUser: User?,
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
    ): User? {
        val user =
                usersRepository.set(
                        newUser,
                        idUser,
                        contactInfoRepository.get(
                                idContactInfo,
                                phoneNumber,
                                email
                        )
                )
        return usersRepository.get(user?.idUser)
    }

    @JsonView(View.TREE::class)
    @PutMapping(value = ["put/set-one-tree"])
    fun updateTree(
            @RequestBody newUser: User?,
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
    ): User? {
        val user =
                usersRepository.set(
                        newUser,
                        idUser,
                        contactInfoRepository.get(
                                idContactInfo,
                                phoneNumber,
                                email
                        )
                )
        return usersRepository.get(user?.idUser)
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
            usersRepository.delete(
                    idUser,
                    contactInfoRepository.get(
                            idContactInfo,
                            phoneNumber,
                            email
                    )
            )

    @JsonView(View.REST::class)
    @DeleteMapping(value = ["delete/one-rest"])
    fun deleteRest(
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
            usersRepository.delete(
                    idUser,
                    contactInfoRepository.get(
                            idContactInfo,
                            phoneNumber,
                            email
                    )
            )


    @JsonView(View.TREE::class)
    @DeleteMapping(value = ["delete/one-tree"])
    fun deleteTree(
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
            usersRepository.delete(
                    idUser,
                    contactInfoRepository.get(
                            idContactInfo,
                            phoneNumber,
                            email
                    )
            )

}
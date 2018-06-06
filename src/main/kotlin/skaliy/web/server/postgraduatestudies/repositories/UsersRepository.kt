package skaliy.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skaliy.web.server.postgraduatestudies.entities.Branch
import skaliy.web.server.postgraduatestudies.entities.ContactInfo
import skaliy.web.server.postgraduatestudies.entities.Degree
import skaliy.web.server.postgraduatestudies.entities.Department
import skaliy.web.server.postgraduatestudies.entities.Faculty
import skaliy.web.server.postgraduatestudies.entities.Institute
import skaliy.web.server.postgraduatestudies.entities.Section
import skaliy.web.server.postgraduatestudies.entities.Speciality
import skaliy.web.server.postgraduatestudies.entities.Task
import skaliy.web.server.postgraduatestudies.entities.User


@Repository
interface UsersRepository : JpaRepository<User, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (user_record(
                          cast_int(:id_user),
                          cast_int(:#{#contact_info.idContactInfo})
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("id_user") idUser: Int? = null,
            @Param("contact_info") contactInfo: ContactInfo? = ContactInfo()
    ): User = User()

    //language=PostgresPLSQL
    @Query(value = """select (user_record(
                          _id_section => cast_int(:#{#section.idSection}),
                          _id_task => cast_int(:#{#task.idTask})
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("section") section: Section? = Section(),
            @Param("task") task: Task? = Task()
    ): User = User()

    //language=PostgresPLSQL
    @Query(value = """select (user_record(
                          _id_degree => cast_int(:#{#degree.idDegree}),
                          _id_branch => cast_int(:#{#branch.idBranch}),
                          _id_speciality => cast_int(:#{#speciality.idSpeciality}),
                          _id_department => cast_int(:#{#department.idDepartment}),
                          _id_faculty => cast_int(:#{#faculty.idFaculty}),
                          _id_institute => cast_int(:#{#institute.idInstitute}),
                          all_records => cast_bool(:all_records)
                      )).*""",
            nativeQuery = true)
    fun getAll(
            @Param("all_records") allRecords: Boolean? = false,
            @Param("degree") degree: Degree? = Degree(),
            @Param("branch") branch: Branch? = Branch(),
            @Param("speciality") speciality: Speciality? = Speciality(),
            @Param("department") department: Department? = Department(),
            @Param("faculty") faculty: Faculty? = Faculty(),
            @Param("institute") institute: Institute? = Institute()
    ): MutableList<User> = mutableListOf(User())


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (user_insert(
                          cast_role(:role),
                          cast_text(:hash),
                          cast_text(:full_name_ua),
                          cast_text(:full_name_en),
                          :birthday,
                          cast_family(:family_status),
                          cast_int(:children),
                          cast_rank(:academic_rank),
                          cast_int(:id_degree),
                          cast_int(:id_speciality),
                          cast_int(:id_department),
                          cast_int(:id_contact_info),
                          cast_int(:id_study_info),
                          cast_int(:id_scientific_links)
                      )).*""",
            nativeQuery = true)
    fun add(
            @Param("role") role: String,
            @Param("hash") hash: String,
            @Param("full_name_ua") fullNameUa: String,
            @Param("full_name_en") fullNameEn: String,
            @Param("birthday") birthday: String,
            @Param("family_status") familyStatus: String?,
            @Param("children") children: Int?,
            @Param("academic_rank") academicRank: String?,
            @Param("id_degree") idDegree: Int?,
            @Param("id_speciality") idSpeciality: Int,
            @Param("id_department") idDepartment: Int,
            @Param("id_contact_info") idContactInfo: Int,
            @Param("id_study_info") idStudyInfo: Int?,
            @Param("id_scientific_links") idScientificLinks: Int
    ): User = User()


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (user_update(
                          cast_role(:#{#user.role.value}),
                          cast_text(:#{#user.hash}),
                          cast_text(:#{#user.fullNameUa}),
                          cast_text(:#{#user.fullNameEn}),
                          :#{#user.birthday},
                          cast_family(:#{#user.familyStatus.value}),
                          cast_int(:#{#user.children}),
                          cast_rank(:#{#user.academicRank.value}),
                          cast_int(:#{#user.degree.idDegree}),
                          cast_int(:#{#user.speciality.idSpeciality}),
                          cast_int(:#{#user.department.idDepartment}),
                          cast_int(:id_user)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("user") newUser: User,
            @Param("id_user") idUser: Int
    ): User = User()

    //language=PostgresPLSQL
    @Query(value = """select (user_update(
                          cast_text(:#{#user.hash}),
                          cast_text(:#{#user.fullNameUa}),
                          cast_text(:#{#user.fullNameEn}),
                          :#{#user.birthday},
                          cast_family(:#{#user.familyStatus.value}),
                          cast_int(:#{#user.children}),
                          cast_int(:id_user)
                      )).*""",
            nativeQuery = true)
    fun setMe(
            @Param("user") newUser: User,
            @Param("id_user") idUser: Int
    ): User = User()


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (user_delete(
                          cast_int(:id_user),
                          cast_int(:#{#contact_info.idContactInfo})
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_user") idUser: Int? = null,
            @Param("contact_info") contactInfo: ContactInfo? = ContactInfo()
    ): User = User()


    /** ============================== HASHING ============================== */


    //language=PostgresPLSQL
    @Query(value = "select generate_pass_decrypt(:id_user)",
            nativeQuery = true)
    fun decrypt(@Param("id_user") idUser: Int): String?

}
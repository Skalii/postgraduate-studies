package skalii.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skalii.web.server.postgraduatestudies.entities.User


@Repository
interface UsersRepository : JpaRepository<User, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (user_record(
                          cast_int(:id_user),
                          cast_text(:email),
                          cast_text(:phone_number),
                          cast_int(:id_contact_info)
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("email") email: String? = null,
            @Param("phone_number") phoneNumber: String? = null,
            @Param("id_user") idUser: Int? = null,
            @Param("id_contact_info") idContactInfo: Int? = null
    ): User

    //language=PostgresPLSQL
    @Query(value = """select (user_record(
                          _id_section => cast_int(:id_section),
                          _id_task => cast_int(:id_task)
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("id_section") idSection: Int? = null,
            @Param("id_task") idTask: Int? = null
    ): User

    //language=PostgresPLSQL
    @Query(value = """select (user_record(
                          _id_degree => cast_int(:id_degree),
                          _id_branch => cast_int(:id_branch),
                          _id_speciality => cast_int(:id_speciality),
                          _id_department => cast_int(:id_department),
                          _id_faculty => cast_int(:id_faculty),
                          _id_institute => cast_int(:id_institute),
                          all_records => cast_bool(:all_records)
                      )).*""",
            nativeQuery = true)
    fun getAll(
            @Param("all_records") allRecords: Boolean? = false,
            @Param("id_degree") idDegree: Int? = null,
            @Param("id_branch") idBranch: Int? = null,
            @Param("id_speciality") idSpeciality: Int? = null,
            @Param("id_department") idDepartment: Int? = null,
            @Param("id_faculty") idFaculty: Int? = null,
            @Param("id_institute") idInstitute: Int? = null
    ): MutableList<User>


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
    ): User


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (user_update(
                          cast_role(:#{#user.role.value}),
                          cast_text(:#{#user.hash}),
                          cast_text(:#{#user.fullNameUa}),
                          cast_text(:#{#user.fullNameEn}),
                          cast(:#{#user.birthday.toString()} as date),
                          cast_family(:#{#user.familyStatus.value}),
                          cast_int(:#{#user.children}),
                          cast_rank(:#{#user.academicRank.value}),
                          cast_int(:#{#user.degree.idDegree}),
                          cast_int(:#{#user.speciality.idSpeciality}),
                          cast_int(:#{#user.department.idDepartment}),
                          cast_int(:id_user),
                          cast_text(:email),
                          cast_text(:phone_number),
                          cast_int(:id_contact_info)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("user") newUser: User,
            @Param("email") email: String? = null,
            @Param("phone_number") phoneNumber: String? = null,
            @Param("id_user") idUser: Int? = null,
            @Param("id_contact_info") idContactInfo: Int? = null
    ): User

    //language=PostgresPLSQL
    @Query(value = """select (user_update(
                          new_full_name_ua => cast_text(:#{#user.fullNameUa}),
                          new_full_name_en => cast_text(:#{#user.fullNameEn}),
                          new_birthday => cast(:#{#user.birthday.toString()} as date),
                          new_family_status => cast_family(:#{#user.familyStatus.value}),
                          new_children => cast_int(:#{#user.children}),
                          _email => cast_text(:email)
                      )).*""",
            nativeQuery = true)
    fun setMe(
            @Param("user") newUser: User,
            @Param("email") email: String
    ): User

    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (user_delete(
                          cast_int(:id_user),
                          cast_text(:email),
                          cast_text(:phone_number),
                          cast_int(:id_contact_info)
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("email") email: String? = null,
            @Param("phone_number") phoneNumber: String? = null,
            @Param("id_user") idUser: Int? = null,
            @Param("id_contact_info") idContactInfo: Int? = null
    ): User


    /** ============================== HASHING ============================== */


    //language=PostgresPLSQL
    @Query(value = "select generate_pass_decrypt(:id_user)",
            nativeQuery = true)
    fun decrypt(@Param("id_user") idUser: Int): String?

}


//  todo fix setMe
//  todo setMePassword
//  todo set id_instructor to 0 for studies in study_info where instructor delete
//  todo set id_instructor for studies in study_info
//  todo create delete instructor
//  todo statistics = user + count tasks done + count tasks not done
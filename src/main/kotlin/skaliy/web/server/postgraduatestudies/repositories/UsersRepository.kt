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
import skaliy.web.server.postgraduatestudies.entities.ScientificLinks
import skaliy.web.server.postgraduatestudies.entities.Speciality
import skaliy.web.server.postgraduatestudies.entities.StudyInfo
import skaliy.web.server.postgraduatestudies.entities.User


@Repository
interface UsersRepository : JpaRepository<User, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (user_record(
                          cast_int(:id_user),
                          cast_int(:#{#contact_info.idContactInfo}),
                          cast_int(:#{#study_info.idStudyInfo}),
                          cast_int(:#{#scientific_links.idScientificLinks})
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("id_user") idUser: Int? = null,
            @Param("contact_info") contactInfo: ContactInfo? = ContactInfo(),
            @Param("study_info") studyInfo: StudyInfo? = StudyInfo(),
            @Param("scientific_links") scientificLinks: ScientificLinks? = ScientificLinks()
    ): User?

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
    ): MutableList<User>?


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (user_insert(
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
                          cast_int(:#{#user.contactInfo.idContactInfo}),
                          cast_int(:#{#user.studyInfo.idStudyInfo}),
                          cast_int(:#{#user.scientificLinks.idScientificLinks})
                      )).*""",
            nativeQuery = true)
    fun add(@Param("user") user: User?): User?


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
                          cast_int(:id_user),
                          cast_int(:#{#contact_info.idContactInfo}),
                          cast_int(:#{#study_info.idStudyInfo}),
                          cast_int(:#{#scientific_links.idScientificLinks})
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("user") newUser: User?,
            @Param("id_user") idUser: Int? = null,
            @Param("contact_info") contactInfo: ContactInfo? = ContactInfo(),
            @Param("study_info") studyInfo: StudyInfo? = StudyInfo(),
            @Param("scientific_links") scientificLinks: ScientificLinks? = ScientificLinks()
    ): User?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (user_delete(
                          cast_int(:id_user),
                          cast_int(:#{#contact_info.idContactInfo}),
                          cast_int(:#{#study_info.idStudyInfo}),
                          cast_int(:#{#scientific_links.idScientificLinks})
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_user") idUser: Int? = null,
            @Param("contact_info") contactInfo: ContactInfo? = ContactInfo(),
            @Param("study_info") studyInfo: StudyInfo? = StudyInfo(),
            @Param("scientific_links") scientificLinks: ScientificLinks? = ScientificLinks()
    ): User?


}
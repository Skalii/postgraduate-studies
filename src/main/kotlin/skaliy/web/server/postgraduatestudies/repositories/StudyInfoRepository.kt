package skaliy.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skaliy.web.server.postgraduatestudies.entities.StudyInfo
import skaliy.web.server.postgraduatestudies.entities.User


@Repository
interface StudyInfoRepository : JpaRepository<StudyInfo, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (study_info_record(
                          cast_int(:id_study_info),
                          cast_int(:#{#user.idUser})
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("id_study_info") idStudyInfo: Int? = null,
            @Param("user") user: User? = User()
    ): StudyInfo?

    //language=PostgresPLSQL
    @Query(value = "select (study_info_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<StudyInfo>?


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (study_info_insert(
                          cast_int(:#{#study_info.year}),
                          cast_form(:#{#study_info.form.value}),
                          cast_basis(:#{#study_info.basis.value}),
                          cast_text(:#{#study_info.themeTitle}),
                          cast_int(:#{#study_info.instructor.idUser})
                      )).*""",
            nativeQuery = true)
    fun add(@Param("study_info") studyInfo: StudyInfo?): StudyInfo?


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (study_info_update(
                          cast_int(:#{#study_info.year}),
                          cast_form(:#{#study_info.form.value}),
                          cast_basis(:#{#study_info.basis.value}),
                          cast_text(:#{#study_info.themeTitle}),
                          cast_int(:#{#study_info.instructor.idUser}),
                          cast_int(:id_study_info),
                          cast_int(:#{#user.idUser})
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("study_info") newStudyInfo: StudyInfo? = null,
            @Param("id_study_info") idStudyInfo: Int? = null,
            @Param("user") user: User? = User()
    ): StudyInfo?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (study_info_delete(
                          cast_int(:id_study_info),
                          cast_int(:#{#user.idUser})
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_study_info") idStudyInfo: Int? = null,
            @Param("user") user: User? = User()
    ): StudyInfo?

}
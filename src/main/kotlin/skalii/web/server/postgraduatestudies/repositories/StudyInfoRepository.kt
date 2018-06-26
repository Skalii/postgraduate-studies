package skalii.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skalii.web.server.postgraduatestudies.entities.StudyInfo


@Repository
interface StudyInfoRepository : JpaRepository<StudyInfo, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (study_info_record(
                          cast_int(:id_study_info),
                          cast_int(:id_user)
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("id_study_info") idStudyInfo: Int? = null,
            @Param("id_user") idUser: Int? = null
    ): StudyInfo

    //language=PostgresPLSQL
    @Query(value = "select (study_info_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<StudyInfo>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (study_info_insert(
                          cast_int(:year),
                          cast_form(:form),
                          cast_basis(:basis),
                          cast_text(:theme_title),
                          cast_int(:id_instructor)
                      )).*""",
            nativeQuery = true)
    fun add(
            @Param("year") year: Int,
            @Param("form") form: String,
            @Param("basis") basis: String,
            @Param("theme_title") themeTitle: String,
            @Param("id_instructor") idInstructor: Int
    ): StudyInfo


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (study_info_update(
                          cast_int(:#{#study_info.year}),
                          cast_form(:#{#study_info.form.value}),
                          cast_basis(:#{#study_info.basis.value}),
                          cast_text(:#{#study_info.themeTitle}),
                          cast_int(:#{#study_info.instructor.idUser}),
                          cast_int(:id_study_info)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("study_info") newStudyInfo: StudyInfo,
            @Param("id_study_info") idStudyInfo: Int
    ): StudyInfo


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (study_info_delete(
                          cast_int(:id_study_info),
                          cast_int(:id_user)
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_study_info") idStudyInfo: Int? = null,
            @Param("id_user") idUser: Int? = null
    ): StudyInfo

}
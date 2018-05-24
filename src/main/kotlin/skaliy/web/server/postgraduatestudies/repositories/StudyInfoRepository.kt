package skaliy.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skaliy.web.server.postgraduatestudies.entities.StudyInfo
import skaliy.web.server.postgraduatestudies.entities.User


@Repository
interface StudyInfoRepository : JpaRepository<StudyInfo, Long> {


    /** ============================== GET ============================== */


    //language=PostgresPLSQL
    @Query(value = "select (study_info_record(cast_int(:id_study_info))).*",
            nativeQuery = true)
    fun get(@Param("id_study_info") idStudyInfo: Int?): StudyInfo?

    //language=PostgresPLSQL
    @Query(value = "select (study_info_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<StudyInfo>?

    //language=PostgresPLSQL
    @Query(value = "select (study_info_record(_id_user => cast_int(:#{#user.idUser}))).*",
            nativeQuery = true)
    fun getByUser(@Param("user") user: User?): StudyInfo?


    /** ============================== INSERT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (study_info_insert(
                          cast_int(:#{#study_info.year}),
                          cast_form(:#{#study_info.form.value}),
                          cast_basis(:#{#study_info.basis.value}),
                          cast_text(:#{#study_info.themeTitle}),
                          cast_int(:#{#study_info.instructor.idUser}))).*""",
            nativeQuery = true)
    fun create(@Param("study_info") studyInfo: StudyInfo?): StudyInfo?


    /** ============================== UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (study_info_update(
                          cast_int(:#{#study_info.year}),
                          cast_form(:#{#study_info.form.value}),
                          cast_basis(:#{#study_info.basis.value}),
                          cast_text(:#{#study_info.themeTitle}),
                          cast_int(:#{#study_info.instructor.idUser}),
                          cast_int(:id_study_info))).*""",
            nativeQuery = true)
    fun update(
            @Param("study_info") newStudyInfo: StudyInfo?,
            @Param("id_study_info") idStudyInfo: Int?
    ): StudyInfo?

    //language=PostgresPLSQL
    @Query(value = """select (study_info_update(
                          cast_int(:#{#study_info.year}),
                          cast_form(:#{#study_info.form.value}),
                          cast_basis(:#{#study_info.basis.value}),
                          cast_text(:#{#study_info.themeTitle}),
                          cast_int(:#{#study_info.instructor.idUser}),
                          _id_user => cast_int(:#{#user.idUser}))).*""",
            nativeQuery = true)
    fun updateByUser(
            @Param("study_info") newStudyInfo: StudyInfo?,
            @Param("user") user: User?
    ): StudyInfo?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = "select (study_info_delete(cast_int(:id_study_info))).*",
            nativeQuery = true)
    fun delete(@Param("id_study_info") idStudyInfo: Int?): StudyInfo?

    //language=PostgresPLSQL
    @Query(value = "select (study_info_delete(_id_user => cast_int(:#{#user.idUser}))).*",
            nativeQuery = true)
    fun deleteByUser(@Param("user") user: User?): StudyInfo?

}
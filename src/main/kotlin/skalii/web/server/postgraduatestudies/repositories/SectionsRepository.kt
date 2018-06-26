package skalii.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skalii.web.server.postgraduatestudies.entities.Section
import skalii.web.server.postgraduatestudies.entities.Task
import skalii.web.server.postgraduatestudies.entities.User


@Repository
interface SectionsRepository : JpaRepository<Section, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (section_record(
                          cast_int(:id_section),
                          cast_int(:id_user),
                          cast_int(:number),
                          cast_text(:title)
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("id_user") idUser: Int? = null,
            @Param("number") number: Int? = null,
            @Param("title") title: String? = null,
            @Param("id_section") idSection: Int? = null
    ): Section

    //language=PostgresPLSQL
    @Query(value = "select (section_record(cast_int(:#{#task.section.idSection}))).*",
            nativeQuery = true)
    fun get(@Param("task") task: Task?): Section

    //language=PostgresPLSQL
    @Query(value = """select (section_record(
                          _id_user => cast_int(:#{#user.idUser}),
                          all_records => true
                      )).*""",
            nativeQuery = true)
    fun getAll(@Param("user") user: User?): MutableList<Section>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (section_insert(
                          cast_int(:id_user),
                          cast_int(:number),
                          cast_text(:title)
                      )).*""",
            nativeQuery = true)
    fun add(
            @Param("id_user") idUser: Int,
            @Param("number") number: Int,
            @Param("title") title: String
    ): Section


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (section_update(
                          cast_int(:#{#section.number}),
                          cast_text(:#{#section.title}),
                          cast_int(:id_section),
                          cast_int(:id_user),
                          cast_int(:number),
                          cast_text(:title)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("section") newSection: Section,
            @Param("id_user") idUser: Int? = null,
            @Param("number") number: Int? = null,
            @Param("title") title: String? = null,
            @Param("id_section") idSection: Int? = null
    ): Section


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (section_delete(
                          cast_int(:id_section),
                          cast_int(:id_user),
                          cast_int(:number),
                          cast_text(:title)
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_user") idUser: Int? = null,
            @Param("number") number: Int? = null,
            @Param("title") title: String? = null,
            @Param("id_section") idSection: Int? = null
    ): Section

    //language=PostgresPLSQL
    @Query(value = """select (section_delete(
                          _id_user => cast_int(:#{#user.idUser}),
                          all_from_user => true)).*""",
            nativeQuery = true)
    fun deleteAll(@Param("user") user: User?): MutableList<Section>

}
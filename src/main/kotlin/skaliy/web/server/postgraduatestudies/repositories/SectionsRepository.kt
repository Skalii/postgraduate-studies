package skaliy.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skaliy.web.server.postgraduatestudies.entities.Section
import skaliy.web.server.postgraduatestudies.entities.Task
import skaliy.web.server.postgraduatestudies.entities.User


@Repository
interface SectionsRepository : JpaRepository<Section, Int> {


    /** ============================== GET ============================== */


    //language=PostgresPLSQL
    @Query(value = "select (section_record(cast_int(:id_section))).*",
            nativeQuery = true)
    fun get(@Param("id_section") idSection: Int?): Section?

    //language=PostgresPLSQL
    @Query(value = "select (section_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<Section>?

    //language=PostgresPLSQL
    @Query(value = "select (section_record(cast_int(:#{#task.section.idSection}))).*",
            nativeQuery = true)
    fun getByTask(@Param("task") task: Task?): Section?

    //language=PostgresPLSQL
    @Query(value = """select (section_record(
                          _id_user => cast_int(:#{#user.idUser}),
                          _number => cast_int(:number),
                          _title => cast_int(:title))).*""",
            nativeQuery = true)
    fun getByUser(
            @Param("user") user: User?,
            @Param("number") number: Int?,
            @Param("title") title: String?
    ): Section?

    //language=PostgresPLSQL
    @Query(value = """select (section_record(
                          _id_user => cast_int(:#{#user.idUser}),
                          all_records => true)).*""",
            nativeQuery = true)
    fun getAllByUser(@Param("user") user: User?): MutableList<Section>?


    /** ============================== INSERT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (section_insert(
                          cast_int(:#{#section.idUser}),
                          cast_int(:#{#section.number}),
                          cast_text(:#{#section.title}))).*""",
            nativeQuery = true)
    fun create(@Param("section") section: Section?): Section?


    /** ============================== UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (section_update(
                          cast_int(:#{#section.number}),
                          cast_text(:#{#section.title}),
                          cast_int(:id_section))).*""",
            nativeQuery = true)
    fun update(
            @Param("section") newSection: Section?,
            @Param("id_section") idSection: Int?
    ): Section?

    //language=PostgresPLSQL
    @Query(value = """select (section_update(
                          cast_int(:#{#section.number}),
                          cast_text(:#{#section.title}),
                          cast_int(:#{#task.section.idSection}))).*""",
            nativeQuery = true)
    fun updateByTask(
            @Param("section") newSection: Section?,
            @Param("task") task: Task?
    ): Section?

    //language=PostgresPLSQL
    @Query(value = """select (section_update(
                          cast_int(:#{#section.number}),
                          cast_text(:#{#section.title}),
                          _id_user => cast_int(:#{#user.idUser}),
                          _number => cast_int(:number),
                          _title => cast_text(:title))).*""",
            nativeQuery = true)
    fun updateByUser(
            @Param("section") newSection: Section?,
            @Param("user") user: User?,
            @Param("number") number: Int?,
            @Param("title") title: String?
    ): Section?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = "select (section_delete(cast_int(:id_section))).*",
            nativeQuery = true)
    fun delete(@Param("id_section") idSection: Int?): Section?

    //language=PostgresPLSQL
    @Query(value = "select (section_delete(cast_int(:#{#task.section.idSection}))).*",
            nativeQuery = true)
    fun deleteByTask(@Param("task") task: Task?): Section?

    //language=PostgresPLSQL
    @Query(value = """select (section_delete(
                          _id_user => cast_int(:#{#user.idUser}),
                          _number => cast_int(:number),
                          _title => cast_text(:title))).*""",
            nativeQuery = true)
    fun deleteByUser(
            @Param("user") user: User?,
            @Param("number") number: Int?,
            @Param("title") title: String?
    ): Section?

    //language=PostgresPLSQL
    @Query(value = """select (section_delete(
                          _id_user => cast_int(:#{#user.idUser}),
                          all_from_user => true)).*""",
            nativeQuery = true)
    fun deleteAllByUser(@Param("user") user: User?): Section?

}
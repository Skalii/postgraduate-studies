package skaliy.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skaliy.web.server.postgraduatestudies.entities.Department
import skaliy.web.server.postgraduatestudies.entities.Faculty
import skaliy.web.server.postgraduatestudies.entities.Institute
import skaliy.web.server.postgraduatestudies.entities.User


@Repository
interface FacultiesRepository : JpaRepository<Faculty, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (faculty_record(
                          cast_int(:id_faculty),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("id_faculty") idFaculty: Int? = null,
            @Param("name") name: String? = null
    ): Faculty?

    //language=PostgresPLSQL
    @Query(value = "select (faculty_record(cast_int(:#{#department.faculty.idFaculty}))).*",
            nativeQuery = true)
    fun get(@Param("department") department: Department?): Faculty?

    //language=PostgresPLSQL
    @Query(value = "select (faculty_record(cast_int(:#{#user.department.faculty.idFaculty}))).*",
            nativeQuery = true)
    fun get(@Param("user") user: User?): Faculty?

    //language=PostgresPLSQL
    @Query(value = """select (faculty_record(
                          _id_institute => cast_int(:#{#institute.idInstitute}),
                          all_records => cast_bool(:all_records)
                      )).*""",
            nativeQuery = true)
    fun getAll(
            @Param("all_records") allRecords: Boolean? = false,
            @Param("institute") institute: Institute? = Institute()
    ): MutableList<Faculty?>?


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = "select (faculty_insert(cast_text(:name))).*",
            nativeQuery = true)
    fun add(@Param("name") name: String): Faculty?


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (faculty_update(
                          cast_text(:#{#faculty.name}),
                          cast_int(:id_faculty)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("faculty") newFaculty: Faculty,
            @Param("id_faculty") idFaculty: Int
    ): Faculty?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (faculty_delete(
                          cast_int(:id_faculty),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_faculty") idFaculty: Int? = null,
            @Param("name") name: String? = null
    ): Faculty?

}
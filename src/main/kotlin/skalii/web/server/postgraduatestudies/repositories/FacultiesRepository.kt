package skalii.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skalii.web.server.postgraduatestudies.entities.Department
import skalii.web.server.postgraduatestudies.entities.Faculty
import skalii.web.server.postgraduatestudies.entities.User


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
            @Param("name") name: String? = null,
            @Param("id_faculty") idFaculty: Int? = null
    ): Faculty

    //language=PostgresPLSQL
    @Query(value = "select (faculty_record(cast_int(:#{#department.faculty.idFaculty}))).*",
            nativeQuery = true)
    fun get(@Param("department") department: Department?): Faculty

    //language=PostgresPLSQL
    @Query(value = "select (faculty_record(cast_int(:#{#user.department.faculty.idFaculty}))).*",
            nativeQuery = true)
    fun get(@Param("user") user: User?): Faculty

    //language=PostgresPLSQL
    @Query(value = """select (faculty_record(
                          _id_institute => cast_int(:id_institute),
                          all_records => cast_bool(:all_records)
                      )).*""",
            nativeQuery = true)
    fun getAll(
            @Param("all_records") allRecords: Boolean? = false,
            @Param("id_institute") idInstitute: Int? = null
    ): MutableList<Faculty>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = "select (faculty_insert(cast_text(:name))).*",
            nativeQuery = true)
    fun add(@Param("name") name: String): Faculty


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
    ): Faculty


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (faculty_delete(
                          cast_int(:id_faculty),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("name") name: String? = null,
            @Param("id_faculty") idFaculty: Int? = null
    ): Faculty

}
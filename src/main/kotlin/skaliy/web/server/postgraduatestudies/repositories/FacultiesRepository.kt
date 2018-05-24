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


    /** ============================== GET ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (faculty_record(
                          cast_int(:id_faculty),
                          cast_text(:name))).*""",
            nativeQuery = true)
    fun get(
            @Param("id_faculty") idFaculty: Int?,
            @Param("name") name: String?
    ): Faculty?

    //language=PostgresPLSQL
    @Query(value = "select (faculty_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<Faculty>?

    //language=PostgresPLSQL
    @Query(value = "select (faculty_record(_id_institute => cast_int(:#{#institute.idInstitute}))).*",
            nativeQuery = true)
    fun getAllByInstitute(@Param("institute") institute: Institute?): Faculty?

    //language=PostgresPLSQL
    @Query(value = "select (faculty_record(cast_int(:#{#department.idFaculty}))).*",
            nativeQuery = true)
    fun getByDepartment(@Param("department") department: Department?): Faculty?

    //language=PostgresPLSQL
    @Query(value = "select (faculty_record(cast_int(:#{#user.department.idFaculty}))).*",
            nativeQuery = true)
    fun getByUser(@Param("user") user: User?): Faculty?


    /** ============================== INSERT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (faculty_insert(
                          cast_text(:#{#faculty.name}))).*""",
            nativeQuery = true)
    fun create(@Param("faculty") faculty: Faculty?): Faculty?


    /** ============================== UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (faculty_update(
                          cast_text(:#{#faculty.name}),
                          cast_int(:id_faculty),
                          cast_text(:name))).*""",
            nativeQuery = true)
    fun update(
            @Param("faculty") newFaculty: Faculty?,
            @Param("id_faculty") idFaculty: Int?,
            @Param("name") name: String?
    ): Faculty?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (faculty_delete(
                          cast_int(:id_faculty),
                          cast_text(:name))).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_faculty") idFaculty: Int?,
            @Param("name") name: String?
    ): Faculty?

}
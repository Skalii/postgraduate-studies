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
interface DepartmentsRepository : JpaRepository<Department, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (department_record(
                          cast_int(:id_department),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("id_department") idDepartment: Int? = null,
            @Param("name") name: String? = null
    ): Department?

    //language=PostgresPLSQL
    @Query(value = "select (department_record(cast_int(:#{#user.department.idDepartment}))).*",
            nativeQuery = true)
    fun get(@Param("user") user: User? = User()): Department?

    //language=PostgresPLSQL
    @Query(value = """select (department_record(
                          _id_institute => cast_int(:#{#institute.idInstitute}),
                          _id_faculty => cast_int(:#{#faculty.idFaculty}),
                          all_records => cast_bool(:all_records)
                      )).*""",
            nativeQuery = true)
    fun getAll(
            @Param("all_records") allRecords: Boolean? = false,
            @Param("institute") institute: Institute? = Institute(),
            @Param("faculty") faculty: Faculty? = Faculty()
    ): MutableList<Department>?


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (department_insert(
                          cast_text(:#{#department.name}),
                          cast_int(:#{#department.institute.idInstitute}),
                          cast_int(:#{#department.faculty.idFaculty})
                      )).*""",
            nativeQuery = true)
    fun add(@Param("department") department: Department?): Department?


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (department_update(
                          cast_text(:#{#department.name}),
                          cast_int(:#{#department.institute.idInstitute}),
                          cast_int(:#{#department.faculty.idFaculty}),
                          cast_int(:id_department),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("department") newDepartment: Department?,
            @Param("id_department") idDepartment: Int? = null,
            @Param("name") name: String? = null
    ): Department?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (department_delete(
                          cast_int(:id_department),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_department") idDepartment: Int? = null,
            @Param("name") name: String? = null
    ): Department?

}
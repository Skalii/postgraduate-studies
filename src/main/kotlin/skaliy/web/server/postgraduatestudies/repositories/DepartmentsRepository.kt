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


    /** ============================== GET ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (department_record(
                          cast_int(:id_department),
                          cast_text(:name))).*""",
            nativeQuery = true)
    fun get(
            @Param("id_department") idDepartment: Int?,
            @Param("name") name: String?
    ): Department?

    //language=PostgresPLSQL
    @Query(value = "select (department_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<Department>?

    //language=PostgresPLSQL
    @Query(value = "select (department_record(_id_institute => cast_int(:#{#institute.idInstitute}))).*",
            nativeQuery = true)
    fun getAllByInstitute(@Param("institute") institute: Institute?): Department?

    //language=PostgresPLSQL
    @Query(value = "select (department_record(_id_faculty => cast_int(:#{#faculty.idFaculty}))).*",
            nativeQuery = true)
    fun getAllByFaculty(@Param("faculty") faculty: Faculty?): Department?

    //language=PostgresPLSQL
    @Query(value = "select (department_record(cast_int(:#{#user.department.idDepartment}))).*",
            nativeQuery = true)
    fun getByUser(@Param("user") user: User?): Department?


    /** ============================== INSERT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (department_insert(
                          cast_text(:#{#department.name}),
                          cast_int(:#{#department.idInstitute}),
                          cast_int(:#{#department.idFaculty}))).*""",
            nativeQuery = true)
    fun create(@Param("department") department: Department?): Department?


    /** ============================== UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (department_update(
                          cast_text(:#{#department.name}),
                          cast_int(:#{#department.idInstitute}),
                          cast_int(:#{#department.idFaculty}),
                          cast_int(:id_department),
                          cast_text(:name))).*""",
            nativeQuery = true)
    fun update(
            @Param("department") newDepartment: Department?,
            @Param("id_department") idDepartment: Int?,
            @Param("name") name: String?
    ): Department?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (department_delete(
                          cast_int(:id_department),
                          cast_text(:name))).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_department") idDepartment: Int?,
            @Param("name") name: String?
    ): Department?

}
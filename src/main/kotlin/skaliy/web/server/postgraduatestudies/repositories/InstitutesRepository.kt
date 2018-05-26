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
interface InstitutesRepository : JpaRepository<Institute, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (institute_record(
                          cast_int(:id_institute),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("id_institute") idInstitute: Int? = null,
            @Param("name") name: String? = null
    ): Institute?

    //language=PostgresPLSQL
    @Query(value = "select (institute_record(cast_int(:#{#faculty.department.idInstitute}))).*",
            nativeQuery = true)
    fun get(@Param("faculty") faculty: Faculty? = Faculty()): Institute?

    //language=PostgresPLSQL
    @Query(value = "select (institute_record(cast_int(:#{#department.idInstitute}))).*",
            nativeQuery = true)
    fun get(@Param("department") department: Department? = Department()): Institute?

    //language=PostgresPLSQL
    @Query(value = "select (institute_record(cast_int(:#{#user.department.idInstitute}))).*",
            nativeQuery = true)
    fun get(@Param("user") user: User? = User()): Institute?

    //language=PostgresPLSQL
    @Query(value = "select (institute_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<Institute>?


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (institute_insert(
                          cast_text(:#{#institute.name}),
                          cast_text(:#{#institute.namedAfter}),
                          cast_text(:#{#institute.abbreviation})
                      )).*""",
            nativeQuery = true)
    fun add(@Param("institute") institute: Institute?): Institute?


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (institute_update(
                          cast_text(:#{#institute.name}),
                          cast_int(:id_institute),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("institute") newInstitute: Institute?,
            @Param("id_institute") idInstitute: Int? = null,
            @Param("name") name: String? = null
    ): Institute?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (institute_delete(
                          cast_int(:id_institute),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_institute") idInstitute: Int? = null,
            @Param("name") name: String? = null
    ): Institute?

}
package skalii.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skalii.web.server.postgraduatestudies.entities.Degree
import skalii.web.server.postgraduatestudies.entities.User


@Repository
interface DegreesRepository : JpaRepository<Degree, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (degree_record(
                          cast_int(:id_degree),
                          cast_degree(:name),
                          cast_branch(:branch)
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("name") name: String? = null,
            @Param("branch") branch: String? = null,
            @Param("id_degree") idDegree: Int? = null
    ): Degree

    //language=PostgresPLSQL
    @Query(value = "select (degree_record(:#{#user.degree.idDegree})).*",
            nativeQuery = true)
    fun get(@Param("user") user: User?): Degree

    //language=PostgresPLSQL
    @Query(value = """select (degree_record(
                          _name => cast_degree(:name),
                          _branch => cast_branch(:branch),
                          all_records => cast_bool(:all_records)
                      )).*""",
            nativeQuery = true)
    fun getAll(
            @Param("all_records") allRecords: Boolean? = false,
            @Param("name") name: String? = null,
            @Param("branch") branch: String? = null
    ): MutableList<Degree>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (degree_insert(
                          cast_degree(:name),
                          cast_branch(:branch)
                      )).*""",
            nativeQuery = true)
    fun add(
            @Param("name") name: String,
            @Param("branch") branch: String
    ): Degree


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (degree_update(
                          cast_degree(:#{#degree.name.value}),
                          cast_branch(:#{#degree.branch.value}),
                          cast_int(:id_degree),
                          cast_degree(:name),
                          cast_branch(:branch)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("degree") newDegree: Degree,
            @Param("name") name: String? = null,
            @Param("branch") branch: String? = null,
            @Param("id_degree") idDegree: Int? = null
    ): Degree


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (degree_delete(
                          cast_int(:id_degree),
                          cast_degree(:name),
                          cast_branch(:branch)
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("name") name: String? = null,
            @Param("branch") branch: String? = null,
            @Param("id_degree") idDegree: Int? = null
    ): Degree

}
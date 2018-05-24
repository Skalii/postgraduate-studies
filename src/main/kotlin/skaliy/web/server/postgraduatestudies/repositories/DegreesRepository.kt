package skaliy.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skaliy.web.server.postgraduatestudies.entities.Degree
import skaliy.web.server.postgraduatestudies.entities.User


@Repository
interface DegreesRepository : JpaRepository<Degree, Int> {


    /** ============================== GET ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (degree_record(
                          cast_int(:id_degree),
                          cast_degree(:name),
                          cast_branch(:branch))).*""",
            nativeQuery = true)
    fun get(
            @Param("id_degree") idDegree: Int?,
            @Param("name") name: String?,
            @Param("branch") branch: String?
    ): Degree?

    //language=PostgresPLSQL
    @Query(value = "select (degree_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<Degree>?

    //language=PostgresPLSQL
    @Query(value = "select (degree_record(_name => cast_degree(:name))).*",
            nativeQuery = true)
    fun getAllByName(@Param("name") name: String?): MutableList<Degree>?

    //language=PostgresPLSQL
    @Query(value = "select (degree_record(_branch => cast_branch(:branch))).*",
            nativeQuery = true)
    fun getAllByBranch(@Param("branch") branch: String?): MutableList<Degree>?

    //language=PostgresPLSQL
    @Query(value = "select (degree_record(:#{#user.idDegree})).*",
            nativeQuery = true)
    fun getByUser(@Param("user") user: User?): Degree?


    /** ============================== INSERT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (degree_insert(
                          cast_degree(:#{#degree.name.value}),
                          cast_branch(:#{#degree.branch.value}))).*""",
            nativeQuery = true)
    fun create(@Param("degree") degree: Degree): Degree?


    /** ============================== UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (degree_update(
                          cast_degree(:#{#degree.name.value}),
                          cast_branch(:#{#degree.branch.value}),
                          cast_int(:id_degree),
                          cast_degree(:name),
                          cast_branch(:branch))).*""",
            nativeQuery = true)
    fun update(
            @Param("degree") newDegree: Degree?,
            @Param("id_degree") idDegree: Int?,
            @Param("name") name: String?,
            @Param("branch") branch: String?
    ): Degree?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (degree_delete(
                          cast_int(:id_degree),
                          cast_degree(:name),
                          cast_branch(:branch))).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_degree") idDegree: Int?,
            @Param("name") name: String?,
            @Param("branch") branch: String?
    ): Degree?

}
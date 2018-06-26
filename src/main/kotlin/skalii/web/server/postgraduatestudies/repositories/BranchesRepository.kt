package skalii.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skalii.web.server.postgraduatestudies.entities.Branch
import skalii.web.server.postgraduatestudies.entities.Speciality
import skalii.web.server.postgraduatestudies.entities.User


@Repository
interface BranchesRepository : JpaRepository<Branch, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (branch_record(
                          cast_int(:id_branch),
                          cast_text(:number),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("number") number: String? = null,
            @Param("name") name: String? = null,
            @Param("id_branch") idBranch: Int? = null
    ): Branch

    //language=PostgresPLSQL
    @Query(value = "select (branch_record(cast_int(:#{#speciality.branch.idBranch}))).*",
            nativeQuery = true)
    fun get(@Param("speciality") speciality: Speciality?): Branch

    //language=PostgresPLSQL
    @Query(value = "select (branch_record(cast_int(:#{#user.speciality.branch.idBranch}))).*",
            nativeQuery = true)
    fun get(@Param("user") user: User?): Branch

    //language=PostgresPLSQL
    @Query(value = "select (branch_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<Branch>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (branch_insert(
                          cast_text(:number),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun add(
            @Param("number") number: String,
            @Param("name") name: String
    ): Branch


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (branch_update(
                          cast_text(:#{#branch.number}),
                          cast_text(:#{#branch.name}),
                          cast_int(:id_branch)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("branch") newBranch: Branch,
            @Param("id_branch") idBranch: Int
    ): Branch


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (branch_delete(
                          cast_int(:id_branch),
                          cast_text(:number),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("number") number: String? = null,
            @Param("name") name: String? = null,
            @Param("id_branch") idBranch: Int? = null
    ): Branch

    //language=PostgresPLSQL
    @Query(value = "select (branch_delete(cast_int(:#{#speciality.branch.idBranch}))).*",
            nativeQuery = true)
    fun delete(@Param("speciality") speciality: Speciality?): Branch

}
package skaliy.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skaliy.web.server.postgraduatestudies.entities.Branch
import skaliy.web.server.postgraduatestudies.entities.Speciality
import skaliy.web.server.postgraduatestudies.entities.User


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
            @Param("id_branch") idBranch: Int? = null,
            @Param("number") number: String? = null,
            @Param("name") name: String? = null
    ): Branch?

    //language=PostgresPLSQL
    @Query(value = "select (branch_record(cast_int(:#{#speciality.branch.idBranch}))).*",
            nativeQuery = true)
    fun getBySpeciality(@Param("speciality") speciality: Speciality? = Speciality()): Branch?

    //language=PostgresPLSQL
    @Query(value = "select (branch_record(cast_int(:#{#user.speciality.branch.idBranch}))).*",
            nativeQuery = true)
    fun getByUser(@Param("user") user: User? = User()): Branch?

    //language=PostgresPLSQL
    @Query(value = "select (branch_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<Branch>?


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (branch_insert(
                          cast_text(:#{#branch.number}),
                          cast_text(:#{#branch.name})
                      )).*""",
            nativeQuery = true)
    fun add(@Param("branch") branch: Branch?): Branch?


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (branch_update(
                          cast_text(:#{#branch.number}),
                          cast_text(:#{#branch.name}),
                          cast_int(:id_branch),
                          cast_text(:number),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("branch") newBranch: Branch?,
            @Param("id_branch") idBranch: Int? = null,
            @Param("number") number: String? = null,
            @Param("name") name: String? = null
    ): Branch?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (branch_delete(
                          cast_int(:id_branch),
                          cast_text(:number),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_branch") idBranch: Int? = null,
            @Param("number") number: String? = null,
            @Param("name") name: String? = null
    ): Branch?

    //language=PostgresPLSQL
    @Query(value = "select (branch_delete(cast_int(:#{#speciality.branch.idBranch}))).*",
            nativeQuery = true)
    fun deleteBySpeciality(@Param("speciality") speciality: Speciality? = Speciality()): Branch?

}

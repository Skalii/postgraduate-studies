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


    /** ============================== GET ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (branch_record(
                          cast_int(:id_branch),
                          cast_text(:number),
                          cast_text(:name))).*""",
            nativeQuery = true)
    fun get(
            @Param("id_branch") idBranch: Int?,
            @Param("number") number: String?,
            @Param("name") name: String?
    ): Branch?

    //language=PostgresPLSQL
    @Query(value = "select (branch_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<Branch>?

    //language=PostgresPLSQL
    @Query(value = "select (branch_record(cast_int(:#{#speciality.branch.idBranch}))).*",
            nativeQuery = true)
    fun getBySpeciality(@Param("speciality") speciality: Speciality?): Branch?

    //language=PostgresPLSQL
    @Query(value = """select (branch_record(
                          (select (speciality_record(
                              cast_int(:#{#user.speciality.idSpeciality})).id_branch)))).*""",
            nativeQuery = true)
    fun getByUser(@Param("user") user: User?): Branch?


    /** ============================== INSERT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (branch_insert(
                          cast_text(:#{#branch.number}),
                          cast_text(:#{#branch.name}))).*""",
            nativeQuery = true)
    fun create(@Param("branch") branch: Branch?): Branch?


    /** ============================== UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (branch_update(
                          cast_text(:#{#branch.number}),
                          cast_text(:#{#branch.name}),
                          cast_int(:id_branch),
                          cast_text(:number),
                          cast_text(:name))).*""",
            nativeQuery = true)
    fun update(
            @Param("branch") newBranch: Branch?,
            @Param("id_branch") idBranch: Int?,
            @Param("number") number: String?,
            @Param("name") name: String?
    ): Branch?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (branch_delete(
                          cast_int(:id_branch),
                          cast_text(:number),
                          cast_text(:name))).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_branch") idBranch: Int?,
            @Param("number") number: String?,
            @Param("name") name: String?
    ): Branch?

    //language=PostgresPLSQL
    @Query(value = "select (branch_delete(cast_int(:#{#speciality.branch.idBranch}))).*",
            nativeQuery = true)
    fun deleteBySpeciality(@Param("speciality") speciality: Speciality?): Branch?

}

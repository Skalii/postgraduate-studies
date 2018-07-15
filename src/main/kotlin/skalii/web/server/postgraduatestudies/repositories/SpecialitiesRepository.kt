package skalii.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skalii.web.server.postgraduatestudies.entities.Branch
import skalii.web.server.postgraduatestudies.entities.Speciality
import skalii.web.server.postgraduatestudies.entities.User


@Repository
interface SpecialitiesRepository : JpaRepository<Speciality, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (speciality_record(
                          cast_int(:id_speciality),
                          cast_text(:number),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("number") number: String? = null,
            @Param("name") name: String? = null,
            @Param("id_speciality") idSpeciality: Int? = null
    ): Speciality

    //language=PostgresPLSQL
    @Query(value = "select (speciality_record(cast_int(:#{#user.speciality.idSpeciality}))).*",
            nativeQuery = true)
    fun get(@Param("user") user: User?): Speciality

    //language=PostgresPLSQL
    @Query(value = """select (speciality_record(
                          all_from_id_branch => cast_int(:id_branch),
                          all_records => cast_bool(:all_records)
                      )).*""",
            nativeQuery = true)
    fun getAll(
            @Param("all_records") allRecords: Boolean? = false,
            @Param("id_branch") idBranch: Int? = null
    ): MutableList<Speciality>

    /** ============================== ADD / INSERT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (speciality_insert(
                          cast_int(:id_branch),
                          cast_text(:number),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun add(
            @Param("number") number: String,
            @Param("name") name: String,
            @Param("id_branch") idBranch: Int
    ): Speciality


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (speciality_update(
                          cast_int(:#{#speciality.branch.idBranch}),
                          cast_text(:#{#speciality.number}),
                          cast_text(:#{#speciality.name}),
                          cast_int(:id_speciality),
                          cast_text(:number),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("speciality") changedSpeciality: Speciality,
            @Param("number") number: String? = null,
            @Param("name") name: String? = null,
            @Param("id_speciality") idSpeciality: Int? = null
    ): Speciality


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (speciality_delete(
                          cast_int(:id_speciality),
                          cast_text(:number),
                          cast_text(:name)
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("number") number: String? = null,
            @Param("name") name: String? = null,
            @Param("id_speciality") idSpeciality: Int? = null
    ): Speciality

    //language=PostgresPLSQL
    @Query(value = "select (speciality_delete(all_from_id_branch => cast_int(:#{#branch.idBranch}))).*",
            nativeQuery = true)
    fun deleteAll(@Param("branch") branch: Branch?): MutableList<Speciality>

}
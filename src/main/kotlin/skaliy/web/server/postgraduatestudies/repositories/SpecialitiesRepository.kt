package skaliy.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skaliy.web.server.postgraduatestudies.entities.Branch
import skaliy.web.server.postgraduatestudies.entities.Speciality
import skaliy.web.server.postgraduatestudies.entities.User


@Repository
interface SpecialitiesRepository : JpaRepository<Speciality, Int> {


    /** ============================== GET ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (speciality_record(
                          cast_int(:id_speciality),
                          cast_text(:number),
                          cast_text(:name))).*""",
            nativeQuery = true)
    fun get(
            @Param("id_speciality") idSpeciality: Int?,
            @Param("number") number: String?,
            @Param("name") name: String?
    ): Speciality?

    //language=PostgresPLSQL
    @Query(value = "select (speciality_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<Speciality>?

    //language=PostgresPLSQL
    @Query(value = "select (speciality_record(_id_branch => cast_int(:#{#branch.idBranch}))).*",
            nativeQuery = true)
    fun getAllByBranch(@Param("branch") branch: Branch?): Speciality?

    //language=PostgresPLSQL
    @Query(value = "select (speciality_record(cast_int(:#{#user.speciality.idSpeciality}))).*",
            nativeQuery = true)
    fun getByUser(@Param("user") user: User?): Speciality?


    /** ============================== INSERT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (speciality_insert(
                          cast_int(:#{#speciality.idBranch}),
                          cast_text(:#{#speciality.number}),
                          cast_text(:#{#speciality.name}))).*""",
            nativeQuery = true)
    fun create(@Param("speciality") speciality: Speciality?): Speciality?


    /** ============================== UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (speciality_update(
                          cast_int(:#{#specialitwy.idBranch}),
                          cast_text(:#{#speciality.number}),
                          cast_text(:#{#speciality.name}),
                          cast_int(:id_speciality),
                          cast_text(:number),
                          cast_text(:name))).*""",
            nativeQuery = true)
    fun update(
            @Param("speciality") newSpeciality: Speciality?,
            @Param("id_speciality") idSpeciality: Int?,
            @Param("number") number: String?,
            @Param("name") name: String?
    ): Speciality?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (speciality_delete(
                          cast_int(:id_speciality),
                          cast_text(:number),
                          cast_text(:name))).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_speciality") idSpeciality: Int?,
            @Param("number") number: String?,
            @Param("name") name: String?
    ): Speciality?

    //language=PostgresPLSQL
    @Query(value = "select (speciality_delete(all_from_id_branch => cast_int(:#{#branch.idBranch}))).*",
            nativeQuery = true)
    fun deleteAllByBranch(
            @Param("branch") branch: Branch?
    ): MutableList<Speciality>?

}
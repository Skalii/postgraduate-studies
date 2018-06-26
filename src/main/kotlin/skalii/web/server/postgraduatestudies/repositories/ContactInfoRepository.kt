package skalii.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skalii.web.server.postgraduatestudies.entities.ContactInfo


@Repository
interface ContactInfoRepository : JpaRepository<ContactInfo, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (contact_info_record(
                          cast_int(:id_contact_info),
                          cast_int(:id_user),
                          cast_text(:phone_number),
                          cast_text(:email)
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("email") email: String? = null,
            @Param("phone_number") phoneNumber: String? = null,
            @Param("id_user") idUser: Int? = null,
            @Param("id_contact_info") idContactInfo: Int? = null
    ): ContactInfo

    //language=PostgresPLSQL
    @Query(value = "select (contact_info_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<ContactInfo>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (contact_info_insert(
                          cast_text(:phone_number),
                          cast_text(:email),
                          cast_text(:address)
                      )).*""",
            nativeQuery = true)
    fun add(
            @Param("email") email: String,
            @Param("phone_number") phoneNumber: String,
            @Param("address") address: String?
    ): ContactInfo


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (contact_info_update(
                          cast_text(:#{#contact_info.phoneNumber}),
                          cast_text(:#{#contact_info.email}),
                          cast_text(:#{#contact_info.address}),
                          cast_int(:id_contact_info)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("contact_info") newContactInfo: ContactInfo,
            @Param("id_contact_info") idContactInfo: Int
    ): ContactInfo


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (contact_info_delete(
                          cast_int(:id_contact_info),
                          cast_text(:phone_number),
                          cast_text(:email)
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("email") email: String? = null,
            @Param("phone_number") phoneNumber: String? = null,
            @Param("id_contact_info") idContactInfo: Int? = null
    ): ContactInfo

}
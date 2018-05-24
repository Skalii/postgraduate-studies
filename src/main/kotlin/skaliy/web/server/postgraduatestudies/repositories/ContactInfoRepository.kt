package skaliy.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skaliy.web.server.postgraduatestudies.entities.ContactInfo
import skaliy.web.server.postgraduatestudies.entities.User


@Repository
interface ContactInfoRepository : JpaRepository<ContactInfo, Int> {


    /** ============================== GET ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (contact_info_record(
                          cast_int(:id_contact_info),
                          _phone_number => cast_text(:phone_number),
                          _email => cast_text(:email))).*""",
            nativeQuery = true)
    fun get(
            @Param("id_contact_info") idContactInfo: Int?,
            @Param("phone_number") phoneNumber: String?,
            @Param("email") email: String?
    ): ContactInfo?

    //language=PostgresPLSQL
    @Query(value = "select (contact_info_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<ContactInfo>?

    //language=PostgresPLSQL
    @Query(value = "select (contact_info_record(_id_user => cast_int(:#{#user.idUser}))).*",
            nativeQuery = true)
    fun getByUser(@Param("user") user: User?): ContactInfo?


    /** ============================== INSERT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (contact_info_insert(
                          cast_text(:#{#contact_info.phoneNumber}),
                          cast_text(:#{#contact_info.email}),
                          cast_text(:#{#contact_info.address}))).*""",
            nativeQuery = true)
    fun create(@Param("contact_info") contactInfo: ContactInfo?): ContactInfo?


    /** ============================== UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (contact_info_update(
                          cast_text(:#{#contact_info.phoneNumber}),
                          cast_text(:#{#contact_info.email}),
                          cast_text(:#{#contact_info.address}),
                          cast_int(:id_contact_info),
                          _phone_number => cast_text(:phone_number),
                          _email => cast_text(:email))).*""",
            nativeQuery = true)
    fun update(
            @Param("contact_info") newContactInfo: ContactInfo?,
            @Param("id_contact_info") idContactInfo: Int?,
            @Param("phone_number") phoneNumber: String?,
            @Param("email") email: String?
    ): ContactInfo?

    //language=PostgresPLSQL
    @Query(value = """select (contact_info_update(
                          cast_text(:#{#contact_info.phoneNumber}),
                          cast_text(:#{#contact_info.email}),
                          cast_text(:#{#contact_info.address}),
                          _id_user => cast_int(:#{#user.idUser}))).*""",
            nativeQuery = true)
    fun updateByUser(
            @Param("contact_info") newContactInfo: ContactInfo?,
            @Param("user") user: User?
    ): ContactInfo?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (contact_info_delete(
                          cast_int(:id_contact_info),
                          cast_text(:phone_number),
                          cast_text(:email))).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_contact_info") idContactInfo: Int?,
            @Param("phone_number") phoneNumber: String?,
            @Param("email") email: String?
    ): ContactInfo?

}
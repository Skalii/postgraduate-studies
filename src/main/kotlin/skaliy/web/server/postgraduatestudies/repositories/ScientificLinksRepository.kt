package skaliy.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skaliy.web.server.postgraduatestudies.entities.User
import skaliy.web.server.postgraduatestudies.entities.ScientificLinks


@Repository
interface ScientificLinksRepository : JpaRepository<ScientificLinks, Int> {


    /** ============================== GET ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_record(
                          cast_int(:id_scientific_links),
                          _orcid => cast_text(:orcid),
                          _researcherid => cast_text(:researcherid),
                          _google_scholar_id => cast_text(:google_scholar_id),
                          _scopus_author_id => cast_text(:scopus_author_id))).*""",
            nativeQuery = true)
    fun get(
            @Param("id_scientific_links") idScientificLinks: Int?,
            @Param("orcid") orcid: String?,
            @Param("researcherid") researcherid: String?,
            @Param("google_scholar_id") googleScholarId: String?,
            @Param("scopus_author_id") scopusAuthorId: String?
    ): ScientificLinks?

    //language=PostgresPLSQL
    @Query(value = "select (scientific_links_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<ScientificLinks>?

    //language=PostgresPLSQL
    @Query(value = "select (scientific_links_record(_id_user => cast_int(:#{#user.idUser}))).*",
            nativeQuery = true)
    fun getByUser(@Param("user") user: User?): ScientificLinks?


    /** ============================== INSERT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_insert(
                          cast_text(:#{#scientific_links.orcid}),
                          cast_text(:#{#scientific_links.researcherid}),
                          cast_text(:#{#scientific_links.googleScholarId}),
                          cast_text(:#{#scientific_links.scopusAuthorId}))).*""",
            nativeQuery = true)
    fun create(@Param("scientific_links") scientificLinks: ScientificLinks?): ScientificLinks?


    /** ============================== UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_update(
                          cast_text(:#{#scientific_links.orcid}),
                          cast_text(:#{#scientific_links.researcherid}),
                          cast_text(:#{#scientific_links.googleScholarId}),
                          cast_text(:#{#scientific_links.scopusAuthorId}),
                          cast_int(:id_scientific_links),
                          _orcid => cast_text(:orcid),
                          _researcherid => cast_text(:researcherid),
                          _google_scholar_id => cast_text(:google_scholar_id),
                          _scopus_author_id => cast_text(:scopus_author_id))).*""",
            nativeQuery = true)
    fun update(
            @Param("scientific_links") newScientificLinks: ScientificLinks?,
            @Param("id_scientific_links") idScientificLinks: Int?,
            @Param("orcid") orcid: String?,
            @Param("researcherid") researcherid: String?,
            @Param("google_scholar_id") googleScholarId: String?,
            @Param("scopus_author_id") scopusAuthorId: String?
    ): ScientificLinks?

    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_update(
                          cast_text(:#{#scientific_links.orcid}),
                          cast_text(:#{#scientific_links.researcherid}),
                          cast_text(:#{#scientific_links.googleScholarId}),
                          cast_text(:#{#scientific_links.scopusAuthorId}),
                          _id_user => cast_int(:#{#user.idUser}))).*""",
            nativeQuery = true)
    fun updateByUser(
            @Param("scientific_links") newScientificLinks: ScientificLinks?,
            @Param("user") user: User?
    ): ScientificLinks?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_delete(
                          cast_int(:id_scientific_links),
                          cast_text(:orcid),
                          cast_text(:researcherid),
                          cast_text(:google_scholar_id),
                          cast_text(:scopus_author_id))).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_scientific_links") idScientificLinks: Int?,
            @Param("orcid") orcid: String?,
            @Param("researcherid") researcherid: String?,
            @Param("google_scholar_id") googleScholarId: String?,
            @Param("scopus_author_id") scopusAuthorId: String?
    ): ScientificLinks?

    //language=PostgresPLSQL
    @Query(value = "select (scientific_links_delete(_id_user => cast_int(:#{#user.idUser}))).*",
            nativeQuery = true)
    fun deleteByUser(@Param("user") user: User?): ScientificLinks?

}
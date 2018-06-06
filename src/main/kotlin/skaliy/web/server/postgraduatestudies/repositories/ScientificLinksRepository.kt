package skaliy.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skaliy.web.server.postgraduatestudies.entities.User
import skaliy.web.server.postgraduatestudies.entities.ScientificLinks


@Repository
interface ScientificLinksRepository : JpaRepository<ScientificLinks, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_record(
                          cast_int(:id_scientific_links),
                          cast_int(:#{#user.idUser}),
                          cast_text(:orcid),
                          cast_text(:researcherid),
                          cast_text(:google_scholar_id),
                          cast_text(:scopus_author_id)
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("id_scientific_links") idScientificLinks: Int? = null,
            @Param("orcid") orcid: String? = null,
            @Param("researcherid") researcherid: String? = null,
            @Param("google_scholar_id") googleScholarId: String? = null,
            @Param("scopus_author_id") scopusAuthorId: String? = null,
            @Param("user") user: User? = User()
    ): ScientificLinks

    //language=PostgresPLSQL
    @Query(value = "select (scientific_links_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<ScientificLinks>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_insert(
                          cast_text(:orcid),
                          cast_text(:researcherid),
                          cast_text(:google_scholar_id),
                          cast_text(:scopus_author_id)
                      )).*""",
            nativeQuery = true)
    fun add(
            @Param("orcid") orcid: String?,
            @Param("researcherid") researcherid: String?,
            @Param("google_scholar_id") googleScholarId: String?,
            @Param("scopus_author_id") scopusAuthorId: String?
    ): ScientificLinks


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_update(
                          cast_text(:#{#scientific_links.orcid}),
                          cast_text(:#{#scientific_links.researcherid}),
                          cast_text(:#{#scientific_links.googleScholarId}),
                          cast_text(:#{#scientific_links.scopusAuthorId}),
                          cast_int(:id_scientific_links)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("scientific_links") newScientificLinks: ScientificLinks,
            @Param("id_scientific_links") idScientificLinks: Int
    ): ScientificLinks


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (scientific_links_delete(
                          cast_int(:id_scientific_links),
                          cast_int(:#{#user.idUser}),
                          cast_text(:orcid),
                          cast_text(:researcherid),
                          cast_text(:google_scholar_id),
                          cast_text(:scopus_author_id)
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_scientific_links") idScientificLinks: Int? = null,
            @Param("orcid") orcid: String? = null,
            @Param("researcherid") researcherid: String? = null,
            @Param("google_scholar_id") googleScholarId: String? = null,
            @Param("scopus_author_id") scopusAuthorId: String? = null,
            @Param("user") user: User? = User()
    ): ScientificLinks

}
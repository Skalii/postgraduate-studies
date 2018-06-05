package skaliy.web.server.postgraduatestudies.entities


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.OneToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import skaliy.web.server.postgraduatestudies.views.View


@Entity(name = "ScientificLinks")
@SequenceGenerator(
        name = "scientific_links_seq",
        sequenceName = "scientific_links_id_scientific_links_seq",
        schema = "public",
        allocationSize = 1)
@Table(name = "scientific_links",
        schema = "public",
        indexes = [
            Index(name = "scientific_links_pkey",
                    columnList = "id_scientific_links",
                    unique = true),
            Index(name = "scientific_links_id_scientific_links_uindex",
                    columnList = "id_scientific_links",
                    unique = true),
            Index(name = "scientific_links_orcid_uindex",
                    columnList = "orcid",
                    unique = true),
            Index(name = "scientific_links_researcherid_uindex",
                    columnList = "researcherid",
                    unique = true),
            Index(name = "scientific_links_google_scholar_id_uindex",
                    columnList = "google_scholar_id",
                    unique = true),
            Index(name = "scientific_links_scopus_author_id_uindex",
                    columnList = "scopus_author_id",
                    unique = true)])
data class ScientificLinks(

        @Column(name = "id_scientific_links",
                nullable = false)
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "scientific_links_seq")
        @Id
        @JsonProperty(value = "id_scientific_links")
        @JsonView(View.REST::class)
        @NotNull
        val idScientificLinks: Int,

        @Size(max = 100)
        @JsonProperty(value = "orcid")
        @JsonView(View.REST::class)
        @Column(name = "orcid",
                length = 100)
        val orcid: String? = "Інформація відсутня",

        @Column(name = "researcherid",
                length = 100)
        @JsonProperty(value = "researcherid")
        @JsonView(View.REST::class)
        @Size(max = 100)
        val researcherid: String? = "Інформація відсутня",

        @Column(name = "google_scholar_id",
                length = 100)
        @JsonProperty(value = "google_scholar_id")
        @JsonView(View.REST::class)
        @Size(max = 100)
        val googleScholarId: String? = "Інформація відсутня",

        @Column(name = "scopus_author_id",
                length = 100)
        @JsonProperty(value = "scopus_author_id")
        @JsonView(View.REST::class)
        @Size(max = 100)
        val scopusAuthorId: String? = "Інформація відсутня"

) {

    @JsonIgnoreProperties(value = ["scientific_links", "students", "sections"])
    @JsonProperty(value = "user")
    @JsonView(View.TREE::class)
    @OneToOne(
            targetEntity = User::class,
            fetch = FetchType.LAZY,
            mappedBy = "scientificLinks")
    var user: User? = null


    constructor() : this(
            0,
            "Інформація відсутня",
            "Інформація відсутня",
            "Інформація відсутня",
            "Інформація відсутня")

    constructor(
            idScientificLinks: Int,
            orcid: String?,
            researcherid: String?,
            googleScholarId: String?,
            scopusAuthorId: String?,
            user: User?
    ) : this(
            idScientificLinks,
            orcid,
            researcherid,
            googleScholarId,
            scopusAuthorId
    ) {
        this.user = user
    }

}

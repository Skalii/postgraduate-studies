package skalii.web.server.postgraduatestudies.entities


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.SEQUENCE
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.OneToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import skalii.web.server.postgraduatestudies.views.View


@Entity(name = "ContactInfo")
@SequenceGenerator(
        name = "contact_info_seq",
        sequenceName = "contact_info_id_contact_info_seq",
        schema = "public",
        allocationSize = 1)
@Table(name = "contact_info",
        schema = "public",
        indexes = [
            Index(name = "contact_info_pkey",
                    columnList = "id_contact_info",
                    unique = true),
            Index(name = "contact_info_id_contact_info_uindex",
                    columnList = "id_contact_info",
                    unique = true),
            Index(name = "contact_info_phone_number_uindex",
                    columnList = "phone_number",
                    unique = true),
            Index(name = "contact_info_email_uindex",
                    columnList = "email",
                    unique = true)])
data class ContactInfo(

        @Column(name = "id_contact_info",
                nullable = false)
        @GeneratedValue(
                strategy = SEQUENCE,
                generator = "contact_info_seq")
        @Id
        @get:JsonProperty(value = "id_contact_info")
        @JsonView(View.REST::class)
        @NotNull
        val idContactInfo: Int = 0,

        @Column(name = "phone_number",
                nullable = false,
                length = 100)
        @get:JsonProperty(value = "phone_number")
        @JsonView(View.REST::class)
        @NotNull
        @Size(max = 100)
        val phoneNumber: String = "",

        @Column(name = "email",
                nullable = false,
                length = 150)
        @get:JsonProperty(value = "email")
        @JsonView(View.REST::class)
        @NotNull
        @Size(max = 150)
        val email: String = "",

        @Column(name = "address",
                length = 200)
        @JsonProperty(value = "address")
        @JsonView(View.REST::class)
        @Size(max = 200)
        val address: String? = null

) {

    @JsonIgnoreProperties(value = ["contact_info", "students", "sections"])
    @get:JsonProperty(value = "user")
    @JsonView(View.TREE::class)
    @OneToOne(
            targetEntity = User::class,
            fetch = LAZY,
            mappedBy = "contactInfo")
    lateinit var user: User


    constructor() : this(
            0,
            "Невідомий номер телефону",
            "Невідомий email",
            null
    )

    constructor(
            idContactInfo: Int = 0,
            phoneNumber: String = "Невідомий номер телефону",
            email: String = "Невідомий email",
            address: String? = null,
            user: User = User()
    ) : this(
            idContactInfo,
            phoneNumber,
            email,
            address
    ) {
        this.user = user
    }

}
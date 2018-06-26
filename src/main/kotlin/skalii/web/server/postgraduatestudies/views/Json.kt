package skalii.web.server.postgraduatestudies.views


import com.fasterxml.jackson.databind.ObjectMapper

import skalii.web.server.postgraduatestudies.views.View.*


/**
 * Contains static functions that create objects of the JSON format.
 */
class Json {

    companion object {

        /**
         * The function of converting any incoming object to the JSON object format
         *
         * with/without considering hierarchy of relations between entities.
         *
         * @param view choice of level of hierarchy from a class [View]:
         * - "-tree" for the construction of object of tree of dependencies;
         * - "other" for the construction of object 'as he is'
         * @param any object for which JSON will be built
         * @return the string representation of the JSON object
         * @see [REST][skalii.web.server.postgraduatestudies.views.View.REST]
         * @see [TREE][skalii.web.server.postgraduatestudies.views.View.TREE]
         */
        fun get(
                view: String,
                any: Any?
        ) =
                when (view) {
                    "-tree" -> ObjectMapper()
                            .writerWithView(TREE::class.java)
                            .writeValueAsString(any)!!
                    else -> ObjectMapper()
                            .writerWithView(REST::class.java)
                            .writeValueAsString(any)!!
                }

        /**
         * The function of converting the [User][skalii.web.server.postgraduatestudies.entities.User] object
         * to the JSON object format.
         *
         * The data is constructed taking into account the role of the user.
         *
         * @see get
         * @see [STUDENT][skalii.web.server.postgraduatestudies.views.View.STUDENT]
         * @see [INSTRUCTOR][skalii.web.server.postgraduatestudies.views.View.INSTRUCTOR]
         */
        fun getUser(
                view: String,
                any: Any?
        ) =
                when (view) {
                    "-tree" -> ObjectMapper()
                            .writerWithView(
                                    if (any.toString().contains("Аспірантура")) {
                                        TREE::class.java
                                    } else if (any.toString().contains("Керівник")) {
                                        INSTRUCTOR_TREE::class.java
                                    } else if (any.toString().contains("Аспірант") ||
                                            any.toString().contains("Докторант")) {
                                        STUDENT_TREE::class.java
                                    } else {
                                        TREE::class.java
                                    }
                            )
                            .writeValueAsString(any)!!
                    else -> ObjectMapper()
                            .writerWithView(
                                    if (any.toString().contains("Аспірантура")) {
                                        REST::class.java
                                    } else if (any.toString().contains("Керівник")) {
                                        INSTRUCTOR::class.java
                                    } else if (any.toString().contains("Аспірант") ||
                                            any.toString().contains("Докторант")) {
                                        STUDENT::class.java
                                    } else {
                                        REST::class.java
                                    }
                            )
                            .writeValueAsString(any)!!
                }

    }

}
package skaliy.web.server.postgraduatestudies.views


import com.fasterxml.jackson.databind.ObjectMapper

import skaliy.web.server.postgraduatestudies.views.View.*


class Json {

    companion object {

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
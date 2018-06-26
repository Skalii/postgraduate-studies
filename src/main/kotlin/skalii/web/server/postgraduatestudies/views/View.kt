package skalii.web.server.postgraduatestudies.views


/**
 * A class whose members are used to fine-tune the display
 *
 * of certain properties of data classes annotated by [JsonView][com.fasterxml.jackson.annotation.JsonView].
 */
class View {

    /**
     * Display of all entity data, and:
     * - includes a record id;
     * - excludes deep dependencies.
     */
    interface REST
    /**
     * Too, that and [REST], except:
     * - includes deep dependencies.
     *
     * @see REST
     */
    interface TREE : REST

    /**
     * View for users of graduate students and doctoral studies.
     *
     * Almost the same as that of the [REST],
     *
     * except more personalized data found only among [User][skalii.web.server.postgraduatestudies.entities.User]s.
     *
     * @see REST
     */
    interface STUDENT : REST
    /**
     * Too, that an [TREE], only by analogy [STUDENT]
     *
     * @see STUDENT
     * @see TREE
     */
    interface STUDENT_TREE : STUDENT

    /**
     * View for users of instructors and administrators by analogy [STUDENT].
     *
     * @see STUDENT
     * @see REST
     */
    interface INSTRUCTOR : REST

    /**
     * Too, that an [TREE], only by analogy [INSTRUCTOR]
     *
     * @see INSTRUCTOR
     * @see TREE
     */
    interface INSTRUCTOR_TREE : INSTRUCTOR

}
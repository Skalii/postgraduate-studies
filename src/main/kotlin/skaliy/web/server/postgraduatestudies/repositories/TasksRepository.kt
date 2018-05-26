package skaliy.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skaliy.web.server.postgraduatestudies.entities.Section
import skaliy.web.server.postgraduatestudies.entities.Task
import skaliy.web.server.postgraduatestudies.entities.User


@Repository
interface TasksRepository : JpaRepository<Task, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (task_record(
                          cast_int(:id_task),
                          cast_int(:#{#section.idSection}),
                          cast_int(:#{#user.idUser}),
                          cast_int(:number),
                          cast_text(:title)
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("id_task") idTask: Int? = null,
            @Param("section") section: Section? = Section(),
            @Param("user") user: User? = User(),
            @Param("number") number: Int? = null,
            @Param("title") title: String? = null
    ): Task?

    //language=PostgresPLSQL
    @Query(value = """select (task_record(
                          _id_section => cast_int(:#{#section.idSection}),
                          _id_user => cast_int(:#{#user.idUser}),
                          all_records => true
                      )).*""",
            nativeQuery = true)
    fun getAll(
            @Param("section") section: Section? = Section(),
            @Param("user") user: User? = User()
    ): MutableList<Task>?


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (task_insert(
                          cast_int(:#{#task.section.idSection}),
                          cast_int(:#{#task.number}),
                          cast_text(:#{#task.title}),
                          cast_ts(:#{#task.balkline} :: timestamp),
                          cast_ts(:#{#task.deadline} :: timestamp),
                          cast_text(:#{#task.link})
                      )).*""",
            nativeQuery = true)
    fun add(@Param("task") task: Task?): Task?


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (task_update(
                          cast_int(:#{#task.number}),
                          cast_text(:#{#task.title}),
                          cast_ts(:#{#task.balkline} :: timestamp),
                          cast_ts(:#{#task.deadline} :: timestamp),
                          cast_bool(:#{#task.markDoneUser} :: timestamp),
                          cast_bool(:#{#task.markDoneInstructor} :: timestamp),
                          cast_text(:#{#task.link}),
                          cast_ts(:#{#task.timestampDoneUser} :: timestamp),
                          cast_ts(:#{#task.timestampDoneInstructor} :: timestamp),
                          cast_int(:id_task),
                          cast_int(:#{#section.idSection}),
                          cast_int(:#{#user.idUser}),
                          cast_int(:number),
                          cast_text(:title)
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("task") newTask: Task?,
            @Param("id_task") idTask: Int? = null,
            @Param("section") section: Section? = Section(),
            @Param("user") user: User? = User(),
            @Param("number") number: Int? = null,
            @Param("title") title: String? = null
    ): Task?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (task_delete(
                          cast_int(:id_task),
                          cast_int(:#{#section.idSection}),
                          cast_int(:number),
                          cast_text(:title)
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_task") idTask: Int? = null,
            @Param("section") section: Section? = Section(),
            @Param("number") number: Int? = null,
            @Param("title") title: String? = null
    ): Task?

    //language=PostgresPLSQL
    @Query(value = """select (task_delete(
                          _id_section => cast_int(:#{#section.idSection}),
                          all_from_section => true
                      )).*""",
            nativeQuery = true)
    fun deleteAll(@Param("section") section: Section? = Section()): MutableList<Task>?

}
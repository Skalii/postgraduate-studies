package skalii.web.server.postgraduatestudies.repositories


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import skalii.web.server.postgraduatestudies.entities.Section
import skalii.web.server.postgraduatestudies.entities.Task


@Repository
interface TasksRepository : JpaRepository<Task, Int> {


    /** ============================== GET / SELECT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (task_record(
                          cast_int(:id_task),
                          cast_int(:#{#section.idSection}),
                          cast_int(:id_user),
                          cast_int(:task_number),
                          cast_text(:task_title),
                          cast_int(:#{#section.number}),
                          cast_text(:#{#section.title})
                      )).*""",
            nativeQuery = true)
    fun get(
            @Param("section") section: Section? = Section(),
            @Param("id_user") idUser: Int? = null,
            @Param("task_number") number: Int? = null,
            @Param("task_title") title: String? = null,
            @Param("id_task") idTask: Int? = null
    ): Task

    //language=PostgresPLSQL
    @Query(value = """select (task_record(
                          _id_section => cast_int(:id_section),
                          _id_user => cast_int(:id_user),
                          all_records => true
                      )).*""",
            nativeQuery = true)
    fun getAll(
            @Param("id_section") idSection: Int? = null,
            @Param("id_user") idUser: Int? = null
    ): MutableList<Task>


    /** ============================== ADD / INSERT INTO ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (task_insert(
                          cast_int(:id_section),
                          cast_text(:title),
                          cast_ts(:balkline),
                          cast_ts(:deadline),
                          cast_text(:link)
                      )).*""",
            nativeQuery = true)
    fun add(
            @Param("id_section") idSection: Int,
            @Param("title") title: String?,
            @Param("balkline") balkline: String?,
            @Param("deadline") deadline: String?,
            @Param("link") link: String?
    ): Task


    /** ============================== SET / UPDATE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (task_update(
                          cast_int(:#{#task.number}),
                          cast_text(:#{#task.title}),
                          cast_ts(:#{#task.balkline.toInstant().toString()}),
                          cast_ts(:#{#task.deadline.toInstant().toString()}),
                          cast_bool(:#{#task.markDoneUser}),
                          cast_bool(:#{#task.markDoneInstructor}),
                          cast_text(:#{#task.link}),
                          cast_int(:id_task),
                          cast_int(:#{#section.idSection}),
                          cast_int(:id_user),
                          cast_int(:task_number),
                          cast_text(:task_title),
                          cast_int(:#{#section.number}),
                          cast_text(:#{#section.title})
                      )).*""",
            nativeQuery = true)
    fun set(
            @Param("task") newTask: Task?,
            @Param("section") section: Section? = Section(),
            @Param("id_user") idUser: Int? = null,
            @Param("task_number") number: Int? = null,
            @Param("task_title") title: String? = null,
            @Param("id_task") idTask: Int? = null
    ): Task

    //language=PostgresPLSQL
    @Query(value = """select (task_update(
                          new_mark_done_instructor => cast_bool(:mark_done_instructor),
                          _id_task => cast_int(:id_task)
                      )).*""",
            nativeQuery = true)
    fun setMarkInstructor(
            @Param("mark_done_instructor") markDoneInstructor: Boolean?,
            @Param("id_task") idTask: Int
    ): Task


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (task_delete(
                          cast_int(:id_task),
                          cast_int(:id_section),
                          cast_int(:number),
                          cast_text(:title)
                      )).*""",
            nativeQuery = true)
    fun delete(
            @Param("id_section") idSection: Int? = null,
            @Param("number") number: Int? = null,
            @Param("title") title: String? = null,
            @Param("id_task") idTask: Int? = null
    ): Task

    //language=PostgresPLSQL
    @Query(value = """select (task_delete(
                          _id_section => cast_int(:#{#section.idSection}),
                          all_from_section => true
                      )).*""",
            nativeQuery = true)
    fun deleteAll(@Param("section") section: Section?): MutableList<Task>

}
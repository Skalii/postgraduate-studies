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


    /** ============================== GET ============================== */


    //language=PostgresPLSQL
    @Query(value = "select (task_record(cast_int(:id_task))).*",
            nativeQuery = true)
    fun get(@Param("id_task") idTask: Int?): Task?

    //language=PostgresPLSQL
    @Query(value = "select (task_record(all_records => true)).*",
            nativeQuery = true)
    fun getAll(): MutableList<Task>?

    //language=PostgresPLSQL
    @Query(value = """select (task_record(
                          _id_section => cast_int(:#{#section.idSection}),
                          _number => cast_int(:number),
                          _title => cast_text(:title))).*""",
            nativeQuery = true)
    fun getBySection(
            @Param("section") section: Section?,
            @Param("number") number: Int?,
            @Param("title") title: String?
    ): Task?

    //language=PostgresPLSQL
    @Query(value = """select (task_record(
                          _id_section => :#{#section.idSection},
                          all_records => true)).*""",
            nativeQuery = true)
    fun getAllBySection(@Param("section") section: Section?): MutableList<Task>?

    //language=PostgresPLSQL
    @Query(value = """select (task_record(
                          _id_user => cast_int(:#{#user.idUser}),
                          _number => cast_int(:number),
                          _title => cast_text(:title))).*""",
            nativeQuery = true)
    fun getByUser(
            @Param("user") user: User?,
            @Param("number") number: Int?,
            @Param("title") title: String?
    ): Task?

    //language=PostgresPLSQL
    @Query(value = """select (task_record(
                          _id_user => :#{#user.idUser},
                          all_records => true)).*""",
            nativeQuery = true)
    fun getAllByUser(@Param("user") user: User?): MutableList<Task>?


    /** ============================== INSERT ============================== */


    //language=PostgresPLSQL
    @Query(value = """select (task_insert(
                          cast_int(:#{#task.section.idSection}),
                          cast_int(:#{#task.number}),
                          cast_text(:#{#task.title}),
                          cast_ts(:#{#task.balkline} :: timestamp),
                          cast_ts(:#{#task.deadline} :: timestamp),
                          cast_text(:#{#task.link}))).*""",
            nativeQuery = true)
    fun create(@Param("task") task: Task?): Task?


    /** ============================== UPDATE ============================== */


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
                          cast_int(:id_task))).*""",
            nativeQuery = true)
    fun update(
            @Param("task") newTask: Task?,
            @Param("id_task") idTask: Int?
    ): Task?

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
                          _id_section => cast_int(:#{#section.idSection}),
                          _number => :number,
                          _title => :title)).*""",
            nativeQuery = true)
    fun updateBySection(
            @Param("task") newTask: Task?,
            @Param("section") section: Section?,
            @Param("number") number: Int?,
            @Param("title") title: String?
    ): Task?

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
                          _id_user => cast_int(:#{#user.idUser}),
                          _number => cast_int(:number),
                          _title => cast_text(:title))).*""",
            nativeQuery = true)
    fun updateByUser(
            @Param("task") newTask: Task?,
            @Param("user") user: User?,
            @Param("number") number: Int?,
            @Param("title") title: String?
    ): Task?


    /** ============================== DELETE ============================== */


    //language=PostgresPLSQL
    @Query(value = "select (task_delete(cast_int(:id_task))).*",
            nativeQuery = true)
    fun delete(@Param("id_task") idTask: Int?): Task?

    //language=PostgresPLSQL
    @Query(value = """select (task_delete(
                          _id_section => cast_int(:#{#section.idSection}),
                          _number => cast_int(:number),
                          _title => cast_text(:title))).*""",
            nativeQuery = true)
    fun deleteBySection(
            @Param("section") section: Section?,
            @Param("number") number: Int?,
            @Param("title") title: String?
    ): Task?

    //language=PostgresPLSQL
    @Query(value = """select (task_delete(
                          cast_int(:#{#section.idSection}),
                          all_from_section => true )).*""",
            nativeQuery = true)
    fun deleteAllBySection(@Param("section") section: Section?): MutableList<Task>?

}
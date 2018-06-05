package skaliy.web.server.postgraduatestudies.views


class View {

    interface REST
    interface TREE : REST

    interface STUDENT : REST
    interface STUDENT_TREE : STUDENT

    interface INSTRUCTOR : REST
    interface INSTRUCTOR_TREE : INSTRUCTOR

}
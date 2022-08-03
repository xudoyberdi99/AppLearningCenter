package com.company.entity.enums;

public enum Permissions {
    ADD_USER, //ADMIN
    EDIT_USER,//ADMIN,user
    DELETE_USER,//ADMIN,USER
    VIEW_USERS,//ADMIN,
    ADD_ROLE,//ADMIN
    EDIT_ROLE,//ADMIN
    DELETE_ROLE,//ADMIN
    VIEW_ROLES,//ADMIN
    ADD_COURSE,//ADMIN
    EDIT_COURSE,//ADMIN
    DELETE_COURSE,//ADMIN
    VIEW_COURSES,//ADMIN,USER,TEACHER,STUDENT
    ADD_DAY,//ADMIN
    EDIT_DAY,//ADMIN
    DELETE_DAY,//ADMIN
    VIEW_DAYS,//ADMIN
    ADD_GROUP,//ADMIN,TEACHER
    EDIT_GROUP,//ADMIN,TEACHER
    DELETE_GROUP,//ADMIN,TEACHER
    VIEW_GROUPS,//ADMIN,TEACHER,STUDENT
    ADD_PAYMENT,//USER,STUDENT
    DELETE_PAYMENT,//ADMIN
    VIEW_PAYMENTS,//ADMIN
    ADD_ROOM,//ADMIN
    EDIT_ROOM,//ADMIN
    DELETE_ROOM,//ADMIN
    VIEW_ROOM,//ADMIN,TEACHER,STUDENT
    ADD_STATUS,//ADMIN,USER,STUDENT
    EDIT_STATUS,//ADMIN,USER,STUDENT
    DELETE_MY_STATUS,//ADMIN,USER,STUDENT
    VIEW_STATUS,//ADMIN,USER,TEACHER,STUDENT
    ADD_STUDENT,//ADMIN TEACHER
    EDIT_STUDENT,//ADMIN,STUDENT
    DELETE_STUDENT,//ADMIN,TEACHER,STUDENT
    VIEW_STUDENT,//ADMIN,TEACHER,
    ADD_TEACHER,//ADMIN
    EDIT_TEACHER,//ADMIN,TEACHER
    DELETE_TEACHER,//ADMIN,TEACHER
    VIEW_TEACHER,//ADMIN
    ADD_TIME_TABLE,//ADMIN
    EDIT_TIME_TABLE,//ADMIN
    DELETE_TIME_TABLE,//ADMIN
    VIEW_TIME_TABLE,//ADMIN
}

package com.nhnacademy.gateway.service;

public interface TaskService {


    void registerTask(Integer projectNo, String id, String title, String content);

    void modifyTask(Integer taskNo, String title, String content);

    void deleteTask(Integer taskNo);

}

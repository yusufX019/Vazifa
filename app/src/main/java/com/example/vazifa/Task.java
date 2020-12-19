package com.example.vazifa;

//Класс в котором будет хранится данные о задачах


public class Task {
    private int id;                 // Идентификатор
    private String name;            // Название задачи
    private String description;     // Описание задачи
    private String date;            // Дата когда задача было создана

    public Task(int id,String name, String description, String date) {
        this.id =id;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return name;
    }

    public Task(){};

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}

package com.example.vazifa;

//Класс в котором будет хранится данные о задачах


public class Task {
    private int id;                 // Идентификатор
    private String name;            // Название задачи
    private String description;     // Описание задачи
    private String date;            // Дата когда задача было создана
    private boolean completed;



    public Task(int id, String name, String description, String date,boolean completed) {
        this.id =id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.completed=completed;
    }

    //second constructor that does nothing
    public Task(){};

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }


    public void setId(int id) {
        this.id = id;
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

    public boolean isCompleted() {
        return completed;
    }

    public void changeState(){
        this.completed=!this.completed;
    }

}

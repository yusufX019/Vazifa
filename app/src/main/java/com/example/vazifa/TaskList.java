package com.example.vazifa;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task>   completed=new ArrayList<>();
    private List<Task> uncompleted=new ArrayList<>();


    //
    public TaskList(Context context){
        for(Task task:(new DataBase(context)).getAll()){
            if(task.isCompleted()) completed.add(task);
            else uncompleted.add(task);
        }
    }

    public List<Task> getAsSorted(){
        List<Task> newList=new ArrayList<>(uncompleted);
        newList.addAll(completed);
        return newList;
    }


    // Этот код можут вызвать некоторые проблемы
    public List<Task> getCompleted()   {return completed;}
    public List<Task> getUncompleted() {return uncompleted;}

    public int getUncompletedPosition(){
        return uncompleted.size();
    }

    public int getCompletedPosition(){
        return completed.size()+uncompleted.size();
    }


}

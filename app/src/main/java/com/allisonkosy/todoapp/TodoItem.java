package com.allisonkosy.todoapp;

public class TodoItem {

    private String title;
    private String description;
    private Boolean completed = false;
    private Boolean deleteIt = false;

    public TodoItem(String title, String description) {
        this.description = description;

        this.title = title;


    }
    public TodoItem(String title, String description, Boolean completed) {
        this.title = title;
        this.completed = completed;
        this.description = description;
    }


    public String getTitle() {
        return title;

    }

    public String getDescription() {
        return  description;

    }

    public  Boolean getCompleted() {
        return completed;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public void setDescription(String newDescription) {
        description = newDescription;
    }

    public void toggleCompleted() {
        completed = !completed;

    }
    public void toggleDeleteIt() {
        deleteIt = !deleteIt;
    }

    public void setDeleteIt(Boolean deleteIt) {
        this.deleteIt = deleteIt;

    }
    public Boolean getDeleteIt() {
        return deleteIt;

    }
}

package pl.coderslab.model;

import java.sql.*;
import java.time.LocalDate;

public class Solution {

    private int id;
    private Date created;
    private Date updated;
    private String description;
    private int exercise_id;
    private long user_id;

    public Solution() {
        this.created = Date.valueOf(LocalDate.now());
    }

    public Solution(String description) {
        this.created = Date.valueOf(LocalDate.now());
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

}

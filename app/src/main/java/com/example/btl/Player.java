package com.example.btl;

public class Player {
    private String _id;
    private String name;
    private String score;

    public Player(String _id, String name, String score) {
        this._id = _id;
        this.name = name;
        this.score = score;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

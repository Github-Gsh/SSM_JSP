package org.example.entity;

public class Project {

    private String name;
    private String project;
    private String scores;
    private String suser;
    private String ouser;
    private String state;

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", project='" + project + '\'' +
                ", scores='" + scores + '\'' +
                ", suser='" + suser + '\'' +
                ", ouser='" + ouser + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public String getSuser() {
        return suser;
    }

    public void setSuser(String suser) {
        this.suser = suser;
    }

    public String getOuser() {
        return ouser;
    }

    public void setOuser(String ouser) {
        this.ouser = ouser;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

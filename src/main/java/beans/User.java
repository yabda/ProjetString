package beans;


import org.hibernate.annotations.Proxy;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;

@Entity
public class User {

    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @OneToMany(mappedBy = "belongUser",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Answer> answers=new HashSet<>();

    @OneToMany(mappedBy = "belongUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Message> messages=new HashSet<>();

    @ManyToMany(mappedBy = "usersParticipation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(columnDefinition = "participe")
    private Set<Project> participeProjects=new HashSet<>();

    @OneToMany(mappedBy = "belongUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(columnDefinition = "create")
    private Set<Project> createdProjects=new HashSet<>();

    @ElementCollection
    private Map<Project,Float> participation;

    //CONSTRUCTORS
    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.createdAt = new Date();
    }

    public User(String name, String password, Date updatedAt, Date createdAt) {
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

//GETTERS & SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<Project> getParticipeProjects() {
        return participeProjects;
    }

    public void setParticipeProjects(Set<Project> participeProjects) {
        this.participeProjects = participeProjects;
    }

    public Set<Project> getCreatedProjects() {
        return createdProjects;
    }

    public void setCreatedProjects(Set<Project> createdProjects) {
        this.createdProjects = createdProjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(createdAt, user.createdAt) &&
                Objects.equals(updatedAt, user.updatedAt) &&
                Objects.equals(answers, user.answers) &&
                Objects.equals(messages, user.messages) &&
                Objects.equals(participeProjects, user.participeProjects) &&
                Objects.equals(createdProjects, user.createdProjects) &&
                Objects.equals(participation, user.participation);
    }
}

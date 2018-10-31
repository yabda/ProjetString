package beans;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
public class Project implements Comparable<Project>{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "goal")
    private int goal;

    @Column(name = "current")
    private int current;

    @Column(name = "deadLine")
    private Date deadLine;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "failed")
    private boolean failed;


    @OneToOne
    private User belongUser;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<User> usersParticipation=new HashSet<>();

    @OneToMany
    private Set<Message> messages=new HashSet<>();

    @OneToMany
    private Set<Counterpart> counterparts=new HashSet<>();

    @OneToOne
    private Category category;

    @ElementCollection
    private Map<User,Float> participations;

    public Project() {
    }

    public Project(String title, String description, int goal, Date deadLine) {
        this.title = title;
        this.description = description;
        this.goal = goal;
        this.deadLine = deadLine;

        this.createdAt = new Date();
        this.failed = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Map<User, Float> getParticipations() {
        return participations;
    }

    public void setParticipations(Map<User, Float> participations) {
        this.participations = participations;
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

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public User getBelongUser() {
        return belongUser;
    }

    public void setBelongUser(User belongUser) {
        this.belongUser = belongUser;
    }

    public Set<User> getUsersParticipation() {
        return usersParticipation;
    }

    public void setUsersParticipation(Set<User> usersParticipation) {
        this.usersParticipation = usersParticipation;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<Counterpart> getCounterparts() {
        return counterparts;
    }

    public void setCounterparts(Set<Counterpart> counterparts) {
        this.counterparts = counterparts;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int compareTo(Project p) {
        return (p.createdAt.after(this.createdAt))?1:0;
    }

}

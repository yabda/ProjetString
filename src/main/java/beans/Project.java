package beans;



import javax.persistence.*;
import java.util.*;

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


    @ManyToOne
    private User belongUser;

    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    private Set<User> usersParticipation=new HashSet<>();

    @OneToMany(mappedBy = "belongProject")
    private Set<Message> messages=new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    private Set<Counterpart> counterparts=new HashSet<>();

    @ManyToOne
    private Category category;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Integer,Float> participations;

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

    public Map<Integer, Float> getParticipations() {
        return participations;
    }

    public void setParticipations(Map<Integer, Float> participations) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id &&
                goal == project.goal &&
                current == project.current &&
                failed == project.failed &&
                Objects.equals(title, project.title) &&
                Objects.equals(description, project.description) &&
                Objects.equals(deadLine, project.deadLine) &&
                Objects.equals(createdAt, project.createdAt) &&
                Objects.equals(updatedAt, project.updatedAt) &&
                Objects.equals(belongUser, project.belongUser) &&
                Objects.equals(usersParticipation, project.usersParticipation) &&
                Objects.equals(messages, project.messages) &&
                Objects.equals(counterparts, project.counterparts) &&
                Objects.equals(category, project.category) &&
                Objects.equals(participations, project.participations);
    }
}

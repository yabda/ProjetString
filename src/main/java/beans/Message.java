package beans;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;


    @OneToOne(mappedBy = "belongMessage",cascade = CascadeType.ALL)
    private Answer belongAnswer;

    @ManyToOne
    private User belongUser;

    @ManyToOne
    private Project belongProject;

    public Message() {
    }

    public Message(String content) {
        this.content = content;
    }

    public Message(User u, Project p, String content){
        this.setBelongUser(u);
        this.setBelongProject(p);
        this.setContent(content);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Answer getBelongAnswer() {
        return belongAnswer;
    }

    public void setBelongAnswer(Answer belongAnswer) {
        this.belongAnswer = belongAnswer;
    }

    public User getBelongUser() {
        return belongUser;
    }

    public void setBelongUser(User belongUser) {
        this.belongUser = belongUser;
    }

    public Project getBelongProject() {
        return belongProject;
    }

    public void setBelongProject(Project belongProject) {
        this.belongProject = belongProject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                Objects.equals(content, message.content) &&
                Objects.equals(belongAnswer, message.belongAnswer) &&
                Objects.equals(belongUser, message.belongUser) &&
                Objects.equals(belongProject, message.belongProject);
    }
}

package beans;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;


    @OneToOne
    private Answer belongAnswer;

    @OneToOne
    private User belongUser;

    @OneToOne
    private Project belongProject;

    public Message() {
    }

    public Message(String content) {
        this.content = content;
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
}

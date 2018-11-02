package beans;

import javax.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    private User belongUser;

    @ManyToOne
    private Message belongMessage;

//CONSTRUCTORS
    public Answer(String content) {
        this.content = content;
    }

    public Answer() {
    }

//GETTERS & SETTERS

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

    public User getBelongUser() {
        return belongUser;
    }

    public void setBelongUser(User belongUser) {
        this.belongUser = belongUser;
    }

    public Message getBelongMessage() {
        return belongMessage;
    }

    public void setBelongMessage(Message belongMessage) {
        this.belongMessage = belongMessage;
    }
}

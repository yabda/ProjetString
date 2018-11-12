package beans;

import javax.persistence.*;
import java.util.Objects;

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

    public Answer(String content, Message m, User u){
        this.content = content;
        this.belongMessage = m;
        this.belongUser = u;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id &&
                Objects.equals(content, answer.content) &&
                Objects.equals(belongUser, answer.belongUser) &&
                Objects.equals(belongMessage, answer.belongMessage);
    }
}

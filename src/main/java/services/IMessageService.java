package services;

import beans.Message;
import beans.Project;
import beans.User;

public interface IMessageService extends ICRUDService<Message> {

    void sendMsg(User u, Project p, String content);
    void answerMsg(Project p, String content, Message m);
}

package services;

import beans.Message;
import beans.Project;
import beans.User;

public interface IMessageService extends CRUDService<Message> {

    public int sendMsg(User u, Project p, String content);
    public int answerMsg(Project p, String content, Message m);

}

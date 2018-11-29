package services;

import beans.Answer;
import beans.Message;
import beans.Project;
import beans.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Service("messageService")
public class MessageService implements IMessageService {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Resource(name = "projectService")
    private IProjectService pS;

    @Resource(name = "userService")
    private IUserService uS;

    @Override
    public List<Message> findAll() {
        Query q = em.createQuery("select m from Message m ");
        return q.getResultList();
    }

    @Override
    public List<Message> findAll(int limit) {
        Query q = em.createQuery("select m from Message m");
        q.setMaxResults(limit);
        return q.getResultList();
    }

    @Override
    public void insert(Message user) {
        em.clear();
        em.persist(user);
    }


    @Override
    public Message getFromId(int id) {
        Query q = em.createQuery("select m from Message m where m.id = :id");
        q.setParameter("id", id);
        q.setMaxResults(1);
        List<Message> tmp = q.getResultList();
        if (tmp.size() > 0)
            return tmp.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public void update(Message user) {
        em.clear();
        em.merge(user);
    }

    @Override
    public void destroy(int id) {
        Query q = em.createQuery("delete Message m where m.id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }


    /*
    * Send a message to a project
    * @Param User u : sender
    * @Param Project p : project the message has been send to
    * @Param String content : content of the message
    *
    * */
    @Override
    public void sendMsg(User u, Project p, String content){
        Message m = new Message(u,p,content);

        List<Message> messP =  p.getMessages();
        messP.add(m);
        p.setMessages(messP);
        pS.update(p);
    }

    public void answerMsg(Project p, String content, Message m){
        User u = p.getBelongUser();
        Answer a = new Answer(content,m,u);
        Set<Answer> ans = u.getAnswers();
        ans.add(a);
        u.setAnswers(ans);
        m.setBelongAnswer(a);
        update(m);
        uS.update(u);
    }

}

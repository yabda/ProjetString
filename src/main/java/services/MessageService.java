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
    private UserServiceInterface uS;

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
    public int update(Message user) {
        em.clear();
        em.merge(user);
        return 0;
    }

    @Override
    public int destroy(int id) {
        Query q = em.createQuery("delete Message m where m.id = :id");
        q.setParameter("id", id);
        return q.executeUpdate();
    }

    @Override
    public int sendMsg(User u, Project p, String content){
        Message m = new Message(u,p,content);

        List<Message> messP =  p.getMessages();
        messP.add(m);
        for (Message me:p.getMessages()) {
            System.out.println(me.getContent());
        }
        p.setMessages(messP);
        pS.update(p);
        return 1;
    }

    public int answerMsg(Project p, String content, Message m){
        User u = p.getBelongUser();
        Answer a = new Answer(content,m,u);
        Set<Answer> ans = u.getAnswers();
        ans.add(a);
        u.setAnswers(ans);
        m.setBelongAnswer(a);
        update(m);
        uS.update(u);
        System.out.println("response : "+m.getBelongAnswer().getContent() + "link to "+m.getId());

        return 1;
    }

}

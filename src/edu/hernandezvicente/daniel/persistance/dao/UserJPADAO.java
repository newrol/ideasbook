

package edu.hernandezvicente.daniel.persistance.dao;

import com.iesdealquerias.dam.ideasbook.Friendship;
import com.iesdealquerias.dam.ideasbook.Request;
import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.persistance.daointerfaces.IUserDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 * 
 * @author Daniel 
 */
public class UserJPADAO extends JPADAO<User, Long> implements IUserDAO {
    
    @Override
    public List<User> findAll() {
        return entityManager.createNamedQuery("User.findAll", User.class)
                .getResultList();
    }

    @Override
    public User findByNameLike(String name) {
        String query = "SELECT u FROM User u where u.name = ?1";
        TypedQuery<User> exeQuery = super.entityManager.createQuery(query, User.class);
        exeQuery.setParameter(1, name);       //Query parameter 1
        return exeQuery.getSingleResult();
    }
    
    /**
     * Method to validate user in database users
     * @param user 
     * @return found user , or null if the client doesn´t exist
     */
    @Override
    public User validateUser(User user){
        //Query to find a user with selected values:
        String query = "SELECT u FROM User u where u.name = ?1  AND u.password = ?2";
        //Query created since father entityManager
        TypedQuery<User> exeQuery = super.entityManager.createQuery(query, User.class);
        
        exeQuery.setParameter(1, user.getName());       //Query parameter 1
        exeQuery.setParameter(2, user.getPassword());   //Query parameter 2
        
        return exeQuery.getSingleResult();
    }

    @Override
    public void adFriend(User user, User friend) {
        RequestJPADAO requestJPADAO = new RequestJPADAO();
        Request r = new Request();
        r.setUser1(user);
        r.setUser2(friend);
        friend.getRequestList().add(r);
        requestJPADAO.persist(r);
        super.update(friend);
    }

    @Override
    public void ConfirmRequest(Request request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


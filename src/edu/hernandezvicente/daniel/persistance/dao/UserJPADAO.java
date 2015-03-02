

package edu.hernandezvicente.daniel.persistance.dao;

import com.iesdealquerias.dam.ideasbook.Friendship;
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
    public List<User> findByNameLike(String name) {
        //@TODO
        return null;
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
    
    /**
     *Method to find all user friends. 
     * @param user
     * @return List friends
     */
    @Override
    public List<User> finduserFriends(User user) {
        //Query to find a user with selected values:
        String query = "SELECT f FROM Friendship f WHERE f.user1 = ?1  OR f.user2 = ?1";
        
        //Query created since father entityManager
        TypedQuery<Friendship> exeQuery = super.entityManager.createQuery(query, Friendship.class);
        exeQuery.setParameter(1, user);       //Query parameter 1
        
        List<Friendship> friendsipList = exeQuery.getResultList();
        List<User> friendsList = new ArrayList<User>();
        
        for(Friendship friendship :friendsipList)
            if(friendship.getUser1().getId() == user.getId())
                friendsList.add(friendship.getUser1());
            else
                friendsList.add(friendship.getUser2());
        
        return friendsList;
    }
}


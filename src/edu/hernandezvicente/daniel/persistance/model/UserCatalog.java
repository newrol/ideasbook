/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.persistance.model;

import com.iesdealquerias.dam.ideasbook.Friendship;
import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.persistance.dao.UserJPADAO;
import java.util.List;

/**
 * 
 * @author Daniel 
 */
public class UserCatalog {
    private UserJPADAO userDao;
    
    public UserCatalog(){
        userDao = new UserJPADAO();
    }
    
    /**
     * Create new user:
     * @param user 
     */
    public void CreateUser(User user){
        userDao.create(user);
    } 
    
    /**
     * Test it the user exist in database:
     * @param user
     * @return User if the user exist. 
     * @exception if the user doesnt exist.
     */
    public User validateUser(User user){
       return userDao.validateUser(user);
    }
    
    /**
     * Find user friends into database.
     * @param user
     * @return A list of friends 
     */
    public List<User> getFriends(User user){
        return userDao.finduserFriends(user);
    }
    
    /**
     * Find users in database since their name.
     * @param username
     * @return User if the user is sent
     * @exception if the user doesnt exist.
     */
    public User searchUserByName(String username){
        return userDao.findByNameLike(username);
    }
    
    /**
     * Update user values.
     * @param user 
     */
    public void modifyUser(User user){
        userDao.update(user);
    }    
    
    public Friendship getusersFriendShip(User user1, User user2){
        return userDao.findUserFriendship(user1, user2);
    }
            
}

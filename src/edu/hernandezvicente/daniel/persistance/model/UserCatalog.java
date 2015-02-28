/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.persistance.model;

import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.persistance.dao.UserJPADAO;

/**
 * 
 * @author Daniel 
 */
public class UserCatalog {
     UserJPADAO userDao;
    
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
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.persistance.model;

import com.iesdealquerias.dam.ideasbook.Friendship;
import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.persistance.dao.UserJPADAO;
import edu.hernandezvicente.daniel.persistance.dao.FriendshipJPADAO;
import java.util.List;

/**
 * 
 * @author Daniel 
 */
public class FriendShipCatalog {
    FriendshipJPADAO friendshipDAO;
    
    public FriendShipCatalog(){
        friendshipDAO = new FriendshipJPADAO();
    }
    
    /**
     * Find user friends into database.
     * @param user
     * @return A list of friends 
     */
    public List<User> getFriends(User user){
        return friendshipDAO.finduserFriends(user);
    }
    
    public List<Friendship> getusersFriendShip(User user1, User user2){
        return friendshipDAO.findUserFriendship(user1, user2);
    }
    
    public void addFriend(User host, User friend){
        friendshipDAO.startFriendship(host, friend);
    }
    
    public void deleteFriend(User host, User friend){
       friendshipDAO.deleteFriendShip(host, friend);
    }
    
    public List<Friendship> findFriendshipRequests(User user){
        return friendshipDAO.findFriendshipRequests(user);
    }
}

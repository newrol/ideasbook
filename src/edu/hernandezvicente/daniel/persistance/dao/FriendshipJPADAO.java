/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.persistance.dao;

import com.iesdealquerias.dam.ideasbook.Friendship;
import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.persistance.daointerfaces.IFriendshipDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 * 
 * @author Daniel 
 */
public class FriendshipJPADAO extends JPADAO<Friendship, Long> implements IFriendshipDAO{

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

    /**
     * Search if two users are friends
     * @param user1
     * @param user2
     */
    @Override
    public List<Friendship> findUserFriendship(User user1, User user2) {
        String query = "SELECT f FROM Friendship f WHERE f.user1 = ?1 AND f.user2 = ?2 OR f.user1 = ?2 AND f.user2 = ?1";
        TypedQuery<Friendship> exeQuery = super.entityManager.createQuery(query, Friendship.class);
        exeQuery.setParameter(1, user1);       //Query parameter 1
        exeQuery.setParameter(2, user2);       //Query parameter 2 
        return exeQuery.getResultList();
    }

    /**
     * Create new Friendship
     * @param host
     * @param friend 
     */
    @Override
    public boolean startFriendship(User host, User friend) {
        Friendship friendship = new Friendship();
        friendship.setUser1(host);
        friendship.setUser2(friend);
        friendship.setId(host.getId() + friend.getId());
        friendship.setType1("friend");
        friendship.setType2("friend");
        String query = "SELECT f FROM Friendship f WHERE f.user1 = ?1 AND f.user2 = ?2";
        TypedQuery<Friendship> exeQuery = super.entityManager.createQuery(query, Friendship.class);
        exeQuery.setParameter(1, host);       //Query parameter 1
        exeQuery.setParameter(2, friend);       //Query parameter 2
        
        try{
            exeQuery.getSingleResult();
            super.create(friendship);
            return false;
            
        }catch(javax.persistence.NoResultException e){
            super.create(friendship);
            return true;
        }
    }
    
    public void deleteFriendShip(User host, User Friend){
        Friendship friendship = new Friendship();
        friendship.setUser1(host);
        friendship.setUser1(Friend);
        super.delete(friendship);
    }

    @Override
    public List<Friendship> findFriendshipRequests(User user) {
        User selectedUser;
        int counter;
        List<Friendship> frienships;
        List<Friendship> friendRequests = new ArrayList<>();
        String query = "SELECT f FROM Friendship f WHERE f.user1 = ?1 OR f.user2 = ?1";
        TypedQuery<Friendship> exeQuery = super.entityManager.createQuery(query, Friendship.class);
        exeQuery.setParameter(1, user);       //Query parameter 1        
        frienships = exeQuery.getResultList();
        
        for(Friendship friendship: frienships){
            selectedUser = friendship.getUser1();
            counter = 0;
            
            for(Friendship pFriendship: frienships){
                if(selectedUser.equals(pFriendship.getUser2())){
                    break;
                }
                else{
                    counter++;                
                }
            }
            if(counter == frienships.size());
                friendRequests.add(friendship);
        }
        return friendRequests;
    }
}

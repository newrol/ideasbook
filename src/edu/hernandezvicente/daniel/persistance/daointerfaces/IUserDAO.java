/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.persistance.daointerfaces;

import com.iesdealquerias.dam.ideasbook.Friendship;
import edu.hernandezvicente.daniel.persistance.daointerfaces.IDAO;
import com.iesdealquerias.dam.ideasbook.User;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface IUserDAO extends IDAO<User, Long> {
    public User validateUser(User user);
    public User findByNameLike(String name);
    public List<User> finduserFriends(User user);
    public Friendship findUserFriendship(User user1, User user2);
}

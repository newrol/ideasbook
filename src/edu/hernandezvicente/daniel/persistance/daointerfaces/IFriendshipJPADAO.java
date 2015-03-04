/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.persistance.daointerfaces;

import com.iesdealquerias.dam.ideasbook.Friendship;
import com.iesdealquerias.dam.ideasbook.User;
import java.util.List;

/**
 * 
 * @author Daniel 
 */
public interface IFriendshipJPADAO {
    public boolean startFriendship(User host, User friend);
    public List<Friendship> findFriendshipRequests(User user);    
}

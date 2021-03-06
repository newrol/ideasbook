/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.persistance.daointerfaces;

import com.iesdealquerias.dam.ideasbook.Publication;
import com.iesdealquerias.dam.ideasbook.User;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface IPublicationDAO {
    public List<Publication> getUserPublications(User user);
    public List<Publication> getLastPublications(User user);
    public void addCommentToPublication(Publication publication, Publication Comment);
}

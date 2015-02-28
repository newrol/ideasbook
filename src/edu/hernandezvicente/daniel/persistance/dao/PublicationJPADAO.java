/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.persistance.dao;

import com.iesdealquerias.dam.ideasbook.Publication;
import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.persistance.daointerfaces.IPublicationDAO;
import java.util.List;

/**
 * 
 * @author Daniel 
 */
public class PublicationJPADAO extends JPADAO<Publication, Long> implements IPublicationDAO {

    @Override
    public List<Publication> getUserPublications(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

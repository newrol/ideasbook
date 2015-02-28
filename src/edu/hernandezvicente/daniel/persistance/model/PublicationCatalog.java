/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.persistance.model;

import com.iesdealquerias.dam.ideasbook.Publication;
import edu.hernandezvicente.daniel.persistance.dao.PublicationJPADAO;

/**
 * 
 * @author Daniel 
 */
public class PublicationCatalog {
    private PublicationJPADAO publicationDAO;
    
    public PublicationCatalog(){
        this.publicationDAO = new PublicationJPADAO();
    }
    
    public void addPublication(Publication publication){
        publicationDAO.create(publication);
    }
}

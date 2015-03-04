/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.persistance.dao;

import com.iesdealquerias.dam.ideasbook.Request;
import edu.hernandezvicente.daniel.persistance.daointerfaces.IRequestDAO;

/**
 * 
 * @author Daniel 
 */
public class RequestJPADAO extends JPADAO<Request, Long> implements IRequestDAO{
    
    /**
     *
     * @param r
     */
    @Override
    public void persist(Request r){
       super.create(r);
    }

}

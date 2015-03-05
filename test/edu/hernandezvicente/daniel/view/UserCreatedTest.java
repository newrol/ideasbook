/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.view;

import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.persistance.model.UserCatalog;
import junit.framework.TestCase;

/**
 * 
 * @author Daniel 
 */
public class UserCreatedTest extends TestCase {
    private UserCatalog userCatalog;
    private String outputvalue = null;
    /**
     * Method will be executed before all mehtod tests
     *  in this case is used to inicialize default values.
     */
    @Override
    protected void setUp() throws Exception {
        userCatalog = new UserCatalog();
    }
    
     /**
     * existingValue Test
     */
    public void testDecimalConversor(){
        User user = userCatalog.searchUserByName("user");
        assertEquals("user" , user.getEmail());        
    }
    
}

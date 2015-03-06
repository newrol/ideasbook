/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.reports;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


/**
 *
 * @author juanvi
 */
public class UserCommentsLoader {
    private static final String CON_STR = "jdbc:mysql://localhost:3306/ideasbook";
    private static final String USER = "root";
    private static final String PASS = "12345";
    private static final String REPORT = "src\\edu\\hernandezvicente\\daniel\\reports\\UserComments.jasper";    
    private static final String REPORT_JXML = "src\\edu\\hernandezvicente\\daniel\\reports\\UserComments.jrxml";    

    private JasperReport reporte;
    private JasperPrint jasperPrint;
    
    public void load() throws ClassNotFoundException, SQLException, JRException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = (Connection) DriverManager.getConnection(CON_STR, USER, PASS);
        
        JasperDesign jasperDesign = JRXmlLoader.load(new File(REPORT_JXML));
        JasperReport jasperReport; 
        reporte = JasperCompileManager.compileReport(jasperDesign);
        HashMap params = new HashMap();
        params.put("USER_ID", 1);
        reporte = (JasperReport) JRLoader.loadObject(new File(REPORT));
        jasperPrint = JasperFillManager.fillReport(reporte, params, conexion);        
    }
    

    public JasperReport getReporte() {
        return reporte;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }    
}

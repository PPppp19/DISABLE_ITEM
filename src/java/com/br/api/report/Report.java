/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.report;

import com.br.api.connect.ConnectDB;
import com.br.api.data.SelectDB2;
import com.br.api.data.UpdateDB2;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Wattana
 */
@WebServlet(name = "Report", urlPatterns = {"/Report"})
@MultipartConfig
public class Report extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Report</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Report at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);

        if (request.getParameter("report").equalsIgnoreCase("GetReport")) {

            JasperDesign JPD;
//            String path = getServletContext().getRealPath("/jasper/");
//            System.out.println(path);

//            try {
//                JPD = JRXmlLoader.load(path + "BM.jrxml");
//                JasperReport jasperReport = JasperCompileManager.compileReport(JPD);
            try {

                String path1 = getServletContext().getRealPath("/jasper/");

                JPD = JRXmlLoader.load(path1 + "BM_NEW.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(JPD);

                File reportFile = new File(getServletContext().getRealPath("jasper/BM_NEW.jasper"));
                System.out.println(getServletContext().getRealPath("jasper/BM_NEW.jasper"));

                String fdate = request.getParameter("vDate").replaceAll("-", "");
                String tdate = request.getParameter("vtDate").replaceAll("-", "");
                String type = request.getParameter("vType").toString().trim();
                String location = request.getParameter("vLoc").trim();

                String parameterA = "";
                String parameterB = "";
                String FGLOCATION = "";
                String RV = "";

                if (type.equalsIgnoreCase("Normal")) {

                    parameterA = "TRANSFER";
                    parameterB = "TRANSFER_EXP";

                } else if (type.equalsIgnoreCase("DEPOSIT")) {

                    parameterA = "TRANSFER_DEPOSIT";
                    parameterB = "TRANSFER_DEPOSIT";
                } else {

                    parameterA = "%";
                    parameterB = "%";

                }

                if (location.equalsIgnoreCase("LS")) {
                    FGLOCATION = "L/S";
                    RV = "RVC-TRN";
                } else if (location.equalsIgnoreCase("DC_CM")) {
                    FGLOCATION = "DC/CM";
                    RV = "RVF-TRN";
                } else if (location.equalsIgnoreCase("WTF")) {
                    FGLOCATION = "WTF";
//                    RV = "RVF-TRN";
                }else if (location.equalsIgnoreCase("ACOOUNT")) {
                    FGLOCATION = "ACOOUNT";
//                    RV = "RVF-TRN";
                }
                else if (location.equalsIgnoreCase("OTHER")) {
                    FGLOCATION = "OTHER";
//                    RV = "RVF-TRN";
                }

                Map parameterss = new HashMap();
                parameterss.put("mono", "10");
                parameterss.put("com", "10");
                parameterss.put("from", fdate);
                parameterss.put("to", tdate);
                parameterss.put("Type", type);
                parameterss.put("Location", location);
                parameterss.put("parameterA", parameterA);
                parameterss.put("parameterB", parameterB);
                parameterss.put("FGLOCATION", FGLOCATION);
                parameterss.put("RV", RV);

//                parameterss.put("from", Integer.parseInt(fdate));
//                parameterss.put("to", Integer.parseInt(tdate));
//                parameterss.put("type", type);
//                parameterss.put("locations", locations);
                Connection conn = ConnectDB.ConnectionDB();
                byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parameterss, conn);
                response.setContentType("application/pdf");

                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();

                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();

            } catch (JRException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
//            } catch (Exception ex) {
//                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
//            }

        } else if (request.getParameter("report").equalsIgnoreCase("GetReport_EXCEL")) {

            JasperDesign JPD;

            try {

                String path1 = getServletContext().getRealPath("/jasper/");

                JPD = JRXmlLoader.load(path1 + "BM_NEW.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(JPD);

                File reportFile = new File(getServletContext().getRealPath("jasper/BM_NEW.jasper"));
                System.out.println(getServletContext().getRealPath("jasper/BM_NEW.jasper"));

                String fdate = request.getParameter("vDate").replaceAll("-", "");
                String tdate = request.getParameter("vtDate").replaceAll("-", "");
                String type = request.getParameter("vType").toString().trim();
                String location = request.getParameter("vLoc").trim();

                String parameterA = "";
                String parameterB = "";
                String FGLOCATION = "";
                String RV = "";

                if (type.equalsIgnoreCase("Normal")) {

                    parameterA = "TRANSFER";
                    parameterB = "TRANSFER_EXP";

                } else if (type.equalsIgnoreCase("DEPOSIT")) {

                    parameterA = "TRANSFER_DEPOSIT";
                    parameterB = "TRANSFER_DEPOSIT";
                } else {

                    parameterA = "%";
                    parameterB = "%";

                }

                if (location.equalsIgnoreCase("LS")) {
                    FGLOCATION = "L/S";
                    RV = "RVC-TRN";
                } else if (location.equalsIgnoreCase("DC_CM")) {
                    FGLOCATION = "DC/CM";
                    RV = "RVF-TRN";
                }

                Map parameterss = new HashMap();
                parameterss.put("mono", "10");
                parameterss.put("com", "10");
                parameterss.put("from", fdate);
                parameterss.put("to", tdate);
                parameterss.put("Type", type);
                parameterss.put("Location", location);
                parameterss.put("parameterA", parameterA);
                parameterss.put("parameterB", parameterB);
                parameterss.put("FGLOCATION", FGLOCATION);
                parameterss.put("RV", RV);


                Connection conn = ConnectDB.ConnectionDB();

                JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameterss, conn);
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + "BANK_MAPPING_Excel" + ".xlsx" + "\"");
                JRXlsxExporter exporterXls = new JRXlsxExporter();
                ServletOutputStream ouputStream = response.getOutputStream();
                exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporterXls.exportReport();
                ouputStream.flush();
                ouputStream.close();

            } catch (JRException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
//            } catch (Exception ex) {
//                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
//            }

        }
    }
}

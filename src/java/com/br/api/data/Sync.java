/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jettison.json.JSONException;

/**
 *
 * @author Wattana
 */
public class Sync extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Sync</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Sync at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true);

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("page").equals("Grid_SCB")) {

                try {
                    out.print(SelectDB2.getGrid_SCB(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_SCB_CUR")) {

                try {
                    out.print(SelectDB2.getGrid_SCB_CUR(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_KBANK_CUR")) {

                try {
                    out.print(SelectDB2.getGrid_KBANK_CUR(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_BBL_CUR")) {

                try {
                    out.print(SelectDB2.getGrid_BBL_CUR(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //********************** GRID BBL_QR ****************
            
             else if (request.getParameter("page").equals("Grid_BBL_QR")) {

                try {
                    out.print(SelectDB2.getGrid_BBL_QR(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            //**************************************************
            
            
            else if (request.getParameter("page").equals("Grid_SCBNSD")) {

                try {
                    out.print(SelectDB2.getGrid_SCBNSD(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("RETURNBM")) {

                try {

                    boolean isBM = SelectDB2.CHKISBM(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("ID"));

                    if (isBM == true) {

                        UpdateDB2.RETURNBM(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("ID"));

                    } else {

                        System.out.println("fuckkkkkk");
                    }

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CHKCUORPY")) {

                try {
                    out.print(SelectDB2.CHKCUORPY(request.getParameter("TXT"), (request.getParameter("CONO"))));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_SCBold")) {

                try {
                    out.print(SelectDB2.getGrid_SCBold(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SAVERC")) {
                try {
                    UpdateDB2.SAVERC(request.getParameter("cono"),
                            request.getParameter("divi"),
                            request.getParameter("rcno"),
                            request.getParameter("bank"),
                            request.getParameter("type"),
                            request.getParameter("date"),
                            request.getParameter("pydt"),
                            request.getParameter("amt"),
                            request.getParameter("pyno"),
                            request.getParameter("bkchg"),
                            request.getParameter("lcode"),
                            request.getParameter("fnno"),
                            request.getParameter("user")
                    );
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("GetIDBYRCNO")) {
                try {

                    out.print(SelectDB2.GetIDBYRCNO(request.getParameter("CRCNO"),request.getParameter("CONO")));
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateHEADfix")) {
                try {

                    UpdateDB2.updateHEADfix(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("rcno"), request.getParameter("pyno"), request.getParameter("cuno"), request.getParameter("fnno"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("getHRRECEIPT")) {
                try {
                    out.print(SelectDB2.Call_GridRCNO(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("rcno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Call_GridRNNO")) {
                try {
                    out.print(SelectDB2.Call_GridRNNO(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("rnno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UNLOCKINVOICE")) {
                try {
                    DeleteDB2.UNLOCKINVOICE(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("invno"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("addstatement")) {
                try {
                    InsertDB2.addstatement(
                            request.getParameter("cono"),
                            request.getParameter("divi"),
                            request.getParameter("user"),
                            request.getParameter("bank"),
                            request.getParameter("bankcode"),
                            request.getParameter("date"),
                            request.getParameter("time"),
                            request.getParameter("amt"),
                            request.getParameter("desc"),
                            request.getParameter("refcus"),
                            request.getParameter("lcode"),
                            request.getParameter("cuna")
                    );

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CheckINVSTS")) {
                try {
                    out.print(SelectDB2.CheckINVSTS(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("invno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("setstatus")) {
                try {
                    UpdateDB2.setstatus(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("rcno"), request.getParameter("sts"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CancelID")) {
                try {
                    System.out.println("kuuuuu");
                    UpdateDB2.CancelID(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("id"), request.getParameter("username"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_KBANK")) {
                try {
                    out.print(SelectDB2.getGrid_KBANK(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_KBANKold")) {
                try {
                    out.print(SelectDB2.getGrid_KBANKold(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_BBLold")) {
                try {
                    out.print(SelectDB2.getGrid_BBLold(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_BBL")) {
                try {
                    out.print(SelectDB2.getGrid_BBL(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Company")) {
                try {
                    out.print(Utility.getCompany());
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_SCB_BILLold")) {
                try {
                    out.print(SelectDB2.getGrid_SCB_BILLold(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_KBANK_BILLold")) {
                try {
                    out.print(SelectDB2.getGrid_KBANK_BILLold(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_SCB_BILL")) {
                try {
                    out.print(SelectDB2.getGrid_SCB_BILL(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_KBANK_BILL")) {
                try {
                    out.print(SelectDB2.getGrid_KBANK_BILL(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_SCB_MMNold")) {
                try {
                    out.print(SelectDB2.getGrid_SCB_MMNold(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_SCB_MMN")) {
                try {
                    out.print(SelectDB2.getGrid_SCB_MMN(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_BBL_BILLold")) {
                try {
                    out.print(SelectDB2.getGrid_BBL_BILLold(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_BBL_BILL")) {
                try {
                    out.print(SelectDB2.getGrid_BBL_BILL(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("GetdatabyrcnoRC")) {
                try {
                    out.print(SelectDB2.GetdatabyrcnoRC(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("rcno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Getdatabyrcno")) {
                try {
                    out.print(SelectDB2.Getdatabyrcno(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("amt"), request.getParameter("time"), request.getParameter("date"), request.getParameter("bank")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UpdatedataBM")) {
                try {
                    UpdateDB2.UpdatedataBM(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("amt"), request.getParameter("time"), request.getParameter("date"), request.getParameter("rcno"), request.getParameter("cuno"), request.getParameter("username"), request.getParameter("bank"), request.getParameter("fnno"), request.getParameter("acc"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DeletedataBM")) {
                try {
                    DeleteDB2.DeletedataBM(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("amt"), request.getParameter("time"), request.getParameter("date"), request.getParameter("bank"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UpdatedataRC")) {
                try {
                    UpdateDB2.UpdatedataRC(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("rcno").toString(), request.getParameter("HC_PYNO").toString(), request.getParameter("HC_REAMT").toString(), request.getParameter("HC_BANK").toString(), request.getParameter("trdt").toString(), request.getParameter("user").toString(), request.getParameter("fixno").toString());

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchCustomerReceipt")) {
                try {
                    out.print(SelectDB2.SearchCustomerReceipt(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("FNNO"), request.getParameter("locations")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SetnamePayer")) {
                try {
                    out.print(SelectDB2.SetnamePayer(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UpdateHeader")) {
                try {
                    UpdateDB2.UpdateHeader(request.getParameter("HR_CONO"), request.getParameter("HR_DIVI"), request.getParameter("HC_PYNO"), request.getParameter("HC_REAMT"), request.getParameter("HC_TYPE"), request.getParameter("HC_PYDT"),
                            request.getParameter("HC_CHKNO"), request.getParameter("HC_BANK"), request.getParameter("HC_ACCODE"), request.getParameter("HR_BKCHG"), request.getParameter("HR_RCNO"), request.getParameter("HC_LOCATION"), request.getParameter("FNNO"), request.getParameter("CUNO"), request.getParameter("TRDT"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("getstatus")) {
                try {
                    out.print(SelectDB2.getstatus(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("rcno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_Receipt")) {
                try {
                    out.print(SelectDB2.Grid_Receipt(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("rcno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_ARS200bycus")) {
                try {
                    out.print(SelectDB2.Grid_ARS200bycus(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("payer"), request.getParameter("rcno"), request.getParameter("customer")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("List_Bank")) {
                try {
                    out.print(Utility.List_Bank(request.getParameter("type"), request.getParameter("code"), request.getParameter("cono").trim(), request.getParameter("divi").trim()));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DELRCNO")) {
                try {

                    System.out.println("deldeldelelde");
                    DeleteDB2.DELRCNO(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("rcno").trim());

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Location")) {
                try {
                    out.print(Utility.getLocation(request.getParameter("cono").trim(), request.getParameter("divi").trim()));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Getrcno")) {

                try {

                    System.out.println("aaaaaaaaaaaazzzzzaaaaaaaaaaaaaaaaaaaaaa");

                    out.print(SelectDB2.Getrcno(request.getParameter("cono"),
                            request.getParameter("divi"),
                            request.getParameter("FNNO"),
                            request.getParameter("CUNO"),
                            request.getParameter("PYNO"),
                            request.getParameter("HC_TRDT"),
                            request.getParameter("HC_REAMT"),
                            request.getParameter("HC_TYPE"),
                            request.getParameter("HC_PYDT"),
                            request.getParameter("HC_CHKNO"),
                            request.getParameter("HC_BANK"),
                            request.getParameter("HC_ACCODE"),
                            request.getParameter("HC_USER"),
                            request.getParameter("HR_BKCHG"),
                            request.getParameter("locations")
                    ));
                    out.flush();
//                    } else {
//                        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbb");

//                        out.print(SelectDB2.checkRCNO(request.getParameter("cono"),
//                                request.getParameter("divi"),
//                                request.getParameter("FNNO"),
//                                request.getParameter("CUNO"),
//                                request.getParameter("PYNO"),
//                                request.getParameter("HC_TRDT"),
//                                request.getParameter("HC_REAMT"),
//                                request.getParameter("HC_TYPE"),
//                                request.getParameter("HC_PYDT"),
//                                request.getParameter("HC_CHKNO"),
//                                request.getParameter("HC_BANK"),
//                                request.getParameter("HC_ACCODE"),
//                                request.getParameter("HC_USER"),
//                                request.getParameter("HR_BKCHG"),
//                                request.getParameter("HC_LOCATION")));
//                        out.flush();
//                    }
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        if (session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
        }

        try {
            
            
            
            if (request.getParameter("page").equals("upload_SCB_IntraDay")) {
                try {
                    InsertDB2.upload_SCB_IntraDay(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("jsondata"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
           else if (request.getParameter("page").equals("Disableitem")) {
                try {
                    UpdateDB2.Disableitem(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("user"), request.getParameter("jsondata"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
            
            
            else if (request.getParameter("page").equals("upload_SCB_IntraDayWTF")) {
                try {
                    InsertDB2.upload_SCB_IntraDayWTF(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("jsondata"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } // Current -------------------------------------------------------
            else if (request.getParameter("page").equals("upload_SCBCUR_Intraday")) {
                try {
                    InsertDB2.upload_SCBCUR_Intraday(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_SCBCUR_Historical")) {
                try {
                    InsertDB2.upload_SCBCUR_Historical(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_KBANKCUR_Intraday")) {
                try {
                    InsertDB2.upload_KBANKCUR_IntraDay(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_KBANKCUR_Historical")) {
                try {
                    InsertDB2.upload_KBANKCUR_Historical(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
                    
                             else if (request.getParameter("page").equals("upload_BBLCUR_Intraday")) {
                try {
                    InsertDB2.upload_BBLCUR_IntraDay(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
                
                             
                                       else if (request.getParameter("page").equals("upload_BBLCUR_Intraday")) {
                try {
                    InsertDB2.upload_BBLCUR_Historical(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
                
                
              // Current -------------------------------------------------------
            else if (request.getParameter("page").equals("upload_SCB_IntraDayNSD")) {
                try {
                    InsertDB2.upload_SCB_IntraDayNSD(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("jsondata"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_SCB_HistoricalNSD")) {
                try {
                    InsertDB2.upload_SCB_HistoricalNSD(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("jsondata"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_SCB_HistoricalWTF")) {
                try {
                    InsertDB2.upload_SCB_HistoricalWTF(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("jsondata"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_SCB_Historical")) {
                try {
                    InsertDB2.upload_SCB_Historical(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("jsondata"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_KBANK_IntraDay")) {
                try {
                    InsertDB2.upload_KBANK_IntraDay(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("jsondata"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_KBANK_Historical")) {
                try {
                    InsertDB2.upload_KBANK_Historical(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("jsondata"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_SCBnewWTF")) {
                try {
                    InsertDB2.createReceipt_SCBnewWTF(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_SCBnewNSD")) {
                try {
                    InsertDB2.createReceipt_SCBnewNSD(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_SCBnew")) {
                try {
                    InsertDB2.createReceipt_SCBnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_SCB_CURnew")) {
                try {
                    InsertDB2.createReceipt_SCB_CURnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_SCB")) {
                try {
                    InsertDB2.createReceipt_SCB(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_SCB_RES")) {

                try {
                    UpdateDB2.updateReceipt_SCB_RES(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_RCNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"), request.getParameter("user"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_SCB_RES")) {

                try {
                    InsertDB2.createReceipt_SCB_RES(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"), request.getParameter("user"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_SCBnewWTF")) {
                try {
                    UpdateDB2.updateReceipt_SCBnewWTF(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter("page").equals("updateReceipt_SCBnewNSD")) {
                try {
                    UpdateDB2.updateReceipt_SCBnewNSD(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_SCBnew")) {
                try {
                    UpdateDB2.updateReceipt_SCBnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_SCB_CURnew")) {
                try {
                    UpdateDB2.updateReceipt_SCB_CURnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_SCB")) {
                try {
                    UpdateDB2.updateReceipt_SCB(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_RCNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateAccont")) {
                try {
                    UpdateDB2.updateAccont_SCB(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_RCNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updatenewReceipt_SCB")) {
                try {

                    //updatenewReceipt_SCB
                    // generate RCNO  for canceled statement  (status = 9 : cancel)
                    int status = SelectDB2.getstatus(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("HC_PYDT"), request.getParameter("HC_PYNO"));

                    System.out.println("Status = " + status);
                    String lastNo = SelectDB2.getlastNo(request.getParameter("cono"), request.getParameter("divi").toString());

                    if (status == 9) {

                        UpdateDB2.updatenewReceipt_SCB(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("HC_PYDT"), request.getParameter("HC_PYNO"), lastNo);
                    }

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_KBANK_CURnew")) {
                try {
                    InsertDB2.createReceipt_KBANK_CURnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_KBANKnew")) {
                try {
                    InsertDB2.createReceipt_KBANKnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_KBANK")) {
                try {
                    InsertDB2.createReceipt_KBANK(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_KBANK")) {
                try {
                    UpdateDB2.updateReceipt_KBANK(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_RCNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_KBANKnew")) {
                try {
                    UpdateDB2.updateReceipt_KBANKnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_KBANK_CURnew")) {
                try {
                    UpdateDB2.updateReceipt_KBANK_CURnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_KBANK_RES")) {
                try {
                    InsertDB2.createReceipt_KBANK_RES(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"), request.getParameter("user"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_KBANK_RES")) {
                try {
                    UpdateDB2.updateReceipt_KBANK_RES(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_RCNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"), request.getParameter("user"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_1AA2250_Intraday")) {
                try {
                    InsertDB2.upload_1AA2250_IntraDay(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_1AA2250_Historical")) {
                try {
                    InsertDB2.upload_1AA2250_Historical(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {

                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_1AA2114_Intraday")) {
                try {
                    InsertDB2.upload_1AA2114_IntraDay(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_1AA2114_Historical")) {
                try {
                    InsertDB2.upload_1AA2114_Historical(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_BBL_IntradayWTF")) {
                try {
                    InsertDB2.upload_BBL_IntraDayWTF(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_BBL_HistoricalWTF")) {
                try {
                    InsertDB2.upload_BBL_HistoricalWTF(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_1AA2214_Intraday")) {
                try {
                    InsertDB2.upload_1AA2214_IntraDay(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_1AA2214_Historical")) {
                try {
                    InsertDB2.upload_1AA2214_Historical(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_1AA2286_Intraday")) {
                try {
                    InsertDB2.upload_1AA2286_IntraDay(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_1AA2286_Historical")) {
                try {
                    InsertDB2.upload_1AA2286_Historical(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_1AA2283_Intraday")) {
                try {
                    InsertDB2.upload_1AA2283_IntraDay(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_1AA2283_Historical")) {
                try {
                    InsertDB2.upload_1AA2283_Historical(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_BBL")) {
                try {
                    InsertDB2.createReceipt_BBL(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_BBL")) {
                try {
                    UpdateDB2.updateReceipt_BBL(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_RCNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_BBLnew")) {
                try {
                    UpdateDB2.updateReceipt_BBLnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_BBL_CURnew")) {
                try {
                    UpdateDB2.updateReceipt_BBL_CURnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_BBLnewWTF")) {
                try {
                    UpdateDB2.updateReceipt_BBLnewWTF(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_BBLnew")) {
                try {
                    InsertDB2.createReceipt_BBLnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //*******************CREATE BBL_QR ******************
            
            else if (request.getParameter("page").equals("createReceipt_BBL_QRnew")) {
                try {
                    InsertDB2.createReceipt_BBL_QRnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            
            
            
            //**********************************************
            
            
            else if (request.getParameter("page").equals("createReceipt_BBL_CURnew")) {
                try {
                    InsertDB2.createReceipt_BBL_CURnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_BBLnewWTF")) {
                try {
                    InsertDB2.createReceipt_BBLnewWTF(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_BBL")) {
                try {
                    InsertDB2.createReceipt_BBL(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            //********************updateReceipt_BBL_QRnew******************
            
              else if (request.getParameter("page").equals("updateReceipt_BBL_QRnew")) {
                try {
                    UpdateDB2.updateReceipt_BBL_QRnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            //************************************************************
            
            else if (request.getParameter("page").equals("updateReceipt_BBL_BILLnew")) {
                try {
                    UpdateDB2.updateReceipt_BBL_BILLnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_BBL_BILL")) {
                try {
                    UpdateDB2.updateReceipt_BBL_BILL(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_RCNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_BBL_BILLnew")) {
                try {
                    InsertDB2.createReceipt_BBL_BILLnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_BBL_BILL")) {
                try {
                    InsertDB2.createReceipt_BBL_BILL(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_SCB_BILLnew")) {
                try {
                    InsertDB2.createReceipt_SCB_BILLnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_SCB_BILL")) {
                try {
                    InsertDB2.createReceipt_SCB_BILL(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_SCB_BILL")) {
                try {
                    UpdateDB2.updateReceipt_SCB_BILL(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_RCNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_SCB_BILLnew")) {
                try {
                    UpdateDB2.updateReceipt_SCB_BILLnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_SCB_RBILLnew")) {
                try {
                    InsertDB2.createReceipt_SCB_RBILLnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_SCB_RBILL")) {
                try {
                    InsertDB2.createReceipt_SCB_RBILL(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_SCB_RBILLnew")) {
                try {
                    UpdateDB2.updateReceipt_SCB_RBILLnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_SCB_RBILL")) {
                try {
                    UpdateDB2.updateReceipt_SCB_RBILL(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_RCNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_KBANK_BILLnew")) {
                try {
                    UpdateDB2.updateReceipt_KBANK_BILLnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_FNNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("updateReceipt_KBANK_BILL")) {
                try {
                    UpdateDB2.updateReceipt_KBANK_BILL(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("BM_DATE"), request.getParameter("BM_AMOUNT"),
                            request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"), request.getParameter("BM_RCNO"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_KBANK_BILL")) {
                try {
                    InsertDB2.createReceipt_KBANK_BILL(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("BM_CUNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("createReceipt_KBANK_BILLnew")) {
                try {
                    InsertDB2.createReceipt_KBANK_BILLnew(request.getParameter("cono"), request.getParameter("divi").toString(), request.getParameter("bank"), request.getParameter("BM_DATE"), request.getParameter("BM_TIME"),
                            request.getParameter("BM_AMOUNT"), request.getParameter("HPYNO"), request.getParameter("BM_BKCHARGE"), request.getParameter("BM_OVPAY"), request.getParameter("BM_CNDN"), request.getParameter("BM_ID"),
                            request.getParameter("BM_TYPE"), request.getParameter("BM_LOCA"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //**************************** BBLQR ****************************
            
             else if (request.getParameter("page").equals("upload_BBLQR_IntraDay")) {
                try {
                    InsertDB2.upload_BBLQR_IntraDay(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("upload_BBLQR_Historical")) {
                try {
                    InsertDB2.upload_BBLQR_Historical(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("bank"), request.getParameter("user"), request.getParameter("myJsonData"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            //**************************************************************

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

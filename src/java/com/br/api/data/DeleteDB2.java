/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.api.data;

import com.br.api.connect.ConnectDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author Phongsathon
 */
public class DeleteDB2 {

    public static String DBPRD = GBVAR.DBPRD;
    public static String M3DB = GBVAR.M3DB;

    public static void DELRCNO(String cono, String divi, String rcno) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "DELETE  FROM " + M3DB + ".HEAD_RECIPT\n"
                        + "WHERE H_RCNO ='" + rcno.trim() + "' \n"
                        + "AND H_CONO = '"+cono+"' AND H_DIVI = '"+divi.trim()+"' ";
                System.out.println(query);
                stmt.executeUpdate(query);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void UNLOCKINVOICE(String cono, String divi, String invno) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "DELETE  FROM " + M3DB + ".FCR040\n"
                        //                        + "WHERE ACCONO='"+cono+"' \n"
                        //                        + "AND ACDIVI='"+divi+"' \n"
                        + "WHERE ACCINO='" + invno.trim() + "' \n"
                        + "AND ACSTCF = 9 ";
                stmt.executeUpdate(query);
                System.out.println(query);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void DeletedataBM(String cono, String divi, String amt, String time, String date, String bank) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "DELETE FROM " + DBPRD + ".BANK_MAPPING\n"
                        + "WHERE BM_DATE='" + date.trim() + "' \n"
                        + "AND BM_BANK='" + bank.trim() + "' \n"
                        + "AND BM_AMOUNT='" + amt.trim() + "' \n"
                        + "AND BM_TIME  ='" + time.trim() + "'\n"
                        + "AND BM_CONO  = '" + cono.trim() + "'\n"
                        + "AND BM_DIVI  = '" + divi.trim() + "'";
                stmt.executeUpdate(query);
                System.out.println(query);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

}

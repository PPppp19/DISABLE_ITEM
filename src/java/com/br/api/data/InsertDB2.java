/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.data;

import com.br.api.connect.ConnectDB;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Jilasak
 */
public class InsertDB2 {

    public static String DBPRD = GBVAR.DBPRD;

    public static void addstatement(String cono, String divi, String user, String bank, String bankcode, String date, String time, String amt, String desc, String refcus, String lcode, String cuna) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                //Date date2 = new Date();
                //String dateNow = formatter.format(date);
                String sql = "";
                try {

                    sql = "INSERT INTO " + DBPRD + ".BANK_MAPPING  (bm_cono, bm_divi , bm_bank, BM_ACCODE, BM_DATE, BM_TIME, BM_CHQNO, BM_TRANSAC, BM_CHANEL , BM_BRANCH , BM_AMOUNT, BM_DESC , BM_TIME1, BM_USER, BM_STATUS, BM_REFCU, BM_REFCU1, BM_LCODE, BM_CUNA)\n"
                            + "VALUES ( '" + cono + "', '" + divi + "' , '" + bank + "', '" + bankcode + "', '" + date + "' , '" + time + "' , '-', '-', '-', '-', '" + amt + "', '" + desc + "', '" + timestamp + "', '" + user + "', '10', '-', '" + refcus + "', '" + lcode + "', '" + cuna + "')";
                    System.out.println(sql);
                    stmt.execute(sql);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    //current -------------------------------------------------------------------
    //current -------------------------------------------------------------------
    
    
            
       
    
    
    public static void upload_SCB_IntraDay(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME, BM_CHQNO, BM_TRANSAC, BM_BRANCH, BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1 , BM_DESC)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?, ?, ?, ?,"
                                + " ?, ? , ? , ?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {
                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, obj.getString("Account_Number").trim());
// 11/12/22
//                            String[] date_arr = obj.getString("Date").split("/");
//                            String newDate = "20" + date_arr[2] + date_arr[0] + date_arr[1];
                            stmt.setString(5, (obj.getString("Date")));
                            stmt.setString(6, (obj.getString("Time")));
                            stmt.setString(7, obj.getString("Cheque_Number"));
                            stmt.setString(8, obj.getString("Transaction_Code"));
                            stmt.setString(9, obj.getString("Branch_Number"));
                            amt = Double.parseDouble(obj.getString("Amount").replaceAll(",", ""));
                            stmt.setBigDecimal(10, BigDecimal.valueOf(amt));
                            stmt.setString(11, user);
                            stmt.setString(12, "10");
                            stmt.setTimestamp(13, timestamp);
                            stmt.setString(14, obj.getString("Description").trim());
                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_SCB_IntraDayNSD(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME, BM_CHQNO, BM_TRANSAC, BM_BRANCH, BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1 , BM_DESC)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?, ?, ?, ?,"
                                + " ?, ? , ? , ?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {
                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "3313029408");
// 11/12/22
//                            String[] date_arr = obj.getString("Date").split("/");
//                            String newDate = "20" + date_arr[2] + date_arr[0] + date_arr[1];
                            stmt.setString(5, (obj.getString("Date")));
                            stmt.setString(6, (obj.getString("Time")));
                            stmt.setString(7, obj.getString("Cheque_Number"));
                            stmt.setString(8, obj.getString("Transaction_Code"));
                            stmt.setString(9, obj.getString("Branch_Number"));
                            amt = Double.parseDouble(obj.getString("Amount").replaceAll(",", ""));
                            stmt.setBigDecimal(10, BigDecimal.valueOf(amt));
                            stmt.setString(11, user);
                            stmt.setString(12, "10");
                            stmt.setTimestamp(13, timestamp);
                            stmt.setString(14, obj.getString("Description").trim());
                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_SCB_IntraDayWTF(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME, BM_CHQNO, BM_TRANSAC, BM_BRANCH, BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1 , BM_DESC)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?, ?, ?, ?,"
                                + " ?, ? , ? , ?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {
                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "3313029652");
// 11/12/22
//                            String[] date_arr = obj.getString("Date").split("/");
//                            String newDate = "20" + date_arr[2] + date_arr[0] + date_arr[1];
                            stmt.setString(5, (obj.getString("Date")));
                            stmt.setString(6, (obj.getString("Time")));
                            stmt.setString(7, obj.getString("Cheque_Number"));
                            stmt.setString(8, obj.getString("Transaction_Code"));
                            stmt.setString(9, obj.getString("Branch_Number"));
                            amt = Double.parseDouble(obj.getString("Amount").replaceAll(",", ""));
                            stmt.setBigDecimal(10, BigDecimal.valueOf(amt));
                            stmt.setString(11, user);
                            stmt.setString(12, "10");
                            stmt.setTimestamp(13, timestamp);
                            stmt.setString(14, obj.getString("Description").trim());
                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_SCB_HistoricalNSD(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME, BM_CHQNO, BM_TRANSAC, BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1 , BM_DESC)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?, ?, ?, "
                                + " ?, ? , ?, ?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {
                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "3313029408");
//                            String[] date_arr = obj.getString("Date").split("/");
//                            String newDate = "20" + date_arr[2] + date_arr[0] + date_arr[1];
                            stmt.setString(5, (obj.getString("Date")));
                            stmt.setString(6, (obj.getString("Time")));
                            stmt.setString(7, obj.getString("Cheque_Number"));
                            stmt.setString(8, obj.getString("Transaction_Code"));
                            amt = Double.parseDouble(obj.getString("Credit_Amount").replaceAll(",", ""));
                            stmt.setBigDecimal(9, BigDecimal.valueOf(amt));
                            stmt.setString(10, user);
                            stmt.setString(11, "10");
                            stmt.setTimestamp(12, timestamp);
                            stmt.setString(13, obj.getString("Description"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_SCB_HistoricalWTF(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME, BM_CHQNO, BM_TRANSAC, BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1 , BM_DESC)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?, ?, ?, "
                                + " ?, ? , ?, ?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {
                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "3313029652");
//                            String[] date_arr = obj.getString("Date").split("/");
//                            String newDate = "20" + date_arr[2] + date_arr[0] + date_arr[1];
                            stmt.setString(5, (obj.getString("Date")));
                            stmt.setString(6, (obj.getString("Time")));
                            stmt.setString(7, obj.getString("Cheque_Number"));
                            stmt.setString(8, obj.getString("Transaction_Code"));
                            amt = Double.parseDouble(obj.getString("Credit_Amount").replaceAll(",", ""));
                            stmt.setBigDecimal(9, BigDecimal.valueOf(amt));
                            stmt.setString(10, user);
                            stmt.setString(11, "10");
                            stmt.setTimestamp(12, timestamp);
                            stmt.setString(13, obj.getString("Description"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_SCB_Historical(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME, BM_CHQNO, BM_TRANSAC, BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1 , BM_DESC)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?, ?, ?, "
                                + " ?, ? , ?, ?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {
                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, obj.getString("Account_Number").trim());
//                            String[] date_arr = obj.getString("Date").split("/");
//                            String newDate = "20" + date_arr[2] + date_arr[0] + date_arr[1];
                            stmt.setString(5, (obj.getString("Date")));
                            stmt.setString(6, (obj.getString("Time")));
                            stmt.setString(7, obj.getString("Cheque_Number"));
                            stmt.setString(8, obj.getString("Transaction_Code"));
                            amt = Double.parseDouble(obj.getString("Credit_Amount").replaceAll(",", ""));
                            stmt.setBigDecimal(9, BigDecimal.valueOf(amt));
                            stmt.setString(10, user);
                            stmt.setString(11, "10");
                            stmt.setTimestamp(12, timestamp);
                            stmt.setString(13, obj.getString("Description"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_KBANK_IntraDay(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? )")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "3402314428");
//                            localdate = LocalDate.parse(obj.getString("Date"), formatter);
//                            stmt.setString(5, localdate.toString().replaceAll("-", ""));
                            stmt.setString(5, (obj.getString("Date")));
                            stmt.setString(6, (obj.getString("Time")));
                            stmt.setString(7, obj.getString("Channel"));
                            amt = Double.parseDouble(obj.getString("Deposit").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("Description"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_KBANK_Historical(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? )")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "3402314428");
//                            localdate = LocalDate.parse(obj.getString("Date"), formatter);
//                            stmt.setString(5, localdate.toString().replaceAll("-", ""));
                            stmt.setString(5, (obj.getString("Date")));
                            stmt.setString(6, (obj.getString("Time_Eff_Date")));
                            stmt.setString(7, obj.getString("Channel"));
                            amt = Double.parseDouble(obj.getString("Deposit").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("Description"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_SCB_RES(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA, String user) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

                    sql = "INSERT INTO " + DBPRD + ".HR_RECEIPT\n"
                            + "(HR_CONO, HR_DIVI, \n"
                            + " HC_RCNO, \n"
                            + " HC_TRDT, HC_PYNO, HC_REAMT, HC_TYPE, HC_BANK,\n"
                            + " HC_ACCODE, HC_PYDT,HC_CHKNO, HC_USER, HC_STS, \n"
                            + " HR_BKCHG, HC_LOCATION)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "', \n"
                            + "(SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2) || '000001' ) AS HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND  SUBSTRING(HC_RCNO,1,1) != '3' AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(HC_TRDT,3,2) = SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2)),\n"
                            + " '" + dateNow.trim() + "',UPPER('" + BM_CUNO.trim() + "') , '" + Amt_Include_BKCHG + "', '" + BM_TYPE.trim() + "', '" + bank.trim() + "',\n"
                            + " '1AA2105', '" + BM_DATE.trim() + "' ,'', '" + user + "', '1',\n"
                            + " '" + BM_BKCHARGE.trim() + "', '" + BM_LOCA.trim() + "' )";
//                    System.out.println(sql);
                    stmt.execute(sql);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + BM_CUNO.trim() + "') ,BM_RCNO = (SELECT HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND HC_TRDT = '" + dateNow.trim() + "'\n"
                            + "AND HC_PYNO = UPPER('" + BM_CUNO.trim() + "')\n"
                            + "AND HC_PYDT = '" + BM_DATE.trim() + "'\n"
                            + "AND HC_REAMT ='" + Amt_Include_BKCHG + "'\n"
                            + "AND HC_BANK = 'SCB'\n"
                            + "AND HC_TYPE = '" + BM_TYPE.trim() + "'\n"
                            + "AND HR_BKCHG = '" + BM_BKCHARGE.trim() + "') "
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'SCB'\n"
                            + "AND BM_USER = '" + user + "'\n"
                            + "AND BM_AMOUNT = '" + BM_AMOUNT.trim() + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
//                    System.out.println(sql2);

                    stmt.execute(sql2);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_SCBnewWTF(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                String sqlcheck = "";
                String sqlFNNO = "";

                String BM_FNNO = "";
                String sqlhead = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);

                System.out.println(BM_TYPE);
                if (BM_TYPE == null) {

                    BM_TYPE = "TRANSFER";
                }

                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

//                    sqlFNNO = "SELECT COALESCE(MAX(H_RNNO)+1,(SUBSTRING(CHAR( '" + BM_DATE + "'),2,3)+43) || '000001' ) AS H_RNNO\n"
//                            + "                         FROM " + DBPRD + ".HEAD_RECIPT  where  H_CONO = '" + cono.trim() + "' AND H_DIVI = '" + divi.trim() + "' ";
//
                    String year = BM_DATE.toString().replaceAll("-", "").substring(2, 4);

                    int yearint = Integer.parseInt(year) + 43;

                    sqlFNNO = "SELECT CASE WHEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) + 1\n"
                            + "ELSE CAST(('" + yearint + "' || '000001') AS DECIMAL(10,0)) END AS THORNO\n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT\n"
                            + "WHERE H_CONO  = '" + cono.trim() + "'\n"
                            + "AND H_DIVI  = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(H_RNNO,0,3) = '" + yearint + "'";

                    ResultSet mRes3 = stmt.executeQuery(sqlFNNO);
                    System.out.println(sqlFNNO);

                    while (mRes3.next()) {
                        BM_FNNO = mRes3.getString(1);
                    }

                    sqlhead = "INSERT INTO " + DBPRD + ".HEAD_RECIPT\n"
                            + "(H_CONO, H_DIVI, H_CUNO, H_RCNO, \n"
                            + " H_RNNO, H_PYNO , H_STS , H_LOCATION , H_TYPE)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "','HEAD', 9 || " + BM_FNNO + " ,  \n"
                            + " '" + BM_FNNO + "' ,'" + HPYNO.trim().toUpperCase() + "' , '1' , '" + BM_LOCA + "' , '" + BM_TYPE + "'\n"
                            + " \n"
                            + " )";
                    System.out.println(sqlhead);
                    stmt.execute(sqlhead);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET BM_LCODE = '1AA2201'  ,  BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + HPYNO.trim() + "') ,BM_FNNO = '" + BM_FNNO.trim() + "' \n"
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'SCB'\n"
                            + "AND BM_AMOUNT = '" + Double.parseDouble(BM_AMOUNT) + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
                System.out.println("---------- Accontaaaa -------------");

            }
        } catch (Exception e) {
            e.printStackTrace();
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

    /////NSD 
    public static void createReceipt_SCBnewNSD(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                String sqlcheck = "";
                String sqlFNNO = "";

                String BM_FNNO = "";
                String sqlhead = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);

                System.out.println(BM_TYPE);
                if (BM_TYPE == null) {

                    BM_TYPE = "TRANSFER";
                }

                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

//                    sqlFNNO = "SELECT COALESCE(MAX(H_RNNO)+1,(SUBSTRING(CHAR( '" + BM_DATE + "'),2,3)+43) || '000001' ) AS H_RNNO\n"
//                            + "                         FROM " + DBPRD + ".HEAD_RECIPT  where  H_CONO = '" + cono.trim() + "' AND H_DIVI = '" + divi.trim() + "' ";
//
                    String year = BM_DATE.toString().replaceAll("-", "").substring(2, 4);

                    int yearint = Integer.parseInt(year) + 43;

                    sqlFNNO = "SELECT CASE WHEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) + 1\n"
                            + "ELSE CAST(('" + yearint + "' || '000001') AS DECIMAL(10,0)) END AS THORNO\n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT\n"
                            + "WHERE H_CONO  = '" + cono.trim() + "'\n"
                            + "AND H_DIVI  = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(H_RNNO,0,3) = '" + yearint + "'";

                    ResultSet mRes3 = stmt.executeQuery(sqlFNNO);
                    System.out.println(sqlFNNO);

                    while (mRes3.next()) {
                        BM_FNNO = mRes3.getString(1);
                    }

                    sqlhead = "INSERT INTO " + DBPRD + ".HEAD_RECIPT\n"
                            + "(H_CONO, H_DIVI, H_CUNO, H_RCNO, \n"
                            + " H_RNNO, H_PYNO , H_STS , H_LOCATION , H_TYPE)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "','HEAD', 9 || " + BM_FNNO + " ,  \n"
                            + " '" + BM_FNNO + "' ,'" + HPYNO.trim().toUpperCase() + "' , '1' , '" + BM_LOCA + "' , '" + BM_TYPE + "'\n"
                            + " \n"
                            + " )";
                    System.out.println(sqlhead);
                    stmt.execute(sqlhead);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET BM_LCODE = '1AA2201'  ,  BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + HPYNO.trim() + "') ,BM_FNNO = '" + BM_FNNO.trim() + "' \n"
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'SCB'\n"
                            + "AND BM_AMOUNT = '" + Double.parseDouble(BM_AMOUNT) + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
                System.out.println("---------- Accontaaaa -------------");

            }
        } catch (Exception e) {
            e.printStackTrace();
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

    ////
    public static void createReceipt_SCBnew(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                String sqlcheck = "";
                String sqlFNNO = "";

                String BM_FNNO = "";
                String sqlhead = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);

                System.out.println(BM_TYPE);
                if (BM_TYPE == null) {

                    BM_TYPE = "TRANSFER";
                }

                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

//                    sqlFNNO = "SELECT COALESCE(MAX(H_RNNO)+1,(SUBSTRING(CHAR( '" + BM_DATE + "'),2,3)+43) || '000001' ) AS H_RNNO\n"
//                            + "                         FROM " + DBPRD + ".HEAD_RECIPT  where  H_CONO = '" + cono.trim() + "' AND H_DIVI = '" + divi.trim() + "' ";
//
                    String year = BM_DATE.toString().replaceAll("-", "").substring(2, 4);

                    int yearint = Integer.parseInt(year) + 43;

                    sqlFNNO = "SELECT CASE WHEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) + 1\n"
                            + "ELSE CAST(('" + yearint + "' || '000001') AS DECIMAL(10,0)) END AS THORNO\n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT\n"
                            + "WHERE H_CONO  = '" + cono.trim() + "'\n"
                            + "AND H_DIVI  = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(H_RNNO,0,3) = '" + yearint + "'";

                    ResultSet mRes3 = stmt.executeQuery(sqlFNNO);
                    System.out.println(sqlFNNO);

                    while (mRes3.next()) {
                        BM_FNNO = mRes3.getString(1);
                    }

                    sqlhead = "INSERT INTO " + DBPRD + ".HEAD_RECIPT\n"
                            + "(H_CONO, H_DIVI, H_CUNO, H_RCNO, \n"
                            + " H_RNNO, H_PYNO , H_STS , H_LOCATION , H_TYPE)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "','HEAD', 9 || " + BM_FNNO + " ,  \n"
                            + " '" + BM_FNNO + "' ,'" + HPYNO.trim().toUpperCase() + "' , '1' , '" + BM_LOCA + "' , '" + BM_TYPE + "'\n"
                            + " \n"
                            + " )";
                    System.out.println(sqlhead);
                    stmt.execute(sqlhead);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET BM_LCODE = '1AA2105'  ,  BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + HPYNO.trim() + "') ,BM_FNNO = '" + BM_FNNO.trim() + "' \n"
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'SCB'\n"
                            + "AND BM_AMOUNT = '" + Double.parseDouble(BM_AMOUNT) + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
                System.out.println("---------- Accontaaaa -------------");

            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_SCB_CURnew(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                String sqlcheck = "";
                String sqlFNNO = "";

                String BM_FNNO = "";
                String sqlhead = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);

                System.out.println(BM_TYPE);
                if (BM_TYPE == null) {

                    BM_TYPE = "TRANSFER";
                }

                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

//                    sqlFNNO = "SELECT COALESCE(MAX(H_RNNO)+1,(SUBSTRING(CHAR( '" + BM_DATE + "'),2,3)+43) || '000001' ) AS H_RNNO\n"
//                            + "                         FROM " + DBPRD + ".HEAD_RECIPT  where  H_CONO = '" + cono.trim() + "' AND H_DIVI = '" + divi.trim() + "' ";
//
                    String year = BM_DATE.toString().replaceAll("-", "").substring(2, 4);

                    int yearint = Integer.parseInt(year) + 43;

                    sqlFNNO = "SELECT CASE WHEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) + 1\n"
                            + "ELSE CAST(('" + yearint + "' || '000001') AS DECIMAL(10,0)) END AS THORNO\n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT\n"
                            + "WHERE H_CONO  = '" + cono.trim() + "'\n"
                            + "AND H_DIVI  = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(H_RNNO,0,3) = '" + yearint + "'";

                    ResultSet mRes3 = stmt.executeQuery(sqlFNNO);
                    System.out.println(sqlFNNO);

                    while (mRes3.next()) {
                        BM_FNNO = mRes3.getString(1);
                    }

                    sqlhead = "INSERT INTO " + DBPRD + ".HEAD_RECIPT\n"
                            + "(H_CONO, H_DIVI, H_CUNO, H_RCNO, \n"
                            + " H_RNNO, H_PYNO , H_STS , H_LOCATION , H_TYPE)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "','HEAD', 9 || " + BM_FNNO + " ,  \n"
                            + " '" + BM_FNNO + "' ,'" + HPYNO.trim().toUpperCase() + "' , '1' , '" + BM_LOCA + "' , '" + BM_TYPE + "'\n"
                            + " \n"
                            + " )";
                    System.out.println(sqlhead);
                    stmt.execute(sqlhead);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET BM_LCODE = '1AA2286'  ,  BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + HPYNO.trim() + "') ,BM_FNNO = '" + BM_FNNO.trim() + "' \n"
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'SCB_CUR'\n"
                            + "AND BM_AMOUNT = '" + Double.parseDouble(BM_AMOUNT) + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
                System.out.println("---------- Accontaaaa -------------");

            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_SCB(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

                    sql = "INSERT INTO " + DBPRD + ".HR_RECEIPT\n"
                            + "( HR_CONO, HR_DIVI, \n"
                            + " HC_RCNO, \n"
                            + " HC_TRDT, HC_PYNO, HC_REAMT, HC_TYPE, HC_BANK,\n"
                            + " HC_ACCODE, HC_PYDT,HC_CHKNO, HC_USER, HC_STS, \n"
                            + " HR_BKCHG, HC_LOCATION , HC_FNNO)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "', \n"
                            + "(SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2) || '000001' ) AS HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND HR_DIVI = '" + divi.trim() + "'  AND  SUBSTRING(HC_RCNO,1,1) != '3'\n"
                            + "AND SUBSTRING(HC_TRDT,3,2) = SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2)),\n"
                            + " '" + dateNow.trim() + "',UPPER('" + BM_CUNO.trim() + "') , '" + Amt_Include_BKCHG + "', '" + BM_TYPE.trim() + "', '" + bank.trim() + "',\n"
                            + " '1AA2105', '" + BM_DATE.trim() + "' ,'', '', '1',\n"
                            + " '" + BM_BKCHARGE.trim() + "', '" + BM_LOCA.trim() + "', '" + BM_TIME.trim().substring(0, 8) + "' )";
                    System.out.println(sql);
                    stmt.execute(sql);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + BM_CUNO.trim() + "') ,BM_RCNO = (SELECT HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND HC_TRDT = '" + dateNow.trim() + "'\n"
                            + "AND HC_PYNO = UPPER('" + BM_CUNO.trim() + "')\n"
                            + "AND HC_PYDT = '" + BM_DATE.trim() + "'\n"
                            + "AND HC_REAMT ='" + Amt_Include_BKCHG + "'\n"
                            + "AND HC_BANK = 'SCB'\n"
                            + "AND HC_TYPE = '" + BM_TYPE.trim() + "' AND HC_FNNO = '" + BM_TIME.trim().substring(0, 8) + "'\n"
                            + "AND HR_BKCHG = '" + BM_BKCHARGE.trim() + "') "
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'SCB'\n"
                            + "AND BM_AMOUNT = '" + BM_AMOUNT.trim() + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
                System.out.println("---------- Accontaaaa -------------");

            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_SCB_BILLnew(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                String sqlcheck = "";
                String sqlFNNO = "";

                String BM_FNNO = "";
                String sqlhead = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

//                    sqlFNNO = "SELECT COALESCE(MAX(H_RNNO)+1,SUBSTRING(REPLACE(CHAR( current date, ISO),'-',''),3,3) || '000001' ) AS H_RNNO\n"
//                            + "                         FROM " + DBPRD + ".HEAD_RECIPT  where  H_CONO = '" + cono.trim() + "' AND H_DIVI = '" + divi.trim() + "' ";
                    String year = BM_DATE.toString().replaceAll("-", "").substring(2, 4);

                    int yearint = Integer.parseInt(year) + 43;

                    sqlFNNO = "SELECT CASE WHEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) + 1\n"
                            + "ELSE CAST(('" + yearint + "' || '000001') AS DECIMAL(10,0)) END AS THORNO\n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT\n"
                            + "WHERE H_CONO  = '" + cono.trim() + "'\n"
                            + "AND H_DIVI  = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(H_RNNO,0,3) = '" + yearint + "'";

                    ResultSet mRes3 = stmt.executeQuery(sqlFNNO);
                    System.out.println(sqlFNNO);

                    while (mRes3.next()) {
                        BM_FNNO = mRes3.getString(1);
                    }

                    sqlhead = "INSERT INTO " + DBPRD + ".HEAD_RECIPT\n"
                            + "(H_CONO, H_DIVI, H_CUNO, H_RCNO, \n"
                            + " H_RNNO, H_PYNO , H_STS ,  H_LOCATION , H_TYPE)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "','HEAD', 9 || " + BM_FNNO + " ,  \n"
                            + " '" + BM_FNNO + "' ,'" + HPYNO.trim().toUpperCase() + "' , '1' , '" + BM_LOCA + "' , '" + BM_TYPE + "'\n"
                            + " \n"
                            + " )";
                    System.out.println(sqlhead);
                    stmt.execute(sqlhead);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET   BM_LCODE='1AA2283' ,  BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + HPYNO.trim() + "') ,BM_FNNO = '" + BM_FNNO.trim() + "' \n"
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'SCB_BILL'\n"
                            + "AND BM_AMOUNT = '" + Double.parseDouble(BM_AMOUNT) + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_SCB_BILL(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

                    sql = "INSERT INTO " + DBPRD + ".HR_RECEIPT\n"
                            + "(HR_CONO, HR_DIVI, \n"
                            + " HC_RCNO, \n"
                            + " HC_TRDT, HC_PYNO, HC_REAMT, HC_TYPE, HC_BANK,\n"
                            + " HC_ACCODE, HC_PYDT,HC_CHKNO, HC_USER, HC_STS, \n"
                            + " HR_BKCHG, HC_LOCATION, HC_FNNO)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "', \n"
                            + "(SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2) || '000001' ) AS HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND  SUBSTRING(HC_RCNO,1,1) != '3' AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(HC_TRDT,3,2) = SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2)),\n"
                            + " '" + dateNow.trim() + "',UPPER('" + BM_CUNO.trim() + "') , '" + Amt_Include_BKCHG + "', '" + BM_TYPE.trim() + "', '" + bank.trim() + "',\n"
                            + " '1AA2283', '" + BM_DATE.trim() + "' ,'', '', '1',\n"
                            + " '" + BM_BKCHARGE.trim() + "', '" + BM_LOCA.trim() + "' , '" + BM_TIME.trim().substring(0, 8) + "' )";
                    System.out.println(sql);
                    stmt.execute(sql);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + BM_CUNO.trim() + "') ,BM_RCNO = (SELECT HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND HC_TRDT = '" + dateNow.trim() + "'\n"
                            + "AND HC_PYNO = UPPER('" + BM_CUNO.trim() + "')\n"
                            + "AND HC_PYDT = '" + BM_DATE.trim() + "'\n"
                            + "AND HC_REAMT ='" + Amt_Include_BKCHG + "'\n"
                            + "AND HC_BANK = 'SCB_BILL'\n"
                            + "AND HC_TYPE = '" + BM_TYPE.trim() + "'  AND HC_FNNO = '" + BM_TIME.trim().substring(0, 8) + "'\n"
                            + "AND HR_BKCHG = '" + BM_BKCHARGE.trim() + "') "
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'SCB_BILL'\n"
                            + "AND BM_AMOUNT = '" + BM_AMOUNT.trim() + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_SCB_RBILLnew(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                String sqlcheck = "";
                String sqlFNNO = "";

                String BM_FNNO = "";
                String sqlhead = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

//                    sqlFNNO = "SELECT COALESCE(MAX(H_RNNO)+1,SUBSTRING(REPLACE(CHAR( current date, ISO),'-',''),3,3) || '000001' ) AS H_RNNO\n"
//                            + "                         FROM " + DBPRD + ".HEAD_RECIPT  where  H_CONO = '" + cono.trim() + "' AND H_DIVI = '" + divi.trim() + "' ";
                    String year = BM_DATE.toString().replaceAll("-", "").substring(2, 4);

                    int yearint = Integer.parseInt(year) + 43;

                    sqlFNNO = "SELECT CASE WHEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) + 1\n"
                            + "ELSE CAST(('" + yearint + "' || '000001') AS DECIMAL(10,0)) END AS THORNO\n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT\n"
                            + "WHERE H_CONO  = '" + cono.trim() + "'\n"
                            + "AND H_DIVI  = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(H_RNNO,0,3) = '" + yearint + "'";
                    ResultSet mRes3 = stmt.executeQuery(sqlFNNO);
                    System.out.println(sqlFNNO);

                    while (mRes3.next()) {
                        BM_FNNO = mRes3.getString(1);
                    }

                    sqlhead = "INSERT INTO " + DBPRD + ".HEAD_RECIPT\n"
                            + "(H_CONO, H_DIVI, H_CUNO, H_RCNO, \n"
                            + " H_RNNO, H_PYNO , H_STS , H_LOCATION , H_TYPE)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "','HEAD',9 || " + BM_FNNO + " ,  \n"
                            + " '" + BM_FNNO + "' ,'" + HPYNO.trim().toUpperCase() + "' , '1' , '" + BM_LOCA + "' , '" + BM_TYPE + "'\n"
                            + " \n"
                            + " )";
                    System.out.println(sqlhead);
                    stmt.execute(sqlhead);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET   BM_LCODE='1AA2286' ,  BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + HPYNO.trim() + "') ,BM_FNNO = '" + BM_FNNO.trim() + "' \n"
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'SCB_MMN'\n"
                            + "AND BM_AMOUNT = '" + Double.parseDouble(BM_AMOUNT) + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_SCB_RBILL(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

                    sql = "INSERT INTO " + DBPRD + ".HR_RECEIPT\n"
                            + "(HR_CONO, HR_DIVI, \n"
                            + " HC_RCNO, \n"
                            + " HC_TRDT, HC_PYNO, HC_REAMT, HC_TYPE, HC_BANK,\n"
                            + " HC_ACCODE, HC_PYDT,HC_CHKNO, HC_USER, HC_STS, \n"
                            + " HR_BKCHG, HC_LOCATION, HC_FNNO)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "', \n"
                            + "(SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2) || '000001' ) AS HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND  SUBSTRING(HC_RCNO,1,1) != '3' AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(HC_TRDT,3,2) = SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2)),\n"
                            + " '" + dateNow.trim() + "',UPPER('" + BM_CUNO.trim() + "') , '" + Amt_Include_BKCHG + "', '" + BM_TYPE.trim() + "', '" + bank.trim() + "',\n"
                            + " '1AA2286', '" + BM_DATE.trim() + "' ,'', '', '1',\n"
                            + " '" + BM_BKCHARGE.trim() + "', '" + BM_LOCA.trim() + "' , '" + BM_TIME.trim().substring(0, 8) + "' )";
//                    System.out.println(sql);
                    stmt.execute(sql);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + BM_CUNO.trim() + "') ,BM_RCNO = (SELECT HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND HC_TRDT = '" + dateNow.trim() + "'\n"
                            + "AND HC_PYNO = UPPER('" + BM_CUNO.trim() + "')\n"
                            + "AND HC_PYDT = '" + BM_DATE.trim() + "'\n"
                            + "AND HC_REAMT ='" + Amt_Include_BKCHG + "'\n"
                            + "AND HC_BANK = 'SCB_MMN'\n"
                            + "AND HC_TYPE = '" + BM_TYPE.trim() + "'  AND HC_FNNO = '" + BM_TIME.trim().substring(0, 8) + "'\n"
                            + "AND HR_BKCHG = '" + BM_BKCHARGE.trim() + "') "
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'SCB_MMN'\n"
                            + "AND BM_AMOUNT = '" + BM_AMOUNT.trim() + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);
                    System.out.println("xxxxxxlllll");

                    stmt.execute(sql2);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_KBANK_BILLnew(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {

            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//                String date = "20" + BM_DATE.substring(6, 8) + BM_DATE.substring(0, 2) + BM_DATE.substring(3, 5);
                Statement stmt = conn.createStatement();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                String sql = "";
                String sqlFNNO = "";
                String BM_FNNO = "";
                String sqlhead = "";

                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

//                    sqlFNNO = "SELECT COALESCE(MAX(H_RNNO)+1,SUBSTRING(REPLACE(CHAR( current date, ISO),'-',''),3,3) || '000001' ) AS H_RNNO\n"
//                            + "                         FROM " + DBPRD + ".HEAD_RECIPT  where  H_CONO = '" + cono.trim() + "' AND H_DIVI = '" + divi.trim() + "' ";
                    String year = BM_DATE.toString().replaceAll("-", "").substring(2, 4);

                    int yearint = Integer.parseInt(year) + 43;

                    sqlFNNO = "SELECT CASE WHEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) + 1\n"
                            + "ELSE CAST(('" + yearint + "' || '000001') AS DECIMAL(10,0)) END AS THORNO\n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT\n"
                            + "WHERE H_CONO  = '" + cono.trim() + "'\n"
                            + "AND H_DIVI  = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(H_RNNO,0,3) = '" + yearint + "'";
                    ResultSet mRes3 = stmt.executeQuery(sqlFNNO);
                    System.out.println(sqlFNNO);

                    while (mRes3.next()) {
                        BM_FNNO = mRes3.getString(1);
                    }

                    sqlhead = "INSERT INTO " + DBPRD + ".HEAD_RECIPT\n"
                            + "(H_CONO, H_DIVI, H_CUNO, H_RCNO, \n"
                            + " H_RNNO, H_PYNO , H_STS , H_LOCATION , H_TYPE)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "','HEAD',9 || " + BM_FNNO + " ,  \n"
                            + " '" + BM_FNNO + "' ,'" + HPYNO.trim().toUpperCase() + "' , '1' , '" + BM_LOCA + "' , '" + BM_TYPE + "'\n"
                            + " \n"
                            + " )";
                    System.out.println(sqlhead);
                    stmt.execute(sqlhead);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET   BM_LCODE='1AA2250' ,  BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + HPYNO.trim() + "') ,BM_FNNO = '" + BM_FNNO.trim() + "' \n"
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'KBANK_BILL'\n"
                            + "AND BM_AMOUNT = '" + Double.parseDouble(BM_AMOUNT) + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_KBANK_BILL(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

                    sql = "INSERT INTO " + DBPRD + ".HR_RECEIPT\n"
                            + "(HR_CONO, HR_DIVI, \n"
                            + " HC_RCNO, \n"
                            + " HC_TRDT, HC_PYNO, HC_REAMT, HC_TYPE, HC_BANK,\n"
                            + " HC_ACCODE, HC_PYDT,HC_CHKNO, HC_USER, HC_STS, \n"
                            + " HR_BKCHG, HC_LOCATION, HC_FNNO)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "', \n"
                            + "(SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2) || '000001' ) AS HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND  SUBSTRING(HC_RCNO,1,1) != '3' AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(HC_TRDT,3,2) = SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2)),\n"
                            + " '" + dateNow.trim() + "',UPPER('" + BM_CUNO.trim() + "') , '" + Amt_Include_BKCHG + "', '" + BM_TYPE.trim() + "', '" + bank.trim() + "',\n"
                            + " '1AA2250', '" + BM_DATE.trim() + "' ,'', '', '1',\n"
                            + " '" + BM_BKCHARGE.trim() + "', '" + BM_LOCA.trim() + "' , '" + BM_TIME.trim().substring(0, 8) + "' )";
//                    System.out.println(sql);
                    stmt.execute(sql);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + BM_CUNO.trim() + "') ,BM_RCNO = (SELECT HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND HC_TRDT = '" + dateNow.trim() + "'\n"
                            + "AND HC_PYNO = UPPER('" + BM_CUNO.trim() + "')\n"
                            + "AND HC_PYDT = '" + BM_DATE.trim() + "'\n"
                            + "AND HC_REAMT ='" + Amt_Include_BKCHG + "'\n"
                            + "AND HC_BANK = 'KBANK_BILL'\n"
                            + "AND HC_TYPE = '" + BM_TYPE.trim() + "'  AND HC_FNNO = '" + BM_TIME.trim().substring(0, 8) + "'\n"
                            + "AND HR_BKCHG = '" + BM_BKCHARGE.trim() + "') "
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'KBANK_BILL'\n"
                            + "AND BM_AMOUNT = '" + BM_AMOUNT.trim() + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
//                    System.out.println(sql2);

                    stmt.execute(sql2);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_BBL_BILLnew(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//                String date = "20" + BM_DATE.substring(6, 8) + BM_DATE.substring(0, 2) + BM_DATE.substring(3, 5);
                Statement stmt = conn.createStatement();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                String sql = "";
                String sqlFNNO = "";
                String BM_FNNO = "";
                String sqlhead = "";

                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

//                    sqlFNNO = "SELECT COALESCE(MAX(H_RNNO)+1,SUBSTRING(REPLACE(CHAR( current date, ISO),'-',''),3,3) || '000001' ) AS H_RNNO\n"
//                            + "                         FROM " + DBPRD + ".HEAD_RECIPT  where  H_CONO = '" + cono.trim() + "' AND H_DIVI = '" + divi.trim() + "' ";
                    String year = BM_DATE.toString().replaceAll("-", "").substring(2, 4);

                    int yearint = Integer.parseInt(year) + 43;

                    sqlFNNO = "SELECT CASE WHEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) + 1\n"
                            + "ELSE CAST(('" + yearint + "' || '000001') AS DECIMAL(10,0)) END AS THORNO\n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT\n"
                            + "WHERE H_CONO  = '" + cono.trim() + "'\n"
                            + "AND H_DIVI  = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(H_RNNO,0,3) = '" + yearint + "'";
                    ResultSet mRes3 = stmt.executeQuery(sqlFNNO);
                    System.out.println(sqlFNNO);

                    while (mRes3.next()) {
                        BM_FNNO = mRes3.getString(1);
                    }

                    sqlhead = "INSERT INTO " + DBPRD + ".HEAD_RECIPT\n"
                            + "(H_CONO, H_DIVI, H_CUNO, H_RCNO, \n"
                            + " H_RNNO, H_PYNO , H_STS , H_LOCATION , H_TYPE)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "','HEAD',9 || " + BM_FNNO + " ,  \n"
                            + " '" + BM_FNNO + "' ,'" + HPYNO.trim().toUpperCase() + "' , '1' , '" + BM_LOCA + "' , '" + BM_TYPE + "'\n"
                            + " \n"
                            + " )";
                    System.out.println(sqlhead);
                    stmt.execute(sqlhead);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET   BM_LCODE='1AA2214' ,  BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + HPYNO.trim() + "') ,BM_FNNO = '" + BM_FNNO.trim() + "' \n"
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'BBL_BILL'\n"
                            + "AND BM_AMOUNT = '" + Double.parseDouble(BM_AMOUNT) + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);

                    stmt.execute(sql2);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_BBL_BILL(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

                    sql = "INSERT INTO " + DBPRD + ".HR_RECEIPT\n"
                            + "(HR_CONO, HR_DIVI, \n"
                            + " HC_RCNO, \n"
                            + " HC_TRDT, HC_PYNO, HC_REAMT, HC_TYPE, HC_BANK,\n"
                            + " HC_ACCODE, HC_PYDT,HC_CHKNO, HC_USER, HC_STS, \n"
                            + " HR_BKCHG, HC_LOCATION , HC_FNNO)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "', \n"
                            + "(SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2) || '000001' ) AS HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND  SUBSTRING(HC_RCNO,1,1) != '3' AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(HC_TRDT,3,2) = SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2)),\n"
                            + " '" + dateNow.trim() + "',UPPER('" + BM_CUNO.trim() + "') , '" + Amt_Include_BKCHG + "', '" + BM_TYPE.trim() + "', '" + bank.trim() + "',\n"
                            + " '1AA2214', '" + BM_DATE.trim() + "' ,'', '', '1',\n"
                            + " '" + BM_BKCHARGE.trim() + "', '" + BM_LOCA.trim() + "', '" + BM_TIME.trim().substring(0, 8) + "' )";
//                    System.out.println(sql);
                    stmt.execute(sql);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + BM_CUNO.trim() + "') ,BM_RCNO = (SELECT HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND HC_TRDT = '" + dateNow.trim() + "'\n"
                            + "AND HC_PYNO = UPPER('" + BM_CUNO.trim() + "')\n"
                            + "AND HC_PYDT = '" + BM_DATE.trim() + "'\n"
                            + "AND HC_REAMT ='" + Amt_Include_BKCHG + "'\n"
                            + "AND HC_BANK = 'BBL_BILL'\n"
                            + "AND HC_TYPE = '" + BM_TYPE.trim() + "' AND HC_FNNO = '" + BM_TIME.trim().substring(0, 8) + "'\n"
                            + "AND HR_BKCHG = '" + BM_BKCHARGE.trim() + "') "
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'BBL_BILL'\n"
                            + "AND BM_AMOUNT = '" + BM_AMOUNT.trim() + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
//                    System.out.println(sql2);

                    stmt.execute(sql2);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_BBLnew(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                String sqlFNNO = "";
                String BM_FNNO = "";
                String sqlhead = "";
                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

//                    sqlFNNO = "SELECT COALESCE(MAX(H_RNNO)+1,SUBSTRING(REPLACE(CHAR( current date, ISO),'-',''),3,3) || '000001' ) AS H_RNNO\n"
//                            + "                         FROM " + DBPRD + ".HEAD_RECIPT  where  H_CONO = '" + cono.trim() + "' AND H_DIVI = '" + divi.trim() + "' ";
                    String year = BM_DATE.toString().replaceAll("-", "").substring(2, 4);

                    int yearint = Integer.parseInt(year) + 43;

                    sqlFNNO = "SELECT CASE WHEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) + 1\n"
                            + "ELSE CAST(('" + yearint + "' || '000001') AS DECIMAL(10,0)) END AS THORNO\n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT\n"
                            + "WHERE H_CONO  = '" + cono.trim() + "'\n"
                            + "AND H_DIVI  = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(H_RNNO,0,3) = '" + yearint + "'";
                    ResultSet mRes3 = stmt.executeQuery(sqlFNNO);
                    System.out.println(sqlFNNO);

                    while (mRes3.next()) {
                        BM_FNNO = mRes3.getString(1);
                    }

                    sqlhead = "INSERT INTO " + DBPRD + ".HEAD_RECIPT\n"
                            + "(H_CONO, H_DIVI, H_CUNO, H_RCNO, \n"
                            + " H_RNNO, H_PYNO , H_STS , H_LOCATION , H_TYPE)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "','HEAD',9 || " + BM_FNNO + " ,  \n"
                            + " '" + BM_FNNO + "' ,'" + HPYNO.trim().toUpperCase() + "' , '1' , '" + BM_LOCA + "' , '" + BM_TYPE + "'\n"
                            + " \n"
                            + " )";
                    System.out.println(sqlhead);
                    stmt.execute(sqlhead);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET   BM_LCODE='1AA2114' ,  BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + HPYNO.trim() + "') ,BM_FNNO = '" + BM_FNNO.trim() + "' \n"
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'BBL'\n"
                            + "AND BM_AMOUNT = '" + Double.parseDouble(BM_AMOUNT) + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    
    //*****************createReceipt_BBL_QRnew *****************
    
    
            
            
    public static void createReceipt_BBL_QRnew(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                String sqlFNNO = "";
                String BM_FNNO = "";
                String sqlhead = "";
                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

//                    sqlFNNO = "SELECT COALESCE(MAX(H_RNNO)+1,SUBSTRING(REPLACE(CHAR( current date, ISO),'-',''),3,3) || '000001' ) AS H_RNNO\n"
//                            + "                         FROM " + DBPRD + ".HEAD_RECIPT  where  H_CONO = '" + cono.trim() + "' AND H_DIVI = '" + divi.trim() + "' ";
                    String year = BM_DATE.toString().replaceAll("-", "").substring(2, 4);

                    int yearint = Integer.parseInt(year) + 43;

                    sqlFNNO = "SELECT CASE WHEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) + 1\n"
                            + "ELSE CAST(('" + yearint + "' || '000001') AS DECIMAL(10,0)) END AS THORNO\n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT\n"
                            + "WHERE H_CONO  = '" + cono.trim() + "'\n"
                            + "AND H_DIVI  = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(H_RNNO,0,3) = '" + yearint + "'";
                    ResultSet mRes3 = stmt.executeQuery(sqlFNNO);
                    System.out.println(sqlFNNO);

                    while (mRes3.next()) {
                        BM_FNNO = mRes3.getString(1);
                    }

                    sqlhead = "INSERT INTO " + DBPRD + ".HEAD_RECIPT\n"
                            + "(H_CONO, H_DIVI, H_CUNO, H_RCNO, \n"
                            + " H_RNNO, H_PYNO , H_STS , H_LOCATION , H_TYPE)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "','HEAD',9 || " + BM_FNNO + " ,  \n"
                            + " '" + BM_FNNO + "' ,'" + HPYNO.trim().toUpperCase() + "' , '1' , '" + BM_LOCA + "' , '" + BM_TYPE + "'\n"
                            + " \n"
                            + " )";
                    System.out.println(sqlhead);
                    stmt.execute(sqlhead);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET   BM_LCODE='1AA2214' ,  BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + HPYNO.trim() + "') ,BM_FNNO = '" + BM_FNNO.trim() + "' \n"
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'BBL_QR'\n"
                            + "AND BM_AMOUNT = '" + Double.parseDouble(BM_AMOUNT) + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    
    
    //******************************************
    

    public static void createReceipt_BBL_CURnew(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                String sqlFNNO = "";
                String BM_FNNO = "";
                String sqlhead = "";
                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

//                    sqlFNNO = "SELECT COALESCE(MAX(H_RNNO)+1,SUBSTRING(REPLACE(CHAR( current date, ISO),'-',''),3,3) || '000001' ) AS H_RNNO\n"
//                            + "                         FROM " + DBPRD + ".HEAD_RECIPT  where  H_CONO = '" + cono.trim() + "' AND H_DIVI = '" + divi.trim() + "' ";
                    String year = BM_DATE.toString().replaceAll("-", "").substring(2, 4);

                    int yearint = Integer.parseInt(year) + 43;

                    sqlFNNO = "SELECT CASE WHEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) + 1\n"
                            + "ELSE CAST(('" + yearint + "' || '000001') AS DECIMAL(10,0)) END AS THORNO\n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT\n"
                            + "WHERE H_CONO  = '" + cono.trim() + "'\n"
                            + "AND H_DIVI  = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(H_RNNO,0,3) = '" + yearint + "'";
                    ResultSet mRes3 = stmt.executeQuery(sqlFNNO);
                    System.out.println(sqlFNNO);

                    while (mRes3.next()) {
                        BM_FNNO = mRes3.getString(1);
                    }

                    sqlhead = "INSERT INTO " + DBPRD + ".HEAD_RECIPT\n"
                            + "(H_CONO, H_DIVI, H_CUNO, H_RCNO, \n"
                            + " H_RNNO, H_PYNO , H_STS , H_LOCATION , H_TYPE)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "','HEAD',9 || " + BM_FNNO + " ,  \n"
                            + " '" + BM_FNNO + "' ,'" + HPYNO.trim().toUpperCase() + "' , '1' , '" + BM_LOCA + "' , '" + BM_TYPE + "'\n"
                            + " \n"
                            + " )";
                    System.out.println(sqlhead);
                    stmt.execute(sqlhead);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET   BM_LCODE='1AA2214' ,  BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + HPYNO.trim() + "') ,BM_FNNO = '" + BM_FNNO.trim() + "' \n"
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'BBL_CUR'\n"
                            + "AND BM_AMOUNT = '" + Double.parseDouble(BM_AMOUNT) + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_BBLnewWTF(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                String sqlFNNO = "";
                String BM_FNNO = "";
                String sqlhead = "";
                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

                    sqlFNNO = "SELECT COALESCE(MAX(H_RNNO)+1,SUBSTRING(REPLACE(CHAR( current date, ISO),'-',''),3,3) || '000001' ) AS H_RNNO\n"
                            + "                         FROM " + DBPRD + ".HEAD_RECIPT  where  H_CONO = '" + cono.trim() + "' AND H_DIVI = '" + divi.trim() + "' ";

                    ResultSet mRes3 = stmt.executeQuery(sqlFNNO);
                    System.out.println(sqlFNNO);

                    while (mRes3.next()) {
                        BM_FNNO = mRes3.getString(1);
                    }

                    sqlhead = "INSERT INTO " + DBPRD + ".HEAD_RECIPT\n"
                            + "(H_CONO, H_DIVI, H_CUNO, H_RCNO, \n"
                            + " H_RNNO, H_PYNO , H_STS , H_LOCATION , H_TYPE)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "','HEAD',9 || " + BM_FNNO + " ,  \n"
                            + " '" + BM_FNNO + "' ,'" + HPYNO.trim().toUpperCase() + "' , '1' , '" + BM_LOCA + "' , '" + BM_TYPE + "'\n"
                            + " \n"
                            + " )";
                    System.out.println(sqlhead);
                    stmt.execute(sqlhead);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET   BM_LCODE='1AA2202' ,  BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + HPYNO.trim() + "') ,BM_FNNO = '" + BM_FNNO.trim() + "' \n"
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'BBL'\n"
                            + "AND BM_AMOUNT = '" + Double.parseDouble(BM_AMOUNT) + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_BBL(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String sql = "";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

                    sql = "INSERT INTO " + DBPRD + ".HR_RECEIPT\n"
                            + "(HR_CONO, HR_DIVI, \n"
                            + " HC_RCNO, \n"
                            + " HC_TRDT, HC_PYNO, HC_REAMT, HC_TYPE, HC_BANK,\n"
                            + " HC_ACCODE, HC_PYDT,HC_CHKNO, HC_USER, HC_STS, \n"
                            + " HR_BKCHG, HC_LOCATION, HC_FNNO)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "', \n"
                            + "(SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2) || '000001' ) AS HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND  SUBSTRING(HC_RCNO,1,1) != '3' AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(HC_TRDT,3,2) = SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2)),\n"
                            + " '" + dateNow.trim() + "',UPPER('" + BM_CUNO.trim() + "') , '" + Amt_Include_BKCHG + "', '" + BM_TYPE.trim() + "', '" + bank.trim() + "',\n"
                            + " '1AA2114', '" + BM_DATE.trim() + "' ,'', '', '1',\n"
                            + " '" + BM_BKCHARGE.trim() + "', '" + BM_LOCA.trim() + "' , '" + BM_TIME.trim().substring(0, 8) + "' )";
//                    System.out.println(sql);
                    stmt.execute(sql);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + BM_CUNO.trim() + "') ,BM_RCNO = (SELECT HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND HC_TRDT = '" + dateNow.trim() + "'\n"
                            + "AND HC_PYNO = UPPER('" + BM_CUNO.trim() + "')\n"
                            + "AND HC_PYDT = '" + BM_DATE.trim() + "'\n"
                            + "AND HC_REAMT ='" + Amt_Include_BKCHG + "'\n"
                            + "AND HC_BANK = 'BBL'\n"
                            + "AND HC_TYPE = '" + BM_TYPE.trim() + "' AND HC_FNNO = '" + BM_TIME.trim().substring(0, 8) + "' \n"
                            + "AND HR_BKCHG = '" + BM_BKCHARGE.trim() + "') "
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'BBL'\n"
                            + "AND BM_AMOUNT = '" + BM_AMOUNT.trim() + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
//                    System.out.println(sql2);

                    stmt.execute(sql2);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_KBANKnew(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//                String date = "20" + BM_DATE.substring(6, 8) + BM_DATE.substring(0, 2) + BM_DATE.substring(3, 5);
                Statement stmt = conn.createStatement();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                String sql = "";
                String sqlFNNO = "";
                String BM_FNNO = "";
                String sqlhead = "";

                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

//                    sqlFNNO = "SELECT COALESCE(MAX(H_RNNO)+1,SUBSTRING(REPLACE(CHAR( current date, ISO),'-',''),3,3) || '000001' ) AS H_RNNO\n"
//                            + "                         FROM " + DBPRD + ".HEAD_RECIPT  where  H_CONO = '" + cono.trim() + "' AND H_DIVI = '" + divi.trim() + "' ";
                    String year = BM_DATE.toString().replaceAll("-", "").substring(2, 4);

                    int yearint = Integer.parseInt(year) + 43;

                    sqlFNNO = "SELECT CASE WHEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) + 1\n"
                            + "ELSE CAST(('" + yearint + "' || '000001') AS DECIMAL(10,0)) END AS THORNO\n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT\n"
                            + "WHERE H_CONO  = '" + cono.trim() + "'\n"
                            + "AND H_DIVI  = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(H_RNNO,0,3) = '" + yearint + "'";
                    ResultSet mRes3 = stmt.executeQuery(sqlFNNO);
                    System.out.println(sqlFNNO);

                    while (mRes3.next()) {
                        BM_FNNO = mRes3.getString(1);
                    }

                    sqlhead = "INSERT INTO " + DBPRD + ".HEAD_RECIPT\n"
                            + "(H_CONO, H_DIVI, H_CUNO, H_RCNO, \n"
                            + " H_RNNO, H_PYNO , H_STS , H_LOCATION , H_TYPE )\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "','HEAD',9 || " + BM_FNNO + " ,  \n"
                            + " '" + BM_FNNO + "' ,'" + HPYNO.trim().toUpperCase() + "' , '1' , '" + BM_LOCA + "', '" + BM_TYPE + "'\n"
                            + " \n"
                            + " )";
                    System.out.println(sqlhead);
                    stmt.execute(sqlhead);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET  BM_LCODE='1AA2110' ,  BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + HPYNO.trim() + "') ,BM_FNNO = '" + BM_FNNO.trim() + "' \n"
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'KBANK'\n"
                            + "AND BM_AMOUNT = '" + Double.parseDouble(BM_AMOUNT) + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);

                    stmt.execute(sql2);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_KBANK_CURnew(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//                String date = "20" + BM_DATE.substring(6, 8) + BM_DATE.substring(0, 2) + BM_DATE.substring(3, 5);
                Statement stmt = conn.createStatement();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                String sql = "";
                String sqlFNNO = "";
                String BM_FNNO = "";
                String sqlhead = "";

                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

//                    sqlFNNO = "SELECT COALESCE(MAX(H_RNNO)+1,SUBSTRING(REPLACE(CHAR( current date, ISO),'-',''),3,3) || '000001' ) AS H_RNNO\n"
//                            + "                         FROM " + DBPRD + ".HEAD_RECIPT  where  H_CONO = '" + cono.trim() + "' AND H_DIVI = '" + divi.trim() + "' ";
                    String year = BM_DATE.toString().replaceAll("-", "").substring(2, 4);

                    int yearint = Integer.parseInt(year) + 43;

                    sqlFNNO = "SELECT CASE WHEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) + 1\n"
                            + "ELSE CAST(('" + yearint + "' || '000001') AS DECIMAL(10,0)) END AS THORNO\n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT\n"
                            + "WHERE H_CONO  = '" + cono.trim() + "'\n"
                            + "AND H_DIVI  = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(H_RNNO,0,3) = '" + yearint + "'";
                    ResultSet mRes3 = stmt.executeQuery(sqlFNNO);
                    System.out.println(sqlFNNO);

                    while (mRes3.next()) {
                        BM_FNNO = mRes3.getString(1);
                    }

                    sqlhead = "INSERT INTO " + DBPRD + ".HEAD_RECIPT\n"
                            + "(H_CONO, H_DIVI, H_CUNO, H_RCNO, \n"
                            + " H_RNNO, H_PYNO , H_STS , H_LOCATION , H_TYPE )\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "','HEAD',9 || " + BM_FNNO + " ,  \n"
                            + " '" + BM_FNNO + "' ,'" + HPYNO.trim().toUpperCase() + "' , '1' , '" + BM_LOCA + "', '" + BM_TYPE + "'\n"
                            + " \n"
                            + " )";
                    System.out.println(sqlhead);
                    stmt.execute(sqlhead);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET  BM_LCODE='1AA2250' ,  BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + HPYNO.trim() + "') ,BM_FNNO = '" + BM_FNNO.trim() + "' \n"
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'KBANK_CUR'\n"
                            + "AND BM_AMOUNT = '" + Double.parseDouble(BM_AMOUNT) + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
                    System.out.println(sql2);

                    stmt.execute(sql2);

                    stmt.execute(sql2);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_KBANK(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//                String date = "20" + BM_DATE.substring(6, 8) + BM_DATE.substring(0, 2) + BM_DATE.substring(3, 5);
                Statement stmt = conn.createStatement();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                String sql = "";
                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

                    sql = "INSERT INTO " + DBPRD + ".HR_RECEIPT\n"
                            + "(HR_CONO, HR_DIVI, \n"
                            + " HC_RCNO, \n"
                            + " HC_TRDT, HC_PYNO, HC_REAMT, HC_TYPE, HC_BANK,\n"
                            + " HC_ACCODE, HC_PYDT,HC_CHKNO, HC_USER, HC_STS, \n"
                            + " HR_BKCHG, HC_LOCATION , HC_FNNO)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "', \n"
                            + "(SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2) || '000001' ) AS HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND  SUBSTRING(HC_RCNO,1,1) != '3' AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(HC_TRDT,3,2) = SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2)),\n"
                            + " '" + dateNow.trim() + "',UPPER('" + BM_CUNO.trim() + "') , '" + Amt_Include_BKCHG + "', '" + BM_TYPE.trim() + "', '" + bank.trim() + "',\n"
                            + " '1AA2110', '" + BM_DATE.trim() + "' ,'', '', '1',\n"
                            + " '" + BM_BKCHARGE.trim() + "', '" + BM_LOCA.trim() + "', '" + BM_TIME.trim().substring(0, 8) + "' )";
//                    System.out.println(sql);
                    stmt.execute(sql);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + BM_CUNO.trim() + "') ,BM_RCNO = (SELECT HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND HC_TRDT = '" + dateNow.trim() + "'\n"
                            + "AND HC_PYNO = UPPER('" + BM_CUNO.trim() + "')\n"
                            + "AND HC_PYDT = '" + BM_DATE.trim() + "'\n"
                            + "AND HC_REAMT ='" + Amt_Include_BKCHG + "'\n"
                            + "AND HC_BANK = 'KBANK'\n"
                            + "AND HC_TYPE = '" + BM_TYPE.trim() + "' AND HC_FNNO = '" + BM_TIME.trim().substring(0, 8) + "'\n"
                            + "AND HR_BKCHG = '" + BM_BKCHARGE.trim() + "') "
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'KBANK'\n"
                            + "AND BM_AMOUNT = '" + BM_AMOUNT.trim() + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
//                    System.out.println(sql2);

                    stmt.execute(sql2);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void createReceipt_KBANK_RES(String cono, String divi, String bank, String BM_DATE, String BM_TIME, String BM_AMOUNT,
            String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_TYPE, String BM_LOCA, String user) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//                String date = "20" + BM_DATE.substring(6, 8) + BM_DATE.substring(0, 2) + BM_DATE.substring(3, 5);
                Statement stmt = conn.createStatement();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", new Locale("en"));
                Date date = new Date();
                String dateNow = formatter.format(date);
                String sql = "";
                try {
                    Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

                    sql = "INSERT INTO " + DBPRD + ".HR_RECEIPT\n"
                            + "(HR_CONO, HR_DIVI, \n"
                            + " HC_RCNO, \n"
                            + " HC_TRDT, HC_PYNO, HC_REAMT, HC_TYPE, HC_BANK,\n"
                            + " HC_ACCODE, HC_PYDT,HC_CHKNO, HC_USER, HC_STS, \n"
                            + " HR_BKCHG, HC_LOCATION)\n"
                            + "VALUES('" + cono.trim() + "', '" + divi.trim() + "', \n"
                            + "(SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2) || '000001' ) AS HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "AND  SUBSTRING(HC_RCNO,1,1) != '3' WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND SUBSTRING(HC_TRDT,3,2) = SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2)),\n"
                            + " '" + dateNow.trim() + "',UPPER('" + BM_CUNO.trim() + "') , '" + Amt_Include_BKCHG + "', '" + BM_TYPE.trim() + "', '" + bank.trim() + "',\n"
                            + " '1AA2110', '" + BM_DATE.trim() + "' ,'', '" + user.trim() + "', '1',\n"
                            + " '" + BM_BKCHARGE.trim() + "', '" + BM_LOCA.trim() + "' )";
//                    System.out.println(sql);
                    stmt.execute(sql);

                    String sql2 = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                            + "SET BM_CNDN = '" + BM_CNDN.trim() + "' , BM_OVPAY = '" + BM_OVPAY.trim() + "' ,BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "' ,BM_CUNO = UPPER('" + BM_CUNO.trim() + "') ,BM_RCNO = (SELECT HC_RCNO \n"
                            + "FROM " + DBPRD + ".HR_RECEIPT\n"
                            + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                            + "AND HR_DIVI = '" + divi.trim() + "'\n"
                            + "AND HC_TRDT = '" + dateNow.trim() + "'\n"
                            + "AND HC_PYNO = UPPER('" + BM_CUNO.trim() + "')\n"
                            + "AND HC_PYDT = '" + BM_DATE.trim() + "'\n"
                            + "AND HC_REAMT ='" + Amt_Include_BKCHG + "'\n"
                            + "AND HC_BANK = 'KBANK'\n"
                            + "AND HC_USER = '" + user.trim() + "'\n"
                            + "AND HC_TYPE = '" + BM_TYPE.trim() + "'\n"
                            + "AND HR_BKCHG = '" + BM_BKCHARGE.trim() + "') "
                            + "WHERE BM_CONO = '" + cono.trim() + "'\n"
                            + "AND BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND BM_DATE  = '" + BM_DATE.trim() + "'\n"
                            + "AND BM_BANK = 'KBANK'\n"
                            + "AND BM_AMOUNT = '" + BM_AMOUNT.trim() + "'\n"
                            + "AND BM_TIME = '" + BM_TIME.trim() + "' \n"
                            + "AND BM_ID = '" + BM_ID.trim() + "' ";
//                    System.out.println(sql2);

                    stmt.execute(sql2);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static String timeConversion(String s) {
        int hour = Integer.parseInt(s.substring(0, 2)) % 12;
        if (s.endsWith("PM")) {
            hour += 12;
        }
        return String.format("%02d", hour) + s.substring(2, 8);
    }

    public static void upload_1AA2250_IntraDay(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

        System.out.println("test valur ");

        System.out.println(cono);
        System.out.println(divi);
        System.out.println(bank);
        System.out.println(user);
        System.out.println(myJsonData);

        System.out.println("test valur ");

        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC ,REF2,REF3,BM_REFCU1)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,? ,? ,? )")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "3401018025");
                            stmt.setString(5, (obj.getString("Transaction_Date")));
                            stmt.setString(6, (obj.getString("Transaction_Time")));
                            stmt.setString(7, "-");
                            amt = Double.parseDouble(obj.getString("Amount").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("Payer_Name"));
                            stmt.setString(13, obj.getString("Reference2"));
                            stmt.setString(14, obj.getString("Reference3"));
                            stmt.setString(15, obj.getString("Reference1"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_1AA2250_Historical(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

        System.out.println("bdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbd");
        System.out.println(myJsonData);
        System.out.println(bank);
        System.out.println("bdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbd");

        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC,REF2,REF3,BM_REFCU1)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,? ,? ,? )")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "3401018025");
                            stmt.setString(5, obj.getString("Transaction_Date"));
                            stmt.setString(6, (obj.getString("Transaction_Time")));
                            stmt.setString(7, "-");
                            amt = Double.parseDouble(obj.getString("Amount").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("Payer_Name"));
                            stmt.setString(13, obj.getString("Reference2"));
                            stmt.setString(14, obj.getString("Reference3"));
                            stmt.setString(15, obj.getString("Reference1"));
                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_1AA2114_IntraDay(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

        System.out.println("bdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbd");
        System.out.println(cono);
        System.out.println(divi);
        System.out.println(myJsonData);
        System.out.println(bank);
        System.out.println(user);
        System.out.println("bdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbd");

        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC , BM_REFCU1 , SENDER)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,?,? )")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "1227360581");
                            stmt.setString(5, obj.getString("Tran_Date"));
                            stmt.setString(6, obj.getString("time"));
                            stmt.setString(7, "-");
                            amt = Double.parseDouble(obj.getString("Credit").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("Description"));
                            stmt.setString(13, obj.getString("Sender_Account_Name"));
                            stmt.setString(14, obj.getString("Sender_Branch"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_1AA2114_Historical(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC ,BM_REFCU1,SENDER)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,? ,?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "1227360581");
                            stmt.setString(5, obj.getString("Tran_Date"));
                            stmt.setString(6, obj.getString("time"));
                            stmt.setString(7, "-");
                            amt = Double.parseDouble(obj.getString("Credit").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("Description"));
                            stmt.setString(13, obj.getString("Sender_Account_Name"));
                            stmt.setString(14, obj.getString("Sender_Bank"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_BBL_IntraDayWTF(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

        System.out.println("bdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbd");
        System.out.println(cono);
        System.out.println(divi);
        System.out.println(myJsonData);
        System.out.println(bank);
        System.out.println(user);
        System.out.println("bdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbd");

        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC , BM_REFCU1 , SENDER)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,?,? )")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "1923053068");
                            stmt.setString(5, obj.getString("Tran_Date"));
                            stmt.setString(6, obj.getString("time"));
                            stmt.setString(7, "-");
                            amt = Double.parseDouble(obj.getString("Credit").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("Description"));
                            stmt.setString(13, obj.getString("Sender_Account_Name"));
                            stmt.setString(14, obj.getString("Sender_Branch"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_BBL_HistoricalWTF(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC ,BM_REFCU1,SENDER)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,? ,?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "1923053068");
                            stmt.setString(5, obj.getString("Tran_Date"));
                            stmt.setString(6, obj.getString("time"));
                            stmt.setString(7, "-");
                            amt = Double.parseDouble(obj.getString("Credit").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("Description"));
                            stmt.setString(13, obj.getString("Sender_Account_Name"));
                            stmt.setString(14, obj.getString("Sender_Bank"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    //***********************BBL QR*****************************
    //todo insert 
    public static void upload_BBLQR_IntraDay(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

        System.out.println(myJsonData);

        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC , BM_REFCU1 , REF2 , REF3 , SENDER , REF4,BM_LCODE )\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,? , ? , ? , ?, ?,?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "1223098219");
                            stmt.setString(5, obj.getString("PAY_DATE"));
                            stmt.setString(6, (obj.getString("PAY_TIME")));
                            stmt.setString(7, "-");
                            amt = Double.parseDouble(obj.getString("AMOUNT_").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("CUSTOMER_NAME"));
                            stmt.setString(13, obj.getString("REF_1")); //
                            stmt.setString(14, obj.getString("REF_2"));
                            stmt.setString(15, obj.getString("REF_3"));
                            stmt.setString(16, obj.getString("PAY_BY"));
                            stmt.setString(17, obj.getString("FR_BR__"));
                            stmt.setString(18, "1AA2214");

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_BBLQR_Historical(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC , BM_REFCU1 , REF2 , REF3 , SENDER , REF4 ,BM_LCODE )\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,? , ? , ? , ?, ?,?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "1223098219");
                            stmt.setString(5, obj.getString("PAY_DATE"));
                            stmt.setString(6, (obj.getString("PAY_TIME")));
                            stmt.setString(7, "-");
                            amt = Double.parseDouble(obj.getString("AMOUNT").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("CUSTOMER_NAME"));
                            stmt.setString(13, obj.getString("CUSTOMER_NO_"));
                            stmt.setString(14, obj.getString("REFERENCE_NO_"));
                            stmt.setString(15, "-");
                            stmt.setString(16, obj.getString("BY"));
                            stmt.setString(17, obj.getString("FR_BR_"));
                            stmt.setString(18, "1AA2214");

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    //*********************************************************
    public static void upload_1AA2214_IntraDay(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC , BM_REFCU1 , REF2 , REF3 , SENDER , REF4 )\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,? , ? , ? , ?, ?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "1223098219");
                            stmt.setString(5, obj.getString("PAY_DATE"));
                            stmt.setString(6, (obj.getString("PAY_TIME")));
                            stmt.setString(7, "-");
                            amt = Double.parseDouble(obj.getString("AMOUNT_").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("CUSTOMER_NAME"));
                            stmt.setString(13, obj.getString("CUSTOMER_NO_"));
                            stmt.setString(14, obj.getString("REFERENCE_NO_"));
                            stmt.setString(15, obj.getString("REFERENCE_NO_3"));
                            stmt.setString(16, obj.getString("BY_"));
                            stmt.setString(17, obj.getString("FR_BR_"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_1AA2214_Historical(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC , BM_REFCU1 )\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,? )")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "1223098219");
                            stmt.setString(5, obj.getString("PAY_DATE"));
                            stmt.setString(6, (obj.getString("PAY_TIME")));
                            stmt.setString(7, "-");
                            amt = Double.parseDouble(obj.getString("AMOUNT").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("CUSTOMER_NAME"));
                            stmt.setString(13, obj.getString("CUSTOMER_NO_"));
//                            stmt.setString(14, obj.getString("Sender_Bank"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_1AA2286_IntraDay(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

        System.out.println("Check value mae manee  intraday");

        System.out.println(cono);
        System.out.println(divi);
        System.out.println(bank);
        System.out.println(user);
        System.out.println(myJsonData);

        System.out.println("Check value mae manee  intraday");

        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC,BM_REFCU1)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "3313019322");
                            stmt.setString(5, obj.getString("Date"));
                            stmt.setString(6, (obj.getString("Time")));
                            stmt.setString(7, obj.getString("Channel"));
                            amt = Double.parseDouble(obj.getString("Amount").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("Customer_Name"));
                            stmt.setString(13, obj.getString("Ref2"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_1AA2286_Historical(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC, BM_REFCU1)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "3313019322");
                            stmt.setString(5, obj.getString("Date"));
                            stmt.setString(6, (obj.getString("Time")));
                            stmt.setString(7, "-");
                            amt = Double.parseDouble(obj.getString("Amount").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("Customer_Name"));
                            stmt.setString(13, obj.getString("Ref2"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_1AA2283_IntraDay(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC, BM_REFCU1, BM_CUNA)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,?,?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "3313019398");
                            stmt.setString(5, obj.getString("Date"));
                            stmt.setString(6, (obj.getString("Time")));
                            stmt.setString(7, "-");
                            amt = Double.parseDouble(obj.getString("Amount").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("CustomerNumber/Ref1"));
                            stmt.setString(13, obj.getString("Ref2"));
                            stmt.setString(14, obj.getString("Customer_Name"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_1AA2283_Historical(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC, BM_REFCU1,BM_CUNA)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,?,?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "3313019398");
                            stmt.setString(5, obj.getString("Date"));
                            stmt.setString(6, (obj.getString("Time")));
                            stmt.setString(7, "-");
                            amt = Double.parseDouble(obj.getString("Amount").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("Customer"));
                            stmt.setString(13, obj.getString("Ref2"));
                            stmt.setString(14, obj.getString("Customer_Name"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    //----------------------- CURRENT---------------------
    public static void upload_SCBCUR_Intraday(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;

                System.out.println("uploaddddddddddddddddddd");
                System.out.println(conn);
                System.out.println(divi);
                System.out.println(user);
                System.out.println(myJsonData);

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME, BM_CHQNO, BM_TRANSAC, BM_BRANCH, BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1 , BM_DESC)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?, ?, ?, ?,"
                                + " ?, ? , ? , ?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {
                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, obj.getString("Account_Number").trim());
// 11/12/22
//                            String[] date_arr = obj.getString("Date").split("/");
//                            String newDate = "20" + date_arr[2] + date_arr[0] + date_arr[1];
                            stmt.setString(5, (obj.getString("Date")));
                            stmt.setString(6, (obj.getString("Time")));
                            stmt.setString(7, obj.getString("Cheque_Number"));
                            stmt.setString(8, obj.getString("Transaction_Code"));
                            stmt.setString(9, obj.getString("Branch_Number"));
                            amt = Double.parseDouble(obj.getString("Amount").replaceAll(",", ""));
                            stmt.setBigDecimal(10, BigDecimal.valueOf(amt));
                            stmt.setString(11, user);
                            stmt.setString(12, "10");
                            stmt.setTimestamp(13, timestamp);
                            stmt.setString(14, obj.getString("Description").trim());
                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_SCBCUR_Historical(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME, BM_CHQNO, BM_TRANSAC, BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1 , BM_DESC)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?, ?, ?, "
                                + " ?, ? , ?, ?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {
                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, obj.getString("AccountNumber").trim());
//                            String[] date_arr = obj.getString("Date").split("/");
//                            String newDate = "20" + date_arr[2] + date_arr[0] + date_arr[1];
                            stmt.setString(5, (obj.getString("Date")));
                            stmt.setString(6, (obj.getString("Time")));
                            stmt.setString(7, obj.getString("Cheque_Number"));
                            stmt.setString(8, obj.getString("Transaction_Code"));
                            amt = Double.parseDouble(obj.getString("Credit_Amount").replaceAll(",", ""));
                            stmt.setBigDecimal(9, BigDecimal.valueOf(amt));
                            stmt.setString(10, user);
                            stmt.setString(11, "10");
                            stmt.setTimestamp(12, timestamp);
                            stmt.setString(13, obj.getString("Description"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_KBANKCUR_IntraDay(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? )")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "3401018025");
//                            localdate = LocalDate.parse(obj.getString("Date"), formatter);
//                            stmt.setString(5, localdate.toString().replaceAll("-", ""));
                            stmt.setString(5, (obj.getString("Date")));
                            stmt.setString(6, (obj.getString("Time")));
                            stmt.setString(7, obj.getString("Channel"));
                            amt = Double.parseDouble(obj.getString("Deposit").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("Description"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_KBANKCUR_Historical(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? )")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "3401018025");
//                            localdate = LocalDate.parse(obj.getString("Date"), formatter);
//                            stmt.setString(5, localdate.toString().replaceAll("-", ""));
                            stmt.setString(5, (obj.getString("Date")));
                            stmt.setString(6, (obj.getString("Time_Ent_Date")));
                            stmt.setString(7, obj.getString("Channel"));
                            amt = Double.parseDouble(obj.getString("Deposit").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("Description"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_BBLCUR_IntraDay(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

        System.out.println("bdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbd");
        System.out.println(cono);
        System.out.println(divi);
        System.out.println(myJsonData);
        System.out.println(bank);
        System.out.println(user);
        System.out.println("bdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbdbd");

        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC , BM_REFCU1 , SENDER)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,?,? )")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "1223098219");
                            stmt.setString(5, obj.getString("Tran_Date"));
                            stmt.setString(6, obj.getString("time"));
                            stmt.setString(7, "-");
                            amt = Double.parseDouble(obj.getString("Credit").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("Description"));
                            stmt.setString(13, obj.getString("Sender_Account_Name"));
                            stmt.setString(14, obj.getString("Sender_Branch"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void upload_BBLCUR_Historical(String cono, String divi, String bank, String user, String myJsonData) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat df = new SimpleDateFormat("hh:mm:ss aa");
                DateFormat outputformat = new SimpleDateFormat("HH:mm:ss");
                String output = null;
                Date date = null;
                double amt = 0.00;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
                LocalDate localdate;

                try (
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO " + DBPRD + ".BANK_MAPPING\n"
                                + "(BM_CONO, BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE,"
                                + " BM_TIME,  BM_CHANEL,  BM_AMOUNT,"
                                + " BM_USER, BM_STATUS, BM_TIME1  , BM_DESC ,BM_REFCU1,SENDER)\n"
                                + "VALUES(?, ?, ?, ?, ?,"
                                + " ?, ?,  ?, "
                                + " ?, ? , ? ,? ,? ,?)")) {
                    JSONArray arr = new JSONArray(myJsonData);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        try {

                            stmt.setInt(1, Integer.parseInt(cono));
                            stmt.setString(2, divi);
                            stmt.setString(3, bank);
                            stmt.setString(4, "1223098219");
                            stmt.setString(5, obj.getString("Tran_Date"));
                            stmt.setString(6, obj.getString("time"));
                            stmt.setString(7, "-");
                            amt = Double.parseDouble(obj.getString("Credit").replaceAll(",", ""));
                            stmt.setBigDecimal(8, BigDecimal.valueOf(amt));
                            stmt.setString(9, user);
                            stmt.setString(10, "10");
                            stmt.setTimestamp(11, timestamp);
                            stmt.setString(12, obj.getString("Description"));
                            stmt.setString(13, obj.getString("Sender_Account_Name"));
                            stmt.setString(14, obj.getString("Sender_Bank"));

                            stmt.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e.toString());
                            continue;
//                            break;
                        }
                    }
                    stmt.close();
                }
            } else {
                System.out.println("Server can't connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    //------------------------CURRENT---------------------
}

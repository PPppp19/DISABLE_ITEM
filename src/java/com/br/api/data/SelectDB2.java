/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.data;

import com.br.api.connect.ConnectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jettison.json.JSONArray;

/**
 *
 * @author Wattana
 */
public class SelectDB2 {

    public static String DBPRD = GBVAR.DBPRD;

    public static String M3DB = GBVAR.M3DB;

    public static String GetIDBYRCNO(String CRCNO ,String CONO) throws Exception {

        String STS = "";
        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  H_RNNO  FROM " + DBPRD + ".HEAD_RECIPT hr \n"
                        + "WHERE  H_RCNO  = '" + CRCNO.trim() + "' AND H_CONO = '"+CONO+"' ";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    STS = mRes.getString("H_RNNO");

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return STS;

    }
    
    
     public static JSONArray Location(String CONO, String DIVI) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT DISTINCT RL_CONO, RL_DIVI,RL_LCCODE, RL_LCDESC\n"
                        + " FROM brldta0100.RECEIPT_LOCAFNC\n"
                        + " WHERE RL_CONO = '" + CONO.trim() + "'\n"
                        + " AND RL_DIVI ='" + DIVI.trim() + "'\n"
                        + " AND RL_STS = '20'";
//                System.out.println("SelectCompany\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("RL_CONO", mRes.getString(1).trim());
                    mMap.put("RL_DIVI", mRes.getString(2).trim());
                    mMap.put("RL_LCCODE", mRes.getString(3).trim());
                    mMap.put("RL_LCDESC", mRes.getString(4).trim());
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }


    public static boolean CHKISBM(String CONO, String DIVI, String ID) throws Exception {

        int ccountbm = 0;
        boolean isBM = false;
        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  COUNT(BM_FNNO) AS CCBANK_MAPPING  FROM "+DBPRD+".BANK_MAPPING bm  \n"
                        + "WHERE  BM_FNNO  = '" + ID.trim() + "' AND BM_CONO = '" + CONO.trim() + "' AND BM_DIVI  = '" + DIVI.trim() + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    ccountbm = mRes.getInt("CCBANK_MAPPING");
                }

                if (ccountbm > 0) {
                    isBM = true;
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return isBM;

    }

    public static String getstatus(String cono, String divi, String rcno) throws Exception {

        String STS = "";
        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " SELECT HC_STS FROM " + DBPRD + ".HR_RECEIPT hr \n"
                        + " WHERE HC_RCNO  = '" + rcno.trim() + "' and HR_CONO = '" + cono + "' and HR_DIVI ='" + divi + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    STS = mRes.getString("HC_STS");

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return STS;

    }

    public static JSONArray Grid_Receipt(String cono, String divi, String rcno) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT LR_CONO, LR_DIVI, LR_RCNO, LR_INVNO, LR_INVDT, LR_INVAMT, LR_REAMT, LR_STS  \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT\n"
                        + "WHERE LR_CONO = '" + cono.trim() + "'\n"
                        + "AND LR_DIVI = '" + divi.trim() + "'\n"
                        + "AND LR_RCNO = '" + rcno.trim() + "'\n"
                        + "AND LR_STS = '1'\n";

//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("LR_CONO", mRes.getString(1).trim());
                    mMap.put("LR_DIVI", mRes.getString(2).trim());
                    mMap.put("LR_RCNO", mRes.getString(3).trim());
                    mMap.put("LR_INVNO", mRes.getString(4).trim());
                    mMap.put("LR_INVDT", mRes.getString(5).trim());
                    mMap.put("LR_INVAMT", mRes.getString(6).trim());
                    mMap.put("LR_REAMT", mRes.getString(7).trim());
                    mMap.put("LR_STS", mRes.getString(8).trim());
//                    mMap.put("ESCUNO", mRes.getString(9).trim());

                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray Grid_ARS200bycus(String cono, String divi, String payer, String rcno, String customer) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
//        payer = "TH01010016";
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT C.*,SUBSTR(D.ESIVDT,1,4) || '-' || SUBSTR(D.ESIVDT,5,2)|| '-' || SUBSTR(D.ESIVDT,7,2) as ESIVDT, C.Balance as RECEIVE \n"
                        + "FROM \n"
                        + "(\n"
                        + "SELECT A.ESPYNO,A.ESCUNO,A.ESCINO,A.ESINYR,A.ESCUCD,A.ESCUAM,COALESCE(b.LineAMT,0) AS LineAMT,(COALESCE(A.ESCUAM,0) - COALESCE(b.LineAMT,0)) AS Balance\n"
                        + "FROM \n"
                        + "(\n"
                        + "SELECT ESPYNO,ESCUNO,ESCINO,ESINYR,ESCUCD,SUM(ESCUAM) as ESCUAM\n"
                        + "FROM " + M3DB + ".FSLEDG\n"
                        + "WHERE ESCONO = '" + cono.trim() + "'\n"
                        + "AND ESDIVI = '" + divi.trim() + "'\n"
                        + "AND ESPYNO = '" + payer.trim() + "'\n"
                        + "AND ESCUNO = '" + customer.trim() + "' \n"
                        + "AND ESCINO NOT IN (SELECT LR_INVNO\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT ," + DBPRD + ".LR_LINERECEIPT \n"
                        + "WHERE HC_STS = '2' \n"
                        + "AND HC_PYNO = '" + payer.trim() + "'\n"
                        + "AND HC_RCNO  = LR_RCNO AND LR_INVAMT - LR_REAMT = 0) \n"
                        + "GROUP BY ESPYNO,ESCUNO,ESCINO,ESINYR,ESCUCD\n"
                        + "HAVING  SUM(ESCUAM) <> 0\n"
                        + ") A LEFT JOIN (\n"
                        + "SELECT LR_INVNO,sum(LR_REAMT) AS LineAMT \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT\n"
                        + "WHERE LR_CONO = '" + cono.trim() + "'\n"
                        + "AND LR_DIVI = '" + divi.trim() + "'\n"
                        + "AND LR_RCNO = '" + rcno.trim() + "' \n"
                        + "GROUP BY LR_INVNO,LR_RCNO\n"
                        + ") B ON B.LR_INVNO = A.ESCINO\n"
                        + ") C LEFT JOIN (\n"
                        + "  SELECT  DISTINCT ESPYNO,ESCUNO,ESCINO,ESIVDT,ESINYR,ESCUCD\n"
                        + "FROM " + M3DB + ".FSLEDG \n"
                        + "WHERE ESCONO =  '" + cono.trim() + "'\n"
                        + "AND ESDIVI = '" + divi.trim() + "'\n"
                        + ") D ON D.ESCUNO = C.ESCUNO AND D.ESCINO = C.ESCINO\n ";
//                        + "ORDER BY ESIVDT asc";

                ResultSet mRes = stmt.executeQuery(query);

                System.out.println("xxxxxxxxxxxx AGS 2000000000000 xxxxxxxxxxxx");

                System.out.println(query);

                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxx");

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    Double CheckData = mRes.getDouble(8);
                    if (CheckData != 0.00) {
                        mMap.put("ESPYNO", mRes.getString(1).trim());
                        mMap.put("ESCUNO", mRes.getString(2).trim());
                        mMap.put("ESCINO", mRes.getString(3).trim());
                        mMap.put("ESINYR", mRes.getString(4).trim());
                        mMap.put("ESCUCD", mRes.getString(5).trim());
                        mMap.put("ESCUAM", mRes.getString(6).trim());
                        mMap.put("LineAMT", mRes.getString(7).trim());
                        mMap.put("Balance", mRes.getString(8).trim());
                        mMap.put("ESIVDT", mRes.getString(9).trim());
                        mMap.put("RECEIVE", mRes.getString(10).trim());

                        mJSonArr.put(mMap);
                    }

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static int getcheckupdate(String cono, String divi, String fnno, String cuno, String pyno) throws Exception {

        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
        Statement stmt = conn.createStatement();
        int count = 0;
        try {
            if (conn != null) {

                String query0 = "SELECT COUNT(H_RCNO) as check FROM   " + DBPRD + ".HEAD_RECIPT\n"
                        + "WHERE   SUBSTRING(H_RCNO,1,1) != '2' AND   SUBSTRING(H_RCNO,1,1) != '3' AND H_RCNO  != 'RUNO' AND H_RCNO  != 'RCNO'\n"
                        + "AND  H_RNNO  = '" + fnno + "'\n"
                        + "AND  H_PYNO  = '" + pyno.toUpperCase() + "' AND  H_CONO  = '" + cono + "' AND  H_DIVI  = '" + divi + "'\n"
                        + "AND H_CUNO  = 'HEAD'";

                System.out.println(query0);
                ResultSet mRes = stmt.executeQuery(query0);

                while (mRes.next()) {

                    count = mRes.getInt("check");

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return count;

    }

    public static String Getrcno(String cono, String divi, String FNNO, String CUNO, String PYNO, String HC_TRDT, String HC_REAMT, String HC_TYPE, String HC_PYDT,
            String HC_CHKNO, String HC_BANK, String HC_ACCODE, String HC_USER, String HR_BKCHG, String locations) throws Exception {

        System.out.println("******************************x");
        System.out.println(cono);
        System.out.println(divi);
        System.out.println(FNNO);
        System.out.println(CUNO);
        System.out.println(PYNO);

        System.out.println(HC_TRDT);
        System.out.println(HC_REAMT);
        System.out.println(HC_TYPE);
        System.out.println(HC_PYDT);
        System.out.println(HC_CHKNO);
        System.out.println(HC_BANK);
        System.out.println(HC_ACCODE);
        System.out.println(HC_USER);
        System.out.println(HR_BKCHG);
        System.out.println(locations);

        System.out.println("******************************");

        String stats = "";
        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
        Statement stmt = conn.createStatement();
        String RCNO = "";
        Double Amt_Include_BKCHG = Double.parseDouble(HC_REAMT) + Double.parseDouble(HR_BKCHG);
        int check = SelectDB2.getcheckupdate(cono, divi, FNNO, CUNO, PYNO);
        String query = "";
        System.out.println("xxxxxxxnumnum \n" + check);
        try {
            if (conn != null) {

                if (check != 0) {

                    query = "UPDATE  " + DBPRD + ".HEAD_RECIPT  \n"
                            + "SET H_LOCATION = '" + locations.trim() + "', H_TYPE = '" + HC_TYPE.trim() + "' ,  H_CUNO = '" + CUNO.toUpperCase() + "',H_STS = '1' ,  H_RCNO  = (SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2) || '000001' ) AS H_RCNO FROM " + DBPRD + ".HR_RECEIPT WHERE  SUBSTRING(HC_RCNO,1,1) != '3')\n"
                            + "WHERE H_RNNO  = '" + FNNO + "'\n"
                            + "AND H_CUNO  = 'HEAD' AND H_CONO = '"+cono+"' AND H_DIVI = '"+divi+"' \n"
                            + "AND H_PYNO  = '" + PYNO.toUpperCase() + "'";

                } else {

                    query = "INSERT INTO " + DBPRD + ".HEAD_RECIPT (H_CONO, H_DIVI, H_RNNO, H_CUNO, H_PYNO, H_RCNO, H_STS)\n"
                            + "VALUES ('" + cono + "', '" + divi + "', '" + FNNO + "', '" + CUNO.toUpperCase() + "', '" + PYNO.toUpperCase()
                            + "',(SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2) || '000001' ) AS H_RCNO FROM " + DBPRD + ".HR_RECEIPT WHERE  SUBSTRING(HC_RCNO,1,1) != '3'), '1')";

//                      query = "INSERT INTO " + DBPRD + ".HEAD_RECIPT (H_CONO, H_DIVI, H_RNNO, H_CUNO, H_PYNO, H_RCNO, H_STS)\n"
//                            + "VALUES ('" + cono + "', '" + divi + "', '" + FNNO + "', '" + CUNO.toUpperCase() + "', '" + PYNO.toUpperCase()
//                            + "',(SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR("+HC_TRDT+"),'-',''),3,2) || '000001' ) AS H_RCNO FROM " + DBPRD + ".HR_RECEIPT WHERE  SUBSTRING(HC_RCNO,1,1) != '3'), '1')";
                }

                System.out.println("xxxxxxx \n" + query);

                stmt.execute(query);

                String query0 = "SELECT  H_RCNO   FROM " + DBPRD + ".HEAD_RECIPT WHERE  H_RNNO = '" + FNNO + "' AND  H_CUNO = '" + CUNO + "' AND H_PYNO = '" + PYNO + "' ";

                System.out.println("query0" + query0);

                ResultSet mRes = stmt.executeQuery(query0);

                while (mRes.next()) {
                    RCNO = mRes.getString("H_RCNO");
                    stats = mRes.getString("H_RCNO");
                }

                String query1 = "INSERT INTO " + DBPRD + ".HR_RECEIPT\n"
                        + "(HC_FNNO, HR_CONO, HR_DIVI, HC_RCNO, HC_TRDT, HC_PYNO, HC_USER, HC_STS,HC_REAMT, HC_TYPE,  HC_PYDT,  HC_CHKNO,  HC_BANK , HC_ACCODE , HR_BKCHG , HC_LOCATION)\n"
                        + "VALUES('" + FNNO + "','" + cono.trim() + "', '" + divi.trim() + "', '" + RCNO + "' \n"
                        + ", '" + HC_TRDT.replaceAll("-", "").trim() + "', '" + PYNO.trim() + "', '" + HC_USER.trim() + "', '2'"
                        + ",'" + Amt_Include_BKCHG + "', '" + HC_TYPE.trim() + "','" + HC_PYDT.replaceAll("-", "").trim() + "','" + HC_CHKNO.trim() + "','" + HC_BANK.trim() + "', '" + HC_ACCODE.trim() + "' , '" + HR_BKCHG.trim() + "' , '" + locations.trim() + "')";
                System.out.println("CheckINVSTS \n" + query1);
                stmt.execute(query1);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return stats;

    }

    public static JSONArray List_Bank(String type, String code, String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "";
                if (type.equalsIgnoreCase("CHECK")) {
                    query = "SELECT '-' AS ACCCODE,BCBKID AS BANKNAME,BCBANA AS BANKDESC \n"
                            + "FROM M3FDBPRD.CBANAC\n"
                            + "WHERE BCCONO = '" + cono.trim() + "'\n"
                            + "  AND BCBKTP = 2\n"
                            + "  AND BCSTAT = '20'\n"
                            + "  AND BCACHO = '" + code.trim() + "' ";
                } else {
                    query = "SELECT BR_ACCODE AS ACCCODE, BR_BANK AS BANKNAME, BR_NAMEAC AS BANKDESC "
                            + "FROM " + DBPRD + ".BANKRECEIPT "
                            + "WHERE BR_CONO = '" + cono.trim() + "' "
                            + "AND  BR_STATUS = '20'";
                }

//                System.out.println("select bank first\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("ACCCODE", mRes.getString(1).trim());
                    mMap.put("BANKNAME", mRes.getString(2).trim());
                    mMap.put("BANKDESC", mRes.getString(3).trim());
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray SetnamePayer(String cono, String divi, String code) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        String PAYERNAME = "NOT FOUND";
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COALESCE(OKCUNM,'-') AS PAYERNAME FROM M3FDBPRD.OCUSMA WHERE OKCONO = '" + cono.trim() + "'  AND OKCUNO = '" + code.trim() + "'";
//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    PAYERNAME = mRes.getString(1).trim();
                }
                Map<String, Object> mMap = new HashMap<>();
                mMap.put("PAYERNAME", PAYERNAME);
                mJSonArr.put(mMap);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray Call_GridRCNO(String cono, String divi, String rcno) throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT * FROM " + DBPRD + ".HR_RECEIPT hr \n"
                        + "WHERE  HC_RCNO  = '" + rcno + "'\n"
                        + "AND HR_CONO  = '" + cono + "'\n"
                        + "AND HR_DIVI  = '" + divi + "'";
                System.out.println("CheckRC \n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    if (mRes.getString(1) != null) {
                        mMap.put("HR_CONO", mRes.getString(1).trim());
                    }
                    if (mRes.getString(2) != null) {
                        mMap.put("HR_DIVI", mRes.getString(2).trim());
                    }
                    if (mRes.getString(3) != null) {
                        mMap.put("HC_RCNO", mRes.getString(3).trim());
                    }
                    if (mRes.getString(4) != null) {
                        mMap.put("HC_TRDT", mRes.getString(4).trim());
                    }
                    if (mRes.getString(5) != null) {
                        mMap.put("HC_PYNO", mRes.getString(5).trim());
                    }
                    if (mRes.getString(6) != null) {
                        mMap.put("HC_REAMT", mRes.getString(6).trim());
                    }
                    if (mRes.getString(7) != null) {
                        mMap.put("HC_TYPE", mRes.getString(7).trim());
                    }
                    if (mRes.getString(8) != null) {
                        mMap.put("HC_BANK", mRes.getString(8).trim());
                    }
                    if (mRes.getString(9) != null) {
                        mMap.put("HC_ACCODE", mRes.getString(9).trim());
                    }
                    if (mRes.getString(10) != null) {
                        mMap.put("HC_PYDT", mRes.getString(10).trim());
                    }
                    if (mRes.getString(12) != null) {
                        mMap.put("HC_USER", mRes.getString(12).trim());
                    }
                    if (mRes.getString(15) != null) {
                        mMap.put("HR_BKCHG", mRes.getString(15).trim());
                    }

                    if (mRes.getString(18) != null) {
                        mMap.put("HC_FNNO", mRes.getString(18).trim());
                    }

//                    if (mRes.getString(11) != null) {
//                        mMap.put("HC_BANK", mRes.getString(11).trim());
//                    }
//                    if (mRes.getString(12) != null) {
//                        mMap.put("HC_BANK", mRes.getString(12).trim());
//                    }
//                    if (mRes.getString(13) != null) {
//                        mMap.put("HC_BANK", mRes.getString(13).trim());
//                    }
//                    if (mRes.getString(14) != null) {
//                        mMap.put("HC_BANK", mRes.getString(14).trim());
//                    }
//                    if (mRes.getString(15) != null) {
//                        mMap.put("HC_BANK", mRes.getString(15).trim());
//                    }
//                    if (mRes.getString(16) != null) {
//                        mMap.put("HC_BANK", mRes.getString(16).trim());
//                    }
                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray SearchCustomerReceipt(String CONO, String DIVI, String FNNO, String Location) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "SELECT  COALESCE(H_RCNO,'') AS H_RCNO, COALESCE(H_CUNO,'') AS H_CUNO ,COALESCE(H_PYNO,'') AS H_PYNO FROM " + DBPRD + ".HEAD_RECIPT hr \n"
                        + "WHERE H_CONO  = '" + CONO + "'\n"
                        + "AND H_DIVI  = '" + DIVI + "'\n"
                        + "AND H_RNNO  = '" + FNNO + "' LIMIT 1 ";

                System.out.println(" SearchID : " + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("H_RCNO", mRes.getString(1).trim());
                    mMap.put("H_CUNO", mRes.getString(2).trim());
                    mMap.put("H_PYNO", mRes.getString(3).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray Call_GridRNNO(String cono, String divi, String rnno) throws Exception {

        String stats = "";
        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT * FROM " + DBPRD + ".HEAD_RECIPT hr  \n"
                        + "WHERE H_RNNO  = '" + rnno + "' and H_CONO = '" + cono + "' and H_DIVI ='" + divi + "' ";
                System.out.println("CheckRNNO \n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    if (mRes.getString(1) != null) {
                        mMap.put("H_CONO", mRes.getString(1).trim());
                    }
                    if (mRes.getString(2) != null) {
                        mMap.put("H_DIVI", mRes.getString(2).trim());
                    }
                    if (mRes.getString(3) != null) {
                        mMap.put("H_RCNO", mRes.getString(3).trim());
                    }
                    if (mRes.getString(4) != null) {
                        mMap.put("H_RNNO", mRes.getString(4).trim());
                    }
                    if (mRes.getString(5) != null) {
                        mMap.put("H_CUNO", mRes.getString(5).trim());
                    }
                    if (mRes.getString(6) != null) {
                        mMap.put("H_PYNO", mRes.getString(6).trim());
                    }
                    if (mRes.getString(7) != null) {
                        mMap.put("H_STS", mRes.getString(7).trim());
                    }
                    if (mRes.getString(8) != null) {
                        mMap.put("H_VCNO", mRes.getString(8).trim());
                    }
                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static String CheckINVSTS(String cono, String divi, String invno) throws Exception {

        String stats = "";
        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " SELECT acstcf FROM " + GBVAR.M3DB + ".FCR040 WHERE  ACCINO LIKE '" + invno.trim() + "%'";
//                String query = " SELECT acstcf FROM M3FDBprd.FCR040 WHERE ACCONO ='"+cono+"' AND ACDIVI ='"+divi+"' AND ACCINO LIKE '"+invno.trim()+"%'";
                System.out.println("CheckINVSTS \n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    stats = mRes.getString("ACSTCF");
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return stats;

    }

    public static JSONArray GetdatabyrcnoRC(String cono, String divi, String rcno) throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " SELECT  * \n"
                        + "FROM  " + DBPRD + ".HR_RECEIPT hr \n"
                        + "WHERE  HR_CONO  = '" + cono.trim() + "'\n"
                        + "AND  HR_DIVI  = '" + divi.trim() + "'\n"
                        + "AND  HC_RCNO  = '" + rcno.trim() + "'";
                System.out.println("GetdatabyrcnoRC\n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    if (mRes.getString(1) != null) {
                        mMap.put("HR_CONO", mRes.getString(1).trim());
                    }
                    if (mRes.getString(2) != null) {

                        mMap.put("HR_DIVI", mRes.getString(2).trim());
                    }
                    if (mRes.getString(3) != null) {

                        mMap.put("HC_RCNO", mRes.getString(3).trim());
                    }
                    if (mRes.getString(4) != null) {

                        mMap.put("HC_TRDT", mRes.getString(4).trim());
                    }

                    if (mRes.getString(5) != null) {

                        mMap.put("HC_PYNO", mRes.getString(5).trim());
                    }
                    if (mRes.getString(7) != null) {

                        mMap.put("HC_TYPE", mRes.getString(7).trim());
                    }
                    if (mRes.getString(8) != null) {

                        mMap.put("HC_BANK", mRes.getString(8).trim());
                    }
                    if (mRes.getString(16) != null) {

                        mMap.put("HC_LOCATION", mRes.getString(16).trim());
                    }
                    if (mRes.getString(17) != null) {

                        mMap.put("HC_FIXNO", mRes.getString(17).trim());
                    } else {
                        mMap.put("HC_FIXNO", "");
                    }
                    if (mRes.getString(13) != null) {

                        mMap.put("HC_VCNO", mRes.getString(13).trim());
                    } else {
                        mMap.put("HC_VCNO", "-");
                    }
                    if (mRes.getString(12) != null) {

                        mMap.put("HC_USER", mRes.getString(12).trim());
                    }
                    if (mRes.getString(6) != null) {

                        mMap.put("HC_REAMT", mRes.getString(6).trim());
                    }

                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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
        System.out.println(mJSonArr);
        return mJSonArr;

    }

    public static JSONArray Getdatabyrcno(String cono, String divi, String amt, String time, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  * \n"
                        + "FROM  " + DBPRD + ".BANK_MAPPING bm  \n"
                        + "WHERE BM_TIME  = '" + time + "'\n"
                        + "AND BM_CONO = '" + cono + "'\n"
                        + "AND BM_DATE =  '" + date + "' \n"
                        + "AND BM_AMOUNT =  '" + amt + "' \n"
                        + "AND BM_BANK =  '" + bank + "' \n"
                        + "AND BM_DIVI  = '" + divi + "'";
                System.out.println("Getdatabyrcno\n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    if (mRes.getString(15) != null) {
                        mMap.put("BM_RCNO", mRes.getString(15).trim());
                    } else {
                        mMap.put("BM_RCNO", "-");
                    }
                    if (mRes.getString(2) != null) {

                        mMap.put("BM_CONO", mRes.getString(2).trim());
                    }
                    if (mRes.getString(3) != null) {

                        mMap.put("BM_DIVI", mRes.getString(3).trim());
                    }
                    if (mRes.getString(4) != null) {

                        mMap.put("BM_BANK", mRes.getString(4).trim());
                    }

                    if (mRes.getString(10) != null) {

                        mMap.put("BM_CUNO", mRes.getString(10).trim());
                    } else {
                        mMap.put("BM_CUNO", "-");
                    }
                    if (mRes.getString(14) != null) {

                        mMap.put("BM_DESC", mRes.getString(14).trim());
                    }
                    if (mRes.getString(27) != null) {

                        mMap.put("BM_REFCU1", mRes.getString(27).trim());
                    } else {
                        mMap.put("BM_REFCU1", "-");
                    }
                    if (mRes.getString(28) != null) {

                        mMap.put("BM_CUNA", mRes.getString(28).trim());
                    } else {
                        mMap.put("BM_CUNA", "-");
                    }
                    if (mRes.getString(29) != null) {

                        mMap.put("BM_FNNO", mRes.getString(29).trim());
                    } else {
                        mMap.put("BM_FNNO", "-");
                    }
                    if (mRes.getString(30) != null) {

                        mMap.put("BM_LCODE", mRes.getString(30).trim());
                    } else {
                        mMap.put("BM_LCODE", "-");
                    }
//                    mMap.put("COMPANY", mRes.getString(4).trim());
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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
        System.out.println(mJSonArr);
        return mJSonArr;

    }

    public static String getlastNo(String cono, String divi) throws Exception {

        String stats = "";
        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2) || '000001' ) AS HC_RCNO \n"
                        + "FROM " + DBPRD + ".HR_RECEIPT\n"
                        + "WHERE HR_CONO = '" + cono + "'\n"
                        + "AND SUBSTRING(HC_RCNO,1,1) != '3' AND HR_DIVI = '" + divi + "'\n"
                        + "AND SUBSTRING(HC_TRDT,3,2) = SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2)";
//                System.out.println("getGrid_SCB_IntraDay\n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    stats = mRes.getString("HC_RCNO");
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return stats;

    }

    public static int getstatus(String cono, String divi, String date, String pyno) throws Exception {

        int stats = 0;
        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT HC_STS FROM " + DBPRD + ".HR_RECEIPT \n"
                        + "                      WHERE HC_PYNO = '" + pyno + "'\n"
                        + "                       AND HC_PYDT  = '" + date + "'\n"
                        + "                       AND HR_DIVI  = '" + divi + "'\n"
                        + "                       AND HR_CONO  = '" + cono + "' \n"
                        + "                       ORDER BY HC_RCNO  DESC \n"
                        + "                       LIMIT 1";
                System.out.println(" Select status : \n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    stats = mRes.getInt("HC_STS");
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return stats;

    }

    public static JSONArray Company() throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT CCCONO,CCDIVI,CCCONM,'\"'|| TRIM(CCCONO) || ' : ' || TRIM(CCDIVI) || ' : ' || TRIM(CCCONM) || '\"' AS COMPANY\n"
                        + "FROM " + GBVAR.M3DB + ".CMNDIV\n"
                        + "WHERE CCDIVI NOT IN  ('','120','130','','')\n"
                        + "AND CCCONO IN ('10','10','500','600')\n"
                        + "ORDER BY CCCONO";
//                System.out.println("select \n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("CCCONO", mRes.getString(1).trim());
                    mMap.put("CCDIVI", mRes.getString(2).trim());
                    mMap.put("CCCONM", mRes.getString(3).trim());
                    mMap.put("COMPANY", mRes.getString(4).trim());
                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static String CHKCUORPY(String TXT, String CONO) throws Exception {

        String RESULT = "NG";

        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "";

                query = "SELECT OKPYNO , OKCUNO ,\n"
                        + "CASE WHEN OKPYNO = OKCUNO  THEN 'OK'\n"
                        + "WHEN  OKPYNO = '' AND OKCUNO != '' THEN 'OK'\n"
                        + "ELSE 'NG'\n"
                        + "END AS  ISPAYER\n"
                        + "FROM  M3FDBPRD.OCUSMA o \n"
                        + "WHERE   OKCUNO  = '" + TXT.trim() + "' AND OKCONO = '" + CONO + "' AND  OKSTAT = '20'";

                System.out.println("CHKCUIRPY\n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    RESULT = mRes.getString("ISPAYER");
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return RESULT;

    }

    public static JSONArray getGrid_SCBold(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {
                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, a.BM_CHQNO, a.BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as custName,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(r.HC_TYPE,'TRANSFER') as BM_TYPE, COALESCE(r.HC_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO, COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO "
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9' and r.HC_LOCATION != 'ACCOUNT' \n"
                            + "LEFT JOIN " + DBPRD + ".HEAD_RECIPT hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_STATUS  != '99'\n"
                            + "AND a.BM_DATE  = '" + date.replaceAll("-", "").trim() + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                } else {
                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, a.BM_CHQNO, a.BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as custName,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(r.HC_TYPE,'TRANSFER') as BM_TYPE, COALESCE(r.HC_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO , COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO "
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9' and r.HC_LOCATION != 'ACCOUNT' \n"
                            + "LEFT JOIN " + DBPRD + ".HEAD_RECIPT hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_STATUS  != '99'\n"
                            + "AND a.BM_DATE  = '" + date.replaceAll("-", "").trim() + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                }
                System.out.println("getGrid_SCB_IntraDay\n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());

                    mMap.put("HPYNO", mRes.getString(25).trim());
                    mMap.put("BM_FNNO", mRes.getString(26).trim());

                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray getGrid_SCB(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {
                    query = "SELECT   a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, a.BM_CHQNO, a.BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as custName,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO, COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO "
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9' and r.HC_LOCATION != 'ACCOUNT' \n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_STATUS  != '99'\n"
                            + "AND a.BM_DATE  = '" + date.replaceAll("-", "").trim() + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                } else {
                    query = "SELECT   a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, a.BM_CHQNO, a.BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as custName,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO, COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO "
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9' and r.HC_LOCATION != 'ACCOUNT' \n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_STATUS  != '99'\n"
                            + "AND a.BM_DATE  = '" + date.replaceAll("-", "").trim() + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                }
                System.out.println("getGrid_SCB_IntraDay\n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());

                    mMap.put("HPYNO", mRes.getString(25).trim());
                    mMap.put("BM_FNNO", mRes.getString(26).trim());

                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }
    
    
    public static JSONArray getGrid_SCB_CUR(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {
                    query = "SELECT   a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, a.BM_CHQNO, a.BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as custName,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO, COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO "
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9' and r.HC_LOCATION != 'ACCOUNT' \n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_STATUS  != '99'\n"
                            + "AND a.BM_DATE  = '" + date.replaceAll("-", "").trim() + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                } else {
                    query = "SELECT   a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, a.BM_CHQNO, a.BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as custName,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO, COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO "
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9' and r.HC_LOCATION != 'ACCOUNT' \n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_STATUS  != '99'\n"
                            + "AND a.BM_DATE  = '" + date.replaceAll("-", "").trim() + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                }
                System.out.println("getGrid_SCB_IntraDay\n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());

                    mMap.put("HPYNO", mRes.getString(25).trim());
                    mMap.put("BM_FNNO", mRes.getString(26).trim());

                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }
    
    
    
    public static JSONArray getGrid_SCBNSD(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {
                    query = "SELECT   a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, a.BM_CHQNO, a.BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as custName,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO, COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO "
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9' and r.HC_LOCATION != 'ACCOUNT' \n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_STATUS  != '99'\n"
                            + "AND a.BM_DATE  = '" + date.replaceAll("-", "").trim() + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                } else {
                    query = "SELECT   a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, a.BM_CHQNO, a.BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as custName,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'NSD') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO, COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO "
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9' and r.HC_LOCATION != 'ACCOUNT' \n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_STATUS  != '99'\n"
                            + "AND a.BM_DATE  = '" + date.replaceAll("-", "").trim() + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                }
                System.out.println("getGrid_SCB_IntraDay\n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());

                    mMap.put("HPYNO", mRes.getString(25).trim());
                    mMap.put("BM_FNNO", mRes.getString(26).trim());

                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }


    public static JSONArray getGrid_KBANKold(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
//        String Date_Format = date.substring(5, 7) + "/" + date.substring(8, 10) + "/" + date.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {
                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(r.HC_TYPE,'TRANSFER') as BM_TYPE, COALESCE(r.HC_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO , COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS BM_PYNO , COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN " + DBPRD + ".HEAD_RECIPT hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                } else {

                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(r.HC_TYPE,'TRANSFER') as BM_TYPE, COALESCE(r.HC_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO , COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO,COALESCE(BM_FNNO,'')   \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN " + DBPRD + ".HEAD_RECIPT hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                }
                ResultSet mRes = stmt.executeQuery(query);
                System.out.println(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());
                    mMap.put("HPYNO", mRes.getString(25).trim());
                    mMap.put("BM_FNNO", mRes.getString(26).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray getGrid_KBANK(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
//        String Date_Format = date.substring(5, 7) + "/" + date.substring(8, 10) + "/" + date.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {
                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO , COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS BM_PYNO , COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                } else {

                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO , COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS BM_PYNO , COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                }
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());
                    mMap.put("HPYNO", mRes.getString(25).trim());
                    mMap.put("BM_FNNO", mRes.getString(26).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    
    public static JSONArray getGrid_KBANK_CUR(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
//        String Date_Format = date.substring(5, 7) + "/" + date.substring(8, 10) + "/" + date.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {
                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO , COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS BM_PYNO , COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                } else {

                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO , COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS BM_PYNO , COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                }
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());
                    mMap.put("HPYNO", mRes.getString(25).trim());
                    mMap.put("BM_FNNO", mRes.getString(26).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray getGrid_BBLold(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
//        String Date_Format = date.substring(5, 7) + "/" + date.substring(8, 10) + "/" + date.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {

                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(r.HC_TYPE,'TRANSFER') as BM_TYPE, COALESCE(r.HC_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1 ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO , COALESCE(BM_FNNO, '-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN " + DBPRD + ".HEAD_RECIPT hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                } else {

                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(r.HC_TYPE,'TRANSFER') as BM_TYPE, COALESCE(r.HC_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1 , COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO , COALESCE(BM_FNNO, '-')  \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN " + DBPRD + ".HEAD_RECIPT hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";
                }
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());
                    mMap.put("BM_REFCU1", mRes.getString(25).trim());
                    mMap.put("HPYNO", mRes.getString(26).trim());
                    mMap.put("BM_FNNO", mRes.getString(27).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray getGrid_BBL(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
//        String Date_Format = date.substring(5, 7) + "/" + date.substring(8, 10) + "/" + date.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {

                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1 ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO , COALESCE(BM_FNNO, '-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                } else {

                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1 ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO , COALESCE(BM_FNNO, '-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";
                }
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());
                    mMap.put("BM_REFCU1", mRes.getString(25).trim());
                    mMap.put("HPYNO", mRes.getString(26).trim());
                    mMap.put("BM_FNNO", mRes.getString(27).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }
    
    //********************** getGrid_BBL_QR ***************
    
    
    public static JSONArray getGrid_BBL_QR(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
//        String Date_Format = date.substring(5, 7) + "/" + date.substring(8, 10) + "/" + date.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {

                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1 ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO , COALESCE(BM_FNNO, '-') ,COALESCE(REF2, '-') as REF2 \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                } else {

                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1 ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO , COALESCE(BM_FNNO, '-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";
                }
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());
                    mMap.put("BM_REFCU1", mRes.getString(25).trim());
                    mMap.put("HPYNO", mRes.getString(26).trim());
                    mMap.put("BM_FNNO", mRes.getString(27).trim());
                     mMap.put("REF2", mRes.getString(28).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }
    
    
    
    
    //***************************************************
    
    
    
    public static JSONArray getGrid_BBL_CUR(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
//        String Date_Format = date.substring(5, 7) + "/" + date.substring(8, 10) + "/" + date.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {

                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1 ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO , COALESCE(BM_FNNO, '-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                } else {

                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1 ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO , COALESCE(BM_FNNO, '-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";
                }
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());
                    mMap.put("BM_REFCU1", mRes.getString(25).trim());
                    mMap.put("HPYNO", mRes.getString(26).trim());
                    mMap.put("BM_FNNO", mRes.getString(27).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray getGrid_SCB_MMN(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
//        String Date_Format = date.substring(5, 7) + "/" + date.substring(8, 10) + "/" + date.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {
                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1 , COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";
                } else {

                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1 , COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";
                }
                ResultSet mRes = stmt.executeQuery(query);
                System.out.println(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());
                    mMap.put("BM_REFCU1", mRes.getString(25).trim());
                    mMap.put("HPYNO", mRes.getString(26).trim());
                    mMap.put("BM_FNNO", mRes.getString(27).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray getGrid_SCB_MMNold(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
//        String Date_Format = date.substring(5, 7) + "/" + date.substring(8, 10) + "/" + date.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {
                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(r.HC_TYPE,'TRANSFER') as BM_TYPE, COALESCE(r.HC_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1 , COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN " + DBPRD + ".HEAD_RECIPT hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";
                } else {

                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(r.HC_TYPE,'TRANSFER') as BM_TYPE, COALESCE(r.HC_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1 ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO,'-') \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN " + DBPRD + ".HEAD_RECIPT hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";
                }
                ResultSet mRes = stmt.executeQuery(query);
                System.out.println(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());
                    mMap.put("BM_REFCU1", mRes.getString(25).trim());
                    mMap.put("HPYNO", mRes.getString(26).trim());
                    mMap.put("BM_FNNO", mRes.getString(27).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray getGrid_SCB_BILLold(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
//        String Date_Format = date.substring(5, 7) + "/" + date.substring(8, 10) + "/" + date.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {
                    query = "SELECT  a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(r.HC_TYPE,'TRANSFER') as BM_TYPE, COALESCE(r.HC_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1, COALESCE(a.BM_CUNA,'-')as BM_CUNA ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO , COALESCE(BM_FNNO,'-')  \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN " + DBPRD + ".HEAD_RECIPT hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                } else {
                    query = "SELECT  a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(r.HC_TYPE,'TRANSFER') as BM_TYPE, COALESCE(r.HC_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1, COALESCE(a.BM_CUNA,'-')as BM_CUNA ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO  ,COALESCE(BM_FNNO,'-')\n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";
                }
                ResultSet mRes = stmt.executeQuery(query);
                System.out.println(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());
                    mMap.put("BM_REFCU1", mRes.getString(25).trim());
                    mMap.put("BM_CUNA", mRes.getString(26).trim());
                    mMap.put("HPYNO", mRes.getString(27).trim());
                    mMap.put("BM_FNNO", mRes.getString(28).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray getGrid_SCB_BILL(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
//        String Date_Format = date.substring(5, 7) + "/" + date.substring(8, 10) + "/" + date.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {
                    query = "SELECT    a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1, COALESCE(a.BM_CUNA,'-')as BM_CUNA ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO , COALESCE(BM_FNNO,'-')  \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                } else {
                    query = "SELECT    a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO ,a.BM_REFCU1 as BM_REFCU1, COALESCE(a.BM_CUNA,'-')as BM_CUNA ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO , COALESCE(BM_FNNO,'-')  \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";
                }
                System.out.println("mamam" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());
                    mMap.put("BM_REFCU1", mRes.getString(25).trim());
                    mMap.put("BM_CUNA", mRes.getString(26).trim());
                    mMap.put("HPYNO", mRes.getString(27).trim());
                    mMap.put("BM_FNNO", mRes.getString(28).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray getGrid_KBANK_BILL(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
//        String Date_Format = date.substring(5, 7) + "/" + date.substring(8, 10) + "/" + date.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {
                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO, a.BM_REFCU1 as BM_REFCU1 ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO,'-') ,COALESCE(REF2,'-')  \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";
                } else {
                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(Hr.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(Hr.H_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO, a.BM_REFCU1 as BM_REFCU1 ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO,'-')   \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                }
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());
                    mMap.put("BM_REFCU1", mRes.getString(25).trim());
                    mMap.put("HPYNO", mRes.getString(26).trim());
                    mMap.put("BM_FNNO", mRes.getString(27).trim());
                    mMap.put("REF2", mRes.getString(28).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray getGrid_KBANK_BILLold(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
//        String Date_Format = date.substring(5, 7) + "/" + date.substring(8, 10) + "/" + date.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {
                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(r.HC_TYPE,'TRANSFER') as BM_TYPE, COALESCE(r.HC_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO, a.BM_REFCU1 as BM_REFCU1 ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO,'-')   \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN " + DBPRD + ".HEAD_RECIPT hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";
                } else {
                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(r.HC_TYPE,'TRANSFER') as BM_TYPE, COALESCE(r.HC_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO, a.BM_REFCU1 as BM_REFCU1 ,COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO  , COALESCE(BM_FNNO,'-')  \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN " + DBPRD + ".HEAD_RECIPT hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                }
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());
                    mMap.put("BM_REFCU1", mRes.getString(25).trim());
                    mMap.put("HPYNO", mRes.getString(26).trim());
                    mMap.put("BM_FNNO", mRes.getString(27).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray getGrid_BBL_BILL(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
//        String Date_Format = date.substring(5, 7) + "/" + date.substring(8, 10) + "/" + date.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {
                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(HR.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(HR.H_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO , COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO , COALESCE(BM_FNNO, '-')   \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";
                } else {
                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(HR.H_TYPE,'TRANSFER') as BM_TYPE, COALESCE(HR.H_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO , COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO , COALESCE(BM_FNNO, '-')   \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN \n"
                            + "(\n"
                            + "SELECT DISTINCT  H_RNNO, H_LOCATION, H_TYPE, H_CONO , H_DIVI \n"
                            + "FROM " + DBPRD + ".HEAD_RECIPT \n"
                            + ") AS hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n" + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                }
                ResultSet mRes = stmt.executeQuery(query);
                System.out.println(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());
                    mMap.put("HPYNO", mRes.getString(25).trim());
                    mMap.put("BM_FNNO", mRes.getString(26).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

    public static JSONArray getGrid_BBL_BILLold(String cono, String divi, String date, String bank) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB.ConnectionDB();
        conn.setAutoCommit(true);
//        String Date_Format = date.substring(5, 7) + "/" + date.substring(8, 10) + "/" + date.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "";
                if (cono.toString().equalsIgnoreCase("10")) {
                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(r.HC_TYPE,'TRANSFER') as BM_TYPE, COALESCE(r.HC_LOCATION ,'LS') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO , COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO , COALESCE(BM_FNNO, '-')   \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN " + DBPRD + ".HEAD_RECIPT hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";
                } else {
                    query = "SELECT a.BM_CONO, a.BM_DIVI, a.BM_BANK, a.BM_ACCODE, a.BM_DATE,\n"
                            + "a.BM_TIME, COALESCE(a.BM_CHQNO,'') as BM_CHQNO,COALESCE(a.BM_TRANSAC,'') as BM_TRANSAC, COALESCE(a.BM_CUNO,'') AS BM_CUNO,COALESCE(a.BM_CHANEL,'') AS  BM_CHANEL,\n"
                            + "COALESCE(a.BM_BRANCH,'-') AS BM_BRANCH, a.BM_AMOUNT,COALESCE(a.BM_DESC,'') AS BM_DESC,COALESCE(a.BM_RCNO,'') AS BM_RCNO, \n"
                            + "COALESCE(a.BM_BKCHARGE,'') AS BM_BKCHARGE,COALESCE(a.BM_OVPAY,'') AS BM_OVPAY,COALESCE(a.BM_CNDN,'') AS BM_CNDN,a.BM_ID ,COALESCE(a.BM_DESC,'-') AS BM_DESC\n"
                            + ",COALESCE(o.okcunm,'-') as okcunm,COALESCE (r.HC_VCNO,'-') as HC_VCNO ,COALESCE(r.HC_TYPE,'TRANSFER') as BM_TYPE, COALESCE(r.HC_LOCATION ,'WTF') as BM_LOCA \n"
                            + ",COALESCE(r.HC_RCNO,'') AS HC_RCNO , COALESCE(COALESCE(NULLIF(o.OKPYNO,''), o.OKCUNO), '')  AS HPYNO ,COALESCE(BM_FNNO, '-')  \n"
                            + "FROM " + DBPRD + ".BANK_MAPPING a\n"
                            + "left join " + GBVAR.M3DB + ".OCUSMA o on o.OKCONO = a.BM_CONO  and o.OKCUNO = a.BM_CUNO \n"
                            + "left join " + DBPRD + ".HR_RECEIPT r on r.HR_CONO = a.BM_CONO and r.HR_DIVI = a.BM_DIVI and COALESCE(a.BM_RCNO,'') = r.HC_RCNO and a.BM_BANK = r.HC_BANK AND r.HC_STS != '9'\n"
                            + "LEFT JOIN " + DBPRD + ".HEAD_RECIPT hr  ON  Hr.H_CONO = a.BM_CONO and Hr.H_DIVI = a.BM_DIVI and COALESCE(a.BM_FNNO,'') = Hr.H_RNNO \n"
                            + "WHERE a.BM_CONO = '" + cono.trim() + "'\n"
                            + "AND a.BM_DIVI  = '" + divi.trim() + "'\n"
                            + "AND a.BM_DATE  = '" + date.trim().replaceAll("-", "") + "'\n"
                            + "AND a.BM_BANK = '" + bank.trim() + "' ORDER BY a.BM_ID asc";

                }
                ResultSet mRes = stmt.executeQuery(query);
                System.out.println(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_BANK", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_DATE", mRes.getString(5).trim());
                    mMap.put("BM_TIME", mRes.getString(6).trim());
                    mMap.put("BM_CHQNO", mRes.getString(7).trim());
                    mMap.put("BM_TRANSAC", mRes.getString(8).trim());
                    mMap.put("BM_CUNO", mRes.getString(9).trim());
                    mMap.put("BM_CHANEL", mRes.getString(10).trim());
                    mMap.put("BM_BRANCH", mRes.getString(11).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(12).trim());
                    mMap.put("BM_DESC", mRes.getString(13).trim());
                    mMap.put("BM_RCNO", mRes.getString(14).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(15).trim());
                    mMap.put("BM_OVPAY", mRes.getString(16).trim());
                    mMap.put("BM_CNDN", mRes.getString(17).trim());
                    mMap.put("BM_ID", mRes.getString(18).trim());
                    mMap.put("BM_DESC", mRes.getString(19).trim());
                    mMap.put("custName", mRes.getString(20).trim());
                    mMap.put("HC_VCNO", mRes.getString(21).trim());
                    mMap.put("BM_TYPE", mRes.getString(22).trim());
                    mMap.put("BM_LOCA", mRes.getString(23).trim());
                    mMap.put("HC_RCNO", mRes.getString(24).trim());
                    mMap.put("HPYNO", mRes.getString(25).trim());
                    mMap.put("BM_FNNO", mRes.getString(26).trim());

                    mJSonArr.put(mMap);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
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

        return mJSonArr;

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.data;

import com.br.api.connect.ConnectDB;
import static com.br.api.data.SelectDB2.DBPRD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Jilasak
 */
public class UpdateDB2 {

    public static String DBPRD = GBVAR.DBPRD;

    public static void CancelID(String cono, String divi, String id, String username) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {

                Statement stmt0 = conn.createStatement();
//                String query0 = "INSERT INTO  " + DBPRD + ".BANK_MAPPING (BM_ID, BM_CONO,BM_DIVI, BM_BANK, BM_ACCODE, BM_DATE, BM_TIME,\n"
//                        + "BM_CHQNO,BM_TRANSAC,BM_CUNO,BM_CHANEL,BM_BRANCH,BM_AMOUNT,BM_DESC,BM_RCNO,BM_TIME1,BM_USER,BM_BKCHARGE,\n"
//                        + "BM_OVPAY,BM_CNDN,BM_STATUS,REF2,REF3,SENDER,BM_REFCU,REF4,BM_REFCU1,BM_CUNA,BM_FNNO,BM_LCODE)\n"
//                        + "SELECT BM_ID, BM_CONO,BM_DIVI, BM_BANK||'_ED', BM_ACCODE, BM_DATE, BM_TIME,\n"
//                        + "BM_CHQNO,BM_TRANSAC,BM_CUNO,BM_CHANEL,BM_BRANCH,BM_AMOUNT,BM_DESC,BM_RCNO,BM_TIME1,BM_USER,BM_BKCHARGE,\n"
//                        + "BM_OVPAY,BM_CNDN,'99',REF2,REF3,SENDER,BM_REFCU,REF4,BM_REFCU1,BM_CUNA,BM_FNNO,BM_LCODE FROM  " + DBPRD + ".BANK_MAPPING \n"
//                        + "WHERE BM_FNNO  = '" + id.trim() + "' AND BM_STATUS = '10'";

//                stmt0.executeUpdate(query0);
              //  System.out.println(query0);

                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING bm  \n"
                        + "SET  BM_FNNO  = '-' , BM_RCNO  = NULL , BM_USER  = '" + username.trim() + "'  \n"
                        + "WHERE BM_FNNO  = '" + id.trim() + "' and bm_cono = '" + cono.trim() + "' and bm_divi = '" + divi.trim() + "' AND  BM_STATUS  = '10'";

                stmt.executeUpdate(query);
                System.out.println(query);

                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE " + DBPRD + ".HEAD_RECIPT hr \n"
                        + "SET  H_STS  = '9' \n"
                        + "WHERE H_RNNO  = '" + id.trim() + "'\n"
                        + "AND H_CONO  = '" + cono.trim() + "' AND  H_DIVI  = '" + divi.trim() + "'";

                stmt2.executeUpdate(query2);
                System.out.println(query2);

                Statement stmt3 = conn.createStatement();
                String query3 = "UPDATE " + DBPRD + ".HR_RECEIPT hr \n"
                        + "SET  HC_STS  = '9' , HC_USER  = '" + username + "' \n"
                        + "WHERE HC_FNNO  = '" + id.trim() + "'\n"
                        + "AND  HR_CONO  = '" + cono.trim()+ "' AND HR_DIVI  = '" + divi.trim() + "'";

                stmt3.executeUpdate(query3);
                System.out.println(query3);
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
    
  public static void Disableitem(String cono, String divi, String user, String myJsonData) throws Exception {
    Connection conn = ConnectDB.ConnectionDB();

    try {
        if (conn != null) {

            // JSON เป็น Array
            JSONArray jsonArray = new JSONArray(myJsonData);

            String sql =
                    "UPDATE M3FDBPRD.MITMAS " +
                    "SET MMSTAT = ? " +
                    "WHERE MMCONO = ? " +
                    "AND MMITNO = ?";

            System.out.println(sql);

            PreparedStatement pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject json = jsonArray.getJSONObject(i);

                String itemcode = json.optString("itemCode", "");
                String status = json.optString("status", "");

                // ข้าม header
                if ("MMITNO".equals(itemcode)) {
                    continue;
                }

                if (itemcode.isEmpty()) {
                    continue;
                }

                // ถ้าต้องการ Disable เป็น 90 เสมอ
                pstmt.setString(1, "90");

                // ถ้าต้องการใช้ cono จาก parameter
                pstmt.setString(2, cono);

                // item code
                pstmt.setString(3, itemcode);

                int result = pstmt.executeUpdate();

                System.out.println(
                        "itemcode = " + itemcode +
                        ", status = " + status +
                        ", update result = " + result
                );
            }

            pstmt.close();
        }

    } catch (Exception e) {
        e.printStackTrace();
        throw e;

    } finally {
        if (conn != null) {
            conn.close();
        }
    }
}
    public static void setstatus(String cono, String divi, String rcno, String sts) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = " UPDATE " + DBPRD + ".HR_RECEIPT hr \n"
                        + " SET HC_STS = '" + sts.trim() + "'\n"
                        + "  WHERE HC_RCNO  = '" + rcno.trim() + "'\n"
                        + "  AND HR_CONO  = '" + cono + "'\n"
                        + "  AND HR_DIVI  = '" + divi + "'";

                System.out.println("xxxxxxxxxxxxxxx");
                System.out.println(query);
                System.out.println("xxxxxxxxxxxxxxx");

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

    public static void UpdateHeader(String HR_CONO, String HR_DIVI, String HC_PYNO, String HC_REAMT, String HC_TYPE, String HC_PYDT, String HC_CHKNO, String HC_BANK, String HC_ACCODE, String HR_BKCHG, String HR_RCNO, String HC_LOCATION, String FNNO, String CUNO, String TRDT) throws Exception {

        Connection conn = ConnectDB.ConnectionDB();
        Double Amt_Include_BKCHG = Double.parseDouble(HC_REAMT) + Double.parseDouble(HR_BKCHG);

        try {
            if (conn != null) {
//                LR_REAMT = new DecimalFormat("0.00").format(Double.parseDouble(LR_REAMT.replaceAll(",", "").trim()));
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".HR_RECEIPT\n"
                        //                String query = "UPDATE BRLDTA0100.HR_RECEIPTV2\n"
                        + "SET HC_PYNO = '" + HC_PYNO.trim() + "', HC_REAMT = '" + Amt_Include_BKCHG + "',HC_TYPE = '" + HC_TYPE.trim() + "',HC_PYDT = '" + HC_PYDT.replaceAll("-", "").trim() + "',HC_TRDT = '" + TRDT.replaceAll("-", "").trim() + "',HC_CHKNO = '" + HC_CHKNO.trim() + "',"
                        + " HC_BANK = '" + HC_BANK.trim() + "', HC_ACCODE = '" + HC_ACCODE.trim() + "',HR_BKCHG = '" + HR_BKCHG.trim() + "'\n"
                        + "WHERE HR_CONO = '" + HR_CONO.trim() + "' AND HR_DIVI='" + HR_DIVI.trim() + "' AND HC_RCNO='" + HR_RCNO.trim() + "' AND HC_LOCATION = '" + HC_LOCATION.trim() + "'";
                System.out.println(query);
                stmt.execute(query);

                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE  " + DBPRD + ".HEAD_RECIPT  \n"
                        + "SET H_LOCATION = '" + HC_LOCATION.trim() + "', H_PYNO  = '" + HC_PYNO.toUpperCase() + "' ,H_TYPE = '" + HC_TYPE.trim() + "' ,  H_CUNO = '" + CUNO.toUpperCase() + "',H_STS = '1' ,  H_RCNO  =  '" + HR_RCNO + "'  \n"
                        + "WHERE H_RNNO  = '" + FNNO + "'";

                System.out.println(query2);
                stmt2.execute(query2);

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

    }

    public static void updateHEADfix(String cono, String divi, String rcno, String pyno, String cuno, String fnno) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".HEAD_RECIPT\n"
                        + "SET H_PYNO = '" + pyno.trim() + "' , H_CUNO = '" + cuno.trim() + "' , H_RNNO = '" + fnno.trim() + "' "
                        + "WHERE H_CONO = '" + cono.trim() + "' "
                        + "AND H_DIVI = '" + divi.trim() + "' "
                        + "AND H_RCNO = '" + rcno.trim() + "' ";

                System.out.println("xxxxxxxxxxxxxxx");
                System.out.println(query);
                System.out.println("xxxxxxxxxxxxxxx");

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

    public static void SAVERC(String cono, String divi, String rcno, String bank, String type, String date, String pydt, String amt, String pyno, String bkchg, String lcode, String fnno, String user) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".HR_RECEIPT\n"
                        + "SET HC_BANK = '" + bank + "' , HC_TYPE = '" + type + "' , HC_TRDT = '" + date + "', HC_PYDT = '" + pydt + "', HC_REAMT = '" + amt + "', HC_PYNO = '" + pyno + "', HR_BKCHG = '" + bkchg + "' ,  HC_ACCODE = '" + lcode + "', HC_FNNO = '" + fnno + "', HC_USER = '" + user + "' "
                        + "WHERE HR_CONO = '" + cono.trim() + "' "
                        + "AND HR_DIVI = '" + divi.trim() + "' "
                        + "AND HC_RCNO = '" + rcno.trim() + "' ";

                System.out.println("xxxxxxxxxxxxxxx");
                System.out.println(query);
                System.out.println("xxxxxxxxxxxxxxx");

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

    public static void updatenewReceipt_SCB(String cono, String divi, String HC_PYDT, String HC_PYNO, String lastNo) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_RCNO = '" + lastNo + "'"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_CUNO = '" + HC_PYNO.trim() + "' "
                        + "AND BM_DATE = '" + HC_PYDT.trim() + "' ";

                System.out.println(query);
                System.out.println("xxxxxxxxxxxxxxx");

                //stmt.executeUpdate(query);
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

    public static void updateReceipt_SCB_BILLnew(String cono, String divi, String BM_DATE, String BM_AMOUNT, String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_FNNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + HPYNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_FNNO = '" + BM_FNNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE " + DBPRD + ".HEAD_RECIPT SET  H_PYNO = '" + HPYNO.toUpperCase() + "', H_LOCATION = '" + BM_LOCA + "' , H_TYPE = '" + BM_TYPE + "' , H_CUNO = 'HEAD' \n"
                        + "WHERE H_RNNO  = '" + BM_FNNO + "'\n"
                        + "AND H_CONO  = '" + cono + "' AND H_STS = '1' \n"
                        + "AND H_DIVI  = '" + divi + "'";
                stmt2.executeUpdate(query2);
                System.out.println(query2);

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

    public static void updateReceipt_SCB_BILL(String cono, String divi, String BM_DATE, String BM_AMOUNT, String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_RCNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + BM_CUNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_RCNO = '" + BM_RCNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                String query_2 = "UPDATE " + DBPRD + ".HR_RECEIPT\n"
                        + "SET HC_PYNO = '" + BM_CUNO.trim() + "', HC_REAMT = '" + Amt_Include_BKCHG + "' , HR_BKCHG = '" + BM_BKCHARGE.trim() + "' , HC_TYPE = '" + BM_TYPE.trim() + "' , HC_LOCATION = '" + BM_LOCA.trim() + "' \n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND HR_DIVI = '" + divi.trim() + "'\n"
                        + "AND HC_STS = '1'\n"
                        + "AND HC_RCNO = '" + BM_RCNO.trim() + "' ";

                stmt.executeUpdate(query_2);

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

    public static void updateReceipt_SCB_RBILLnew(String cono, String divi, String BM_DATE, String BM_AMOUNT, String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_FNNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + HPYNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_FNNO = '" + BM_FNNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                System.out.println(query);
                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE " + DBPRD + ".HEAD_RECIPT SET  H_PYNO = '" + HPYNO.toUpperCase() + "', H_LOCATION = '" + BM_LOCA + "' , H_TYPE = '" + BM_TYPE + "'  , H_CUNO = 'HEAD' \n"
                        + "WHERE H_RNNO  = '" + BM_FNNO + "'\n"
                        + "AND H_CONO  = '" + cono + "' AND H_STS = '1'\n"
                        + "AND H_DIVI  = '" + divi + "'";
                stmt2.executeUpdate(query2);
                System.out.println(query2);

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

    public static void updateReceipt_SCB_RBILL(String cono, String divi, String BM_DATE, String BM_AMOUNT, String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_RCNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + BM_CUNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_RCNO = '" + BM_RCNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                String query_2 = "UPDATE " + DBPRD + ".HR_RECEIPT\n"
                        + "SET HC_PYNO = '" + BM_CUNO.trim() + "', HC_REAMT = '" + Amt_Include_BKCHG + "' , HR_BKCHG = '" + BM_BKCHARGE.trim() + "' , HC_TYPE = '" + BM_TYPE.trim() + "' , HC_LOCATION = '" + BM_LOCA.trim() + "' \n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND HR_DIVI = '" + divi.trim() + "'\n"
                        + "AND HC_STS = '1'\n"
                        + "AND HC_RCNO = '" + BM_RCNO.trim() + "' ";

                stmt.executeUpdate(query_2);

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
    
    
    //***********************updateReceipt_BBL_QRnew************
    
    
    
    
    public static void updateReceipt_BBL_QRnew(String cono, String divi, String BM_DATE, String BM_AMOUNT, String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_FNNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + HPYNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_FNNO = '" + BM_FNNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                System.out.println(query);
                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE " + DBPRD + ".HEAD_RECIPT SET  H_PYNO = '" + HPYNO.toUpperCase() + "' , H_LOCATION = '" + BM_LOCA + "' , H_TYPE = '" + BM_TYPE + "'  , H_CUNO = 'HEAD' \n"
                        + "WHERE H_RNNO  = '" + BM_FNNO + "'\n"
                        + "AND H_CONO  = '" + cono + "' AND H_STS = '1'\n"
                        + "AND H_DIVI  = '" + divi + "'";
                stmt2.executeUpdate(query2);
                System.out.println(query2);

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
    
    
    
    
    
    //********************************************************

    public static void updateReceipt_BBL_BILLnew(String cono, String divi, String BM_DATE, String BM_AMOUNT, String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_FNNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + HPYNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_FNNO = '" + BM_FNNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                System.out.println(query);
                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE " + DBPRD + ".HEAD_RECIPT SET  H_PYNO = '" + HPYNO.toUpperCase() + "' , H_LOCATION = '" + BM_LOCA + "' , H_TYPE = '" + BM_TYPE + "'  , H_CUNO = 'HEAD' \n"
                        + "WHERE H_RNNO  = '" + BM_FNNO + "'\n"
                        + "AND H_CONO  = '" + cono + "' AND H_STS = '1'\n"
                        + "AND H_DIVI  = '" + divi + "'";
                stmt2.executeUpdate(query2);
                System.out.println(query2);

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

    public static void updateReceipt_BBL_BILL(String cono, String divi, String BM_DATE, String BM_AMOUNT, String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_RCNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + BM_CUNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_RCNO = '" + BM_RCNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                String query_2 = "UPDATE " + DBPRD + ".HR_RECEIPT\n"
                        + "SET HC_PYNO = '" + BM_CUNO.trim() + "', HC_REAMT = '" + Amt_Include_BKCHG + "' , HR_BKCHG = '" + BM_BKCHARGE.trim() + "' , HC_TYPE = '" + BM_TYPE.trim() + "' , HC_LOCATION = '" + BM_LOCA.trim() + "' \n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND HR_DIVI = '" + divi.trim() + "'\n"
                        + "AND HC_STS = '1'\n"
                        + "AND HC_RCNO = '" + BM_RCNO.trim() + "' ";

                stmt.executeUpdate(query_2);

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
    
    
    public static void updateReceipt_BBLnewWTF(String cono, String divi, String BM_DATE, String BM_AMOUNT, String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_FNNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET  BM_CUNO = '" + HPYNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_FNNO = '" + BM_FNNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE " + DBPRD + ".HEAD_RECIPT SET  H_PYNO = '" + HPYNO.toUpperCase() + "' , H_LOCATION = '" + BM_LOCA + "' , H_TYPE = '" + BM_TYPE + "'  , H_CUNO = 'HEAD' \n"
                        + "WHERE H_RNNO  = '" + BM_FNNO + "'\n"
                        + "AND H_CONO  = '" + cono + "' AND H_STS = '1'\n"
                        + "AND H_DIVI  = '" + divi + "' ";
                stmt2.executeUpdate(query2);
                System.out.println(query2);

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


    public static void updateReceipt_BBLnew(String cono, String divi, String BM_DATE, String BM_AMOUNT, String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_FNNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET  BM_CUNO = '" + HPYNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_FNNO = '" + BM_FNNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE " + DBPRD + ".HEAD_RECIPT SET  H_PYNO = '" + HPYNO.toUpperCase() + "' , H_LOCATION = '" + BM_LOCA + "' , H_TYPE = '" + BM_TYPE + "'  , H_CUNO = 'HEAD' \n"
                        + "WHERE H_RNNO  = '" + BM_FNNO + "'\n"
                        + "AND H_CONO  = '" + cono + "' AND H_STS = '1'\n"
                        + "AND H_DIVI  = '" + divi + "' ";
                stmt2.executeUpdate(query2);
                System.out.println(query2);

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
    
    
    public static void updateReceipt_BBL_CURnew(String cono, String divi, String BM_DATE, String BM_AMOUNT, String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_FNNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET  BM_CUNO = '" + HPYNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_FNNO = '" + BM_FNNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);

                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE " + DBPRD + ".HEAD_RECIPT SET  H_PYNO = '" + HPYNO.toUpperCase() + "' , H_LOCATION = '" + BM_LOCA + "' , H_TYPE = '" + BM_TYPE + "'  , H_CUNO = 'HEAD' \n"
                        + "WHERE H_RNNO  = '" + BM_FNNO + "'\n"
                        + "AND H_CONO  = '" + cono + "' AND H_STS = '1'\n"
                        + "AND H_DIVI  = '" + divi + "' ";
                stmt2.executeUpdate(query2);
                System.out.println(query2);

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

    public static void updateReceipt_BBL(String cono, String divi, String BM_DATE, String BM_AMOUNT, String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_RCNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + BM_CUNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_RCNO = '" + BM_RCNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                String query_2 = "UPDATE " + DBPRD + ".HR_RECEIPT\n"
                        + "SET HC_PYNO = '" + BM_CUNO.trim() + "', HC_REAMT = '" + Amt_Include_BKCHG + "' , HR_BKCHG = '" + BM_BKCHARGE.trim() + "' , HC_TYPE = '" + BM_TYPE.trim() + "' , HC_LOCATION = '" + BM_LOCA.trim() + "' \n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND HR_DIVI = '" + divi.trim() + "'\n"
                        + "AND HC_STS = '1'\n"
                        + "AND HC_RCNO = '" + BM_RCNO.trim() + "' ";

                stmt.executeUpdate(query_2);

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

    public static void updateReceipt_KBANK_BILLnew(String cono, String divi, String BM_DATE, String BM_AMOUNT, String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_FNNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + HPYNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_FNNO = '" + BM_FNNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                System.out.println(query);
                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE " + DBPRD + ".HEAD_RECIPT SET  H_PYNO = '" + HPYNO.toUpperCase() + "' , H_LOCATION = '" + BM_LOCA + "' , H_TYPE = '" + BM_TYPE + "'  , H_CUNO = 'HEAD' \n"
                        + "WHERE H_RNNO  = '" + BM_FNNO + "'\n"
                        + "AND H_CONO  = '" + cono + "' AND H_STS = '1'\n"
                        + "AND H_DIVI  = '" + divi + "'";
                stmt2.executeUpdate(query2);
                System.out.println(query2);

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

    public static void updateReceipt_KBANK_BILL(String cono, String divi, String BM_DATE, String BM_AMOUNT, String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_RCNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + BM_CUNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_RCNO = '" + BM_RCNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                String query_2 = "UPDATE " + DBPRD + ".HR_RECEIPT\n"
                        + "SET HC_PYNO = '" + BM_CUNO.trim() + "', HC_REAMT = '" + Amt_Include_BKCHG + "' , HR_BKCHG = '" + BM_BKCHARGE.trim() + "' , HC_TYPE = '" + BM_TYPE.trim() + "' , HC_LOCATION = '" + BM_LOCA.trim() + "' \n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND HR_DIVI = '" + divi.trim() + "'\n"
                        + "AND HC_STS = '1'\n"
                        + "AND HC_RCNO = '" + BM_RCNO.trim() + "' ";

                stmt.executeUpdate(query_2);

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

    public static void updateAccont_SCB(String cono, String divi, String BM_DATE, String BM_AMOUNT, String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_RCNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + BM_CUNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "',  BM_STATUS = '99' , BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_RCNO = '" + BM_RCNO.trim() + "' ";
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

    public static void updateReceipt_SCB(String cono, String divi, String BM_DATE, String BM_AMOUNT, String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_RCNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {

//                Statement stmt2 = conn.createStatement();
//                String  BM_PYNO = ""; 
//
//                String sqlpyno = " SELECT  COALESCE(COALESCE(NULLIF(OKPYNO,''), OKCUNO), '-')  AS HPYNO  FROM M3FDBPRD.OCUSMA\n"
//                        + "WHERE  OKSTAT = '20'\n"
//                        + "AND OKCUNO = '" + BM_CUNO + "'";
//
//                ResultSet mRes = stmt2.executeQuery(sqlpyno);
//
//                while (mRes.next()) {
//                    BM_PYNO = mRes.getString(1);
//                }
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        //                        + "SET BM_CUNO = '" + BM_CUNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"

                        + "SET BM_CUNO = '" + BM_CUNO.trim() + "', BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_RCNO = '" + BM_RCNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                String query_2 = "UPDATE " + DBPRD + ".HR_RECEIPT\n"
                        + "SET HC_PYNO = '" + BM_CUNO.trim() + "' , HC_REAMT = '" + Amt_Include_BKCHG + "' , HR_BKCHG = '" + BM_BKCHARGE.trim() + "' , HC_TYPE = '" + BM_TYPE.trim() + "' , HC_LOCATION = '" + BM_LOCA.trim() + "' \n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND HR_DIVI = '" + divi.trim() + "'\n"
                        + "AND HC_STS = '1'\n"
                        + "AND HC_RCNO = '" + BM_RCNO.trim() + "' ";

                stmt.executeUpdate(query_2);

//                String query_3 = "UPDATE BRLDTA0100.HEAD_RECIPT\n"
//                        + "SET H_CUNO = '" + BM_CUNO.trim() + "', H_PYNO = '" + BM_PYNO + "' \n"
//                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
//                        + "AND HR_DIVI = '" + divi.trim() + "'\n"
//                        + "AND HC_RCNO = '" + BM_RCNO.trim() + "'";
//
//                stmt.executeUpdate(query_3);
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

    public static void RETURNBM(String CONO, String DIVI, String ID) throws Exception {

        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "  UPDATE  "+DBPRD+".BANK_MAPPING  \n"
                        + "  SET  BM_CUNO = NULL , BM_RCNO = NULL , BM_BKCHARGE = 0 \n"
                        + "  WHERE   BM_FNNO  = '" + ID.trim() + "' AND BM_CONO  = '" + CONO.trim() + "'  AND BM_DIVI  = '" + DIVI.trim() + "'";

                System.out.println(query);
                stmt.executeUpdate(query);

                Statement stmt1 = conn.createStatement();
                String query1 = "  UPDATE  "+DBPRD+".HEAD_RECIPT  \n"
                        + "  SET  H_CUNO = NULL , H_PYNO = NULL , H_STS = 1 , H_VCNO = NULL , H_TYPE = NULL \n"
                        + "  WHERE   H_RNNO  = '" + ID.trim() + "' AND  H_CONO = '" + CONO.trim() + "'  AND  H_DIVI = '" + DIVI.trim() + "'";

                System.out.println(query1);
                stmt1.executeUpdate(query1);

                Statement stmt2 = conn.createStatement();
                String query2 = "   UPDATE  "+DBPRD+".HR_RECEIPT  \n"
                        + "  SET  HC_TRDT = NULL , HC_PYNO = NULL ,  HC_REAMT = 0 , HC_TYPE = NULL , H_TYPE = NULL ,HC_BANK = NULL ,HC_BANK = NULL ,HC_ACCODE = NULL ,HC_PYDT = NULL, HC_USER = NULL, HC_VCNO = NULL , HC_STS = 2 , HR_BKCHG = 0 , HC_FIXNO = NULL \n"
                        + "  WHERE   HC_FNNO  = '" + ID.trim() + "' AND  HR_CONO = '" + CONO.trim() + "'  AND  HR_DIVI = '" + DIVI.trim() + "'";

                System.out.println(query2);
                stmt2.executeUpdate(query2);

                Statement stmt3 = conn.createStatement();
                String query3 = "DELETE FROM   "+DBPRD+".LR_LINERECEIPT\n"
                        + "        WHERE LR_RCNO  IN  (SELECT HC_RCNO FROM "+DBPRD+".HR_RECEIPT hr WHERE  HC_FNNO = '"+ID.trim()+"' ) AND LR_CONO  = '"+CONO.trim()+"' AND LR_DIVI  = '"+DIVI.trim()+"'";

                System.out.println(query3);
                stmt3.executeUpdate(query3);
                
                
//                  Statement stmt4 = conn.createStatement();
//                String query4 = "DELETE FROM   M3FDBTST.LR_LINERECEIPT\n"
//                        + "        WHERE LR_RCNO  IN  (SELECT HC_RCNO FROM M3FDBTST.HR_RECEIPT hr WHERE  HC_FNNO = '"+ID.trim()+"' ) AND LR_CONO  = '"+CONO.trim()+"' AND LR_DIVI  = '"+DIVI.trim()+"'";
//
//                System.out.println(query4);
//                stmt4.executeUpdate(query4);

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

    }
    
    
    
    public static void RETURNRC(String CONO, String DIVI, String ID) throws Exception {

        Connection conn = ConnectDB.ConnectionDB();

        try {
            if (conn != null) {

//                Statement stmt = conn.createStatement();
//                String query = "  UPDATE  M3FDBTST.BANK_MAPPING  \n"
//                        + "  SET  BM_CUNO = NULL , BM_RCNO = NULL , BM_BKCHARGE = 0 \n"
//                        + "  WHERE   BM_FNNO  = '" + ID.trim() + "' AND BM_CONO  = '" + CONO.trim() + "'  AND BM_DIVI  = '" + DIVI.trim() + "'";
//
//                System.out.println(query);
//                stmt.executeUpdate(query);

                Statement stmt1 = conn.createStatement();
                String query1 = "  UPDATE  "+DBPRD+".HEAD_RECIPT  \n"
                        + "  SET  H_CUNO = NULL , H_PYNO = NULL , H_STS = 1 , H_VCNO = NULL , H_TYPE = NULL \n"
                        + "  WHERE   H_RNNO  = '" + ID.trim() + "' AND  H_CONO = '" + CONO.trim() + "'  AND  H_DIVI = '" + DIVI.trim() + "'";

                System.out.println(query1);
                stmt1.executeUpdate(query1);

                Statement stmt2 = conn.createStatement();
                String query2 = "   UPDATE  "+DBPRD+".HR_RECEIPT  \n"
                        + "  SET  HC_TRDT = NULL , HC_PYNO = NULL ,  HC_REAMT = 0 , HC_TYPE = NULL , H_TYPE = NULL ,HC_BANK = NULL ,HC_BANK = NULL ,HC_ACCODE = NULL ,HC_PYDT = NULL, HC_USER = NULL, HC_VCNO = NULL , HC_STS = 2 , HR_BKCHG = 0 , HC_FIXNO = NULL \n"
                        + "  WHERE   HC_FNNO  = '" + ID.trim() + "' AND  HR_CONO = '" + CONO.trim() + "'  AND  HR_DIVI = '" + DIVI.trim() + "'";

                System.out.println(query2);
                stmt2.executeUpdate(query2);

                Statement stmt3 = conn.createStatement();
                String query3 = "DELETE FROM   "+DBPRD+".LR_LINERECEIPT\n"
                        + "        WHERE LR_RCNO  IN  (SELECT HC_RCNO FROM "+DBPRD+".HR_RECEIPT hr WHERE  HC_FNNO = '"+ID.trim()+"' ) AND LR_CONO  = '"+CONO.trim()+"' AND LR_DIVI  = '"+DIVI.trim()+"'";

                System.out.println(query3);
                stmt3.executeUpdate(query3);
                
                
//                  Statement stmt4 = conn.createStatement();
//                String query4 = "DELETE FROM   M3FDBTST.LR_LINERECEIPT\n"
//                        + "        WHERE LR_RCNO  IN  (SELECT HC_RCNO FROM M3FDBTST.HR_RECEIPT hr WHERE  HC_FNNO = '"+ID.trim()+"' ) AND LR_CONO  = '"+CONO.trim()+"' AND LR_DIVI  = '"+DIVI.trim()+"'";
//
//                System.out.println(query4);
//                stmt4.executeUpdate(query4);

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

    }

    public static void updateReceipt_SCBnew(String cono, String divi, String BM_DATE, String BM_AMOUNT, String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_FNNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

//        System.out.println("xxxxxxxxx");
//        System.out.println(cono);
//        System.out.println(divi);
//        System.out.println(BM_DATE);
//        System.out.println(BM_AMOUNT);
//        System.out.println(HPYNO); // null
//        System.out.println(BM_BKCHARGE);
//        System.out.println(BM_OVPAY);
//        System.out.println(BM_CNDN); // ---
//        System.out.println(BM_ID);
//        System.out.println(BM_FNNO);
//        System.out.println(BM_TYPE);
//        System.out.println(BM_LOCA);
//        System.out.println(BM_FNNO);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        //                        + "SET BM_CUNO = '" + BM_CUNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "SET BM_CUNO = '" + HPYNO.trim() + "' ,  BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_FNNO = '" + BM_FNNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                System.out.println(query);
                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE " + DBPRD + ".HEAD_RECIPT SET  H_PYNO = '" + HPYNO.toUpperCase() + "', H_LOCATION = '" + BM_LOCA + "' , H_TYPE = '" + BM_TYPE + "' , H_CUNO = 'HEAD' \n"
                        + "WHERE H_RNNO  = '" + BM_FNNO + "' \n"
                        + "AND H_CONO  = '" + cono + "' AND H_STS = 1 \n"
                        + "AND H_DIVI  = '" + divi + "'";
                System.out.println(query2);
                stmt2.executeUpdate(query2);

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
    
    
    public static void updateReceipt_SCB_CURnew(String cono, String divi, String BM_DATE, String BM_AMOUNT, String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_FNNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

//        System.out.println("xxxxxxxxx");
//        System.out.println(cono);
//        System.out.println(divi);
//        System.out.println(BM_DATE);
//        System.out.println(BM_AMOUNT);
//        System.out.println(HPYNO); // null
//        System.out.println(BM_BKCHARGE);
//        System.out.println(BM_OVPAY);
//        System.out.println(BM_CNDN); // ---
//        System.out.println(BM_ID);
//        System.out.println(BM_FNNO);
//        System.out.println(BM_TYPE);
//        System.out.println(BM_LOCA);
//        System.out.println(BM_FNNO);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        //                        + "SET BM_CUNO = '" + BM_CUNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "SET BM_CUNO = '" + HPYNO.trim() + "' ,  BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_FNNO = '" + BM_FNNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                System.out.println(query);
                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE " + DBPRD + ".HEAD_RECIPT SET  H_PYNO = '" + HPYNO.toUpperCase() + "', H_LOCATION = '" + BM_LOCA + "' , H_TYPE = '" + BM_TYPE + "' , H_CUNO = 'HEAD' \n"
                        + "WHERE H_RNNO  = '" + BM_FNNO + "' \n"
                        + "AND H_CONO  = '" + cono + "' AND H_STS = 1 \n"
                        + "AND H_DIVI  = '" + divi + "'";
                System.out.println(query2);
                stmt2.executeUpdate(query2);

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
    
      public static void updateReceipt_SCBnewWTF(String cono, String divi, String BM_DATE, String BM_AMOUNT, String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_FNNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

//        System.out.println("xxxxxxxxx");
//        System.out.println(cono);
//        System.out.println(divi);
//        System.out.println(BM_DATE);
//        System.out.println(BM_AMOUNT);
//        System.out.println(HPYNO); // null
//        System.out.println(BM_BKCHARGE);
//        System.out.println(BM_OVPAY);
//        System.out.println(BM_CNDN); // ---
//        System.out.println(BM_ID);
//        System.out.println(BM_FNNO);
//        System.out.println(BM_TYPE);
//        System.out.println(BM_LOCA);
//        System.out.println(BM_FNNO);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        //                        + "SET BM_CUNO = '" + BM_CUNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "SET BM_CUNO = '" + HPYNO.trim() + "' ,  BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_FNNO = '" + BM_FNNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                System.out.println(query);
                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE " + DBPRD + ".HEAD_RECIPT SET  H_PYNO = '" + HPYNO.toUpperCase() + "', H_LOCATION = '" + BM_LOCA + "' , H_TYPE = '" + BM_TYPE + "' , H_CUNO = 'HEAD' \n"
                        + "WHERE H_RNNO  = '" + BM_FNNO + "' \n"
                        + "AND H_CONO  = '" + cono + "' AND H_STS = 1 \n"
                        + "AND H_DIVI  = '" + divi + "'";
                System.out.println(query2);
                stmt2.executeUpdate(query2);

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
      
      
      
       public static void updateReceipt_SCBnewNSD(String cono, String divi, String BM_DATE, String BM_AMOUNT, String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_FNNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

//        System.out.println("xxxxxxxxx");
//        System.out.println(cono);
//        System.out.println(divi);
//        System.out.println(BM_DATE);
//        System.out.println(BM_AMOUNT);
//        System.out.println(HPYNO); // null
//        System.out.println(BM_BKCHARGE);
//        System.out.println(BM_OVPAY);
//        System.out.println(BM_CNDN); // ---
//        System.out.println(BM_ID);
//        System.out.println(BM_FNNO);
//        System.out.println(BM_TYPE);
//        System.out.println(BM_LOCA);
//        System.out.println(BM_FNNO);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        //                        + "SET BM_CUNO = '" + BM_CUNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "SET BM_CUNO = '" + HPYNO.trim() + "' ,  BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_FNNO = '" + BM_FNNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                System.out.println(query);
                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE " + DBPRD + ".HEAD_RECIPT SET  H_PYNO = '" + HPYNO.toUpperCase() + "', H_LOCATION = '" + BM_LOCA + "' , H_TYPE = '" + BM_TYPE + "' , H_CUNO = 'HEAD' \n"
                        + "WHERE H_RNNO  = '" + BM_FNNO + "' \n"
                        + "AND H_CONO  = '" + cono + "' AND H_STS = 1 \n"
                        + "AND H_DIVI  = '" + divi + "'";
                System.out.println(query2);
                stmt2.executeUpdate(query2);

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


    public static void updateReceipt_SCB_RES(String cono, String divi, String BM_DATE, String BM_AMOUNT, String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_RCNO, String BM_TYPE, String BM_LOCA, String user) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + BM_CUNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_USER = '" + user.trim() + "' "
                        + "AND BM_RCNO = '" + BM_RCNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                String query_2 = "UPDATE " + DBPRD + ".HR_RECEIPT\n"
                        + "SET HC_PYNO = '" + BM_CUNO.trim() + "', HC_REAMT = '" + Amt_Include_BKCHG + "' , HR_BKCHG = '" + BM_BKCHARGE.trim() + "' , HC_TYPE = '" + BM_TYPE.trim() + "' , HC_LOCATION = '" + BM_LOCA.trim() + "' \n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND HR_DIVI = '" + divi.trim() + "'\n"
                        + "AND HC_STS = '1'\n"
                        + "AND HC_USER = '" + user.trim() + "'\n"
                        + "AND HC_RCNO = '" + BM_RCNO.trim() + "' ";

                stmt.executeUpdate(query_2);

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

    public static void updateReceipt_KBANKnew(String cono, String divi, String BM_DATE, String BM_AMOUNT, String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_FNNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

        System.out.println(cono);
        System.out.println(divi);
        System.out.println(BM_DATE);
        System.out.println(BM_AMOUNT);
        System.out.println(HPYNO);
        System.out.println(BM_BKCHARGE);
        System.out.println(BM_OVPAY);
        System.out.println(BM_CNDN);
        System.out.println(BM_FNNO);
        System.out.println(BM_TYPE);
        System.out.println(BM_LOCA);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + HPYNO.trim() + "' ,  BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_FNNO = '" + BM_FNNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                System.out.println(query);
                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE " + DBPRD + ".HEAD_RECIPT SET  H_PYNO = '" + HPYNO.toUpperCase() + "' , H_LOCATION = '" + BM_LOCA + "' , H_TYPE = '" + BM_TYPE + "' , H_CUNO = 'HEAD' \n"
                        + "WHERE H_RNNO  = '" + BM_FNNO + "'\n"
                        + "AND H_CONO  = '" + cono + "' AND H_STS = '1'\n"
                        + "AND H_DIVI  = '" + divi + "'";
                stmt2.executeUpdate(query2);
                System.out.println(query2);

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

    
    public static void updateReceipt_KBANK_CURnew(String cono, String divi, String BM_DATE, String BM_AMOUNT, String HPYNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_FNNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();

        System.out.println(cono);
        System.out.println(divi);
        System.out.println(BM_DATE);
        System.out.println(BM_AMOUNT);
        System.out.println(HPYNO);
        System.out.println(BM_BKCHARGE);
        System.out.println(BM_OVPAY);
        System.out.println(BM_CNDN);
        System.out.println(BM_FNNO);
        System.out.println(BM_TYPE);
        System.out.println(BM_LOCA);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + HPYNO.trim() + "' ,  BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_FNNO = '" + BM_FNNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                System.out.println(query);
                Statement stmt2 = conn.createStatement();
                String query2 = "UPDATE " + DBPRD + ".HEAD_RECIPT SET  H_PYNO = '" + HPYNO.toUpperCase() + "' , H_LOCATION = '" + BM_LOCA + "' , H_TYPE = '" + BM_TYPE + "' , H_CUNO = 'HEAD' \n"
                        + "WHERE H_RNNO  = '" + BM_FNNO + "'\n"
                        + "AND H_CONO  = '" + cono + "' AND H_STS = '1'\n"
                        + "AND H_DIVI  = '" + divi + "'";
                stmt2.executeUpdate(query2);
                System.out.println(query2);

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

    
    public static void updateReceipt_KBANK(String cono, String divi, String BM_DATE, String BM_AMOUNT, String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_RCNO, String BM_TYPE, String BM_LOCA) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + BM_CUNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_RCNO = '" + BM_RCNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                String query_2 = "UPDATE " + DBPRD + ".HR_RECEIPT\n"
                        + "SET HC_PYNO = '" + BM_CUNO.trim() + "', HC_REAMT = '" + Amt_Include_BKCHG + "' , HR_BKCHG = '" + BM_BKCHARGE.trim() + "'  , HC_TYPE = '" + BM_TYPE.trim() + "' , HC_LOCATION = '" + BM_LOCA.trim() + "'\n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND HR_DIVI = '" + divi.trim() + "'\n"
                        + "AND HC_STS = '1'\n"
                        + "AND HC_RCNO = '" + BM_RCNO.trim() + "' ";

                stmt.executeUpdate(query_2);

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

    public static void updateReceipt_KBANK_RES(String cono, String divi, String BM_DATE, String BM_AMOUNT, String BM_CUNO, String BM_BKCHARGE, String BM_OVPAY, String BM_CNDN, String BM_ID, String BM_RCNO, String BM_TYPE, String BM_LOCA, String user) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_CUNO = '" + BM_CUNO.trim() + "' , BM_BKCHARGE = '" + BM_BKCHARGE.trim() + "', BM_OVPAY = '" + BM_OVPAY.trim() + "', BM_CNDN = '" + BM_CNDN.trim() + "', BM_USER = '" + user.trim() + "'\n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + BM_DATE.trim() + "' "
                        + "AND BM_ID = '" + BM_ID.trim() + "' "
                        + "AND BM_RCNO = '" + BM_RCNO.trim() + "' ";
                stmt.executeUpdate(query);
                Double Amt_Include_BKCHG = Double.parseDouble(BM_AMOUNT) + Double.parseDouble(BM_BKCHARGE);
                String query_2 = "UPDATE " + DBPRD + ".HR_RECEIPT\n"
                        + "SET HC_PYNO = '" + BM_CUNO.trim() + "', HC_REAMT = '" + Amt_Include_BKCHG + "' , HR_BKCHG = '" + BM_BKCHARGE.trim() + "'  , HC_TYPE = '" + BM_TYPE.trim() + "', HC_USER = '" + user.trim() + "' , HC_LOCATION = '" + BM_LOCA.trim() + "'\n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND HR_DIVI = '" + divi.trim() + "'\n"
                        + "AND HC_STS = '1'\n"
                        + "AND HC_RCNO = '" + BM_RCNO.trim() + "' ";

                stmt.executeUpdate(query_2);

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

    public static void UpdatedataRC(String cono, String divi, String rcno, String HC_PYNO, String HC_REAMT, String HC_BANK, String trdt, String user, String fixno) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".HR_RECEIPT\n"
                        + "SET HC_TRDT = '" + trdt.trim() + "' , HC_PYNO = '" + HC_PYNO.trim() + "'  , HC_BANK = '" + HC_BANK + "' , HC_REAMT = '" + HC_REAMT + "' , HC_USER = '" + user.trim() + "' , HC_FIXNO = '" + fixno.trim() + "'  \n"
                        + "WHERE HC_RCNO = '" + rcno.trim() + "'";
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

    public static void UpdatedataBM(String cono, String divi, String amt, String time, String date, String rcno, String cuno, String username, String bank, String fnno, String acc) throws Exception {
        Connection conn = ConnectDB.ConnectionDB();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "UPDATE " + DBPRD + ".BANK_MAPPING\n"
                        + "SET BM_RCNO = '" + rcno.trim() + "' ,  BM_CUNO = '" + cuno.trim() + "' , BM_USER = '" + username + "', BM_TIME1 = '" + timestamp + "',BM_FNNO = '" + fnno + "', BM_LCODE = '" + acc + "'   \n"
                        + "WHERE BM_CONO = '" + cono.trim() + "' "
                        + "AND BM_DIVI = '" + divi.trim() + "' "
                        + "AND BM_DATE = '" + date.trim() + "' "
                        + "AND BM_TIME = '" + time.trim() + "' "
                        + "AND BM_AMOUNT = '" + amt.trim() + "' ";
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

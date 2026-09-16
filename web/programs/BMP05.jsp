<%-- 
    Document   : BMP05
    Created on : Mar 14, 2023, 3:50:51 PM
    Author     : Phongsathon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="container">
        <div class="container-fluid ">
            <div class="row shadow">


                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >Receipt No.</label>
                    <input type="text" class="form-control text-center "  id="rctxt" name="rctxt" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type"><br></label>

                    <button  type="button" name="searchbtn"  id="searchbtn"  class="form-control btn-success" value="searchbtn" style="width: 157px;">SEARCH RECEIPT</button> 
                </div>
            </div>
            <hr>
            <div class="row">
                <div id="grid" class="shadow"></div>

                <div class="container">
                    <div class="col-md-12">
                        <div class="row">
                            <div class="panel panel-default container" id="reportfield" >
                                <div class="panel-heading" style="background-color: black">
                                    <h3 class="panel-title"> <font color="#FFFFFF" id="rcnono" name="rcnono" >Receipt : </font></h3>
                                </div>

                                <br>
                                <div>
                                    <label for="type" > CONO. :</label>
                                    <input type="text"  id="conoip" name="conoip" width="10px" readonly="true" disabled="true" >
                                </div>
                                <div>
                                    <label 
                                        <label for="type" > DIVI. :</label>
                                    <input type="text"  id="diviip" name="diviip" width="10px" disabled="true" >
                                </div>
                                <div>
                                    <label 
                                        <label for="type" > BANK. :</label>
                                    <input type="text"  id="bankip" name="bankip" width="10px"  >
                                </div>
                                <div>
                                    <label 
                                        <label for="type" > AMONUT. :</label>
                                    <input type="text"  id="amtip" name="amtip" width="10px" >
                                </div>
                                
                                <div>
                                    <label 
                                        <label for="type" > CUSTOMER NO.. :</label>
                                    <input type="text"  id="HC_PYNO" name="HC_PYNO" width="10px" >
                                </div>
                                <div>
                                    <label 
                                        <label for="type" > TYPE. :</label>
                                    <input type="text"  id="tyip" name="tyip" width="10px" disabled="true" >
                                </div>
                                <div>
                                    <label 
                                        <label for="type" > Voucher. :</label>
                                    <input type="text"  id="vcip" name="vcip" width="10px" disabled="true" >


                                </div>
                                <br><!-- comment -->
                                <br>
                                <label for="type" > TRANSACTION DATE :</label>
                                <input type="text"  id="trdtip" name="trdtip" width="10px" >
                                <label for="type" > USER. :</label>
                                <!--                                <input type="text"  id="userip" name="userip" width="10px" >-->
                                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">  <label for="type" > USER :</label></div>
                                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                                  
                                    <select class="form-control form-control-user" name="vUser" id="vUser">
                                        <option disabled>────────────────────────</option>
                                        <option value="SANTIM_PUP">SANTIM_PUP</option>
                                        <option value="TAWA_HOA">TAWA_HOA</option>
                                        <option value="NITTAY_KOM">NITTAY_KOM</option>
                                        <option value="PRANEE_KHA">PRANEE_KHA</option>
                                        <option value="WANIDA_KOS">WANIDA_KOS</option>
                                        <option value="KAMONR_SAT">KAMONR_SAT</option>
                                        <option disabled>────────────────────────</option>
                                        <option value="SUWANN_RAK">SUWANN_RAK</option>
                                        <option value="PIYATH_SUK">PIYATH_SUK</option>
                                        <option value="CHOLTI_MEE">CHOLTI_MEE</option>
                                         <option value="SAVANE_KON">SAVANE_KON</option>
                                        <option disabled>────────────────────────</option>
                                        <!--<option value="PHONGS_PHO">PHONGS_PHO</option>-->

                                    </select>
                                </div>
                                <label 
                                    <label for="type" > FIX NO. :</label>
                                <input type="text"  id="fixip" name="fixip" width="10px"  >




                                <!--                                 <label for="type" >TRANSECTION DATE :</label>
                                                                <input type="text"  id="Userip" name="Userip" width="10px" >-->

                                <br>
                                <br>
                                <button  type="button" name="savebtn"  id="savebtn"  class="form-control btn-success" value="savebtn" style="width: 157px;">SAVE</button> 
                                <br>
                            </div>
                        </div>
                    </div>

                </div>
            </div>


        </div>
    </div>
</body>
</html>
<script>

    $("#searchbtn").click(function (e) {
        var cono = <%out.print(session.getAttribute("cono"));%>;
        var divi = <%out.print(session.getAttribute("divi"));%>;
        var rcno = document.getElementById("rctxt").value;


        document.getElementById("rcnono").innerHTML = "Receipt : " + rcno;
//        console.log(cono);
//        console.log(divi);
//        console.log(amt);
//        console.log(time);
//        console.log(vType);
        $.ajax({
            type: 'GET',
            url: './Sync',
            dataType: 'json',
            data: {
                page: "GetdatabyrcnoRC",
                cono: cono,
                divi: divi,
                rcno: rcno


            },
            async: false
        }).done(function (response) {
            $.each(response, function (i, obj) {
                document.getElementById("vUser").value = obj.HC_USER;
                document.getElementById("conoip").value = obj.HR_CONO;
                document.getElementById("diviip").value = obj.HR_DIVI;
                document.getElementById("bankip").value = obj.HC_BANK;
                document.getElementById("trdtip").value = obj.HC_TRDT;
                document.getElementById("amtip").value = obj.HC_REAMT;
                document.getElementById("tyip").value = obj.HC_TYPE;
                document.getElementById("vcip").value = obj.HC_VCNO;
                document.getElementById("fixip").value = obj.HC_FIXNO;
                 document.getElementById("HC_PYNO").value = obj.HC_PYNO;

//                document.getElementById("desip").value = obj.BM_DESC;
//                document.getElementById("rcusip").value = obj.BM_REFCU1;
//                document.getElementById("cunip").value = obj.BM_CUNA;
            });
        });
    });
    $("#savebtn").click(function (e) {

        var cono = <%out.print(session.getAttribute("cono"));%>;
        var divi = <%out.print(session.getAttribute("divi"));%>;
        var rcno = document.getElementById("rctxt").value;

        var trdtip = document.getElementById("trdtip").value;
        var userip = document.getElementById("vUser").value;
        var fixip = document.getElementById("fixip").value;
        
          var HC_PYNO = document.getElementById("HC_PYNO").value;
            var HC_REAMT = document.getElementById("amtip").value;
              var HC_BANK = document.getElementById("bankip").value;
        
 alert("xxx");

        $.ajax({
            type: 'GET',
            url: './Sync',
            data: {
                page: "UpdatedataRC",
                cono: cono,
                divi: divi,
                rcno: rcno,
                HC_PYNO:HC_PYNO,
                HC_REAMT:HC_REAMT,
                HC_BANK:HC_BANK,
                trdt: trdtip,
                user: userip,
                fixno: fixip


            },
            async: false
        }).done(function (response) {
            alert("SAVED");
        });
    });

</script>

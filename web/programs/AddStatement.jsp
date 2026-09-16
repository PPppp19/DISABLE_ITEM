<%-- 
    Document   : ULRC01
    Created on : Apr 4, 2023, 2:06:54 PM
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
                    <label for="type" >BANK</label>
                    <input type="text" class="form-control text-center "  id="vbank" name="vbank" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >BANK CODE.</label>
                    <input type="text" class="form-control text-center "  id="vbankcode" name="vbankcode" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >DATE.</label>
                    <input type="text" class="form-control text-center "  id="vdate" name="vdate" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >TIME.</label>
                    <input type="text" class="form-control text-center "  id="vtime" name="vtime" >
                </div>
<!--                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >BM_CUNO.</label>
                    <input type="text" class="form-control text-center "  id="vcus" name="vcus" >
                </div>-->
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >AMOUNT.</label>
                    <input type="text" class="form-control text-center "  id="vamt" name="vamt" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >DESC.</label>
                    <input type="text" class="form-control text-center "  id="vdesc" name="vdesc" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >REF CUS.</label>
                    <input type="text" class="form-control text-center "  id="vrefcus" name="vrefcus" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >ขาบัญชี.</label>
                    <input type="text" class="form-control text-center "  id="vlcode" name="vlcode" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >Customer name.</label>
                    <input type="text" class="form-control text-center "  id="vcuna" name="vcuna" >
                </div>


            </div>
            <hr>
            <div class="row">
                <button  type="button" name="addstate"  id="addstate"  class="form-control btn-success" value="addstate" style="width: 157px; ">ADD</button> 


            </div>


        </div>
    </div>
</body>
</html>

<script>

    $("#addstate").click(function (e) {
        var cono = <%out.print(session.getAttribute("cono"));%>;
        var divi = <%out.print(session.getAttribute("divi"));%>;
        var user = "<%out.print(session.getAttribute("username"));%>";

        var Bank = document.getElementById("vbank").value; 
        var BankCode = document.getElementById("vbankcode").value;
        var Date = document.getElementById("vdate").value; 
        var Time = document.getElementById("vtime").value; 
//        var CuS = document.getElementById("vcus").value; 
        var Amount = document.getElementById("vamt").value; 
        var Desc = document.getElementById("vdesc").value; 
        var RefCus = document.getElementById("vrefcus").value; 
        var Lcode = document.getElementById("vlcode").value; 
         var cuna = document.getElementById("vcuna").value; 
        
         
        
        alert("Add : " + cono + ":" +  divi + ":" + user + ":" +  Bank + ":" + BankCode + ":" + Date + ":" + Time  + ":" + Amount + ":" + Desc + ":" + RefCus + ":" + Lcode + ":" + cuna ); 
        
       
         $.ajax({
         type: 'GET',
         url: './Sync',
         dataType: 'text',
         data: {
         page: "addstatement",
         cono: cono,
         divi: divi,
         user: user, 
         bank: Bank,
         bankcode : BankCode,
         date : Date,
         time : Time, 
         amt : Amount,
         desc : Desc,
         refcus : RefCus,
         lcode : Lcode,
         cuna : cuna
         
         
         
         
         },
         async: false
         }).done(function (response) {
             
             alert("added"); 
         });
         
         

    });





</script>

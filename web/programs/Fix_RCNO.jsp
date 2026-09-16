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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/jszip.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/xlsx.js"></script>
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.css" />
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid-theme.min.css" />

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.js"></script>

    <body class="container">
        <div class="container-fluid ">
            <div class="row shadow">


                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >ID</label>
                    <input type="text" class="form-control text-center "  id="vrnno" name="vrnno" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label type="hidden" style=" visibility: hidden" >ID</label>
                    <button  type="button" name="addstate"  id="addstate"  class="form-control btn-success" value="addstate" style="width: 157px; ">SEARCH</button> 


                </div>

                <br>
                <br>
                <br>
                <br>

                <div id="jsGrid"></div>

                <br>
                <br>
                <br>

                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >RCNO</label>
                    <input type="text" class="form-control text-center " disabled="true"  id="vrcno" name="vrcno" >
                </div>

                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >BANK</label>
                    <input type="text" class="form-control text-center "  id="vbank" name="vbank" >
                </div>

                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >TYPE.</label>
                    <input type="text" class="form-control text-center "  id="vtype" name="vtype" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >DATE.</label>
                    <input type="text" class="form-control text-center "  id="vdate" name="vdate" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >PYDT.</label>
                    <input type="text" class="form-control text-center "  id="vpydt" name="vpydt" >
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
                    <label for="type" >PYNO.</label>
                    <input type="text" class="form-control text-center "  id="vpyno" name="vpyno" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >BANK CHARGE.</label>
                    <input type="text" class="form-control text-center "  id="vbkchg" name="vbkchg" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >ขาบัญชี.</label>
                    <input type="text" class="form-control text-center "  id="vlcode" name="vlcode" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >FNNO.</label>
                    <input type="text" class="form-control text-center "  id="vfnno" name="vfnno" >
                </div>


            </div>
            <!--<hr>-->
            <br>
            <div class="row">
                <button  type="button" name="addstate"  id="savestate"  class="form-control btn-success" value="savestate" style="width: 157px; ">SAVE</button> 


            </div>
            <hr>
            <br>
            <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                <label for="type" >CHANGS STATUS : RCNO</label>
                <input type="text" class="form-control text-center "  id="vCHANGE" name="vCHANGE" >
            </div>
            <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                <label for="type" >STATUS</label>
                <input  type="text" class="form-control text-center"  id="vsts" name="vsts" >
            </div>
            <br>


            <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                <button  type="button" name="searchstate"  id="searchstate"  class="form-control btn-success" value="searchstate" style="width: 157px; ">SEARCH</button> 


            </div>
            <div class="row">
                <button  type="button" name="CHANGEBTN"  id="CHANGEBTN"  class="form-control btn-primary" value="CHANGEBTN" style="width: 157px; ">CHANGE</button> 


            </div>

            <hr>
            <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                <label for="type" >DELETE : RCNO</label>
                <input type="text" class="form-control text-center "  id="DRCNO" name="DRCNO" >
            </div>
            <br>
            <div class="row">
                <button  type="button" name="DELETEBTN"  id="DELETEBTN"  class="form-control btn-danger" value="DELETEBTN" style="width: 157px; ">DELETED</button> 


            </div>


            <br>
            <hr>  

            <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                <label for="type" >SEARCH : ID  </label>
                <input disabled="true" type="text" class="form-control text-center "  id="SRCNO" name="SCNO" >
                
                <BR>
                <input type="text" class="form-control text-center "  id="CRCNO" name="CRCNO" >
            </div>
            <br>
            <div class="row">
                <button  type="button" name="CHECKID"  id="CHECKID"  class="form-control btn-primary" value="CHECKID" style="width: 157px; ">CHECK</button> 

                <br><hr>

            </div>
        </div>
    </body>
</html>

<script>




    $("#CHECKID").click(function (e) {

        var CRCNO = document.getElementById('CRCNO').value;
        var SRCNO = document.getElementById('SRCNO').value;
          var CONO = <%out.print(session.getAttribute("cono"));%>

               



        $.ajax({
            type: 'GET',
            url: './Sync',
            dataType: 'json',
            data: {
                page: "GetIDBYRCNO",
                CRCNO: CRCNO,
                CONO:CONO



            },
            async: false
        }).done(function (response) {

            document.getElementById('SRCNO').value = response;



        });

       






    });

    $("#CHANGEBTN").click(function (e) {

        var vCHANGE = document.getElementById('vCHANGE').value;
        var vsts = document.getElementById('vsts').value;
        var cono = <%out.print(session.getAttribute("cono"));%>
        var divi = <%out.print(session.getAttribute("divi"));%>

        $.ajax({
            type: 'GET',
            url: './Sync',
            dataType: 'json',
            data: {
                page: "setstatus",
                cono: cono,
                divi: divi,
                rcno: vCHANGE,
                sts: vsts


            },
            async: false
        });

        alert("CHANGED STS to : " + vsts);






    });

    $("#searchstate").click(function (e) {

        var vCHANGE = document.getElementById('vCHANGE').value;
        var cono = <%out.print(session.getAttribute("cono"));%>
        var divi = <%out.print(session.getAttribute("divi"));%>

        $.ajax({
            type: 'GET',
            url: './Sync',
            dataType: 'json',
            data: {
                page: "getstatus",
                cono: cono,
                divi: divi,
                rcno: vCHANGE,

            },
            async: false
        }).done(function (response) {

            document.getElementById('vsts').value = response;



        });


    });

    $("#savestate").click(function (e) {

        var cono = <%out.print(session.getAttribute("cono"));%>
        var divi = <%out.print(session.getAttribute("divi"));%>
        var user = "<%out.print(session.getAttribute("username"));%>";
        var rcno = document.getElementById("vrcno").value;
        var bank = document.getElementById("vbank").value;
        var type1 = document.getElementById("vtype").value;
        var date = document.getElementById("vdate").value;
        var pydt = document.getElementById("vpydt").value;
        var amt = document.getElementById("vamt").value;
        var pyno = document.getElementById("vpyno").value;
        var bkchg = document.getElementById("vbkchg").value;
        var lcode = document.getElementById("vlcode").value;
        var fnno = document.getElementById("vfnno").value;


        console.log(cono);
        console.log(divi);
        console.log(user);
        console.log(rcno);
        console.log(bank);
        console.log(type1);
        console.log(date);
        console.log(pydt);
        console.log(amt);
        console.log(pyno);
        console.log(bkchg);
        console.log(lcode);
        console.log(fnno);

        $.ajax({
            type: 'GET',
            url: './Sync',
            dataType: 'json',
            data: {
                page: "SAVERC",
                cono: cono,
                divi: divi,
                rcno: rcno,
                bank: bank,
                type: type1,
                date: date,
                pydt: pydt,
                amt: amt,
                pyno: pyno,
                bkchg: bkchg,
                lcode: lcode,
                fnno: fnno,
                user: user

            },
            async: false
        }).done(function (response) {

            alert("SAVED");



        });

        alert("SAVED");


    });



    $("#DELETEBTN").click(function (e) {

        var cono = <%out.print(session.getAttribute("cono"));%>;
        var divi = <%out.print(session.getAttribute("divi"));%>;
        var rcno = document.getElementById("DRCNO").value;



        if (window.confirm(`DELETE RCNO`)) {


            $.ajax({
                type: 'GET',
                url: './Sync',
                data: {
                    page: "DELRCNO",
                    cono: cono,
                    divi: divi,
                    rcno: rcno
                },
                async: false
            }).done(function () {


                alert("DELETED");


            });
        } else {
            //  alert("CANCEL");

        }

    });

    $("#addstate").click(function (e) {
        var rnno = document.getElementById("vrnno").value;


        $("#jsGrid").jsGrid({
            width: "100%",
            height: "auto",
            filtering: true,
            inserting: false,
            editing: true,
            sorting: true,
            paging: true,
            autoload: true,
            controller: {
                loadData: function (filter) {
                    var data = $.Deferred();
                    var cono = <%out.print(session.getAttribute("cono"));%>
                    var divi = <%out.print(session.getAttribute("divi"));%>

                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'json',
                        data: {
                            page: "Call_GridRNNO",
                            cono: cono,
                            divi: divi,
                            rnno: rnno
                        },
                        async: false
                    }).done(function (response) {
                        data.resolve(response);




                    });
                    return data.promise();
                },
                updateItem: function (item) {


                    var cono = <%out.print(session.getAttribute("cono"));%>
                    var divi = <%out.print(session.getAttribute("divi"));%>
                    var rcno = item.H_RCNO;

                    alert("PYNO :" + item.H_PYNO);
                    alert("ID :" + item.H_RNNO);
                    alert("CUNO :" + item.H_CUNO);
                    alert("CUNO :" + item.H_RCNO);


                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'JSON',
                        data: {
                            page: "updateHEADfix",
                            cono: cono,
                            divi: divi,
                            rcno: item.H_RCNO,
                            pyno: item.H_PYNO,
                            cuno: item.H_CUNO,
                            fnno: item.H_RNNO,

                        },
                        async: false
                    }).done(function (response) {



                    });
                    alert("Updated");

                }
            },

            fields: [

                {title: "CONO", width: 30, name: "H_CONO", type: "text", align: "center", editing: false},
                {title: "DIVI", width: 30, name: "H_DIVI", type: "text", align: "center", editing: false},
                {title: "RCNO", name: "H_RCNO", type: "text", align: "center", editing: false, width: 40},
                {title: "RNNO", name: "H_RNNO", type: "text", align: "center", editing: true, width: 40},
                {title: "CUNO", name: "H_CUNO", type: "text", align: "center", editing: true, width: 40},
                {title: "PYNO", name: "H_PYNO", type: "text", align: "center", editing: true, width: 40},
                {title: "STS", name: "H_STS", type: "text", align: "center", editing: false, width: 40},
                {title: "VCNO", name: "H_VCNO", type: "text", align: "center", editing: false, width: 40},
                {type: "control", deleteButton: false}
            ],
            rowClick: function (args) {
//                console.log(args.item.H_RCNO);

                //document.getElementById("vrcno").value = args.item.H_RCNO;
                var cono = <%out.print(session.getAttribute("cono"));%>
                var divi = <%out.print(session.getAttribute("divi"));%>
                var rcno = args.item.H_RCNO;

                $.ajax({
                    type: 'GET',
                    url: './Sync',
                    dataType: 'JSON',
                    data: {
                        page: "getHRRECEIPT",
                        cono: cono,
                        divi: divi,
                        rcno: rcno

                    },
                    async: false
                }).done(function (response) {

                    $.each(response, function (i, obj) {
                        document.getElementById("vrcno").value = obj.HC_RCNO;
                        document.getElementById("vbank").value = obj.HC_BANK;
                        document.getElementById("vlcode").value = obj.HC_ACCODE;
                        document.getElementById("vtype").value = obj.HC_TYPE;
                        document.getElementById("vdate").value = obj.HC_TRDT;
                        document.getElementById("vamt").value = obj.HC_REAMT;
                        document.getElementById("vpyno").value = obj.HC_PYNO;
                        document.getElementById("vamt").value = obj.HC_REAMT;
                        document.getElementById("vfnno").value = obj.HC_FNNO;
                        document.getElementById("vpydt").value = obj.HC_PYDT;
                        document.getElementById("vbkchg").value = obj.HR_BKCHG;




                    });

                });



            }

        });

//        var cono = <%out.print(session.getAttribute("cono"));%>;
//        var divi = <%out.print(session.getAttribute("divi"));%>;
//        var user = "<%out.print(session.getAttribute("username"));%>";
//
//        var Bank = document.getElementById("vbank").value; 
//        var BankCode = document.getElementById("vbankcode").value;
//        var Date = document.getElementById("vdate").value; 
//        var Time = document.getElementById("vtime").value; 
////        var CuS = document.getElementById("vcus").value; 
//        var Amount = document.getElementById("vamt").value; 
//        var Desc = document.getElementById("vdesc").value; 
//        var RefCus = document.getElementById("vrefcus").value; 
//        var Lcode = document.getElementById("vlcode").value; 
//         var cuna = document.getElementById("vcuna").value; 
//        
//         
//        
//        alert("Add : " + cono + ":" +  divi + ":" + user + ":" +  Bank + ":" + BankCode + ":" + Date + ":" + Time  + ":" + Amount + ":" + Desc + ":" + RefCus + ":" + Lcode + ":" + cuna ); 
//        
//       
//         $.ajax({
//         type: 'GET',
//         url: './Sync',
//         dataType: 'text',
//         data: {
//         page: "addstatement",
//         cono: cono,
//         divi: divi,
//         user: user, 
//         bank: Bank,
//         bankcode : BankCode,
//         date : Date,
//         time : Time, 
//         amt : Amount,
//         desc : Desc,
//         refcus : RefCus,
//         lcode : Lcode,
//         cuna : cuna
//         
//         
//         
//         
//         },
//         async: false
//         }).done(function (response) {
//         });
//         


    });





</script>

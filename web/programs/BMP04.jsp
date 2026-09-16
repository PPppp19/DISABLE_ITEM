<%-- 
    Document   : BMP04
    Created on : Mar 14, 2023, 9:27:41 AM
    Author     : Phongsathon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/jszip.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/xlsx.js"></script>
        <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.css" />
        <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid-theme.min.css" />

        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.js"></script>

    </head>

    <style>


        .vertical-center {
            margin: 0;
            position: absolute;
            top: 50%;
            -ms-transform: translateY(-50%);
            transform: translateY(-50%);
        }
        .jsgrid-cell {
            overflow: hidden;
        }

        .jsgrid-header-row>.jsgrid-header-cell {
            background: #ffffff;
        }
        .table {
            display: table;
            border-collapse: separate;
            box-sizing: border-box;
            text-indent: initial;
            border-spacing: 2px;
            border-color: black;
        }

    </style>
    <body class="container">
        <div class="container-fluid ">
            <div class="row shadow">

                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                    <label for="type">Type</label>
                    <select class="form-control form-control-user" name="vType" id="vType">
                        <option disabled>────────────────────────</option>
                        <option value="SCB">SCB</option>
                        <option value="KBANK">KBANK</option>
                        <option value="BBL">BBL</option>
                        <option disabled>────────────────────────</option>
                        <option value="SCB_BILL">SCB (Bill Payment)</option>
                        <option value="KBANK_BILL">KBANK (Bill Payment)</option>
                        <option value="BBL_BILL">BBL (Bill Payment)</option>
                        <option disabled>────────────────────────</option>
                        <option value="SCB_MMN">SCB (MAE MANEE)</option>
                        <option disabled>────────────────────────</option>
                        <option value="SCB_CUR">SCB (Current)</option>
                        <option value="KBANK_CUR">KBANK (Current)</option>
                        <option value="BBL_CUR">BBL (Current)</option>
                        <option disabled>────────────────────────</option>
                        <option value="BBL_QR">BBL (Bill QR Code)</option>

                    </select>
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >Date</label>
                    <input type="date" class="form-control text-center "  id="vDate" name="vDate" >
                </div>

                <!--                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                                    <label for="type">Receipt Type</label>
                                    <select class="form-control form-control-user" name="vRCType" id="vRCType">
                                        <option value="TRANSFER">Normal</option>
                                        <option value="TRANSFER_EXP">Expense</option>
                                        <option value="TRANSFER_RESERVE">Reserve</option>
                                        <option value="TRANSFER_CHK">Cash Check Key</option>
                                    </select>
                                </div>-->

                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >TIME</label>
                    <input type="text" class="form-control text-center "  id="timetxt" name="timetxt" >
                    <input type="time" id="appt" name="appt" value="00:00:01" style="visibility: hidden" required>
                    <!--            <label for="type" >Date</label>
                                <input type="date" class="form-control text-center "  id="vDate" name="vDate" >-->
                </div>

                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >Amount.</label>
                    <input type="text" class="form-control text-center "  id="amttxt" name="amttxt" >

                    <!--            <label for="type" >Date</label>
                                <input type="date" class="form-control text-center "  id="vDate" name="vDate" >-->
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type"><br></label>

                    <button  type="button" name="searchbtn"  id="searchbtn"  class="form-control btn-success" value="searchbtn" style="width: 157px;">SEARCH_BM</button> 
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
                                    <h3 class="panel-title"> <font color="#FFFFFF" id="rcnono" name="rcnono" >BANK MAPPING LIST. : </font></h3>
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
                                    <input type="text"  id="bankip" name="bankip" width="10px" disabled="true" >
                                </div>
                                <div>
                                    <label 
                                        <label for="type" > DESCRIPTION. :</label>
                                    <input type="text"  id="desip" name="desip" width="10px" disabled="true" >
                                </div>
                                <div>
                                    <label 
                                        <label for="type" > REF_CUS. :</label>
                                    <input type="text"  id="rcusip" name="rcusip" width="10px" disabled="true" >
                                </div>
                                <div>
                                    <label 
                                        <label for="type" > Customer NAME. :</label>
                                    <input type="text"  id="cunip" name="cunip" width="10px" disabled="true" >


                                </div>
                                <br><!-- comment -->
                                <br>
                                <label for="type" > Customer No. :</label>
                                <input type="text"  id="cusip" name="cusip" width="10px" >
                                <!--                                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                                
                                                                    <select class="form-control form-control-user" name="cunip" id="cunip">
                                                                        <option> Please Select Customer No.</option>
                                                                        <option value="TH01077986">TH01077986</option>
                                                                        <option value="TH01077001">TH01077001</option>
                                                                        <option value="TH01077002">TH01077002</option>
                                                                        <option value="TH01077003">TH01077003</option>
                                                                        <option value="TH01077004">TH01077004</option>
                                                                        <option value="TH01077005">TH01077005</option>
                                
                                
                                                                    </select>
                                                                </div>-->


                                <label for="type" > RECEIPT NO. :</label>
                                <input type="text"  id="rcip" name="rcip" width="10px" >

                                <label for="type" > ID NO. :</label>
                                <input type="text"  id="fnno" name="fnno" width="10px" >

                                <label for="type" > ACCODE :</label>
                                <input type="text"  id="acc" name="acc" width="10px" >




                                <!--                                 <label for="type" >TRANSECTION DATE :</label>
                                                                <input type="text"  id="Userip" name="Userip" width="10px" >-->

                                <br>
                                <br>

                                <button  type="button" name="savebtn"  id="savebtn"  class="form-control btn-success" value="savebtn" style="width: 157px;">SAVE</button> 
                                <br>
                                <button  type="button" name="delbtn"  id="delbtn"  class="form-control btn-danger" value="delbtn" style="width: 157px;">DELETE</button> 
                                <br>
                            </div>
                        </div>
                    </div>

                </div>
            </div>


        </div>
    </div>
</body>

<script>//
//function getGrid_SCB(date, bank, rctype) {
//            $("#grid").jsGrid({
//                width: "100%",
//                height: "700",
//                filtering: true,
//                inserting: false,
//                editing: true,
//                sorting: true,
//                paging: true,
//                autoload: true,
//                pageSize: 10000,
//                noDataContent: "Not found",
//                loadMessage: "Please, wait...",
//                onItemUpdated: function (args) {
//                    console.log(args.item.BM_CUNO);
//
//                    // todo
////                    $.ajax({
////                        type: "POST",
////                        url: "./Sync",
////                        dataType: 'json',
////                        async: false,
////                        cache: false,
////                        data: {
////                            page: "updatenewReceipt_SCB",
////                            cono: cono,
////                            divi: divi,
////                            HC_PYNO: args.item.BM_CUNO ,
////                            HC_PYDT: args.item.BM_DATE
////                            
////
////                        },
////                    });
////                    $("#grid").jsGrid("loadData");
//
//                    UpdateColPos(2);
//                },
//                onItemEditing: function (args) {
//                    console.log("PPPP");
//
//                    setTimeout(function () {
//                        UpdateColPos(2);
//                    }, 1);
//                },
//                onRefreshed: function (args) {
//                    UpdateColPos(2);
//                },
//                controller: {
//                    loadData: function (filter) {
//                        var data = $.Deferred();
//                        var cono = <%out.print(session.getAttribute("cono"));%>;
//                        var divi = <%out.print(session.getAttribute("divi"));%>;
//                        $.ajax({
//                            type: 'GET',
//                            url: './Sync',
//                            dataType: 'json',
//                            data: {
//                                page: "Grid_SCB",
//                                cono: cono,
//                                divi: divi,
//                                date: $("#vDate").val(),
//                                bank: bank
//
//                            },
//                            async: false
//                        }).done(function (response) {
//                            console.log(response);
//                            response = $.grep(response, function (item) {
//                                return(!filter.BM_ACCODE || (item.BM_ACCODE.indexOf(filter.BM_ACCODE) > -1))
//                                        && (!filter.BM_DESC || (item.BM_DESC.indexOf(filter.BM_DESC) > -1))
//                                        && (!filter.BM_DATE || (item.BM_DATE.indexOf(filter.BM_DATE) > -1))
//                                        && (!filter.BM_TIME || (item.BM_TIME.indexOf(filter.BM_TIME) > -1))
//                                        && (!filter.BM_AMOUNT || (item.BM_AMOUNT.indexOf(filter.BM_AMOUNT) > -1))
//                                        && (!filter.BM_CUNO || (item.BM_CUNO.indexOf(filter.BM_CUNO) > -1))
//                                        && (!filter.custName || (item.custName.indexOf(filter.custName) > -1))
//                                        && (!filter.BM_BKCHARGE || (item.BM_BKCHARGE.indexOf(filter.BM_BKCHARGE) > -1))
//                                        && (!filter.BM_OVPAY || (item.BM_OVPAY.indexOf(filter.BM_OVPAY) > -1))
//                                        && (!filter.BM_CNDN || (item.BM_CNDN.indexOf(filter.BM_CNDN) > -1))
//                                        && (!filter.BM_TYPE || (item.BM_TYPE.indexOf(filter.BM_TYPE) > -1))
//                                        && (!filter.BM_LOCA || (item.BM_LOCA.indexOf(filter.BM_LOCA) > -1))
//                                        && (!filter.HC_RCNO || (item.HC_RCNO.indexOf(filter.HC_RCNO) > -1))
//                                        && (!filter.HC_VCNO || (item.HC_VCNO.indexOf(filter.HC_VCNO) > -1));
//
//                            });
//                            data.resolve(response);
//
//                            console.log(response);
//                            console.log("response");
//                        });
//                        return data.promise();
//                    },
//                    updateItem: function (item) {
//                        console.log("***************");
//                        console.log(item);
//                        console.log("***************");
//
//                        var cono = <%out.print(session.getAttribute("cono"));%>;
//                        var divi = <%out.print(session.getAttribute("divi"));%>;
//                        var user = "<%out.print(session.getAttribute("username"));%>";
//
//                        item.cono = cono;
//                        item.divi = divi;
//                        item.bank = "SCB";
//                        item.user = user;
////                        if (item.BM_TYPE == "TRANSFER_RESERVE") {
////                            if (item.BM_RCNO !== "") {
////                                item.page = "updateReceipt_SCB_RES";
////                            } else {
////                                item.page = "createReceipt_SCB_RES";
////                            }
////                        } else {
//
//                        if (item.BM_RCNO !== "") {
//
//                            if (item.BM_LOCA !== "ACCOUNT") {
//                                item.page = "updateReceipt_SCB";
//                            } else {
//                                console.log("updaupdaupduapdupadup");
//                                item.page = "updateAccont";
//                            }
//
//                        } else {
//                            if (item.BM_LOCA !== "ACCOUNT") {
//
//                                item.page = "createReceipt_SCB";
//                            } else {
//                                item.page = "updateAccont";
//                            }
//
//                        }
////                        }
//
//
//
//                        $.ajax({
//                            type: "POST",
//                            url: "./Sync",
//                            dataType: 'json',
//                            async: false,
//                            cache: false,
//                            data: item,
//                        });
//                        $("#grid").jsGrid("loadData");
//                    }
//                },
//                fields: [
//                    {title: "AccNo", name: "BM_ACCODE", type: "text", align: "center", editing: false, width: "85"},
//                    {title: "Description", name: "BM_DESC", type: "text", align: "center", width: "150", editing: false},
//                    {title: "Date", name: "BM_DATE", type: "text", align: "center", editing: false, width: "80"},
//                    {title: "Time", name: "BM_TIME", type: "text", align: "center", editing: false},
//                    {title: "Amount", name: "BM_AMOUNT", type: "text", align: "right", editing: false, itemTemplate: function (value) {
//                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
//                        }},
//                    {title: "CustCode", width: "120", name: "BM_CUNO", type: "text", align: "center"},
//                    {title: "CustName", width: "150", name: "custName", type: "text", align: "center", editing: false},
//                    {title: "BankCharge", name: "BM_BKCHARGE", type: "number", align: "center"},
//                    {title: "OverPay", name: "BM_OVPAY", type: "number", align: "center"},
//                    {title: "CN/DN", name: "BM_CNDN", type: "text", align: "center"},
//                    {title: "Type", name: "BM_TYPE", type: "select", items: Type, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
//                    {title: "Location", name: "BM_LOCA", type: "select", items: LocationCode, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
//                    {title: "Receipt", name: "HC_RCNO", type: "text", align: "center", editing: false},
//                    {title: "Voucher", name: "HC_VCNO", type: "text", align: "center", editing: false},
//
//                    {type: "control", deleteButton: false}
//                ]
//            });
//
//        }



    $("#searchbtn").click(function (e) {
        var appt = document.getElementById("appt").value;

//        alert(appt + "value :" + formatTime1(appt));
        var cono = <%out.print(session.getAttribute("cono"));%>;
        var divi = <%out.print(session.getAttribute("divi"));%>;
//        var rcno = document.getElementById("rcnotxt").value;
        var time = document.getElementById("timetxt").value;
        var amt = document.getElementById("amttxt").value;
        var date = document.getElementById("vDate").value;
        var vType = document.getElementById("vType").value;

        document.getElementById("rcnono").innerHTML = "BANK MAPPING LIST.";
        console.log(cono);
        console.log(divi);
        console.log(formatedate(date));
        console.log(amt);
        console.log(time);
        console.log(vType);
        $.ajax({
            type: 'GET',
            url: './Sync',
            dataType: 'json',
            data: {
                page: "Getdatabyrcno",
                cono: cono,
                divi: divi,
                amt: amt,
//                time:  formatTime1(appt),
                time: time,
                date: formatedate(date),
                bank: vType

            },
            async: false
        }).done(function (response) {
            $.each(response, function (i, obj) {
                document.getElementById("rcip").value = obj.BM_RCNO;
                document.getElementById("conoip").value = obj.BM_CONO;
                document.getElementById("diviip").value = obj.BM_DIVI;
                document.getElementById("bankip").value = obj.BM_BANK;
                document.getElementById("cusip").value = obj.BM_CUNO;
                document.getElementById("desip").value = obj.BM_DESC;
                document.getElementById("rcusip").value = obj.BM_REFCU1;
                document.getElementById("cunip").value = obj.BM_CUNA;
                document.getElementById("fnno").value = obj.BM_FNNO;
                document.getElementById("acc").value = obj.BM_LCODE;
            });
        });
    });
    $("#savebtn").click(function (e) {

        var cono = <%out.print(session.getAttribute("cono"));%>;
        var divi = <%out.print(session.getAttribute("divi"));%>;
        var username = "<%out.print(session.getAttribute("username"));%>";
        var rcno = document.getElementById("rcip").value;
        var cuno = document.getElementById("cusip").value;
        var time = document.getElementById("timetxt").value;
        var amt = document.getElementById("amttxt").value;
        var date = document.getElementById("vDate").value;
        var vType = document.getElementById("vType").value;
        var vFNNO = document.getElementById("fnno").value;
        var acc = document.getElementById("acc").value;
        $.ajax({
            type: 'GET',
            url: './Sync',
            data: {
                page: "UpdatedataBM",
                cono: cono,
                divi: divi,
                amt: amt,
                time: time,
                date: formatedate(date),
                rcno: rcno,
                cuno: cuno,
                username: username,
                bank: vType,
                fnno: vFNNO,
                acc: acc

            },
            async: false
        }).done(function (response) {
            alert("SAVED");
        });
    });


    $("#delbtn").click(function (e) {

        if (confirm("Are you sure to Delete? ") === true) {


            var cono = <%out.print(session.getAttribute("cono"));%>;
            var divi = <%out.print(session.getAttribute("divi"));%>;
            var username = "<%out.print(session.getAttribute("username"));%>";
            var rcno = document.getElementById("rcip").value;
            var cuno = document.getElementById("cusip").value;
            var time = document.getElementById("timetxt").value;
            var amt = document.getElementById("amttxt").value;
            var date = document.getElementById("vDate").value;
            var vType = document.getElementById("vType").value;
            if (time !== "" & amt !== "") {

                $.ajax({
                    type: 'GET',
                    url: './Sync',
                    data: {
                        page: "DeletedataBM",
                        cono: cono,
                        divi: divi,
                        amt: amt,
                        time: time,
                        date: formatedate(date),
                        rcno: rcno,
                        cuno: cuno,
                        username: username,
                        bank: vType

                    },
                    async: false
                }).done(function (response) {
                    alert("DELETED");
                });
            } else {
                alert("Please insert  all fields");
            }
        } else {

        }
    });


    function formatedate(date) {
//               var date = document.getElementById("vDate").value;
        var date = date.split("-");
        var years = date[0];
        var months = date[1];
        var day = date[2];
        return years + months + day;
    }

    function formatTime1(time) {
        var time = time.split(":");
        var hours = time[0];
        var inthours = parseInt(hours) % 12 || 12;

        var minutes = time[1];
        var second = time[2];

        var type = "ZZ";
        if (parseInt(hours) >= 12)
        {
            type = "PM";
        } else
        {
            type = "AM";
        }


        return inthours.toString() + ":" + minutes + ":" + second + " " + type;

    }


</script> 
</html>

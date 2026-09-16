<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Excel</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/jszip.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/xlsx.js"></script>
        <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.css" />
        <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid-theme.min.css" />

        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.js"></script>

        <style>

            .jsgrid-header-row>.jsgrid-header-cell {
                background-color: #000000;
                color: #cd8b9a;
                border : none;
                /*            background-image: linear-gradient(#6fcaf3, #71f5fc);*/
                /*                       
                            font-family: "Roboto Slab";
                            background-image: linear-gradient(#1c385c, #6b808d);
                
                            font-size: 1.2em;
                            font-weight: normal;
                            align-content: center;
                            text-align: center;
                            align-self: center;*/
            }

            .jsgrid-filter-row > .jsgrid-cell{
                background-color: #000000 ;
                color:#f0a8be;
                border-color: #f0a8be;


            }

            .jsgrid-row>.jsgrid-cell.blue-cell {
                background-color: #F3B9CB ;
                color:#000000;
                border-color: #cd8b9a;
            }

              .jsgrid-filter-row > .jsgrid-cell{
            background-color: #000000 ;
            color:#f0a8be;
            border-color: #f0a8be;


        }
        
           .jsgrid-nodata-row .jsgrid-cell {
            padding: .5em 0;
            text-align: center;
            background-color: #cd8b9a;
            color: #000000; 
        }
        
            .jsgrid-alt-row>.jsgrid-cell {
                background-color: #F3B9CB;
                color:#000000;
                border-color: #cd8b9a;
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

    </head>
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
                         <option disabled>───────────WTF──────────</option>
                         <option value="BBLW">BBL WTF</option>

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
                                        <option value="TRANSFER_DEPOSIT">DEPOSIT</option>
                                        <option value="TRANSFER_CHK">Cash Check Key</option>
                                    </select>
                                </div>-->

                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type"><br></label>
                    <button id="vSearch" class="form-control btn-danger">Search</button>
                </div>
            </div>
            <hr>
            <div class="row">
                <div id="grid" class="shadow"></div>
            </div>
        </div>
    </body>


    <script>
        $('.jsgrid-grid-body').scroll((function () {
            var left = $('.jsgrid-grid-body').scrollLeft() < $('.jsgrid-grid-body .jsgrid-table').width() - $('.jsgrid-grid-body').width() + 1
                    ? $('.jsgrid-grid-body').scrollLeft() : $('.jsgrid-grid-body .jsgrid-table').width() - $('.jsgrid-grid-body').width() + 1;
            $('.jsgrid-header-row th:nth-child(-n+1), .jsgrid-filter-row td:nth-child(-n+1), .jsgrid-insert-row td:nth-child(-n+2), .jsgrid-grid-body tr td:nth-child(-n+1)')
                    .css({
                        "position": "relative",
                        "left": left,
                        "box-shadow": "-1px -1px 0px 0px #e9e9e9 inset"
                    });
        }));

        $('.jsgrid-grid-body').scroll(function () {
            UpdateColPos(1);
        });


        function GenRcno(item) {




        }
        function UpdateColPos(cols) {

            var left = $('.jsgrid-grid-body').scrollLeft() < $('.jsgrid-grid-body .jsgrid-table').width() - $('.jsgrid-grid-body').width() + 16
                    ? $('.jsgrid-grid-body').scrollLeft() : $('.jsgrid-grid-body .jsgrid-table').width() - $('.jsgrid-grid-body').width() + 16;
            $('.jsgrid-header-row th:nth-child(-n+' + cols + '), .jsgrid-filter-row td:nth-child(-n+' + cols + '), .jsgrid-insert-row td:nth-child(-n+' + cols + '), .jsgrid-grid-body tr td:nth-child(-n+' + cols + ')')
                    .css({
                        "position": "relative",
                        "left": left
                    });
        }

        $(document).ready(function () {
            localStorage.clear();
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth() + 1; //January is 0!

            var yyyy = today.getFullYear();
            if (dd < 10) {
                dd = '0' + dd;
            }
            if (mm < 10) {
                mm = '0' + mm;
            }
            var today = yyyy + '-' + mm + '-' + dd;
            document.getElementById("vDate").value = today;
        });
        $("#vSearch").click(function (e) {
            const date = document.getElementById("vDate").value;
            const type = document.getElementById("vType").value;
//            const rctype = document.getElementById("vRCType").value;

//            console.log(rctype);


            if (type === "SCB") {
                getGrid_SCB(date, type);
            } else if (type === "KBANK") {
                getGrid_KBANK(date, "KBANK");
            } else if (type === "BBL") {
                getGrid_BBL(date, "BBL");
            } else if (type === "SCB_MMN") {
                document.body.style.zoom = 0.75;
                getGrid_SCB_MMN(date, "SCB_MMN");
            } else if (type === "KBANK_BILL") {
                document.body.style.zoom = 0.75;
                getGrid_KBANK_BILL(date, "KBANK_BILL");
            } else if (type === "BBL_BILL") {
                document.body.style.zoom = 0.75;
                getGrid_BBL_BILL(date, "BBL_BILL");

            } else if (type === "SCB_BILL") {
                document.body.style.zoom = 0.75;
                getGrid_SCB_BILL(date, "SCB_BILL");

            } else {
                window.alert("Data not found !");
            }

        });
        const Type = [
            {Name: "Normal", Id: "TRANSFER"},
            {Name: "Expense", Id: "TRANSFER_EXP"},
            {Name: "DEPOSIT", Id: "TRANSFER_RESERVE"}
//            {Name: "Cash Check Key", Id: "TRANSFER_CHK"}
        ];
        var cono = <%out.print(session.getAttribute("cono"));%>;
        var LocationCode = "";
        if (cono === 10) {
            LocationCode = [
                {Name: "LS", Id: "LS"},
                {Name: "DC_CM", Id: "DC_CM"},
                {Name: "HC_PCB", Id: "HC_PCB"},
                {Name: "ACCOUNT", Id: "ACCOUNT"},
                {Name: "FM", Id: "FM"},
                {Name: "OTHER", Id: "OTHER"},
                {Name: "OTHER_FM", Id: "OTHER_FM"}
            ];
        } else {

            LocationCode = [
                {Name: "WTF", Id: "WTF"},
            ];
        }

        var cono = <%out.print(session.getAttribute("cono"));%>;
        var divi = <%out.print(session.getAttribute("divi"));%>;
        function getGrid_SCB(date, bank, rctype) {
            $("#grid").jsGrid({
                width: "100%",
                height: "700",
                filtering: true,
                inserting: false,
                editing: true,
                sorting: true,
                paging: true,
                autoload: true,
                pageSize: 10000,
                noDataContent: "Not found",
                loadMessage: "Please, wait...",
                onItemUpdated: function (args) {
                    console.log(args.item.BM_CUNO);

                    // todo
                    $.ajax({
                        type: "POST",
                        url: "./Sync",
                        dataType: 'json',
                        async: false,
                        cache: false,
                        data: {
                            page: "updatenewReceipt_SCB",
                            cono: cono,
                            divi: divi,
                            HC_PYNO: args.item.BM_CUNO,
                            HC_PYDT: args.item.BM_DATE


                        },
                    });
                    $("#grid").jsGrid("loadData");

                    UpdateColPos(2);
                },
                onItemEditing: function (args) {
                    console.log("PPPP");

                    setTimeout(function () {
                        UpdateColPos(2);
                    }, 1);
                },
                onRefreshed: function (args) {
                    UpdateColPos(2);
                },
                controller: {
                    loadData: function (filter) {
                        var data = $.Deferred();
                        var cono = <%out.print(session.getAttribute("cono"));%>;
                        var divi = <%out.print(session.getAttribute("divi"));%>;
                        $.ajax({
                            type: 'GET',
                            url: './Sync',
                            dataType: 'json',
                            data: {
                                page: "Grid_SCBold",
                                cono: cono,
                                divi: divi,
                                date: $("#vDate").val(),
                                bank: bank

                            },
                            async: false
                        }).done(function (response) {
                            console.log(response);
                            response = $.grep(response, function (item) {
                                return(!filter.BM_ACCODE || (item.BM_ACCODE.indexOf(filter.BM_ACCODE) > -1))
                                        && (!filter.BM_DESC || (item.BM_DESC.indexOf(filter.BM_DESC) > -1))
                                        && (!filter.BM_DATE || (item.BM_DATE.indexOf(filter.BM_DATE) > -1))
                                        && (!filter.BM_TIME || (item.BM_TIME.indexOf(filter.BM_TIME) > -1))
                                        && (!filter.BM_AMOUNT || (item.BM_AMOUNT.indexOf(filter.BM_AMOUNT) > -1))
                                        && (!filter.BM_CUNO || (item.BM_CUNO.indexOf(filter.BM_CUNO) > -1))
                                        && (!filter.custName || (item.custName.indexOf(filter.custName) > -1))
                                        && (!filter.BM_BKCHARGE || (item.BM_BKCHARGE.indexOf(filter.BM_BKCHARGE) > -1))
                                        && (!filter.BM_OVPAY || (item.BM_OVPAY.indexOf(filter.BM_OVPAY) > -1))
                                        && (!filter.BM_CNDN || (item.BM_CNDN.indexOf(filter.BM_CNDN) > -1))
                                        && (!filter.BM_TYPE || (item.BM_TYPE.indexOf(filter.BM_TYPE) > -1))
                                        && (!filter.BM_LOCA || (item.BM_LOCA.indexOf(filter.BM_LOCA) > -1))
                                        && (!filter.HC_RCNO || (item.HC_RCNO.indexOf(filter.HC_RCNO) > -1))
                                        && (!filter.HC_VCNO || (item.HC_VCNO.indexOf(filter.HC_VCNO) > -1));

                            });
                            data.resolve(response);

                            console.log(response);
                            console.log("response");
                        });
                        return data.promise();
                    },
                    updateItem: function (item) {
                        console.log("***************");
                        console.log(item);
                        console.log("***************");

                        var cono = <%out.print(session.getAttribute("cono"));%>;
                        var divi = <%out.print(session.getAttribute("divi"));%>;
                        var user = "<%out.print(session.getAttribute("username"));%>";

                        item.cono = cono;
                        item.divi = divi;
                        item.bank = "SCB";
                        item.user = user;

                        if (item.BM_CUNO !== "" && (item.BM_CUNO.indexOf("TH") !== 0 && item.BM_CUNO.indexOf("th") !== 0 && item.BM_CUNO.indexOf("Th") !== 0 && item.BM_CUNO.indexOf("tH") !== 0)) {

                            item.BM_CUNO = "TH" + item.BM_CUNO;
                        }




                        //                        if (item.BM_TYPE == "TRANSFER_RESERVE") {
                        //                            if (item.BM_RCNO !== "") {
                        //                                item.page = "updateReceipt_SCB_RES";
                        //                            } else {
                        //                                item.page = "createReceipt_SCB_RES";
                        //                            }
                        //                        } else {

                        if (item.BM_RCNO !== "") {

                            if (item.BM_LOCA !== "ACCOUNT") {
                                item.page = "updateReceipt_SCB";
                            } else {
                                item.page = "updateAccont";
                            }

                        } else {
                            if (item.BM_LOCA !== "ACCOUNT") {

                                item.page = "createReceipt_SCB";
                            } else {
                                item.page = "updateAccont";
                            }

                        }
                        //                        }



                        $.ajax({
                            type: "POST",
                            url: "./Sync",
                            dataType: 'json',
                            async: false,
                            cache: false,
                            data: item
                        });
                        $("#grid").jsGrid("loadData");


                    }
                },
                fields: [
                    {title: "AccNo", name: "BM_ACCODE", type: "text", align: "center", editing: false, width: "85"},
                    {title: "Description", name: "BM_DESC", type: "text", align: "center", width: "150", editing: false},
                    {title: "Date", name: "BM_DATE", type: "text", align: "center", editing: false, width: "80"},
                    {title: "Time", name: "BM_TIME", type: "text", align: "center", editing: false},
                    {title: "Amount", name: "BM_AMOUNT", type: "text", align: "right", editing: false, itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }},
//                    {title: "PAYER", name: "HPYNO", type: "text", align: "center", editing: true},

                    {title: "CustCode", width: "120", name: "BM_CUNO", type: "text", align: "center"},
//                    {title: "PAYER", name: "BM_CUNO", type: "text", align: "center", editing: false},
                    {title: "CustName", width: "150", name: "custName", type: "text", align: "center", editing: false},
                    {title: "BankCharge", name: "BM_BKCHARGE", type: "text", align: "center"},
                    {title: "OverPay", name: "BM_OVPAY", type: "text", align: "center"},
                    {title: "CN/DN", name: "BM_CNDN", type: "text", align: "center"},
                    {title: "Type", name: "BM_TYPE", type: "select", items: Type, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
                    {title: "Location", name: "BM_LOCA", type: "select", items: LocationCode, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
                    {title: "Receipt", name: "HC_RCNO", type: "text", align: "center", editing: false},
                    {title: "Voucher", name: "HC_VCNO", type: "text", align: "center", editing: false},
//                    {title: "ID", name: "BM_FNNO", type: "text", align: "center", editing: false},

                    {type: "control", deleteButton: false}
                ]
            });

        }




        function getGrid_KBANK(date, bank) {
            $("#grid").jsGrid({
                width: "100%",
                height: "700",
                filtering: true,
                inserting: false,
                editing: true,
                sorting: true,
                paging: true,
                autoload: true,
                pageSize: 1000,
                noDataContent: "Not found",
                loadMessage: "Please, wait...",
                onItemUpdated: function (args) {
                    UpdateColPos(2);
                    console.log("click");


                },
                onItemEditing: function (args) {
                    setTimeout(function () {
                        UpdateColPos(2);
                    }, 1);
                },
                onRefreshed: function (args) {
                    UpdateColPos(2);
                },
                controller: {
                    loadData: function (filter) {
                        var data = $.Deferred();
                        var cono = <%out.print(session.getAttribute("cono"));%>;
                        var divi = <%out.print(session.getAttribute("divi"));%>;
                        $.ajax({
                            type: 'GET',
                            url: './Sync',
                            dataType: 'json',
                            data: {
                                page: "Grid_KBANKold",
                                cono: cono,
                                divi: divi,
                                date: $("#vDate").val(),
                                bank: bank
                            },
                            async: false
                        }).done(function (response) {
                            console.log(response);
                            response = $.grep(response, function (item) {
                                return(!filter.BM_ACCODE || (item.BM_ACCODE.indexOf(filter.BM_ACCODE) > -1))
                                        && (!filter.BM_DESC || (item.BM_DESC.indexOf(filter.BM_DESC) > -1))
                                        && (!filter.BM_DATE || (item.BM_DATE.indexOf(filter.BM_DATE) > -1))
                                        && (!filter.BM_TIME || (item.BM_TIME.indexOf(filter.BM_TIME) > -1))
                                        && (!filter.BM_AMOUNT || (item.BM_AMOUNT.indexOf(filter.BM_AMOUNT) > -1))
                                        && (!filter.BM_CUNO || (item.BM_CUNO.indexOf(filter.BM_CUNO) > -1))
                                        && (!filter.custName || (item.custName.indexOf(filter.custName) > -1))
                                        && (!filter.BM_BKCHARGE || (item.BM_BKCHARGE.indexOf(filter.BM_BKCHARGE) > -1))
                                        && (!filter.BM_OVPAY || (item.BM_OVPAY.indexOf(filter.BM_OVPAY) > -1))
                                        && (!filter.BM_CNDN || (item.BM_CNDN.indexOf(filter.BM_CNDN) > -1))
                                        && (!filter.BM_RCNO || (item.BM_RCNO.indexOf(filter.BM_RCNO) > -1))
                                        && (!filter.HC_VCNO || (item.HC_VCNO.indexOf(filter.HC_VCNO) > -1));

                            });
                            data.resolve(response);
                        });
                        return data.promise();
                    },
                    updateItem: function (item) {
                        var cono = <%out.print(session.getAttribute("cono"));%>;
                        var divi = <%out.print(session.getAttribute("divi"));%>;
                        var user = "<%out.print(session.getAttribute("username"));%>";

                        item.cono = cono;
                        item.divi = divi;
                        item.bank = "KBANK";

                        if (item.BM_CUNO !== "" && (item.BM_CUNO.indexOf("TH") !== 0 && item.BM_CUNO.indexOf("th") !== 0 && item.BM_CUNO.indexOf("Th") !== 0 && item.BM_CUNO.indexOf("tH") !== 0)) {

                            item.BM_CUNO = "TH" + item.BM_CUNO;
                        }
//                         if (BM_TYPE == "TRANSFER_RESERVE") {
//                            if (item.BM_RCNO !== "") {
//                                item.page = "updateReceipt_KBANK_RES";
//                            } else {
//                                item.page = "createReceipt_KBANK_RES";
//                            }
//                        }
//                        else{
                        if (item.BM_RCNO !== "") {

                            if (item.BM_LOCA !== "ACCOUNT") {
                                item.page = "updateReceipt_KBANK";
                            } else {
                                item.page = "updateAccont";
                            }

                        } else {

                            if (item.BM_LOCA !== "ACCOUNT") {
                                item.page = "createReceipt_KBANK";
                            } else {
                                item.page = "updateAccont";
                            }
                        }
//                    }

                        $.ajax({
                            type: "POST",
                            url: "./Sync",
                            dataType: 'json',
                            async: false,
                            cache: false,
                            data: item
                        });
                        $("#grid").jsGrid("loadData");
                    }
                },
                fields: [
                    {title: "AccNo", name: "BM_ACCODE", type: "text", align: "center", editing: false, width: "85"},
                    {title: "Desc", name: "BM_DESC", type: "text", align: "center", editing: false},
                    {title: "Date", name: "BM_DATE", type: "text", align: "center", editing: false},
                    {title: "Time", name: "BM_TIME", type: "text", align: "center", editing: false},
                    {title: "Amount", name: "BM_AMOUNT", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }},
//                    {title: "PAYER", name: "HPYNO", type: "text", align: "center", editing: false},
                    {title: "CustCode", width: "120", name: "BM_CUNO", type: "text", align: "center"},
                    {title: "CustName", width: "150", name: "custName", type: "text", align: "center", editing: false},
                    {title: "BankCharge", name: "BM_BKCHARGE", type: "text", align: "center"},
                    {title: "OverPay", name: "BM_OVPAY", type: "text", align: "center"},
                    {title: "CN/DN", name: "BM_CNDN", type: "text", align: "center"},
                    {title: "Type", name: "BM_TYPE", type: "select", items: Type, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
                    {title: "Location", name: "BM_LOCA", type: "select", items: LocationCode, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
                    {title: "Receipt", name: "BM_RCNO", type: "text", align: "center", editing: false},
                    {title: "Voucher", name: "HC_VCNO", type: "text", align: "center", editing: false},
//                    {title: "ID", name: "BM_FNNO", type: "text", align: "center", editing: false},

                    {type: "control", deleteButton: false}
                ]
            });

        }
        function getGrid_BBL(date, bank) {
            $("#grid").jsGrid({
                width: "100%",
                height: "700",
                filtering: true,
                inserting: false,
                editing: true,
                sorting: true,
                paging: true,
                autoload: true,
                pageSize: 1000,
                noDataContent: "Not found",
                loadMessage: "Please, wait...",
                onItemUpdated: function (args) {
                    UpdateColPos(2);
                    console.log("click");


                },
                onItemEditing: function (args) {
                    setTimeout(function () {
                        UpdateColPos(2);
                    }, 1);
                },
                onRefreshed: function (args) {
                    UpdateColPos(2);
                },
                controller: {
                    loadData: function (filter) {
                        var data = $.Deferred();
                        var cono = <%out.print(session.getAttribute("cono"));%>;
                        var divi = <%out.print(session.getAttribute("divi"));%>;
                        $.ajax({
                            type: 'GET',
                            url: './Sync',
                            dataType: 'json',
                            data: {
                                page: "Grid_BBLold",
                                cono: cono,
                                divi: divi,
                                date: $("#vDate").val(),
                                bank: bank
                            },
                            async: false
                        }).done(function (response) {
                            console.log(response);
                            response = $.grep(response, function (item) {
                                return(!filter.BM_ACCODE || (item.BM_ACCODE.indexOf(filter.BM_ACCODE) > -1))
                                        && (!filter.BM_DESC || (item.BM_DESC.indexOf(filter.BM_DESC) > -1))
                                        && (!filter.BM_DATE || (item.BM_DATE.indexOf(filter.BM_DATE) > -1))
                                        && (!filter.BM_TIME || (item.BM_TIME.indexOf(filter.BM_TIME) > -1))
                                        && (!filter.BM_AMOUNT || (item.BM_AMOUNT.indexOf(filter.BM_AMOUNT) > -1))
                                        && (!filter.BM_CUNO || (item.BM_CUNO.indexOf(filter.BM_CUNO) > -1))
                                        && (!filter.custName || (item.custName.indexOf(filter.custName) > -1))
                                        && (!filter.BM_BKCHARGE || (item.BM_BKCHARGE.indexOf(filter.BM_BKCHARGE) > -1))
                                        && (!filter.BM_OVPAY || (item.BM_OVPAY.indexOf(filter.BM_OVPAY) > -1))
                                        && (!filter.BM_CNDN || (item.BM_CNDN.indexOf(filter.BM_CNDN) > -1))
                                        && (!filter.BM_RCNO || (item.BM_RCNO.indexOf(filter.BM_RCNO) > -1))
                                        && (!filter.HC_VCNO || (item.HC_VCNO.indexOf(filter.HC_VCNO) > -1));

                            });
                            data.resolve(response);
                        });
                        return data.promise();
                    },
                    updateItem: function (item) {
                        var cono = <%out.print(session.getAttribute("cono"));%>;
                        var divi = <%out.print(session.getAttribute("divi"));%>;
                        var user = "<%out.print(session.getAttribute("username"));%>";

                        item.cono = cono;
                        item.divi = divi;
                        item.bank = "BBL";

                        if (item.BM_CUNO !== "" && (item.BM_CUNO.indexOf("TH") !== 0 && item.BM_CUNO.indexOf("th") !== 0 && item.BM_CUNO.indexOf("Th") !== 0 && item.BM_CUNO.indexOf("tH") !== 0)) {

                            item.BM_CUNO = "TH" + item.BM_CUNO;
                        }

//                         if (BM_TYPE == "TRANSFER_RESERVE") {
//                            if (item.BM_RCNO !== "") {
//                                item.page = "updateReceipt_KBANK_RES";
//                            } else {
//                                item.page = "createReceipt_KBANK_RES";
//                            }
//                        }
//                        else{
                        if (item.BM_RCNO !== "") {
                            if (item.BM_LOCA !== "ACCOUNT") {
                                item.page = "updateReceipt_BBL";
                            } else {
                                item.page = "updateAccont";
                            }

                        } else {
                            if (item.BM_LOCA !== "ACCOUNT") {
                                item.page = "createReceipt_BBL";
                            } else {
                                item.page = "updateAccont";
                            }
                        }
//                    }

                        $.ajax({
                            type: "POST",
                            url: "./Sync",
                            dataType: 'json',
                            async: false,
                            cache: false,
                            data: item
                        });
                        $("#grid").jsGrid("loadData");
                    }
                },
                fields: [
                    {title: "AccNo", name: "BM_ACCODE", type: "text", align: "center", editing: false, width: "85"},
                    {title: "Desc", name: "BM_DESC", type: "text", align: "center", editing: false},
                    {title: "Date", name: "BM_DATE", type: "text", align: "center", editing: false},
                    {title: "Time", name: "BM_TIME", type: "text", align: "center", editing: false},
                    {title: "Amount", name: "BM_AMOUNT", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }},
//                    {title: "PAYER", name: "HPYNO", type: "text", align: "center", editing: false},
                    {title: "CustCode", width: "120", name: "BM_CUNO", type: "text", align: "center"},
                    {title: "CustName", width: "150", name: "custName", type: "text", align: "center", editing: false},
                    {title: "BankCharge", name: "BM_BKCHARGE", type: "text", align: "center"},
                    {title: "OverPay", name: "BM_OVPAY", type: "text", align: "center"},
                    {title: "CN/DN", name: "BM_CNDN", type: "text", align: "center"},
                    {title: "Type", name: "BM_TYPE", type: "select", items: Type, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
                    {title: "Location", name: "BM_LOCA", type: "select", items: LocationCode, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
                    {title: "Receipt", name: "BM_RCNO", type: "text", align: "center", editing: false},
                    {title: "Voucher", name: "HC_VCNO", type: "text", align: "center", editing: false},
//                    {title: "ID", name: "BM_FNNO", type: "text", align: "center", editing: false},

                    {type: "control", deleteButton: false}
                ]
            });

        }



        /////////////////////  bill ////////////////////////////



        function getGrid_SCB_MMN(date, bank) {
            $("#grid").jsGrid({
                width: "100%",
                height: "700",
                filtering: true,
                inserting: false,
                editing: true,
                sorting: true,
                paging: true,
                autoload: true,
                pageSize: 1000,
                noDataContent: "Not found",
                loadMessage: "Please, wait...",
                onItemUpdated: function (args) {
                    UpdateColPos(2);
                    console.log("click");


                },
                onItemEditing: function (args) {
                    setTimeout(function () {
                        UpdateColPos(2);
                    }, 1);
                },
                onRefreshed: function (args) {
                    UpdateColPos(2);
                },
                controller: {
                    loadData: function (filter) {
                        var data = $.Deferred();
                        var cono = <%out.print(session.getAttribute("cono"));%>;
                        var divi = <%out.print(session.getAttribute("divi"));%>;
                        $.ajax({
                            type: 'GET',
                            url: './Sync',
                            dataType: 'json',
                            data: {
                                page: "Grid_SCB_MMNold",
                                cono: cono,
                                divi: divi,
                                date: $("#vDate").val(),
                                bank: bank
                            },
                            async: false
                        }).done(function (response) {
                            console.log(response);
                            response = $.grep(response, function (item) {
                                return(!filter.BM_ACCODE || (item.BM_ACCODE.indexOf(filter.BM_ACCODE) > -1))
                                        && (!filter.BM_DESC || (item.BM_DESC.indexOf(filter.BM_DESC) > -1))
                                        && (!filter.BM_DATE || (item.BM_DATE.indexOf(filter.BM_DATE) > -1))
                                        && (!filter.BM_TIME || (item.BM_TIME.indexOf(filter.BM_TIME) > -1))
                                        && (!filter.BM_AMOUNT || (item.BM_AMOUNT.indexOf(filter.BM_AMOUNT) > -1))
                                        && (!filter.BM_CUNO || (item.BM_CUNO.indexOf(filter.BM_CUNO) > -1))
                                        && (!filter.custName || (item.custName.indexOf(filter.custName) > -1))
                                        && (!filter.BM_BKCHARGE || (item.BM_BKCHARGE.indexOf(filter.BM_BKCHARGE) > -1))
                                        && (!filter.BM_OVPAY || (item.BM_OVPAY.indexOf(filter.BM_OVPAY) > -1))
                                        && (!filter.BM_CNDN || (item.BM_CNDN.indexOf(filter.BM_CNDN) > -1))
                                        && (!filter.BM_RCNO || (item.BM_RCNO.indexOf(filter.BM_RCNO) > -1))
                                        && (!filter.HC_VCNO || (item.HC_VCNO.indexOf(filter.HC_VCNO) > -1));

                            });
                            data.resolve(response);
                        });
                        return data.promise();
                    },
                    updateItem: function (item) {
                        var cono = <%out.print(session.getAttribute("cono"));%>;
                        var divi = <%out.print(session.getAttribute("divi"));%>;
                        var user = "<%out.print(session.getAttribute("username"));%>";

                        item.cono = cono;
                        item.divi = divi;
                        item.bank = "SCB_MMN";

                        if (item.BM_CUNO !== "" && (item.BM_CUNO.indexOf("TH") !== 0 && item.BM_CUNO.indexOf("th") !== 0 && item.BM_CUNO.indexOf("Th") !== 0 && item.BM_CUNO.indexOf("tH") !== 0)) {

                            item.BM_CUNO = "TH" + item.BM_CUNO;
                        }

//                         if (BM_TYPE == "TRANSFER_RESERVE") {
//                            if (item.BM_RCNO !== "") {
//                                item.page = "updateReceipt_KBANK_RES";
//                            } else {
//                                item.page = "createReceipt_KBANK_RES";
//                            }
//                        }
//                        else{
                        if (item.BM_RCNO !== "") {
                            if (item.BM_LOCA !== "ACCOUNT") {
                                item.page = "updateReceipt_SCB_RBILL";
                            } else {
                                item.page = "updateAccont";
                            }

                        } else {
                            if (item.BM_LOCA !== "ACCOUNT") {
                                item.page = "createReceipt_SCB_RBILL";
                            } else {
                                item.page = "updateAccont";
                            }
                        }
//                    }

                        $.ajax({
                            type: "POST",
                            url: "./Sync",
                            dataType: 'json',
                            async: false,
                            cache: false,
                            data: item
                        });
                        $("#grid").jsGrid("loadData");
                    }
                },
                fields: [
                    {title: "AccNo", name: "BM_ACCODE", type: "text", align: "center", editing: false, width: "85"},
                    {title: "Desc", name: "BM_DESC", type: "text", align: "center", editing: false},
                    {title: "REFCU.", name: "BM_REFCU1", type: "text", align: "center", editing: false},
                    {title: "Date", name: "BM_DATE", type: "text", align: "center", editing: false},
                    {title: "Time", name: "BM_TIME", type: "text", align: "center", editing: false},
                    {title: "Amount", name: "BM_AMOUNT", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }},
                    {title: "PAYER", name: "HPYNO", type: "text", align: "center", editing: false},
                    {title: "CustCode", width: "120", name: "BM_CUNO", type: "text", align: "center"},
                    {title: "CustName", width: "150", name: "custName", type: "text", align: "center", editing: false},
                    {title: "BankCharge", name: "BM_BKCHARGE", type: "text", align: "center"},
                    {title: "OverPay", name: "BM_OVPAY", type: "text", align: "center"},
                    {title: "CN/DN", name: "BM_CNDN", type: "text", align: "center"},
                    {title: "Type", name: "BM_TYPE", type: "select", items: Type, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
                    {title: "Location", name: "BM_LOCA", type: "select", items: LocationCode, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
                    {title: "Receipt", name: "BM_RCNO", type: "text", align: "center", editing: false},
                    {title: "Voucher", name: "HC_VCNO", type: "text", align: "center", editing: false},
//                    {title: "ID", name: "BM_FNNO", type: "text", align: "center", editing: false},

                    {type: "control", deleteButton: false}
                ]
            });

        }


        function getGrid_SCB_BILL(date, bank) {
            $("#grid").jsGrid({
                width: "100%",
                height: "700",
                filtering: true,
                inserting: false,
                editing: true,
                sorting: true,
                paging: true,
                autoload: true,
                pageSize: 1000,
                noDataContent: "Not found",
                loadMessage: "Please, wait...",
                onItemUpdated: function (args) {
                    UpdateColPos(2);
                    console.log("click");


                },
                onItemEditing: function (args) {
                    setTimeout(function () {
                        UpdateColPos(2);
                    }, 1);
                },
                onRefreshed: function (args) {
                    UpdateColPos(2);
                },
                controller: {
                    loadData: function (filter) {
                        var data = $.Deferred();
                        var cono = <%out.print(session.getAttribute("cono"));%>;
                        var divi = <%out.print(session.getAttribute("divi"));%>;
                        $.ajax({
                            type: 'GET',
                            url: './Sync',
                            dataType: 'json',
                            data: {
                                page: "Grid_SCB_BILLold",
                                cono: cono,
                                divi: divi,
                                date: $("#vDate").val(),
                                bank: bank
                            },
                            async: false
                        }).done(function (response) {
                            console.log(response);
                            response = $.grep(response, function (item) {
                                return(!filter.BM_ACCODE || (item.BM_ACCODE.indexOf(filter.BM_ACCODE) > -1))
                                        && (!filter.BM_DESC || (item.BM_DESC.indexOf(filter.BM_DESC) > -1))
                                        && (!filter.BM_DATE || (item.BM_DATE.indexOf(filter.BM_DATE) > -1))
                                        && (!filter.BM_TIME || (item.BM_TIME.indexOf(filter.BM_TIME) > -1))
                                        && (!filter.BM_AMOUNT || (item.BM_AMOUNT.indexOf(filter.BM_AMOUNT) > -1))
                                        && (!filter.BM_CUNO || (item.BM_CUNO.indexOf(filter.BM_CUNO) > -1))
                                        && (!filter.custName || (item.custName.indexOf(filter.custName) > -1))
                                        && (!filter.BM_BKCHARGE || (item.BM_BKCHARGE.indexOf(filter.BM_BKCHARGE) > -1))
                                        && (!filter.BM_OVPAY || (item.BM_OVPAY.indexOf(filter.BM_OVPAY) > -1))
                                        && (!filter.BM_CNDN || (item.BM_CNDN.indexOf(filter.BM_CNDN) > -1))
                                        && (!filter.BM_RCNO || (item.BM_RCNO.indexOf(filter.BM_RCNO) > -1))
                                        && (!filter.HC_VCNO || (item.HC_VCNO.indexOf(filter.HC_VCNO) > -1));

                            });
                            data.resolve(response);
                        });
                        return data.promise();
                    },
                    updateItem: function (item) {
                        var cono = <%out.print(session.getAttribute("cono"));%>;
                        var divi = <%out.print(session.getAttribute("divi"));%>;
                        var user = "<%out.print(session.getAttribute("username"));%>";

                        item.cono = cono;
                        item.divi = divi;
                        item.bank = "SCB_BILL";

                        if (item.BM_CUNO !== "" && (item.BM_CUNO.indexOf("TH") !== 0 && item.BM_CUNO.indexOf("th") !== 0 && item.BM_CUNO.indexOf("Th") !== 0 && item.BM_CUNO.indexOf("tH") !== 0)) {

                            item.BM_CUNO = "TH" + item.BM_CUNO;
                        }

//                         if (BM_TYPE == "TRANSFER_RESERVE") {
//                            if (item.BM_RCNO !== "") {
//                                item.page = "updateReceipt_KBANK_RES";
//                            } else {
//                                item.page = "createReceipt_KBANK_RES";
//                            }
//                        }
//                        else{


                        if (item.BM_RCNO !== "") {
                            if (item.BM_LOCA !== "ACCOUNT") {
                                item.page = "updateReceipt_SCB_BILL";
                            } else {
                                item.page = "updateAccont";
                            }
                        } else {
                            if (item.BM_LOCA !== "ACCOUNT") {
                                item.page = "createReceipt_SCB_BILL";
                            } else {
                                item.page = "updateAccont";
                            }
                        }
//                    }

                        $.ajax({
                            type: "POST",
                            url: "./Sync",
                            dataType: 'json',
                            async: false,
                            cache: false,
                            data: item
                        });
                        $("#grid").jsGrid("loadData");
                    }
                },
                fields: [
                    {title: "AccNo", name: "BM_ACCODE", type: "text", align: "center", editing: false, width: "85"},
                    {title: "Desc", name: "BM_CUNA", type: "text", align: "center", editing: false},
                    {title: "Desc", name: "BM_DESC", type: "text", align: "center", editing: false},
                    {title: "REFCU.", name: "BM_REFCU1", type: "text", align: "center", editing: false},
                    {title: "Date", name: "BM_DATE", type: "text", align: "center", editing: false},
                    {title: "Time", name: "BM_TIME", type: "text", align: "center", editing: false},
                    {title: "Amount", name: "BM_AMOUNT", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }},
//                    {title: "PAYER", name: "HPYNO", type: "text", align: "center", editing: false},
                    {title: "CustCode", width: "120", name: "BM_CUNO", type: "text", align: "center"},
                    {title: "CustName", width: "150", name: "custName", type: "text", align: "center", editing: false},
//                    {title: "BankCharge", name: "BM_BKCHARGE", type: "number", align: "center"},
                    {title: "OverPay", name: "BM_OVPAY", type: "text", align: "center"},
//                    {title: "CN/DN", name: "BM_CNDN", type: "text", align: "center"},
                    {title: "Type", name: "BM_TYPE", type: "select", items: Type, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
                    {title: "Location", name: "BM_LOCA", type: "select", items: LocationCode, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
                    {title: "Receipt", name: "BM_RCNO", type: "text", align: "center", editing: false},
                    {title: "Voucher", name: "HC_VCNO", type: "text", align: "center", editing: false},
//                    {title: "ID", name: "BM_FNNO", type: "text", align: "center", editing: false},

                    {type: "control", deleteButton: false}
                ]
            });

        }

        function getGrid_KBANK_BILL(date, bank) {
            $("#grid").jsGrid({
                width: "100%",
                height: "700",
                filtering: true,
                inserting: false,
                editing: true,
                sorting: true,
                paging: true,
                autoload: true,
                pageSize: 1000,
                noDataContent: "Not found",
                loadMessage: "Please, wait...",
                onItemUpdated: function (args) {
                    UpdateColPos(2);
                    console.log("click");


                },
                onItemEditing: function (args) {
                    setTimeout(function () {
                        UpdateColPos(2);
                    }, 1);
                },
                onRefreshed: function (args) {
                    UpdateColPos(2);
                },
                controller: {
                    loadData: function (filter) {
                        var data = $.Deferred();
                        var cono = <%out.print(session.getAttribute("cono"));%>;
                        var divi = <%out.print(session.getAttribute("divi"));%>;
                        $.ajax({
                            type: 'GET',
                            url: './Sync',
                            dataType: 'json',
                            data: {
                                page: "Grid_KBANK_BILLold",
                                cono: cono,
                                divi: divi,
                                date: $("#vDate").val(),
                                bank: bank
                            },
                            async: false
                        }).done(function (response) {
                            console.log(response);
                            response = $.grep(response, function (item) {
                                return(!filter.BM_ACCODE || (item.BM_ACCODE.indexOf(filter.BM_ACCODE) > -1))
                                        && (!filter.BM_DESC || (item.BM_DESC.indexOf(filter.BM_DESC) > -1))
                                        && (!filter.BM_DATE || (item.BM_DATE.indexOf(filter.BM_DATE) > -1))
                                        && (!filter.BM_TIME || (item.BM_TIME.indexOf(filter.BM_TIME) > -1))
                                        && (!filter.BM_AMOUNT || (item.BM_AMOUNT.indexOf(filter.BM_AMOUNT) > -1))
                                        && (!filter.BM_CUNO || (item.BM_CUNO.indexOf(filter.BM_CUNO) > -1))
                                        && (!filter.custName || (item.custName.indexOf(filter.custName) > -1))
                                        && (!filter.BM_BKCHARGE || (item.BM_BKCHARGE.indexOf(filter.BM_BKCHARGE) > -1))
                                        && (!filter.BM_OVPAY || (item.BM_OVPAY.indexOf(filter.BM_OVPAY) > -1))
                                        && (!filter.BM_CNDN || (item.BM_CNDN.indexOf(filter.BM_CNDN) > -1))
                                        && (!filter.BM_RCNO || (item.BM_RCNO.indexOf(filter.BM_RCNO) > -1))
                                        && (!filter.HC_VCNO || (item.HC_VCNO.indexOf(filter.HC_VCNO) > -1));

                            });
                            data.resolve(response);
                        });
                        return data.promise();
                    },
                    updateItem: function (item) {
                        var cono = <%out.print(session.getAttribute("cono"));%>;
                        var divi = <%out.print(session.getAttribute("divi"));%>;
                        var user = "<%out.print(session.getAttribute("username"));%>";

                        item.cono = cono;
                        item.divi = divi;
                        item.bank = "KBANK_BILL";

                        if (item.BM_CUNO !== "" && (item.BM_CUNO.indexOf("TH") !== 0 && item.BM_CUNO.indexOf("th") !== 0 && item.BM_CUNO.indexOf("Th") !== 0 && item.BM_CUNO.indexOf("tH") !== 0)) {

                            item.BM_CUNO = "TH" + item.BM_CUNO;
                        }

//                         if (BM_TYPE == "TRANSFER_RESERVE") {
//                            if (item.BM_RCNO !== "") {
//                                item.page = "updateReceipt_KBANK_RES";
//                            } else {
//                                item.page = "createReceipt_KBANK_RES";
//                            }
//                        }
//                        else{
                        if (item.BM_RCNO !== "") {
                            if (item.BM_LOCA !== "ACCOUNT") {
                                item.page = "updateReceipt_KBANK_BILL";
                            } else {
                                item.page = "updateAccont";
                            }
                        } else {
                            if (item.BM_LOCA !== "ACCOUNT") {
                                item.page = "createReceipt_KBANK_BILL";
                            } else {
                                item.page = "updateAccont";
                            }
                        }
//                    }

                        $.ajax({
                            type: "POST",
                            url: "./Sync",
                            dataType: 'json',
                            async: false,
                            cache: false,
                            data: item
                        });
                        $("#grid").jsGrid("loadData");
                    }
                },
                fields: [
                    {title: "AccNo", name: "BM_ACCODE", type: "text", align: "center", editing: false, width: "85"},
                    {title: "Desc", name: "BM_DESC", type: "text", align: "center", editing: false},
                    {title: "REFCU.", name: "BM_REFCU1", type: "text", align: "center", editing: false},
                    {title: "Date", name: "BM_DATE", type: "text", align: "center", editing: false},
                    {title: "Time", name: "BM_TIME", type: "text", align: "center", editing: false},
                    {title: "Amount", name: "BM_AMOUNT", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }},
//                    {title: "PAYER", name: "HPYNO", type: "text", align: "center", editing: false},
                    {title: "CustCode", width: "120", name: "BM_CUNO", type: "text", align: "center"},
                    {title: "CustName", width: "150", name: "custName", type: "text", align: "center", editing: false},
                    {title: "BankCharge", name: "BM_BKCHARGE", type: "text", align: "center"},
                    {title: "OverPay", name: "BM_OVPAY", type: "text", align: "center"},
                    {title: "CN/DN", name: "BM_CNDN", type: "text", align: "center"},
                    {title: "Type", name: "BM_TYPE", type: "select", items: Type, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
                    {title: "Location", name: "BM_LOCA", type: "select", items: LocationCode, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
                    {title: "Receipt", name: "BM_RCNO", type: "text", align: "center", editing: false},
                    {title: "Voucher", name: "HC_VCNO", type: "text", align: "center", editing: false},
//                    {title: "ID", name: "BM_FNNO", type: "text", align: "center", editing: false},

                    {type: "control", deleteButton: false}
                ]
            });

        }

        function getGrid_BBL_BILL(date, bank) {
            $("#grid").jsGrid({
                width: "100%",
                height: "700",
                filtering: true,
                inserting: false,
                editing: true,
                sorting: true,
                paging: true,
                autoload: true,
                pageSize: 1000,
                noDataContent: "Not found",
                loadMessage: "Please, wait...",
                onItemUpdated: function (args) {
                    UpdateColPos(2);
                    console.log("click");


                },
                onItemEditing: function (args) {
                    setTimeout(function () {
                        UpdateColPos(2);
                    }, 1);
                },
                onRefreshed: function (args) {
                    UpdateColPos(2);
                },
                controller: {
                    loadData: function (filter) {
                        var data = $.Deferred();
                        var cono = <%out.print(session.getAttribute("cono"));%>;
                        var divi = <%out.print(session.getAttribute("divi"));%>;
                        $.ajax({
                            type: 'GET',
                            url: './Sync',
                            dataType: 'json',
                            data: {
                                page: "Grid_BBL_BILLold",
                                cono: cono,
                                divi: divi,
                                date: $("#vDate").val(),
                                bank: bank
                            },
                            async: false
                        }).done(function (response) {
                            console.log(response);
                            response = $.grep(response, function (item) {
                                return(!filter.BM_ACCODE || (item.BM_ACCODE.indexOf(filter.BM_ACCODE) > -1))
                                        && (!filter.BM_DESC || (item.BM_DESC.indexOf(filter.BM_DESC) > -1))
                                        && (!filter.BM_DATE || (item.BM_DATE.indexOf(filter.BM_DATE) > -1))
                                        && (!filter.BM_TIME || (item.BM_TIME.indexOf(filter.BM_TIME) > -1))
                                        && (!filter.BM_AMOUNT || (item.BM_AMOUNT.indexOf(filter.BM_AMOUNT) > -1))
                                        && (!filter.BM_CUNO || (item.BM_CUNO.indexOf(filter.BM_CUNO) > -1))
                                        && (!filter.custName || (item.custName.indexOf(filter.custName) > -1))
                                        && (!filter.BM_BKCHARGE || (item.BM_BKCHARGE.indexOf(filter.BM_BKCHARGE) > -1))
                                        && (!filter.BM_OVPAY || (item.BM_OVPAY.indexOf(filter.BM_OVPAY) > -1))
                                        && (!filter.BM_CNDN || (item.BM_CNDN.indexOf(filter.BM_CNDN) > -1))
                                        && (!filter.BM_RCNO || (item.BM_RCNO.indexOf(filter.BM_RCNO) > -1))
                                        && (!filter.HC_VCNO || (item.HC_VCNO.indexOf(filter.HC_VCNO) > -1));

                            });
                            data.resolve(response);
                        });
                        return data.promise();
                    },
                    updateItem: function (item) {
                        var cono = <%out.print(session.getAttribute("cono"));%>;
                        var divi = <%out.print(session.getAttribute("divi"));%>;
                        var user = "<%out.print(session.getAttribute("username"));%>";

                        item.cono = cono;
                        item.divi = divi;
                        item.bank = "BBL_BILL";

                        if (item.BM_CUNO !== "" && (item.BM_CUNO.indexOf("TH") !== 0 && item.BM_CUNO.indexOf("th") !== 0 && item.BM_CUNO.indexOf("Th") !== 0 && item.BM_CUNO.indexOf("tH") !== 0)) {

                            item.BM_CUNO = "TH" + item.BM_CUNO;
                        }

//                         if (BM_TYPE == "TRANSFER_RESERVE") {
//                            if (item.BM_RCNO !== "") {
//                                item.page = "updateReceipt_KBANK_RES";
//                            } else {
//                                item.page = "createReceipt_KBANK_RES";
//                            }
//                        }
//                        else{
                        if (item.BM_RCNO !== "") {
                            if (item.BM_LOCA !== "ACCOUNT") {
                                item.page = "updateReceipt_BBL_BILL";
                            } else {
                                item.page = "updateAccont";
                            }

                        } else {
                            if (item.BM_LOCA !== "ACCOUNT") {
                                item.page = "createReceipt_BBL_BILL";
                            } else {
                                item.page = "updateAccont";
                            }
                        }
//                    }

                        $.ajax({
                            type: "POST",
                            url: "./Sync",
                            dataType: 'json',
                            async: false,
                            cache: false,
                            data: item
                        });
                        $("#grid").jsGrid("loadData");
                    }
                },
                fields: [
                    {title: "AccNo", name: "BM_ACCODE", type: "text", align: "center", editing: false, width: "85"},
                    {title: "Desc", name: "BM_DESC", type: "text", align: "center", editing: false},
                    {title: "REFCU.", name: "BM_REFCU1", type: "text", align: "center", editing: false},
                    {title: "Date", name: "BM_DATE", type: "text", align: "center", editing: false},
                    {title: "Time", name: "BM_TIME", type: "text", align: "center", editing: false},
                    {title: "Amount", name: "BM_AMOUNT", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }},
//                    {title: "PAYER", name: "HPYNO", type: "text", align: "center", editing: false},
                    {title: "CustCode", width: "120", name: "BM_CUNO", type: "text", align: "center"},
                    {title: "CustName", width: "150", name: "custName", type: "text", align: "center", editing: false},
                    {title: "BankCharge", name: "BM_BKCHARGE", type: "text", align: "center"},
                    {title: "OverPay", name: "BM_OVPAY", type: "text", align: "center"},
                    {title: "CN/DN", name: "BM_CNDN", type: "text", align: "center"},
                    {title: "Type", name: "BM_TYPE", type: "select", items: Type, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
                    {title: "Location", name: "BM_LOCA", type: "select", items: LocationCode, valueField: "Id", textField: "Name", align: "left", filtering: false, editing: true},
                    {title: "Receipt", name: "BM_RCNO", type: "text", align: "center", editing: false},
                    {title: "Voucher", name: "HC_VCNO", type: "text", align: "center", editing: false},
//                    {title: "ID", name: "BM_FNNO", type: "text", align: "center", editing: false},

                    {type: "control", deleteButton: false}
                ]
            });

        }





    </script>


</html>

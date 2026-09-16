<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Excel</title>
        <!--        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.14.3/xlsx.full.min.js"></script>
          <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.12.12/xlsx.min.map"></script>-->

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/jszip.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/xlsx.js"></script>
        <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.css" />
        <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid-theme.min.css" />
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.js"></script>
        <style>
            .jsgrid-cell {
                overflow: hidden;
            }
        </style>
    </head>
    <body >
        <div class="container-fluid px-0 py-0 mx-auto">
            <div class="row">
                <div class="panel panel-default " id="reportfield" >
                    <div class="panel-heading " style="background-color: #000">
                        <h3 class="panel-title"> <font color="#FFFFFF" >Bank Mapping : Upload_Data</font></h3>
                    </div>
                    <div class="container-fluid panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
                        <div class="row">
                            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                               
                            </div>
                            <div class="col-xs-5 col-sm-4 col-md-4 col-lg-3">
                                <label for="cars">File:</label>
                                <form enctype="multipart/form-data">
                                    <input id="upload" class="form-control-user" type=file name="files[]" accept=".csv,application/vnd.ms-excel,.xlt,application/vnd.ms-excel,.xla,application/vnd.ms-excel,.xlsx,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,.xltx,application/vnd.openxmlformats-officedocument.spreadsheetml.template,.xlsm,application/vnd.ms-excel.sheet.macroEnabled.12,.xltm,application/vnd.ms-excel.template.macroEnabled.12,.xlam,application/vnd.ms-excel.addin.macroEnabled.12,.xlsb,application/vnd.ms-excel.sheet.binary.macroEnabled.12">
                                </form>   
                            </div>
                            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                <label for="cars"></label>
                                <button id="vUpload" class="form-control btn-block btn-danger">DISABLE</button>
                            </div>                           
                        </div>

                        <hr>
                        <div class="row">
                            <div id="jsGrid"></div>
                        </div>
                    </div>
                </div>
            </div> 
        </div>
    </body>
    <script>
        $(document).ready(function () {
            localStorage.clear();


        });

        function formatDateBBLINT(datetime1) {


            var datetime2 = datetime1.split(" ");
            var date = datetime2[0];
            var time = datetime2[1];



            return formatDateBBL(date)
        }

        function formatDateBBLINT(datetime1) {


            var datetime2 = datetime1.split(" ");
            var date = datetime2[0];
            var time = datetime2[1];



            return formatDateBBL(date)
        }

        function formatTimeBBLINT(datetime1) {


            var datetime2 = datetime1.split(" ");
            var date = datetime2[0];
            var time = datetime2[1];



            return formatTime1(time)
        }



        function formatDateBBLTHAI(datetime1) {
            
            var datetime2 = datetime1.split("/");

            var itime = parseInt(datetime2[2]) - 543;

            time = itime + datetime2[1] + datetime2[0];


            return time;

        }


        function formatDateBBL(date) {
            var date = date.split("/");
            var day = date[0];
            var months = date[1];
            var years = date[2];


            return formattedDate = years + months + day;
        }

        function formatDateMMN(date) {
            var date = date.split("/");
            var day = date[0];
            var months = date[1];
            var years = date[2];

            var fullm = (months < 10) ? '0' + months.toString() : months.toString();
            var fulld = (day < 10) ? '0' + day.toString() : day.toString();


            return formattedDate = "20" + years + fulld + fullm;
        }


        function formatDateSCBBILLHIS(date) {
            var date = date.split("/");
            var day = date[0];
            var months = date[1];
            var years = date[2];

            //var fullm = (months < 10) ? '0' + months.toString() : months.toString();
            //var fulld = (day < 10) ? '0' + day.toString() : day.toString();


            return formattedDate = years + months + day;
        }



        function formatDate1(date) {
            var date = date.split("-");
            var months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
            for (var j = 0; j < months.length; j++) {
                if (date[1] == months[j]) {
                    date[1] = months.indexOf(months[j]) + 1;
                }
            }
            if (date[1] < 10) {
                date[1] = '0' + date[1];
            }
            console.log(date[0]);

            return formattedDate = date[2] + date[1] + date[0];
        }


        function formatTime1(time) {
            var time = time.split(":");
            var hours = time[0];
            var inthours = parseInt(hours) % 12 || 12;
            ;
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

//            return hours + ":"+ minutes+ ":"+ second+ ":"+ type;
        }


        function formatTime1SCB(time) {
            var time = time.split(":");
            var hours = time[0];
            var inthours = parseInt(hours) % 12 || 12;
            ;
            var minutes = time[1];
            var second = "00";

            var type = "ZZ";
            if (parseInt(hours) >= 12)
            {
                type = "PM";
            } else
            {
                type = "AM";
            }

            return inthours.toString() + ":" + minutes + ":" + second + " " + type;

//            return hours + ":"+ minutes+ ":"+ second+ ":"+ type;
        }

        function renameKey(obj, oldKey, newKey) {
            obj[newKey] = obj[oldKey];
            delete obj[oldKey];
        }

        document.getElementById('upload').addEventListener('change', handleFileSelect, false);
        var json_object;
  
  var ExcelToJSON = function () {

    this.parseExcel = function (file) {

        var reader = new FileReader();

        reader.onload = function (e) {

            var data = e.target.result;

            var workbook = XLSX.read(data, {
                type: 'binary'
            });

          var sheetName = workbook.SheetNames[0];
var sheet = workbook.Sheets[sheetName];
                // =====================================================
                // Excel ไม่มี Header
                //
                // Column A = Item Code
                // Column B = PPRIOD
                // =====================================================

                var rows = XLSX.utils.sheet_to_json(sheet, {
                    header: 1,
                    defval: ""
                });

                console.log("Excel Rows:");
                console.log(rows);

                var arr = [];

                rows.forEach(function (row) {

                    // ไม่มีข้อมูล
                    if (!row || row.length === 0) {
                        return;
                    }

                    // Column A
                    var itemCode = String(row[0]).trim();

                    // Column B
                    var status = String(row[1]).trim();

                    // ถ้าไม่มี Item Code ให้ข้าม
                    if (itemCode === "") {
                        return;
                    }

                    arr.push({
                        itemCode: itemCode,
                        status: status
                    });

                });

                // =====================================================
                // แปลงเป็น JSON
                // =====================================================

                var json_object = JSON.stringify(arr);

                console.log("JSON:");
                console.log(json_object);

                console.log("Array:");
                console.log(arr);

                // =====================================================
                // ตัวอย่าง JSON ที่ได้
                //
                // [
                //     {
                //         "itemCode": "RFZZZ2J20003",
                //         "ppriod": "90"
                //     },
                //     {
                //         "itemCode": "RFZZZ2J20004",
                //         "ppriod": "90"
                //     }
                // ]
                // =====================================================


                // =====================================================
                // เอาข้อมูลไปใช้ต่อ
                // =====================================================

                // ตัวอย่าง:
                // uploadExcelData(json_object);


                // ถ้าต้องการใช้กับ grid เดิม
                // gridSCB_Intraday(json_object);
console.log("--------------------");

console.log(json_object);

gridSCB_Intraday(json_object);

   

        };


        // =====================================================
        // Error
        // =====================================================

        reader.onerror = function (ex) {

            console.log(ex);

            alert("ไม่สามารถอ่านไฟล์ Excel ได้");

        };


        // =====================================================
        // อ่าน Excel
        // =====================================================

        reader.readAsBinaryString(file);

    };

};

        function handleFileSelect(evt) {
            console.log("selected file");
            var files = evt.target.files; // FileList object
            var xl2json = new ExcelToJSON();

console.log(xl2json);
            xl2json.parseExcel(files[0]);
            
            
        }


        $("#vUpload").click(function (e) {



            var data = $("#jsGrid").jsGrid("option", "data");

            var cono = <%out.print(session.getAttribute("cono"));%>;
            var divi = <%out.print(session.getAttribute("divi"));%>;
            var username = "<%out.print(session.getAttribute("username"));%>";
            console.log("--------------------");
            console.log(JSON.stringify(data));
            console.log(data);


            console.log("--------------------");

           
                $.ajax({
                    url: './Sync',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        page: "Disableitem",
                        cono: cono,
                        divi: divi,
                        user: username,
                        jsondata: JSON.stringify(data)
                    },
                    async: false
                });
          
            //*****************************************************************

        
            alert("Disable complete !");
            location.reload(0);
        });






       function gridSCB_Intraday(json_data) {

    var data = JSON.parse(json_data);



    $("#jsGrid").jsGrid({
        width: "100%",
        height: "auto",
        pageSize: 100,
        pageButtonCount: 5,
        sorting: true,
        paging: true,
        editing: false,

        data: data,

        fields: [
            {
                title: "Item No.",
                name: "itemCode",
                type: "text",
                align: "center",
                width: 80,
                editing: false
            },   
            {
                title: "Status",
                name: "status",
                type: "text",
                align: "center",
                width: 50,
                editing: false
            }
        ]
    });

    var gridData = $("#jsGrid").jsGrid("option", "data");

    console.log("Grid Data:");
    console.log(gridData);
}

        function gridSCB_History(json_data) {
            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: false,
//                editing: true,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", width: "70", name: "Account_Number", type: "text", align: "center", editing: false},
                    {title: "Date", width: "50", name: "Date", type: "text", align: "center", editing: false},
                    {title: "Time", width: "50", name: "Time", type: "time", align: "center", editing: false},
                    {title: "TransCode", width: "50", name: "Transaction_Code", type: "text", align: "center", editing: false},
                    {title: "Channel", width: "50", name: "Channel", type: "text", align: "center", editing: false},
                    {title: "Cheque", width: "50", name: "Cheque_Number", type: "text", align: "center", editing: false},
                    {title: "Description", name: "Description", type: "text", align: "left", editing: false},
                    {title: "Amount", width: "65", name: "Credit_Amount", editing: false, type: "number", align: "right", itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }}
                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");

        }

        function gridKBANK_Intraday(json_data) {
            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", width: "70", itemTemplate: function (value) {
                            return  "3402314428";
                        }},
                    {title: "Date", name: "Date", type: "text", align: "center"},
                    {title: "Time", name: "Time", type: "text", align: "center"},
                    {title: "Description", name: "Description", type: "text", align: "center"},
                    {title: "ChequeNo", name: "Cheque_No", type: "text", align: "center"},
                    {title: "Amount", name: "Deposit", type: "number", align: "right", itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }},
                    {title: "Channel", name: "Channel", type: "text", align: "center"}
                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }

        function gridKBANK_Historical(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "3402314428";
                        }},
                    {title: "Date", name: "Date", type: "text", align: "center"},
                    {title: "Time", name: "Time_Eff_Date", type: "text", align: "center"},
                    {title: "Description", name: "Description", type: "text", align: "center"},
                    {title: "Amount", name: "Deposit", type: "number", align: "right", itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }},
                    {title: "Channel", name: "Channel", type: "text", align: "left"}
                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }

        function grid1AA2250_IntraDay(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "3401018025";
                        }},
                    {title: "Date", name: "Transaction_Date", type: "text", align: "center"},
                    {title: "Time", name: "Transaction_Time", type: "text", align: "center"},
                    {title: "Desc", name: "Payer_Name", type: "text", align: "center"},
                    {title: "Ref.Cust.", name: "Reference1", type: "text", align: "center"},
                    {title: "Ref.2.", name: "Reference2", type: "text", align: "center"},
                    {title: "Amount", name: "Amount", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }}
                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }

        //KBNAK BILL 
        function grid1AA2250_Historical(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "3401018025";
                        }},
                    {title: "Date", name: "Transaction_Date", type: "text", align: "center"},
                    {title: "Time", name: "Transaction_Time", type: "text", align: "center"},
                    {title: "Description", name: "Payer_Name", type: "text", align: "center"},
                    {title: "Ref.Cust.", name: "Reference1", type: "text", align: "center"},
                    {title: "Ref.2.", name: "Reference2", type: "text", align: "center"},
                    {title: "Amount", name: "Amount", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }}

                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }

        function grid1AA2114_Historical(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "1227360581";
                        }},
                    {title: "Date", name: "Tran_Date", type: "text", align: "center"},
                    {title: "Time", name: "time", type: "text", align: "center"},
                    {title: "DESC", name: "Sender_Account_Name", type: "text", align: "center"},
                    {title: "Credit", name: "Credit", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                if (value.toString() == "") {

                                    return val = "0.00".replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                }
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }},
                    {title: "Ref1", name: "Description", type: "text", align: "center"}


                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }


        function grid1AA2114_Intraday(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "1227360581";
                        }},
                    {title: "Date", name: "Tran_Date", type: "text", align: "center"},
                    {title: "Time", name: "time", type: "text", align: "center"},
                    {title: "Desc", name: "Sender_Account_Name", type: "text", align: "center"},
                    {title: "Credit", name: "Credit", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                if (val == "") {

                                    return 0.00;
                                }
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }},
                    {title: "Ref1", name: "Description", type: "text", align: "center"}

                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }
        function grid1AA2214_Intraday(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "1223098219";
                        }},
                    {title: "Date", name: "PAY_DATE", type: "text", align: "center"},
                    {title: "Time", name: "PAY_TIME", type: "text", align: "center"},
                    {title: "Description", name: "CUSTOMER_NAME", type: "text", align: "center"},
                    {title: "Ref.CUS", name: "CUSTOMER_NO_", type: "text", align: "center"},
                    {title: "REF1", name: "REFERENCE_NO_", type: "text", align: "center"},
                    {title: "Ref3", name: "REFERENCE_NO_3", type: "text", align: "center"},
                    {title: "FR_BR", name: "FR_BR_", type: "text", align: "center"},
                    {title: "BY_", name: "BY_", type: "text", align: "center"},
                    {title: "AMOUNT_", name: "AMOUNT_", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }}

                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }


        //**********************BBL QR ************************



 function gridBBLQR_Historical(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [

                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "1223098219";
                        }},
                    {title: "Date", name: "PAY_DATE", type: "text", align: "center"},
                    {title: "Time", name: "PAY_TIME", type: "text", align: "center"},
                    {title: "Description", name: "CUSTOMER_NAME", type: "text", align: "center"},
                    {title: "REF1", name: "REFERENCE_NO_", type: "text", align: "center"},
                    {title: "FR_BR", name: "FR_BR_", type: "text", align: "center"},
                    {title: "BY", name: "BY", type: "text", align: "center"},
                    {title: "AMOUNT", name: "AMOUNT", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }}



                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }



        function gridBBLQR_Intraday(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [

                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "1223098219";
                        }},
                    {title: "Date", name: "PAY_DATE", type: "text", align: "center"},
                    {title: "Time", name: "PAY_TIME", type: "text", align: "center"},
                    {title: "Description", name: "CUSTOMER_NAME", type: "text", align: "center"},
                    {title: "REF1", name: "REF_1", type: "text", align: "center"},
                    {title: "Ref2", name: "REF_2", type: "text", align: "center"},
                    {title: "FR_BR", name: "FR_BR__", type: "text", align: "center"},
                    {title: "PAY_BY", name: "PAY_BY", type: "text", align: "center"},
                    {title: "AMOUNT", name: "AMOUNT_", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }}

//                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
//                            return  "1223098219";
//                        }},
//                    {title: "Date", name: "Tran_Date", type: "text", align: "center"},
//                    {title: "Time", name: "time", type: "text", align: "center"},
//                    {title: "Description", name: "Description", type: "text", align: "center"},
//                    {title: "Sender_Account_Name", name: "Sender_Account_Name", type: "text", align: "center"},
////                    {title: "Ref2", name: "Ref2", type: "text", align: "center"},
////                    {title: "Ref3", name: "Ref3", type: "text", align: "center"},
////                    {title: "Bankcode", name: "Bankcode", type: "text", align: "center"},
////                    {title: "Branch", name: "Branch", type: "text", align: "center"},
////                    {title: "ActDate", name: "ActDate", type: "text", align: "center"},
////                    {title: "CHQNO", name: "CHQNO", type: "text", align: "center"},
////                    {title: "BankCHQ", name: "BankCHQ", type: "text", align: "center"},
////                    {title: "BranchCHQ", name: "BranchCHQ", type: "text", align: "center"},
////                    {title: "Channel", name: "Channel", type: "text", align: "center"},
////                    {title: "Remarks", name: "Remarks", type: "text", align: "center"},
//                    {title: "Amount", name: "Credit", type: "number", align: "right", editing: false, itemTemplate: function (value) {
//                            try {
//                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
//                                return val;
//                            } catch (e) {
//                                return 0;
//                            }
//                        }}

                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }


        //****************************************************


        function grid1AA2214_Historical(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [

                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "1223098219";
                        }},
                    {title: "Date", name: "PAY_DATE", type: "text", align: "center"},
                    {title: "Time", name: "PAY_TIME", type: "text", align: "center"},
                    {title: "Description", name: "CUSTOMER_NAME", type: "text", align: "center"},
                    {title: "Ref.CUS", name: "CUSTOMER_NO_", type: "text", align: "center"},
                    {title: "REF1", name: "REFERENCE_NO_", type: "text", align: "center"},
                    {title: "Ref3", name: "REFERENCE_NO_3", type: "text", align: "center"},
                    {title: "FR_BR", name: "FR_BR_", type: "text", align: "center"},
                    {title: "BY_", name: "BY_", type: "text", align: "center"},
                    {title: "AMOUNT", name: "AMOUNT", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }}

//                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
//                            return  "1223098219";
//                        }},
//                    {title: "Date", name: "Tran_Date", type: "text", align: "center"},
//                    {title: "Time", name: "time", type: "text", align: "center"},
//                    {title: "Description", name: "Description", type: "text", align: "center"},
//                    {title: "Sender_Account_Name", name: "Sender_Account_Name", type: "text", align: "center"},
////                    {title: "Ref2", name: "Ref2", type: "text", align: "center"},
////                    {title: "Ref3", name: "Ref3", type: "text", align: "center"},
////                    {title: "Bankcode", name: "Bankcode", type: "text", align: "center"},
////                    {title: "Branch", name: "Branch", type: "text", align: "center"},
////                    {title: "ActDate", name: "ActDate", type: "text", align: "center"},
////                    {title: "CHQNO", name: "CHQNO", type: "text", align: "center"},
////                    {title: "BankCHQ", name: "BankCHQ", type: "text", align: "center"},
////                    {title: "BranchCHQ", name: "BranchCHQ", type: "text", align: "center"},
////                    {title: "Channel", name: "Channel", type: "text", align: "center"},
////                    {title: "Remarks", name: "Remarks", type: "text", align: "center"},
//                    {title: "Amount", name: "Credit", type: "number", align: "right", editing: false, itemTemplate: function (value) {
//                            try {
//                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
//                                return val;
//                            } catch (e) {
//                                return 0;
//                            }
//                        }}

                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }

        function grid1AA2286_Intraday(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "3313019322";
                        }},
                    {title: "Date", name: "Date", type: "text", align: "center"},
                    {title: "Time", name: "Time", type: "text", align: "center"},
                    {title: "Discription", name: "Customer_Name", type: "text", align: "center"},
                    {title: "Desc", name: "Customer_Name", type: "text", align: "center"},
                    {title: "Ref1", name: "Ref1", type: "text", align: "center"},
//                    {title: "Ref2", name: "Ref2", type: "text", align: "center"},
//                    {title: "Ref3", name: "Ref3", type: "text", align: "center"},
//                    {title: "Bankcode", name: "Bankcode", type: "text", align: "center"},
//                    {title: "Branch", name: "Branch", type: "text", align: "center"},
//                    {title: "ActDate", name: "ActDate", type: "text", align: "center"},
//                    {title: "CHQNO", name: "CHQNO", type: "text", align: "center"},
//                    {title: "BankCHQ", name: "BankCHQ", type: "text", align: "center"},
//                    {title: "BranchCHQ", name: "BranchCHQ", type: "text", align: "center"},
//                    {title: "Channel", name: "Channel", type: "text", align: "center"},
//                    {title: "Remarks", name: "Remarks", type: "text", align: "center"},
                    {title: "Amount", name: "Amount", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }}

                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }


        function grid1AA2286_Historical(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "3313019322";
                        }},
                    {title: "Date", name: "Date", type: "text", align: "center"},
                    {title: "Time", name: "Time", type: "text", align: "center"},
                    {title: "Desc", name: "Customer_Name", type: "text", align: "center"},
                    {title: "Ref.Cus", name: "Ref2", type: "text", align: "center"},

                    {title: "Amount", name: "Amount", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }}

                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }


        function grid1AA2283_Historical(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "3313019398";
                        }},
                    {title: "Date", name: "Date", type: "text", align: "center"},
                    {title: "Time", name: "Time", type: "text", align: "center"},
                    {title: "Desc", name: "Customer", type: "text", align: "center"},
                    {title: "Ref.Cus", name: "Ref2", type: "text", align: "center"},

                    {title: "Amount", name: "Amount", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }}

                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }
        function grid1AA2283_Intraday(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "3313019398";
                        }},
                    {title: "Date", name: "Date", type: "text", align: "center"},
                    {title: "Time", name: "Time", type: "text", align: "center"},
                    {title: "Desc", name: "Customer_Name", type: "text", align: "center"},
                    {title: "Ref.Cus", name: "Ref2", type: "text", align: "center"},

                    {title: "Amount", name: "Amount", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }}

                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }

//---------------------------------Current--------------------------------

        function gridSCBCUR_Intraday(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccNo", name: "Account_Number", type: "text", align: "center", itemTemplate: function (value) {
                            return  "3313019322";
                        }},
                    {title: "Code", name: "Transaction_Code", type: "text", align: "center", width: "30", editing: false},
                    {title: "Description", name: "Description", type: "text", align: "left", editing: false},
                    {title: "Date", name: "Date", type: "text", align: "center", width: "50", editing: false},
                    {title: "Time", name: "Time", type: "text", align: "center", width: "50", editing: false},
//                    {title: "TransferCode", name: "TransferCode", type: "text", align: "center", editing: false},
                    {title: "BranchNumber", name: "Branch_Number", type: "text", width: "50", align: "center", editing: false},
                    {title: "Amount", name: "Amount", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }}
                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }





        function gridSCBCUR_Historical(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: false,
//                editing: true,
                data: JSON.parse(json_object),
                fields: [
                    {title: "Account_Number", width: "70", name: "Account_Number", type: "text", align: "center", itemTemplate: function (value) {
                            return  "3313019322";
                        }, editing: false},
                    {title: "Date", width: "50", name: "Date", type: "text", align: "center", editing: false},
                    {title: "Time", width: "50", name: "Time", type: "time", align: "center", editing: false},
                    {title: "Transaction_Code", width: "50", name: "Transaction_Code", type: "text", align: "center", editing: false},
                    {title: "Channel", width: "50", name: "Channel", type: "text", align: "center", editing: false},
                    {title: "Cheque_Number", width: "50", name: "Cheque_Number", type: "text", align: "center", editing: false},
                    {title: "Description", name: "Description", type: "text", align: "left", editing: false},
                    {title: "Credit_Amount", width: "65", name: "Credit_Amount", editing: false, type: "number", align: "right", itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }}
                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");



        }



        function gridKBANKCUR_Intraday(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", width: "70", itemTemplate: function (value) {
                            return  "3401018025";
                        }},
                    {title: "Date", name: "Date", type: "text", align: "center"},
                    {title: "Time", name: "Time", type: "text", align: "center"},
                    {title: "Description", name: "Description", type: "text", align: "center"},
                    {title: "ChequeNo", name: "Cheque_No", type: "text", align: "center"},
                    {title: "Amount", name: "Deposit", type: "number", align: "right", itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }},
                    {title: "Channel", name: "Channel", type: "text", align: "center"}
                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }

        //KBNAK BILL 
        function gridKBANKCUR_Historical(json_data) {


            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "3401018025";
                        }},
                    {title: "Date", name: "Date", type: "text", align: "center"},
                    {title: "Time", name: "Time_Ent_Date", type: "text", align: "center"},
                    {title: "Description", name: "Description", type: "text", align: "center"},
                    {title: "Amount", name: "Deposit", type: "number", align: "right", itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }},
                    {title: "Channel", name: "Channel", type: "text", align: "left"}
                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }


        function gridBBLCUR_Intraday(json_data) {
            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "1223098219";
                        }},
                    {title: "Date", name: "Tran_Date", type: "text", align: "center"},
                    {title: "Time", name: "time", type: "text", align: "center"},
                    {title: "Desc", name: "Sender_Account_Name", type: "text", align: "center"},
                    {title: "Credit", name: "Credit", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                if (val == "") {

                                    return 0.00;
                                }
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }},
                    {title: "Ref1", name: "Description", type: "text", align: "center"}

                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");
        }


        function gridBBLCUR_Historical(json_data) {

            $("#jsGrid").jsGrid({
                width: "100%",
                height: "auto",
                pageSize: 100,
                pageButtonCount: 5,
                sorting: true,
                paging: true,
                editing: false,
                data: JSON.parse(json_object),
                fields: [
                    {title: "AccountNumber", name: "AccountNumber", type: "number", align: "center", itemTemplate: function (value) {
                            return  "122-3-098219";
                        }},
                    {title: "Date", name: "Tran_Date", type: "text", align: "center"},
                    {title: "Time", name: "time", type: "text", align: "center"},
                    {title: "DESC", name: "Sender_Account_Name", type: "text", align: "center"},
                    {title: "Credit", name: "Credit", type: "number", align: "right", editing: false, itemTemplate: function (value) {
                            try {
                                const val = value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                if (value.toString() == "") {

                                    return val = "0.00".replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                                }
                                return val;
                            } catch (e) {
                                return 0;
                            }
                        }},
                    {title: "Ref1", name: "Description", type: "text", align: "center"}


                ]
            });
            var data = $("#jsGrid").jsGrid("option", "data");

        }


//---------------------------------Current--------------------------------





    </script>

</html>

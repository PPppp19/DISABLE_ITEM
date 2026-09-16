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
                    <div class="panel-heading " style="background-color: blue">
                        <h3 class="panel-title"> <font color="#FFFFFF" >Bank Mapping : Upload_Data Winthai Farm</font></h3>
                    </div>
                    <div class="container-fluid panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
                        <div class="row">
                            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                <label for="type">Type</label>
                                <select class="form-control form-control-user" name="vType" id="vType">
                                    <option disabled>────────────────────────────────────</option>
                                    <option value="SCB_IntraDayWTF">SCB Intraday WTF</option>
                                    <option value="SCB_HistoricalWTF">SCB Historical WTF</option>
                                    <option value="BBL_IntradayWTF">BBL Intraday WTF</option>
                                    <option value="BBL_HistoricalWTF">BBL Historical WTF</option>
                                    <option disabled>────────────────────────────────────</option>
                                </select>
                            </div>
                            <div class="col-xs-5 col-sm-4 col-md-4 col-lg-3">
                                <label for="cars">File:</label>
                                <form enctype="multipart/form-data">
                                    <input id="upload" class="form-control-user" type=file name="files[]" accept=".csv,application/vnd.ms-excel,.xlt,application/vnd.ms-excel,.xla,application/vnd.ms-excel,.xlsx,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,.xltx,application/vnd.openxmlformats-officedocument.spreadsheetml.template,.xlsm,application/vnd.ms-excel.sheet.macroEnabled.12,.xltm,application/vnd.ms-excel.template.macroEnabled.12,.xlam,application/vnd.ms-excel.addin.macroEnabled.12,.xlsb,application/vnd.ms-excel.sheet.binary.macroEnabled.12">
                                </form>   
                            </div>
                            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                <label for="cars"></label>
                                <button id="vUpload" class="form-control btn-block btn-danger">Upload</button>
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
            var fullPath = document.getElementById('upload').value;
            if (fullPath) {
                var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
                var filename = fullPath.substring(startIndex);
                if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
                    filename = filename.substring(1);
                    console.log(filename);
                }
                const nameArray = filename.split(" ");
                localStorage.setItem("bank", nameArray[0]);
            }


            this.parseExcel = function (file) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    var data = e.target.result;
                    var workbook = XLSX.read(data, {
                        type: 'binary'
                    });




                    workbook.SheetNames.forEach(function (sheetName) {
                        // Here is your object
                        var XL_row_object = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName]);
                        json_object = JSON.stringify(XL_row_object);

                        console.log(json_object);




                        const bank = localStorage.getItem("bank");
                        const type = $("#vType").val();

                        console.log(type);
                        console.log("type");

                        if (type === "SCB_IntraDayWTF") {
//                            json_object = json_object.replace(/Account Number/g, "AccountNumber");
//                            json_object = json_object.replace(/Record Number/g, "RecordNumber");
//                            json_object = json_object.replace(/Transaction Code/g, "TransactionCode");
//                            json_object = json_object.replace(/Branch Number/g, "BranchNumber");
//                            json_object = json_object.replace(/Credit Amount/g, "CreditAmount");
                            console.log("****************");
                            const arr = JSON.parse(json_object);

                            arr.forEach(function (e, i) {
                                // Iterate over the keys of object
                                Object.keys(e).forEach(function (key) {

                                    // Copy the value
                                    var val = e[key],
                                            newKey = key.replace(/\s+/g, '_');

                                    // Remove key-value from object
                                    delete arr[i][key];

                                    // Add value with new key
                                    arr[i][newKey] = val;

                                    if (newKey == "Date") {

                                        e.Date = formatDateMMN(arr[i].Date);

                                    }
                                    if (newKey == "Time") {

                                        e.Time = formatTime1(arr[i].Time);


                                    }



                                });
                            });

                            json_object = JSON.stringify(arr);

                            console.log("****************");
                            console.log(json_object);
                            console.log(arr);




                            gridSCB_Intraday(json_object);
                        } else if (type === "SCB_HistoricalWTF") {
//                            json_object = json_object.replace(/Account Number/g, "AccountNumber");
//                            json_object = json_object.replace(/Record Number/g, "RecordNumber");
//                            json_object = json_object.replace(/Transaction Code/g, "TransactionCode");
//                            json_object = json_object.replace(/Branch Number/g, "BranchNumber");
//                            json_object = json_object.replace(/Credit Amount/g, "CreditAmount");

                            console.log("****************");
                            const arr = JSON.parse(json_object);

                            arr.forEach(function (e, i) {
                                // Iterate over the keys of object
                                Object.keys(e).forEach(function (key) {

                                    // Copy the value
                                    var val = e[key],
                                            newKey = key.replace(/\s+/g, '_');

                                    // Remove key-value from object
                                    delete arr[i][key];

                                    // Add value with new key
                                    arr[i][newKey] = val;

                                    if (newKey == "Date") {

                                        e.Date = formatDateMMN(arr[i].Date);

                                    }
                                    if (newKey == "Time") {

                                        e.Time = formatTime1SCB(arr[i].Time);


                                    }



                                });
                            });

                            json_object = JSON.stringify(arr);

                            console.log("****************");
                            console.log(json_object);
                            console.log(arr);

                            gridSCB_History(json_object);
//                        } else if (type === "KBANK_IntraDay") {
////                            json_object = json_object.replace(/Cheque No./g, "ChequeNo");
//
//                            console.log("****************");
//                            const arr = JSON.parse(json_object);
//
//                            arr.forEach(function (e, i) {
//                                // Iterate over the keys of object
//                                Object.keys(e).forEach(function (key) {
//
//                                    // Copy the value
//                                    var val = e[key],
//                                            newKey = key.replace(/\s+/g, '_');
//
//                                    // Remove key-value from object
//                                    delete arr[i][key];
//
//                                    // Add value with new key
//                                    arr[i][newKey] = val;
//
//                                    if (newKey == "Date") {
//
//                                        e.Date = formatDate1(arr[i].Date);
//
//                                    }
//                                    if (newKey == "Time") {
//
//                                        e.Time = formatTime1(arr[i].Time);
//
//
//                                    }
//
//
//
//                                });
//                            });
//
//                            json_object = JSON.stringify(arr);
//
//                            console.log("****************");
//                            console.log(json_object);
//                            console.log(arr);
//
//
//                            gridKBANK_Intraday(json_object);
//                        } else if (type === "KBANK_Historical") {
////                            json_object = json_object.replace(/Cheque No./g, "ChequeNo");
//
//                            const arr = JSON.parse(json_object);
//
//                            arr.forEach(function (e, i) {
//                                // Iterate over the keys of object
//                                Object.keys(e).forEach(function (key) {
//
//                                    // Copy the value
//                                    var val = e[key],
//                                            newKey = key.replace(/\s+/g, '_');
//                                    newKey = newKey.replace(/\.+/g, '_');
//                                    newKey = newKey.replace(/\/+/g, '_');
//
//                                    // Remove key-value from object
//                                    delete arr[i][key];
//
//                                    // Add value with new key
//                                    arr[i][newKey] = val;
//
//                                    if (newKey == "Date") {
//
//                                        e.Date = formatDate1(arr[i].Date);
//
//                                    }
//                                    if (newKey == "Time_Eff_Date") {
//
//                                        e.Time_Eff_Date = formatTime1(arr[i].Time_Eff_Date);
//
//
//                                    }
//
//
//
//                                });
//                            });
//
//                            json_object = JSON.stringify(arr);
//
//                            console.log("****************");
//                            console.log(json_object);
//                            console.log(arr);
//
//
//
//                            gridKBANK_Historical(json_object);
//                        } else if (type === "KBANKBill_Intraday") {
//                            console.log("****************");
//                            const arr = JSON.parse(json_object);
//
//                            arr.forEach(function (e, i) {
//                                // Iterate over the keys of object
//                                Object.keys(e).forEach(function (key) {
//
//                                    // Copy the value
//                                    var val = e[key],
//                                            newKey = key.replace(/\s+/g, '_');
//
//                                    // Remove key-value from object
//                                    delete arr[i][key];
//
//                                    // Add value with new key
//                                    arr[i][newKey] = val;
//
//                                    if (newKey == "Transaction_Date") {
//
//                                        e.Transaction_Date = formatDate1(arr[i].Transaction_Date);
//
//                                    }
//                                    if (newKey == "Transaction_Time") {
//
//                                        e.Transaction_Time = formatTime1(arr[i].Transaction_Time);
//
//
//                                    }
//
//
//
//                                });
//                            });
//
//                            json_object = JSON.stringify(arr);
//
//                            console.log("****************");
//
//                            grid1AA2250_IntraDay(json_object);
//
//                        } else if (type === "KBANKBill_Historical") {
//
//                            console.log("****************");
//                            const arr = JSON.parse(json_object);
//
//
//                            arr.forEach(function (e, i) {
//                                // Iterate over the keys of object
//                                Object.keys(e).forEach(function (key) {
//
//
//
//
//
//                                    // Copy the value
//                                    var val = e[key],
//                                            newKey = key.replace(/\s+/g, '_');
//
//                                    // Remove key-value from object
//                                    delete arr[i][key];
//
//                                    // Add value with new key
//                                    arr[i][newKey] = val;
//
//                                    if (newKey == "Transaction_Date") {
//
//                                        e.Transaction_Date = formatDate1(arr[i].Transaction_Date);
//
//                                    }
//                                    if (newKey == "Transaction_Time") {
//
//                                        e.Transaction_Time = formatTime1(arr[i].Transaction_Time);
//
////                                        e.Transaction_Time = "1";
//
//                                    }
//
//
//
//                                });
//                            });
//
//                            json_object = JSON.stringify(arr);
//
//                            console.log("****************");
//                            grid1AA2250_Historical(json_object);
//
                        } else if (type === "BBL_HistoricalWTF") {

                            console.log("****************");
                            console.log(JSON.parse(json_object));
                            console.log("****************");
                            const arr = JSON.parse(json_object);
                            arr.forEach(function (e, i) {
                                // Iterate over the keys of object
                                Object.keys(e).forEach(function (key) {





                                    // Copy the value
                                    var val = e[key],
                                            newKey = key.replace(/\s+/g, '_');

                                    if (newKey == "Tran_Date") {
                                        newKey1 = "time";
                                        arr[i][newKey1] = val;
                                    }

                                    // Remove key-value from object
                                    delete arr[i][key];

                                    // Add value with new key
                                    arr[i][newKey] = val;

                                    if (newKey == "Tran_Date") {

                                        e.Tran_Date = formatDateBBLINT(arr[i].Tran_Date);

                                        if (newKey1 == "time") {
                                            e.time = formatTimeBBLINT(arr[i].time);

                                        }


                                    }








                                });
                            });

                            json_object = JSON.stringify(arr);
                            gridBBL_Historical(json_object);

                        } else if (type === "BBL_IntradayWTF") {

                            console.log("****************");
                            console.log(JSON.parse(json_object));
                            console.log("****************");
                            const arr = JSON.parse(json_object);
                            arr.forEach(function (e, i) {
                                // Iterate over the keys of object
                                Object.keys(e).forEach(function (key) {





                                    // Copy the value
                                    var val = e[key],
                                            newKey = key.replace(/\s+/g, '_');

                                    if (newKey == "Tran_Date") {
                                        newKey1 = "time";
                                        arr[i][newKey1] = val;
                                    }

                                    // Remove key-value from object
                                    delete arr[i][key];

                                    // Add value with new key
                                    arr[i][newKey] = val;

                                    if (newKey == "Tran_Date") {

                                        e.Tran_Date = formatDateBBLINT(arr[i].Tran_Date);

                                        if (newKey1 == "time") {
                                            e.time = formatTimeBBLINT(arr[i].time);

                                        }


                                    }








                                });
                            });

                            json_object = JSON.stringify(arr);
                            console.log(JSON.parse(json_object));

                            gridBBL_Intraday(json_object);


//                        } else if (type === "BBLBill_Historical") {
//
//                            console.log("****************");
//                            console.log(JSON.parse(json_object));
//                            console.log("****************");
//                            const arr = JSON.parse(json_object);
//                            arr.forEach(function (e, i) {
//                                // Iterate over the keys of object
//                                Object.keys(e).forEach(function (key) {
//
//
//
//
//
//                                    // Copy the value
//                                    var val = e[key],
//                                            newKey = key.replace(/\s+/g, '_');
//                                    newKey = newKey.replace(/\.+/g, '_');
//                                    newKey = newKey.replace(/\/+/g, '_');
//
////                                    if (newKey == "Tran_Date") {
////                                        newKey1 = "time";
////                                        arr[i][newKey1] = val;
////                                    }
//
//                                    // Remove key-value from object
//                                    delete arr[i][key];
//
//                                    // Add value with new key
//                                    arr[i][newKey] = val;
//
//                                    if (newKey == "PAY_DATE") {
//
//                                        e.PAY_DATE = formatDateBBL(arr[i].PAY_DATE);
//
//
//                                    }
//
//                                    if (newKey == "PAY_TIME") {
//                                        e.PAY_TIME = formatTime1SCB(arr[i].PAY_TIME);
//
//                                    }
//
//
//
//
//
//
//
//
//                                });
//                            });
//
//                            json_object = JSON.stringify(arr);
//                            console.log("json_objectxx");
//                            console.log(json_object);
//                            grid1AA2214_Historical(json_object);
//
//                        } else if (type === "BBLBill_Intraday") {
//
//                            console.log("****************");
//                            console.log(JSON.parse(json_object));
//                            console.log("****************");
//
//                            const arr = JSON.parse(json_object);
//                            arr.forEach(function (e, i) {
//                                // Iterate over the keys of object
//                                Object.keys(e).forEach(function (key) {
//
//
//
//
//
//                                    // Copy the value
//                                    var val = e[key],
//                                            newKey = key.replace(/\s+/g, '_');
//                                    newKey = newKey.replace(/\.+/g, '_');
//
//
//                                    // Remove key-value from object
//                                    delete arr[i][key];
//
//                                    // Add value with new key
//                                    arr[i][newKey] = val;
//
//                                    if (newKey == "PAY_DATE") {
//
//                                        e.PAY_DATE = formatDateBBLTHAI(arr[i].PAY_DATE);
//
//
//                                    }
//                                    if (newKey == "PAY_TIME") {
//                                        e.PAY_TIME = formatTime1(arr[i].PAY_TIME);
//
//                                    }
//
//
//
//
//
//
//
//
//                                });
//                            });
//
//
//
//                            json_object = JSON.stringify(arr);
//                            console.log(JSON.parse(json_object));
//                            grid1AA2214_Intraday(json_object);
//
//                        } else if (type === "SCBMMN_Intraday") {
//
//                            console.log("****************");
//                            console.log(JSON.parse(json_object));
//                            console.log("****************");
//                            const arr = JSON.parse(json_object);
//
//                            arr.forEach(function (e, i) {
//                                // Iterate over the keys of object
//                                Object.keys(e).forEach(function (key) {
//
//                                    // Copy the value
//                                    var val = e[key],
//                                            newKey = key.replace(/\s+/g, '_');
//
//                                    // Remove key-value from object
//                                    delete arr[i][key];
//
//                                    // Add value with new key
//                                    arr[i][newKey] = val;
//
//                                    if (newKey == "Date") {
//
//                                        e.Date = formatDateMMN(arr[i].Date);
//
//                                    }
//
//
//
//
//
//                                    if (newKey == "Time") {
//
//                                        e.Time = formatTime1(arr[i].Time).toString();
//                                    }
//
//
//
//                                });
//                            });
//
//                            json_object = JSON.stringify(arr);
//                            console.log(JSON.parse(json_object));
//                            grid1AA2286_Intraday(json_object);
//
//                        } else if (type === "SCBMMN_Historical") {
//
//                            console.log("****************");
//                            console.log(JSON.parse(json_object));
//                            console.log("****************");
//
//                            const arr = JSON.parse(json_object);
//
//                            arr.forEach(function (e, i) {
//                                // Iterate over the keys of object
//                                Object.keys(e).forEach(function (key) {
//
//                                    // Copy the value
//                                    var val = e[key],
//                                            newKey = key.replace(/\s+/g, '_');
//
//                                    // Remove key-value from object
//                                    delete arr[i][key];
//
//                                    // Add value with new key
//                                    arr[i][newKey] = val;
//
//                                    if (newKey == "Date") {
//
//                                        e.Date = formatDateMMN(arr[i].Date);
//
//                                    }
//
//
//
//
//
//                                    if (newKey == "Time") {
//
//                                        e.Time = formatTime1(arr[i].Time).toString();
//                                    }
//
//
//
//                                });
//                            });
//
//                            json_object = JSON.stringify(arr);
//                            console.log(JSON.parse(json_object));
//                            grid1AA2286_Historical(json_object);
//
//                        } else if (type === "SCBBill_Intraday") {
//
//                            console.log("****************");
//                            console.log(JSON.parse(json_object));
//                            console.log("****************");
//
//                            const arr = JSON.parse(json_object);
//
//                            arr.forEach(function (e, i) {
//                                // Iterate over the keys of object
//                                Object.keys(e).forEach(function (key) {
//
//                                    // Copy the value
//                                    var val = e[key],
//                                            newKey = key.replace(/\s+/g, '_');
//
//                                    // Remove key-value from object
//                                    delete arr[i][key];
//
//                                    // Add value with new key
//                                    arr[i][newKey] = val;
//
//                                    if (newKey == "Date") {
//
//                                        e.Date = formatDateMMN(arr[i].Date);
//
//                                    }
//
//
//
//
//
//                                    if (newKey == "Time") {
//
//                                        e.Time = formatTime1(arr[i].Time).toString();
//                                    }
//
//
//
//                                });
//                            });
//
//                            json_object = JSON.stringify(arr);
//                            console.log(JSON.parse(json_object));
//                            grid1AA2283_Intraday(json_object);
//
//                        } else if (type === "SCBBill_Historical") {
//
//                            console.log("****************");
//                            console.log(JSON.parse(json_object));
//                            console.log("****************");
//
//                            const arr = JSON.parse(json_object);
//
//                            arr.forEach(function (e, i) {
//                                // Iterate over the keys of object
//                                Object.keys(e).forEach(function (key) {
//
//                                    // Copy the value
//                                    var val = e[key],
//                                            newKey = key.replace(/\s+/g, '_');
//
//                                    // Remove key-value from object
//                                    delete arr[i][key];
//
//                                    // Add value with new key
//                                    arr[i][newKey] = val;
//
//                                    if (newKey == "Date") {
//
//                                        e.Date = formatDateSCBBILLHIS(arr[i].Date);
//
//                                    }
//
//
//
//
//
//                                    if (newKey == "Time") {
//
//                                        e.Time = formatTime1(arr[i].Time).toString();
//                                    }
//
//
//
//                                });
//                            });
//
//                            json_object = JSON.stringify(arr);
//                            console.log(JSON.parse(json_object));
//
//                            grid1AA2283_Historical(json_object);

                        } else {
                            alert("ไม่พบข้อมูลธนาคาร กรุณาตรวจสอบชื่อไฟล์ !!");
                            location.reload(0);
                        }
//                        gridSCB_2(json_object);


                    });


                };
                reader.onerror = function (ex) {
                    console.log(ex);
//                    alert(ex);
                };
                reader.readAsBinaryString(file);
            };
        };

        function handleFileSelect(evt) {
            console.log("selected file");
            var files = evt.target.files; // FileList object
            var xl2json = new ExcelToJSON();

            xl2json.parseExcel(files[0]);
        }


        $("#vUpload").click(function (e) {
            var data = $("#jsGrid").jsGrid("option", "data");

            var cono = <%out.print(session.getAttribute("cono"));%>;
            var divi = <%out.print(session.getAttribute("divi"));%>;
            var username = "<%out.print(session.getAttribute("username"));%>";
            var type = document.getElementById("vType").value;
            console.log("--------------------");
            console.log(JSON.stringify(data));
            console.log(data);
            console.log("--------------------");

            if (type === "SCB_IntraDayWTF") {
                $.ajax({
                    url: './Sync',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        page: "upload_SCB_IntraDayWTF",
                        cono: cono,
                        divi: divi,
                        bank: "SCB",
                        user: username,
                        jsondata: JSON.stringify(data)
                    },
                    async: false
                });
            } else if (type === "SCB_HistoricalWTF") {

                $.ajax({
                    url: './Sync',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        page: "upload_SCB_Historical",
                        cono: cono,
                        divi: divi,
                        bank: "SCB",
                        user: username,
                        jsondata: JSON.stringify(data)
                    },
                    async: false
                });
//            } else if (type === "KBANK_IntraDay") {
//                $.ajax({
//                    url: './Sync',
//                    type: 'POST',
//                    dataType: 'json',
//                    data: {
//                        page: "upload_KBANK_IntraDay",
//                        cono: cono,
//                        divi: divi,
//                        bank: "KBANK",
//                        user: username,
//                        jsondata: JSON.stringify(data)
//                    },
//                    async: false
//                });
//            } else if (type === "KBANK_Historical") {
//                $.ajax({
//                    url: './Sync',
//                    type: 'POST',
//                    dataType: 'json',
//                    data: {
//                        page: "upload_KBANK_Historical",
//                        cono: cono,
//                        divi: divi,
//                        bank: "KBANK",
//                        user: username,
//                        jsondata: JSON.stringify(data)
//                    },
//                    async: false
//                });
//            } else if (type === "KBANKBill_Intraday") {
//                console.log("KBANKBill_Intraday");
//                $.ajax({
//                    url: './Sync',
//                    type: 'POST',
//                    dataType: 'json',
//                    data: {
//                        page: "upload_1AA2250_Intraday",
//                        cono: cono,
//                        divi: divi,
//                        bank: "KBANK_BILL",
//                        user: username,
//                        myJsonData: JSON.stringify(data)
//                    },
//                    async: false
//                });
//            } else if (type === "KBANKBill_Historical") {
//                console.log("KBANKBill_Historical");
//                console.log(JSON.stringify(data));
//
//                $.ajax({
//                    url: './Sync',
//                    type: 'POST',
//                    dataType: 'json',
//                    data: {
//                        page: "upload_1AA2250_Historical",
//                        cono: cono,
//                        divi: divi,
//                        bank: "KBANK_BILL",
//                        user: username,
//                        myJsonData: JSON.stringify(data)
//                    },
//                    async: false
//                });
            } else if (type === "BBL_IntradayWTF") {
                console.log("BBL_Intraday");
                $.ajax({
                    url: './Sync',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        page: "upload_BBL_IntradayWTF",
                        cono: cono,
                        divi: divi,
                        bank: "BBL",
                        user: username,
                        myJsonData: JSON.stringify(data)
                    },
                    async: false
                });
            } else if (type === "BBL_HistoricalWTF") {
                console.log("BBL_Historical");
                $.ajax({
                    url: './Sync',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        page: "upload_BBL_HistoricalWTF",
                        cono: cono,
                        divi: divi,
                        bank: "BBL",
                        user: username,
                        myJsonData: JSON.stringify(data)
                    },
                    async: false
                });
//            } else if (type === "BBLBill_Intraday") {
//                console.log("BBLBill_Intraday");
//                $.ajax({
//                    url: './Sync',
//                    type: 'POST',
//                    dataType: 'json',
//                    data: {
//                        page: "upload_1AA2214_Intraday",
//                        cono: cono,
//                        divi: divi,
//                        bank: "BBL_BILL",
//                        user: username,
//                        myJsonData: JSON.stringify(data)
//                    },
//                    async: false
//                });
//            } else if (type === "BBLBill_Historical") {
//                console.log("BBLBill_Historical");
//                $.ajax({
//                    url: './Sync',
//                    type: 'POST',
//                    dataType: 'json',
//                    data: {
//                        page: "upload_1AA2214_Historical",
//                        cono: cono,
//                        divi: divi,
//                        bank: "BBL_BILL",
//                        user: username,
//                        myJsonData: JSON.stringify(data)
//                    },
//                    async: false
//                });
//            } else if (type === "SCBMMN_Intraday") {
//                console.log("SCBMMN_Intraday");
//                $.ajax({
//                    url: './Sync',
//                    type: 'POST',
//                    dataType: 'json',
//                    data: {
//                        page: "upload_1AA2286_Intraday",
//                        cono: cono,
//                        divi: divi,
//                        bank: "SCB_MMN",
//                        user: username,
//                        myJsonData: JSON.stringify(data)
//                    },
//                    async: false
//                });
//            } else if (type === "SCBMMN_Historical") {
//                console.log("SCBMMN_Historical");
//                $.ajax({
//                    url: './Sync',
//                    type: 'POST',
//                    dataType: 'json',
//                    data: {
//                        page: "upload_1AA2286_Historical",
//                        cono: cono,
//                        divi: divi,
//                        bank: "SCB_MMN",
//                        user: username,
//                        myJsonData: JSON.stringify(data)
//                    },
//                    async: false
//                });
//            } else if (type === "SCBBill_Intraday") {
//                console.log("SCBBill_Intraday");
//                $.ajax({
//                    url: './Sync',
//                    type: 'POST',
//                    dataType: 'json',
//                    data: {
//                        page: "upload_1AA2283_Intraday",
//                        cono: cono,
//                        divi: divi,
//                        bank: "SCB_BILL",
//                        user: username,
//                        myJsonData: JSON.stringify(data)
//                    },
//                    async: false
//                });
//            } else if (type === "SCBBill_Historical") {
//                console.log("SCBBill_Historical");
//                $.ajax({
//                    url: './Sync',
//                    type: 'POST',
//                    dataType: 'json',
//                    data: {
//                        page: "upload_1AA2283_Historical",
//                        cono: cono,
//                        divi: divi,
//                        bank: "SCB_BILL",
//                        user: username,
//                        myJsonData: JSON.stringify(data)
//                    },
//                    async: false
//                });
            } else {
                alert("error");
            }
            alert("Upload complete !");
            location.reload(0);
        });






        function gridSCB_Intraday(json_data) {

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
                    {title: "AccNo", name: "Account_Number", type: "text", align: "center", width: "80", editing: false},
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
                    {title: "Description", name: "Description_", type: "text", align: "center"},
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
                    {title: "Description", name: "Description_", type: "text", align: "center"},
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

        function gridBBL_Historical(json_data) {

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
                            return  "1923053068";
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


        function gridBBL_Intraday(json_data) {

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
                            return  "1923053068";
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







    </script>

</html>

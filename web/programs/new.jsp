<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="assets/jQuery-3.3.1/jquery-ui.min.css">
        <link rel="stylesheet" type="text/css" href="assets/Bootstrap-3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="assets/jsgrid-1.5.3/dist/jsgrid.min.css" />
        <link rel="stylesheet" type="text/css" href="assets/jsgrid-1.5.3/dist/jsgrid-theme.min.css">
        <script type="text/javascript" src="assets/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="assets/Bootstrap-3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="assets/jQuery-3.3.1/jquery-ui.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
        <script type="text/javascript" src="assets/jsgrid-1.5.3/dist/jsgrid.min.js"></script>
    </head> 
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.13.5/xlsx.full.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.13.5/jszip.js"></script>

    <script>

    </script>
    <body>  
        <input type="file" id="fileUpload" />
        <input type="button" id="upload" value="Upload" onclick="UploadProcess()" />
        <br/>
        <div class="container panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
            <div class="row ">
                <div id="ExcelTable"></div>
            </div>

        </div>
    </body> 
    <script type="text/javascript">
        function UploadProcess() {
            //Reference the FileUpload element.
            var fileUpload = document.getElementById("fileUpload");

            //Validate whether File is valid Excel file.
            var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xls|.xlsx)$/;
            if (regex.test(fileUpload.value.toLowerCase())) {
                if (typeof (FileReader) !== "undefined") {
                    var reader = new FileReader();

                    //For Browsers other than IE.
                    if (reader.readAsBinaryString) {
                        reader.onload = function (e) {
                            GetTableFromExcel(e.target.result);
                        };
                        reader.readAsBinaryString(fileUpload.files[0]);
                    } else {
                        //For IE Browser.
                        reader.onload = function (e) {
                            var data = "";
                            var bytes = new Uint8Array(e.target.result);
                            for (var i = 0; i < bytes.byteLength; i++) {
                                data += String.fromCharCode(bytes[i]);
                            }

                            GetTableFromExcel(data);
                        };
                        reader.readAsArrayBuffer(fileUpload.files[0]);
                    }
                } else {
                    alert("This browser does not support HTML5.");
                }
            } else {
                alert("Please upload a valid Excel file.");
            }
        }
        ;
        function GetTableFromExcel(data) {
            //Read the Excel File data in binary
            var workbook = XLSX.read(data, {
                type: 'binary'
            });

            //get the name of First Sheet.
            var Sheet = workbook.SheetNames[0];

            //Read all rows from First Sheet into an JSON array.
            var excelRows = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[Sheet]);

            //Create a HTML Table element.
            var myTable = document.createElement("table");
            myTable.border = "1";

            //Add the header row.
            var row = myTable.insertRow(-1);

            //Add the header cells.
            var headerCell = document.createElement("TH");
            headerCell.innerHTML = "Cono";
            row.appendChild(headerCell);

            headerCell = document.createElement("TH");
            headerCell.innerHTML = "Item";
            row.appendChild(headerCell);

            headerCell = document.createElement("TH");
            headerCell.innerHTML = "Group1";
            row.appendChild(headerCell);

            headerCell = document.createElement("TH");
            headerCell.innerHTML = "Group2";
            row.appendChild(headerCell);

            headerCell = document.createElement("TH");
            headerCell.innerHTML = "Group3";
            row.appendChild(headerCell);

            headerCell = document.createElement("TH");
            headerCell.innerHTML = "Group4";
            row.appendChild(headerCell);



            //Add the data rows from Excel file.
            for (var i = 0; i < excelRows.length; i++) {
                //Add the data row.
                var row = myTable.insertRow(-1);

                //Add the data cells.
                var cell = row.insertCell(-1);
                cell.innerHTML = excelRows[i].Cono;

                cell = row.insertCell(-1);
                cell.innerHTML = excelRows[i].Item;

                cell = row.insertCell(-1);
                cell.innerHTML = excelRows[i].Group1;

                cell = row.insertCell(-1);
                cell.innerHTML = excelRows[i].Group2;

                cell = row.insertCell(-1);
                cell.innerHTML = excelRows[i].Group3;

                cell = row.insertCell(-1);
                cell.innerHTML = excelRows[i].Group4;
            }


            var ExcelTable = document.getElementById("ExcelTable");
            ExcelTable.innerHTML = "";
            ExcelTable.appendChild(myTable);
        }
        ;
    </script>
</html>
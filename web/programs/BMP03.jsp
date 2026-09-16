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
        <form method="GET" action="./Report">
            <div class="container-fluid ">
                <div class="row shadow">
                    <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                        <label for="type">Type</label>
                        <select class="form-control form-control-user" name="vType" id="vType">
                            <option value="Normal">Normal+Expend</option>
                            <option value="DEPOSIT">DEPOSIT</option>
                            <option value="%">All</option>
                        </select>
                    </div>
                    <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                        <label for="type" >From Date</label>
                        <input type="date" class="form-control text-center "  id="vDate" name="vDate" >
                    </div>
                    <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                        <label for="type" >To Date</label>
                        <input type="date" class="form-control text-center "  id="vtDate" name="vtDate" >
                    </div>

                    <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                        <label for="type" >Location</label>
                        <select class="form-control form-control-user" name="vLoc" id="vLoc">
                            <option value="LS">L/S</option>
                            <option value="DC_CM">DC_CM</option>
<!--                            <option value="HC_PCR">HC_PCR</option>
                            <option value="WTF">WTF</option>
                            <option value="ACCOUNT">ACCOUNT</option>
                            <option value="OTHER">OTHER</option>-->
                            <option value="%">All</option>
                        </select>
                    </div>

                    <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                        <label for="type"><br></label>
                        <!--<button id="vSearch" class="form-control btn-success">Get Report</button>-->
                        <button  type="submit" name="report"  id="report"  class="form-control btn-success" value="GetReport" style="width: 157px;">PRINT REPORT</button> 


                    </div>
               
                    
                      <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                        <label for="type"><br></label>
                        <!--<button id="vSearch" class="form-control btn-success">Get Report</button>-->
                        <button  type="submit" name="report"  id="report"  class="form-control btn-success" value="GetReport_EXCEL" style="width: 200px;">PRINT REPORT EXCEL</button> 

                    </div>
                
                    


                   

                </div>
                <hr>
            </div>
        </form
    </body>


    <script>

//        $("#vSearch").click(function (e) {
//
//
//        });






    </script>


</html>

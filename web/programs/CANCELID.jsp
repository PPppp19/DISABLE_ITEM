<%-- 
    Document   : DC000
    Created on : Apr 26, 2023, 10:19:15 AM
    Author     : Phongsathon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <style>
        .ui-widget *, .ui-widget input, .ui-widget select, .ui-widget button {
            font-family: 'Helvetica Neue Light', 'Open Sans', Helvetica;
            font-size: 14px;
            font-weight: 300 !important;
        }
        .center {


            text-align: center;
        }

        .text {

            text-transform: uppercase;
            background: linear-gradient( #6f9dae 0%, #d583ad 100%);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            font: {
                size: 20vw;
                family: 'Poppins', sans-serif;
            }
            ;
        }

    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container"  style=" background-color: #aaf0d1; border-radius: 15px">
            <div class="col-md-12">
                <div class="row">
                    <div class="panel panel-default container">
                        <div class="panel-heading" style="background-color: #000000">
                            <h4 class="text"> <font color="#ebd4f5" style="font-weight: 1000;" >CANCEL ID</font></h4>
                        </div>
                        <div class="container panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 " style="background: linear-gradient(#be5fc1 30% ,#5e2f83  );">
                            <form  method="GET"  id="MyForm" action="./Report1" >
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">


                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style=" color: #ffffff;" >ID : </label>
                                                <input type="text" name="vID" id="vID"  style="border-radius: 5px; border: thin; color: #b356b5; width: 15%;text-align: center"> 
                                            </div>
                                            <br>
                                            <br>

                                            <hr>


                                        </div>
                                    </div>
                                </div>

                                <br>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <!--<button class="btn btn-success " style=" background: #aaf0d1; border-radius: 5px; border: thin; color: #b356b5; width: 25%" id="vSave" type="button" name="vSave" >SAVE</button>-->
                                            </div>

                                            <br> 
                                            <br> 

                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <button class="btn btn-danger " style=" background: #red; border-radius: 5px; border: thin; color: #ffffff; width: 25%" id="vCANCEL" type="button" name="vCANCEL" >CANCEL ID</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br>
                            </form>
                        </div>
                    </div>
                </div> 
            </div> 
        </div>
    </body>



</html>

<script>






    $("#vCANCEL").click(function (e) {
        var code = document.getElementById("vID").value;
        if (window.confirm(`Are you sure to Cancel ID : ` + code + " ?")) {

            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var ID = document.getElementById("vID").value
            var username =  "<%out.print(session.getAttribute("username"));%>"
            
            

           alert(cono + divi + ID + username )

            $.ajax({
                type: "GET",
                url: "./Sync",
                async: false,
                data: {
                    page: "CancelID",
                    cono: cono,
                    divi: divi,
                    id:  ID,
                    username: username,
                 
                },
                success: function (result) {

                    alert("CANCELED");
                }
            });
        }

    });








</script>

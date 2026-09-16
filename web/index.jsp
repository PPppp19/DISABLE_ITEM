<%-- 
    Document   : index
    Created on : Jun 14, 2019, 9:32:52 AM
    Author     : Wattana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        if (session.getAttribute("cono") == null) {
            response.sendRedirect("login.jsp");

        }

        String username1 = (String) session.getAttribute("username");
        String locations1 = (String) session.getAttribute("locations");

    %>
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
        <script src="assets/jsgrid-1.5.3/src/jsgrid.core.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.validation.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.load-indicator.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.load-strategies.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.sort-strategies.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.field.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.text.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.number.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.select.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.checkbox.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.control.js"></script>
        <link rel="icon" type="image/icon" href="images/duck.jpg" />
        <script src='assets/select2/select2.min.js' type='text/javascript'></script>
        <link href='assets/select2/select2.min.css' rel='stylesheet' type='text/css'>

        <title>DISABLE ITEM  </title>
    </head>
    <body style="background-color: #999999">
        <div>
            <nav class="navbar navbar-default navigation-clean" style="background-color: white" >
                <div class="container">
                    <div class="navbar-header"><a class="navbar-brand" href="./" style="color: black;font-weight: bold">DISABLE ITEM : <font color="#0E5EDC" style="font-size: 16px"><%=username1%> :</font> <font color="#FF0000" style="font-size: 16px"> </font></a><button data-toggle="collapse" class="navbar-toggle collapsed" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button></div>
                    <div class="collapse navbar-collapse" id="navcol-1">
                        <ul class="nav navbar-nav navbar-right">
                            <!--                            <li role="presentation"></li>
                                                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./"style="color: black">Programs&nbsp;<span class="caret"></span></a>
                                                            <ul class="dropdown-menu" role="menu">
                                                                <li role="presentation"><a class='dropdown-item'  href="?page=BMP01">Upload Data</a></li>
                                                                <li role="presentation"><a class='dropdown-item'  href="?page=BMP02"></a></li>
                                                            </ul>
                                                        </li>-->
                            <!--                            <li class="nav-item " >
                                                            <a class="nav-link" href="?page=BMP01" style="font-weight: bold;color: #0b2e13;font-size: 16px">Upload Data</a>
                                                        </li> -->


                            <li class="dropdown"  ><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./" style="font-weight: bold;color: #0b2e13;font-size: 16px">Upload Disable File&nbsp;<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li class="nav-item " >
                                        <a class="nav-link" id="comzone1" href="?page=BMP01" style="font-weight: bold;color: #0b2e13;font-size: 16px">Upload Data </a>
                                    </li> 
<!--                                    <li class="nav-item " >
                                        <a class="nav-link" id="comzoneWTF1" href="?page=BMP01WTF" style="font-weight: bold;color: #0b2e13;font-size: 16px">Upload Data WTF</a>
                                    </li> 
                                    <li class="nav-item " >
                                        <a class="nav-link" id="comzoneNSD1" href="?page=BMP01NSD" style="font-weight: bold;color: #0b2e13;font-size: 16px">Upload Data NSD</a>
                                    </li> -->
                                    <!--                                    <li class="nav-item " >
                                                                            <a class="nav-link" href="?page=BMP02" style="font-weight: bold;color: #0b2e13;font-size: 16px">Create Receipt</a>
                                                                        </li> -->
                                </ul>
                            </li>


<!--                            <li class="dropdown"  ><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./" style="font-weight: bold;color: #0b2e13;font-size: 16px">Create Receipt&nbsp;<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li class="nav-item " >
                                        <a class="nav-link" id="comzone" href="?page=newBMP02" style="font-weight: bold;color: #0b2e13;font-size: 16px">New Create Receipt</a>
                                    </li>
                                    <li class="nav-item " >
                                        <a class="nav-link" id="comzoneWTF"  href="?page=BMP02WTF" style="font-weight: bold;color: #0b2e13;font-size: 16px">New Create Receipt WTF</a>
                                    </li> 

                                    <li class="nav-item " >
                                        <a class="nav-link" id="comzoneNSD"  href="?page=BMP02NSD" style="font-weight: bold;color: #0b2e13;font-size: 16px">New Create Receipt NSD</a>
                                    </li> 
                                                                        <li class="nav-item " >
                                                                            <a class="nav-link" href="?page=BMP02" style="font-weight: bold;color: #0b2e13;font-size: 16px">Create Receipt</a>
                                                                        </li> 
                                </ul>
                            </li>-->

<!--                            <li class="nav-item " >
                                <a class="nav-link" href="?page=BMP03" style="font-weight: bold;color: #0b2e13;font-size: 16px">Create Report</a>
                            </li>
                                                        <li class="nav-item " >
                                                            <a class="nav-link" href="?page=BMP04" style="font-weight: bold;color: #0b2e13;font-size: 16px">Edit Receipt</a>
                                                        </li>

                            <li class="dropdown" id="pppart" style=" visibility: hidden" ><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./" style="font-weight: bold;color: #0b2e13;font-size: 16px">Edit Receipt&nbsp;<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li role="presentation"><a class='dropdown-item' id="addbm"  href="?page=BMP04"> Add Receipt(BANK MAPPING)</a></li>
                                    <li role="presentation" id="editzone" style=" visibility: hidden;" ><a class='dropdown-item'  href="?page=BMP05"> Add USER (RECEIPT_REPORT)</a></li>
                                    <li role="presentation" id="editzone"  ><a class='dropdown-item' href="?page=BMP05"> Add USER (RECEIPT_REPORT)</a></li>
                                    <li role="presentation" id="editzone1"  ><a class='dropdown-item'  href="?page=ULRC01"> UNLOCK INVOICE</a></li>
                                    <li role="presentation" id="editzone2"  ><a class='dropdown-item'  href="?page=AddStatement"> ADD STATEMENT</a></li>
                                    <li role="presentation" id="editzone3"  ><a class='dropdown-item'  href="?page=Fix_RCNO"> FIX RCNO</a></li>
                                    <li role="presentation" id="editzone4"  ><a class='dropdown-item'  href="?page=GENRC"> GEN RCNO</a></li>
                                    <li role="presentation" id="editzone5"  ><a class='dropdown-item'  href="?page=CANCELID">CANCEL ID</a></li>
                                    <li role="presentation" id="editzone5"  ><a class='dropdown-item'  href="?page=RETURNBM">RETURN BM</a></li>




                                </ul>
                            </li>-->
                            <!--                           <li class="dropdown"  ><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./" style="font-weight: bold;color: #0b2e13;font-size: 16px">(OLD)____Create Receipt&nbsp;<span class="caret"></span></a>
                                                            <ul class="dropdown-menu" role="menu">
                            
                                                                <li class="nav-item " ><a class="nav-link" href="?page=BMP02" style="font-weight: bold;color: #0b2e13;font-size: 16px">(OLD)_____Create Receipt</a>
                                                               </li> 
                                                            </ul>
                                                        </li>-->
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    <form action="Logout" method="POST">
                                        <input class="alert-danger" type="submit" value="Sign Out">
                                    </form>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>
                </div>
            </nav>
        </div>
        <section id="programs">
            <div class='centerDiv' id="vSection"></div>
        </section>
    </body>
</html>

<script type="text/javascript">

    <% if (request.getParameter("page") != null) {%>
    $("#vSection").load("./programs/<%=request.getParameter("page").toString()%>.jsp");
    <%  } else {  %>
//    $("#vSection").load("./programs/BMP01.jsp");

    <% }%>

    $('.dropdown-item').on('click', function () {
        console.log(this.name);
        $("#vSection").load("./programs/" + this.name + ".jsp");
    });



    $(document).ready(function () {

        var username = "<%out.print(session.getAttribute("username"));%>";
        var cono = "<%out.print(session.getAttribute("cono"));%>";
        var com = document.getElementById("comzone");
        var comWTF = document.getElementById("comzoneWTF");
        var com1 = document.getElementById("comzone1");
        var comWTF1 = document.getElementById("comzoneWTF1");

        var comNSD = document.getElementById("comzoneNSD");
        var comNSD1 = document.getElementById("comzoneNSD1");
        var ez = document.getElementById("editzone");
        var pp = document.getElementById("pppart");

        var ep = document.getElementById("addbm");



//         var ez1 = document.getElementById("editzone1");
//        var DH = document.getElementById("DEPTHEAD");

        if (cono === "10") {
            com.style.visibility = "Visible";
            comWTF.style.visibility = "Hidden";
            comNSD.style.visibility = "Hidden";

            com1.style.visibility = "Visible";
            comWTF1.style.visibility = "Hidden";
            comNSD1.style.visibility = "Hidden";

        } else if (cono === "600")
        {

            comWTF.style.visibility = "Visible";
            com.style.visibility = "Hidden";
            comNSD.style.visibility = "Hidden";


            comWTF1.style.visibility = "Visible";
            com1.style.visibility = "Hidden";
            comNSD1.style.visibility = "Hidden";

        } else if (cono === "500")
        {

            comWTF.style.visibility = "Hidden";
            com.style.visibility = "Hidden";

            comNSD.style.visibility = "Visible";

            comWTF1.style.visibility = "Hidden";
            com1.style.visibility = "Hidden";

            comNSD1.style.visibility = "Visible";
        }

        if (username === "PHONGS_PHO" || username === "NITTAY_KOM" || username === "SANTIM_PUP"|| username === "WANIDA_KOS"   ||username === "PRANEE_KHA" ||username === "TAWA_HOA" || username === "WEETAR_KIT")
        {
            ez.style.visibility = "Visible";

            pp.style.visibility = "Visible";



//             ez1.style.visibility = "Visible";

        } else {
//            Checkpriority(username);
//            CheckpriorityAPV(username);
        }

        if (username === "PHONGS_PHO" || username === "SANTIM_PUP" || username === "TAWA_HOA" ||username === "PRANEE_KHA") {
            ep.style.visibility = "Visible";
        } else {
            ep.style.visibility = "Hidden";

        }

    });


    function Check() {

    }

</script>
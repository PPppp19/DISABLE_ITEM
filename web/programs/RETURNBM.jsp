<%-- 
    Document   : RETURNBM
    Created on : Sep 12, 2023, 1:25:24 PM
    Author     : Phongsathon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <style>

        .center {
            margin: auto;
            width: 50%;
            padding: 10px;
        }


    </style>
    <body>


        <div class="center">


            <div class="center">
                <h3 for="ID">ID :</h3>
                <input type="text" class="form-control text-center"  id="vID" name="vID" maxlength="8" >
            </div>
            <div class="center">
                <button class="btn btn-danger " id="vRETURN" name="vRETURN" type="button">RETURN ID</button>
            </div>
        </div>
    </body>
</html>

<script>



    $("#vRETURN").click(function (e) {


        var cono = <%out.print(session.getAttribute("cono"));%>
        var divi = <%out.print(session.getAttribute("divi"));%>
            $.ajax({
                url: './Sync',
                type: 'GET',
                dataType: 'json',
                data: {
                    page: "RETURNBM",
                    CONO: cono,
                    DIVI: divi,
                    ID: $("#vID").val()
                   
                },
                async: false
            }).done(function (response) {
                
                alert(response); 
            });

    });


</script> 

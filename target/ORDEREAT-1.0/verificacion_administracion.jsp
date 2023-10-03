<%-- 
    Document   : verificacion_administracion
    Created on : 21/11/2021, 12:27:39 AM
    Author     : MARIA DEL REFUGIO
--%>

<%@page import="Modelo.MPagos"%>
<%@page import="Modelo.Tokens"%>
<%@page import="Modelo.MNegocio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    MNegocio neg = new MNegocio();
    String nombre="";
    HttpSession sesionneg = request.getSession();
    neg = (MNegocio) sesionneg.getAttribute("Negocio");
    if(neg == null){
    
    %>
    
    <jsp:forward page="error.html">
        <jsp:param name="error" value="Es obligatorio Identificarse" />
        
    </jsp:forward>
    
    <%    
    }
    MPagos pag = (MPagos) sesionneg.getAttribute("Pagos");
    if(pag == null){
        %>
    
    <jsp:forward page="error_neg.jsp">
        <jsp:param name="error" value="" />
        
    </jsp:forward>
    
    <%  
    }
    %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Alatsi&family=Nunito+Sans&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="isotipo.png"> 
    <link rel="stylesheet" href="CSS/paginas.css">
    <link rel="stylesheet" href="CSS/sidebar.css">
    <link rel="stylesheet" href="CSS/agregarp.css">
    <title>Token - OrderEat</title>
</head>
<body>
    <div class="home_content">
        <a href="reportes.jsp"><i class='regreso bx bx-left-arrow-alt'><p class="at">Regresar</p></i></a> 
    
        <br>
        <br>
        <br>
        <br>
        <div class="container">
            <div class="title">Token de confirmación</div>
            <div class="content">
              <form method="post" action="Validar_Token_Administracion">
                <div class="user-details">
                
                  <div class="input-box">
                    <span class="details"></span>
                    <input type="text" name="Token" placeholder="Ingresa la clave de seguridad que se envio al correo" required>
                  </div>
                </div>
                <div class="button" class="button_plus">
                  <input type="submit" value="Entrar">
                </div>
              </form>
            </div>
        </div>
        

    </div>
</body>
</html>
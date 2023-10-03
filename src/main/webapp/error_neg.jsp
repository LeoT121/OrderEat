
<%@page import="Modelo.MNegocio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    MNegocio neg = new MNegocio();
    String nombre="";
    HttpSession sesionneg = request.getSession();
    neg = (MNegocio) sesionneg.getAttribute("Negocio");
    if(neg==null){
    
    %>
    
    <jsp:forward page="error.html">
        <jsp:param name="error" value="Es obligatorio Identificarse" />
        
    </jsp:forward>
    
    <%    
    }
    nombre = neg.getNomneg_mneg();
    %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="CSS/pag-agregar.css">
    <link rel="stylesheet" href="CSS/xx.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;500;700&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="IMG/isotipo.png"> 
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
    
    <title>Error</title>
</head>
<body>
    <div class="home_content">
        <a href="ordenes.jsp"><i class='regreso bx bx-left-arrow-alt'><p class="at"></p></i></a> 
        <div class="container">
            <div class="title">Error</div>
            <br>
            <br>
            <div class="content">
                <form action="ordenes.jsp">
                    <div class="user-details">
                        <div class="input-box">
                            <br>
                            <br>
                            <br>
                            <br>
                            <center><span class="details">Ocurrió un error inesperado, por favor vuelve a intentarlo de nuevo</span></center>
                        </div>
                        <br><br><br>
                        <div class="input-box">
                            <center><img src="IMG/Error.jpeg" alt="Error" width="230px" height="200px">     </center>                               
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
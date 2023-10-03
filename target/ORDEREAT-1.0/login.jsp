
<%@page import="Modelo.MNegocio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    MNegocio neg = new MNegocio();
    String nombre="";
    HttpSession sesionneg = request.getSession();
    neg = (MNegocio) sesionneg.getAttribute("Negocio");
    if(neg != null){
    
    %>
    
    <jsp:forward page="ordenes.jsp">
        <jsp:param name="" value="" />
        
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
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;500;700&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="IMG/isotipo.png"> 
    <link rel="stylesheet" href="CSS/login.css">
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="JS/Validar_Login.js"></script>   

    <title>Login</title>
</head>
<body>
    <div class="l-form">

        <div class="form">
            <form method="post" action="Login_Negocio" class="form__content">
                <a href="index.html"><i class='regreso bx bx-left-arrow-alt'></i></a>
                <h1 class="form__title">Iniciar Sesión</h1>

                <div class="form__div form__div-one">
                    <div class="form__icon">
                        <i class='bx bx-user-circle'></i>
                    </div>

                    <div class="form__div-input">
                        <label for="" class="form__label">Nombre de Usuario</label>
                        <input type="text" class="form__input" id="Nombre_Usuario" name="Nombre_Usuario" required>
                    </div>
                </div>

                <div class="form__div">
                    <div class="form__icon">
                        <i class='bx bx-lock' ></i>
                    </div>

                    <div class="form__div-input">
                        <label for="" class="form__label">Contraseña</label>
                        <input type="password" class="form__input" id="Password" name="Password" required>
                    </div>
                </div>
                <br>
                <br>
                <button class="form__button">Entrar</button> 
                <a href="registrar.jsp" class="form__reg">Crear cuenta</a>
            </form>
        </div>

    </div>

    <!--===== MAIN JS =====-->
    <script src="JS/login.js"></script>

    <script>
        /*===== FOCUS =====*/
    const inputs = document.querySelectorAll(".form__input")

    /*=== Add focus ===*/
    function addfocus(){
        let parent = this.parentNode.parentNode
        parent.classList.add("focus")
    }

    /*=== Remove focus ===*/
    function remfocus(){
        let parent = this.parentNode.parentNode
        if(this.value == ""){
            parent.classList.remove("focus")
        }
    }

    /*=== To call function===*/
    inputs.forEach(input=>{
        input.addEventListener("focus",addfocus)
        input.addEventListener("blur",remfocus)
    })
    </script>

</body>
</html> 
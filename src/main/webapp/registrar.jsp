
<%@page import="Modelo.MNegocio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    MNegocio neg = new MNegocio();
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
    <script src="JS/Validar_Registro.js"></script>
    <title>Crear Cuenta</title>
</head>
<body>
    <div class="l-form">
        <div class="form">
            <form method="post" action="Registro_Negocio" class="form__content">
                <a href="index.html" class="atras"><i class='regreso bx bx-left-arrow-alt'></i></a>
                <h1 class="form__title">Crear Cuenta</h1>
    
                <div class="form__div">
                    <div class="form__icon">
                        <i class='bx bx-dish' ></i>
                    </div>
      
                    <div class="form__div-input">
                        <label for="" class="form__label">Nombre del Negocio</label>
                        <input type="text" class="form__input" id="Nombre_Negocio" name="Nombre_Negocio" required>
                    </div>
                  </div>
    
                <div class="form__div">
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
                        <i class='bx bx-mail-send' ></i>
                    </div>
    
                    <div class="form__div-input">
                        <label for="" class="form__label">Correo Gmail</label>
                        <input type="email" class="form__input" id="Correo_Negocio" name="Correo_Negocio" required>
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
    
                <div class="form__div form__div-last">
                    <div class="form__icon">
                        <i class='bx bx-lock' ></i>
                    </div>
      
                    <div class="form__div-input">
                        <label for="" class="form__label">Confirmar contraseña</label>
                        <input type="password" class="form__input" id="Confirmar_Password" name="Confirmar_Password" required>
                    </div>
                  </div>
    
                <br>
                <input type="checkbox" class="check" id="Confirmar_Aviso" name="Confirmar_Aviso" required>
                <a href="priv.html" target="_blank" class="form__forgot">He leído el Aviso de Privacidad</a>
    
                <button class="form__button">Registarse</button>
                <a href="login.jsp" class="form__reg">Ya tengo cuenta</a>
            </form>
        </div>
    
    </div>
    
    <!--===== MAIN JS =====-->
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


    <!--===== MAIN JS =====-->
    <script src="JS/login.js"></script>
    
</body>
</html>
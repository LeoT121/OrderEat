





<%@page import="Modelo.Colonias"%>
<%@page import="Modelo.Municipios"%>
<%@page import="Modelo.MNegocio"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    MNegocio neg = new MNegocio();
    HttpSession sesionneg = request.getSession();
    neg = (MNegocio) sesionneg.getAttribute("Negocio");
    if(neg==null){
    
    %>
    
    <jsp:forward page="error.html">
        <jsp:param name="error" value="Es obligatorio Identificarse" />
        
    </jsp:forward>
    
    <%    
    }
    Municipios mun = new Municipios();
    mun = (Municipios) sesionneg.getAttribute("Delegacion");
    if(mun == null){
        %>
    
    <jsp:forward page="error_neg.jsp">
        <jsp:param name="error" value="Es obligatorio Identificarse" />
        
    </jsp:forward>
    
    <%   
    }
    Vector<Colonias> tiempos = new Vector<Colonias>();
    Colonias tiem = new Colonias();
    tiempos = tiem.Col(mun.getId());
    if(tiempos == null){
        %>
    
    <jsp:forward page="error_neg.jsp">
        <jsp:param name="error" value="Es obligatorio Identificarse" />
        
    </jsp:forward>
    
    <%  
    }
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
        <script src="JS/Validar_Orden.js"></script>
        <title>Agregar Cliente</title>
        <style>
            .title{
                margin-top: 10px !important;
            }
        </style>
    </head>
    <body>
        <script>
            $(document).ready(function() {
                $('.js-example-basic-single').select2();
            });
        </script>
        
        <header class="header">
            <div class="header__container">
                <img src="IMG/isotipo.png" alt="logo" class="header__img">
                <a class="atras" href="cliente.jsp"><i class='bx bx-chevron-left atras'></i></a>
                
                <a class="header__logo">Cliente</a>

    
                <div class="header__toggle">
                    <i class='bx bx-menu' id="header-toggle"></i>
                </div>
            </div>
        </header>

        <!--========== NAV ==========-->
         <!--========== CAmbiar terminación de las páginas ==========-->
        <div class="nav" id="navbar">
            <nav class="nav__container">
                <div>
                    <a href="ordenes.jsp" class="nav__link nav__logo">
                        <i class='bx bx-chevron-right nav__icon' ></i>
                        <span class="nav__logo-name"><%=neg.getNomneg_mneg()%></span>
                    </a>
    
                    <div class="nav__list">
                        <div class="nav__items">
                            <h3 class="nav__subtitle">Modulos</h3>
    
                            <a href="ordenes.jsp" class="nav__link active">
                                <i class='bx bx-home nav__icon' ></i>
                                <span class="nav__name">Ordenes</span>
                            </a>

                            <a href="platillos.jsp" class="nav__link">
                                    <i class='bx bx-dish nav__icon' ></i>
                                    <span class="nav__name">Platillos</span>
                            </a>

                            <a href="inventario.jsp" class="nav__link">
                                <i class='bx bx-package nav__icon' ></i>
                                <span class="nav__name">Inventario</span>
                            </a>
                        </div>
    
                        <div class="nav__items">
                            <h3 class="nav__subtitle">Estadísticas</h3>

                            <a href="historial.jsp" class="nav__link">
                                <i class='bx bx-pie-chart-alt nav__icon' ></i>
                                <span class="nav__name">Historial</span>
                            </a>
                            <a href="reportes.jsp" class="nav__link">
                                <i class='bx bx-bar-chart-alt-2 nav__icon' ></i>
                                <span class="nav__name">Reportes</span>
                            </a>
                        </div>
                    </div>
                </div>

                <a href="Logout_Negocio" class="nav__link nav__logout">
                    <i class='bx bx-log-out nav__icon' ></i>
                    <span class="nav__name">Cerrar Sesión</span>
                </a>
            </nav>
        </div>

        <!--========== CONTENTS ==========-->
        <main>
            <section>
                <div class="container">
                    <div class="form-container">
                        <h1 class="title">Agregar Cliente</h1>
                            <form method="post" action="Agregar_Cliente" class="form">
                                <div>
                                    <label for="name" class="label">Nombre</label>
                                    <input type="text"  placeholder="" id="Cliente" name="Cli" class="input input-name" required>

                                    <label for="name" class="label">Número de Celular</label>
                                    <input type="tel"  placeholder="" id="Telefono" name="Tel" class="input input-name" required>

                                    <label for="name" class="label">Calle</label>
                                    <input type="text" placeholder="" id="Calle" name="Cal" class="input input-name" required>

                                    <label for="name" class="label">Número Exterior</label>
                                    <input type="number" placeholder="" id="N_Ext" name="N_Ext" class="input input-name" required>

                                    <label for="name" class="label">Número Interior</label>
                                    <input type="number" placeholder="" id="N_Int" name="N_Int" class="input input-name" required>
                                    
                                    <label for="name" class="label">Colonia</label>
                                    <select class="select" name="Col" id="Col" style="width: 300px;">
                                        <option value=""> Selecciona una Opción</option>
                                    <%                            
                                        int longitud = tiempos.size();
                                        for(int i = 0; i < longitud; ++i){
                                        Colonias act = tiempos.elementAt(i);
                                    %>      
                                        <option value = "<%=i%>">
                                        <%=act.getNombre()%>
                                        </option>
                                    <%
                                    }
                                    %>

                                    </select>

                                    <label for="name" class="label">Alcaldía</label>
                                    <input type="text" placeholder="<%=mun.getNombre()%>" readonly>

                                    <label for="name" class="label">Estado</label>
                                    <input type="text" placeholder="CDMX"  class="input input-name" readonly>
                                </div>
                                <input type="submit" value="Agregar" class="primary-button login-button">
                            </form>
                    </div>
                </div>
            </section>
        </main>

        <!--========== MAIN JS ==========-->
        <script src="JS/xx.js"></script>
    </body>
</html>
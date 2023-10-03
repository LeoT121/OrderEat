<%-- 
    Document   : agregarperdida.jsp
    Created on : 26/05/2022, 04:13:30 AM
    Author     : MARIA DEL REFUGIO
--%>

<%@page import="Modelo.MInsumos"%>
<%@page import="java.util.Vector"%>
<%@page import="Modelo.MNegocio"%>
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
        <title>Agregar Platillo</title>
        <style>
            .title{
                margin-top: 30px !important;
            }
        </style>
    </head>
    <body>
        <header class="header">
            <div class="header__container">
                <img src="IMG/isotipo.png" alt="logo" class="header__img">
                <a class="atras" href="inventario.jsp"><i class='bx bx-chevron-left atras'></i></a>
                
                <a class="header__logo">Ingredientes del Platillo</a>

    
                <div class="header__toggle">
                    <i class='bx bx-menu' id="header-toggle"></i>
                </div>
            </div>
        </header>

        <!--========== NAV ==========-->
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
    
                            <a href="ordenes.jsp" class="nav__link">
                                <i class='bx bx-home nav__icon' ></i>
                                <span class="nav__name">Ordenes</span>
                            </a>

                            <a href="platillos.jsp" class="nav__link">
                                    <i class='bx bx-dish nav__icon active' ></i>
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
                        <h1 class="title">Agregar Perdida</h1>
                            <form method="post" action="Agregar_Perdida" class="form">
                                <div>
                                    <label for="name" class="label" >Insumo</label>
                                    <select class="select" name="Insumo" id="Insumo">
                                        <option value="">Selecciona una opcion</option>
                                        <%
                                    Vector<MInsumos> insumos = new Vector<MInsumos>();
                                    MInsumos mins = new MInsumos();
                                    insumos = mins.Insumos(neg.getId_mneg());
                                    if(insumos != null){
                                        int ind = 0;
                                        for(MInsumos i : insumos){     
                                            String auxiliar = i.getNom_mins();
                                            if(i.getId_cuni() == 1){
                                                auxiliar += " (Kilogramos)";
                                            }
                                            else if(i.getId_cuni() == 2){
                                                auxiliar += " (Unidad)";
                                            }
                                            else{
                                                auxiliar += " (Litros)";
                                            }
                                            %>      
                                        <option value = "<%=ind%>">
                                            <%=auxiliar%>
                                        </option>
                                                <%
                                                ind ++;
                                            }
                                        }                                    
                                    %>
                                    </select>

                                    <label for="name" class="label">Cantidad</label>
                                    <input type="number" name="cantidad" step="any" id="cantidad" placeholder="" class="input input-name" required>
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

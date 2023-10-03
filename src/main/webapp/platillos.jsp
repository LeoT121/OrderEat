

<%@page import="Modelo.VWIPlat"%>
<%@page import="Modelo.Rel_Plat_Ins"%>
<%@page import="Modelo.Aux_Ins"%>
<%@page import="Modelo.MInsumos"%>
<%@page import="Modelo.VWPlatillo"%>
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
    Vector<Aux_Ins> vains = new Vector<Aux_Ins>();
    sesionneg.setAttribute("Vector_Aux_Insumos", vains);
    %>

<!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <link rel="stylesheet" href="CSS/xx.css">
        <link rel="stylesheet" href="CSS/tabla.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;500;700&display=swap" rel="stylesheet">
        <link rel="shortcut icon" href="IMG/isotipo.png"> 
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="JS/Validar_Editar_Eliminar_Platillo.js"></script>
        <title>Platillos</title>
        <style>
            button {
            cursor: pointer;
            border: none;
            outline: none;
            }

            .button {
            display: inline-block;
            background-color: var(--first-color);
            color: #fff;
            padding: 1rem 2rem;
            font-weight: bold;
            border-radius: .5rem;
            font-family: 'Quicksand';
            font-size: 14px;
            }

            .button:hover {
                box-shadow: 0px 15px 36px rgba(0,0,0,.15);
                color: #fff;
            }

            .sec-button{
                display: inline-block;
                background-color: white;
                border-color: #FFDE59;
                padding: 1rem 2rem;
                font-weight: bold;
                border-radius: .5rem;
                font-family: 'Quicksand';
                font-size: 12px;
            }
        </style>
    </head>
    <body>
        <header class="header">
            <div class="header__container">
                <img src="IMG/isotipo.png" alt="logo" class="header__img">

                <a class="header__logo">Platillos</a>

    
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

                            <a href="platillos.jsp" class="nav__link active">
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
                <center>
                    <a href="agregarplatillo.jsp">
                        <button class="button">Agregar Platillo</button>
                    </a>
                </center>
            </section>
            <br>
            <br>
            <table class="table">
                <thead>
                    <tr>
                        <th>Platillo</th>
                        <th>Tiempo</th>
                        <th>Descripción</th>
                        <th>Ingredientes</th>
                        <th>Cantidad</th>
                        <th>Precio</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    Vector<VWPlatillo> plat = new Vector<VWPlatillo>();   
                    Vector<VWIPlat> vrpi = new Vector<VWIPlat>();
                    VWIPlat crpi = new VWIPlat();
                    vrpi = crpi.DetallesPlat();
                    VWPlatillo cpla = new VWPlatillo();
                    int id_mneg = neg.getId_mneg();
                    plat = cpla.Platillos2(id_mneg);
                    sesionneg.setAttribute("Vector_Platillos", plat);
                    if(plat != null && vrpi != null){
                        int longitud = plat.size();
                        for(int i = 0; i < longitud; ++i){
                            VWPlatillo aux = plat.elementAt(i);
                            int aux1 = 0;
                            for(VWIPlat j : vrpi){
                                if(j.getId_cpla() == aux.getId_cpla()){
                                    aux1 ++;
                                }
                            }
                            %>


                      <tr>
                          <td rowspan="<%=aux1%>">   
                                <%=aux.getNom_cpla()%>
                            </td>   
                            <td rowspan="<%=aux1%>">
                                <%=aux.getNom_ctie()%>
                            </td>
                            <td rowspan="<%=aux1%>">           
                                <%=aux.getDes_cpla()%>
                            </td>    
                            <%
                            for(VWIPlat j : vrpi){
                                if(j.getId_cpla() == aux.getId_cpla()){
                                    %>
                                    <td>
                                        <%=j.getNom_cpla()%>
                                    </td>
                                    <td>
                                        <%=j.getCan_rpi()%>
                                    </td>
                            <%
                                    break;
                                }
                            }
                            %>
                                
                            <td rowspan="<%=aux1%>">           
                                <%=aux.getCos_cpla()%>
                            </td>
                      </tr>
                      <%
                      int contador = 0;
                            for(VWIPlat j : vrpi){
                                if(j.getId_cpla() == aux.getId_cpla()){
                                    if(contador > 0){
                                        %>
                                    <tr>
                                        <td>
                                        <%=j.getNom_cpla()%>
                                    </td>
                                    <td>
                                        <%=j.getCan_rpi()%>
                                    </td>
                                    </tr>                                    
                            <% 
                                    }
                                    
                                    contador ++;
                                }
                            }
                        }

                    }
                %>

                </tbody>
            </table>
        </main>

        <!--========== MAIN JS ==========-->
        <script src="JS/xx.js"></script>
    </body>
</html>
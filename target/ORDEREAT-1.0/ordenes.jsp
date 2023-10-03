<%@page import="Modelo.VWOrden"%>
<%@page import="Modelo.VCliente"%>
<%@page import="Modelo.VOrden"%>
<%@page import="Modelo.MNegocio"%>
<%@page import="Modelo.Orden_Platillos"%>
<%@page import="Modelo.DOrden"%>
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
    Vector<Orden_Platillos> voplat = new Vector<Orden_Platillos>();
    sesionneg.setAttribute("Vector_Orden", voplat);
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
        <title>Ordenes</title>
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

                <a class="header__logo">Ordenes</a>

    
                <div class="header__toggle">
                    <i class='bx bx-menu' id="header-toggle"></i>
                </div>
            </div>
        </header>

        <!--========== NAV ==========-->
        <div class="nav" id="navbar">
            <nav class="nav__container">
                <div>
                    <a href="" class="nav__link nav__logo">
                        <i class='bx bx-chevron-right nav__icon' ></i>
                        <span class="nav__logo-name"><%=neg.getNomneg_mneg()%></span>
                    </a>
    
                    <div class="nav__list">
                        <div class="nav__items">
                            <h3 class="nav__subtitle">Modulos</h3>
    
                            <a href="" class="nav__link active">
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
                <center>
                    <a href="cliente.jsp">
                        <button class="button">Agregar Orden</button>
                    </a>
                </center>
            </section>
            <br>
            <br>
            <table class="table">
                <thead>
                    <tr>
                        <th>Cliente</th>
                        <th>Dirección</th>
                        <th>Telefono</th>
                        <th>Cantidad</th>
                        <th>Platillo</th>
                        <th>Comentario</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>

                    
                      <tr>
                        <%
                        Vector<VWOrden> ordenes = new Vector<VWOrden>();
                        Vector<VOrden> det_orden = new Vector<VOrden>();
                        VWOrden aux_ord = new VWOrden();
                        VOrden aux_det = new VOrden();
                        ordenes = aux_ord.Ordenes_Actuales(neg.getId_mneg());
                        det_orden = aux_det.Detalle_Orden_Actuales(neg.getId_mneg());
                        int longitud1 = 0, longitud2 = 0;                               
                        if(ordenes != null && det_orden != null){
                            longitud1 = ordenes.size();
                            longitud2 = det_orden.size();
                            for(int i = 0; i < longitud1; ++i){
                                VWOrden act_ord = ordenes.elementAt(i);
                                int cantidad = 0;
                                for(int j = 0; j < longitud2; ++j){
                                VOrden act_det = det_orden.elementAt(j);
                                if(act_det.getId_mord() == act_ord.getId_mord()){
                                    cantidad ++;
                                }
                                
                            }
                                %>
                    <tr>
                        <td rowspan="<%=cantidad%>"><%=act_ord.getNom_mcli()%></td>
                        <%
                        String auxiliar = "";
                        auxiliar += act_ord.getCalle_dcli();
                        auxiliar += (" " + Integer.toString(act_ord.getNumext_dcli()));
                        if(act_ord.getNumint_dcli() != -1){
                            auxiliar += (" Int. " + Integer.toString(act_ord.getNumint_dcli()));
                        }
                        auxiliar += (" Col. " + act_ord.getNombre());
                        auxiliar += (" Alc. " + act_ord.getNom_municipio());
                        auxiliar += (" Est. CDMX");
                        auxiliar += (" CP. " + Integer.toString(act_ord.getCodigo_postal()));
                    %>
                        <td rowspan="<%=cantidad%>"><%=auxiliar%></td>
                        <td rowspan="<%=cantidad%>"><%=act_ord.getTel_dcli()%></td>
                        <%
                        for(int j = 0; j < longitud2; ++j){
                            VOrden act_det = det_orden.elementAt(j);
                            if(act_det.getId_mord() == act_ord.getId_mord()){
                                %>
                                <td>
                                    <%=act_det.getCant_dord() %>
                                </td>
                                <td>
                                    <%=act_det.getNom_cpla() %>
                                </td>
                        <%
                                break;
                            }
                                
                        }
                        %>      
                        <td><%=act_ord.getCom_mord()%></td>
                        <td><%="$" + act_ord.getCos_mord() %></td>
                    </tr>
                    <%
                        cantidad = 0;
                        for(int j = 0; j < longitud2; ++j){
                            VOrden act_det = det_orden.elementAt(j);
                            if(act_det.getId_mord() == act_ord.getId_mord()){
                                if(cantidad > 0){
                                    %>
                                    <tr><td>
                                    <%=act_det.getCant_dord() %>
                                </td>
                                <td>
                                    <%=act_det.getNom_cpla() %>
                                </td></tr>                                
                        <%
                                }                                
                                cantidad ++;
                            }
                                
                        }  
                            }
                        }
                    %>

                

                      </tr>
                </tbody>
            </table>
        </main>

        <!--========== MAIN JS ==========-->
        <script src="JS/xx.js"></script>
    </body>
</html>
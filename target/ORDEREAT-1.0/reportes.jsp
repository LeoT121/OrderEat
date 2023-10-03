<%@page import="Modelo.CPlatillo"%>
<%@page import="Modelo.MOrden"%>
<%@page import="Modelo.VWOrden"%>
<%@page import="Modelo.MPagos"%>
<%@page import="Modelo.VCliente"%>
<%@page import="Modelo.VOrden"%>
<%@page import="java.util.Vector"%>
<%@page import="Modelo.Tokens"%>
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
    int costos = 0;
    Vector<MPagos> pagos = new Vector<MPagos>();
    MPagos pag = new MPagos();
    pagos = pag.Pagos(neg.getId_mneg());
    Vector<VWOrden> ordenes = new Vector<VWOrden>();
    Vector<VOrden> det_orden = new Vector<VOrden>();
    VWOrden aux_ord = new VWOrden();
    VOrden cvord = new VOrden();
    ordenes = aux_ord.Ordenes_Historial(neg.getId_mneg());
    det_orden = cvord.Detalle_Orden_Historial(neg.getId_mneg());
    Vector<CPlatillo> vcpla = new Vector<CPlatillo>();
    CPlatillo ccpla = new CPlatillo();
    vcpla = ccpla.Platillos(neg.getId_mneg());
    Vector<Integer> Cantidad_Necesaria = new Vector<Integer>();
    float promedio = 0, total = 0;
    int longitud = 0;
    if(ordenes != null){
        longitud = ordenes.size();
        for(VWOrden i : ordenes){
            total += Float.parseFloat(i.getCos_mord());           
        }
        if(longitud > 0){
            promedio = total;
            promedio /= (float) longitud;
        }
    }
    String prom = (promedio == 0.0) ? "N/A" : "$" + Float.toString(promedio);
    String tot = (total == 0.0) ? "N/A" : "$" + Float.toString(total);
    MOrden mord = new MOrden();
    int orden = longitud;
    %>
<!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <link rel="stylesheet" href="CSS/ordereat-inicio.css">
        <link rel="stylesheet" href="CSS/xx.css">
        <link rel="stylesheet" href="CSS/tabla.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;500;700&display=swap" rel="stylesheet">
        <link rel="shortcut icon" href="IMG/isotipo.png"> 
        <title>Reportes</title>
        <style>
            section{
                padding-top: 0px !important;
            }

            .main{
                overflow: scroll;
            }

            .pagos a{
                text-decoration: none;
                color: hsl(355, 4%, 35%);
                border-color: hsl(355, 4%, 35%);
            }

            button{
                padding: 2px;
            }
        </style>
    </head>
    <body>
        <header class="header">
            <div class="header__container">
                <img src="IMG/isotipo.png" alt="logo" class="header__img">

                <a class="header__logo">Reportes</a>

    
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
                            <a href="reportes.jsp" class="nav__link active">
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
            <section class="gift section container" id="gift">

                <div class="gift__container grid">
                    <article class="gift__card">
                        <img src="assets/img/gift1.png" alt="" class="gift__img">
                        <i class='bx bx-dollar gift__icon'></i>
                        
                        <h3 class="gift__price">Venta Total</h3>
                        <span class="gift__title"><%=tot%></span>
                    </article>

                    <article class="gift__card">
                        <img src="assets/img/gift2.png" alt="" class="gift__img">
                        <i class='bx bx-receipt gift__icon'></i>
                        
                        <h3 class="gift__price">Ordenes Entregadas</h3>
                        <span class="gift__title"><%=orden%></span>
                    </article>

                    <article class="gift__card">
                        <img src="assets/img/gift3.png" alt="" class="gift__img">
                        <i class='bx bx-user gift__icon'></i>
                        
                        <h3 class="gift__price">Cuenta Promedio</h3>
                        <span class="gift__title"><%=prom%>          
                        </span>
                    </article>
                </div>
            </section>


            <div class="pagos">
                <center>
                    <h2>Pagos</h2>
                    <button><a href="agregar_pago.jsp">Agregar Pago</a></button>
                    
                </center>
                <br>
                <table class="table">
                    <thead>
                        <tr>                     
                                <th>Pago</th>
                                <th>Comentario</th>
                                <th>Precio</th>                     
                            </tr>
                            </thead>
                            <tbody>
                            <%                        
                        if(pagos != null){
                            longitud = pagos.size();
                            for(int i = 0; i < longitud; ++i){
                                MPagos aux = pagos.elementAt(i);
                                costos -= Integer.parseInt(aux.getCos_mpag());
                                %>
                            <tr>
                                <td><%=aux.getTit_mpag()%></td>
                                <td><%=aux.getDes_mpag()%></td>
                                <td><%=aux.getCos_mpag()%></td>
                        </tr>
                        <%
                        }
                    }
                %>
                    </tbody>
                </table>
            </div>
        </main>
        <!--========== MAIN JS ==========-->
        <script src="JS/xx.js"></script>
    </body>
</html>
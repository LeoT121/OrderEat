<%-- 
    Document   : historial
    Created on : 28/11/2021, 03:49:59 PM
    Author     : MARIA DEL REFUGIO
--%>

<%@page import="Modelo.MCliente"%>
<%@page import="Modelo.VWOrden"%>
<%@page import="Modelo.VCliente"%>
<%@page import="Modelo.VOrden"%>
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
    Vector<VWOrden> ordenes = new Vector<VWOrden>();
    Vector<VOrden> det_orden = new Vector<VOrden>();
    VWOrden aux_ord = new VWOrden();
    VOrden aux_det = new VOrden();
    det_orden = aux_det.Detalle_Orden_Historial(neg.getId_mneg());
    String numero = (String) sesionneg.getAttribute("Busqueda");
    String numero2 = (String) sesionneg.getAttribute("Busqueda2");
    String numero3 = (String) sesionneg.getAttribute("Busqueda3");
    if(numero == null || numero2 == null || numero3 == null){
        ordenes = aux_ord.Ordenes_Historial(neg.getId_mneg());
    }
    else{
        boolean bandera = true;
        String regex_cos = "^[0-9]{1,4}$";
        int num, num2, num3;
        if(!numero.matches(regex_cos)){
            numero = "-1";
            num = -1;
        }
        else{
            num = Integer.parseInt(numero);
        }
        if(!numero2.matches(regex_cos)){
            numero2 = "-1";
            num2 = -1;
        }
        else{
            num2 = Integer.parseInt(numero2);
        }
        if(!numero3.matches(regex_cos)){
            numero3 = "-1";
            num3 = -1;
        }
        else{
            num3 = Integer.parseInt(numero3);
        }
        ordenes = aux_ord.Ordenes_Historial_Personalizado(neg.getId_mneg(), num, num2, num3);
               
    }   
    sesionneg.setAttribute("Busqueda", null);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
        <link rel="stylesheet" href="CSS/pag-agregar.css">
        <link rel="stylesheet" href="CSS/xx.css">
        <link rel="stylesheet" href="CSS/tabla.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;500;700&display=swap" rel="stylesheet">
        <link rel="shortcut icon" href="IMG/isotipo.png"> 
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>

        <title>Historial</title>
</head>
<body>
    <header class="header">
        <div class="header__container">
            <img src="IMG/isotipo.png" alt="logo" class="header__img">
            <a class="header__logo">Historial</a>
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
                        <a href="historial.jsp" class="nav__link active">
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
                    <h1 class="title">Seleccionar Cliente</h1>
                    <form method="post" class="form" action="Busqueda_Completa">
                        <div>
                            <span class="details">Seleccionar Dia</span>
                            <select class="select"  name="Dia" id="Dia">
                                <option value="">Todos los dias</option>
                                <%
                                    for(int i = 1; i <= 31 ; ++i){
                                %>      
                                <option value = "<%=i%>">
                                    <%=i%>
                                </option>
                                <%
                                    }                                   
                                %>
                            </select>
                        </div>
                        <div>
                            <span class="details">Seleccionar Mes</span>
                            <select class="select"  name="Mes" id="Mes">
                                <option value="">Todos los meses</option>                    
                                <option value="0">Enero</option>
                                <option value="1">Febrero</option>
                                <option value="2">Marzo</option>
                                <option value="3">Abril</option>
                                <option value="4">Mayo</option>
                                <option value="5">Junio</option>
                                <option value="6">Julio</option>
                                <option value="7">Agosto</option>
                                <option value="8">Septiembre</option>
                                <option value="9">Octubre</option>
                                <option value="10">Noviembre</option>
                                <option value="11">Diciembre</option>
                            </select>
                        </div>
                        <div>
                            <label for="name" class="label">Cliente</label>
                            <select class="select" name="Cli" id="Cli">
                                <option value="">Todos los Clientes</option>
                                <%
                                    Vector<MCliente> tiempos = new Vector<MCliente>();
                                    MCliente tiem = new MCliente();
                                    tiempos = tiem.Clientes(neg.getId_mneg());
                                    if(tiempos != null){
                                        int longitud = tiempos.size();
                                        for(int i = 0; i < longitud; ++i){
                                            MCliente act = tiempos.get(i);
                                %>
                                <option value = "<%=i%>">
                                    <%=act.getNom_mcli()%>
                                </option>
                                <%
                                        }
                                    }                                    
                                %>
                            </select>
                        </div>
                        <input type="submit" value="Buscar" class="primary-button login-button">
                    </form>
                </div>
            </div>
            <table class="table">
                <thead>
                    <tr>
                        <th>Cliente</th>
                        <th>Dirección</th>
                        <th>Cantidad</th>
                        <th>Platillo</th>
                        <th>Comentarios</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    int longitud1 = 0, longitud2 = 0;                               
                    VOrden act_det = new VOrden();
                    if(ordenes != null && det_orden != null){
                        longitud1 = ordenes.size();
                        longitud2 = det_orden.size();
                        for(int i = 0; i < longitud1; ++i){
                            VWOrden act_ord = ordenes.elementAt(i);
                            int cantidad = 0;
                            for(int j = 0; j < longitud2; ++ j){
                                act_det = det_orden.elementAt(j);
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
                <%
                            for(int j = 0; j < longitud2; ++j){
                                act_det = det_orden.elementAt(j);
                                if(act_det.getId_mord() == act_ord.getId_mord()){
                %>
                                    <td><%=act_det.getCant_dord()%></td>
                                    <td><%=act_det.getNom_cpla()%></td>
                <%
                                    break;
                                }
                            }
                %>                                        
                        <td rowspan="<%=cantidad%>"><%=act_ord.getCom_mord()%></td>
                        <td rowspan="<%=cantidad%>"><%="$" + act_ord.getCos_mord() %></td>
                    </tr>
                <%
                            cantidad = 0;
                            for(int j = 0; j < longitud2; ++j){
                                act_det = det_orden.elementAt(j);
                                if(act_det.getId_mord() == act_ord.getId_mord()){
                                    if(cantidad > 0){
                                    %>
                                    <tr>
                                           <td><%=act_det.getCant_dord()%></td>
                                    <td><%=act_det.getNom_cpla()%></td>
                                    </tr>                                    
                <%
                                    }
                                    cantidad ++;
                                }
                            }
                        }
                    }
                %>
                </tbody>
            </table>
        </section>
    </main>

        <!--========== MAIN JS ==========-->
        <script src="https://cdn.jsdelivr.net/npm/sortablejs@latest/Sortable.min.js"></script>

        <script src="JS/xx.js"></script>

</body>
</html>






<%@page import="Modelo.VWCliente"%>
<%@page import="Modelo.Colonias"%>
<%@page import="Modelo.Municipios"%>
<%@page import="Modelo.MCliente"%>
<%@page import="Modelo.CPlatillo"%>
<%@page import="Modelo.MNegocio"%>
<%@page import="Modelo.Orden_Platillos"%>
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
    voplat = (Vector<Orden_Platillos>) sesionneg.getAttribute("Vector_Orden");
    if(voplat == null){
        %>
    <jsp:forward page="error_neg.jsp">
        <jsp:param name="error" value="" />
        
    </jsp:forward>
<%
    }
    Vector<CPlatillo> plat = new Vector<CPlatillo>();
    CPlatillo pla = new CPlatillo();
    plat = pla.Platillos(neg.getId_mneg());
    if(plat == null){
        %>
    <jsp:forward page="error_neg.jsp">
        <jsp:param name="error" value="" />
        
    </jsp:forward>   
        <%
    }
    if(plat.size() == 0){
        %>
    <jsp:forward page="error_neg.jsp">
        <jsp:param name="error" value="" />
        
    </jsp:forward>   
        <%
    }
    VWCliente clie = (VWCliente) sesionneg.getAttribute("Cliente_Orden");
    if(clie == null){
        %>
    <jsp:forward page="error_neg.jsp">
        <jsp:param name="error" value="" />
        
    </jsp:forward>   
        <%
    }
    String num_int = "";
    if(clie.getNumint_dcli() != -1){
        num_int = Integer.toString(clie.getNumint_dcli());
    }
    String colonia = Integer.toString(clie.getCp());
    if(colonia.length() != 5){
        colonia = "0" + colonia;
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
        <link rel="stylesheet" href="CSS/tabla.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;500;700&display=swap" rel="stylesheet">
        <link rel="shortcut icon" href="IMG/isotipo.png"> 
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="JS/Validar_Orden2.js"></script> 
        <title>Agregar Orden</title>
        <style>
            .title{
                margin-top: 10px !important;
                text-align: end;
                color: hsl(355, 4%, 35%);
            }

            button {
            cursor: pointer;
            border: none;
            outline: none;
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
                <a class="atras" href="cliente.jsp"><i class='bx bx-chevron-left atras'></i></a>
                
                <a class="header__logo">Nueva Orden</a>

    
                <div class="header__toggle">
                    <i class='bx bx-menu' id="header-toggle"></i>
                </div>
            </div>
        </header>

        <!--========== NAV ==========-->
        <!--========== Cambiar terminología de archivos a .jsp ==========-->
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
                        <a href="agregar_platillo_orden.jsp"><h1 class="title"> + Agregar Platillo</h1></a>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Cantidad</th>
                                    <th>Platillo</th>
                                    <th>Precio</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%                      
                                int suma = 0;
                                if(voplat != null){
                                    int longitud = voplat.size();
                                    for(int i = 0; i < longitud; ++i){
                                        Orden_Platillos aux = voplat.elementAt(i);
                                        suma += aux.getCost();
                                %>

                                  <tr>
                                        <td><%=aux.getCant()%></td>
                                        <td><%=aux.getNom_pla()%></td>
                                        <td><%=aux.getCost()%></td>
                                        <!--Hay una acción para programar-->

                                        <td data-label="Acciones">
                                            <button class="sec-button">Eliminar</button>
                                        </td>
                                  </tr>
                                    <%
                                        }
                                    }                      
                                %>
                                    <tr>
                                        <td colspan="2">Total</td>
                                        <td><%=suma%></td>
                                    </tr>
                            </tbody>
                        </table>
                            <form action="#" class="form">
                                <div>

                                    <label for="name" class="label">Comentario</label>
                                    <input type="text" id="Comentario" name="Comentario" placeholder="" class="input input-name" required>

                                    <label for="name" class="label">Nombre</label>
                                    <input type="text" placeholder="<%=clie.getNom_mcli()%>" class="input input-name" readonly>

                                    <label for="name" class="label">Número de Celular</label>
                                    <input type="text" placeholder="<%=clie.getTel_dcli()%>" class="input input-name" readonly>

                                    <label for="name" class="label">Calle</label>
                                    <input type="text" placeholder="<%=clie.getCalle_dcli()%>" class="input input-name" readonly>

                                    <label for="name" class="label">Número Exterior</label>
                                    <input type="text" placeholder="<%=clie.getNumext_dcli()%>" class="input input-name" readonly>

                                    <label for="name" class="label">Número Interior</label>
                                    <input type="text" placeholder="<%=num_int%>" class="input input-name" readonly>

                                    <label for="name" class="label">Colonia</label>
                                    <input type="text" placeholder="<%=clie.getColonia()%>" class="input input-name" readonly>

                                    <label for="name" class="label">Alcaldía</label>
                                    <input type="text" placeholder="<%=clie.getMunicipio()%>" class="input input-name" readonly>

                                    <label for="name" class="label">Código Postal</label>
                                    <input type="text" placeholder="<%=colonia%>" class="input input-name" readonly>

                                    <label for="name" class="label">Estado</label>
                                    <input type="text" placeholder="CDMX" class="input input-name" readonly>

                                    
                                </div>
                                <input type="submit" value="Agregar Orden" class="primary-button login-button">
                            </form>
                    </div>
                </div>
            </section>
        </main>

        <!--========== MAIN JS ==========-->
        <script src="JS/xx.js"></script>
    </body>
</html>
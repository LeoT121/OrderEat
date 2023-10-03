


<%@page import="Modelo.Aux_Ins"%>
<%@page import="Modelo.MInsumos"%>
<%@page import="Modelo.CTiempo"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Vector"%>
<%@page import="Modelo.MNegocio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    MNegocio neg = new MNegocio();
    HttpSession sesionneg = request.getSession();
    neg = (MNegocio) sesionneg.getAttribute("Negocio");
    if(neg == null){
    
    %>
    
    <jsp:forward page="error.html">
        <jsp:param name="error" value="Es obligatorio Identificarse" />
        
    </jsp:forward>
    
    <%    
    }
    Vector<Aux_Ins> vains = new Vector<Aux_Ins>();
    vains = (Vector<Aux_Ins>) sesionneg.getAttribute("Vector_Aux_Insumos");
    if(vains == null) {
        %>
        <jsp:forward page="error_neg.jsp">
        <jsp:param name="error" value="Hubo un error inesperado" />
        
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
        <link rel="stylesheet" href="CSS/tabla.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;500;700&display=swap" rel="stylesheet">
        <link rel="shortcut icon" href="IMG/isotipo.png"> 
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
        <script src="JS/Validar_Platillo.js"></script>
        <title>Agregar Platillo</title>
        <style>
            .title{
                margin-top: 10px !important;
                text-align: end;
                color: hsl(355, 4%, 35%);
                font-size: 14px;
            }

            .sec{
                display: grid;
                place-content: center;
                font-weight: bold;
                font-family: 'Quicksand';
                font-size: 12px;
                text-decoration: none;
                color: hsl(355, 4%, 15%);
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
        <script>
            $(document).ready(function() {
                $('.js-example-basic-single').select2();
            });
        </script>
        <header class="header">
            <div class="header__container">
                <img src="IMG/isotipo.png" alt="logo" class="header__img">
                <a class="atras" href="platillos.jsp"><i class='bx bx-chevron-left atras'></i></a>
                
                <a class="header__logo">Platillo Nuevo</a>

    
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
                <div class="container">
                    <div class="form-container">
                        <a href="agregarinsumo_platillo.jsp"><h1 class="title"> + Agregar Ingrediente</h1></a>
                        
                        <!--Tabla para hacer el inventario :3-->
                        
                        
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Cantidad</th>
                                    <th>Ingrediente</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int longitud = vains.size();
                                    for(int i = 0; i < longitud; ++ i){
                                        Aux_Ins act = vains.elementAt(i);
                                        %>
                                <tr>
                                    <td data-label="Cantidad"><%=act.getCan_mins()%></td>
                                    <td data-label="Platillo"><%=act.getName_mins()%></td>
                                </tr>
                                <%
                                    }
                                %>                                  
                            </tbody>
                        </table>




                            <form method="post" action="Registro_Platillo" class="form">
                                <div>
                                    <label for="name" class="label">Nombre</label>
                                    <input type="text"  placeholder="" id="Nombre_Platillo" name="Nombre_Platillo" class="input input-name">

                                    <label for="name" class="label">Tiempo</label>
                                    <select class="select" name="Tiempo" required>
                                    <option value="">Selecciona una Opción</option>
                                        <%
                                    Vector<CTiempo> tiempos = new Vector<CTiempo>();
                                    CTiempo tiem = new CTiempo();
                                    tiempos = tiem.Tiempos(neg.getId_mneg());
                                    if(tiempos != null){
                                        longitud = tiempos.size();
                                        for(int i = 0; i < longitud; ++i){
                                            CTiempo act = tiempos.get(i);
                                            %>      
                                        <option value = "<%=i%>">
                                            <%=act.getNom_ctie()%>
                                        </option>
                                                <%
                                            }
                                        }                                    
                                    %>

                                    </select>

                                    <label for="name" class="label">Descripción</label>
                                    <input type="text"  placeholder="" id="Descripcion_Platillo" name="Descripcion_Platillo" class="input input-name" required>

                                    <label for="name" class="label">Precio</label>
                                    <input type="number" placeholder="" id="Precio_Platillo" name="Precio_Platillo" class="input input-name" required>
                                </div>
                                <input type="submit" value="Agregar" class="primary-button login-button">
                            </form>
                            <a href="agregartiempo.jsp" class="link">Agregar Tiempo</a>
                    </div>
                </div>
            </section>
        </main>

        <!--========== MAIN JS ==========-->
        <script src="JS/xx.js"></script>
    </body>
</html>
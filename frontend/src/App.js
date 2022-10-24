import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import './App.css';
import AddPersona from "./components/AddPersona";
import Persona from "./components/Persona";
import PersonaList from "./components/PersonaList";
import MocionList from "./components/MocionList";
import AddTipoMocion from "./components/AddTipoMocion";
import AddMocion from "./components/AddMocion";
function App() {
return(
<div>
<nav class="navbar navbar-expand navbar-dark bg-dark">
 <a href="/persona" className="navbar-brand">
 Control de mociones
</a>
 <div className="navbar-nav mr-auto">
 <li className="nav-item">
 <Link to={"/add"} className="nav-link">
 Agregar Persona
 </Link>
 </li>
 <li className="nav-item">
 <Link to={"/personas"} className="nav-link">
 Lista de Personas
 </Link>
</li>


<li className="nav-item">
 <Link to={"/add_Tipo_Mocion"} className="nav-link">
 Agregar Tipo de Moción
 </Link>
 </li>

 <li className="nav-item">
 <Link to={"/add_Mocion"} className="nav-link">
 Agregar Mocion
 </Link>
 </li>

<li className="nav-item">
 <Link to={"/mociones"} className="nav-link">
 Lista de Mociones
 </Link>
</li>






{/* <li className="nav-item">
 <Link to={"/log"} className="nav-link">
 Lista Log
 </Link>
</li> */}



 </div>
 </nav>

 <div className="container mt-3">
    <Routes>
    <Route path="/" element={<PersonaList/>} />
    <Route path="/personas" element={<PersonaList/>} />
    <Route path="/add" element={<AddPersona/>} />
    <Route path="/persona/:id" element={<Persona/>} />
    <Route path="/mociones" element={<MocionList/>} />
    <Route path="/add_Tipo_Mocion" element={<AddTipoMocion/>} />
    <Route path="/addMocion" element={<AddMocion/>} />
    {/*
    <Route path="/" element={<LogList/>} />
<Route path="/log" element={<LogList/>} /> */}
    </Routes> 
  </div> 

 </div>
);
}

export default App;


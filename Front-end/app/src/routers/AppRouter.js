import React from "react";
import { Routes, Route } from "react-router-dom";

// General
import Login from "../pages/Login";

// Satelite
import HomeSatelite from "../pages/user-Satelite/HomeSatelite";
import FormAmpollasSatelite from "../pages/user-Satelite/FormAmpollasSatelite";
import GestionDeStockSatelite from "../pages/user-Satelite/GestionDeStockSatelite";
import PedidoDeEmergenciaSatelite from "../pages/user-Satelite/PedidoDeEmergenciaSatelite";
import AtencionDePacientesSatelite from "../pages/user-Satelite/AtencionDePacientesSatelite";

// Central
import HomeCentral from "../pages/user-Central/HomeCentral";

export default function AppRouter() {
  const getRole = () => {
    let user = localStorage.getItem("user") || "{}";
    return JSON.parse(user).role;
  };

  return (
    <Routes>
      {getRole() === "user_satelite" && (
        <>
          <Route path="/" element={<HomeSatelite />} />
          <Route path="/HomeSatelite" element={<HomeSatelite />} />
          <Route
            path="/GestionDeStockSatelite"
            element={<GestionDeStockSatelite />}
          />
          <Route
            path="/AtencionDePacientesSatelite"
            element={<AtencionDePacientesSatelite />}
          />
          <Route
            path="/PedidoDeEmergenciaSatelite"
            element={<PedidoDeEmergenciaSatelite />}
          />
          <Route
            path="/FormAmpollasSatelite"
            element={<FormAmpollasSatelite />}
          />
        </>
      )}

      {getRole() === "user_admin" && (
        <>
          <Route path="/" element={<HomeCentral />} />
          <Route path="/HomeCentral" element={<HomeCentral />} />
        </>
      )}

      {getRole() !== "user_satelite" && getRole() !== "user_admin" && (
        <>
          <Route path="/" element={<Login />} />
          <Route path="/Login" element={<Login />} />
        </>
      )}
    </Routes>
  );
}

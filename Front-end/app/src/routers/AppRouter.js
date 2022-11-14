import React from "react";
import { Routes, Route } from "react-router-dom";

// General
import Login from "../pages/Login";

// Satelite
import HomeSatelite from "../pages/user-Satelite/HomeSatelite";
import FormAmpollasSatelite from "../pages/user-Satelite/FormAmpollasSatelite";
import GestionDeStockSatelite from "../pages/user-Satelite/GestionDeStockSatelite";
import PedidoDeEmergenciaSatelite from "../pages/user-Satelite/PedidoDeEmergenciaSatelite";
import PedidosSatelite from "../pages/user-Satelite/PedidosSatelite";

// Central
import HomeCentral from "../pages/user-Central/HomeCentral";
import PedidosCentral from "../pages/user-Central/PedidosCentral";
import CargarNuevoPedido from "../pages/user-Central/CargarNuevoPedido"

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
            path="/FormAmpollasSatelite"
            element={<FormAmpollasSatelite />}
          />
          <Route
            path="/PedidoDeEmergenciaSatelite"
            element={<PedidoDeEmergenciaSatelite />}
          />
          <Route
            path="/GestionDeStockSatelite"
            element={<GestionDeStockSatelite />}
          />
          <Route path="/PedidosSatelite" element={<PedidosSatelite />} />
        </>
      )}

      {getRole() === "user_admin" && (
        <>
          <Route path="/" element={<HomeCentral />} />
          <Route path="/HomeCentral" element={<HomeCentral />} />
          <Route path="/PedidosCentral" element={<PedidosCentral />} />
          <Route path="/CargarNuevoPedido" element={<CargarNuevoPedido />} />
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

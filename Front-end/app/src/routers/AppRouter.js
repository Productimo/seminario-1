import React from "react";
import { Router, Routes, Route } from "react-router-dom";
import HomeSatelite from "../pages/user-Satelite/HomeSatelite";

import FormAmpollasSatelite from "../pages/user-Satelite/FormAmpollasSatelite";
import PedidoDeEmergenciaSatelite from "../pages/user-Satelite/PedidoDeEmergenciaSatelite";
import AtencionDePacientesSatelite from "../pages/user-Satelite/AtencionDePacientesSatelite";
import GestionDeStockSatelite from "../pages/user-Satelite/GestionDeStockSatelite";

export default function AppRouter() {
  return (
    <Routes>
      <Route path="/" element={<HomeSatelite />} />
      <Route path="/HomeSatelite" element={<HomeSatelite />} />
      <Route path="/GestionDeStockSatelite" element={<GestionDeStockSatelite />} />
      <Route path="/AtencionDePacientesSatelite" element={<AtencionDePacientesSatelite />} />
      <Route path="/PedidoDeEmergenciaSatelite" element={<PedidoDeEmergenciaSatelite />} />
      <Route path="/FormAmpollasSatelite" element={<FormAmpollasSatelite />} />
    </Routes>
  );
}

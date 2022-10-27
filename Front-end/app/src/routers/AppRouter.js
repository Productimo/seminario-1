import React from "react";
import { Router, Routes, Route } from "react-router-dom";
import Home from "../pages/Home";
import Dashboard from "../pages/Dashboard";
import FormAmpollas from "../pages/FormAmpollas";

export default function AppRouter() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/Home" element={<Home />} />
      <Route path="/FormAmpollas" element={<FormAmpollas />} />
      <Route path="/Dashboard" element={<Dashboard />} />
    </Routes>
  );
}

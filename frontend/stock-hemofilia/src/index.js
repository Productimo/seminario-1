import App from "./App";
import React from "react";
import "./css/global.css";
import ReactDOM from "react-dom/client";
import { Route } from "react-router-dom";
import FormularioAmpolla from "./FormularioAmpolla.js";

// Router
<Route exact path="/test" element={<FormularioAmpolla />}></Route>;
// End of router

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

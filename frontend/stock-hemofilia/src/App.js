import FormularioAmpolla from "./FormularioAmpolla.js";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import "./css/App.css";

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route
            exact
            path="/formulario-ampollas"
            element={<FormularioAmpolla />}
          ></Route>
        </Routes>
      </div>
    </Router>
  );
}

export default App;

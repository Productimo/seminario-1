import { Box } from "@mui/system";
import React, { useState } from "react";
import Alert from "@mui/material/Alert";
import { Container } from "@mui/system";
import AppBar from "@mui/material/AppBar";
import { Typography } from "@mui/material";
import Toolbar from "@mui/material/Toolbar";
import Snackbar from "@mui/material/Snackbar";
import { Grid, TextField } from "@mui/material";
import LoadingButton from "@mui/lab/LoadingButton";

const bgStyle = {
  minWidth: "100vw",
  minHeight: "100vh",
  position: "relative",
};

const boxShadowStyle = {
  padding: "15px 40px",
  borderRadius: "10px",
  border: "1px solid var(--main-border)",
  boxShadow: "1px 1px 10px 0 rgb(0 0 0 / 15%)",
  backgroundColor: "white",
  width: "500px",
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  textAlign: "center",
};

export default function Login() {
  const [usuario, setUsuario] = useState();
  const [password, setPassword] = useState();
  const [loading, setLoading] = useState(false);

  const [alertOpen, setAlertOpen] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const login = () => {
    if (!usuario || !password) {
      setErrorMessage("Por favor complete todos los datos");
      return setAlertOpen(true);
    }

    setLoading(true);
    if (usuario === "satelite" && password === "satelite") {
      localStorage.setItem(
        "user",
        JSON.stringify({
          role: "user_satelite",
        })
      );
      window.location.href = "/";
    } else if (usuario === "admin" && password === "admin") {
      localStorage.setItem(
        "user",
        JSON.stringify({
          role: "user_admin",
        })
      );
      window.location.href = "/";
    } else {
      setErrorMessage("Credenciales incorrectas");
      setAlertOpen(true);
    }
    setLoading(false);
  };

  return (
    <Container sx={bgStyle}>
      <Box>
        <AppBar
          position="fixed"
          sx={{
            zIndex: (theme) => theme.zIndex.drawer + 1,
            backgroundColor: "teal",
          }}
        >
          <Toolbar>
            <Typography variant="h6" noWrap component="div">
              Factor VIII
            </Typography>
          </Toolbar>
        </AppBar>
      </Box>

      <Box sx={boxShadowStyle}>
        <Typography
          variant="h4"
          noWrap
          component="div"
          sx={{ marginBottom: "20px" }}
        >
          Iniciar Sesión
        </Typography>
        <Grid container spacing={2} columns={2}>
          <Grid item xs={2}>
            <TextField
              label="Usuario o correo"
              onChange={(e) => setUsuario(e.target.value)}
              value={usuario}
              type="text"
              sx={{ width: "100%" }}
            />
          </Grid>
          <Grid item xs={2}>
            <TextField
              label="Contraseña"
              onChange={(e) => setPassword(e.target.value)}
              value={password}
              type="password"
              sx={{ width: "100%" }}
            />
          </Grid>

          <Grid item xs={2} sx={{ textAlign: "center" }}>
            <LoadingButton
              variant="contained"
              onClick={login}
              loading={loading}
              sx={{
                width: "100%",
                backgroundColor: "teal !important",
              }}
            >
              INGRESAR
            </LoadingButton>
          </Grid>
        </Grid>
      </Box>
      <Snackbar
        anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
        open={alertOpen}
        autoHideDuration={3000}
        message="Note archived"
        onClose={() => setAlertOpen(false)}
      >
        <Alert severity="error" sx={{ width: "100%" }}>
          {errorMessage}
        </Alert>
      </Snackbar>
    </Container>
  );
}

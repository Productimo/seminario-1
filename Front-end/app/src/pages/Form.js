import uuid from "react-uuid";
import { Box } from "@mui/system";
import { Container } from "@mui/system";
import Alert from "@mui/material/Alert";
import React, { useState } from "react";
import Button from "@mui/material/Button";
import Snackbar from "@mui/material/Snackbar";
import MenuItem from "@mui/material/MenuItem";
import { Grid, TextField } from "@mui/material";
import BadgeIcon from "@mui/icons-material/Badge";
import MenuDrawer from "../components/MenuDrawer";
import { AccountCircle } from "@mui/icons-material";
import FormControl from "@mui/material/FormControl";
import VaccinesIcon from "@mui/icons-material/Vaccines";
import InputAdornment from "@mui/material/InputAdornment";
import { Divider, Typography, Select } from "@mui/material";
import LocalHospitalIcon from "@mui/icons-material/LocalHospital";

const boxShadowStyle = {
  padding: "10px 20px",
  borderRadius: "10px",
  border: "1px solid var(--main-border)",
  boxShadow: "1px 1px 10px 0 rgb(0 0 0 / 15%)",
  margin: "auto",
  marginTop: "80px",
};

export default function Form() {
  const [dni_paciente, setDNI] = useState();
  const [nombre_paciente, setNombrePaciente] = useState();
  const [cod_medicamento, setCodMedicamento] = useState("");
  const [hospital_urgencia, setHospitalUrgencia] = useState("");
  const [cant_ampollas, setCantAmpollas] = useState();
  const [comentarios, setComentarios] = useState();
  const [formulario, setFormulario] = useState({});

  const [alertOpen, setAlertOpen] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = () => {
    if (
      !dni_paciente ||
      !nombre_paciente ||
      !cod_medicamento ||
      !hospital_urgencia ||
      !cant_ampollas
    ) {
      setErrorMessage("Por favor complete todos los datos");
      return setAlertOpen(true);
    }
    setFormulario({
      // dni_paciente:dni_paciente,
      id_medico: uuid(),
      id_formulario: uuid(),
      nombre_paciente: nombre_paciente,
      cod_medicamento: cod_medicamento,
      hospital_urgencia: hospital_urgencia,
      cant_ampollas: cant_ampollas,
      comentarios: comentarios,
    });
  };

  console.log(formulario);

  return (
    <Container
      sx={{ display: "flex", minWidth: "100%", padding: "20px" }}
      disableGutters
    >
      <MenuDrawer />
      <Box style={boxShadowStyle}>
        <Typography
          variant="h5"
          component="div"
          sx={{ paddingBottom: "25px", paddingTop: "10px" }}
        >
          Ingreso de nuevo formulario
        </Typography>
        <Grid container spacing={2} columns={4}>
          <Grid item xs={2}>
            <TextField
              label="DNI paciente *"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <BadgeIcon sx={{ color: "teal" }} />
                  </InputAdornment>
                ),
              }}
              onChange={(e) => setDNI(e.target.value)}
              value={dni_paciente}
              type="number"
              sx={{ width: "100%" }}
            />
          </Grid>
          <Grid item xs={2}>
            <TextField
              label="Nombre paciente *"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <AccountCircle sx={{ color: "teal" }} />
                  </InputAdornment>
                ),
              }}
              variant="outlined"
              onChange={(v) => setNombrePaciente(v.target.value)}
              value={nombre_paciente}
              sx={{ width: "100%" }}
            />
          </Grid>
          <Grid item xs={4}>
            <Divider />
          </Grid>
          <Grid item xs={1.3333333}>
            <FormControl sx={{ minWidth: "100%" }} variant="outlined">
              <Select
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start">
                      <VaccinesIcon sx={{ color: "teal" }} />
                    </InputAdornment>
                  ),
                }}
                onChange={(v) => setHospitalUrgencia(v.target.value)}
                value={hospital_urgencia}
                sx={{ width: "100%" }}
                displayEmpty
              >
                <MenuItem value="" default disabled>
                  <em>Hospital en el que se atendió *</em>
                </MenuItem>
                <MenuItem value={"posadas"}>Hospital Posadas</MenuItem>
              </Select>
            </FormControl>
          </Grid>
          <Grid item xs={1.3333333}>
            <FormControl sx={{ minWidth: "100%" }} variant="outlined">
              <Select
                onChange={(v) => setCodMedicamento(v.target.value)}
                value={cod_medicamento}
                sx={{ width: "100%" }}
                displayEmpty
              >
                <MenuItem value="" default disabled>
                  <em>Código medicamento *</em>
                </MenuItem>
                <MenuItem value={"CHS0001"}>CHS0001</MenuItem>
              </Select>
            </FormControl>
          </Grid>
          <Grid item xs={1.3333333}>
            <TextField
              label="Cantidad de ampollas *"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <LocalHospitalIcon sx={{ color: "teal" }} />
                  </InputAdornment>
                ),
              }}
              variant="outlined"
              onChange={(v) => setCantAmpollas(v.target.value)}
              value={cant_ampollas}
              sx={{ width: "100%" }}
              type="number"
            />
          </Grid>
          <Grid item xs={4}>
            <TextField
              label="Comentarios"
              multiline
              rows={4}
              onChange={(v) => setComentarios(v.target.value)}
              value={comentarios}
              sx={{ width: "100%" }}
            />
          </Grid>
          <Grid item xs={4} sx={{ textAlign: "center" }}>
            <Button variant="contained" onClick={handleSubmit}>
              ENVIAR FORMULARIO
            </Button>
          </Grid>
        </Grid>
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
      </Box>
    </Container>
  );
}

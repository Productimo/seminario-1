import uuid from "react-uuid";
import { Box } from "@mui/system";
import { Container } from "@mui/system";
import Alert from "@mui/material/Alert";
import React, { useState } from "react";
import Modal from "@mui/material/Modal";
import Button from "@mui/material/Button";
import Snackbar from "@mui/material/Snackbar";
import MenuItem from "@mui/material/MenuItem";
import { Grid, TextField } from "@mui/material";
import BadgeIcon from "@mui/icons-material/Badge";
import MenuDrawerSatelite from "../../components/MenuDrawerSatelite";
import LoadingButton from "@mui/lab/LoadingButton";
import { AccountCircle } from "@mui/icons-material";
import FormControl from "@mui/material/FormControl";
import VaccinesIcon from "@mui/icons-material/Vaccines";
import InputAdornment from "@mui/material/InputAdornment";
import { Divider, Typography, Select } from "@mui/material";
import AttachFileIcon from "@mui/icons-material/AttachFile";
import LocalHospitalIcon from "@mui/icons-material/LocalHospital";
import MedicalInformationIcon from "@mui/icons-material/MedicalInformation";

const boxShadowStyle = {
  padding: "10px 20px",
  borderRadius: "10px",
  border: "1px solid var(--main-border)",
  boxShadow: "1px 1px 10px 0 rgb(0 0 0 / 15%)",
  margin: "auto",
  marginTop: "80px",
};

const modalStyle = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-25%, -50%)",
  width: "auto",
  bgcolor: "white",
  boxShadow: 24,
  p: 4,
};

export default function FormAmpollasSatelite() {
  const [dni_paciente, setDNI] = useState();
  const [comentarios, setComentarios] = useState();
  const [formulario, setFormulario] = useState({});
  const [cant_ampollas, setCantAmpollas] = useState();
  const [nombre_paciente, setNombrePaciente] = useState();
  const [cod_medicamento, setCodMedicamento] = useState("");
  const [medico_atencion, setMedicoAtencion] = useState("");

  const [alertOpen, setAlertOpen] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const [open, setOpen] = useState(false);
  const [loading, setLoading] = useState(false);

  const checkData = () => {
    if (
      !dni_paciente ||
      !nombre_paciente ||
      !cod_medicamento ||
      !cant_ampollas ||
      !medico_atencion
    ) {
      setErrorMessage("Por favor complete todos los datos");
      return setAlertOpen(true);
    }
    setOpen(true);
  };

  const handleSubmit = () => {
    // TODO: Send data to backend

    setFormulario({
      dni_paciente,
      nombre_paciente,
      cod_medicamento,
      cant_ampollas,
      medico_atencion,
    });
    //setLoading(true);
    window.location.reload();
  };

  console.log(formulario);

  return (
    <Container
      sx={{ display: "flex", minWidth: "100%", padding: "20px" }}
      disableGutters
    >
      <MenuDrawerSatelite />
      <Box style={boxShadowStyle}>
        <Typography
          variant="h1"
          component="div"
          sx={{ paddingBottom: "25px", paddingTop: "10px", color:"rgba(0, 129, 128, 0.87)" }}
        >
          Ingreso de nueva atención
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
          <Grid item xs={1.3333}>
            <FormControl sx={{ minWidth: "100%" }} variant="outlined">
              <Select
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start">
                      <VaccinesIcon sx={{ color: "teal" }} />
                    </InputAdornment>
                  ),
                }}
                onChange={(v) => setMedicoAtencion(v.target.value)}
                value={medico_atencion}
                sx={{ width: "100%" }}
                displayEmpty
              >
                <MenuItem value="" default disabled>
                  <em>Medico que realizó la atención *</em>
                </MenuItem>
                <MenuItem value={"medico1"}>Medico 1</MenuItem>
              </Select>
            </FormControl>
          </Grid>
          <Grid item xs={1.3333}>
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

          <Grid item xs={1.3333}>
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
            <Button variant="contained" onClick={checkData}>
              CONTINUAR
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

      <Modal
        open={open}
        onClose={() => setOpen(false)}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={modalStyle}>
          <Typography variant="h6" component="h2">
            Por favor verificá que los datos sean correctos
          </Typography>
          <Divider sx={{ marginTop: 1, marginBottom: 2 }} />
          <Typography variant="p" component="p" sx={{ marginBottom: "5px" }}>
            Nombre paciente: {nombre_paciente}
          </Typography>
          <Typography variant="p" component="p" sx={{ marginBottom: "5px" }}>
            DNI paciente: {dni_paciente}
          </Typography>
          <br />
          <Typography variant="p" component="p" sx={{ marginBottom: "5px" }}>
            Médico encargado: {medico_atencion}
          </Typography>
          <Typography variant="p" component="p" sx={{ marginBottom: "5px" }}>
            Medicamento utilizado: {cod_medicamento}
          </Typography>
          <Typography variant="p" component="p" sx={{ marginBottom: "5px" }}>
            Ampollas utilizadas: {cant_ampollas}
          </Typography>
          <Typography variant="p" component="p" sx={{ marginBottom: "5px" }}>
            Comentarios: {comentarios}
          </Typography>
          <br />
          <Container sx={{ textAlign: "center" }}>
            <Button
              variant="outlined"
              onClick={() => setOpen(false)}
              sx={{ marginRight: 1, width: 170 }}
            >
              EDITAR
            </Button>
            <LoadingButton
              variant="contained"
              onClick={handleSubmit}
              //loading={loading}
              sx={{ marginLeft: 1 }}
            >
              ENVIAR FORMULARIO
            </LoadingButton>
          </Container>
        </Box>
      </Modal>
    </Container>
  );
}

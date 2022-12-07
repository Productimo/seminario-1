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
import DomainAddIcon from "@mui/icons-material/DomainAdd";

const boxShadowStyle = {
  padding: "10px 20px",
  borderRadius: "10px",
  border: "1px solid var(--main-border)",
  boxShadow: "1px 1px 10px 0 rgb(0 0 0 / 15%)",
  marginTop: "80px",
  width: "100%",
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

export default function PedidoDeEmergenciaSatelite() {
  const [alertOpen, setAlertOpen] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const [cod_medicamento, setCodMedicamento] = useState("");
  const [open, setOpen] = React.useState(false);
  const [ampollas_solicitadas, setAmpollasSolicitadas] = useState("");
  const [comentarios, setComentarios] = useState("");
  const [pedido_emergencia, setPedidoEmergencia] = useState({});

  const checkData = () => {
    if (!cod_medicamento || !ampollas_solicitadas || !comentarios) {
      setErrorMessage("Por favor complete todos los datos");
      return setAlertOpen(true);
    }
    setOpen(true);
  };

  const handleSubmit = () => {
    setPedidoEmergencia({
      cod_medicamento,
      ampollas_solicitadas,
      comentarios,
    });
    window.location.reload();
  };

  return (
    <Container
      sx={{ display: "flex", minWidth: "100%", padding: "20px" }}
      disableGutters
    >
      <MenuDrawerSatelite />
      <Box style={boxShadowStyle}>
        <Typography variant="h5" component="div" sx={{ paddingTop: "10px" }}>
          Pedido de urgencia
        </Typography>
        <Typography
          variant="p"
          component="p"
          sx={{ paddingBottom: "25px", paddingTop: "10px" }}
        >
          Este formulario creará un pedido de urgencia al hospital central para
          cubrir la demanda lo antes posible.
        </Typography>
        <Grid container spacing={2} columns={4}>
          <Grid item xs={2}>
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
                <MenuItem value={"CHS0001"}>HEM0001</MenuItem>
                <MenuItem value={"CHS0002"}>HEM0002</MenuItem>
                <MenuItem value={"CHS0003"}>HEM0003</MenuItem>
              </Select>
            </FormControl>
          </Grid>

          <Grid item xs={2}>
            <TextField
              label="Cantidad de ampollas solicitadas *"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <VaccinesIcon sx={{ color: "teal" }} />
                  </InputAdornment>
                ),
              }}
              variant="outlined"
              onChange={(v) => setAmpollasSolicitadas(v.target.value)}
              value={ampollas_solicitadas}
              type="number"
              sx={{ width: "100%" }}
            />
          </Grid>

          <Grid item xs={4}>
            <TextField
              label="Comentarios *"
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
            Medicamente solicitado: {cod_medicamento}
          </Typography>
          <Typography variant="p" component="p" sx={{ marginBottom: "5px" }}>
            Cantidad de ampollas: {ampollas_solicitadas}
          </Typography>
          <br />
          <Typography variant="p" component="p" sx={{ marginBottom: "5px" }}>
            Comentarios: {comentarios}
          </Typography>
          <br />

          <Container sx={{ textAlign: "center" }}>
            <Button
              variant="outlined"
              onClick={() => setOpen(false)}
              sx={{ marginRight: 1, width: 130 }}
            >
              EDITAR
            </Button>
            <LoadingButton
              variant="contained"
              onClick={handleSubmit}
              sx={{ marginLeft: 1 }}
            >
              ENVIAR PEDIDO
            </LoadingButton>
          </Container>
        </Box>
      </Modal>
    </Container>
  );
}

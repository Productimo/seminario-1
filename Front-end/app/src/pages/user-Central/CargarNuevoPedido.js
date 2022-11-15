import React from "react";
import MenuDrawerCentral from "../../components/MenuDrawerCentral"

import { Box } from "@mui/system";
import { Container } from "@mui/system";
import Alert from "@mui/material/Alert";
import Modal from "@mui/material/Modal";
import Button from "@mui/material/Button";
import Snackbar from "@mui/material/Snackbar";
import MenuItem from "@mui/material/MenuItem";
import { Grid, TextField } from "@mui/material";
import FormControl from "@mui/material/FormControl";
import InputAdornment from "@mui/material/InputAdornment";
import { Divider, Typography, Select } from "@mui/material";
import { useState } from "react";
import ProductionQuantityLimitsIcon from '@mui/icons-material/ProductionQuantityLimits';
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import Paper from "@mui/material/Paper";
import TableRow from "@mui/material/TableRow";

const boxShadowStyle = {
  padding: "10px 20px",
  borderRadius: "10px",
  border: "1px solid var(--main-border)",
  boxShadow: "1px 1px 10px 0 rgb(0 0 0 / 15%)",
  margin: "auto",
  marginTop: "80px",
};

const titleStyle = {
  position: "absolute",
  left: "20.79%",
  right: "32.78%",
  top: "15.77%",
  bottom: "82%",

  fontFamily: 'Roboto',
  fontStyle: "normal",
  fontWeight: "500",
  fontSize: "96px",
  lineHeight: "24px",
  letterSpacing: "0.17px",
  color: "rgba(0, 129, 128, 0.87)",
  textShadow: "0px 4px 4px rgba(0, 0, 0, 0.25)",
}

export default function CargarNuevoPedido(){
  const [hospital, setHospital] = useState("");
  const [medicamento, setMedicamento] = useState("");
  const [unidades, setUnidades] = useState();
  const [listaMedicamentos, setListaMedicamentos] = useState("");
  const [formulario, setFormulario] = useState({})

  const [alertOpen, setAlertOpen] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const [open, setOpen] = useState(false);
  const [loading, setLoading] = useState(false);

  const checkData = () => {
    if (
      !hospital ||
      !medicamento ||
      !unidades
    ) {
      setErrorMessage("Por favor complete todos los datos");
      return setAlertOpen(true);
    }
    setOpen(true);
  };

  const handleSubmit = () => {
    // TODO: Send data to backend

    setFormulario({
      hospital,
      unidades,
      medicamento,
    });
    //setLoading(true);
    window.location.reload();
  };

  console.log(formulario);
    return(
    <Container
      sx={{ display: "flex", minWidth: "100%", padding: "20px" }}
      disableGutters>
        <MenuDrawerCentral/>
        <Box style={boxShadowStyle}>
        <Typography
          variant="h1"
          component="div"
          sx={{ paddingBottom: "25px", paddingTop: "10px", color:"rgba(0, 129, 128, 0.87)" }}
        >
          Cargar nuevo pedido
        </Typography>

        <Typography variant='h5' sx={{paddingBottom:'15px'}}>Este formulario creará un nuevo pedido que sera enviado al <b style={{ color: "rgba(0, 129, 128, 0.87)" }}>hospital satelite seleccionado</b></Typography>

        <Grid container spacing={2} columns={4}>

            <Grid item xs={1.3333}>
                <FormControl sx={{ minWidth: "100%" }} variant="outlined">
                  <Select
                    onChange={(v) => setHospital(v.target.value)}
                    value={hospital}
                    sx={{ width: "100%" }}
                    displayEmpty
                  >
                    <MenuItem value="" default disabled>
                      <em>Hospital *</em>
                    </MenuItem>
                    <MenuItem value={"Hospital1"}>Hospital1</MenuItem>
                  </Select>
                </FormControl>
              </Grid>

              <Grid item xs={1.3333}>
                <FormControl sx={{ minWidth: "100%" }} variant="outlined">
                  <Select
                    onChange={(v) => setMedicamento(v.target.value)}
                    value={medicamento}
                    sx={{ width: "100%" }}
                    displayEmpty
                  >
                    <MenuItem value="" default disabled>
                      <em>Medicamento *</em>
                    </MenuItem>
                    <MenuItem value={"CHS0001"}>CHS0001</MenuItem>
                  </Select>
                </FormControl>
              </Grid>

              <Grid item xs={1.3333}>
                <TextField
                  label="Unidades *"
                  InputProps={{
                    startAdornment: (
                      <InputAdornment position="start">
                        <ProductionQuantityLimitsIcon sx={{ color: "teal" }} />
                      </InputAdornment>
                    ),
                  }}
                  variant="outlined"
                  onChange={(v) => setUnidades(v.target.value)}
                  value={unidades}
                  sx={{ width: "100%" }}
                  type="number"
                />
              </Grid>

              <Grid item xs={4}>
                  <TableContainer component={Paper}>
                    <Table aria-label="simple table">
                      <TableHead>
                        <TableRow
                          sx={{
                            backgroundColor: "#f5f5f5",
                          }}
                        >
                          <TableCell>Hospital</TableCell>
                          <TableCell>Referencia pedido</TableCell>
                          <TableCell>Nombre de medicamento</TableCell>
                          <TableCell>Fecha del pedido</TableCell>
                          <TableCell>Cantidad</TableCell>
                          <TableCell>Tipo</TableCell>
                          <TableCell>Estado</TableCell>
                          <TableCell>Acción</TableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {rows.map((row) => (
                          <TableRow
                            key={row.hospital}
                            sx={{
                              "&:last-child td, &:last-child th": { border: 0 },
                              backgroundColor: "#f5f5f5",
                            }}
                          >
                            <TableCell component="th" scope="row">
                              {row.hospital}
                            </TableCell>
                            <TableCell>{row.ref}</TableCell>
                            <TableCell>{row.medicamento}</TableCell>
                            <TableCell>{row.fecha}</TableCell>
                            <TableCell>{row.cantidad}</TableCell>
                            <TableCell>{row.tipo}</TableCell>
                            <TableCell>{row.estado}</TableCell>
                            <TableCell>
                              {row.estado === "En preparación" && (
                                <DeleteIcon
                                  sx={{ color: "red", cursor: "pointer" }}
                                  onClick={() => setStockOpen(true)}
                                ></DeleteIcon>
                              )}
                            </TableCell>
                          </TableRow>
                        ))}
                      </TableBody>
                    </Table>
                  </TableContainer>
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

    </Container>
    )
}
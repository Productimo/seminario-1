import React, { useEffect } from "react";
import MenuDrawerCentral from "../../components/MenuDrawerCentral";
import { Box } from "@mui/system";
import { Container } from "@mui/system";
import Alert from "@mui/material/Alert";
import Modal from "@mui/material/Modal";
import Button from "@mui/material/Button";
import Snackbar from "@mui/material/Snackbar";
import MenuItem from "@mui/material/MenuItem";
import { Grid, IconButton, TextField } from "@mui/material";
import FormControl from "@mui/material/FormControl";
import InputAdornment from "@mui/material/InputAdornment";
import { Divider, Typography, Select } from "@mui/material";
import { useState } from "react";
import ProductionQuantityLimitsIcon from "@mui/icons-material/ProductionQuantityLimits";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import Paper from "@mui/material/Paper";
import TableRow from "@mui/material/TableRow";
import DeleteIcon from "@mui/icons-material/Delete";
import AddIcon from "@mui/icons-material/Add";
import { Add } from "@mui/icons-material";
import uuid from "react-uuid";

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

  fontFamily: "Roboto",
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
  const [codMedicamento, setCodMedicamento] = useState(uuid());
  const [unidades, setUnidades] = useState();
  const [formulario, setFormulario] = useState({});
  const [alertOpen, setAlertOpen] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const [open, setOpen] = useState(false);
  const [rows, setRows] = useState([]);
  const [successMessage, setSuccessMessage] = useState("")
  const [successAlertOpen, setSuccessAlertOpen] = useState(false);

  /*const rows = [
    {
      Medicamento: "Garrahan",
      Codigo_medicamento: "OE0005",
      Unidades: "20",
      Acciones: "",
    },
  ];*/

  const sendData = () => {
    setSuccessMessage("Pedido cargado con exito")
    setSuccessAlertOpen(true)
    window.location.reload();
  }

  const checkData = () => {
   if ( 
     rows.length==0
    ) {
      setErrorMessage("Por favor inserte un nuevo pedido");
      setAlertOpen(true);
    }
    else{setOpen(true);}
  };

  const handleNewRow = () => {
    if(
      !hospital||
      !codMedicamento||
      !unidades||
      !medicamento
      ){
        setErrorMessage("Por favor complete todos los datos");
        setAlertOpen(true);
      }
    else{rows.push({medicamento,codMedicamento,unidades})
    setMedicamento("")
    setUnidades("")
    }
  }

  console.log("columnas:", rows)

    return(
    <Container
      sx={{ display: "flex", minWidth: "100%", padding: "20px" }}
      disableGutters
    >
      <MenuDrawerCentral />
      <Box style={boxShadowStyle}>
        <Typography
          variant="h1"
          component="div"
          sx={{
            paddingBottom: "25px",
            paddingTop: "10px",
            color: "rgba(0, 129, 128, 0.87)",
          }}
        >
          Cargar nuevo pedido
        </Typography>

        <Typography variant='h5' sx={{paddingBottom:'15px'}}>Este formulario creará un nuevo pedido que sera enviado al <b style={{ color: "rgba(0, 129, 128, 0.87)" }}>hospital satelite seleccionado</b></Typography>
        <FormControl sx={{ width: "100%", pb:'1%'}} variant="outlined">
            <Select
              onChange={(v) => {setHospital(v.target.value)}}
              value={hospital}
              displayEmpty
              disabled = {hospital}
            >
              <MenuItem value="" default disabled>
                <em>Hospital *</em>
              </MenuItem>
              <MenuItem value={"Rivadavia"}>Rivadavia</MenuItem>
              <MenuItem value={"Garrahan"}>Garrahan</MenuItem>
              <MenuItem value={"Udaondo"}>Udaondo</MenuItem>
            </Select>
        </FormControl>

        <Grid container spacing={1} columns={3.3}>
              <Grid item xs={1}>
                <FormControl sx={{ minWidth: "100%" }} variant="outlined">
                  <Select
                    onChange={(v) => setMedicamento(v.target.value)}
                    value={medicamento}
                    displayEmpty
                  >
                    <MenuItem value="" default disabled>
                      <em>Medicamento *</em>
                    </MenuItem>
                    <MenuItem value={"CHS0001"}>HEM0001</MenuItem>
                    <MenuItem value={"CHS0002"}>HEM0002</MenuItem>
                    <MenuItem value={"CHS0003"}>HEM0003</MenuItem>
                  </Select>
                </FormControl>
              </Grid>

              <Grid item xs={1}>
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

              <Grid item xs={0.3} >
                <Button  variant="text" color='success' 
                onClick={handleNewRow}
                >Agregar al pedido
                </Button>
              </Grid>

              <Grid item xs={3.3}>
                  <TableContainer component={Paper} sx={{}}>
                    <Table aria-label="simple table">
                      <TableHead>
                        <TableRow
                          sx={{
                            backgroundColor: "#f5f5f5",
                          }}
                        >
                          <TableCell align="left"><b>Medicamento</b></TableCell>
                          <TableCell align="left"><b>Codigo medicamento</b></TableCell>
                          <TableCell align="left"><b>Unidades</b></TableCell>
                          <TableCell align="left"><b>Acciones</b></TableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {rows.map((row) => (
                          <TableRow
                            key={row.Codigo_medicamento}
                            sx={{
                              "&:last-child td, &:last-child th": { border: 0 },
                              backgroundColor: "#f5f5f5",
                            }}
                          >
                            <TableCell align="left">{row.medicamento}</TableCell>
                            <TableCell align="left">{row.codMedicamento}</TableCell>
                            <TableCell align="left">{row.unidades}</TableCell>
                            <TableCell align="left"><IconButton onClick="">
                                <DeleteIcon/>
                            </IconButton>
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
          message=""
          onClose={() => setAlertOpen(false)}
        >
          <Alert severity="error" sx={{ width: "100%" }}>
            {errorMessage}
          </Alert>
        </Snackbar>

        <Snackbar
          anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
          open={successAlertOpen}
          autoHideDuration={3000}
          message=""
          onClose={() => setSuccessAlertOpen(false)}
        >
          <Alert severity="error" sx={{ width: "100%" }}>
            {successMessage}
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
            ¿Esta seguro que desea cargar este pedido con {rows.length} medicamentos diferentes?
          </Typography>
          <Divider sx={{ marginTop: 1, marginBottom: 2 }} />
          <br />
          <Container sx={{ textAlign: "center" }}>
            <Button
              variant="outlined"
              onClick={sendData}
              sx={{ marginRight: 1, width: 170 }}
            >
              Enviar pedido
            </Button>
          </Container>
        </Box>
      </Modal>

    </Container>
  );
}

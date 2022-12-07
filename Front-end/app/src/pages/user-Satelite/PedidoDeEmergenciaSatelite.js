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
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import Paper from "@mui/material/Paper";
import TableRow from "@mui/material/TableRow";
import DeleteIcon from "@mui/icons-material/Delete"
import IconButton from "@mui/material/IconButton";

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
  const [medicamento, setMedicamento] = useState("");
  const [codMedicamento, setCodMedicamento] = useState(uuid());
  const [open, setOpen] = React.useState(false);
  const [unidades, setUnidades] = useState();
  const [rows, setRows] = useState([]);
  const [pedido_emergencia, setPedidoEmergencia] = useState({});
  const [successMessage, setSuccessMessage] = useState("")
  const [successAlertOpen, setSuccessAlertOpen] = useState(false);

  const sendData = () => {
    //aca deberia ir el POST incluyendo el hospital de la sesion actual
    setSuccessMessage("Pedido cargado con exito")
    setSuccessAlertOpen(true)
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
      !codMedicamento||
      !unidades||
      !medicamento
      ){
        setErrorMessage("Por favor complete todos los datos");
        setAlertOpen(true);
      }
    else{rows.push({medicamento,codMedicamento,unidades})
    setMedicamento("")
    setUnidades()
    }
  }

  return (
    <Container
      sx={{ display: "flex", minWidth: "100%", padding: "20px" }}
      disableGutters
    >
      <MenuDrawerSatelite />
      <Box style={boxShadowStyle}>
        <Typography variant="h1" component="div" sx={{ paddingBottom: "25px", paddingTop: "10px", color:"rgba(0, 129, 128, 0.87)" }}>
          Pedido de urgencia
        </Typography>
        <Typography
          variant="h6"
          component="p"
          sx={{ paddingBottom: "25px", paddingTop: "10px" }}
        >
          <b>Este formulario creará un pedido de urgencia al hospital central para
          cubrir la demanda lo antes posible.</b>
        </Typography>
        <Grid container spacing={2} columns={4}>
          <Grid item xs={2}>
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
                <MenuItem value={"Ibuprofeno"}>Ibuprofeno</MenuItem>
              </Select>
            </FormControl>
          </Grid>

          <Grid item xs={2}>
            <TextField
              label="Unidades *"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <VaccinesIcon sx={{ color: "teal" }} />
                  </InputAdornment>
                ),
              }}
              variant="outlined"
              onChange={(v) => setUnidades(v.target.value)}
              value={unidades}
              type="number"
              sx={{ width: "100%" }}
            />
          </Grid>
          
          <Grid item xs={2} >
                <Button  variant="contained" color='success' 
                onClick={handleNewRow}
                >Agregar al pedido
                </Button>
          </Grid>

          <Grid item xs={4}>
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
                        key={row.codMedicamento}
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
            ¿Esta seguro que desea cargar este pedido con {rows.length} medicamento/s diferentes?
          </Typography>
          <br />
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
              onClick={sendData}
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

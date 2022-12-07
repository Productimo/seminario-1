import React, { useState } from "react";
import { Container } from "@mui/system";
import { Box } from "@mui/system";
import { Typography, Divider, Select } from "@mui/material";
import { Grid } from "@mui/material";
import MenuDrawerSatelite from "../../components/MenuDrawerSatelite";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import Paper from "@mui/material/Paper";
import TableRow from "@mui/material/TableRow";
import AssessmentIcon from "@mui/icons-material/Assessment";
import MedicationIcon from "@mui/icons-material/Medication";
import Modal from "@mui/material/Modal";
import Button from "@mui/material/Button";

import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";

const boxShadowStyle = {
  padding: "10px 20px",
  borderRadius: "5px",
  border: "1px solid var(--main-border)",
  boxShadow: "1px 1px 10px 0 rgb(0 0 0 / 15%)",
  margin: "auto",
  marginTop: "40px",
  display: "flex",
  alignItems: "center",
};

const modalStyle = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-25%, -50%)",
  width: "400px",
  bgcolor: "white",
  boxShadow: 24,
  p: 4,
  textAlign: "center",
};

const barData = [
  {
    name: "Factor VII Octapharma",
    sep: 400,
    oct: 240,
  },
  {
    name: "Factor VIII UNC",
    sep: 300,
    oct: 139,
  },
  {
    name: "Factor VIII Koate",
    sep: 200,
    oct: 980,
  },
];

const rows = [
  {
    medicamento: "Factor VIII Koate",
    stock: "5 unidades",
  },
  {
    medicamento: "Factor VII Octapharma",
    stock: "10 unidades",
  },
];

const rowsPacientes = [
  {
    paciente: "Franco Lorem",
    medicamento: "Factor VIII Koate",
    cantidad: "1 unidad",
  },
  {
    paciente: "Juan Battaglia",
    medicamento: "Factor VII Octapharma",
    cantidad: "2 unidades",
  },
  {
    paciente: "Timoteo Guerini",
    medicamento: "Factor VIII UNC",
    cantidad: "2 unidades",
  },
  {
    paciente: "Liliana Rodriguez",
    medicamento: "Factor VII Octapharma",
    cantidad: "1 unidad",
  },
];

export default function HomeSatelite() {
  const [reportOpen, setReportOpen] = useState(false);

  return (
    <Container
      sx={{
        display: "flex",
        minWidth: "100%",
        marginTop: "80px",
      }}
    >
      <MenuDrawerSatelite />
      <Box>
        <Grid container spacing={2} columns={4}>
          <Grid item xs={2}>
            <Typography
              variant="h5"
              component="div"
              sx={{ paddingBottom: "25px", paddingTop: "10px" }}
            >
              Medicamentos con <b style={{ color: "#FF0000" }}>bajo stock</b>
            </Typography>
            <br />
            <br />
            <TableContainer component={Paper}>
              <Table aria-label="simple table">
                <TableHead>
                  <TableRow
                    sx={{
                      backgroundColor: "#f5f5f5",
                    }}
                  >
                    <TableCell>Medicamento</TableCell>
                    <TableCell>Stock</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {rows.map((row) => (
                    <TableRow
                      key={row.medicamento}
                      sx={{
                        "&:last-child td, &:last-child th": { border: 0 },
                        backgroundColor: "#f5f5f5",
                      }}
                    >
                      <TableCell component="th" scope="row">
                        {row.medicamento}
                      </TableCell>
                      <TableCell style={{ color: "#FF0000" }}>
                        {row.stock}
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </Grid>
          <Grid item xs={2}>
            <Typography
              variant="h5"
              component="div"
              sx={{ paddingBottom: "25px", paddingTop: "10px" }}
            >
              Comparativa de uso
              <b style={{ color: "#008180" }}> medicamentos</b> entre mes actual
              y mes pasado
            </Typography>
            <BarChart
              width={650}
              height={300}
              data={barData}
              margin={{
                top: 5,
                right: 30,
                left: 20,
                bottom: 5,
              }}
            >
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="name" />
              <YAxis />
              <Tooltip />
              <Legend />
              <Bar dataKey="sep" fill="#008180" unit={" unidades"} />
              <Bar dataKey="oct" fill="#E93030" unit={" unidades"} />
            </BarChart>
          </Grid>
          <Grid item xs={2}>
            <Typography
              variant="h5"
              component="div"
              sx={{ paddingBottom: "25px", paddingTop: "10px" }}
            >
              Últimos <b style={{ color: "#008180" }}>pacientes</b> atendidos
            </Typography>
            <br />
            <br />
            <TableContainer component={Paper}>
              <Table aria-label="simple table">
                <TableHead>
                  <TableRow
                    sx={{
                      backgroundColor: "#f5f5f5",
                    }}
                  >
                    <TableCell>Paciente</TableCell>
                    <TableCell>Medicamento utilizado</TableCell>
                    <TableCell>Cantidad utilizada</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {rowsPacientes.map((row) => (
                    <TableRow
                      key={row.paciente}
                      sx={{
                        "&:last-child td, &:last-child th": { border: 0 },
                        backgroundColor: "#f5f5f5",
                      }}
                    >
                      <TableCell component="th" scope="row">
                        {row.paciente}
                      </TableCell>
                      <TableCell>{row.medicamento}</TableCell>
                      <TableCell>{row.cantidad}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </Grid>
          <Grid item xs={2}>
            <Typography
              variant="h5"
              component="div"
              sx={{ paddingBottom: "25px", paddingTop: "10px" }}
            >
              Descargá los reportes <b style={{ color: "#008180" }}>acá</b>
            </Typography>
            <Box style={boxShadowStyle}>
              <AssessmentIcon sx={{ color: "teal", fontSize: "56px" }} />
              <Typography
                variant="h5"
                component="span"
                sx={{ paddingLeft: "25px" }}
              >
                <a href="#" onClick={() => setReportOpen(true)}>
                  Stock actual
                </a>
              </Typography>
            </Box>
            <Box style={boxShadowStyle}>
              <MedicationIcon sx={{ color: "teal", fontSize: "56px" }} />
              <Typography
                variant="h5"
                component="span"
                sx={{ paddingLeft: "25px" }}
              >
                <a href="#" onClick={() => setReportOpen(true)}>
                  Uso de medicación
                </a>
              </Typography>
            </Box>
          </Grid>
        </Grid>
      </Box>

      <Modal
        open={reportOpen}
        onClose={() => setReportOpen(false)}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={modalStyle}>
          <Typography variant="h6" component="h2">
            Esto descargará un reporte del stock de todos los medicamentos
          </Typography>
          <Divider sx={{ marginTop: 1, marginBottom: 2 }} />
          <br />
          <Container
            sx={{ textAlign: "center", marginTop: "20px", padding: 0 }}
          >
            <Button
              variant="contained"
              onClick={() => setReportOpen(false)}
              sx={{ width: "100%" }}
            >
              DESCARGAR
            </Button>
          </Container>
        </Box>
      </Modal>
    </Container>
  );
}

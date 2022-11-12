import React, { useState } from "react";
import { Container } from "@mui/system";
import MenuDrawerSatelite from "../../components/MenuDrawerSatelite";
import { Box } from "@mui/system";
import { Divider, Typography, Select } from "@mui/material";
import { Grid, TextField } from "@mui/material";
import RadioButtonCheckedIcon from "@mui/icons-material/RadioButtonChecked";
import Modal from "@mui/material/Modal";
import Button from "@mui/material/Button";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import Paper from "@mui/material/Paper";
import TableRow from "@mui/material/TableRow";
import Tooltip from "@mui/material/Tooltip";
import IconButton from "@mui/material/IconButton";
import WarningIcon from "@mui/icons-material/Warning";
import ErrorOutlineIcon from "@mui/icons-material/ErrorOutline";

import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Legend,
  Line,
  ComposedChart,
} from "recharts";
import { Tooltip as ChartTooltip } from "recharts";

const boxShadowStyle = {
  padding: "10px 20px",
  borderRadius: "10px",
  margin: "auto",
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

const barData = [
  {
    name: "Enero",
    urg: 440,
    rec: 452,
    stock: 480,
  },
  {
    name: "Febrero",
    urg: 557,
    rec: 153,
    stock: 330,
  },
  {
    name: "Marzo",
    urg: 160,
    rec: 160,
    stock: 165,
  },
  {
    name: "Abril",
    urg: 120,
    rec: 299,
    stock: 110,
  },
  {
    name: "Mayo",
    urg: 461,
    rec: 52,
    stock: 450,
  },
  {
    name: "Junio",
    urg: 432,
    rec: 12,
    stock: 450,
  },
  {
    name: "Julio",
    urg: 432,
    rec: 410,
    stock: 150,
  },
  {
    name: "Agosto",
    urg: 401,
    rec: 390,
    stock: 180,
  },
  {
    name: "Septiembre",
    urg: 50,
    rec: 452,
    stock: 50,
  },
  {
    name: "Octubre",
    urg: 150,
    rec: 432,
    stock: 50,
  },
  {
    name: "Noviembre",
    urg: 458,
    rec: 291,
    stock: 300,
  },
  {
    name: "Diciembre",
    urg: 145,
    rec: 286,
    stock: 130,
  },
];

const rows = [
  {
    codigo: "HEMO001",
    nombre: "Factor VIII Octapharma",
    stock: 55,
  },
  {
    codigo: "HEMO002",
    nombre: "Ivermectina",
    stock: 33,
  },
  {
    codigo: "HEMO003",
    nombre: "Ramipril",
    stock: 10,
  },
  {
    codigo: "HEMO004",
    nombre: "Refrianex",
    stock: 29,
  },
  {
    codigo: "HEMO005",
    nombre: "Lexotiroxina",
    stock: 35,
  },
];

export default function GestionDeStockSatelite() {
  const [stockOpen, setStockOpen] = useState(false);

  return (
    <Container
      sx={{ display: "flex", minWidth: "100%", padding: "20px" }}
      disableGutters
    >
      <MenuDrawerSatelite />
      <Box style={boxShadowStyle}>
        <Typography
          variant="h5"
          component="div"
          sx={{ paddingBottom: "25px", paddingTop: "10px" }}
        >
          Stock
        </Typography>
        <Grid container spacing={2} columns={2}>
          <Grid item xs={2} sx={{ display: "flex", justifyContent: "center" }}>
            <ComposedChart
              width={1100}
              height={350}
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
              <ChartTooltip />
              <Legend />
              <Bar
                dataKey="urg"
                fill="#E93030"
                unit={" comprimidos"}
                name="Cant. medicamentos utilizados en urgencias"
              />
              <Bar
                dataKey="rec"
                fill="#008180"
                unit={" comprimidos"}
                name="Cant. medicamentos recibidos"
              />
              <Line
                type="monotone"
                dataKey="stock"
                stroke="#EFBE3F"
                unit={" comprimidos"}
                name="Cant. medicamentos en stock"
              />
            </ComposedChart>
          </Grid>
          <Grid item xs={2}>
            <Box
              sx={{
                padding: "15px",
                backgroundColor: "#F0F0F0",
                display: "flex",
                marginBottom: "20px",
              }}
            >
              <RadioButtonCheckedIcon
                sx={{ marginRight: "10px" }}
              ></RadioButtonCheckedIcon>
              <Typography variant="p" component="span">
                <a href="#" onClick={() => setStockOpen(true)}>
                  Medicamentos por debajo del minimo de stock: Ramipril,
                  Lexotiroxina..
                </a>
              </Typography>
            </Box>
            <Box
              sx={{
                padding: "15px",
                backgroundColor: "#F0F0F0",
                display: "flex",
              }}
            >
              <RadioButtonCheckedIcon
                sx={{ marginRight: "10px" }}
              ></RadioButtonCheckedIcon>
              <Typography variant="p" component="span">
                Rotación de medicamentos{" "}
                <b style={{ color: "#008180" }}>recibidos</b>/
                <b style={{ color: "#FF0000" }}>usados</b> este mes:{" "}
                <b style={{ color: "#008180" }}>entró 95</b> /{" "}
                <b style={{ color: "#FF0000" }}>salió 19</b>
              </Typography>
            </Box>
          </Grid>
        </Grid>

        <Grid container spacing={2} columns={2} sx={{ marginTop: "40px" }}>
          <Grid item xs={2}>
            <TableContainer component={Paper}>
              <Table aria-label="simple table">
                <TableHead>
                  <TableRow
                    sx={{
                      backgroundColor: "#f5f5f5",
                    }}
                  >
                    <TableCell>Código de medicamento</TableCell>
                    <TableCell>Nombre de medicamento</TableCell>
                    <TableCell>Stock</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {rows.map((row) => (
                    <TableRow
                      key={row.codigo}
                      sx={{
                        "&:last-child td, &:last-child th": { border: 0 },
                        backgroundColor: "#f5f5f5",
                      }}
                    >
                      <TableCell component="th" scope="row">
                        {row.codigo}
                      </TableCell>
                      <TableCell>{row.nombre}</TableCell>
                      <TableCell>
                        {row.stock}
                        {row.stock === 29 && (
                          <Tooltip title="Cerca del limite de seguridad">
                            <IconButton>
                              <WarningIcon sx={{ color: "#E7BE30" }} />
                            </IconButton>
                          </Tooltip>
                        )}
                        {row.stock === 10 && (
                          <Tooltip title="Por debajo del limite de seguridad">
                            <IconButton>
                              <ErrorOutlineIcon sx={{ color: "#E93030" }} />
                            </IconButton>
                          </Tooltip>
                        )}
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </Grid>
        </Grid>
      </Box>

      <Modal
        open={stockOpen}
        onClose={() => setStockOpen(false)}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={modalStyle}>
          <Typography variant="h6" component="h2">
            Medicamentos a punto de{" "}
            <b style={{ color: "#FF0000" }}>quebrar stock</b>
          </Typography>
          <Divider sx={{ marginTop: 1, marginBottom: 2 }} />
          <Typography variant="p" component="p" sx={{ marginBottom: "5px" }}>
            Metformina: <b style={{ color: "#FF0000" }}>16 comprimidos</b>
          </Typography>
          <Typography variant="p" component="p" sx={{ marginBottom: "5px" }}>
            Ibuprofeno: <b style={{ color: "#FF0000" }}>12 comprimidos</b>
          </Typography>
          <Typography variant="p" component="p" sx={{ marginBottom: "5px" }}>
            Amoxicilina: <b style={{ color: "#FF0000" }}>10 comprimidos</b>
          </Typography>
          <br />
          <Container sx={{ textAlign: "center" }}>
            <Button
              variant="contained"
              onClick={() => setStockOpen(false)}
              sx={{ marginRight: 1, width: 270 }}
            >
              CERRAR
            </Button>
          </Container>
        </Box>
      </Modal>
    </Container>
  );
}

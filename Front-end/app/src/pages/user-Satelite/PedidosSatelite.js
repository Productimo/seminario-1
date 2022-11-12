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
import FactCheckIcon from "@mui/icons-material/FactCheck";

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
    prev: 90,
    curr: 80,
  },
  {
    name: "Febrero",
    prev: 75,
    curr: 10,
  },
  {
    name: "Marzo",
    prev: 30,
    curr: 35,
  },
  {
    name: "Abril",
    prev: 15,
    curr: 33,
  },
  {
    name: "Mayo",
    prev: 67,
    curr: 34,
  },
  {
    name: "Junio",
    prev: 30,
    curr: 80,
  },
  {
    name: "Julio",
    prev: 35,
    curr: 64,
  },
  {
    name: "Agosto",
    prev: 72,
    curr: 28,
  },
  {
    name: "Septiembre",
    prev: 10,
    curr: 100,
  },
  {
    name: "Octubre",
    prev: 90,
    curr: 71,
  },
  {
    name: "Noviembre",
    prev: 40,
    curr: 75,
  },
  {
    name: "Diciembre",
    prev: 69,
    curr: 10,
  },
];

const rows = [
  {
    codigo: "HEMO001",
    nombre: "Factor VIII Octapharma",
    fecha: "25/10/2022",
    cantidad: 25,
    tipo: "Estandar",
    estado: "En preparación",
  },
  {
    codigo: "HEMO001",
    nombre: "Factor VIII Octapharma",
    fecha: "18/10/2022",
    cantidad: 40,
    tipo: "Estandar",
    estado: "En preparación",
  },
  {
    codigo: "HEMO004",
    nombre: "Salbutamol",
    fecha: "17/10/2022",
    cantidad: 15,
    tipo: "Estandar",
    estado: "Enviado",
  },
  {
    codigo: "HEMO003",
    nombre: "Trimetafán",
    fecha: "10/10/2022",
    cantidad: 50,
    tipo: "Urgencia",
    estado: "Recibido",
  },
];

export default function PedidosSatelite() {
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
          Pedidos
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
                dataKey="prev"
                fill="#E93030"
                unit={" pedidos"}
                name="Cant. pedidos en 2021"
              />
              <Bar
                dataKey="curr"
                fill="#008180"
                unit={" pedidos"}
                name="Cant. pedidos en 2022"
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
                Cantidad de <b style={{ color: "#FF0000" }}>pedidos</b> de tipo
                urgencia del mes: <b style={{ color: "#008180" }}>1</b>
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
                Cantidad de pedidos recibidos este mes:{" "}
                <b style={{ color: "#008180" }}>15</b>
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
                      <TableCell>{row.fecha}</TableCell>
                      <TableCell>{row.cantidad}</TableCell>
                      <TableCell>{row.tipo}</TableCell>
                      <TableCell>{row.estado}</TableCell>
                      <TableCell>
                        {row.estado === "Enviado" && (
                          <FactCheckIcon
                            sx={{ color: "teal", cursor: "pointer" }}
                            onClick={() => setStockOpen(true)}
                          ></FactCheckIcon>
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
            ¿Desea cambiar el estado del pedido a “Recibido”?
          </Typography>
          <Divider sx={{ marginTop: 1, marginBottom: 2 }} />
          <br />
          <Container sx={{ textAlign: "center" }}>
            <Button
              variant="outlined"
              onClick={() => setStockOpen(false)}
              sx={{ marginRight: 1, width: 210 }}
            >
              CANCELAR
            </Button>
            <Button
              variant="contained"
              onClick={() => setStockOpen(false)}
              sx={{ marginRight: 1, width: 210 }}
            >
              CONFIRMAR
            </Button>
          </Container>
        </Box>
      </Modal>
    </Container>
  );
}

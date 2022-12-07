import React, { useState } from "react";
import { Container } from "@mui/system";
import MenuDrawerCentral from "../../components/MenuDrawerCentral";
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
    name: "Ene",
    urg: 440,
    rec: 452,
    stock: 480,
  },
  {
    name: "Feb",
    urg: 557,
    rec: 153,
    stock: 330,
  },
  {
    name: "Mar",
    urg: 160,
    rec: 160,
    stock: 165,
  },
  {
    name: "Abr",
    urg: 120,
    rec: 299,
    stock: 110,
  },
  {
    name: "May",
    urg: 461,
    rec: 52,
    stock: 450,
  },
  {
    name: "Jun",
    urg: 432,
    rec: 12,
    stock: 450,
  },
  {
    name: "Jul",
    urg: 432,
    rec: 410,
    stock: 150,
  },
  {
    name: "Ago",
    urg: 401,
    rec: 390,
    stock: 180,
  },
  {
    name: "Sep",
    urg: 50,
    rec: 452,
    stock: 50,
  },
  {
    name: "Oct",
    urg: 150,
    rec: 432,
    stock: 50,
  },
  {
    name: "Nov",
    urg: 458,
    rec: 291,
    stock: 300,
  },
  {
    name: "Dic",
    urg: 145,
    rec: 286,
    stock: 130,
  },
];

const rows = [
  {
    Hospital: "Argerich",
    codigo: "HEMO001",
    nombre: "Factor VIII Octapharma",
    stock: 55,
  },
  {
    Hospital: "Argerich",
    codigo: "HEMO002",
    nombre: "Factor VIII UNC",
    stock: 20,
  },
  {
    Hospital: "Rivadavia",
    codigo: "HEMO001",
    nombre: "Factor VIII Octapharma",
    stock: 10,
  },
  {
    Hospital: "Rivadavia",
    codigo: "HEMO002",
    nombre: "Factor VIII UNC",
    stock: 33,
  },
  {
    Hospital: "Rivadavia",
    codigo: "HEMO003",
    nombre: "Factor VIII Koate",
    stock: 5,
  },
];

export default function GestionDeStockCentral() {
  const [stockOpen, setStockOpen] = useState(false);

  return (
    <Container
      sx={{ display: "flex", minWidth: "100%", padding: "20px" }}
      disableGutters
    >
      <MenuDrawerCentral />
      <Box style={boxShadowStyle}>
        <Typography
          variant="h1"
          component="div"
          sx={{ paddingBottom: "25px", paddingTop: "10px", color: "#008180" }}
        >
          Stock
        </Typography>
        <Grid container spacing={2} columns={2}>
          <Grid item xs={2} sx={{ display: "flex", justifyContent: "center" }}>
            <ComposedChart
              width={1000}
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
                    <TableCell>
                      <b>Hospital</b>
                    </TableCell>
                    <TableCell>
                      <b>Código de medicamento</b>
                    </TableCell>
                    <TableCell>
                      <b>Nombre de medicamento</b>
                    </TableCell>
                    <TableCell>
                      <b>Stock</b>
                    </TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {rows.map((row) => (
                    <TableRow
                      key={row.Hospital}
                      sx={{
                        "&:last-child td, &:last-child th": { border: 0 },
                        backgroundColor: "#f5f5f5",
                      }}
                    >
                      <TableCell component="th" scope="row">
                        {row.Hospital}
                      </TableCell>
                      <TableCell>{row.codigo}</TableCell>
                      <TableCell>{row.nombre}</TableCell>
                      <TableCell>
                        {row.stock}
                        {row.stock === 10 && (
                          <Tooltip title="Cerca del limite de seguridad">
                            <IconButton>
                              <WarningIcon sx={{ color: "#E7BE30" }} />
                            </IconButton>
                          </Tooltip>
                        )}
                        {row.stock === 5 && (
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

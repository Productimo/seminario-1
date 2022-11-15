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
import DeleteIcon from "@mui/icons-material/Delete";
import AddBoxIcon from "@mui/icons-material/AddBox";

import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    Legend,
    Pie,
    PieChart,
    Cell,
  } from "recharts";

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

const pieData = [
    { name: "Argerich", value: 400, color: "#0D3EEE" },
    { name: "Udaondo", value: 300, color: "#E40C0C" },
    { name: "Durand", value: 300, color: "#12DCAC" },
    { name: "Pirovano", value: 200, color: "#E77D30" },
    { name: "Garrahan", value: 278, color: "#60E04C" },
    { name: "Gandulfo", value: 189, color: "#31BCDB" },
    { name: "Rivadavia", value: 500, color: "#DB10AE" },
  ];

const rows = [
    {
        DNI_Paciente: "41765897",
        Hospital: "Argerich",
        Médico: "Jose, Perez",
        Fecha_de_atención: "25/10/2022",
        Medicamento: "Factor VIII Octapharma",
        Cantidad: "5",
        Descripción_de_la_urgencia: "El paciente se presentó con un fuerte sangrado en la pierna"
    },
    {
        DNI_Paciente: "38987654",
        Hospital: "Garrahan",
        Médico: "Pedro, Gomez",
        Fecha_de_atención: "20/10/2022",
        Medicamento: "Factor VIII Octapharma",
        Cantidad: "7",
        Descripción_de_la_urgencia: "Sangrado espontáneo en el abdomen"
    },
    {
        DNI_Paciente: "41834579",
        Hospital: "Argerich",
        Médico: "Jose, Perez",
        Fecha_de_atención: "17/10/2022",
        Medicamento: "salbutamol",
        Cantidad: "2",
        Descripción_de_la_urgencia: "Múltiples sangrados en el brazo"
    },
    {
        DNI_Paciente: "37673257",
        Hospital: "Argerich",
        Médico: "Jose, Perez",
        Fecha_de_atención: "10/10/2022",
        Medicamento: "Factor VIII Octapharma",
        Cantidad: "10",
        Descripción_de_la_urgencia: "Sangrado en la pierna derecha, muy difícil de parar"
    }
];

export default function HistorialDeAtenciones() {
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
          Historial de atenciones
        </Typography>
        
        <Grid container spacing={2} columns={2}>

        <Grid item xs={1} sx={{mt: "7%"}}>

                <Box sx={{
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
                        Cantidad de atenciones en el mes de octubre: <b style={{ color: "#008180" }}>6</b>
                    </Typography>
                </Box>

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
                    Paciente con más atenciones en el mes: <b style={{ color: "#008180" }}> Julian Gutierrez</b>  
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
                    Promedio de ampollas utilizadas en urgencias de Hemofilia:{" "}
                    <b style={{ color: "#008180" }}>5,4</b>
                </Typography>
                </Box>
            </Grid>

            <Grid item xs={1} >
                <Grid container spacing={0} columns={2}>
                    
                    <Grid item xs={0.5} >
                        <Typography
                        variant="h6"
                        component="div"
                        sx={{ paddingBottom: "25px", paddingTop: "10px", mt:"7%", ml:"30%" }}
                        >
                        ¿Cuántos <b style={{ color: "#008180" }}>pacientes</b> se atendieron en cada hospital este mes? Averigualo {" "}
                        <b style={{ color: "#008180" }}>acá</b>
                        </Typography>
                    </Grid>
                    <Grid item xs={1} >
                        <PieChart width={600} height={350}>
                        <Pie
                            dataKey="value"
                            isAnimationActive={false}
                            data={pieData}
                            fill="#8884d8"
                            label={"name"}
                        >
                            {pieData.map((entry, index) => (
                            <Cell key={`cell-${index}`} fill={entry.color} />
                            ))}
                        </Pie>
                        <Tooltip />
                        </PieChart>
                    </Grid>
                </Grid>
            </Grid>

            <Grid item xs={2}>
                <TableContainer component={Paper}>
                <Table aria-label="simple table">
                    <TableHead>
                        <TableRow
                            sx={{
                            backgroundColor: "#f5f5f5",
                            }}
                        >
                            <TableCell><b>DNI Paciente</b> </TableCell>
                            <TableCell><b>Hospital</b></TableCell>
                            <TableCell><b>Médico</b></TableCell>
                            <TableCell><b>Fecha de atención</b></TableCell>
                            <TableCell><b>Medicamento</b></TableCell>
                            <TableCell><b>Cantidad</b></TableCell>
                            <TableCell><b>Descripción de la urgencia</b></TableCell>

                        </TableRow>
                    </TableHead>
                    <TableBody>
                    {rows.map((row) => (
                        <TableRow
                        key={row.DNI_Paciente}
                        sx={{
                            "&:last-child td, &:last-child th": { border: 0 },
                            backgroundColor: "#f5f5f5"
                        }}
                        >
                        <TableCell component="th" scope="row">
                            {row.DNI_Paciente}
                        </TableCell>
                        <TableCell>{row.Hospital}</TableCell>
                        <TableCell>{row.Médico}</TableCell>
                        <TableCell>{row.Fecha_de_atención}</TableCell>
                        <TableCell>{row.Medicamento}</TableCell>
                        <TableCell>{row.Cantidad}</TableCell>
                        <TableCell>{row.Descripción_de_la_urgencia}</TableCell>
                        
                        </TableRow>
                    ))}
                    </TableBody>
                </Table>
                </TableContainer>
            </Grid>
        </Grid>


      </Box>
    </Container>
  );
}
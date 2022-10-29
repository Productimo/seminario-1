import React from "react";
import MenuDrawerSatelite from "../../components/MenuDrawerSatelite";
import { Container } from "@mui/system";
import { Box } from "@mui/system";
import { Typography } from "@mui/material";

export default function HomeSatelite() {
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
        <Typography
          variant="h4"
          component="div"
          sx={{ paddingBottom: "25px", paddingTop: "10px" }}
        >
          ¡Bienvenido Fabian!
        </Typography>
        <Typography variant="h6" component="div" sx={{ paddingBottom: "25px" }}>
          El día de hoy has ingresado X formularios.
        </Typography>
      </Box>
    </Container>
  );
}

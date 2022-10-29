import React from "react";
import { Container } from "@mui/system";
import { Box } from "@mui/system";
import { Typography } from "@mui/material";
import MenuDrawerCentral from "../../components/MenuDrawerCentral";

export default function HomeSatelite() {
  return (
    <Container
      sx={{
        display: "flex",
        minWidth: "100%",
        marginTop: "80px",
      }}
    >
      <MenuDrawerCentral />
      <Box>
        <Typography
          variant="h4"
          component="div"
          sx={{ paddingBottom: "25px", paddingTop: "10px" }}
        >
          ¡Bienvenido Hospital Central!
        </Typography>
      </Box>
    </Container>
  );
}

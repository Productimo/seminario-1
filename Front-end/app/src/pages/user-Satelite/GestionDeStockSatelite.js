import { Container } from "@mui/system";
import React from "react";
import MenuDrawerSatelite from "../../components/MenuDrawerSatelite";
import { Box } from "@mui/system";
import { Divider, Typography, Select } from "@mui/material";
import { Grid, TextField } from "@mui/material";

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

export default function GestionDeStockSatelite() {
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
          <Grid item xs={2}></Grid>
          <Grid item xs={2}></Grid>
        </Grid>
      </Box>
    </Container>
  );
}

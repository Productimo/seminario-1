import React from "react";
import MenuDrawerCentral from "../../components/MenuDrawerCentral";
import { Container } from "@mui/system";

export default function CargarNuevoPedido(){
    return(
    <Container
      sx={{ display: "flex", minWidth: "100%", padding: "20px" }}
      disableGutters>
        <MenuDrawerCentral/>
    </Container>
    )
}
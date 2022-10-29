import React from "react";
import { Container } from "@mui/system";
import MenuDrawerSatelite from "../../components/MenuDrawerSatelite";

export default function AtencionDePacientesSatelite(){
    return(
        <Container sx={{ display: "flex", minWidth: "100%", padding: "20px" }} disableGutters>
            <MenuDrawerSatelite/>
        </Container>
    )
}
import { Container } from "@mui/system";
import React from "react";
import MenuDrawerSatelite from "../../components/MenuDrawerSatelite";

export default function GestionDeStockSatelite(){
    return(
        <Container
            sx={{ display: "flex", minWidth: "100%", padding: "20px" }}
            disableGutters
            >
            <MenuDrawerSatelite />
        </Container>
    )
}
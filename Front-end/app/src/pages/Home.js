import React from "react";
import AppBar from "../components/AppBar";
import { Container } from "@mui/system";

export default function Home(){
    return(
        <Container sx={{display:"flex", bgcolor:'#0A1929', minWidth:'100%'}} disableGutters>
            <AppBar/>
        </Container>
    )
}
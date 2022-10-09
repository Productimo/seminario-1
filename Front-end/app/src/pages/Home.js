import React from "react";
import AppBar from "../components/AppBar";
import { Container } from "@mui/system";
import { Box } from "@mui/system";

export default function Home(){
    return(
        <Container sx={{display:"flex", bgcolor:'#0A1929', minWidth:'100%'}} disableGutters>
            <AppBar/>
            <Box sx={{width:'100%', bgcolor:'#0A1936'}}>
            </Box>
        </Container>
    )
}
import React from "react"
import { Container } from "@mui/system";
import AppBar from "../components/AppBar";
import {Box} from "@mui/system";
import {Badge, Grid, Select, TextField } from "@mui/material";
import { AccountCircle } from "@mui/icons-material";
import InputAdornment from "@mui/material/InputAdornment";
import BadgeIcon from '@mui/icons-material/Badge';
import Data from '../pages/Data.json'


export default function Form(){

    const data = JSON.stringify(Data)
    const dataObj = JSON.parse(data)
    //console.log(dataObj.DNI_paciente)

    return(
        <Container sx={{display:"flex", bgcolor:'#0A1929'}} disableGutters>
            <AppBar/>
            <Box sx={{width:'100%', bgcolor:'#5468ff'}}>
                <Grid container spacing={4} sx={{margin:1, width:'100%'}} >
                    <Grid item xs={4}>
                    <TextField
                        id="input-with-icon-textfield"
                        label="DNI paciente"
                        disabled
                        InputProps={{
                            startAdornment: (
                            <InputAdornment position="start">
                                <BadgeIcon/>
                            </InputAdornment>
                            ),
                        }}
                        variant="outlined"
                    />
                    </Grid>
                    <Grid item xs={4}>
                    <TextField
                        id="input-with-icon-textfield"
                        label="Nombre paciente"
                        disabled
                        value="Franco Ricci"
                        InputProps={{
                            startAdornment: (
                            <InputAdornment position="start">
                                <AccountCircle />
                            </InputAdornment>
                            ),
                        }}
                        variant="outlined"
                    />
                    </Grid>
                    <Grid item xs={4}>
                    <TextField
                        id="input-with-icon-textfield"
                        label="Hospital en el que se atendio"
                        disabled
                        value="Franco Ricci"
                        InputProps={{
                            startAdornment: (
                            <InputAdornment position="start">
                                <AccountCircle />
                            </InputAdornment>
                            ),
                        }}
                        variant="outlined"
                    />
                    </Grid>
                </Grid>
            </Box>
        </Container>
    );
}
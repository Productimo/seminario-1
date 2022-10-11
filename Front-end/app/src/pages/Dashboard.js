import React, { useState } from "react";
import { Box } from "@mui/system";
import AppBar from "../components/AppBar";
import Container from "@mui/system/Container";
import { CardContent, CardHeader, Grid, Typography } from "@mui/material";
import Card from "@mui/material/Card";
import styled from "@emotion/styled";
import MedicalInformationIcon from '@mui/icons-material/MedicalInformation';
import Button from "@mui/material/Button";
import uuid from "react-uuid";

const CssTypography = styled(Typography)({
    '&:hover label': {
      color: 'black',
    },
  });

export default function Dashboard(){

    const[usernameTextfield,setUsernameTextfield]=useState('Timoteo Güerini')
    const[id,setId]=useState(uuid())

    return(
        <Container sx={{display:"flex", bgcolor:'#0A1929', minWidth:'100%'}} disableGutters>
            <AppBar/>
            <Box sx={{width:'100%', bgcolor:'#0A1936'}}>
                <Grid container spacing={2} sx={{margin:1, width:'100%'}} columns={2}>
                    <Grid item xs={1}> 
                        <Card sx={{width:'100%', bgcolor:"#0A1929", boxShadow:7}}>
                                <CardContent sx={{display:'flex'}}>
            
                                        <Typography variant="h7" sx={{color:'yellow',border:1,padding:1, borderRadius:1}}>Administrador:{usernameTextfield}</Typography>
                                        <Button variant="outlined" startIcon={<MedicalInformationIcon />} sx={{width:'60%'}}>
                                            ID:{id}
                                        </Button>
                            </CardContent>
                        </Card>
                    </Grid>
                </Grid>
            </Box>
        </Container>
    )
}
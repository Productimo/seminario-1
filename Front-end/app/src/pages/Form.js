import React, { useEffect, useState } from "react"
import { Container } from "@mui/system";
import AppBar from "../components/AppBar";
import {Box} from "@mui/system";
import {Badge, formControlClasses, Grid, Input, Select, TextField } from "@mui/material";
import { AccountCircle, Label } from "@mui/icons-material";
import InputAdornment from "@mui/material/InputAdornment";
import BadgeIcon from '@mui/icons-material/Badge';
import Data from '../pages/Data.json'
import LocalHospitalIcon from '@mui/icons-material/LocalHospital';
import VaccinesIcon from '@mui/icons-material/Vaccines';
import Button from "@mui/material/Button";


export default function Form(){

    /*console.log(dataObj.DNI_paciente) esto me permite obtener un array con los dnis de los pacientes
    const dnis_pacientes = () => {
        
        return rta_dnis_pacientes;
    }
    const dnis_pacientes = (Data)=>{
        let n;
        return dnis_pacientes.map(i => {
            i = Data[n].DNI_paciente
            n+=1
            return i
        })
    }
    console.log(dnis_pacientes)*/

    const[dni_paciente, setDNI]=useState('')
    const[nombre_paciente, setNombrePaciente]=useState('')
    const[cod_medicamento, setCodMedicamento]=useState('')
    const[hospital_urgencia, setHospitalUrgencia]=useState('')
    const[cant_ampollas, setCantAmpollas]=useState('')
    const[comentarios, setComentarios]=useState('')
    
    const[formulario,setFormulario] = useState({})

    const handleSubmit = ()=>{
        setFormulario({
            dni_paciente:dni_paciente,
            nombre_paciente:nombre_paciente,
            cod_medicamento:cod_medicamento,
            hospital_urgencia:hospital_urgencia,
            cant_ampollas:cant_ampollas,
            comentarios:comentarios
        })
    }

    console.log(formulario)
    
    return(
        <Container sx={{display:"flex", bgcolor:'#0A1929', minWidth:'100%'}} disableGutters>
            <AppBar/>
            <Box sx={{width:'100%', bgcolor:'#5468ff'}}>
                <Grid container spacing={2} sx={{width:'30%', margin:1}} columns={4}>
                    <Grid item xs={2}>
                    <TextField
                        id="input-with-icon-textfield"
                        label="DNI paciente"
                        InputProps={{
                            startAdornment: (
                            <InputAdornment position="start">
                                <BadgeIcon/>
                            </InputAdornment>
                            ),
                        }}
                        onChange={v=>setDNI(v.target.value)}
                        value={dni_paciente}
                    />
                    </Grid>
                    <Grid item xs={2}>
                    <TextField
                        id="input-with-icon-textfield"
                        label="Nombre paciente"
                        InputProps={{
                            startAdornment: (
                            <InputAdornment position="start">
                                <AccountCircle />
                            </InputAdornment>
                            ),
                        }}
                        variant="outlined"
                        onChange={(v)=>setNombrePaciente(v.target.value)}
                        value={nombre_paciente}
                    />
                    </Grid>
                    <Grid item xs={2}>
                    <TextField
                        id="outlined-error"
                        label="Hospital en el que se atendio"
                        InputProps={{
                            startAdornment: (
                            <InputAdornment position="start">
                                <LocalHospitalIcon />
                            </InputAdornment>
                            ),
                        }}
                        variant="outlined"
                        onChange={v=>setHospitalUrgencia(v.target.value)}
                        value={hospital_urgencia}
                    />
                    </Grid>
                    <Grid item xs={2}>
                    <TextField
                        id="outlined-error"
                        label="codigo medicamento"
                        InputProps={{
                            startAdornment: (
                            <InputAdornment position="start">
                                <VaccinesIcon/>
                            </InputAdornment>
                            ),
                        }}
                        variant="outlined"
                        onChange={v=>setCodMedicamento(v.target.value)}
                        value={cod_medicamento}
                    />
                    </Grid>
                    <Grid item xs={2}>
                    <TextField
                        id="outlined-error"
                        label="cantidad de ampollas"
                        InputProps={{
                            startAdornment: (
                            <InputAdornment position="start">
                                <LocalHospitalIcon />
                            </InputAdornment>
                            ),
                        }}
                        variant="outlined"            
                        onChange={v=>setCantAmpollas(v.target.value)}
                        value={cant_ampollas}
                    />
                    </Grid>
                    <Grid item xs={2}>
                        <TextField
                            id="outlined-textarea"
                            label="Comentarios"
                            
                            multiline
                            maxRows={4}
                            onChange={v=>setComentarios(v.target.value)}
                            value={comentarios}
                            sx={{borderColor:'yellow'}}
                        />
                    </Grid>
                    <Grid item xs={2}>
                        <Button variant="contained" sx={{height:'100%',bgcolor:'yellow', color:'black'}} onClick={handleSubmit}>
                            ENVIAR FORMULARIO
                        </Button>
                    </Grid>
                    <Grid item xs={2}>
                        <Button variant="contained" sx={{height:'100%',bgcolor:'yellow', color:'black'}}>
                            DESCARGAR VISTA PREVIA
                        </Button>
                    </Grid>
                </Grid>
            </Box>
        </Container>
    );
}
import React from "react";
import { Box } from "@mui/system";
import SendIcon from '@mui/icons-material/Send';
import List from '@mui/material/List';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import AccountBoxOutlinedIcon from '@mui/icons-material/AccountBoxOutlined';
import FormatIndentIncreaseOutlinedIcon from '@mui/icons-material/FormatIndentIncreaseOutlined';
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import { ListAltRounded } from "@mui/icons-material";
import { ListItem, ListItemAvatar, Button, IconButton, Icon, Typography, MenuItem } from "@mui/material";
import SaveAltIcon from '@mui/icons-material/SaveAlt';
import HealingIcon from '@mui/icons-material/Healing';
import Select from "@mui/material/Select";

export default function AppBar(){
    const handleChange = ()=>{} //esto deberia manejar que cada opcion del menu ejecute una accion diferente

    return(
    <Box sx={{width:'30%', height:'1200px', bgcolor:'#0A1929', borderRadius:1}}>
        <List sx={{width:'100%'}}>
            <ListItem sx={{padding:0}}>
                <Button variant="outlined" color="primary" sx={{borderRadius:0,width:'100%', height:30, display:'flex', justifyContent:'left'}}> 
                    <HealingIcon sx={{color:'yellowgreen'}}/>
                    <Typography sx={{ml:'40%', fontWeight:'bold', color:'yellowgreen'}}>Hemodinamia</Typography>
                </Button>
            </ListItem>
        
            <ListItem sx={{boxShadow:2}}>
                <ListItemIcon>
                    <AccountBoxOutlinedIcon sx={{color:'white'}}/>
                </ListItemIcon>
                <ListItemText primary="Mi sesión" sx={{color:'lightblue'}}/>
                <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    value={null}
                    label="Age"
                    onChange={handleChange}
                    sx={{bgcolor:'yellowgreen', width:'20%', height:40}}
                >
                <MenuItem value={'Mis datos'}>Mis datos</MenuItem>
                <MenuItem value={'Cerrar sesion'}>Cerrar sesión</MenuItem>
                </Select>
            </ListItem>

            <ListItemButton sx={{boxShadow:3}}>
                <ListItemIcon>
                    <FormatIndentIncreaseOutlinedIcon sx={{color:'white'}}/>
                </ListItemIcon>
                <ListItemText primary="Generar formulario" sx={{color:'lightblue', fontFamily:'sans-serif'}}/>
            </ListItemButton>

            <ListItemButton sx={{boxShadow:3}}>
                <ListItemIcon>
                    <PersonAddIcon sx={{color:'white'}}/>
                </ListItemIcon>
                <ListItemText primary="Dar paciente de alta" sx={{color:'lightblue'}}/>
            </ListItemButton>
        </List>
    </Box>
    );
}
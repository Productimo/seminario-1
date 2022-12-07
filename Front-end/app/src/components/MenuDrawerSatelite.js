import React from "react";
import { Box } from "@mui/system";
import List from "@mui/material/List";
import Drawer from "@mui/material/Drawer";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import { LogoDev } from "@mui/icons-material";
import SosIcon from "@mui/icons-material/Sos";
import { useNavigate } from "react-router-dom";
import HomeIcon from "@mui/icons-material/Home";
import LogoutIcon from "@mui/icons-material/Logout";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import ListItemButton from "@mui/material/ListItemButton";
import { Divider, ListItem, Typography } from "@mui/material";
import InventoryIcon from "@mui/icons-material/Inventory";
import LocalShippingIcon from "@mui/icons-material/LocalShipping";
import SupportAgentIcon from "@mui/icons-material/SupportAgent";

const drawerWidth = 280;

export default function MenuDrawerSatelite() {
  let navigate = useNavigate();

  const goToHomeSatelite = () => {
    let path = "/HomeSatelite";
    navigate(path);
  };

  const goToPedidoDeEmergenciaSatelite = () => {
    let path = "/PedidoDeEmergenciaSatelite";
    navigate(path);
  };

  const goToAtencionDePacientesSatelite = () => {
    let path = "/FormAmpollasSatelite";
    navigate(path);
  };

  const goToGestionDeStockSatelite = () => {
    let path = "/GestionDeStockSatelite";
    navigate(path);
  };

  const goToPedidosSatelite = () => {
    let path = "/PedidosSatelite";
    navigate(path);
  };

  const closeSession = () => {
    localStorage.removeItem("user");
    window.location.href = "/";
  };

  return (
    <Box sx={{ display: "flex" }}>
      <AppBar
        position="fixed"
        sx={{
          zIndex: (theme) => theme.zIndex.drawer + 1,
          backgroundColor: "teal",
        }}
      >
        <Toolbar>
          <Typography variant="h6" noWrap component="div">
            Factor VIII
          </Typography>
        </Toolbar>
      </AppBar>
      <Drawer
        variant="permanent"
        sx={{
          width: drawerWidth,
          flexShrink: 0,
          [`& .MuiDrawer-paper`]: {
            width: drawerWidth,
            boxSizing: "border-box",
          },
        }}
      >
        <Toolbar />
        <Box sx={{ overflow: "auto" }}>
          <List sx={{ width: "100%" }}>
            <ListItem sx={{ padding: 0 }}></ListItem>

            <ListItemButton onClick={goToHomeSatelite}>
              <ListItemIcon>
                <HomeIcon />
              </ListItemIcon>
              <ListItemText primary="Inicio" />
            </ListItemButton>

            <ListItemButton onClick={goToAtencionDePacientesSatelite}>
              <ListItemIcon>
                <SupportAgentIcon />
              </ListItemIcon>
              <ListItemText primary="Atencion de pacientes" />
            </ListItemButton>

            <ListItemButton onClick={goToPedidoDeEmergenciaSatelite}>
              <ListItemIcon>
                <SosIcon />
              </ListItemIcon>
              <ListItemText primary="Pedido de urgencia" />
            </ListItemButton>

            <ListItemButton onClick={goToGestionDeStockSatelite}>
              <ListItemIcon>
                <InventoryIcon />
              </ListItemIcon>
              <ListItemText primary="Gestion de stock" />
            </ListItemButton>

            <ListItemButton onClick={goToPedidosSatelite}>
              <ListItemIcon>
                <LocalShippingIcon />
              </ListItemIcon>
              <ListItemText primary="Pedidos" />
            </ListItemButton>

            <Divider />

            <ListItemButton onClick={closeSession}>
              <ListItemIcon>
                <LogoutIcon />
              </ListItemIcon>
              <ListItemText primary="Cerrar sesión" />
            </ListItemButton>
          </List>
        </Box>
      </Drawer>
    </Box>
  );
}

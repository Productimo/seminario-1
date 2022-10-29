import React from "react";
import { Box } from "@mui/system";
import List from "@mui/material/List";
import Drawer from "@mui/material/Drawer";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import { useNavigate } from "react-router-dom";
import HomeIcon from "@mui/icons-material/Home";
import LogoutIcon from "@mui/icons-material/Logout";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import InventoryIcon from "@mui/icons-material/Inventory";
import ListItemButton from "@mui/material/ListItemButton";
import AssessmentIcon from "@mui/icons-material/Assessment";
import { Divider, ListItem, Typography } from "@mui/material";
import LocalShippingIcon from "@mui/icons-material/LocalShipping";
import SupportAgentIcon from "@mui/icons-material/SupportAgent";

const drawerWidth = 280;

export default function MenuDrawerCentral() {
  let navigate = useNavigate();

  const goToHomeCentral = () => {
    let path = "/HomeCentral";
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
            Hemodinamia
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

            <ListItemButton onClick={goToHomeCentral}>
              <ListItemIcon>
                <HomeIcon />
              </ListItemIcon>
              <ListItemText primary="Inicio" />
            </ListItemButton>

            <ListItemButton>
              <ListItemIcon>
                <LocalShippingIcon />
              </ListItemIcon>
              <ListItemText primary="Pedidos" />
            </ListItemButton>

            <ListItemButton>
              <ListItemIcon>
                <InventoryIcon />
              </ListItemIcon>
              <ListItemText primary="Gestion de stock" />
            </ListItemButton>

            <ListItemButton>
              <ListItemIcon>
                <SupportAgentIcon />
              </ListItemIcon>
              <ListItemText primary="Historial de atenciones" />
            </ListItemButton>

            <ListItemButton>
              <ListItemIcon>
                <AssessmentIcon />
              </ListItemIcon>
              <ListItemText primary="Reportes" />
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

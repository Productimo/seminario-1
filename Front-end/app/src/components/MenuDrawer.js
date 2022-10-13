import React from "react";
import { Box } from "@mui/system";
import List from "@mui/material/List";
import Drawer from "@mui/material/Drawer";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import { useNavigate } from "react-router-dom";
import LogoutIcon from "@mui/icons-material/Logout";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import ListItemButton from "@mui/material/ListItemButton";
import PersonAddIcon from "@mui/icons-material/PersonAdd";
import HomeIcon from "@mui/icons-material/Home";
import { Divider, ListItem, Typography } from "@mui/material";
import BackupTableSharpIcon from "@mui/icons-material/BackupTableSharp";
import AccountBoxOutlinedIcon from "@mui/icons-material/AccountBoxOutlined";
import FormatIndentIncreaseOutlinedIcon from "@mui/icons-material/FormatIndentIncreaseOutlined";

const drawerWidth = 240;

export default function MenuDrawer() {
  let navigate = useNavigate();

  const goToHome = () => {
    let path = "/Home";
    navigate(path);
  };

  const goToForm = () => {
    let path = "/Form";
    navigate(path);
  };

  const goToDashboard = () => {
    let path = "/Dashboard";
    navigate(path);
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

            {/*<ListItemButton onClick={goToDashboard}>
              <ListItemIcon>
                <BackupTableSharpIcon />
              </ListItemIcon>
              <ListItemText primary="Dashboard" />
            </ListItemButton>

            <ListItem>
              <ListItemIcon>
                <AccountBoxOutlinedIcon />
              </ListItemIcon>
              <ListItemText primary="Mi sesión" />
            </ListItem>*/}

            <ListItemButton onClick={goToHome}>
              <ListItemIcon>
                <HomeIcon />
              </ListItemIcon>
              <ListItemText primary="Inicio" />
            </ListItemButton>

            <ListItemButton onClick={goToForm}>
              <ListItemIcon>
                <FormatIndentIncreaseOutlinedIcon />
              </ListItemIcon>
              <ListItemText primary="Generar formulario" />
            </ListItemButton>

            {/*<ListItemButton>
              <ListItemIcon>
                <PersonAddIcon />
              </ListItemIcon>
              <ListItemText primary="Dar paciente de alta" />
            </ListItemButton>*/}

            <Divider />
            <ListItemButton>
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

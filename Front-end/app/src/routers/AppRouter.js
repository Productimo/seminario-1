import React from "react";
import { Router , Routes, Route} from "react-router-dom";
import Form from "../pages/Form";
import Home from "../pages/Home";
import Dashboard from "../pages/Dashboard";

export default function AppRouter(){
    return(
        <Routes>
            <Route path='/' element={<Home/>}/>
            <Route path='/Form' element={<Form/>}/>
            <Route path='/Dashboard' element={<Dashboard/>}/>
        </Routes>    
    )
}
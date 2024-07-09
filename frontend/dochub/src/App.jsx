import "./App.css";
import React from "react";
import { Routes, Route, BrowserRouter } from "react-router-dom";
import Home from "./doctor/home/Home";
import Registration from "./doctor/registration/Registration";
import Login from "./doctor/login/Login";
import Profile from "./doctor/profile/Profile";
import Appointments from "./doctor/appointments/Appointments";


function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/registration" element={<Registration />} />
                <Route path="/login" element={<Login />} />
                <Route path="/profile" element={<Profile />} />
                <Route path="/appointments" element={<Appointments />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;

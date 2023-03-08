import { BrowserRouter, Route, Routes } from "react-router-dom";

import NavBar from "./components/NavBar";
import HomePage from "./pages/HomePage";
import Completed from "./pages/Completed";

export default function App() {
    return (
        <div className="center-container">
            <BrowserRouter>
                <NavBar />
                <Routes>
                    <Route path="/" element={<HomePage />} />
                    <Route path="/completed" element={<Completed />} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

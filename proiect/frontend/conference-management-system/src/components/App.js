import { Routes, Route } from "react-router-dom";

import Login from "./Login/Login";
import LandingPage from "./LandingPage/LandingPage";
import Register from "./Register/Register";
import CreateConference from "./CreateConferece/CreateConference";
import Conference from "./CreateConferece/Conference";
import Home from "./Home";

function App() {
  return (
    <Routes>
      <Route path={"/"} element={<LandingPage />} />
      <Route path={"/login"} element={<Login />} />
      <Route path={"/register"} element={<Register />} />
      <Route path={"/home"} element={<Home />} />
      {/*<Route path={"/create"} element={<Conference />} />*/}
    </Routes>
  );
}

export default App;

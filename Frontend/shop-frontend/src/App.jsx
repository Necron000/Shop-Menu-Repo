import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { AuthProvider, useAuth } from "./AuthContext";
import Nav from "./components/Nav";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Items from "./pages/Items";
import AdminItems from "./pages/AdminItems";
import Orders from "./pages/Orders";
import "./App.css";

function RequireAuth({ children, adminOnly = false }) {
  const { isLoggedIn, isAdmin } = useAuth();
  if (!isLoggedIn) return <Navigate to="/login" replace />;
  if (adminOnly && !isAdmin) return <Navigate to="/" replace />;
  return children;
}

export default function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Nav />
        <main className="container">
          <Routes>
            <Route path="/" element={<Items />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route
              path="/orders"
              element={
                <RequireAuth>
                  <Orders />
                </RequireAuth>
              }
            />
            <Route
              path="/admin"
              element={
                <RequireAuth adminOnly>
                  <AdminItems />
                </RequireAuth>
              }
            />
          </Routes>
        </main>
      </BrowserRouter>
    </AuthProvider>
  );
}
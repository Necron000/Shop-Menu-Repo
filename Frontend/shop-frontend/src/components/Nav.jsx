import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../AuthContext";

export default function Nav() {
  const { auth, isLoggedIn, isAdmin, logout } = useAuth();
  const navigate = useNavigate();

  function handleLogout() {
    logout();
    navigate("/");
  }

  return (
    <nav className="nav">
      <div className="nav-links">
        <Link to="/">Shop</Link>
        {isLoggedIn && <Link to="/orders">My Orders</Link>}
        {isAdmin && <Link to="/admin">Admin</Link>}
      </div>
      <div className="nav-user">
        {isLoggedIn ? (
          <>
            <span>
              {auth.email} <em>({auth.role})</em>
            </span>
            <button onClick={handleLogout}>Log out</button>
          </>
        ) : (
          <>
            <Link to="/login">Log in</Link>
            <Link to="/register">Register</Link>
          </>
        )}
      </div>
    </nav>
  );
}
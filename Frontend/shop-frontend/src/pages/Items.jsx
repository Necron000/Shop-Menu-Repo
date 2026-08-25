import { useEffect, useState } from "react";
import { api } from "../api";
import { useAuth } from "../AuthContext";
import { Link } from "react-router-dom";

export default function Items() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [message, setMessage] = useState(null);

  const { auth, isLoggedIn } = useAuth();

  async function loadItems() {
    try {
      setItems(await api.getItems());
      setError(null);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadItems();
  }, []);

  async function handleBuy(item, quantity) {
    setMessage(null);
    setError(null);
    try {
      const order = await api.buyItem(item.id, auth.email, quantity);
      setMessage(
        `Order #${order.id} placed for ${quantity} × ${item.name} — status: ${order.status}`
      );
      await loadItems();
    } catch (err) {
      setError(err.message);
    }
  }

  if (loading) return <p>Loading items…</p>;

  return (
    <div>
      <h2>Shop</h2>
      {message && <p className="success">{message}</p>}
      {error && <p className="error">{error}</p>}
      {items.length === 0 && <p>No items available.</p>}

      <div className="grid">
        {items.map((item) => (
          <ItemCard
            key={item.id}
            item={item}
            canBuy={isLoggedIn}
            onBuy={handleBuy}
          />
        ))}
      </div>

      {!isLoggedIn && (
        <p className="hint">
          <Link to="/login">Log in</Link> to buy items.
        </p>
      )}
    </div>
  );
}

function ItemCard({ item, canBuy, onBuy }) {
  const [quantity, setQuantity] = useState(1);
  const [busy, setBusy] = useState(false);

  async function buy() {
    setBusy(true);
    await onBuy(item, quantity);
    setBusy(false);
  }

  const outOfStock = item.stock === 0;

  return (
    <div className="card">
      <h3>{item.name}</h3>
      <p className="desc">{item.description}</p>
      <p className="price">
        {item.price} {item.currency}
      </p>
      <p className={outOfStock ? "error" : "stock"}>
        {outOfStock ? "Out of stock" : `${item.stock} in stock`}
      </p>

      {canBuy && !outOfStock && (
        <div className="buy-row">
          <input
            type="number"
            min={1}
            max={item.stock}
            value={quantity}
            onChange={(e) => setQuantity(Number(e.target.value))}
          />
          <button onClick={buy} disabled={busy}>
            {busy ? "Buying…" : "Buy"}
          </button>
        </div>
      )}
    </div>
  );
}
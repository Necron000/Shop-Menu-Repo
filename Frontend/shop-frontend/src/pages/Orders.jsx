import { useEffect, useState } from "react";
import { api } from "../api";

export default function Orders() {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    api
      .getMyOrders()
      .then(setOrders)
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p>Loading orders…</p>;
  if (error) return <p className="error">{error}</p>;
  if (orders.length === 0) return <p>You have no orders yet.</p>;

  return (
    <div>
      <h2>My orders</h2>
      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Item</th>
            <th>Qty</th>
            <th>Amount</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {orders.map((order) => (
            <tr key={order.id}>
              <td>{order.id}</td>
              <td>{order.itemId}</td>
              <td>{order.quantity}</td>
              <td>
                {order.amount} {order.currency}
              </td>
              <td>
                <span className={`badge ${order.status?.toLowerCase()}`}>
                  {order.status}
                </span>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
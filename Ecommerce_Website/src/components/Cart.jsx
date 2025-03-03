// components/Cart.jsx
import React from 'react';

const Cart = ({ item, onRemoveFromCart }) => {
  return (
    <div className="flex justify-between items-center mb-2">
      <span>{item.name} - ${item.price}</span>
      <button
        onClick={() => onRemoveFromCart(item)}
        className="bg-red-500 text-white px-2 py-1 rounded"
      >
        Remove
      </button>
    </div>
  );
};

export default Cart;
// pages/CartPage.jsx
import React from 'react';
import { useCart } from '../context/CartContext';
import Cart from '../components/Cart';

const CartPage = () => {
  const { cart, dispatch } = useCart();

  const handleRemoveFromCart = (product) => {
    dispatch({ type: 'REMOVE_FROM_CART', payload: product });
  };

  const totalPrice = cart.reduce((total, item) => total + item.price, 0);

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4">Cart</h1>
      {cart.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <div>
          {cart.map(item => (
            <Cart
              key={item.id}
              item={item}
              onRemoveFromCart={handleRemoveFromCart}
            />
          ))}
          <div className="mt-4">
            <strong>Total: ${totalPrice}</strong>
          </div>
        </div>
      )}
    </div>
  );
};

export default CartPage;
// components/Navbar.jsx
import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => {
  return (
    <nav className="bg-blue-500 p-4">
      <div className="container mx-auto flex justify-between">
        <Link to="/" className="text-white text-lg font-bold">Home</Link>
        <Link to="/cart" className="text-white text-lg font-bold">Cart</Link>
      </div>
    </nav>
  );
};

export default Navbar;
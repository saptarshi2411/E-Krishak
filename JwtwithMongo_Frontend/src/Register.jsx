import { useState } from 'react';

function Register() {
  const [formData, setFormData] = useState({
    email: '',
    username: '',
    password: '',
    address: '',
    mobileno: '',
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData),
      });
      if (response.ok) {
        alert('Registration successful!');
      } else {
        alert('Registration failed!');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div>
      <h2>Register</h2>
      <form onSubmit={handleSubmit}>
        <input name="email" placeholder="Email" value={formData.email} onChange={handleChange} required />
        <input name="username" placeholder="Username" value={formData.username} onChange={handleChange} required />
        <input name="password" type="password" placeholder="Password" value={formData.password} onChange={handleChange} required />
        <input name="address" placeholder="Address" value={formData.address} onChange={handleChange} />
        <input name="mobileno" placeholder="Mobile Number" value={formData.mobileno} onChange={handleChange} />
        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default Register;

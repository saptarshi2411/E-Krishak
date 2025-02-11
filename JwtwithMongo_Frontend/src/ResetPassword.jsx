import { useState } from 'react';

function ResetPassword() {
  const [formData, setFormData] = useState({
    username: '',
    newPassword: '',
    otp: '',
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/reset-password', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData),
      });
      if (response.ok) {
        alert('Password reset successful!');
      } else {
        alert('Failed to reset password! Invalid OTP or expired.');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div>
      <h2>Reset Password</h2>
      <form onSubmit={handleSubmit}>
        <input name="username" placeholder="Username" value={formData.username} onChange={handleChange} required />
        <input name="newPassword" type="password" placeholder="New Password" value={formData.newPassword} onChange={handleChange} required />
        <input name="otp" placeholder="OTP" value={formData.otp} onChange={handleChange} required />
        <button type="submit">Reset Password</button>
      </form>
    </div>
  );
}

export default ResetPassword;

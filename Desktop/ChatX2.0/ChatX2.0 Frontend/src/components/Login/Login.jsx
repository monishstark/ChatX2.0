// src/pages/Login.js
import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { requestOtp, verifyOtp } from '../../redux/userSlice';
import './Login.css';

const Login = () => {
  const [phoneNumber, setPhoneNumber] = useState('');
  const [otp, setOtp] = useState(['', '', '', '']);
  const dispatch = useDispatch();
  const authStatus = useSelector((state) => state.user.status);

  const handlePhoneNumberChange = (e) => {
    const value = e.target.value.replace(/\D/g, ''); // Allow only numbers
    setPhoneNumber(value);
  };

  const handleOtpChange = (index, value) => {
    if (!/^\d$/.test(value) && value !== '') return; // Allow only single digits
    const newOtp = [...otp];
    newOtp[index] = value;
    setOtp(newOtp);

    // Focus on the next input box if a digit is entered
    if (value && index < otp.length - 1) {
      document.getElementById(`otp-${index + 1}`).focus();
    }
  };

  const handleRequestOTP = (e) => {
    e.preventDefault();
    dispatch(requestOtp(phoneNumber));
  };

  const handleVerifyOTP = (e) => {
    e.preventDefault();
    dispatch(verifyOtp({ phoneNumber, data: otp.join('') })); 
  };

  return (
    <div className="login-container">
      <div className="form-container">
        <h2>{authStatus !== 'otpSent' ? 'Login' : 'Enter OTP'}</h2>
        {authStatus !== 'otpSent' ? (
          <form onSubmit={handleRequestOTP}>
            <input
              type="text"
              value={phoneNumber}
              onChange={handlePhoneNumberChange}
              placeholder="Enter phone number"
              maxLength="10"
              className="input-field"
            />
            <button type="submit" className="submit-button">
              Request OTP
            </button>
          </form>
        ) : (
          <form onSubmit={handleVerifyOTP}>
            <div className="otp-container">
              {otp.map((value, index) => (
                <input
                  key={index}
                  id={`otp-${index}`}
                  type="text"
                  value={value}
                  onChange={(e) => handleOtpChange(index, e.target.value)}
                  maxLength="1"
                  className="otp-box"
                />
              ))}
            </div>
            <button type="submit" className="submit-button">
              Verify OTP
            </button>
          </form>
        )}
      </div>
    </div>
  );
};

export default Login;

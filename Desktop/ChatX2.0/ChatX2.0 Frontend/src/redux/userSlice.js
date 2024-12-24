// src/store/userSlice.js
import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const requestOtp = createAsyncThunk('auth/requestOtp', async (phone) => {
  const response = await axios.post('http://localhost:8080/auth/sendOtp', { phone });
  return response.data;
});

export const verifyOtp = createAsyncThunk('auth/verifyOtp', async (data) => {
  const phone=data["phoneNumber"]
  const otpNO=data["data"]
  console.log(phone,otpNO,"verifyotp")
  const response = await axios.post('http://localhost:8080/auth/verifyOtp', { phone, otpNO });
  return response.data;
});

export const userSlice = createSlice({
  name: 'user',
  initialState: {
    userdetails: null,
    token: localStorage.getItem('token') || null,
    status: 'idle',
    error: null,
  },
  reducers: {
    logout: (state) => {
      state.userdetails = null;
      state.token = null;
      localStorage.removeItem('token');
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(requestOtp.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(requestOtp.fulfilled, (state) => {
        state.status = 'otpSent';
      })
      .addCase(requestOtp.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
      .addCase(verifyOtp.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(verifyOtp.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.token = action.payload.token;
        state.userdetails = action.payload.userDetails;
        console.log(action.payload.userDetails,"userdetails");
        localStorage.setItem('token', action.payload.token);
      })
      .addCase(verifyOtp.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      });
  },
});

export const { logout } = userSlice.actions;

export default userSlice.reducer;


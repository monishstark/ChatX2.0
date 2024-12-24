// src/store/store.js
import { configureStore } from '@reduxjs/toolkit';
import userReducer from './userSlice'; // Import reducer, not the entire slice
import chatReducer from './chatSlice'; 

const store = configureStore({
  reducer: {
    user: userReducer, 
    chat: chatReducer,
  },
});

export default store;

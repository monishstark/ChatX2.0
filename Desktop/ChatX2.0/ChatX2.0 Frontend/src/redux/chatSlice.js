
import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const fetchChatList = createAsyncThunk('chats/fetchChatList', async (_, { getState }) => {
  const token = getState().user.token; 
  console.log(token)
  const response = await axios.get('http://localhost:8080/chats/getChats', {
    headers: {
      Authorization: token, 
    },
  });
  console.log(response.data, "fetchchatlist");
  return response.data;
});

export const fetchChat = createAsyncThunk('chats/fetchChat', async (chatId) => {
  const response = await axios.get(`http://localhost:8080/message/getMessages?inboxId=${chatId}`); // Your endpoint here
  console.log(response.data,"fetchchat");

  return response.data;
});

export const addMessage = createAsyncThunk('chats/sendMessage', async (message,) => {
  const response = await axios.post('http://localhost:8080/message/sendMessage', message); // Your endpoint here
  console.log(response.data,"sendmessage");
  return response.data;
});

const chatSlice = createSlice({
    name: 'chats',
    initialState: {
      chatList: [],
      messages: [], 
      selectedChatId: null,
      status: 'idle',
        error: null,
    },
    reducers: {
      setSelectedChatId: (state, action) => {
        state.selectedChatId = action.payload;
      },
      pushMessage:(state,action)=>{
        state.messages.push(action.payload.body);
      }

    },
    
    extraReducers: (builder) => {
      builder
        .addCase(fetchChatList.pending, (state) => {
          state.status = 'loading';
        })
        .addCase(fetchChatList.fulfilled, (state, action) => {
          state.status = 'succeeded';
          state.chatList = action.payload;
        })
        .addCase(fetchChatList.rejected, (state, action) => {
          state.status = 'failed';
          state.error = action.error.message;
        })
        .addCase(fetchChat.fulfilled, (state, action) => {
          state.messages = action.payload;

        });
    },
  });
  
  export const { setSelectedChatId,pushMessage } = chatSlice.actions;
  export default chatSlice.reducer;
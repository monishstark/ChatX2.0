import React, { useState, useEffect } from 'react';
import { Avatar, IconButton } from '@mui/material';
import MoreHorizIcon from '@mui/icons-material/MoreHoriz';
import SendRoundedIcon from '@mui/icons-material/SendRounded';
import MicNoneOutlinedIcon from '@mui/icons-material/MicNoneOutlined';
import TimerOutlinedIcon from '@mui/icons-material/TimerOutlined';
import { motion, AnimatePresence } from 'framer-motion';
import Message from './Message';
import { fetchChat } from '../../redux/chatSlice'; // Import your action
import { useSelector, useDispatch } from 'react-redux';
import './ChatWindow.css';
import ChatInput from './ChatInput';
import {useChatWebSocket} from '../../websocket/useWebSocket';

const ChatWindow = () => {
    const chatId = useSelector((state) => state.chat.selectedChatId.chatId);
    const chatList = useSelector((state) => state.chat.chatList);
    const chatName = useSelector((state) => state.chat.chatList.find((chat) => chat.inboxId === chatId)?.name);
    const messages = useSelector((state) => state.chat.messages);
    const chatPhoto = useSelector((state) => state.chat.chatList.find((chat) => chat.inboxId === chatId)?.avatar);
    const userId = useSelector((state) => state.user.userdetails.userid);
    const userdetail = useSelector((state) => state.user.userdetails);
    const dispatch = useDispatch();
    console.log(chatId,chatName,messages,userId,chatPhoto)
    useEffect(() => {
        if (chatId) {
            // Fetch messages for the selected chatId
            dispatch(fetchChat(chatId));
        }
    }, [chatId, dispatch]);

    

    return (
        <div className="chatWindow">

            <div className="chatWindow__header">
                <div className="chatWindow__headerDetails">
                    <Avatar src={chatPhoto} />
                    <div className="chatWindow__headerInfo">
                        <h4>{chatName || 'Select a chat'}</h4>
                    </div>
                </div>
                <IconButton>
                    <MoreHorizIcon />
                </IconButton>
            </div>

            {/* Messages Section */}
            <div className="chatWindow__messages">
                <AnimatePresence>
                    {messages.map((message) => (
                        <motion.div
                        key={message.messageId}
                        initial={{ opacity: 0, y: 20 }}
                        animate={{ opacity: 1, y: 0 }}
                        exit={{ opacity: 0, y: -20 }}
                    > 
                        <Message
                            key={message.id}
                            message={message}
                            isSender={message.userId===userId} // Check if the message is from the current user
                        />
                        </motion.div>
                    ))}
                </AnimatePresence>
            </div>

            <ChatInput />
            
            
        </div>
    );
};

export default ChatWindow;

// SidebarThread.js
import React from 'react';
import { Avatar } from "@mui/material";
import './SidebarThread.css';
import { useDispatch } from 'react-redux';
import { setSelectedChatId } from '../../redux/chatSlice';


const SidebarThread = ({ chat }) => {

    const dispatch = useDispatch();

    const handleCLick = (id,reciverId) => {
        console.log("chatid",id);
        dispatch(setSelectedChatId({chatId:id,selectedUser:reciverId}));
    }
    return (
        <div className="sidebarThread" onClick={()=>handleCLick(chat.inboxId,chat.reciverId)}>
            <Avatar src={chat.avatar} />
            <div className="sidebarThread__info">
                <h2>{chat.name}</h2>
                <p>{chat.lastMessage}</p>
            </div>
        </div>
    );
};

export default SidebarThread;

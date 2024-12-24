import React from 'react';
import Sidebar from './SideBar';
import ChatWindow from './ChatWindow';
import { useSelector } from 'react-redux';
import './Telegram.css';

const Telegram = () => {
    // Get the selected chat ID from the Redux store
    const chatId = useSelector((state) => state.chat.selectedChatId);

    return (
        <div className="telegram">
            <Sidebar />
            {chatId ? (
                <ChatWindow />
            ) : (
                <div className="emptyCanvas">
                    {/* Empty canvas to show when no chat is selected */}
                    <p>Select a chat to start messaging</p>
                </div>
            )}
        </div>
    );
};

export default Telegram;

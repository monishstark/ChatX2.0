// Message.js
import React from 'react';
import './Message.css';

const Message = ({ message, isSender }) => {
    return (
        <div className={`message-container ${isSender ? "message--sender" : "message--receiver"}`}>
            <div className="message">
                <p>{message.message}</p>
                <span className="message__timestamp">{message.createdAt}</span>
            </div>
        </div>
    );
};

export default Message;

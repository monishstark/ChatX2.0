import React, { useState } from "react";
import IconButton from "@mui/material/IconButton";
import SendRoundedIcon from "@mui/icons-material/SendRounded";
import MicNoneOutlinedIcon from "@mui/icons-material/MicNoneOutlined";
import TimerOutlinedIcon from "@mui/icons-material/TimerOutlined";
import { useChatWebSocket } from "../../websocket/useWebSocket";
import { useSelector } from "react-redux";

const ChatInput = () => {
  const chatId = useSelector((state) => state.chat.selectedChatId?.chatId);
  const userId = useSelector((state) => state.user.userdetails?.userid);
  const receiverId = useSelector((state) => state.chat.selectedChatId?.selectedUser);

  const { sendChatMessage } = useChatWebSocket(chatId);
  const [message, setMessage] = useState("");

  const handleInputChange = (e) => {
    setMessage(e.target.value);
  };

  const handleSend = (e) => {
    e.preventDefault();
    console.log(chatId, userId, receiverId);
    if (message.trim() && chatId && userId && receiverId) {
      const payload = {
        inboxId: chatId,
        senderId: userId,
        receiverId,
        message,
      };
      console.log(payload);
      sendChatMessage(payload); // Use WebSocket to send messages
      setMessage("");
    }
  };
  

  return (
    <div className="chatWindow__input">
      <form onSubmit={handleSend} id="messageInput">
        <input
          placeholder="Enter a message..."
          type="text"
          value={message}
          onChange={handleInputChange}
        />
        <IconButton>
          <TimerOutlinedIcon />
        </IconButton>
        <IconButton type="submit">
          <SendRoundedIcon />
        </IconButton>
        <IconButton>
          <MicNoneOutlinedIcon />
        </IconButton>
      </form>
    </div>
  );
};

export default ChatInput;

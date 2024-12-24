import { useEffect, useRef } from "react";
import { Client } from "@stomp/stompjs";
import { useDispatch } from 'react-redux';
import { pushMessage } from '../redux/chatSlice';

export const useChatWebSocket = (chatId) => {
  const client = useRef(null);
  const dispatch = useDispatch();

  useEffect(() => {
    // Initialize Stomp.js Client with native WebSocket
    client.current = new Client({
      brokerURL: "ws://localhost:8080/ws-chat", // Native WebSocket URL
      debug: (str) => console.log(str), // Optional debugging
      reconnectDelay: 5000, // Attempt to reconnect after 5 seconds
    });

    client.current.onConnect = () => {
      console.log("Connected to WebSocket");

      // Subscribe to a chat topic if chatId is provided
      if (chatId) {
        client.current.subscribe(`/topic/messages/${chatId}`, (message) => {
          
          const receivedMessage = JSON.parse(message.body);
          console.log("Message received:", receivedMessage);
          // Dispatch action to Redux or update the UI
          dispatch(pushMessage(receivedMessage));
        });
      }
    };

    client.current.onStompError = (frame) => {
      console.error("WebSocket error:", frame.headers["message"]);
      console.error("Details:", frame.body);
    };

    // Activate WebSocket connection
    client.current.activate();

    return () => {
      // Deactivate WebSocket connection on cleanup
      if (client.current) {
        client.current.deactivate();
      }
    };
  }, [chatId]);

  const sendChatMessage = (message) => {
    if (client.current && client.current.connected) {
      client.current.publish({
        destination: `/app/sendMessage/${chatId}`, // Matches backend MessageMapping
        body: JSON.stringify(message),
      });
    } else {
      console.error("WebSocket client not connected");
    }
  };

  return { sendChatMessage };
};

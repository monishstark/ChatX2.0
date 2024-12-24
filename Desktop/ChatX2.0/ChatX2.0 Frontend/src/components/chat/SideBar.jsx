import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fetchChatList } from "../../redux/chatSlice";
import SearchIcon from "@mui/icons-material/Search";
import BorderColorOutlinedIcon from "@mui/icons-material/BorderColorOutlined";
import { Avatar, IconButton } from "@mui/material";
import SidebarThread from "./SidebarThread";
import PhoneOutlinedIcon from "@mui/icons-material/PhoneOutlined";
import QuestionAnswerOutlinedIcon from "@mui/icons-material/QuestionAnswerOutlined";
import SettingsIcon from "@mui/icons-material/Settings";
import './SideBar.css'


const Sidebar = () => {
  const dispatch = useDispatch();
  const chatList = useSelector((state) => state.chat.chatList);
  const status = useSelector((state) => state.chat.status);
  const error = useSelector((state) => state.chat.error);

  useEffect(() => {
    if (status === 'idle') {
      dispatch(fetchChatList());
    }
  }, [status, dispatch]);

  return (
    <div className="sidebar">
      <div className="sidebar__header">
        <div className="sidebar__search">
          <SearchIcon className="sidebar__searchIcon" />
          <input placeholder="Search" className="sidebar__input" />
        </div>
        <IconButton variant="outlined" id="sidebar__button">
          <BorderColorOutlinedIcon onClick={() => console.log("clicked")} />
        </IconButton>
      </div>

      <div className="sidebar__threads">
        {status === 'loading' ? (
          <p>Loading chats...</p>
        ) : status === 'failed' ? (
          <p>Error loading chats: {error}</p>
        ) : chatList.length > 0 ? (
          chatList.map((chat) => (
            <SidebarThread key={chat.id} chat={chat}  />
          ))
        ) : (
          <p>No chats available</p>
        )}
      </div>

      <div className="sidebar__bottom">
        <Avatar className="sidebar__bottom_avatar" />
        <IconButton>
          <PhoneOutlinedIcon />
        </IconButton>
        <IconButton>
          <QuestionAnswerOutlinedIcon />
        </IconButton>
        <IconButton>
          <SettingsIcon />
        </IconButton>
      </div>
    </div>
  );
};

export default Sidebar;

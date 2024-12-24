import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Login from './components/Login/Login.jsx';
import { useDispatch, useSelector } from 'react-redux';
import Telegram from './components/chat/Telegram.jsx';
import './App.css'
const App=()=>{
  
  const token = useSelector((state) => state.user.token);
  return (
    <div className="App">
      {token ? <Telegram /> : <Login />}
    </div>
  );
}

export default App;
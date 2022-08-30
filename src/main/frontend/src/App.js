import logo from './logo.svg';
import './App.css';
import  {useState, useEffect} from "react";
import axios from "axios";

function App() {

  const [users, setUsers] = useState([]);

  const fecthUserProfiles = async () =>{
    const data = await axios.get("http://localhost:8080/api/v1/user-profile")
    setUsers(data.data)
    console.log(data);
  }

  useEffect( () => {
    fecthUserProfiles();
    console.log("users: ", users);

  }, [])

  return (
    <div className="App">
      <h1> hello</h1>
      {users.map((user) =>{
        return <>
        <h1>{user.username}</h1>
        <p>{user.userProfileId}</p>
        <h1>{user.userProfileImageLink}</h1>
        </>
      })}
    </div>
  );
}

export default App;

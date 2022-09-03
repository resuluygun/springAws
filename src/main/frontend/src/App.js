import logo from './logo.svg';
import './App.css';
import  {useState, useEffect} from "react";
import axios from "axios";
import Dropzone from "./Dropzone"

function App() {

  const [users, setUsers] = useState([]);

  const fecthUserProfiles = async () =>{
    const data = await axios.get("http://localhost:8080/api/v1/user-profile")
    setUsers(data.data)
  }

  useEffect( () => {
    fecthUserProfiles();
  }, [])

  return (
    <div className="App">
      {users.map((user, index) =>{
        return (
          <div key={index}>
            <br/>
            <h1>{user.username}</h1>
            <p>{user.userProfileId}</p>
            { user.userProfileId ? 
              <img src={`http://localhost:8080/api/v1/user-profile/${user.userProfileId}/image/download`} /> : 
              null
            }
            <Dropzone {...user}/>
            <br/>
        </div>
        )
      })}
    </div>
  );
}

export default App;

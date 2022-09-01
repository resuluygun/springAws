import React from 'react'
import Dropzone from 'react-dropzone'
import axios from "axios";

function myZone  ({userProfileId}) {
    return <Dropzone onDrop={acceptedFiles => {
        console.log(acceptedFiles)
        const [file] = acceptedFiles;
        const formData = new FormData();
        formData.append("file",file); // this file keyword came from java request
         axios.post(
            `http://localhost:8080/api/v1/user-profile/${userProfileId}/image/upload`,
            formData,
            {
                "headers" : {
                    "Content-Type": "multipart/form-data"
                }
            }
        ).then((response) => {
            console.log("response: ", response)

        }).catch((err) => {
            console.log("err: ",err)
        });

        }}>
    {({getRootProps, getInputProps, isDragActive}) => (
      <section>
        <div {...getRootProps()}>
          <input {...getInputProps()} />
          {
          isDragActive ? 
          (<p>Drop the files here ...</p>) :
          (<p>Drag 'n' drop profile image, or click to select profile image</p>)
        }
          
        </div>
      </section>
    )}
  </Dropzone>
  
} 

export default myZone;
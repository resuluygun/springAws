import React from 'react'
import Dropzone from 'react-dropzone'

function myZone  () {
    return <Dropzone onDrop={acceptedFiles => console.log(acceptedFiles)}>
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
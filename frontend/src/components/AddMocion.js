/*import React, { useState } from "react";
import MDataService from "../services/MocionService";
import TipoMDataService from "../services/Tipo_MocionService";
const AddMocion = () => {
  const initialMState = {
    id: null,
    id_Mocion: "",
    texto: "",
    //id_tipo : null,
    //fecha: "",
  };
  const [mocion, setM] = useState(initialMState);
  const [submitted, setSubmitted] = useState(false);
  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setM({ ...mocion, [name]: value });
  };
  const saveM = () => {
    var data = {
        id_Mocion: mocion.id_Mocion,
        texto: mocion.texto,
        //id_tipo: mocion.id_tipo,
        //fecha: mocion.fecha,
    };
    MDataService.create(data)
      .then((response) => {
        setM({
          id_Mocion: response.data.id_Mocion,
          texto: response.data.texto,
          //id_tipo: response.data.id_tipo,
          //fecha: response.data.fecha,
        });
        setSubmitted(true);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };
//-----------------------------------------------------------------------


//----------------------------------------------------------------------
  const newM = () => {
    setM(initialMState);
    setSubmitted(false);
  };
   return (
   <div className="submit-form">
      {submitted ? (
        <div>
          <h4>Almacenado Corretamente</h4>
          <button className="btn btn-success" onClick={newM}>
            Agregar
          </button>
        </div>
      ) : (
        <div>
          <div className="form-group">
            <label htmlFor="id_Mocion">Id Moción</label>
            <input
              type="text"
              className="form-control"
              id="id_Mocion"
              required
              value={mocion.id_Mocion}
              onChange={handleInputChange}
              name="id_Mocion"
            />
          </div>
          <div className="form-group">
            <label htmlFor="texto">Texto</label>
            <input
              type="text"
              className="form-control"
              id="texto"
              required
              value={mocion.texto}
              onChange={handleInputChange}
              name="texto"
            />
          </div>
            <div className="form-group">
            <label htmlFor="id_tipo">Tipo de mocion</label>
            <input
              type="text"
              className="form-control"
              id="id_tipo"
              required
              value={mocion.id_tipo}
              onChange={handleInputChange}
              name="id_tipo"
                />
      </div>
          <div className="form-group">
            <label htmlFor="fecha">Fecha</label>
            <input
              type="text"
              className="form-control"
              id="fecha"
              required
              value={mocion.fecha}
              onChange={handleInputChange}
              name="fecha"
            />
      </div>
          <button onClick={saveM} className="btn btn-success">
            Enviar
          </button>
        </div>
      )}
      </div>
  ); 
}; 
export default AddMocion;*/

import React, { useState, useEffect } from "react";
import MocionService from "../services/MocionService";
import TipoMocionService from "../services/Tipo_MocionService";

const AddMocion = () => {
  const initialMocionState = {
    id: null,
    id_Mocion: "",
    texto: "",


  };
  const initialTipoMocion ={
    id:null,
    id_tipo_Mocion:"",
    descripcion:""
  }
  const [mocion, setMocion] = useState(initialMocionState);
  const [submitted, setSubmitted] = useState(false);
  const [currentTipoMocion, setCurrentTipoMocion] = useState(initialTipoMocion);
  const [tipoMociones, settipoMociones] = useState([]);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setMocion({ ...mocion, [name]: value });
  };
  const saveMocion = () => {
    var data = {
      texto: mocion.texto,
      id_Mocion: mocion.id_Mocion,
    };
    MocionService.create(data,currentTipoMocion.id_tipo_Mocion.id)
      .then((response) => {
        setMocion({
          texto: response.data.texto,
          id_Mocion: response.data.id_Mocion,
        });
        setSubmitted(true);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  let valores = tipoMociones.map((tipoMocion, index)=> {
    return (
    console.log(tipoMocion),
    <option key={index} value={tipoMocion.id_tipo_Mocion.id}>
      
      {tipoMocion.descripcion}
    </option>
    );
    
  },this);

  useEffect(() => {
    retrievetipoMociones();
  }, []);

  
  const handleInputChange2 = (event) => {
    const { name, value } = event.target;
    setCurrentTipoMocion({ ...currentTipoMocion, [name]: value });
  };
  const newMocion = () => {
    setMocion(initialMocionState);
    setSubmitted(false);
  };
  const retrievetipoMociones = () => {
    TipoMocionService.getAll()
      .then((response) => {
        settipoMociones(response.data);
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  };
  return (
    <div className="submit-form">
      {submitted ? (
        <div>
          <h4>Almacenado Corretamente</h4>
          <button className="btn btn-success" onClick={newMocion}>
            Agregar
          </button>
        </div>
      ) : (
        <div>
          <div className="form-group">
            <label htmlFor="texto">Texto</label>
            <input
              type="text"
              className="form-control"
              id="texto"
              required
              value={mocion.texto}
              onChange={handleInputChange}
              name="texto"
            />
          </div>
          <div className="form-group">
            <label htmlFor="id_Mocion">Id Mocion</label>
            <input
              type="text"
              className="form-control"
              id="id_Mocion"
              required
              value={mocion.id_Mocion}
              onChange={handleInputChange}
              name="id_Mocion"
            />
          </div>   
          <div className="form-group"> 
              <label htmlFor="id">Tipo de Moción</label>
              <select name = "id" id="id" placeholder="seleccione una opcion"  onChange={handleInputChange2}>
              <option value= "0" >escoja</option>
                {valores}
              </select>
            </div>
          <button onClick={saveMocion} className="btn btn-success">
            Enviar
          </button>
        </div>
      )}
    </div>
  );
};
export default AddMocion;
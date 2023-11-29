import "./App.css";
import Home from "../../fast_development_of_frontend/fast-html/src/pages/Home";

function App() {
  function borrarTexto() {
    var inputElement = document.getElementById("miInput");
    inputElement.value = ""; // Vaciar el contenido del input al hacer clic en él
  }

  return (
    <>
      <div class="card" style={{ width: "30rem", margin: "1rem" }}>
        <div class="card-body">
          <p style={{ fontWeight: "bold", fontSize: "1.5rem" }}>
            REVISION DE INCIDENTE
          </p>
          <p
            style={{ fontWeight: "bold", fontSize: "1.2rem" }}
            class="card-text"
          >
            Baños - Jumbo Almagro
          </p>
          <p style={{ fontWeight: "bold" }} class="card-text">
            Ingrese una descripción si el incidente se encuentra cerrado
          </p>
          <div>
            <textarea
              class="form-control"
              id="textTareaCierreIncidente"
              rows="5"
              style={{ resize: "vertical" }}
              placeholder={
                "Registra un nuevo incidente en tus comunidades. Completa los detalles necesarios, como ubicación, descripción del incidente y cualquier información relevante. Ayúdanos a mejorar la accesibilidad y la calidad de los servicios en tu ciudad."
              }
            ></textarea>
          </div>

          <div class="container text-center">
            <div class="row d-flex justify-content-center">
              <div class="col-12 col-md-6" style={{ padding: 10 }}>
                <a
                  href="#"
                  class="btn btn-primary btn-block btn-lg font-weight-bold"
                >
                  SIGUE ABIERTO
                </a>
              </div>
              <div class="col-12 col-md-6" style={{ padding: 10 }}>
                <a
                  href="#"
                  class="btn btn-primary btn-block btn-lg font-weight-bold"
                >
                  CERRAR
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
      {/* pasado! */}

      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>

      <div class="card" style={{ width: "30rem", margin: "1rem" }}>
        <div class="card-body">
          <div style={{}}>
            <p style={{ fontWeight: "bold", fontSize: "1.5rem", margin: 0 }}>
              Baños - Jumbo Almagro
            </p>
            <p style={{ fontSize: "1.1rem" }}>🟢 Habitada</p>
          </div>

          <div style={{ textAlign: "center" }}>
            <a
              href="#"
              class="btn btn-primary"
              style={{
                width: "14rem",
                margin: "1rem",
                fontWeight: "bold",
                fontSize: "1.2rem",
              }}
            >
              Reportar incidente
            </a>
          </div>
        </div>
      </div>
      {/* pasado */}

      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>

      <div class="card" style={{ width: "30rem", margin: "1rem" }}>
        <div class="card-body">
          <div style={{ textAlign: "right" }}>
            <p style={{ fontSize: "1rem" }}>10/06/2023 : 23:45 hs</p>
          </div>
          <div style={{}}>
            <p style={{ fontWeight: "bold", fontSize: "1.5rem", margin: 0 }}>
              INCIDENTE
            </p>
            <p style={{ fontSize: "1.1rem" }}>🟢 Abierto</p>
          </div>

          <p
            style={{ fontWeight: "bold", fontSize: "1.2rem" }}
            class="card-text"
          >
            Baños - Jumbo Almagro
          </p>
          <p style={{}} class="card-text">
            Los baños pierden agua y necesitan ser reparados. El incidente bla
            lorem lorem lorem itstu lorem itesu llorem lala lprem itsu marl
            lorem itsu lorem blue label lorem blue label blue label lorem lorem
          </p>

          <div style={{ textAlign: "right" }}>
            <a
              href="#"
              class="btn btn-primary"
              style={{
                width: "7rem",
                margin: "1rem",
                fontWeight: "bold",
                fontSize: "1.2rem",
              }}
            >
              Cerrar
            </a>
          </div>
        </div>
      </div>
      {/* pasada */}

      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>

      <div class="card" style={{ width: "30rem", margin: "1rem" }}>
        <div class="card-body">
          <p style={{ fontWeight: "bold", fontSize: "1.5rem" }}>
            CERRAR INCIDENTE
          </p>
          <p
            style={{ fontWeight: "bold", fontSize: "1.2rem" }}
            class="card-text"
          >
            Baños - Jumbo Almagro
          </p>
          <p style={{ fontWeight: "bold" }} class="card-text">
            Ingrese una descripción para el cierre del incidente
          </p>
          <div>
            <textarea
              class="form-control"
              id="textTareaCierreIncidente"
              rows="5"
              style={{ resize: "vertical" }}
              placeholder={
                "Registra un nuevo incidente en tus comunidades. Completa los detalles necesarios, como ubicación, descripción del incidente y cualquier información relevante. Ayúdanos a mejorar la accesibilidad y la calidad de los servicios en tu ciudad."
              }
            ></textarea>
          </div>

          <a
            href="#"
            class="btn btn-primary"
            style={{
              width: "25rem",
              margin: "1rem",
              fontWeight: "bold",
              fontSize: "1.2rem",
            }}
          >
            Cerrar incidente
          </a>
        </div>
      </div>
      {/* pasada, cierre incidente */}

      <div>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>
        <br></br>

      

        <Home></Home>
      </div>
    </>
  );
}

export default App;

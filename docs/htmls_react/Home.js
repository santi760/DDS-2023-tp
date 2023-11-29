import FootBar from "../../fast_development_of_frontend/fast-html/src/components/FootBar.js";
import Navbar from "../../fast_development_of_frontend/fast-html/src/components/Navbar.js";

export default function Home() {
  const url = "https://cdn-icons-png.flaticon.com/512/6159/6159698.png";

    const redirigirRegistro = () => {
        window.location.href='https://www.google.com'
    }



  return (
    <>


    


      <Navbar />

      <section className="primer-home">
        <div className="contenido-container">
          <div className="texto-section-01-home-container">
            <h2 className="titulo-blanco">
              EL FUTURO DE LA MOVILIDAD REDUCIDA
            </h2>
            <div className="subtitulo-futuro">
              <p className="texto-blanco">
                Facilitamos la movilidad de personas con movilidad reducida al
                proporcionar información actualizada sobre la accesibilidad de
                servicios públicos y establecimientos en la ciudad.
              </p>
            </div>
          </div>

          <div className="video-container">
            <iframe
              width="560"
              height="315"
              src="https://www.youtube.com/watch?v=dXzTPSw0nhw&ab_channel=MarioPower"
              frameborder="0"
              allowfullscreen
            ></iframe>
          </div>
        </div>
      </section>

      {/* ----------- SEGUNDA SECCION -------------  */}

      <section class="section-2">
        <div class="section-2-item">
          <h2>AHORRA TIEMPO</h2>
          <p>CONOCE LOS INCIDENTES EN TUS SERVICIOS DE INTERES</p>
          <div>
            <img
              class=""
              style={{ width: 200 }}
              src="https://cdn-icons-png.flaticon.com/512/6159/6159698.png"
              alt="Descripción de la imagen"
            />
          </div>
        </div>
        <div class="section-2-item">
          <div>
            <img
              style={{ width: 200 }}
              src="https://cdn-icons-png.flaticon.com/512/6159/6159698.png"
              alt="Descripción de la imagen"
            />
          </div>
          <h2>SE PARTE</h2>
          <p>PARTICIPA EN LAS COMUNIDADES QUE QUIERAS </p>
        </div>
        <div class="section-2-item">
          <h2>SIEMPRE CONECTADOS</h2>
          <p>PERSONALIZA TUS MEDIOS Y HORARIOS DE NOTIFICACION PREFERIDOS</p>
          <div>
            <img
              style={{ width: 200 }}
              src="https://cdn-icons-png.flaticon.com/512/6159/6159698.png"
              alt="Descripción de la imagen"
            />
          </div>
        </div>
      </section>

      {/* ----------- TERCERA SECCION -------------  */}
      <section>
        <div>
          <h2>FÁCIL DE USAR, RESULTADOS INMEDIATOS</h2>
        </div>

        <div>
          <ul>
            <li>Selecciona tu ubicación o comunidad de interés.</li>
            <li>
              {" "}
              Explora la información actualizada sobre servicios y
              establecimientos cercanos.{" "}
            </li>
            <li>
              Recibe notificaciones y contribuye informando sobre incidentes
              para mejorar la calidad de los servicios.
            </li>
          </ul>
        </div>
      </section>

      {/* ----------- CUARTA SECCION -------------  */}
      <section>
        <div>
          <h1>ÚNETE A NUESTRA COMUNIDAD</h1>
          <p>
            Forma parte de nuestra comunidad comprometida en mejorar la
            accesibilidad y calidad de los servicios.
          </p>
          <a id="mi-boton" class="boton-estilizado" href="login">
            Click aquí
          </a>
        </div>
      </section>

      <FootBar />
    </>
  );
}

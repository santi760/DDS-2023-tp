window.addEventListener('DOMContentLoaded', () => {
    const dropZone = document.getElementById('dropZone');
    const fileInput = document.getElementById('fileInput');
    const uploadBtn = document.getElementById('uploadBtn');
    const sendBtn = document.getElementById('sendBtn');
  
    let uploadedFile = null;
  
    dropZone.addEventListener('dragover', (e) => {
      e.preventDefault();
      dropZone.classList.add('dragover');
    });
  
    dropZone.addEventListener('dragleave', () => {
      dropZone.classList.remove('dragover');
    });
  
    dropZone.addEventListener('drop', (e) => {
      e.preventDefault();
      dropZone.classList.remove('dragover');
      const file = e.dataTransfer.files[0];
      handleFile(file);
    });
  
    fileInput.addEventListener('change', () => {
      const file = fileInput.files[0];
      handleFile(file);
    });
  
    uploadBtn.addEventListener('click', () => {
      fileInput.click();
    });
  
    sendBtn.addEventListener('click', () => {
      if (uploadedFile) {
        // Aquí puedes enviar el archivo cargado al backend
        console.log('Enviando archivo:', uploadedFile.name);
      } else {
        // Agregar la clase 'disabled' al botón de envío
        sendBtn.classList.add('disabled');
        
        // Agregar animación en rojo al botón de envío
        setTimeout(() => {
          sendBtn.classList.remove('disabled');
        }, 1000);
      }
    });
  
    function handleFile(file) {
      // Aquí puedes realizar las acciones necesarias con el archivo CSV
      console.log('Archivo cargado:', file.name);
  
      // Mostrar el nombre del archivo en la zona de arrastrar y soltar
      dropZone.innerHTML = `<p>Archivo cargado: ${file.name}</p>`;
      uploadedFile = file;
    }
  });
  
let trabajadores = [];

function agregarTrabajador() {
  // Obtener valores del formulario
  const nombre = document.getElementById("nombre").value;
  const apellido = document.getElementById("apellido").value;
  const correo = document.getElementById("correo").value;
  const departamento = document.getElementById("departamento").value;

  // Validar que no haya campos vacíos, y si los hay mostrar mensaje de error
  if (!nombre || !apellido || !correo || !departamento) {
    Swal.fire({
      title: "Error",
      text: "Por favor, rellene todos los campos",
      icon: "error",
    });
    return;
  }

  //Mensaje que aparece si se introducen correctamente todos los campos
  Swal.fire({
    title: "Correcto",
    text: "Usuario agregado correctamente",
    icon: "success",
    confirmButtonText: "Cool",
  });

  // Se crea el objeto trabajador
  const nuevoTrabajador = {
    nombre: nombre,
    apellido: apellido,
    correo: correo,
    departamento: departamento,
  };

  // Se agrega trabajador a la lista de trabajadores
  trabajadores.push(nuevoTrabajador);

  // Actualizar lista de trabajadores
  actualizarListaTrabajadores();

  // Actualizar resumen de trabajadores por departamento
  actualizarResumenDepartamentos();
}

function actualizarListaTrabajadores() {
  const listaTrabajadoresElement = document.getElementById("listaTrabajadores");
  listaTrabajadoresElement.innerHTML = "";

  trabajadores.forEach((trabajador) => {
    const li = document.createElement("li");
    li.className = "list-group-item";
    li.textContent = `${trabajador.nombre} ${trabajador.apellido}`;
    listaTrabajadoresElement.appendChild(li);
  });
}

function actualizarResumenDepartamentos() {
  const resumenElement = document.getElementById("resumen");
  resumenElement.innerHTML = "";

  // Contar trabajadores por departamento
  const resumenDepartamentos = {};
  trabajadores.forEach((trabajador) => {
    if (resumenDepartamentos[trabajador.departamento]) {
      resumenDepartamentos[trabajador.departamento]++;
    } else {
      resumenDepartamentos[trabajador.departamento] = 1;
    }
  });

  // Mostrar resumen en el elemento
  for (const departamento in resumenDepartamentos) {
    const cantidad = resumenDepartamentos[departamento];
    const p = document.createElement("p");
    p.textContent = `${departamento}: ${cantidad} trabajador${
      cantidad !== 1 ? "es" : ""
    }`;
    resumenElement.appendChild(p);
  }
}

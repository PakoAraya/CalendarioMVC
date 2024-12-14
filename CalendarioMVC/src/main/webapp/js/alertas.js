

// Función para mostrar un pop-up de confirmación antes de eliminar un usuario
function confirmarEliminacion() {
    var confirmacion = confirm("¿Estás seguro de que deseas eliminar a este usuario?");
    if (confirmacion) {
        alert("Usuario eliminado con éxito.");
    }
    return confirmacion; // Si el usuario confirma, se envía el formulario; si no, se cancela.
}

// Función para mostrar un pop-up de éxito después de agregar un nuevo usuario
function mostrarUsuarioAgregado() {
    alert("Nuevo usuario agregado correctamente.");
}

// Función para mostrar un pop-up de confirmación antes de editar un usuario
function confirmarEdicion() {
    return confirm("¿Estás seguro de que deseas editar los detalles de este usuario?");
}

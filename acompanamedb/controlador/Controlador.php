<?php
require_once("modelo/Modelo.php");

class Controlador {

    public function handle($accion) {
        switch ($accion) {

            case "usuarios":
                $this->usuarios();
                break;

            case "login":
                $this->login();
                break;

            case "registrar":
                $this->registrar();
                break;

            case "ciudades":
                $this->ciudades();
                break;

            default:
                echo json_encode(["success" => false, "message" => "Acción no válida"]);
        }
    }

    private function usuarios() {
        $modelo = new Modelo();
        $data = $modelo->getUsuarios();
        echo json_encode(["success" => true, "data" => $data]);
    }

    private function ciudades() {
        $modelo = new Modelo();
        $data = $modelo->getCiudades();
        echo json_encode(["success" => true, "data" => $data]);
    }

    private function login() {
    if ($_SERVER["REQUEST_METHOD"] !== "POST") {
        echo json_encode([
            "success" => false,
            "message" => "El endpoint login requiere método POST"
        ]);
        return;
    }

    $email = trim($_POST["email"] ?? "");
    $pw = trim($_POST["pw"] ?? "");

    if ($email === "" || $pw === "") {
        echo json_encode([
            "success" => false,
            "message" => "Faltan parámetros: email y/o pw"
        ]);
        return;
    }

    $modelo = new Modelo();
    $user = $modelo->login($email, $pw);

    if ($user) {
        echo json_encode([
            "success" => true,
            "user" => $user
        ]);
    } else {
        echo json_encode([
            "success" => false,
            "message" => "Credenciales incorrectas"
        ]);
    }
}

private function registrar() {
    if ($_SERVER["REQUEST_METHOD"] !== "POST") {
        echo json_encode([
            "success" => false,
            "message" => "El endpoint registrar requiere método POST"
        ]);
        return;
    }

    $data = [
        "nombre" => trim($_POST["nombre"] ?? ""),
        "apellidos" => trim($_POST["apellidos"] ?? ""),
        "ciudad" => trim($_POST["ciudad"] ?? ""),
        "hospital" => trim($_POST["hospital"] ?? ""),
        "enfermedad" => trim($_POST["enfermedad"] ?? ""),
        "descripcion" => trim($_POST["descripcion"] ?? ""),
        "email" => trim($_POST["email"] ?? ""),
        "telefono" => trim($_POST["telefono"] ?? ""),
        "pw" => trim($_POST["pw"] ?? "")
    ];

    if (
        $data["nombre"] === "" ||
        $data["apellidos"] === "" ||
        $data["ciudad"] === "" ||
        $data["hospital"] === "" ||
        $data["enfermedad"] === "" ||
        $data["email"] === "" ||
        $data["pw"] === ""
    ) {
        echo json_encode([
            "success" => false,
            "message" => "Faltan campos obligatorios"
        ]);
        return;
    }

    $modelo = new Modelo();
    $result = $modelo->registrar($data);

    echo json_encode($result);
}
}
?>
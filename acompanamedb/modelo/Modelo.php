<?php
require_once("config/db.php");

class Modelo {

    public function getUsuarios() {
        $con = getConnection();
        $result = $con->query("SELECT * FROM usuario");

        $usuarios = [];
        while ($row = $result->fetch_assoc()) {
            unset($row["pw"]); // seguridad
            $usuarios[] = $row;
        }

        return $usuarios;
    }

    public function getCiudades() {
        $con = getConnection();
        $result = $con->query("SELECT * FROM ciudad");

        $ciudades = [];
        while ($row = $result->fetch_assoc()) {
            $ciudades[] = $row;
        }

        return $ciudades;
    }

    public function login($email, $pw) {
        $con = getConnection();

        $stmt = $con->prepare("SELECT * FROM usuario WHERE email=? AND pw=?");
        $stmt->bind_param("ss", $email, $pw);
        $stmt->execute();

        $result = $stmt->get_result();

        if ($row = $result->fetch_assoc()) {
            unset($row["pw"]);
            return $row;
        }

        return null;
    }

    public function registrar($data) {
    $con = getConnection();

    try {
        $stmt = $con->prepare("INSERT INTO usuario 
        (nombre, apellidos, ciudad, hospital, enfermedad, descripcion, email, telefono, pw) 
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

        if (!$stmt) {
            return [
                "success" => false,
                "message" => "Error al preparar la consulta"
            ];
        }

        $stmt->bind_param(
            "sssssssss",
            $data["nombre"],
            $data["apellidos"],
            $data["ciudad"],
            $data["hospital"],
            $data["enfermedad"],
            $data["descripcion"],
            $data["email"],
            $data["telefono"],
            $data["pw"]
        );

        $ok = $stmt->execute();

        if ($ok) {
            return [
                "success" => true,
                "message" => "Usuario registrado correctamente"
            ];
        }

        return [
            "success" => false,
            "message" => "No se pudo registrar el usuario"
        ];

    } catch (mysqli_sql_exception $e) {
        if ($e->getCode() == 1062) {
            return [
                "success" => false,
                "message" => "El email ya existe"
            ];
        }

        return [
            "success" => false,
            "message" => "Error SQL: " . $e->getMessage()
        ];
    }
}
}
?>
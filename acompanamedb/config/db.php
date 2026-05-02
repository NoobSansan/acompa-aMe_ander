<?php
function getConnection() {
    $con = new mysqli("localhost", "root", "", "acompanamedb", 3307);

    if ($con->connect_error) {
        echo json_encode(["success" => false, "message" => "Error conexión"]);
        exit();
    }

    $con->set_charset("utf8mb4");
    return $con;
}
?>
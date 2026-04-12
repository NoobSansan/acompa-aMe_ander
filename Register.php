<?php
    $con = mysqli_connect("localhost", "root", "", "acompañame_bbdd");
    
    $nombre = $_POST["nombre"];
    $apellidos = $_POST["apellidos"];
    $ciudad = $_POST["ciudad"];
    $hospital = $_POST["hospital"];
    $enfermedad = $_POST["enfermedad"];
    $descripcion = $_POST["descripcion"];
    $email = $_POST["email"];
    $telefono = $_POST["telefono"];
    $pw = $_POST["pw"];
    $statement = mysqli_prepare($con, "INSERT INTO usuario (nombre, apellidos, ciudad, hospital, enfermedad, descripcion, email, telefono, pw) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sssssssss", $nombre, $apellidos, $ciudad, $hospital, $enfermedad, $descripcion, $email, $telefono, $pw);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>
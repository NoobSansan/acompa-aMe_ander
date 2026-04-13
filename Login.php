<?php
    $con = mysqli_connect("localhost", "root", "", "acompañame_bbdd");
    
    $email = $_POST["email"];
    $pw = $_POST["pw"];
    
    $statement = mysqli_prepare($con, "SELECT nombre, apellidos, ciudad, hospital, enfermedad, descripcion, email, telefono, pw FROM usuario WHERE email = ? AND pw = ?");
    mysqli_stmt_bind_param($statement, "ss", $email, $pw);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $nombre, $apellidos, $ciudad, $hospital, $enfermedad, $descripcion, $email_db, $telefono, $pw_db);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["nombre"] = $nombre;
        $response["apellidos"] = $apellidos;
        $response["ciudad"] = $ciudad;
        $response["hospital"] = $hospital;
        $response["enfermedad"] = $enfermedad;
        $response["descripcion"] = $descripcion;
        $response["email"] = $email_db;
        $response["telefono"] = $telefono;
        $response["pw"] = $pw_db;
    }
    
    echo json_encode($response);
?>
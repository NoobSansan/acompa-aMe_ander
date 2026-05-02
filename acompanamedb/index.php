<?php
header('Content-Type: application/json; charset=utf-8');
ini_set('display_errors', 0);
error_reporting(0);

require_once("controlador/Controlador.php");

$accion = $_GET["accion"] ?? "";

$controlador = new Controlador();
$controlador->handle($accion);
?>
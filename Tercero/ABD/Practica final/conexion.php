<?php
    session_start();
    define('DB_SERVER', 'localhost');
    define('DB_USERNAME', 'root');
    define('DB_PASSWORD', '');
    define('DB_NAME', 'abd-proyecto-bnj');
    ini_set('default_charset', 'UTF-8');
    setLocale(LC_ALL, 'es_ES.UTF.8');
    setlocale(LC_TIME, 'es_ES');
    date_default_timezone_set('Europe/Madrid');
    $conn = new mysqli(DB_SERVER, DB_USERNAME, DB_PASSWORD, DB_NAME);
    if($conn === false)
    {
        die("ERROR: Could not connect. " . $conn->connect_error);
    }
?>
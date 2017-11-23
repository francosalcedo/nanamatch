<?php

/*ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

*/
require_once 'config.php';
header('Content-type: application/json');

/*
// toda respuesta debe ser JSON

echo json_encode(['a' => print_r($_REQUEST, true)]);
die;

*/

$mode = explode('/', $_GET['request']);

if($mode[0] == '') echo json_encode(['error' => 'no method']);

switch ($mode[0]) {
	case 'login':
			echo $api->login( (object)[
				'email' => $_REQUEST['email'],
				'password' => $_REQUEST['password']
			]);
		break;

	case 'register':
			echo $api->register( (object) $_REQUEST);
		break;

	case 'list':
			echo $api->listAll( (object) $_REQUEST);
		break;

	case 'create_service':
			echo $api->createService( (object) $_REQUEST );
		break;

	case 'list_service':
			echo $api->listService( (object) $_REQUEST );
		break;

	case 'edit_name':
			echo $api->editName((object) $_REQUEST);
			break;

	case 'list_service_client':
			echo $api->listServiceClient((object) $_REQUEST);
			break;

}

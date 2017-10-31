<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

require_once 'config.php';
header('Content-type:application/json');

$m =  $_REQUEST;
switch ($m['m']) {
	case 'login':
			echo $client->login( (object)[
				'email' => $m['email'],
				'password' => $m['password']
			]);
		break;

	case 'register':
			echo $client->register( (object) $m);
		break;

	case '':
		break;

	case '':
		break;

	case '':
		break;

}

<?php
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
		break;

	case '':
		break;

	case '':
		break;

	case '':
		break;

}

<?php

require 'vendor/autoload.php';

$config = [
	'host'      => 'localhost',
	'driver'    => 'mysql',
	'database'  => 'nanamatch',
	'username'  => 'user',
	'password'  => '',
	'charset'   => 'utf8',
	'collation' => 'utf8_general_ci',
	'prefix'    => ''
];

require 'model.client.php';

$client = new Client($config);

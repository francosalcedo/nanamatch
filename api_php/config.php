<?php

require 'vendor/autoload.php';

$config = [
	'host'      => 'localhost',
	'driver'    => 'mysql',
	'database'  => 'c1dbnana',
	'username'  => 'c1nana',
	'password'  => 'vd!wRM6B',
	'charset'   => 'utf8',
	'collation' => 'utf8_general_ci',
	'prefix'    => ''
];

require 'api.php';

$api = new Api($config);

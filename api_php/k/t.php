<?php

$c = print_r($_SERVER, true) . " \n-------------\n \n-------------\n ". print_r($_REQUEST, true);
file_put_contents('a.txt', $c);

?>
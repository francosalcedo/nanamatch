<?php

/**
 * Entidad Client
 */
class Client
{
  private $db;
  private $client;

  function __construct($config)
  {
    $this->db = new \Buki\Pdox($config);
    $this->client = $this->db->table('Client');
  }

  public function login($d)
  {
    $r = new stdClass();
    $sql = $this->client->select('*')
      ->where(
      [
        'password'  => $d->password
      ])
      ->get();

    print_r($this->db->getQuery());
    die;
    if($sql){
      $r->status = 1;
      $r->msj 	 = 'Login correcto';
      $r->data 	 = $sql;
    }else{
      $r->status = 2;
      $r->msj 	 = 'Datos incorrectos';
    }
    return $this->json($r);
  }

  private function register($d)
  {
    //$sql =
  }

  private function json($a)
  {
    return json_encode($a);
  }
}

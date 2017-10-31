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

  public function register($d)
  {
    try {
      $save = [
        'email'         => $d->email,
        'password'      => $d->password,
        'name'          => $d->name,
        'last_name'     => $d->lastname,
        'address'       => $d->address,
        'phone_number'  => $d->phone_number,
        'birthday'      => $d->birthday,
        'id_distrit'    => $d->id_distrit
      ];

      $this->validate($save);
      $sql = $this->client->insert($save);

      if($sql)
      {
        return $this->json([
          'status' => 1,
          'msj'    => 'Registrado correctamente'
        ]);

      }else{
        return $this->json([
          'status'  => 2,
          'msj'     => 'Error registro db'
        ]);

      }
    } catch (Exception $e) {
      return $this->json([
        'status' => 2,
        'msj'    => 'Error al registrar',
        'msj_details' => $e->getMessage()
      ]);
    }

  }

  private function validate($d = [])
  {
    foreach ($d as $k => $v) {
      if(empty($v))
      {
        throw new Exception("Falta algun valor.", 1);
      }
    }
  }

  private function json($a)
  {
    return json_encode($a);
  }
}

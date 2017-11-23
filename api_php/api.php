<?php

/**
 * Entidad Client
 */
class Api
{
  private $db;
  private $client;
  const TABLE_CLIENT = "Client";
  const TABLE_NANA   = "Nana";
  const TABLE_SERVICE = "Service";

  function __construct($config)
  {
    $this->db = new \Buki\Pdox($config);
  }

  public function editName($d)
  {
    $data = [
      'name'  => $d->name
    ];

    $sql = $this->db->table( self::TABLE_CLIENT )
                    ->update($data)
                    ->where('id', $d->id);
    if($sql){
      $r->status = 1;
      $r->msj 	 = 'Servicio creado';
    }else{
      $r->status = 2;
      $r->msj 	 = 'Error al procesar';
    }
    return $this->json($r);

  }

    public function listService($d)
    {
      $sql = $this->db->table( self::TABLE_SERVICE)
                      ->select('*')
                      ->where('id_nana', $d->id)
                      ->getAll();
      if($sql){
        $r->status = 1;
        $r->msj 	 = 'ok';
        $r->data   = (object)$sql;
      }else{
        $r->status = 2;
        $r->msj 	 = 'Error al procesar lista';
      }
      return $this->json((object)$r);
    }

    public function listServiceClient($d)
    {
      $sql = $this->db->table( self::TABLE_SERVICE)
                      ->select('*')
                      ->where('id_cliente', $d->id)
                      ->getAll();
      if($sql){
        $r->status = 1;
        $r->msj 	 = 'ok';
        $r->data   = (object)$sql;
      }else{
        $r->status = 2;
        $r->msj 	 = 'Error al procesar lista';
      }
      return $this->json((object)$r);
    }


  public function createService($d)
  {
    $r = new stdClass();
    $data = [
      'hours' => '3',
      'final_price' => rand(10,35),
      'id_cliente'   => $d->id_client,
      'id_nana'     => $d->id_nana,
      'comment'     => $d->comment
    ];

    $sql = $this->db->table( self::TABLE_SERVICE )->insert($data);

    if($sql){
      $r->status = 1;
      $r->msj 	 = 'Servicio creado';
    }else{
      $r->status = 2;
      $r->msj 	 = 'Error al procesar';
    }
    return $this->json($r);
  }

  public function listAll($q)
  {
    $sql = $this->db->table(( $q->q=='nana'?self::TABLE_NANA:self::TABLE_CLIENT ))
                  ->select("*")
                  ->getAll();
    return $this->json((object)$sql);
  }

  public function login($d)
  {
    $r = new stdClass();
    $sql = $this->db->table(self::TABLE_CLIENT)->select('*')
      ->where([
        'email'     => $d->email,
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
        'last_name'     => $d->last_name,
        'address'       => $d->address,
        'phone_number'  => $d->phone_number,
        'id_distrit'    => $d->id_distrit,
        'gender'        => $d->gender
      ];

      $this->validate($save);
      $this->registerValidateEmail($d->email);

      $sql = $this->db->table(self::TABLE_CLIENT)->insert($save);

      if($sql)
      {
        return $this->json([
          'status' => 1,
          'msj'    => 'Registrado correctamente',
          'data'   => $save
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

  private function registerValidateEmail($e)
  {
    if(!filter_var($e, FILTER_VALIDATE_EMAIL))
    {
      throw new Exception("Formato de email invalido.", 1);
    }

    $sql = $this->db->table(self::TABLE_CLIENT)
                    ->select('*')
                    ->where('email', $e)
                    ->get();

    if($sql)
    {
      throw new Exception("El email ya esta registrado.", 1);
    }

  }

  private function json($a)
  {
    return json_encode($a);
  }

}

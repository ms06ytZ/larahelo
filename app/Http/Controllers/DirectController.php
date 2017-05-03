<?php
namespace App\Http\Controllers;

class DirectController extends Controller
{
  public function index(){

   return view('direct.index');
     }
  public function create(){
   return view('direct.create');
     }
  public function detail(){
$id = $request->input('id');
$direct = direct::find($id);
   return view('direct.detail')->with('direct',$direct);
     }
  public function update(){
   return view('direct.update');
     }
  public function delete(){
   return view('direct.delete');
     }
}


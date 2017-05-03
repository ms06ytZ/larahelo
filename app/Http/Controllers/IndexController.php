<?php
namespace App\Http\Controllers;

class IndexController extends Controller
{
  public function index(){

   return view('index');
     }
  public function create(){
   return view('index.create');
     }
  public function detail(){
$id = $request->input('id');
$index = index::find($id);
   return view('index.detail')->with('index',$index);
     }
  public function update(){
   return view('index.update');
     }
  public function delete(){
   return view('index.delete');
     }
}


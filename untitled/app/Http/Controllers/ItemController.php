<?php
namespace App\Http\Controllers;

class ItemController extends Controller
{
  public function index(){

   return view('item.index');
     }
  public function create(){
   return view('item.create');
     }
  public function detail(){
$id = $request->input('id');
$item = item::find($id);
   return view('item.detail')->with('item',$item);
     }
  public function update(){
   return view('item.update');
     }
  public function delete(){
   return view('item.delete');
     }
}


<?php
namespace App\Http\Controllers;

class AboutController extends Controller
{
  public function index(){

   return view('about.index');
     }
  public function create(){
   return view('about.create');
     }
  public function detail(){
$id = $request->input('id');
$about = about::find($id);
   return view('about.detail')->with('about',$about);
     }
  public function update(){
   return view('about.update');
     }
  public function delete(){
   return view('about.delete');
     }
}


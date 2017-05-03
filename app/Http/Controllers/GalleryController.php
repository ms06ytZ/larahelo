<?php
namespace App\Http\Controllers;

class GalleryController extends Controller
{
  public function index(){

   return view('gallery.index');
     }
  public function create(){
   return view('gallery.create');
     }
  public function detail(){
$id = $request->input('id');
$gallery = gallery::find($id);
   return view('gallery.detail')->with('gallery',$gallery);
     }
  public function update(){
   return view('gallery.update');
     }
  public function delete(){
   return view('gallery.delete');
     }
}


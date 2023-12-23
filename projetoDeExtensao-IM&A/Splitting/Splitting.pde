PImage img, out, aux;

void setup () { 
  
  size (1300, 750);
  
  String fname;
  fname = "Figura2.3a";
  
  img = loadImage (fname + ".png");
  frameRate (1);
  img = Splitting (img);
  img.save (fname + "-splitting.png");
  exit ();

}

PImage Splitting (PImage in){
  
  PImage out = createImage (img.width, img.height, RGB);
  float r, g, b, passo;
  
  in.loadPixels ();
  passo = 50.0 / 0.9;
  
  for (int i = 0; i < in.height; i++){
    
    for (int j = 0; j < in.width; j++){
      
      color cor = in.get (j,i);
      r = floor (red (cor) / passo) * passo;
      g = floor (green (cor) / passo) * passo;
      b = floor (blue (cor) / passo) * passo;
      
      out.set (j, i, color (r, g, b));
    
    }
  
  }
  
  out.updatePixels ();
  return out;

}

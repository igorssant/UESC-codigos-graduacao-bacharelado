PImage img, out, aux;

void setup () { 
  
  size (1300, 750);
  
  String fname;
  fname = "Figura2.3a";
  
  img = loadImage (fname + ".png");
  frameRate (1);
  img = Limiar (img);
  img.save (fname + "-limiar.png");
  exit ();

}

PImage Limiar (PImage in){
  
  PImage out = createImage (img.width, img.height, RGB);
  float r, g, b, limiar, contanteSplitting;
  
  in.loadPixels ();
  limiar = 80.0;
  contanteSplitting = 30;
  
  for (int i = 0; i < in.height; i++){
    
    for (int j = 0; j < in.width; j++){
      
      color cor = in.get (j,i);
      r = red (cor);
      g = green (cor);
      b = blue (cor);
      if (r < limiar) r = r - contanteSplitting;
      else r = r + contanteSplitting;
      
      if (g < limiar) g = g - contanteSplitting;
      else g = g + contanteSplitting;
      
      if (b < limiar) b = b - contanteSplitting;
      else b = b + contanteSplitting;
      
      out.set (j, i, color (r, g, b));
    
    }
  
  }
  
  out.updatePixels ();
  return out;

}

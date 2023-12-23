PImage img, out, aux;

void setup () { 
  
  size (1300, 750);
  
  String fname;
  fname = "OIP";
  
  img = loadImage (fname + ".jpg");
  frameRate (1);
  img = TonsCinzaAcentuado (img);
  img.save (fname + "-darker.png");
  exit();

}

PImage TonsCinzaAcentuado (PImage in){
  
  PImage out = createImage (img.width, img.height, RGB);
  int constanteQuantizacao;
  float r, g, b;
  
  in.loadPixels();
  constanteQuantizacao = 50;
  
  for (int i = 0; i < in.height; i++){
    
    for (int j = 0; j < in.width; j++){
      
      color cor = in.get (j,i);
      r = red (cor) - constanteQuantizacao;
      g = green (cor) - constanteQuantizacao;
      b = blue (cor) - constanteQuantizacao;
      out.set (j,i,color (r, g, b));
    
    }
  
  }
  
  out.updatePixels();
  return out;

}

// VARIAVEIS USADAS
int pinoSensorProximidade = 2;
int pinoTemperatura = A0;
double distanciaDaPessoa = 120.0;

// CONSTANTES USADAS
float VARIACAO_TEMPERATURA = 1.0;
double DISTANCIA_MAXIMA_ACEITAVEL = 200.0;
double CONSTANTE_CENTIMETROS = 0.01723;
float TEMPERATURA_MINIMA = 15.0;
float TEMPERATURA_MAXIMA = 60.0;

// CABECALHO - FUNCOES
void alterarVariacaoTemperatura(double);
float temperaturaDesejada();
float aumentarTemperatura(float);
float diminuirTemperatura(float);
int verificarTemperatura(float);
double conversaoParaCentimentros(long);
long lerDistanciaUltrasonica();
float gerenciarChoveiro(float);
float voltagemParaTemperatura(float);

// -------------- IMPLEMENTACAO --------------
void setup() {
  //start serial connection
  Serial.begin(9600);
  pinMode(pinoTemperatura, INPUT);
}

void loop() {
  	float temperaturaAtual = 0.0;
  
  	digitalWrite(LED_BUILTIN, HIGH);
   	temperaturaAtual = voltagemParaTemperatura(
     	analogRead(pinoTemperatura)
     );
  
  	distanciaDaPessoa = conversaoParaCentimentros(
      lerDistanciaUltrasonica()
    );
    
    if(distanciaDaPessoa < DISTANCIA_MAXIMA_ACEITAVEL) {
      temperaturaAtual = gerenciarChoveiro(
        temperaturaAtual
      );
      
      Serial.print("TEMPERATURA: ");
      Serial.print(temperaturaAtual);
      Serial.println(" C");

      Serial.print("DISTANCIA DO CHOVEIRO: ");
      Serial.print(distanciaDaPessoa);
      Serial.println(" cm");
    } else {
    	Serial.println("Pessoa fora do alcance da agua.\nChoveiro desligado.");
    }
  
  	delay(800);
}

void alterarVariacaoTemperatura(float novaVariacao) {
	VARIACAO_TEMPERATURA = novaVariacao;
	return;
}

float temperaturaDesejada() {
	return 40.0;
}

float aumentarTemperatura(float temperaturaAtual) {
	Serial.println("Agua muito quente.\nDiminuindo a temperatura da agua.");
  	return temperaturaAtual + VARIACAO_TEMPERATURA;
}

float diminuirTemperatura(float temperaturaAtual) {
  	Serial.println("Agua muito fria.\nAumentando a temperatura da agua.");
	return temperaturaAtual - VARIACAO_TEMPERATURA;
}

int verificarTemperatura(float temperaturaAtual) {
  if(temperaturaAtual > temperaturaDesejada()) {
    // ESTA MAIS QUENTE DO QUE A TEMPERATURA DESEJADA
    return 1;
  } else if(temperaturaAtual < temperaturaDesejada()) {
  	// ESTA MAIS FRIO DO QUE A TEMPERATURA DESEJADA
    return -1;
  } else {
    // CHEGOU NA TEMPERATURA DESEJADA
  	return 0;
  }
}

double conversaoParaCentimentros(long leituraDoPulso) {
	return CONSTANTE_CENTIMETROS * leituraDoPulso;
}

long lerDistanciaUltrasonica() {
 	// LIMPA QUALQUER COISA NO BUFFER DO PINO
	pinMode(pinoSensorProximidade, OUTPUT);
 	
  	digitalWrite(pinoSensorProximidade, LOW);
  	delayMicroseconds(2);
  	digitalWrite(pinoSensorProximidade, HIGH);
  	delayMicroseconds(10);
	digitalWrite(pinoSensorProximidade, LOW);
 	pinMode(pinoSensorProximidade, INPUT);
  	
  	// LE O PINO E RETORNA O TEMPO EM MILI SEGUNDOS
  	return pulseIn(pinoSensorProximidade, HIGH);
}

float gerenciarChoveiro(float temperaturaAtual) {
	int situacaoTemperatura = 0;
  
  	if( // OCORREU UM ERRO !!!
      temperaturaAtual < TEMPERATURA_MINIMA ||
      temperaturaAtual > TEMPERATURA_MAXIMA
    ) {
    	return -1.0;
    }
    
  	situacaoTemperatura = verificarTemperatura(temperaturaAtual);
  
  	switch(situacaoTemperatura) {
    	case 0:
      		Serial.println("Agua esta na temperatura desejada");
      		return temperaturaAtual;
      
      	case 1:
      		return aumentarTemperatura(temperaturaAtual);
      
      	default:
      		return diminuirTemperatura(temperaturaAtual);
    }
}

float voltagemParaTemperatura(float saidaDoSensor) {
  	float voltagem = 0.0,
  		temperatura = 0.0;
	
  	voltagem = saidaDoSensor * (5.0 / 1023.0);
  	temperatura = (voltagem - 0.5) * 100;
  	return temperatura;
}

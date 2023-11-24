const telaCanvas = document.querySelector("canvas");
const contexto = telaCanvas.getContext("2d");
const botaoIniciar = document.querySelector(".botao-inicia");

// os codigos das setas para o javascript
const CIMA = 38,
    BAIXO = 40,
    ESQUERDA = 37,
    DIREITA = 39;

const larguraCanvas = telaCanvas.width,
    alturaCanvas = 140,
    velocidadeInicial = 0.5,
    constanteDeCorrida = 0.03,
    totalDeObjetos = 20;

//para ser usado no detector de tecla
var moverEsquerda = false,
    pular = false,
    moverDireita = false,
    abaixar = false,
    objetos = [];

var personagem = {
    eixoX: 5,
    eixoY: Number(alturaCanvas),
    altura : 15,
    largura : 15,
    velocidadeX : Number(velocidadeInicial),
    velocidadeY : 0,
    gravidade : 0.2,
    velocidadeGravidade : 0,
    pulo : 30,
    pontosVitoria : 0
};

for(let i = 0; i < totalDeObjetos; i++){
    adicionarObjeto(objetos);
}

function keydownHandler(e) {
	let key = e.keyCode;

	switch (key) {
        case ESQUERDA:
            moverEsquerda = true;
            break;
	
        case CIMA:
            pular = true;
            break;
	
        case DIREITA:
            moverDireita = true;
            break;
	
        case BAIXO:
            abaixar = true;
            break;
    }
}

function keyupHandler(e) {
	let key = e.keyCode; 			

	switch(key) {
        case ESQUERDA:
    		moverEsquerda = false;
            resetarVelocidade();
	    	break;

        case CIMA:
            pular = false;
            break;

        case DIREITA:
            moverDireita = false;
            resetarVelocidade();
            break;

        case BAIXO:
            abaixar = false;
            break;
    }
}

function resetarVelocidade() {
    personagem.velocidadeX = velocidadeInicial;
}

function baterNaParede() {
    const paredeEsquerda = 0,
        paredeDireita = larguraCanvas - personagem.largura;

    if(personagem.eixoX < paredeEsquerda) {
        personagem.eixoX = paredeEsquerda;
        resetarVelocidade();
    }

    else if(personagem.eixoX > paredeDireita) {
        personagem.eixoX = paredeDireita;
        resetarVelocidade();
    }
}

function correr() {
    personagem.velocidadeX += constanteDeCorrida;
}

function mover() {
	if(moverEsquerda) {
		personagem.eixoX = personagem.eixoX - personagem.velocidadeX;
        correr();
	}

	if(pular) {
		personagem.eixoY = personagem.eixoY - personagem.pulo;
        pular = false;
	}

	if(moverDireita) {
		personagem.eixoX = personagem.eixoX + personagem.velocidadeX;
        correr();
	}

    baterNaParede();
}

function bateuNoChao() {
    const chao = alturaCanvas - personagem.altura;
    
    if(personagem.eixoY > chao) {
        personagem.eixoY = chao;
        personagem.velocidadeGravidade = 0;
    }
}

function cair() {
    const chao = alturaCanvas - personagem.altura;
    
    if(personagem.eixoY !== chao) {
        personagem.velocidadeGravidade += personagem.gravidade;
        personagem.eixoY += personagem.velocidadeY + personagem.velocidadeGravidade;
        bateuNoChao();
    }
}

function limpar() {
    contexto.clearRect(0, 0, telaCanvas.width, telaCanvas.height);
}

function desenharPersonagem() {
    contexto.fillRect(personagem.eixoX, personagem.eixoY, personagem.largura, personagem.altura);
}

function desenhaMacas() {
    for(let i = 0; i < objetos.length; i++) {
        let objetoAtual = objetos[i];
        contexto.fillStyle = objetoAtual.cor;
        contexto.fillRect(objetoAtual.eixoX, objetoAtual.eixoY, objetoAtual.largura, objetoAtual.altura);
    }
}

function fimDeJogo(){
    objetos = [];
    contexto.font = "12px Arial";
    contexto.fillStyle = "red";
    contexto.fillText("Fim de jogo!", 10, 35);
    window.cancelAnimationFrame(iniciar);
    window.removeEventListener("keydown", keydownHandler);
}

function vitoria() {
    if(personagem.pontosVitoria < (-50)) {
        console.log("DERROTA");
        fimDeJogo();
    }

    else if(personagem.pontosVitoria > 49) {
        console.log("VITORIA");
        fimDeJogo();
    }
}

function desenhaTudo() {
    limpar();
    desenharPersonagem();
    desenhaMacas();
    contexto.font = "10px Arial";
    contexto.fillStyle = "black";
    contexto.fillText("Pontos: " + personagem.pontosVitoria, 10, 15);
}

function iniciar() {
	window.requestAnimationFrame(iniciar);

    for(let i = 0; i < objetos.length; i++) {
        var objeto = objetos[i];

        if(colisao(objeto, personagem)) {
            personagem.pontosVitoria += objeto.ponto;
            resetarObjeto(objeto);
        }

        objeto.eixoY += objeto.velocidade;

        if(objeto.eixoY >= alturaCanvas) {
            resetarObjeto(objeto);
        }
    }

    desenhaTudo();
    mover();
    cair();
    vitoria();
}

function numeroAleatorioCanvas(min, max) {
    return Math.random() * (max - min) + min;
}

function objetoAleatorio() {
    return Math.random() * (2 - 0) + 0;        
}

function criarObjeto() {
    var posicaoDeQueda = Math.round(numeroAleatorioCanvas(larguraCanvas, 0));
    var nroObjeto = Math.round(objetoAleatorio());
    var objeto;
    
    switch(nroObjeto) {
        case 0:
            objeto = {
                nome : "macaBoa",
                cor : "red",
                largura : 10,
                altura : 10,
                ponto : 3,
                eixoX : Number(posicaoDeQueda),
                eixoY : 0,
                velocidade : 0
            };
            break;
        
        case 1:
            objeto = {
                nome : "macaPodre",
                cor : "green",
                largura : 10,
                altura : 10,
                ponto : -3,
                eixoX : Number(posicaoDeQueda),
                eixoY : 0,
                velocidade : 0
            };
            break;
        
        case 2:
            objeto = {
                nome : "bomba",
                cor : "gray",
                largura : 10,
                altura : 10,
                ponto : -100,
                eixoX : Number(posicaoDeQueda),
                eixoY : 0,
                velocidade : 0
            };
            break;
       
        default:
            console.log("Algo deu errado!");
    }

    return objeto;
}

function adicionarObjeto() {
    var objeto = criarObjeto();
    resetarObjeto(objeto);
    objetos.push(objeto);
}

function resetarObjeto(objeto) {
    objeto.eixoX = numeroAleatorioCanvas(5, larguraCanvas);
    objeto.eixoY = 5 + Math.random() * 25;
    objeto.velocidade = 0.2 + Math.random() * 0.5;
}

function colisao(a, b) {
    return !(b.eixoX > a.eixoX + a.largura || b.eixoX + b.largura < a.eixoX || b.eixoY > a.eixoY + a.altura || b.eixoY + b.altura < a.eixoY);
}

window.addEventListener("keydown", keydownHandler);
window.addEventListener("keyup", keyupHandler);
botaoIniciar.addEventListener("click", iniciar);

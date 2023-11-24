class Objeto {
    constructor(nome, cor, largura, altura, ponto, eixoX, eixoY) {
        this.nome = nome;
        this.cor = cor;
        this.largura = largura;
        this.altura = altura;
        this.ponto = ponto;
        this.eixoX = eixoX;
        this.eixoY = eixoY;
        this.velocidade = 0;
    }

    get nome() {
        return this.nome;
    }

    get cor() {
        return this.cor;
    }

    get largura() {
        return this.largura;
    }
    
    get altura() {
        return this.altura;
    }
    
    get ponto() {
        return this.ponto;
    }
    
    get eixoX() {
        return this.eixoX;
    }
    
    get eixoY() {
        return this.eixoY;
    }
    
    get velocidade() {
        return this.velocidade;
    }

    set nome(nome) {
        this.nome = nome;
    }

    set cor(cor) {
        this.cor = cor;
    }

    set largura(largura) {
        this.largura = largura;
    }
    
    set altura(altura) {
        this.altura = this.altura;
    }
    
    set ponto(ponto) {
        this.ponto = ponto;
    }
    
    set eixoX(eixoX) {
        this.eixoX = eixoX;
    }
    
    set eixoY(eixoY) {
        this.eixoY = eixoY;
    }
    
    set velocidade(velocidade) {
        this.velocidade = velocidade;
    }
}

var objetos = [];
const totalDeObjetos = 20;

for(let i = 0; i < totalDeObjetos; i++){
    adicionarObjeto(objetos);
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
            objeto = new Objeto(
                "macaBoa",
                "red",
                5,
                5,
                3,
                posicaoDeQueda,
                0
            );
            break;
        
        case 1:
            objeto = new Objeto(
                "macaPodre",
                "green",
                5,
                5,
                -2,
                posicaoDeQueda,
                0
            );
            break;
        
        case 2:
            objeto = new Objeto(
                "bomba",
                "green",
                5,
                5,
                -100,
                posicaoDeQueda,
                0
            );
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
    objeto.eixoX = numeroAleatorioCanvas();
    objeto.eixoY = 5 + Math.random() * 25;
    objeto.velocidade = 0.2 + Math.random() * 0.5;
}

function colisao(a, b) {
    return !(b.eixoX > a.eixoX + a.largura || b.eixoX + b.largura < a.eixoX || b.eixoY > a.eixoY + a.altura || b.eixoY + b.altura < a.eixoY);
}

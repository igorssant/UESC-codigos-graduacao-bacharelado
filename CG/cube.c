#include <GL/glut.h>

#define LEN 0.25 // Tamanho dos segmentos ou lados dos quadrados

double rotateX = 0.0;
double rotateY = 0.0;
int lastX, lastY;

/*
     E+--------+F
     /|       /|
   A+--------+B|
    | |_ _ _ |_|
    | /H     | /G
    |/       |/
   D+--------+C
*/

enum pts {A, B, C, D, E, F, G, H}; // Enumeração para os pontos do cubo

static GLfloat colors[6][3] = {
    {0xFE/255.0, 0xD1/255.0, 0xB0/255.0}, // Cor da Pele
    {0xAE/255.0, 0x00/255.0, 0x00/255.0}, // Vermelho
    {0x00/255.0, 0x45/255.0, 0x83/255.0}, // Azul
    {0xFF/255.0, 0xFF/255.0, 0xFF/255.0}, // Branco
    {0x69/255.0, 0x2C/255.0, 0x0F/255.0}, // Marrom
    {0x13/255.0, 0xA1/255.0, 0x3A/255.0}  // Verde
};

static GLfloat points[8][3] = {
    {-LEN,  LEN, -LEN}, // A
    { LEN,  LEN, -LEN}, // B
    { LEN, -LEN, -LEN}, // C
    {-LEN, -LEN, -LEN}, // D
    {-LEN,  LEN,  LEN}, // E
    { LEN,  LEN,  LEN}, // F
    { LEN, -LEN,  LEN}, // G
    {-LEN, -LEN,  LEN}  // H
};

static GLint index_v[6][4] = {
    {A, B, C, D}, {E, F, G, H}, // Front, Back
    {A, E, F, B}, {H, G, C, D}, // Top, Down
    {A, E, H, D}, {B, F, G, C}  // Left, Right
};

void cube(GLfloat *color)
{
    glBegin(GL_QUADS);
    for (short i = 0; i < 6; i++)
    {
        glColor3fv(color);
        glVertex3fv(&points[index_v[i][0]][0]);
        glVertex3fv(&points[index_v[i][1]][0]);
        glVertex3fv(&points[index_v[i][2]][0]);
        glColor4f(1, 1, 1, .5);
        glVertex3fv(&points[index_v[i][3]][0]);
    }
    glEnd();
}

void init(void) {
    glEnable(GL_DEPTH_TEST);
    glClearColor(.1, .5, .8, 1.0); // Define a cor de fundo da janela
}

float zoomFactor = 1.0;

void design(void) {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glLoadIdentity();

    glRotatef(rotateX, 1.0, 0.0, 0.0); // Rotação no eixo X
    glRotatef(rotateY, 0.0, 1.0, 0.0); // Rotação no eixo Y
    glTranslatef(0, .2, 0); // Translação no eixo Y para posicionar o cubo

    // Cabeça e cabelo
    glPushMatrix();
        glScalef(1.1, 1.0, 1.0);
        glRotatef(-2, 1.0, 0.0, 0.0);
        cube(colors[0]); // Desenha a cabeça

        glScalef(1.05, 0.45, 0.45);
        glTranslatef(0, LEN * 0.1, LEN * 1.3);
        cube(colors[4]); // Desenha parte do cabelo

        glScalef(1.01, 1.0, 1.0);
        glTranslatef(0, LEN, -LEN * 2);
        cube(colors[4]); // Desenha parte do cabelo
    glPopMatrix();

    // Orelha esquerda
    glPushMatrix();
        glScalef(.25, .4, .25);
        glTranslatef(LEN * 4, 0, 0);
        cube(colors[0]);
    glPopMatrix();

    // Orelha direita
    glPushMatrix();
        glScalef(.25, .4, .25);
        glTranslatef(-LEN * 4, 0, 0);
        cube(colors[0]);
    glPopMatrix();

    // Nariz
    glPushMatrix();
        glScalef(.4, .4, .4);
        glTranslatef(0, -LEN * 0.7, -LEN * 2.75);
        cube(colors[0]);
    glPopMatrix();

    // Bigode e Sobrancelhas
    glPushMatrix();
        glScalef(.9, .24, .4);
        glTranslatef(0, -LEN * 2.25, -LEN * 2.1);
        cube(colors[4]);

        glScalef(0.3, 0.55, 1);
        glTranslatef(LEN * 2, LEN * 8, 0);
        cube(colors[4]);

        glTranslatef(-LEN * 4, 0, 0);
        cube(colors[4]);
    glPopMatrix();

    // Chapéu
    glPushMatrix();
        glRotatef(5, 1, 0, 0);
        glScalef(1.3, .13, 1.5);
        glTranslatef(0, LEN * 5, -LEN * 0.23);
        cube(colors[1]);

        glScalef(0.98, 2.5, 0.78);
        glTranslatef(0, LEN * 1, LEN * 0.25);
        cube(colors[1]);

        glScalef(0.4, 0.4, 0.2);
        glTranslatef(0, LEN * 1.2, -LEN * 4.2);
        cube(colors[3]);
    glPopMatrix();

    // Torso, pernas e pés
    glPushMatrix();
        glScalef(.75, .8, .6);
        glTranslatef(0, -LEN * 1.2, 0);
        cube(colors[2]);

        glScalef(0.95, .6, .6);
        glTranslatef(0, -LEN * 2, 0);
        cube(colors[2]);

        glScalef(1.01, 0.20, 1.2);
        glTranslatef(0, -LEN * 4, -LEN * 0.1);
        cube(colors[4]);

        glScalef(.7, 1.5, 1.35);
        glTranslatef(0, LEN * 8, 0);
        cube(colors[1]);
    glPopMatrix();

    // Braços direito
    glPushMatrix();
        glRotated(50, 0, 0, 1);
        glScalef(.22, .4, .25);
        glTranslatef(-LEN * 2, -LEN * 4, 0);
        cube(colors[1]);

        glScalef(1.65, 0.9, 1.65);
        glTranslatef(0, -LEN * 1.2, 0);
        cube(colors[3]);
    glPopMatrix();

    // Braço esquerdo
    glPushMatrix();
        glRotated(50, 0, 0, -1);
        glScalef(.22, .4, .25);
        glTranslatef(LEN * 2, -LEN * 4, 0);
        cube(colors[1]);

        glScalef(1.65, 0.9, 1.65);
        glTranslatef(0, -LEN * 1.2, 0);
        cube(colors[3]);
    glPopMatrix();

    // Olho direito
    glPushMatrix();
        glScalef(.25, .35, .2);
        glTranslatef(LEN * 2.1, LEN * 0.5, -LEN * 4.2);
        cube(colors[3]);

        glScalef(.5, 1, 1);
        glTranslatef(-LEN, 0, -LEN * 0.1);
        cube(colors[2]);
    glPopMatrix();

    // Olho esquerdo
    glPushMatrix();
        glScalef(.25, .35, .2);
        glTranslatef(-LEN * 2.1, LEN * 0.5, -LEN * 4.2);
        cube(colors[3]);

        glScalef(.5, 1, 1);
        glTranslatef(LEN, 0, -LEN * 0.1);
        cube(colors[2]);
    glPopMatrix();

    // Base
    glPushMatrix();
        glScalef(1.5, 0.2, 1.5);
        glTranslatef(0, -LEN * 13, 0);
        cube(colors[5]);
    glPopMatrix();

    glutSwapBuffers(); // Troca os buffers (necessário para animação)
}

void resize(int width, int height) {
    // Calcula a posição para centralizar a janela
    int screenWidth = glutGet(GLUT_SCREEN_WIDTH);
    int screenHeight = glutGet(GLUT_SCREEN_HEIGHT);
    int windowX = (screenWidth - width) / 2;
    int windowY = (screenHeight - height) / 2;

    // Posiciona a janela centralizada
    glutPositionWindow(windowX, windowY);
}

void mouseMotion(int x, int y) {
    int deltaX = x - lastX;
    int deltaY = y - lastY;

    rotateX -= deltaY; // Atualiza a rotação no eixo X
    rotateY -= deltaX; // Atualiza a rotação no eixo Y

    lastX = x;
    lastY = y;

    glutPostRedisplay(); // Marca a janela como precisando ser redesenhada
}

void mouse(int button, int state, int x, int y) {
    if (button == GLUT_LEFT_BUTTON && state == GLUT_DOWN) {
        lastX = x;
        lastY = y;
        glutMotionFunc(mouseMotion); // Registra a função de movimento do mouse
    } else if (button == GLUT_LEFT_BUTTON && state == GLUT_UP) {
        glutMotionFunc(NULL); // Desregistra a função de movimento do mouse
    }
}

int main(int argc, char **argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH); // Modo de exibição

    int windowWidth = 400, windowHeight = 400;
    glutInitWindowSize(windowWidth, windowHeight);
    glutCreateWindow("cube"); // Cria a janela com o título "cube"

    init();
    glutReshapeFunc(resize); // Registra a função de redimensionamento da janela
    glutDisplayFunc(design); // Registra a função de desenho da cena
    glutMouseFunc(mouse); // Registra a função de interação do mouse
    glutMainLoop(); // Inicia o loop principal do OpenGL

    return 0;
}

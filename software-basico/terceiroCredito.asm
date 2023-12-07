# Feito por: Everaldina e Alberto
#
# controla com teclas awsd
# objetivo eh deixar tds as plataformas amarelas


.kdata
ktemp:	.space 20

# Overwrites previous handler defined in exceptions.s
.ktext 0x80000180
	
la $k1, ktemp
sw $a0, 0($k1)
sw $a1, 4($k1)
sw $v0, 8($k1)
sw $ra, 12($k1)
sw $at, 16($k1)


# hardware interrupt
mfc0 $a0, $13  # Cause
andi $v0, $a0, 0x0100
beq $v0, $zero, e_int_keyrecv_end
# keyboard receive interrupt

xor $a0, $a0, $v0
mtc0 $a0, $13  # Cause
li $a0, 0xffff0000
lw $a0, 4($a0) #valor digitado

#chama funcao pra trocar tela
jal change_screen

e_int_keyrecv_end:
la $k1, ktemp
lw $a0, 0($k1)
lw $a1, 4($k1)
lw $v0, 8($k1)
lw $ra, 12($k1)
lw $at, 16($k1)
	
	
mfc0 $k0, $12  # Status
ori $k0, 0x01  # re-enable interrupts
mtc0 $k0, $12  # Status
eret



#$s4 --> tela atual
#$s5 --> valor x do boneco
#$s6 --> valor y do boneco
change_screen:
	#atribui ponto de retorno pra plataforma em loop
	la $k0, plataforma_loop
	
	# ve qual tecla foi pressionada e qual acao tem que ser feita
	# a0 = tecla precionada
	beq $a0, 97, esquerda # tecla a
	beq $a0, 65, esquerda # tecla A
	beq $a0, 100, direita # tecla d
	beq $a0, 68, direita # tecla D
	beq $a0, 119, cima # tecla w
	beq $a0, 87, cima # tecla W
	beq $a0, 115, baixo # tecla s
	beq $a0, 83, baixo # tecla S
sair:
	mtc0 $k0, $14 #ponto de retorno eh atribuido ao EPC
	jr $ra

# caso acao de ir para esquerda
esquerda:
	#casos que nao podem ir pra esquerda
	beq $s4, 0, sair
	beq $s4, 3, sair
	beq $s4, 6, sair
	
	la $k0, boneco_plataforma #muda ponto de retorno para refazer a tela
	sb $s4, plataforma_ant # plataforma anterior é atualizada
	
	#se puder ir pra esquerda 
	#plataforma decrementa
	# eixo x decrementa
	addi $s4, $s4, -1
	addi $s5, $s5, -12
	j sair

# caso acao de ir para direita
direita:
	#casos que nao podem ir pra direita
	beq $s4, 2, sair
	beq $s4, 5, sair
	beq $s4, 8, sair
	
	la $k0, boneco_plataforma #muda ponto de retorno para refazer a tela
	sb $s4, plataforma_ant # plataforma anterior é atualizada
	
	#se puder ir pra esquerda 
	#plataforma incrementa
	# eixo x incrementa
	addi $s4, $s4, 1
	addi $s5, $s5, 12
	j sair
	
# caso acao de ir para cima
cima:
	#casos que nao podem ir pra cima
	beq $s4, 2, sair
	beq $s4, 0, sair
	beq $s4, 1, sair
	
	la $k0, boneco_plataforma #muda ponto de retorno para refazer a tela
	sb $s4, plataforma_ant # plataforma anterior é atualizada
	
	#se puder ir pra esquerda 
	#plataforma decrementada
	# eixo y decrementado
	addi $s4, $s4, -3
	addi $s6, $s6, -10
	j sair
	
# caso acao de ir para baixo			
baixo:
	#casos que nao podem ir pra cima
	beq $s4, 6, sair
	beq $s4, 7, sair
	beq $s4, 8, sair
	
	la $k0, boneco_plataforma #muda ponto de retorno para refazer a tela
	sb $s4, plataforma_ant # plataforma anterior é atualizada
	
	#se puder ir pra esquerda 
	#plataforma incrementada
	# eixo y incrementado
	addi $s4, $s4, 3
	addi $s6, $s6, 10
	j sair



# Demo for painting
#
# Bitmap Display Configuration:
# - Unit width in pixels: 8					     
# - Unit height in pixels: 8
# - Display width in pixels: 256
# - Display height in pixels: 256
# - Base Address for Display: 0x10008000 ($gp)
#
# controla com teclas awsd
# objetivo eh deixar tds as plataformas amarelas


# Feito por: Everaldina e Alberto

.data
	displayAddress:	.word	0x10008000
	plataforma_cor: .byte 0, 0, 0, 0, 0, 0, 0, 0, 0  #vetor com cor das plataformas
	plataforma_ant: .space 4
.text
main:
	lw $s0, displayAddress	# $s0 stores the base address for display
	
	#cores
	li $s1, 0xff950a
	li $s2, 0x969696
	li $s3, 0x6e4600
	
	#config de interrupcoes
	li $a0, 0xffff0000
	lw $t0, 0($a0)
	ori $t0, 0x02  # use keyboard interrupts
	sw $t0, 0($a0)
	mfc0 $t0, $12  # Status
	ori $t0, 0x01  # interrupts enable
	mtc0 $t0, $12
	
	#config do cenario inicial na plataforma 0
	# valores boneco plataforma0: a0 = 2  | a1 = 5  
	li $s4, 0 #$s4 = numero da plataforma
	sb $s4, plataforma_ant #plataforma anterior igual a atual
	li $s5, 2 #$s5, eixo x
	li $s6, 8 #$s6, eixo y


# valores boneco plataforma0: a0 = 2  | a1 = 8 
# valores boneco plataforma1: a0 = 14 | a1 = 8
# valores boneco plataforma2: a0 = 26 | a1 = 8
# valores boneco plataforma3: a0 = 2  | a1 = 18
# valores boneco plataforma4: a0 = 14 | a1 = 18 
# valores boneco plataforma5: a0 = 26 | a1 = 18 
# valores boneco plataforma6: a0 = 2  | a1 = 28
# valores boneco plataforma7: a0 = 14 | a1 = 28
# valores boneco plataforma8: a0 = 26 | a1 = 28



#imprime boneco em cima de plataforma $s4
boneco_plataforma:
	jal limpa_tela #limpa posicao anterior do boneco
	
	#ativa a plataforma atual e imprime o fundo novamente
	move $a0, $s4
	jal ativar_plataforma
	jal fundo
	
	#checa se chegou ao final	
	jal check_fim
	beq $v0, 1, Exit

plataforma_loop: #imprime o boneco na plataforma repetidas vezes
        move $a0, $s5
        move $a1, $s6
        jal boneco
	j plataforma_loop
	
Exit:
	#imprime tela final
	jal tela_fim
	li $v0, 10 # terminate the program gracefully
	syscall
	
#fim_main


linhah:	
#a2 = cor da linha
#a1 = linha
#a0 = coluna
	addi $sp, $sp, -4
       	sw $ra, 0($sp)

	li $t5, 0
	sll $t6, $a1, 7  #$a1 x 128 eixo y
	sll $t7, $a0, 2  #$a0 x 4 eixo x
	addu $t4, $t6, $t7 #endere�o
	addu $t4, $t4, $s0
	
Linha:
       addiu $t5,$t5, 1
       	sw $a2, ($t4)	
       	addiu $t4, $t4, 4
       	bne $t5, 6, Linha
	
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	
	jr $ra
	
boneco:
	addi $sp, $sp, -4
	sw $ra, 0($sp)

	# Primeira linha (amarela)
	sll $t6, $a1, 7  # $a1 x 128 (eixo y)
	sll $t7, $a0, 2  # $a0 x 4 (eixo x)
	addu $t4, $t6, $t7  # endereço
	addu $t4, $t4, $s0
	sw $s1, 0($t4)    
	sw $s1, 4($t4)
	sw $s1, 8($t4)
	sw $s1, 12($t4)

	# parte cinza
	addiu $t4, $t4, -128
	sw $s2, 0($t4)    
	sw $s2, 4($t4)
	sw $s2, 8($t4)
	sw $s2, 12($t4)

	#parte marrom
	addiu $t4, $t4, -128
	sw $s3, 0($t4)
	sw $s3, 4($t4)
	sw $s3, 8($t4)
	sw $s3, 12($t4)

	# parte dina marrom
	addiu $t4, $t4, -128
	sw $s3, 4($t4)
	sw $s3, 8($t4)

	addiu $t4, $t4, -128
	sw $s3, 4($t4)
	sw $s3, 8($t4)

	lw $ra, 0($sp)
	addi $sp, $sp, 4

	jr $ra

#pinta fundo (plataformas)
fundo:
	addi $sp, $sp, -4
        sw $ra, 0($sp)


	#pinta plataforma 0
	li $a0, 0
	jal get_color # pega a cor que plataforma deve ser pintada
	move $a2, $v0 #move cor resultado para paremetro de cor de linha
	#chama funcao pra implimir linha da plataforma 0
	li $a1, 10
	li $a0, 1
	jal linhah
	
	#pinta plataforma 1
	li $a0, 1
	jal get_color # pega a cor que plataforma deve ser pintada
	move $a2, $v0 #move cor resultado para paremetro de cor de linha
	#chama funcao pra implimir linha da plataforma 1
	li $a1, 10
	li $a0, 13
	jal linhah
	
	#pinta plataforma 2
	li $a0, 2
	jal get_color # pega a cor que plataforma deve ser pintada
	move $a2, $v0 #move cor resultado para paremetro de cor de linha
	#chama funcao pra implimir linha da plataforma 2
	li $a1, 10
	li $a0, 25
	jal linhah
	
	#pinta plataforma 3
	li $a0, 3
	jal get_color# pega a cor que plataforma deve ser pintada
	move $a2, $v0 #move cor resultado para paremetro de cor de linha
	#chama funcao pra implimir linha da plataforma 3
	li $a1, 20
	li $a0, 1
	jal linhah
	
	#pinta plataforma 4
	li $a0, 4
	jal get_color # pega a cor que plataforma deve ser pintada
	move $a2, $v0 #move cor resultado para paremetro de cor de linha
	#chama funcao pra implimir linha da plataforma 4
	li $a1, 20
	li $a0, 13
	jal linhah
	
	#pinta plataforma 5
	li $a0, 5
	jal get_color # pega a cor que plataforma deve ser pintada
	move $a2, $v0 #move cor resultado para paremetro de cor de linha
	#chama funcao pra implimir linha da plataforma 5
	li $a1, 20
	li $a0, 25
	jal linhah
	
	#pinta plataforma 6
	li $a0, 6
	jal get_color # pega a cor que plataforma deve ser pintada
	move $a2, $v0 #move cor resultado para paremetro de cor de linha
        #chama funcao pra implimir linha da plataforma 6
        li $a1, 30
	li $a0, 1
	jal linhah
	
	#pinta plataforma 7
	li $a0, 7
	jal get_color # pega a cor que plataforma deve ser pintada
	move $a2, $v0 #move cor resultado para paremetro de cor de linha
	#chama funcao pra implimir linha da plataforma 7
	li $a1, 30
	li $a0, 13
	jal linhah
	
	#pinta plataforma 8
	li $a0, 8
	jal get_color # pega a cor que plataforma deve ser pintada
	move $a2, $v0 #move cor resultado para paremetro de cor de linha
	#chama funcao pra implimir linha da plataforma 8
	li $a1, 30
	li $a0, 25
	jal linhah
	
	
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	
	jr $ra
	
#limpa o boneco da posicao anterior
limpa_tela:
	addi $sp, $sp, -4
        sw $ra, 0($sp)
        
        #coloca todas as cores em preto
	li $s1, 0x000000
	li $s2, 0x000000
	li $s3, 0x000000
	
	lb $t0, plataforma_ant #pega valor da plataforma anterior 
	
	#switch para limpar plataforma anterior
	bne $t0, 0, cont_lp1
		#limpa plataforma 0
		li $a1, 8
		li $a0, 2
		jal boneco
	cont_lp1:
	bne $t0, 1, cont_lp2	
		#limpa plataforma 1
		li $a1, 8
		li $a0, 14
		jal boneco
	cont_lp2:
	bne $t0, 2, cont_lp3
		#limpa plataforma 2
		li $a1, 8
		li $a0, 26
		jal boneco
	cont_lp3:
	bne $t0, 3, cont_lp4
		#limpa plataforma 3
		li $a1, 18
		li $a0, 2
		jal boneco
	cont_lp4:
	bne $t0, 4, cont_lp5
		#limpa plataforma 4
		li $a1, 18
		li $a0, 14
		jal boneco
	cont_lp5:
	bne $t0, 5, cont_lp6
		#limpa plataforma 5
		li $a1, 18
		li $a0, 26
		jal boneco
	cont_lp6:
	bne $t0, 6, cont_lp7
		#limpa plataforma 6
		li $a1, 28
		li $a0, 2
		jal boneco
	cont_lp7:
	bne $t0, 7, cont_lp8
		#limpa plataforma 7
		li $a1, 28
		li $a0, 14
		jal boneco
	cont_lp8:
	bne $t0, 8, fim_lp
		#limpa plataforma 8
		li $a1, 28
		li $a0, 26
		jal boneco
fim_lp:
	#retorna cor original para os registradores
	li $s1, 0xff950a
	li $s2, 0x969696
	li $s3, 0x6e4600
	
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	
	jr $ra

#percorre o vetor de cores de plataforma
# se todas estiverem em um o jogo acaba
check_fim:
	addi $sp, $sp, -4
        sw $ra, 0($sp)
        
	li $v0, 0
	li $t0, 0 # i = 0
	la $t1, plataforma_cor # endereco base do vetor de cor
	
	loop:
	beq $t0, 9, ganhou # se chegou todas as platormas foram verifica jogador ganhou
		add $t2, $t1, $t0 #addiciona i ao endereco base
		lb $t2, 0($t2) # pega bit bit do vetor
		beq $t2, 0, exit_cf # se algum bit for zero o jogo ainda nao acabou
		addi $t0, $t0, 1 #i++
		j loop	
	
#se nao for encontrado nenhum zero saida eh verdadeira	
ganhou:
	li $v0, 1
exit_cf:
	addi $sp, $sp, -4
        sw $ra, 0($sp)
	jr $ra



#retorna em $v0 a cor que uma plataforma tem que ser pintada
#a0 = numero da plataforma
get_color:
	addi $sp, $sp, -4
        sw $ra, 0($sp)
        
        #pega a cor padrao da platagora
        move $v0, $s3
        
        
        la $t0, plataforma_cor
        add $t0, $t0, $a0 #adiciona o numero da plataforma ao endereco base
        lb $t0, ($t0) #pega o bit de ativacao da plataforma
        
        # se o bit for zero ja era na cor padrao
        # se nao for zero troca a cor para a cor de ativado
        beqz $t0, exit
        li $v0, 0xffff00
        
        exit:
        lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra
	
#troca a cor da plataforma atual
#a0 = numero da plataform
ativar_plataforma:
	addi $sp, $sp, -4
        sw $ra, 0($sp)
        
        
        la $t0, plataforma_cor
        add $t0, $t0, $a0 # soma plataforma ao endereco base
        lb $t1, ($t0) # pega bit de estado plataforma
        
        #inverte bit de estado da plataforma
        not $t1, $t1
        sb $t1, ($t0)
        
        lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra
	
#imprime a palavra fim nada tela	
tela_fim:
	li $t0, 0x7f7f7f
	li $t1, 0xffffffff
	
	#F coluna1
	sw    $t0,  1556($s0)
	sw    $t0,  1684($s0)
	sw    $t0,  1812($s0)
	sw    $t0,  1940($s0)
	sw    $t0,  2068($s0)
	sw    $t0,  2196($s0)
	sw    $t0,  2324($s0)
	sw    $t0,  2452($s0)
	sw    $t0,  2580($s0)
	sw    $t0,  2708($s0)
	#F coluna2
	sw    $t0,  1432($s0)
	sw    $t1,  1560($s0)
	sw    $t1,  1688($s0)
	sw    $t1,  1816($s0)
	sw    $t1,  1944($s0)
	sw    $t1,  2072($s0)
	sw    $t1,  2200($s0)
	sw    $t1,  2328($s0)
	sw    $t1,  2456($s0)
	sw    $t1,  2584($s0)
	sw    $t1,  2712($s0)
	sw    $t0,  2840($s0)
	#F coluna3
	sw    $t0,  1308($s0)
	sw    $t1,  1436($s0)
	sw    $t1,  1564($s0)
	sw    $t1,  1692($s0)
	sw    $t1,  1820($s0)
	sw    $t1,  1948($s0)
	sw    $t1,  2076($s0)
	sw    $t1,  2204($s0)
	sw    $t0,  2332($s0)
	sw    $t0,  2460($s0)
	sw    $t0,  2588($s0)
	sw    $t0,  2716($s0)
	#F coluna4
	sw    $t0,  1312($s0)
    	sw    $t1,  1440($s0)
    	sw    $t1,  1568($s0)
    	sw    $t1,  1696($s0)
    	sw    $t0,  1952($s0)
    	sw    $t1,  2080($s0)
    	sw    $t1,  2208($s0)
    	sw    $t0,  2336($s0)
    	#F coluna5
    	sw    $t0,  1316($s0)
    	sw    $t1,  1444($s0)
    	sw    $t1,  1572($s0)
    	sw    $t1,  1700($s0)
    	sw    $t0,  1956($s0)
    	sw    $t1,  2084($s0)
    	sw    $t1,  2212($s0)
    	sw    $t0,  2340($s0)
    	#F coluna6
    	sw    $t0,  1320($s0)
    	sw    $t1,  1448($s0)
    	sw    $t1,  1576($s0)
    	sw    $t1,  1704($s0)
    	sw    $t0,  1960($s0)
    	sw    $t1,  2088($s0)
    	sw    $t1,  2216($s0)
    	sw    $t0,  2344($s0)
    	#F coluna7
    	sw    $t0,  1324($s0)
    	sw    $t1,  1452($s0)
    	sw    $t1,  1580($s0)
    	sw    $t0,  1708($s0)
    	sw    $t0,  2092($s0)
    	sw    $t0,  2220($s0)
    	#F coluna8
    	sw    $t0,  1456($s0)
    	sw    $t0,  1584($s0)
    	
    	
	#i coluna1
	sw    $t0, 2228($s0)
	sw    $t0, 2356($s0)
	sw    $t0, 2484($s0)
	sw    $t0, 2612($s0)
	sw    $t0, 2740($s0)
	#i coluna2
	sw    $t0, 1464($s0)
	sw    $t0, 1592($s0)
	sw    $t0, 2104($s0)
	sw    $t1, 2232($s0)
	sw    $t1, 2360($s0)
	sw    $t1, 2488($s0)
	sw    $t1, 2616($s0)
	sw    $t1, 2744($s0)
	sw    $t0, 2872($s0)
	#i coluna3
	sw    $t0, 1340($s0)
	sw    $t1, 1468($s0)
	sw    $t1, 1596($s0)
	sw    $t0, 1724($s0)
	sw    $t0, 1980($s0)
	sw    $t1, 2108($s0)
	sw    $t1, 2236($s0)
	sw    $t1, 2364($s0)
	sw    $t1, 2492($s0)
	sw    $t1, 2620($s0)
	sw    $t1, 2748($s0)
	sw    $t0, 2876($s0)
	#i coluna4
	sw    $t0, 1344($s0)
	sw    $t1, 1472($s0)
	sw    $t1, 1600($s0)
	sw    $t0, 1728($s0)
	sw    $t0, 1984($s0)
	sw    $t0, 2112($s0)
	sw    $t0, 2240($s0)
	sw    $t0, 2368($s0)
	sw    $t0, 2496($s0)
	sw    $t0, 2624($s0)
	sw    $t0, 2752($s0)
	#i coluna5
	sw    $t0, 1476($s0)
	sw    $t0, 1604($s0)
	
	#m coluna1
    	sw    $t0,  1992($s0)
    	sw    $t0,  2120($s0)
    	sw    $t0,  2248($s0)
    	sw    $t0,  2376($s0)
    	sw    $t0,  2504($s0)
    	sw    $t0,  2632($s0)
    	sw    $t0,  2760($s0)
    	#m coluna2
    	sw    $t0,  1868($s0)
    	sw    $t1,  1996($s0)
    	sw    $t1,  2124($s0)
    	sw    $t1,  2252($s0)
    	sw    $t1,  2380($s0)
    	sw    $t1,  2508($s0)
    	sw    $t1,  2636($s0)
    	sw    $t1,  2764($s0)
    	sw    $t0,  2892($s0)
    	#m coluna3
    	sw    $t0,  1872($s0)
    	sw    $t1,  2000($s0)
    	sw    $t1,  2128($s0)
    	sw    $t1,  2256($s0)
    	sw    $t1,  2384($s0)
    	sw    $t1,  2512($s0)
    	sw    $t1,  2640($s0)
    	sw    $t1,  2768($s0)
    	sw    $t0,  2896($s0)
    	#m coluna4
    	sw    $t0,  2004($s0)
    	sw    $t1,  2132($s0)
    	sw    $t1,  2260($s0)
    	sw    $t1,  2388($s0)
    	sw    $t0,  2516($s0)
    	sw    $t0,  2644($s0)
    	sw    $t0,  2772($s0)
    	#m coluna5
    	sw    $t0,  1880($s0)
    	sw    $t1,  2008($s0)
    	sw    $t1,  2136($s0)
    	sw    $t1,  2264($s0)
    	sw    $t1,  2392($s0)
    	sw    $t1,  2520($s0)
    	sw    $t1,  2648($s0)
    	sw    $t1,  2776($s0)
    	sw    $t0,  2904($s0)
    	#m coluna6
    	sw    $t0,  1884($s0)
    	sw    $t1,  2012($s0)
    	sw    $t1,  2140($s0)
    	sw    $t1,  2268($s0)
    	sw    $t1,  2396($s0)
    	sw    $t1,  2524($s0)
    	sw    $t1,  2652($s0)
    	sw    $t1,  2780($s0)
    	sw    $t0,  2908($s0)
    	#m coluna7
    	sw    $t0, 2016($s0)
	sw    $t1, 2144($s0)
	sw    $t1, 2272($s0)
	sw    $t1, 2400($s0)
	sw    $t0, 2528($s0)
	sw    $t0, 2656($s0)
	sw    $t0, 2784($s0)
    	#m coluna8
    	sw    $t0,  1892($s0)
    	sw    $t1,  2020($s0)
    	sw    $t1,  2148($s0)
    	sw    $t1,  2276($s0)
    	sw    $t1,  2404($s0)
    	sw    $t1,  2532($s0)
    	sw    $t1,  2660($s0)
    	sw    $t1,  2788($s0)
    	sw    $t0,  2916($s0)
    	#m coluna9
    	sw    $t0,  1896($s0)
    	sw    $t1,  2024($s0)
    	sw    $t1,  2152($s0)
    	sw    $t1,  2280($s0)
    	sw    $t1,  2408($s0)
    	sw    $t1,  2536($s0)
    	sw    $t1,  2664($s0)
    	sw    $t1,  2792($s0)
    	sw    $t0,  2920($s0)
	#m coluna10
    	sw    $t0,  2028($s0)
    	sw    $t0,  2156($s0)
    	sw    $t0,  2284($s0)
    	sw    $t0,  2412($s0)
    	sw    $t0,  2540($s0)
    	sw    $t0,  2668($s0)
    	sw    $t0,  2796($s0)
	
	jr $ra

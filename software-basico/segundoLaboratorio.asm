.data
tamanhoFrase: .asciiz "\nDigite o tamanho de sua frase: "
pergunta: .asciiz "\nDigite sua frase:\n"
vetor: .space 32
repetidos: .space 32
frequencia: .space 32

.text
j main

# Complexidade theta(n)
lerVetor:
	# recebendo os parametros
	la $t0, tamanhoFrase	# salvando o endereco de tamanhoFrase
	la $t1, pergunta 	# salvando o endereco de pergunta
	
	# imprimindo a pergunta na tela
	move $a0, $t0
	li $v0, 4
	syscall
	
	# lendo tamanho do vetor do usuario
	li $v0, 5
	syscall
	
	# salvando o tamanho do vetor
	move $t2, $a0
	
	# imprimindo a pergunta na tela
	move $a0, $t1
	li $v0, 4
	syscall
	
	# processo de leitura da string
	move $a1, $t2
	la $a0, vetor
	li $v0, 8
	syscall
	
	# salvando o tamanho do vetor no retorno da funcao
	move $v0, $t2
	# retornando para main
	jr $ra

# Complexidade O(n^2)
verificaSeRepete:
# IDEALIZACAO DA FUNCAO
#
#
# int temp;
#int vetor_1[tamanho];
#int vetor_2[tamanho];
#bool leu = false;
#
#for(int i = 0; i < tamanho; i++) {
#	temp = vetor_1[i];
#	
#	for(int j = 0; j < tamanho; j++) {
#		if(a == vetor_1[j] && !leu) {
#			vetor_2[i] = a;
#			leu = true;
#		}
#	}
#	
#	leu = false;
#}

	# recebendo os parametros
	move $t0, $a0	# o vetor
	move $t1, $a1	# o tamanho do vetor
	
	la $t2, repetidos	# salvando o endereco do vetor repetidos em $t2
	addi $t1, $t1, -1	# trabalhando no intervalo de [0..9]
	li $t3, -1		# condicao de parada
	li $t4, 0		# variavel de controle >> booleana
	
	# loop que itera sobre o vetor *repetidos*
	loopExterno:
		# verifica se $t1 == -1
		# se for, vai para fimLoopExterno
		beq $t1, $t3, fimLoopExterno
		
			# se $t1 >= 0, entao continua aqui
			lb $t5, 0($t0)
			move $t7, $t0
		
			# usando a pilha para preservar o contador $t1
			addi $sp, $sp, -4
			sw $t1, 0($sp)
			# usando a pilha para preserva o ponteiro para o vetor *vetor* salvo em $t0
			addi $sp, $sp, -8
			sb $t0, 4($sp)
		
			# $t0 vamos utilizar o ponteiro $t0 no loop interno tambem
			move $t0, $a0
			# $t1 tambem servira como contador para o loop interno
			li $t1, 9
		
			# loop que itera pelo vetor *vetor*
			loopInterno:
				# verifica se $t1 == -1
				# se for, vai para fimLoopInterno
				beq $t1, $t3, fimLoopInterno
					# se $t1 >= 0, entao continua aqui
					lb $t6, 0($t0)
			
					# verifica se $t5 == $t6
					# se for, vamos verificar se a variavel de controle $t4 == 0
					bne $t5, $t6, diferentes
						# verifica se $t4 != 0
						# se for, o valor lido ja foi armazenado no vetor *repetidos*
						bne $t4, $zero, diferentes
							# se nao foi lido, devemos ler e salvar o valor em *repetidos*
							sb $t6, 0($t7)	# salvando o valor de $t6 no vetor *repetidos*
							li $t4, 1	# ativando a variavel de controle
				diferentes:
					# passando para a proxima posicao do vetor *vetor*
					addi $t0, $t0, 8 
					# decrementando $t1
					addi $t1, $t1, -1
			j loopInterno
			
		fimLoopInterno:
		# reinicializando $t4 >> variavel de controle
		li $t4, 0
		
		# retomando o ponteiro para o vetor *vetor* salvo na pilha
		lb $t0, 4($sp)
		addi $sp, $sp, 8
		# retomando o valor do contador $t3 da pilha
		lw $t1, 0($sp)
		addi $sp, $sp, 4
		
		# passando para a proxima posicao do vetor *vetor*
		addi $t0, $t0, 8 
		# decrementando $t1
		addi $t1, $t1, -1
	
	fimLoopExterno:
		# a funcao nao retorna nada, pois os vetores sao globais
		# retornando a funcao main
		jr $ra

# Complexidade O(n^2)
contaFrequencia:
# IDEALIZACAO DA FUNCAO
#
#
#int contador;
#int vetor[tamanho];
#int repetidos[tamanho];
#int frequencia[tamanho];
#
#for(int i = 0; i < tamanho; i++) {
#	contador = 0;
#	
#	for(int j = 0; j < tamanho; j++) {
#		if(repetidos[i] == vetor[j]) {
#			contador++;
#		}
#	}
#	
#	frequencia[i] = contador;
#}
	# recebendo os parametros
	move $t0, $a0	# o vetor
	move $t1, $a1	# o tamanho do vetor
	move $t2, $a2	# vetor dos repetidos
	move $t3, $a3	# vetor de frequencias
	
	la $s5, vetor	# usaremos para nao perder o ponteiro para o original
	li $t4, 0	# variavel contador de iteracoes
	li $t7, 9	# condicao de parada dos *for*
	li $s7, 0	# nosso contador
	
	forExterno:
		# verificando se o contador $t4 >= 9
		bge $t4, $t7, fimForExterno
			# se $t4 < 9, entao continua aqui
			lw $t5, 0($t2)	# lendo do vetor *repetidos*
		
			# salvando $t4 na pilha, pois iremos usar para o forInterno tambem
			addi $sp, $sp, -4
			sw $t4, 0($sp)
			
			li $t4, 0	# reinicializando $t4 com 0
			lw $t6, 0($t0)	# lendo do vetor *vetor*
			move $s5, $t0	# recebendo a referencia
		
			forInterno:
				# verificando se o contador $t4 >= 9
				bge $t4, $t7, fimForExterno
					# se $t4 < 9, entao continua aqui 
					# verificando se $t5 != $t6
					bne $t5, $t6, naoSaoIguais
						# se $t5 == $t6, entao continua aqui
						addi $s7, $s7, 1
					
					naoSaoIguais:
						# iterando sobre o vetor *vetor*
						addi $s5, $s5, 8
						
						# lendo da nova posicao
						lw $t6, 0($s5)
					
						# incrementando o contador de lacos
						addi $t4, $t4, 1
						
						# fazendo o loop
						j forInterno
			fimForInterno:
				# lendo $t4 da pilha
				lw $t4, 0($sp)
				addi $sp, $sp, 4
				
				# incrementando $t4
				addi $t4, $t4, 1
			
				# iterando sobre o vetor *repetidos*
				addi $t2, $t2, 8
				
				# salvando o valor de $s7 no vetor de frequencia
				sw $s7, 0($t3)
				
				# iterando sobre o vetor *frequencia*
				li $s6, 1
				sll $s6, $s6, 2
				add $t3, $t3, $s6
			
	fimForExterno:
		# a funcao nao precisa retornar nada, pois os vetores sao globais
		# retornado para main
		jr $ra

main:
	la $t0, tamanhoFrase	# salvando o endereco de tamanhoFrase
	la $t1, pergunta 	# salvando o endereco de pergunta
	
	# passando os parametros para a funcao
	move $a0, $t0
	move $a1, $t1 
	# chamando funcao LerVetor
	jal lerVetor
	# guardando o retorno da funcao
	move $s0, $v0
	
	# passando os parametros
	la $a0, vetor	# vetor
	move $a1, $s0	# tamanho do vetor
	# chamando a funcao verificaSeRepete
	jal verificaSeRepete

	# passando os parametros
	la $a0, vetor		# o vetor
	move $a1, $s1		# o tamanho do vetor
	la $a2, repetidos	# vetor dos repetidos
	la $a3, frequencia	# vetor de frequencias
	# chamando a funcao contaFrequencia
	jal contaFrequencia
	
	# encerrando o programa
	li $v0, 10
	syscall
	

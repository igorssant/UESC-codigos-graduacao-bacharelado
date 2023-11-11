.data
b: .word 3, 0, 10, 2, 4, 10, 3, 7, 8, 9
explicaX: .asciiz "\nA quandidade de vezes que o maior valor aparece no vetor e: "
explicaY: .asciiz "\nO maior valor contido no vetor e: "

.text
j main	# vai diretamente para main

maior:
	# salvando os parametros passados
	move $t0, $a0	# vetor
	move $t1, $a1	# quantidade de elementos no vetor
	
	# para realizar o if
	li $t2, 1
	
	# se tamanho do vetor == 1, entao esta posicao ja e a maior
	beq $t1, $t2, retorna
	
	# o vetor nao possui tamanho == 1
	lw $t2, 0($t0)	# posicao n
	lw $t3, 4($t0)	# posicao n+1
	
	# verifica se valor na posicao n < n+1
	blt $t2, $t3, recursao
	
	# se nao for menor, salva n atual na posicao n+1
	# pega o dado da posicao n+1 salva na posicao n
	sw $t2, 4($t0)
	sw $t3, 0($t0)
		
	recursao:
		addi $t6, $0, 1
		sll $t4, $t6, 2
		add $t4, $t4, $t0
		
		# passando os parametros
		move $a0, $t4
		addi $t1, $t1, -1
		move $a1, $t1
		
		# salvando $ra atual na pilha
		addi $sp, $sp, -4
		sw $ra, 4($sp)
		
		# chamando a funcao recursivamente
		jal maior

	retorna:
		lw $t6, 0($t0)
		move $v0, $t6
		
		# retomando o valor de $ra da pilha
		lw $ra, 4($sp)
		addi $sp, $sp, 4
		
		jr $ra

quantidadeMaior:
	# salvando os parametros passados
	move $t1, $a0 	# vetor
	move $t2, $a1 	# o maior valor no vetor
	move $t3, $a2 	# quantidade de elementos no vetor
	
	li $t0, 0		# modifica $t0 para 0 caso nao esteja zerado
	lw $t4, 0($t1)		# $t4 sera utilizado para iterar pelo vetor
	addi $t3, $t3, -1	# vamos trabalhar com o intervalo de 0..9
	
	DO:
		# verifica se $t4 != $t2
		bne $t2, $t4, WHILE
		# se sao iguais
		addi $t0, $t0, 1	# $t0 = $t0 + 1
		
		WHILE:
			addi $t3, $t3, -1	# $t3 = $t3 - 1
			
			# verifica se $t3 == 0
			blt $t3, $0, END
			
			# se $t3 != 0, entao continua o codigo
			# iterando pelo vetor
			addi $t5, $0, 1
			sll $t4, $t5, 2
			add $t1, $t4, $t1
			
			# carrega o dado na proxima posicao do vetor
			lw $t4, 0($t1)
			j DO
			
	END:
		# salvando o retorno da funcao
		move $v0, $t0
		
		# retornando a main
		jr $ra

main:
	# tamanho do vetor
	# salvando o tamanho em $s1
	li $s1, 10
	
	# passsando o parametros para a funcao maior
	la $a0, b	# vetor
	move $a1, $s1	# quantidade de elementos no vetor
	
	# chamando a funcao maior
	jal maior
	
	# salvando o retorno da funcao maior
	move $s2, $v0
	
	# imprime o texto de maior valor no vetor
	la $a0, explicaY
	li $v0, 4
	syscall
	
	# imprime o maior valor
	move $a0, $s2
	li $v0, 1
	syscall
	
	# passando os parametros para a funcao quantidadeMaior
	la $a0, b	# vetor
	move $a1, $s2	# o maior valor no vetor
	move $a2, $s1	# quantidade de elementos no vetor
	
	# chamando a funcao quantidadeMaior
	jal quantidadeMaior
	
	# salvando o retorno da funcao quantidadeMaior
	move $s3, $v0
	
	# imprime o texto de ocorrencias do maior numero
	la $a0, explicaX
	li $v0, 4
	syscall
	
	# imprime o numero de ocorrencias
	move $a0, $s3
	li $v0, 1
	syscall
	
	# encerrando o programa
	li $v0, 10
	syscall
	
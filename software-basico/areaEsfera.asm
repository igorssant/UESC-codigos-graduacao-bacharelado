.data
pedirvalor: .asciiz "Digite o valor do raio da esfera: "
textoarea: .asciiz "A area da circuferencia eh: "
PI: .float 3.1416

.text
j main

lerValor:
	# Recebendo os parametros passados
	move $t0, $a0
	
	# Passando os parametros para imprimir texto na tela
	move $a0, $t0
	# Fazendo chamada ao sistema para imprimir texto na tela
	li $v0, 4
	syscall
	
	# Fazendo chamada ao sistema para ler Float do teclado
	li $v0, 6
	syscall
	
	# Salvando valor lido do teclado em um registrador
	move $f1, $f0
	
	# Salvando valor de retorno da rotina
	move $f12, $f1
	# Retornando a funcao main
	jr $ra

areaEsfera:
	# Recebendo os parametros passados
	move $f1, $f0
	
	
	
	
	# Salvando valor de retorno da rotina
	move $f12, $f1
	# Retornando a funcao main
	jr $ra

main:
	# texto para pedir o valor
	la $s0, pedirvalor
	# texto para imprimir o resultado final
	la $s1, textoarea
	# valor de PI
	lwc1 $f2, PI
	
	# Passando os parametros para a funcao
	move $a0, $s0
	# chamando a funcao de leitura do teclado
	jal lerValor
	
	# Salvando retorno da funcao
	move $f1, $f0
	
	# Passando os parametros para a funcao
	move $f0, $f1
	move $
	# salvando o valor de $f1 original na pilha
	addi $sp, $sp, -32
	swc1 $f1, 0($sp)
	# chamando a funcao areaEsfera
	jal areaEsfera
	
	# Retomando o valor original de $f1 da pilha
	lwc1 $f1, 0($sp)
	addi $sp, $sp, 32
	
	# Salvando o retorno da funcao em $f2
	move $f2, $f12
	
	# Passando os parametros para a funcao
	move $f0, $f2
	move $a0, $s1
	# chamando a funcao de impressao do resultado
	jal imprimeResultado
	
	# Finalizando o programa
	li $v0, 10
	syscall
	
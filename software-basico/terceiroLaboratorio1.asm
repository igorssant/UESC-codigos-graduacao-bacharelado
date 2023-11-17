.data
primeiro: .asciiz "\n Digite o primeiro valor inteiro: "
segundo: .asciiz "\n Digite o segundo valor inteiro: "
terceiro: .asciiz "\n mdc("
virgula: .asciiz ", "
igual: .asciiz ") = "

.text
# pulando direto para main
j main

restoDivisao:
	# recebendo os parametros
	move $t2, $a0	# primeiro valor
	move $t3, $a1	# segundo valor
	
	# realizando a divisao $t2 / $t3 e salvando em $t2	
	div $t2, $t2, $t3
	# pegando o resultado do registrador hi
	mfhi $t2
	# salvando o retorno em $v0
	move $v0, $t2
	#retornando para a funcao pai
	jr $ra

gcd:
	# recebendo os parametros
	move $t0, $a0	# primeiro valor
	move $t1, $a1	# segundo valor
	
	li $t7, 1
	# verificando se $t1 > 1
	bgt $t1, $t7, else
		# se $t1 <= 0, entao continua aqui
		move $v0, $t0	# salvando o valor de retorno em $v0
		jr $ra		# retornando o valor salvo
	else:
	
	# salvando a pilha o endereco de retorno
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	
	# passando os parametros para a funcao gcd
	move $a0, $t0	# primeiro valor
	move $a1, $t1	# segundo valor
	# chamando a funcao restoDivisao
	jal restoDivisao
	
	# retomando da pilha o endereco de retorno
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	
	# salvando $t1 em $t0
	move $t0, $t1
	# salvando o valor de retorno em $t1
	move $t1, $v0
	
	# passando os parametros para a chamada recursiva
	move $a0, $t0
	move $a1, $t1
	
	# salvando a pilha o endereco de retorno
	addi $sp, $sp, -4
	sw $ra, 0($sp)
	# chamando a funcao recursivamente
	jal gcd
	
	# retomando da pilha o endereco de retorno
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	
	# retornando para main
	jr $ra

main:
	# imprimindo o primeiro vetor na tela
	la $a0, primeiro
	li $v0, 4
	syscall
	
	# recebendo o valor lido
	li $v0, 5
	syscall
	
	# salvando o valor lido em $s0
	move $s0, $v0

	# imprimindo o primeiro vetor na tela
	la $a0, segundo
	li $v0, 4
	syscall
	
	# recebendo o valor lido
	li $v0, 5
	syscall
	
	# salvando o valor lido em $s1
	move $s1, $v0
	
	# passando os parametros para a funcao gcd
	move $a0, $s0	# primeiro valor lido
	move $a1, $s1	# segundo valor lido
	# cahamndo a funcao gcd
	jal gcd

	# salvando o retorno em $s2
	move $s2, $v0
	
	# imprimindo o terceiro vetor
	la $a0, terceiro
	li $v0, 4
	syscall
	
	# imprimindo o primeiro valor digitado
	li $v0, 1
	move $a0, $s0
	syscall
		
	# imprimindo a virgula
	la $a0, virgula
	li $v0, 4
	syscall

	# imprimindo o segundo valor digitado
	li $v0, 1
	move $a0, $s1
	syscall
	
	# imprimindo o sinal de igual
	la $a0, igual
	li $v0, 4
	syscall
	
	# imprimindo o resultado da operacao
	li $v0, 1
	move $a0, $s2
	syscall

	# encerrando o programa
	li $v0, 10
	syscall
	

.data
	descricao: .asciiz "O VALOR EH: "
	estiloParede: .byte 0
.text

j main

# gera um numero aleatorio no alcance e retorna o valor em $v0
rangeNumber:
	# salvando o ponteiro de retorno na pilha
	sw $ra, 0($sp)
	addi $sp, $sp, -4
	
	# $a0 == i.d. of pseudorandom number generator
	# $a1 == upper bound of range of returned values
	li $v0, 42
	syscall
	
	# retomando da pilha o ponteiro de retorno da funcao
	addi $sp, $sp, 4
	lw $ra, 0($sp)
	
	# passando valor de retorno
	# e retornando para main
	move $v0, $a0
	jr $ra

main:
	li $s0, 1
	li $s1, 2
	
	# passando os parametros para a funcao
	# e chamando a funcao abaixo
	move $a0, $s0
	move $a1, $s1
	jal rangeNumber
	
	# salvando o retorno da funcao
	move $s2, $v0
	
	# imprimindo a mensagem na tela
	la $a0, descricao
	li $v0, 4
	syscall
	
	# imprimindo o valor aleatorio
	li $v0, 1
	move $a0, $s2
	syscall
	
	la $s3, estiloParede
	lb $a0, 0($s3)
	syscall
	
	sb $s2, 0($s3)
	
	# return 0
	li $v0, 10
	syscall
	
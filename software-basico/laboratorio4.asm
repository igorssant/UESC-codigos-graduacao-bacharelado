.data
primeiro: .asciiz "Digite o primeiro valor: "
segundo: .asciiz "Digite o segundo valor: "
resultado: .asciiz "O resultado e: "

.text
j main # vai diretamente para main

menor:
	# recebendo os parametros
	move $t0, $a0
	move $t1, $a1
	
	# verificando se $t0 > $t1
	bge $t0, $t1, maiorif
	j menorif # se nao for maior, va para menor-if
	
	# condicao do if -> verdadeira
	maiorif:
		move $v0, $t1
		j retorno
	
	# condicao if -> falsa
	menorif:
		move $v0, $t0
		
	# retorna o menor valor entre $t0 e $t1
	retorno:
		jr $ra

main:
	# pede o primeiro valor
	la $a0, primeiro
	li $v0, 4
	syscall
	
	# salva o primeiro valor
	li $v0, 5
	syscall
	move $s0, $v0
	
	# pede o segundo valor
	la $a0, segundo
	li $v0, 4
	syscall

	# salva o sergundo valor
	li $v0, 5
	syscall
	move $s1, $v0
	
	# passando os parametros
	move $a0, $s0
	move $a1, $s1
	
	# chama a funcao menor
	jal menor
	
	# salvando o retorno
	move $s2, $v0
	
	# mensagem final
	la $a0, resultado
	li $v0, 4
	syscall
	
	# imprimindo o valor final para o usuario
	move $a0, $s2
	li $v0, 1
	syscall
	
	# encerrando programa
	li $v0, 10
	syscall
	
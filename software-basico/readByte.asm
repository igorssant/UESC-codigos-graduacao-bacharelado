.data
	valor: .byte 4
.text

main:
	la $s0, valor
	lb $s1, 0($s0)
	sb $zero, 0($s0)
	li $s2, 0x1111
	lb $s2, 0($s0)
	# return 0
	li $v0, 10
	syscall
	




# chamando a funcao que gera numeros aleatorios
	# SEED = 1, topo = 2
	# ira gerar numeros inteiro entre 0 e 1
	li $a0, 1
	li $a1 2
	jal rangeNumber
	
	sw $s0, 4($sp)
	sw $s1, 8($sp)
	addi $sp, $sp, -8
	
	# pegando o retorno da funcao rangeNumber
	# e salvando seu valor em estiloParede
	move $s1, $v0
	la $s0, estiloParede
	sb $s1, 0($s0)
	
	addi $sp, $sp, 8
	sw $s1, 8($sp)
	lw $s0, 4($sp)

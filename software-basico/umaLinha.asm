.data
	displayAddress: .word 0x10008000
.text
	lw $t0, displayAddress	# $t0 stores the base address for display
	li $t1, 0xff0000	# $t1 stores the red colour code
	li $t2, 0x00ff00	# $t2 stores the green colour code
	li $t3, 0x0000ff	# $t3 stores the blue colour code

jal planoDeFundo

Exit:
	li $v0, 10 # terminate the program gracefully
	syscall

coluna:	
       	addi $sp, $sp, -4
	sw $ra, 0($sp)

	li $t5, 0
	sll $t6, $a1, 7  #$a1 x 128 eixo y
	sll $t7, $a0, 2  #$a0 x 4 eixo x
	addu $t4, $t6, $t7 #endere�o
	addu $t4, $t4, $t0
	
	forInterno:
    	   	addiu $t5, $t5, 1
    	   	sw $t3, ($t4)	
     	  	addiu $t4, $t4, 128
     	  	bne $t5, 6, forInterno
	
	lw $ra, 0($sp)
	addi $sp, $sp, 4	
	jr $ra

linhah:	
	addi $sp, $sp, -4
	sw $ra, 0($sp)

	li $t5, 0
	sll $t6, $a1, 7  #$a1 x 128 eixo y
	sll $t7, $a0, 2  #$a0 x 4 eixo x
	addu $t4, $t6, $t7 #endere�o
	addu $t4, $t4, $t0
	
	Linha:
       		addiu $t5,$t5, 1
       		sw $t3, ($t4)	
    	   	addiu $t4, $t4, 4
     	  	bne $t5, 6, Linha
	
	lw $ra, 0($sp)
	addi $sp, $sp, 4	
	jr $ra


planoDeFundo:
	addi $sp, $sp, -4
       	sw $ra, 0($sp)

	li $s0, 1
	li $s1, 0
	
	bne $s0, $s1, tipo2
		li $a1, 1
		li $a0, 9
		jal coluna
	
		li $a1, 22
		li $a0, 9
		jal coluna
	
		li $a1, 10
		li $a0, 20
		jal coluna


	tipo2:
		li $a1, 1
		li $a0, 20
		jal coluna
	
		li $a1, 22
		li $a0, 20
		jal coluna
	
		li $a1, 10
		li $a0, 9
		jal coluna
	
	li $a1, 30
	li $a0, 2
	jal linhah

	li $a1, 18
	li $a0, 12
	jal linhah
	
	li $a1, 7
	li $a0, 25
	jal linhah
	
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra

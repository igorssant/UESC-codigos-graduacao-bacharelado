.data
b: .word 3, 0, 1, 2, 4, 10, 3, 7, 8, -9
.text
j main

max:
	li $t0, 1
	lw $t1, 0($a0)
	FOR:
		blt $t0, $t1, DO
		j END
		DO:
			# 
		
	END:	
	
	jr $ra
	
main:
	la $s1, b
	li $s0, 0
	addiu $a0, $0, $s1
	li $a1, 10
	jal max
	addi $0, $v0, 1
	li $v0, 10
	syscall
	
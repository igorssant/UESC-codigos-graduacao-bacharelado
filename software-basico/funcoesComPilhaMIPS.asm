# A PILHA ESTA LOCALIZADA EM
# 0x 7FFF FFFC
#
# NESTE CASO, ESTA EM:
# 0x 7FFF EFFC
#
# LEMBRANDO: ISSO EH SOMENTE A BASE
#
.text
j main

diffofsums:
	addi $sp, $sp, -12
	sw $s0, 8($sp)
	sw $t0, 4($sp)
	sw $t1, 0($sp)
	add $t0, $a0, $a1
	add $t1, $a2, $a3
	sub $s0, $t0, $t1
	add $v0, $s0, $0
	lw $t1, 0($sp)
	lw $t0, 4($sp)
	lw $s0, 8($sp)
	addi $sp, $sp, 12
	jr $ra
	
main:
	li $s0, 27
	li $t0, 51
	li $t1, 38
	li $a0, 2
	li $a1, 3
	li $a2, 4
	li $a3, 5
	jal diffofsums
	add $s0, $v0, $0
	li $v0, 10
	syscall

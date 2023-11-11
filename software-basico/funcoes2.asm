# VERSAO 1
#
#.text
#main:
#	li $s0, 0
#	li $a0, 2
#	li $a1, 3
#	li $a2, 4
#	li $a3, 5
#	jal diffofsums
#	add $s0, $v0, $0
#	li $v0, 10
#	syscall
#	
#diffofsums:
#	add $t0, $a0, $a1
#	add $t1, $a2, $a3
#	sub $v0, $t0, $t1
#	jr $ra

# VERSAO 2
#
.text
j main

diffofsums:
	add $t0, $a0, $a1
	add $t1, $a2, $a3
	sub $v0, $t0, $t1
	jr $ra
	
main:
	li $s0, 0
	li $a0, 2
	li $a1, 3
	li $a2, 4
	li $a3, 5
	jal diffofsums
	add $s0, $v0, $0
	li $v0, 10
	syscall

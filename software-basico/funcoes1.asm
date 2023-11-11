# VERSAO 1
#
#.text
#main:
#	jal simple
#	add $0, $1, $2
#	li $v0, 10
#	syscall
#
#simple:
#	jr $ra

# VERSAO 2
#
.text
j main

simple:
	jr $ra

main:
	li $s0, 0
	li $s1, 1
	li $s2, 2
	jal simple
	add $0, $1, $2
	li $v0, 10
	syscall

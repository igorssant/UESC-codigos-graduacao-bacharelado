.text
j main

sum:
	add $v0, $a0, $a1
	jr $ra
	
main:
	li $s0, 4
	li $s1, 0
	li $s2, 5
	add $a0, $0, $s0
	add $a1, $0, $s2
	jal sum
	add $s1, $v0, $0
	li $v0, 10
	syscall

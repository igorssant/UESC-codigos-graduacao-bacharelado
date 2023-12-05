##################################
# Must enable memory-mapped I/O! #
##################################


################
# Handler Data #
################

.kdata
ktemp:	.space 16
#hex:	.ascii  "0123456789abcdef"  # table for quick hex conversion
#exc:	.ascii  "\texception:"
#spc:	.asciiz " "
#timer:	.asciiz "\ttimer expired... and reset\n"
key:	.ascii  "\tkey: "
char:	.ascii  " "
nl:	.asciiz "\n"

##########################
# Handler Implementation #
##########################

	# Overwrites previous handler defined in exceptions.s
	.ktext 0x80000180
	
	move $k0, $at
	

	la $k1, ktemp
	sw $a0, 0($k1)
	sw $a1, 4($k1)
	sw $v0, 8($k1)
	sw $ra, 12($k1)

        

	
	# hardware interrupt
		mfc0 $a0, $13  # Cause
		#jal print_hex
		andi $v0, $a0, 0x0100
		beq $v0, $zero, e_int_keyrecv_end
		# keyboard receive interrupt

		xor $a0, $a0, $v0
		mtc0 $a0, $13  # Cause

		li $a0, 0xffff0000
		lw $v0, 4($a0) #valor digitado
		la $a0, char
		sb $v0, ($a0) #coloca o valor digitado no char

		la $a0, key
		
		jal print_string
   e_int_keyrecv_end:

	la $k1, ktemp
	lw $a0, 0($k1)
	lw $a1, 4($k1)
	lw $v0, 8($k1)
	lw $ra, 12($k1)

	
	move $at, $k0
	
	mfc0 $k0, $12  # Status
	ori $k0, 0x01  # re-enable interrupts
	mtc0 $k0, $12  # Status
	eret


###############################
# print_string Implementation #
###############################

print_string: # $a0: string
	j ps_cond
ps_loop:
	lw $v0, 0xffff0008
	andi $v0, $v0, 0x01
	beq $v0, $zero, ps_loop
	sw $a1, 0xffff000c
ps_cond:
	lbu $a1, ($a0)
	addi $a0, $a0, 1
	bne $a1, $zero, ps_loop
	jr $ra





#######################
# Program Entry Point #
#######################

	.text
	.globl main
main:
	li $a0, 0xffff0000
	lw $t0, 0($a0)
	ori $t0, 0x02  # use keyboard interrupts
	sw $t0, 0($a0)

	mfc0 $t0, $12  # Status
	ori $t0, 0x01  # interrupts enable
	mtc0 $t0, $12  # Status

	
	

loop:
	nop
	nop
	nop
	nop
	nop
	nop
	nop
	nop
	nop
	nop
	nop
	nop
	nop
	nop
	nop
	nop
	j loop


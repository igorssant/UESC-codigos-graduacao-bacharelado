# Demo for painting
#
# Bitmap Display Configuration:
# - Unit width in pixels: 8					     
# - Unit height in pixels: 8
# - Display width in pixels: 256
# - Display height in pixels: 256
# - Base Address for Display: 0x10008000 ($gp)
#
.data
	displayAddress:	.word	0x10008000
.text
	lw $t0, displayAddress	# $t0 stores the base address for display
	li $t1, 0xff0000	# $t1 stores the red colour code
	li $t2, 0x00ff00	# $t2 stores the green colour code
	li $t3, 0xffff00	# $t3 stores the yellow colour code
	
	sw $t1, 0($t0)	 # paint the first (top-left) unit red. 
	sw $t2, 4($t0)	 # paint the second unit on the first row green. Why $t0+4?
	sw $t3, 128($t0) # paint the first unit on the second row blue. Why +128?
Exit:
	li $v0, 10 # terminate the program gracefully
	syscall

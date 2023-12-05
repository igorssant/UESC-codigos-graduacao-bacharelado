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
	li $t3, 0x0000ff	# $t3 stores the blue colour code
	
	#sw $t1, 0($t0)	 # paint the first (top-left) unit red. 
	#sw $t2, 4($t0)	 # paint the second unit on the first row green. Why $t0+4?
	#sw $t3, 128($t0) # paint the first unit on the second row blue. Why +128?
	#li $t5,0
	#addiu $t4, $t0, 3844 #posição de inicio da reta
tela1:	
	li $a1, 28
	li $a0, 2
        jal boneco
        
        jal fundo
        
        
	
	li $v0, 32          # sleep
        li $a0, 1000 # sleep por 500 ms
        syscall
	
	
tela2:
	li $a1, 28
	li $a0, 2
	li $t1, 0x000000
        jal boneco
        li $t1, 0xff0000
        
        li $a1, 15
	li $a0, 13
        jal boneco
        
        jal fundo
	
	li $v0, 32          # sleep
        li $a0, 1000 # sleep por 500 ms
        syscall
  
 tela3:
	li $a1, 15
	li $a0, 13
	li $t1, 0x000000
        jal boneco
        li $t1, 0xff0000
        
        li $a1, 5
	li $a0, 26
        jal boneco
        
	jal fundo
	
	
	
	
Exit:
	li $v0, 10 # terminate the program gracefully
	syscall
	
linhah:	
       addi $sp, $sp, -4
       sw $ra, 0($sp)

	li $t5, 0
	sll $t6, $a1, 7  #$a1 x 128 eixo y
	sll $t7, $a0, 2  #$a0 x 4 eixo x
	addu $t4, $t6, $t7 #endereço
	addu $t4, $t4, $t0
	
Linha:
       addiu $t5,$t5, 1
       	sw $t3, ($t4)	
       	addiu $t4, $t4, 4
       	bne $t5, 6, Linha
	
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	
	jr $ra
	
boneco:

	addi $sp, $sp, -4
        sw $ra, 0($sp)
	
	
	sll $t6, $a1, 7  #$a1 x 128 eixo y
	sll $t7, $a0, 2  #$a0 x 4 eixo x
	addu $t4, $t6, $t7 #endereço
	addu $t4, $t4, $t0
	sw $t1, ($t4)
	sw $t1, 8($t4)
	
	addi $a1, $a1, -1
	addi $a0, $a0, 1
	
	#li $a1, 27
	#li $a0, 3
	
	sll $t6, $a1, 7  #$a1 x 128 eixo y
	sll $t7, $a0, 2  #$a0 x 4 eixo x
	addu $t4, $t6, $t7 #endereço
	addu $t4, $t4, $t0
	sw $t1, ($t4)
	
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	
	jr $ra
	
fundo:

	addi $sp, $sp, -4
        sw $ra, 0($sp)

        li $a1, 30
	li $a0, 1
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

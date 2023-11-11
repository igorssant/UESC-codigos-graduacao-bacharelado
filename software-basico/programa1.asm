#.text
#li $s2, 10
#li $s3, 15
#add $s1, $s2, $s3


#.text
#li $s2, 0X0000efff
#li $s3, 15
#add $s1, $s2, $s3


#.text
#li $s2, 0X00008fff
#li $s3, 15
#add $s1, $s2, $s3


#.text
#li $s2, 0X000f7fff
#li $s3, 15
#add $s1, $s2, $s3


#.text
#li $s2, 0X00017fff
#li $s3, 15
#add $s1, $s2, $s3


# ESSE CODIGO DA OVER-FLOW
#.text
#li $s2, 0X00000001
#li $s3, 0x7fffffff
#add $s1, $s2, $s3


# PROVA QUE li EH MELHOR QUE addi PARA ATRIBUICOES
#.text
#li $s2, 0X7fffffff
#addi $s1, $0, 0X7fffffff


# EXEMPLO 12
#.text
#li $s1, 5	# a
#li $s2, 10	# b
#li $s3, 15	# c
#li $s4, 21	# d
# ----
#add $t0, $s1, $s2
#sub $t0, $s3, $t0
#sub $s0, $t0, $s4


# EXEMPLO 13
#.data
#C:.word 3, 0, 1, 2, -6, -2, 4, 10, 3, 7, 8, -9, -15, -20, -87, 0 
#B: .word 30
#.text
#lw $t0, B
#la, $t1, c
#lw $t2, 


# EXEMPLO 14
#.data
#C:.word 3, 0, 1, 2, -6, -2, 4, 10, 3, 7, 8, -9, -15, -20, -87, 0 
#B: .word 30
#A: .word
#.text
#lw $t0, B
#la, $t1, C
#lw $t2, 12($t1)
#add $t3, 


#.text
#addi $t0, $0, 12


.text
li $s0, 56
li $s1, 24
sub $s2, $s0, $s1 



















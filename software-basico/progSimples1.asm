.data
pergunta: .asciiz "entre o valor de x:\n"

.text
li $t0, 0
li $t1, 0
la $a0, pergunta

li $v0, 4
syscall

li $v0, 5
syscall

add $t0, $v0, $0
addi $t1, $t0, 5
add $a0, $0, $t1
li $v0, 1
syscall

li $v0, 10
syscall

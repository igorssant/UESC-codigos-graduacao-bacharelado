.data
string: .asciiz "HELLO WORLD!!!\n"
.text
la $a0, string
li $v0, 4
syscall
li $v0, 10
